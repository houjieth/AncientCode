package frame;
import handler.DrawHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.util.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
public class MyLittleCanvas extends JPanel{
	MainFrame frame;
	PaintProperty currentPaintProperty;
	Color color1;
	Color color2;
	ShapePro demo;
	
	public MyLittleCanvas(PaintProperty property,MainFrame frm){
		currentPaintProperty=property;
		frame=frm;
		ShapePro demo;
	}
	public void paint(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		
		
		if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
			//下面根据当前绘图属性设置生成预览图像
			switch(DrawHandler.currentSelectedShapePro.shapeType){
			case ShapeType.RECTANGLE:
				demo=new ShapePro(new Rectangle2D.Double(75, 30, 185, 63),DrawHandler.currentSelectedShapePro.stroke,DrawHandler.currentSelectedShapePro.lined,DrawHandler.currentSelectedShapePro.lineColor,DrawHandler.currentSelectedShapePro.fillType,DrawHandler.currentSelectedShapePro.fillColor,DrawHandler.currentSelectedShapePro.gradientFillColor,ShapeType.ROUNDRECTANGLE,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
				break;
			case ShapeType.ROUNDRECTANGLE:
				demo=new ShapePro(new RoundRectangle2D.Double(75, 30, 185, 63,((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).arcwidth,((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).archeight),DrawHandler.currentSelectedShapePro.stroke,DrawHandler.currentSelectedShapePro.lined,DrawHandler.currentSelectedShapePro.lineColor,DrawHandler.currentSelectedShapePro.fillType,DrawHandler.currentSelectedShapePro.fillColor,DrawHandler.currentSelectedShapePro.gradientFillColor,ShapeType.ROUNDRECTANGLE,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
				break;
			case ShapeType.ELLIPSE:
				demo=new ShapePro(new Ellipse2D.Double(75, 30, 185, 63),DrawHandler.currentSelectedShapePro.stroke,DrawHandler.currentSelectedShapePro.lined,DrawHandler.currentSelectedShapePro.lineColor,DrawHandler.currentSelectedShapePro.fillType,DrawHandler.currentSelectedShapePro.fillColor,DrawHandler.currentSelectedShapePro.gradientFillColor,ShapeType.ROUNDRECTANGLE,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
				break;
			case ShapeType.LINE:
				demo=new ShapePro(new Line2D.Double(75, 30, 260, 93),DrawHandler.currentSelectedShapePro.stroke,DrawHandler.currentSelectedShapePro.lined,DrawHandler.currentSelectedShapePro.lineColor,DrawHandler.currentSelectedShapePro.fillType,DrawHandler.currentSelectedShapePro.fillColor,DrawHandler.currentSelectedShapePro.gradientFillColor,ShapeType.ROUNDRECTANGLE,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
				break;
			case ShapeType.TEXT:
			    demo=new TextShapePro("Text",new Font("Arail",Font.PLAIN,90),new Point2D.Double(75,90),DrawHandler.currentSelectedShapePro.stroke,DrawHandler.currentSelectedShapePro.lined,DrawHandler.currentSelectedShapePro.lineColor,DrawHandler.currentSelectedShapePro.fillType,DrawHandler.currentSelectedShapePro.fillColor,DrawHandler.currentSelectedShapePro.gradientFillColor,ShapeType.TEXT,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
			    break;
			}
			//如果是文字对象，先要生成shape
			if(demo.shapeType==ShapeType.TEXT){
				    FontRenderContext context=g2.getFontRenderContext();
					TextShapePro ts=(TextShapePro)(demo);//ts和demo指向同一对象，只不过这两个引用的类型不同
					TextLayout layout=new TextLayout(ts.text,ts.font,context);
					AffineTransform transform=new AffineTransform();
					transform.setToTranslation(ts.position.x, ts.position.y);
					Shape outline=layout.getOutline(transform);
					ts.shape=outline;
					Rectangle2D boundsRect=ts.shape.getBounds2D();
					ts.textHeight=boundsRect.getHeight();
					ts.textWidth=boundsRect.getWidth();
			}
			//设置基本绘图属性
			g2.setStroke(new BasicStroke(DrawHandler.currentSelectedShapePro.stroke.width,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,DrawHandler.currentSelectedShapePro.stroke.pattern,0));
			g2.setColor(DrawHandler.currentSelectedShapePro.lineColor);
			//若有边框则开始绘边框
			if(DrawHandler.currentSelectedShapePro.lined==true)
				g2.draw(demo.shape);
			//开始填充
			if(DrawHandler.currentSelectedShapePro.fillType==FillType.NONE){
				//Don't fill
			}
			if(DrawHandler.currentSelectedShapePro.fillType==FillType.SOLID){
			    g2.setColor(DrawHandler.currentSelectedShapePro.fillColor);
			    g2.fill(demo.shape);
			}
			else if(DrawHandler.currentSelectedShapePro.fillType==FillType.GRADIENT){
				g2.setPaint(new GradientPaint(DrawHandler.currentSelectedShapePro.gradientFillColor.point1,DrawHandler.currentSelectedShapePro.gradientFillColor.color1,DrawHandler.currentSelectedShapePro.gradientFillColor.point2,DrawHandler.currentSelectedShapePro.gradientFillColor.color2,true));
				g2.fill(demo.shape);
			}
		}
		else{
			//下面根据当前绘图属性设置生成预览图像
			switch(frame.currentFunction){
			case Function.RECTANGLE:
				demo=new ShapePro(new Rectangle2D.Double(75, 30, 185, 63),currentPaintProperty.currentStroke,currentPaintProperty.currentLined,currentPaintProperty.currentLineColor,currentPaintProperty.currentFillType,currentPaintProperty.currentFillColor,currentPaintProperty.currentGradientFillColor,ShapeType.ELLIPSE,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
				break;
			case Function.ROUNDRECTANGLE:
				demo=new ShapePro(new RoundRectangle2D.Double(75, 30, 185, 63,currentPaintProperty.currentArcWidth,currentPaintProperty.currentArcHeight),currentPaintProperty.currentStroke,currentPaintProperty.currentLined,currentPaintProperty.currentLineColor,currentPaintProperty.currentFillType,currentPaintProperty.currentFillColor,currentPaintProperty.currentGradientFillColor,ShapeType.ELLIPSE,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
				break;
			case Function.ELLIPSE:
				demo=new ShapePro(new Ellipse2D.Double(75, 30, 185, 63),currentPaintProperty.currentStroke,currentPaintProperty.currentLined,currentPaintProperty.currentLineColor,currentPaintProperty.currentFillType,currentPaintProperty.currentFillColor,currentPaintProperty.currentGradientFillColor,ShapeType.ELLIPSE,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
				break;
			case Function.LINE:
				demo=new ShapePro(new Line2D.Double(75, 30, 260, 93),currentPaintProperty.currentStroke,currentPaintProperty.currentLined,currentPaintProperty.currentLineColor,currentPaintProperty.currentFillType,currentPaintProperty.currentFillColor,currentPaintProperty.currentGradientFillColor,ShapeType.ELLIPSE,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
				break;
			case Function.TEXT:
			    demo=new TextShapePro("Text",new Font("Arail",Font.PLAIN,90),new Point2D.Double(75,90),currentPaintProperty.currentStroke,currentPaintProperty.currentLined,currentPaintProperty.currentLineColor,currentPaintProperty.currentFillType,currentPaintProperty.currentFillColor,currentPaintProperty.currentGradientFillColor,ShapeType.TEXT,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
			    break;
			default:
				demo=new ShapePro(new RoundRectangle2D.Double(75, 30, 185, 63,currentPaintProperty.currentArcWidth,currentPaintProperty.currentArcHeight),currentPaintProperty.currentStroke,currentPaintProperty.currentLined,currentPaintProperty.currentLineColor,currentPaintProperty.currentFillType,currentPaintProperty.currentFillColor,currentPaintProperty.currentGradientFillColor,ShapeType.ELLIPSE,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
				break;
			}
			//如果是文字对象，先要生成shape
			if(demo.shapeType==ShapeType.TEXT){
				    FontRenderContext context=g2.getFontRenderContext();
					TextShapePro ts=(TextShapePro)(demo);//ts和demo指向同一对象，只不过这两个引用的类型不同
					TextLayout layout=new TextLayout(ts.text,ts.font,context);
					AffineTransform transform=new AffineTransform();
					transform.setToTranslation(ts.position.x, ts.position.y);
					Shape outline=layout.getOutline(transform);
					ts.shape=outline;
					Rectangle2D boundsRect=ts.shape.getBounds2D();
					ts.textHeight=boundsRect.getHeight();
					ts.textWidth=boundsRect.getWidth();
			}
			//设置基本绘图属性
			g2.setStroke(new BasicStroke(currentPaintProperty.currentStroke.width,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,currentPaintProperty.currentStroke.pattern,0));
			g2.setColor(currentPaintProperty.currentLineColor);
			//若有边框则开始绘边框
			if(currentPaintProperty.currentLined==true)
				g2.draw(demo.shape);
			//开始填充
			if(currentPaintProperty.currentFillType==FillType.NONE){
				//Don't fill
			}
			if(currentPaintProperty.currentFillType==FillType.SOLID){
			    g2.setColor(currentPaintProperty.currentFillColor);
			    g2.fill(demo.shape);
			}
			else if(currentPaintProperty.currentFillType==FillType.GRADIENT){
				g2.setPaint(new GradientPaint(currentPaintProperty.currentGradientFillColor.point1,currentPaintProperty.currentGradientFillColor.color1,currentPaintProperty.currentGradientFillColor.point2,currentPaintProperty.currentGradientFillColor.color2,true));
				g2.fill(demo.shape);
			}
		}
		
	}
}
