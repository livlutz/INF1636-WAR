package View;

import java.awt.Color;
import java.awt.Dimension;
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

	//Cor do jogador correspondente a cor do exercito

	//Construtor
	public Exercitos(int x, int y,Color color) {
		this.posX = x;
		this.posY = y;
		this.cor = color;
		this.qntExercitos = "1";
		setBounds(0,0, 660, 660);
	}
	
	void drawPlayer(Graphics g2d) {
		Ellipse2D player;
		super.paintComponent(g2d);
		this.g2d = (Graphics2D) g2d;
		this.g2d.setColor(cor);
		player = new Ellipse2D.Double(posX, posY, size, size);
		this.g2d.fill(player);
		this.g2d.drawString(qntExercitos, posX, posY);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public Graphics2D getG2d() {
		return g2d;
	}

	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

	public String getQntExercitos() {
		return qntExercitos;
	}

	public void setQntExercitos(String qntExercitos) {
		this.qntExercitos = qntExercitos;
	}	
}
