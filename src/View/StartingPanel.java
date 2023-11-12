package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class StartingPanel extends JPanel{
	
	//Botoes
	JButton iniciar = new JButton("Iniciar novo jogo");
	JButton continuar = new JButton("Continuar jogo");
	
	//Imagem do frame de inicio do jogo
	Image start = null;
	public static StartingPanel startingPanel = null;
	
	//Criando e agrupando botoes
	JRadioButton radioButton3 = new JRadioButton();
	JRadioButton radioButton4 = new JRadioButton();
	JRadioButton radioButton5 = new JRadioButton();
	JRadioButton radioButton6 = new JRadioButton();
	ButtonGroup buttonGroup = new ButtonGroup();
	
	//Construtor
	private StartingPanel() {
	
		iniciar.addActionListener(new ActionListener(){
			@Override
	        public void actionPerformed(ActionEvent e) {
				int selected = 0;
				if (radioButton3.isSelected()) {
					CharacterSelectionPanel.getCharacterSelectionPanel().setNumJogadores(3);
					selected++;
				}
				if (radioButton4.isSelected()) {
					CharacterSelectionPanel.getCharacterSelectionPanel().setNumJogadores(4);
					selected++;
				}
				if (radioButton5.isSelected()) {
					CharacterSelectionPanel.getCharacterSelectionPanel().setNumJogadores(5);
					selected++;
				}
				if (radioButton6.isSelected()) {
					CharacterSelectionPanel.getCharacterSelectionPanel().setNumJogadores(6);
					selected++;
				}
				if(selected == 0) {
					JOptionPane.showMessageDialog(null, "Selecione o número de jogadores");
					return;
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
		
		try {
			start = ImageIO.read(new File ("images/bgconfiguracao.png"));
		}
		
		catch (IOException e) {
			System.out.println("Erro na leitura do plano de fundo\n");
		}
	}
	
	//Singleton
	public static StartingPanel getStartingPanel() {
		if (startingPanel == null) {
			startingPanel = new StartingPanel();	
		}
		return startingPanel;
	}
}
