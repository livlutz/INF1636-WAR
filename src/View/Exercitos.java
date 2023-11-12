package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

class Exercitos extends JComponent {
	
	private final int size = 20; //Tamanho da bolinha
	private int posX; //Coordenada x da bolinha
	private int posY; //Coordenada y da bolinha
	private Color cor; // Cor da bolinha
	Graphics2D g2d;   //Componente grafica para o desenho
	private String qntExercitos = "0"; // Qtd de exercitos "Default" das bolinhas

	//OBS : Cor do jogador correspondente a cor do exercito

	//Construtor
	public Exercitos(int x, int y,Color color) {
		this.posX = x;
		this.posY = y;
		this.cor = color;
		this.qntExercitos = "1";
		setBounds(0,0, 660, 660);
	}
	
	//Desenha a bolinha
	void drawPlayer(Graphics g) {
		this.g2d = (Graphics2D) g;

		// Pega a cor do jogador
		this.g2d.setPaint(cor);

		Ellipse2D player = new Ellipse2D.Float(posX, posY, size, size);
		// Preenche a bolinha com a cor do jogador
		this.g2d.fill(player);

		// Pega nova cor para contraste
		if (cor == Color.BLACK || cor == Color.BLUE){
			this.g2d.setPaint(Color.WHITE);
		}
		else {
			this.g2d.setPaint(Color.BLACK);
		}

		// Desenha a borda 
		this.g2d.draw(player);

		// Desenha a quantidade de exercitos
		this.g2d.drawString(qntExercitos, posX + 7, posY + 14);
	}
	
	//----------------------------- getters & setters -----------------//
	
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
