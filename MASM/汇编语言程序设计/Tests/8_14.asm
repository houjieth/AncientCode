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

    mov time,0;time is used to count interruption times
    
    ;FROM HERE YOU CAN PLACE THE PROGRAM THAT YOU WANT TO 
    ;COUNT THE RUNNING TIME 
    ;call far ptr main_sort
    mov di,1000
delay1:
    mov si,1000
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
    mov bx,time
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
    mov ax,data
    mov ds,ax
    inc time
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
data_sort segment
    dat dw 16,4,92,35,16,72,34,64,77,1,19,36,26,92,11,62,34,23,76,30,34,72,13,65,33,75,34,23,53,43
    n dw 30
data_sort ends

code_sort segment
    main_sort proc far
    assume cs:code_sort,ds:data_sort
start_sort:
    mov ax,data_sort
    mov ds,ax
    ;main part
    mov ax,offset dat
    push ax;压数组元素首地址
    push n;压需要排序的元素个数
    call Bubble;调用冒泡排序子程序,由大到小排序
    ret
    main_sort endp
    
    Bubble proc near
    mov bp,sp
    mov dx,[bp+2];元素个数存于dx
    mov si,[bp+4];数组首地址存于bx
    cmp dx,1
    je exit;如果只剩一个元素就直接返回
    mov cx,dx
    dec cx;一轮中n个数只需比较n-1次
next_sort:
    mov ax,[si]
    mov bx,[si+2]
    cmp ax,bx
    jae cont;若前方数大于等于后方数，则不必交换
    xchg ax,bx;否则交换
    mov [si],ax
    mov [si+2],bx;写回数据区
cont:
    add si,2
    loop next_sort
    dec dx;这一轮排n个数，下一轮排n-1个数
    push [bp+4];向下一次递归压数组首地址
    push dx;以及需要排序的元素个数
    call Bubble
exit:
    ret 4
    Bubble endp
code_sort ends
;---------------------------------------------------------
    end start
    
    
    