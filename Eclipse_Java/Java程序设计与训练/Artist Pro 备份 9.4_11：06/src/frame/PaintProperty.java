package frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class PaintProperty {
	public BasicStroke currentStroke;
	public String currentStrokeName;
	public boolean currentLined;
	public Color currentLineColor;
	public int currentFillType;
	public Color currentFillColor;
	public GradientPaint currentGradientFillColor;
	public boolean transformable;
	public PaintProperty(){
		currentStroke=new BasicStroke(4.0f);
		currentStrokeName=new String("Continuum");
		currentLined=true;
		currentLineColor=Color.BLACK;
		currentFillType=FillType.SOLID;
		currentFillColor=Color.RED;
		currentGradientFillColor=new GradientPaint(0,0,Color.BLACK,200,200,Color.WHITE);
		boolean transfromable=false;
	}
}
