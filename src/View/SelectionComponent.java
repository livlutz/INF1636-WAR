package View;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;

class SelectionComponent extends JPanel {
	
	public static SelectionComponent selectionComponent = null;
	//Campos de texto e escolha de cor do jogador
	JTextField textField = new JTextField("Nome do jogador",20);
	JComboBox comboBox = new JComboBox(new String[] { "Azul","Preto","Vermelho","Verde","Amarelo","Branco"});
	
	// Guarda a cor escolhida
	private Color cor; 

	// Guarda o nome escolhido
	private String nome; 

	//Construtor
	public SelectionComponent() {
		add(textField);
		add(comboBox);
	}
	
	//----------------- getters & setters --------------//
	
	//Pega o nome do jogador
	private void setNome() {
		if (!textField.getText().equals("Nome do jogador")) {
			this.nome = textField.getText();
		}
	}

	//Seta a cor de acordo com a cor que o jogador escolher
	private void setCor() {
			
		switch(comboBox.getSelectedItem().toString()) {
			case "Azul":
				this.cor = Color.BLUE;
				break;
			case "Preto":
				this.cor = Color.BLACK;
				break;
			case "Vermelho":
				this.cor = Color.RED;
				break;
			case "Verde":
				this.cor = Color.GREEN;
				break;
			case "Amarelo":
				this.cor = Color.YELLOW;
				break;
			default:
				this.cor= Color.WHITE;
				break;
		}
			
	}
	
	// Devolve o nome que está na textField
	public String getNome() {
		setNome();
		return nome;
	}

	// Devolve a cor que está no comboBox
	public Color getCor() {
		setCor();
		return cor;
	}
}
