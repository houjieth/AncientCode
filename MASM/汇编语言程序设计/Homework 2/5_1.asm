data_seg segment
    string1 db 'demo string'
    count equ $-string1
    string2 db count dup(?)
data_seg ends

code_seg segment
    main proc far
    assume cs:code_seg,ds:data_seg
start:
    mov ax,data_seg
    mov ds,ax
    ;start copying
    lea si,string1
    lea di,string2
    mov cx,count
copy:
    mov al,ds:[si]
    mov ds:[di],al
    inc si
    inc di
    dec cx
    jnz copy
    ;exit
    mov ax,4c00h
    int 21h
    main endp
code_seg ends
    end start