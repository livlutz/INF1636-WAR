package View;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectionComponent extends JPanel {
	JTextField textField = new JTextField("Nome do jogador",20);
	JComboBox comboBox = new JComboBox(new String[] { "Azul","Preto","Vermelho","Verde","Amarelo"});
	
	public SelectionComponent() {
		
		add(textField);
		add(comboBox);
	}
}
