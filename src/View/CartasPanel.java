package View;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CartasPanel extends Panel {
	
	Image cartaObj,cartaObjGrande;
	
	public CartasPanel() {
		setBackground(Color.darkGray);
		try {
			cartaObj = ImageIO.read(new File("images/war_carta_objetivo.png"));
			cartaObjGrande = ImageIO.read(new File("images/war_carta_objetivo_grande.png"));
		}
		catch (IOException e) {
			System.out.println("Nao foi possivel carregar a imagem da carta objetivo");
			
		}
		
		System.out.println("Cartas desenhadas");

	}
			
}
