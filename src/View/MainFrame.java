package View;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class MainFrame extends JFrame {
		
		//Tamanho da janela grafica
		public final int LARGURA = 1500;
		public final int ALTURA = 840;
		
		//Frames do jogo
		private static MainFrame mf = null;
		public CardLayout mLayout = null;
		private StartingPanel startingPanel =  StartingPanel.getStartingPanel();
		private GamePanel gamePanel = GamePanel.getGamePanel();
		private CharacterSelectionPanel csPanel = CharacterSelectionPanel.getCharacterSelectionPanel();
		private APIView apiView = APIView.getAPIView();
		
		/* TODO
		Metodo em jogador de pegar exercitos
		private int qtdJogadores = csPanel.getJogadores();*/
		
		//Construtor
		private MainFrame() {
			setSize(LARGURA, ALTURA);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setTitle("War");
			setVisible(true);
			getContentPane().add(startingPanel);
			
			// Associa observador ao observado
			apiView.add(gamePanel);

			//getContentPane().add(csPanel);
		
			//getContentPane().add(gamePanel);
			
		}
		
		//Singleton
		public static MainFrame getMainFrame() {
			if (mf == null) {
				mf = new MainFrame();
			}
			return mf;	
		}
		
		//Direcionamento entre os frames
		public void goToCsPanel() {
			startingPanel.setVisible(false);
			getContentPane().add(csPanel);
		}
		
		public void goToGamePanel() {
			csPanel.setVisible(false);
			getContentPane().add(gamePanel);
		}
}
