public class E34{
/**
* 赋值运算符与强制类型转换
*/
public static void main(String args[]){
/**
* 声明int 变量
*/
int i;
/**
* 声明byte 变量
*/
byte b;
/**
* 声明double 变量
*/
double x,y;
/**
* 声明char 变量
*/
char ch;
x=10.0;
y=3.0;
i=(int)(x/y);//cast double to int
System.out.println("Interger outcome of x/y is: "+i); //(1) 3
i=100;
b=(byte)i;
System.out.println("b = "+b); //(2) 100
i=257;
b=(byte)i;
System.out.println("b = "+b);//(3) 1
b=88;
ch=(char)b; 
System.out.println("ch = "+ch); //(4) X
}
}