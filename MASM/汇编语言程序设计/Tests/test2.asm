; example.asm
data1 segment
string1         db      'thanks you'
string2         db      'thanks you'
mass1         db      'match!',13,10,'$'
mass2         db      'no match!',13,10,'$'
data1 ends        

program segment
main    proc far
        assume cs:program, ds:data1
begin:
        push    ds
        sub     ax,ax
        push    ax     

        mov     ax,data1
        mov     ds,ax
        mov     es,ax
        add     ax,1    
        lea     si,string1
        lea     di,string2
        cld
        mov     cl,10
        repne    cmpsb
          
        jz       match
        lea      dx,mass2

        jmp     short disp
match:
        lea     dx,mass1
disp:
        mov     ah,09
        int      21h
        ret
main endp    
program ends
        end begin     
