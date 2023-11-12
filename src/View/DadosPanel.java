package View;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DadosPanel extends Panel {

		Image dado1Atacante;
		Image dado2Atacante;
		Image dado3Atacante;
		Image dado4Atacante;
		Image dado5Atacante;
		Image dado6Atacante;
		Image dado1Defensor;
		Image dado2Defensor;
		Image dado3Defensor;
		Image dado4Defensor;
		Image dado5Defensor;
		Image dado6Defensor;

		public DadosPanel(){
			setBackground(Color.darkGray);
			try {
				dado1Atacante = ImageIO.read(new File("images/dado_ataque_1.png"));
				dado2Atacante = ImageIO.read(new File("images/dado_ataque_2.png"));
				dado3Atacante = ImageIO.read(new File("images/dado_ataque_3.png"));
				dado4Atacante = ImageIO.read(new File("images/dado_ataque_4.png"));
				dado5Atacante = ImageIO.read(new File("images/dado_ataque_5.png"));
				dado6Atacante = ImageIO.read(new File("images/dado_ataque_6.png"));
			
				dado1Defensor = ImageIO.read(new File("images/dado_defesa_1.png"));
				dado2Defensor = ImageIO.read(new File("images/dado_defesa_2.png"));
				dado3Defensor = ImageIO.read(new File("images/dado_defesa_3.png"));
				dado4Defensor = ImageIO.read(new File("images/dado_defesa_4.png"));
				dado5Defensor = ImageIO.read(new File("images/dado_defesa_5.png"));
				dado6Defensor = ImageIO.read(new File("images/dado_defesa_6.png"));
				
			}
			catch (IOException e) {
				System.out.println("Nao foi possivel carregar a imagem dos dados");
				
			}
		}
}
