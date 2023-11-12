package View;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class CartasPanel extends Panel {
	
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

	// Desenha as cartas de objetivo
	public void desenhaCartas(Graphics g) {
		super.paint(g);
		g.drawImage(cartaObj, 1255, 350, 100, 150, null);
		g.drawImage(cartaObjGrande, 1275, 350, 100, 150, null);
	}
			
}
