package View;

import java.awt.*;

import javax.swing.*;

public class CharacterSelectionPanel extends JPanel {

	int numJogadores = 4;
	SelectionComponent sc = new SelectionComponent();
	SelectionComponent sc1 = new SelectionComponent();
	SelectionComponent sc2 = new SelectionComponent();
	SelectionComponent sc3 = new SelectionComponent();
	JButton btn = new JButton("Iniciar Jogo");
	public CharacterSelectionPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(sc);
		add(sc1);
		add(sc2);
		add(sc3);
		add(btn);
			
	}
		
}