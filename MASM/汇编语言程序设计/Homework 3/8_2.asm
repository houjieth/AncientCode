code segment
    main proc far
    assume cs:code
start:
    mov ah,2
    mov dl,54h
    mov bx,0
    mov ds,bx
    mov bx,84h
    call dword ptr [bx]
    ;exit
    mov ax,4c00h
    int 21h
    main endp
code ends
    end start
    