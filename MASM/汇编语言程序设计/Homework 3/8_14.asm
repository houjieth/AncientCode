;---------------------------------------------------------
;---------------------------------------------------------
data segment
    time dw 0
    Dec_0_Outputable db 0 	;(used when Dec output) 0:'0' unoutputable, 1:'0' outputable
    const1 dw 1000
    const2 dw 18
data ends
;---------------------------------------------------------
;---------------------------------------------------------
code segment
;---------------------------------------------------------
    main proc far
    assume cs:code,ds:data
start:
    mov ax,data
    mov ds,ax
   
    ;main program
    ;backup old interrupt program
    mov al,1ch
    mov ah,35h
    int 21h
    push es
    push bx
    
    push ds
    
    ;write new interrupt program
    mov dx,offset timing
    mov ax,seg timing
    mov ds,ax
    mov al,1ch
    mov ah,25h
    int 21h
    
    pop ds
    ;set interrupt mask bits
    in al,21h
    and al,11111110b
    out 21h,al

    mov bx,0;bx is used to count interruption times
    
    ;FROM HERE YOU CAN PLACE THE PROGRAM THAT YOU WANT TO 
    ;COUNT THE RUNNING TIME 
    mov di,50000
delay1:
    mov si,55000
delay2:
    dec si
    jnz delay2
    dec di
    jnz delay1
    ;PROGRAM ENDS HERE
    

    ;restore interrupt program
    pop dx
    pop ds
    mov al,1ch
    mov ah,25h
    int 21h
    
    ;restore ds
    mov ax,data
    mov ds,ax
    
    ;convert to millisecond
    mov ax,bx
    mov cx,const1
    mul cx
    mov dx,0
    mov cx,const2
    div cx
    mov bx,ax
    ;display running time
    call BIN_DEC
    ;output "ms"
    mov dl,6dh
    mov ah,2
    int 21h
    mov dl,73h
    mov ah,2
    int 21h
    ;exit 
    mov ax,4c00h
    int 21h
    
    main endp
;--------------------------------------------------------- 
    timing proc near
    
    inc bx
    iret
    
    timing endp
;---------------------------------------------------------
    BIN_DEC proc near
    ;the number to be changed must be saved in bx
    add bx,0 		;in order to get sign flag
    jns next	
outputSign:
    mov dl,2dh	;if sf=1, then first output a '-'
    mov ah,2
    int 21h
    neg  bx
next:
    mov  cx,10000d
    call DEC_DIV
    mov  cx,1000d
    call DEC_DIV
    mov  cx,100d
    call DEC_DIV
    mov  cx,10d
    call DEC_DIV
    mov  dl,bl
    add  dl,30h
    mov  ah,2
    int  21h
    ret
    BIN_DEC endp
;---------------------------------------------------------
    DEC_DIV proc near
    ;Screen output, and the number to be divided must be saved in bx
    mov ax,bx
    mov dx,0
    div cx 			;quotient in ax, residue in dx
    mov bx,dx 	;residue is saved in bx, quontient is saved in ax(in fact, al)
    cmp al,0
    jnz output	;for non-zero number, output it directly
    cmp Dec_0_Outputable,0
    je 	exit2
output: 		
    mov dl,al
    add dl,30h
    mov ah,2
    int 21h
    mov Dec_0_Outputable,1
exit2:
    ret
    DEC_DIV endp
;---------------------------------------------------------
code ends
;---------------------------------------------------------
;---------------------------------------------------------
    end start
    
    
    