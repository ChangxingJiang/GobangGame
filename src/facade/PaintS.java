package facade;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PaintS extends JPanel{
	private static final long serialVersionUID=1L;
	
	public void paintComponent(Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(510,10,510,350);
		g.drawLine(780,10,780,350);
	}
}
