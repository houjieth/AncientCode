data segment
    dat dw 16,4,92,35,16,72,34,64,77,1,19,36,26,92,11,62,34,23,76,30,34,72,13,65,33,75,34,23,53,43
    n dw 30
data ends

code segment
    main proc far
    assume cs:code,ds:data
start:
    mov ax,data
    mov ds,ax
    ;main part
    mov ax,offset dat
    push ax;压数组元素首地址
    push n;压需要排序的元素个数
    call Bubble;调用冒泡排序子程序,由大到小排序
    ;exit
    mov ax,4c00h
    int 21h
    main endp
    
    Bubble proc near
    mov bp,sp
    mov dx,[bp+2];元素个数存于dx
    mov si,[bp+4];数组首地址存于bx
    cmp dx,1
    je exit;如果只剩一个元素就直接返回
    mov cx,dx
    dec cx;一轮中n个数只需比较n-1次
next:
    mov ax,[si]
    mov bx,[si+2]
    cmp ax,bx
    jae cont;若前方数大于等于后方数，则不必交换
    xchg ax,bx;否则交换
    mov [si],ax
    mov [si+2],bx;写回数据区
cont:
    add si,2
    loop next
    dec dx;这一轮排n个数，下一轮排n-1个数
    push [bp+4];向下一次递归压数组首地址
    push dx;以及需要排序的元素个数
    call Bubble
exit:
    ret 4
    Bubble endp
code ends
    end start