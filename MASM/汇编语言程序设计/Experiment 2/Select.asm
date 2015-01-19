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
    call Select;调用选择排序子程序,由大到小排序
    ;exit
    mov ax,4c00h
    int 21h
    main endp
    
    Select proc near
    mov bp,sp
    mov dx,[bp+2];元素个数存于dx
    mov bx,[bp+4];数组首地址存于bx
    cmp dx,1
    je exit;如果只剩一个元素就直接返回
    mov si,bx;从现在bx用来存此轮发现的最小值的元素的地址
    mov cx,dx
next:
    mov ax,[si]
    cmp ax,[bx]
    jae cont1;si所指向的元素比bx所指向的元素值大，不管它
    mov bx,si;否则要更新bx
cont1:
    add si,2
    loop next
    ;循环退出后，si指向最后一个元素的后面空间
    sub si,2;si重新指向最后一个元素
    mov ax,[si]
    mov cx,[bx]
    cmp ax,cx;看最小的是不是已经在最后面
    je cont2;如果是就无所谓了
    xchg ax,cx;否则将最小的与最后面的交换
    mov [si],ax
    mov [bx],cx;写回内存
cont2:
    dec dx;这一轮排n个数，下一轮排n-1个数
    push [bp+4];向下一次递归压数组首地址
    push dx;以及需要排序的元素个数
    call Select
exit:
    ret 4
    Select endp
code ends
    end start