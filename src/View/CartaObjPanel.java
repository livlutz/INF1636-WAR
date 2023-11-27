package View;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class CartaObjPanel extends Panel {
	
	Image cartaObj;
	
	// Construtor
	public CartaObjPanel() {
		setBackground(Color.LIGHT_GRAY);
		try {
			cartaObj = ImageIO.read(new File("imagens/war_carta_objetivo.png"));
		}
		catch (IOException e) {
			System.out.println("Nao foi possivel carregar a imagem da carta objetivo");
			
		}

	}

	// Desenha as cartas de objetivo
	public void desenhaCartas(Graphics g) {
		super.paint(g);
		g.drawImage(cartaObj, 410, 490, 160, 220, null);
	}

	//Escreve o objetivo da carta
	public void escreveObjetivo(Graphics g, String objetivo) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.drawString(objetivo, 422, 550);
	}
			
}
