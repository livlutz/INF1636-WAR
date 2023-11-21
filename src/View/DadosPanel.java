package View;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class DadosPanel extends JPanel {

	Image [] dadoAtacante = new Image[6];

	//Array de imagens dos dados de defesa
	ArrayList<Image> dadoDefensor = new ArrayList<Image>();
	Image ataque1;
	Image ataque2;
	Image ataque3;
	Image defesa1;
	Image defesa2;
	Image defesa3;

	//Componente gráfico
	Graphics2D g2d;

	//Construtor
	public DadosPanel(){
		//Fundo cinza
		setBackground(Color.darkGray);

		//Carrega as imagens dos dados
		try {
		
			dadoAtacante[1] =(ImageIO.read(new File("images/dado_ataque_2.png")));
			dadoAtacante[2] =(ImageIO.read(new File("images/dado_ataque_3.png")));
			dadoAtacante[3] =(ImageIO.read(new File("images/dado_ataque_4.png")));
			dadoAtacante[4] =(ImageIO.read(new File("images/dado_ataque_5.png")));
			dadoAtacante[5] =(ImageIO.read(new File("images/dado_ataque_6.png")));
			
			dadoDefensor.add(ImageIO.read(new File("images/dado_defesa_1.png")));
			dadoDefensor.add(ImageIO.read(new File("images/dado_defesa_2.png")));
			dadoDefensor.add(ImageIO.read(new File("images/dado_defesa_3.png")));
			dadoDefensor.add(ImageIO.read(new File("images/dado_defesa_4.png")));
			dadoDefensor.add(ImageIO.read(new File("images/dado_defesa_5.png")));
			dadoDefensor.add(ImageIO.read(new File("images/dado_defesa_6.png")));
				
		}

		catch (IOException e) {
			System.out.println("Nao foi possivel carregar a imagem dos dados");
				
		}
	}
	
	//Desenha as imagens dos dados
	public void paintComponent(Graphics g) {
		g2d = (Graphics2D) g;
		g2d.drawImage(ataque1,10,10,50,50,null);
		g2d.drawImage(ataque2,10,60,50,50,null);
		g2d.drawImage(ataque3,10,110,50,50,null);
		g2d.drawImage(defesa1,60,10,50,50,null);
		g2d.drawImage(defesa2,60,60,50,50,null);
		g2d.drawImage(defesa3,60,110,50,50,null);
	}

	//Mostra os dados de ataque e defesa na tela
	public void mostrarDados(int[] dadosAtaque, int[] dadosDefesa) {
		ataque1 = dadoAtacante[dadosAtaque[0]-1];
		ataque2 = dadoAtacante[dadosAtaque[1]-1];
		ataque3 = dadoAtacante[dadosAtaque[2]-1];
		defesa1 = dadoDefensor.get(dadosDefesa[0]-1);
		defesa2 = dadoDefensor.get(dadosDefesa[1]-1);
		defesa3 = dadoDefensor.get(dadosDefesa[2]-1);
		repaint();
		System.out.println("Dados  mostrados");
	}
}
