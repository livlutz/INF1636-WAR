package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingPanel extends JPanel{
	
	JButton iniciar = new JButton("Iniciar novo jogo");
	JButton continuar = new JButton("Continuar jogo");
	public static StartingPanel startingPanel = null;
	private StartingPanel() {
	
		iniciar.addActionListener(new ActionListener(){
			@Override
	        public void actionPerformed(ActionEvent e) {
	            MainFrame.getMainFrame().goToCsPanel();
	        }
		});
		
		add(iniciar);
		add(continuar);
	}
	
	public static StartingPanel getStartingPanel() {
		if (startingPanel == null) {
			startingPanel = new StartingPanel();	
		}
		return startingPanel;
	}
}
