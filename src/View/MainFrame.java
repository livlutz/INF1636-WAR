package View;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class MainFrame extends JFrame {
	
		public final int LARGURA = 1200;
		public final int ALTURA = 700;
		private static MainFrame mf = null;
		public CardLayout mLayout = null;
		private StartingPanel startingPanel =  StartingPanel.getStartingPanel();
		private GamePanel gamePanel = GamePanel.getGamePanel();
		private CharacterSelectionPanel csPanel = CharacterSelectionPanel.getCharacterSelectionPanel();
		//Metodo em jogador de pegar exercitos
		//private int qtdJogadores = csPanel.getJogadores();
		
		//Construtor
		private MainFrame() {
			setSize(LARGURA, ALTURA);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle("War");
			setVisible(true);
			getContentPane().add(startingPanel);
		
			//getContentPane().add(csPanel);
		
			//getContentPane().add(gamePanel);
			
		}

		public static MainFrame getMainFrame() {
			if (mf == null) {
				mf = new MainFrame();
			}
			return mf;	
		}
		
		public void goToCsPanel() {
			startingPanel.setVisible(false);
			getContentPane().add(csPanel);
		}
		
		public void goToGamePanel() {
			csPanel.setVisible(false);
			getContentPane().add(gamePanel);
			//gamePanel.desenhaExercitos();
		}
}
