package View;

import java.awt.CardLayout;

import javax.swing.*;

public class MainFrame extends JFrame {
	
		private final int LARGURA = 1200;
		private final int ALTURA = 800;
		private static MainFrame mf = null;
		public CardLayout mLayout = null;
		private StartingPanel startingPanel =  StartingPanel.getStartingPanel();
		private GamePanel gamePanel = GamePanel.getGamePanel();
		private CharacterSelectionPanel csPanel = CharacterSelectionPanel.getCharacterSelectionPanel();
		private Exercitos exercitos = Exercitos.getExercitos();
		private MainFrame() {
			
			setSize(LARGURA, ALTURA);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		}
}
