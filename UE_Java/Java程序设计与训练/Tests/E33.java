public class E33{
/**
* &与&&运算符
*/
public static void main(String args[]){
/**
* 声明int 变量
*/
int i,j;
i=0;
j=0;
if(false&(++i<100)){
System.out.println("will this be displayed?"); //(1) no
}
if(false&&(++j<100)){
System.out.println("will this be displayed?"); //(2) no
}
System.out.println("i="+i); //(3)i=1
System.out.println("j="+j); //(4)j=0
}
}