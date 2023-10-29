package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class Exercitos extends JComponent {
	
	private final int size = 20;
	private int posX;
	private int posY;
	private Color cor;
	Graphics2D g2d;
	private String qntExercitos = "0";
	
	public Exercitos(double d, double e,Color color, String qntExercitos) {
		this.posX = (int)d;
		this.posY = (int)e;
		this.cor = color;
		this.qntExercitos = qntExercitos;
		//setBounds(0,0, 660, 660);
	}
	
	void drawPlayer(Graphics g2d) {
		Ellipse2D player;
		player = new Ellipse2D.Float(posX, posY, size, size);
		g2d.setColor(cor);
		((Graphics2D) g2d).fill(player);
		g2d.drawString(qntExercitos, posX, posY);
	}
	

}