package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel{
	public static GamePanel gamePanel = null;
	Image tabuleiro = null;
	Image background = null;
	Graphics2D g2d;

	private GamePanel() {
		String filePath = new File("").getAbsolutePath();
		System.out.println(filePath);
	}
	public static GamePanel getGamePanel() {
		if (gamePanel == null) {
			gamePanel = new GamePanel();
		}
		return gamePanel;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		
		try {
			background = ImageIO.read(new File("images/war_tabuleiro_fundo.png"));
			
		}
		catch (IOException e) {
			System.out.println("Erro na leitura do plano de fundo");
		}
		
		try {
			tabuleiro = ImageIO.read(new File("images/war_tabuleiro_mapa.png"));
		}
		catch (IOException e) {
			System.out.println("Nao foi possivel carregar a imagem do tabuleiro");
			
		}
		g2d.drawImage(background, 0,0,1200,800,null);
		g2d.drawImage(tabuleiro, 0,0,1200,800,null);
	}
		
}
