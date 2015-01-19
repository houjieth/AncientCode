package handler;
import frame.*;
import handler.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
public class ColorChooserHandler {
	public static class LineColorChooser implements ActionListener{
		MainFrame frame;
		public LineColorChooser(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			Color selected=JColorChooser.showDialog(frame,"Set Line Color",frame.currentPaintProperty.currentLineColor);
			if(selected!=null)
				frame.currentPaintProperty.currentLineColor=selected;
			    frame.lineColorIndicator.setBackground(selected);
		}
	}
	public static class FillColorChooser implements ActionListener{
		MainFrame frame;
		public FillColorChooser(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			Color selected=JColorChooser.showDialog(frame,"Set Line Color",frame.currentPaintProperty.currentLineColor);
			if(selected!=null)
				frame.currentPaintProperty.currentFillColor=selected;
			    frame.fillColorIndicator.setBackground(selected);
		}
	}
}
