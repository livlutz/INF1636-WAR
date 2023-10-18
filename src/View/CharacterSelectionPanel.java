package View;

import java.awt.*;

import javax.swing.*;

public class CharacterSelectionPanel extends JPanel {

		JTextField textFields = new JTextField();
		JComboBox comboBox = new JComboBox(new String[] { "Azul","Preto","Vermelho","Verde","Amarelo"});
		
		public CharacterSelectionPanel() {
			add(textFields);
			add(comboBox);
		}
		
}