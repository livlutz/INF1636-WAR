package View;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Controller.Gerente;

class CartasPanel extends Panel implements ObservadoIF {
	
	Image cartaObj,cartaObjGrande;
	
	// Construtor
	public CartasPanel() {
		setBackground(Color.LIGHT_GRAY);
		try {
			cartaObj = ImageIO.read(new File("images/war_carta_objetivo.png"));
			cartaObjGrande = ImageIO.read(new File("images/war_carta_objetivo_grande.png"));
		}
		catch (IOException e) {
			System.out.println("Nao foi possivel carregar a imagem da carta objetivo");
			
		}

	}

	// Adiciona um observador
	public void add(ObservadorIF o) {
		// TODO Auto-generated method stub
		
	}

	// Desenha as cartas de objetivo
	public void desenhaCartas(Graphics g) {
		super.paint(g);
		g.drawImage(cartaObj, 1255, 350, 100, 150, null);
		g.drawImage(cartaObjGrande, 1275, 350, 100, 150, null);
		//Escreve o objetivo do jogador na carta
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 15));
		//Pegar o objetivo do jogador da vez pelo 
	}
			
}
