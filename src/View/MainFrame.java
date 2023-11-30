package View;


import java.awt.CardLayout;

import javax.swing.*;

public class MainFrame extends JFrame {
		
		//Tamanho da janela gráfica
		public final int LARGURA = 1500;
		public final int ALTURA = 840;
		
		//Frames do jogo
		private static MainFrame mf = null;
		public CardLayout mLayout = null;
		private StartingPanel startingPanel =  StartingPanel.getStartingPanel();
		private GamePanel gamePanel = GamePanel.getGamePanel();
		private CharacterSelectionPanel csPanel = CharacterSelectionPanel.getCharacterSelectionPanel();
		
		//Construtor
		private MainFrame() {
			setSize(LARGURA, ALTURA);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle("War");
			setVisible(true);
			getContentPane().add(startingPanel);
		}
		
		//Singleton
		public static MainFrame getMainFrame() {
			if (mf == null) {
				mf = new MainFrame();
			}
			return mf;	
		}
		
		//Direcionamento entre os frames (tela inicial)
		public void goToCsPanel() {
			startingPanel.setVisible(false);
			getContentPane().add(csPanel);
		}
		
		//Direcionamento entre os frames (início do jogo)
		public void goToGamePanel() {
			startingPanel.setVisible(false);
			csPanel.setVisible(false);
			getContentPane().add(gamePanel);
			gamePanel.mudaParaPosicionamento();
		}
}
