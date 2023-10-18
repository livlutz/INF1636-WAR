package View;

import javax.swing.*;

public class MainFrame extends JFrame {
	
		private final int LARGURA = 1200;
		private final int ALTURA = 800;
		private static MainFrame mf = null;
		private StartingPanel startingPanel =  new StartingPanel();
		private GamePanel gamePanel = new GamePanel();
		private CharacterSelectionPanel csPanel = new CharacterSelectionPanel();
		private MainFrame() {
			
			setSize(LARGURA, ALTURA);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("War");
			setVisible(true);
			getContentPane().add(startingPanel);
			//getContentPane().add(gamePanel);
			//getContentPane().add(csPanel);
			
		}
		public static MainFrame getInstance() {
			if (mf == null) {
				return new MainFrame();
			}
			else {
				return mf;
			}
				
		}

}
