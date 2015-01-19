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
    push ax;数组首地址进栈
    mov ax,0
    push ax;左下标进栈
    mov ax,n
    dec ax
    push ax;右下标进栈
    call Quick
    ;exit
    mov ax,4c00h
    int 21h
    main endp
    
    Quick proc near
    mov bp,sp
    mov dx,[bp+2];右下标读入dx
    mov cx,[bp+4];左下标读入cx
    mov si,[bp+6];数组首地址读入si
    cmp cx,dx
    jge exit;若左下标大于等于右下标，则不必再排序
    ;先以最左侧元素为基准，进行左右划分
    sal cx,1;现在cx=左下标*2
    add si,cx;现在si指向数组中的下标为left的元素
    push si;备份si
    mov bx,si;bx:=pivotPos
    mov ax,[si];ax:=pivot
    add si,2;现在si指向数组中的下标为(left+1)的元素
    mov dx,[bp+2];右下标读入dx
    mov cx,[bp+4];左下标读入cx
    sub dx,cx;得到循环次数
    mov cx,dx;并存入cx
    
nextLoop:
    cmp [si],ax
    jna next
    add bx,2
    cmp bx,si
    je next
    push cx
    mov cx,[bx]
    mov dx,[si]
    xchg cx,dx
    mov [bx],cx
    mov [si],dx;写回内存
    pop cx
next:
    add si,2
    loop nextLoop
    
    pop si;恢复si，si指向数组中下标为left的元素
    mov dx,[bx]
    mov [si],dx
    mov [bx],ax
    mov cx,[bp+4];左下标读入cx
    mov si,[bp+6];数组首地址读入si
    sub bx,si;bx减去数组首地址得到相对数组首元素的偏移地址
    sar bx,1;偏移地址除以2得到相对数组首元素的下标
    push bx;备份bx
    push si;压数组首地址
    push cx;压左下标
    dec bx
    push bx;压右下标
    call Quick
    
    pop bx;
    mov bp,sp
    mov dx,[bp+2];右下标读入dx
    mov si,[bp+6];数组首地址读入si
    push si;压数组首地址
    add bx,1
    push bx;压左下标
    push dx;压右下标
    call Quick
exit:
    ret 6
    Quick endp
code ends
    end start
    
    