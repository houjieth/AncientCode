;从键盘上连续打入多个属于‘0’到‘9’的数字符并在屏幕上显示, 遇非数字符结束输入过程。

org 2040h
  MVRD   R2,  0030h	;用于判数字符的下界值
  MVRD   R3,  0039h     ;用于判数字符的上界值

input:
  IN    81h             ;判键盘上是否按了一个键, 
  SHR   R0
  SHR   R0              ;即串行口是否有了输入的字符
  JRNC  input           ;没有输入则循环测试
  IN    80h             ;输入字符到R0
  CMP   R0, R2          ;判输入字符≥字符'0'否
  JRNC  exit            ;为否, 则转到程序结束处
  CMP   R3, R0          ;判输入字符≤字符'9'否
  JRNC  exit            ;为否, 则转到程序结束处
  OUT   80h             ;输出刚输入的数字符
  JMPA  input           ;转去程序前边2044处等待输入下一个字符
 
exit:
  RET
end
