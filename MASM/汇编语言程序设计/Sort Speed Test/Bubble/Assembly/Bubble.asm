data1 segment
    include data1.inc
    n1 dw 4000
data1 ends

data2 segment
    include data2.inc
    n2 dw 4000
data2 ends

data3 segment
    include data3.inc
    n3 dw 4000
data3 ends

data4 segment
    include data4.inc
    n4 dw 4000
data4 ends

data5 segment
    include data5.inc
    n5 dw 4000
data5 ends

code segment
    main proc far
		;Group1
    assume cs:code,ds:data1
start:
    mov ax,data1
    mov ds,ax
    mov ax,offset dat1
    push ax;压数组元素首地址
    push n1;压需要排序的元素个数
    call Bubble;调用冒泡排序子程序,由大到小排序
    ;Group2
    assume cs:code,ds:data2
    mov ax,data2
    mov ds,ax
    mov ax,offset dat2
    push ax;压数组元素首地址
    push n2;压需要排序的元素个数
    call Bubble;调用冒泡排序子程序,由大到小排序
    ;Group3
    assume cs:code,ds:data3
    mov ax,data3
    mov ds,ax
    mov ax,offset dat3
    push ax;压数组元素首地址
    push n3;压需要排序的元素个数
    call Bubble;调用冒泡排序子程序,由大到小排序
     ;Group4
    assume cs:code,ds:data4
    mov ax,data4
    mov ds,ax
    mov ax,offset dat4
    push ax;压数组元素首地址
    push n4;压需要排序的元素个数
    call Bubble;调用冒泡排序子程序,由大到小排序
     ;Group3
    assume cs:code,ds:data5
    mov ax,data5
    mov ds,ax
    mov ax,offset dat5
    push ax;压数组元素首地址
    push n5;压需要排序的元素个数
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