import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class GraExp1 extends JFrame
{
   Container c;
   JTextField tf=new JTextField(10);
   public GraExp1()
   {
      c=getContentPane();
      c.setLayout(new FlowLayout());
      tf.setText("���磬��ã�");
      c.add(tf);
      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      setSize(new Dimension(400,300));
      show();
   }
   public void paint(Graphics g)
   {
      super.paint(g);
      Dimension d=getSize();
      int x=(int)d.getWidth();
      int y=(int)d.getHeight();
      g.clearRect(50,50,200,200);
      String s=Integer.toString(x);
      String s1=Integer.toString(y);
      g.drawString(s,100,100);
      g.drawString(","+s1,140,100);
   }
 
   public static void main(String[] args)
   {
      GraExp1 ge=new GraExp1();
   }
}
