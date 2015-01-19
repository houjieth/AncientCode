data_seg segment
    arrayA db 1,3,4,6,9,12,22,40,50,57;demo array
    arrayB db 2,3,4,7,9,15,22,33,40,57;demo array
    arrayC db 10 dup(?)
data_seg ends

code_seg segment
    main proc far
    assume cs:code_seg,ds:data_seg
start:
    mov ax,data_seg
    mov ds,ax
    mov es,ax;IMPORTANT!!!
    
    cld
    mov bx,0;bx is used for the index of arrayC
    mov dx,0
    mov si,0
    mov dx,10;dx is used for the count for outer loop
    
inner:
    mov cx,10;cx is used for the count for inner loop
    lea di,arrayB
    mov al,arrayA[si]
    repne scasb
    jne NotFound
Found:
    mov arrayC[bx],al;append an element to arrayC
    inc bx
NotFound:
    inc si
    dec dx
    jz exit
    jmp inner    
    
exit:
    mov ax,4c00h
    int 21h
    main endp
code_seg ends
    end start