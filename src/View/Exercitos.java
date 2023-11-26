package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

class Exercitos extends JComponent {
	
	//Tamanho da bolinha
	private final int size = 20; 

	//Coordenada x da bolinha
	private int posX; 

	//Coordenada y da bolinha
	private int posY; 

	// Cor da bolinha
	private Color cor; 

	//Componente gráfica para o desenho
	Graphics2D g2d;   

	// Quantidade de exércitos "Default" das bolinhas
	private String qntExercitos = "0"; 

	//OBS : Cor do jogador correspondente à cor do exército

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

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawPlayer(g);
	}
	
	//----------------------------- getters & setters -----------------//
	
	//Retorna a coordenada x da bolinha
	public int getPosX() {
		return posX;
	}

	//Altera a coordenada x da bolinha
	public void setPosX(int posX) {
		this.posX = posX;
	}

	//Retorna a coordenada y da bolinha
	public int getPosY() {
		return posY;
	}

	//Altera a coordenada y da bolinha
	public void setPosY(int posY) {
		this.posY = posY;
	}

	//Retorna a cor da bolinha
	public Color getCor() {
		return cor;
	}

	//Altera a cor da bolinha
	public void setCor(Color cor) {
		this.cor = cor;
	}

	//Retorna a componente gráfica
	public Graphics2D getG2d() {
		return g2d;
	}

	//Altera a componente gráfica
	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

	//Retorna a quantidade de exércitos
	public String getQntExercitos() {
		return qntExercitos;
	}

	//Altera a quantidade de exércitos
	public void setQntExercitos(String qntExercitos) {
		this.qntExercitos = qntExercitos;
	}	
}
