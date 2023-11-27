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
		String linha[]; //vetor de strings para armazenar as linhas do objetivo

		//se o objetivo for de destruir, diminui o tamanho da fonte
		if(objetivo.contains("Destruir")) {
			g.setFont(new Font("Arial", Font.BOLD, 10));
			//cabe 20 caracteres por linha
			linha = new String[objetivo.length()/20 + 1];
			int i = 0;
			int j = 0;
			while (i < objetivo.length()) {
				if (i+20 < objetivo.length()) {
					linha[j] = objetivo.substring(i, i+20);
				}
				else {
					linha[j] = objetivo.substring(i);
				}
				i += 30;
				j++;
			}
			//Escreve as linhas
			for (int k = 0; k < linha.length; k++) {
				g.drawString(linha[k], 422, 545 + 10*k);
			}

		}

		else{
			g.setFont(new Font("Arial", Font.BOLD, 12));

			//Quebra a string em linhas de 21 caracteres
			linha = new String[objetivo.length()/21 + 1];
			int i = 0;
			int j = 0;
			while (i < objetivo.length()) {
				if (i+21 < objetivo.length()) {
					linha[j] = objetivo.substring(i, i+21);
				}
				else {
					linha[j] = objetivo.substring(i);
				}
				i += 21;
				j++;
			}
			//Escreve as linhas
			for (int k = 0; k < linha.length; k++) {
				g.drawString(linha[k], 422, 545 + 15*k);
			}
		}
		
	}
			
}
