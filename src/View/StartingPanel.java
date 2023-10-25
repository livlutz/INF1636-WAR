package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingPanel extends JPanel{
	
	JButton iniciar = new JButton("Iniciar novo jogo");
	JButton continuar = new JButton("Continuar jogo");
	public static StartingPanel startingPanel = null;
	JRadioButton radioButton3 = new JRadioButton();
	JRadioButton radioButton4 = new JRadioButton();
	JRadioButton radioButton5 = new JRadioButton();
	JRadioButton radioButton6 = new JRadioButton();
	ButtonGroup buttonGroup = new ButtonGroup();
	private StartingPanel() {
	
		iniciar.addActionListener(new ActionListener(){
			@Override
	        public void actionPerformed(ActionEvent e) {
				if (radioButton3.isSelected()) {
					CharacterSelectionPanel.getCharacterSelectionPanel().setNumJogadores(3);
				}
				if (radioButton4.isSelected()) {
					CharacterSelectionPanel.getCharacterSelectionPanel().setNumJogadores(4);
				}
				if (radioButton5.isSelected()) {
					CharacterSelectionPanel.getCharacterSelectionPanel().setNumJogadores(5);
				}
				if (radioButton6.isSelected()) {
					CharacterSelectionPanel.getCharacterSelectionPanel().setNumJogadores(6);
				}
				CharacterSelectionPanel.getCharacterSelectionPanel().drawJogadores();
				
				MainFrame.getMainFrame().goToCsPanel();
	            
			}
		});
		
		add(iniciar);
		add(continuar);
		
		buttonGroup.add(radioButton3);
		buttonGroup.add(radioButton4);
		buttonGroup.add(radioButton5);
		buttonGroup.add(radioButton6);
		
		radioButton3.setText("3 jogadores");
		radioButton4.setText("4 jogadores");
		radioButton5.setText("5 jogadores");
		radioButton6.setText("6 jogadores");
		
		add(radioButton3);
		add(radioButton4);
		add(radioButton5);
		add(radioButton6);
	}
	
	public static StartingPanel getStartingPanel() {
		if (startingPanel == null) {
			startingPanel = new StartingPanel();	
		}
		return startingPanel;
	}
}
