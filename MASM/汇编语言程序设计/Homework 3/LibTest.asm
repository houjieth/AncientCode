include MathLib.mac
data segment
    A dw 32
    B dw -9
    RESULT dw ?
data ends

code segment
    main proc far
    assume cs:code,ds:data
start:
    mov ax,data
    mov ds,ax
    ;main code
    logarithm A,RESULT
    
    ;exit
    mov ax,4c00h
    int 21h
    main endp
code ends
    end start