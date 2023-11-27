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

		//Quebra a string em linhas de 21 caracteres
		String[] linhas = new String[objetivo.length()/21 + 1];
		int i = 0;
		int j = 0;
		while (i < objetivo.length()) {
			if (i+21 < objetivo.length()) {
				linhas[j] = objetivo.substring(i, i+21);
			}
			else {
				linhas[j] = objetivo.substring(i);
			}
			i += 21;
			j++;
		}
		//Escreve as linhas
		for (int k = 0; k < linhas.length; k++) {
			g.drawString(linhas[k], 422, 545 + 15*k);
		}
	}
			
}
