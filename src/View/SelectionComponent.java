package View;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;

public class SelectionComponent extends JPanel {
	
	public static SelectionComponent selectionComponent = null;
	//Campos de texto e escolha de cor do jogador
	JTextField textField = new JTextField("Nome do jogador",20);
	JComboBox comboBox = new JComboBox(new String[] { "Azul","Preto","Vermelho","Verde","Amarelo","Branco"});
	
	private Color cor; // Guarda a cor escolhida
	private String nome; // Guarda o nome escolhido

	//Construtor
	public SelectionComponent() {
		add(textField);
		add(comboBox);
	}
	
	//----------------- getters & setters --------------//
	
	//Pega o nome do jogador
	public void setNome() {
		nome = comboBox.getName();
	}
	
	//Seta o nome do jogador
	public String getNome() {
		return nome;
	}

	//Seta a cor de acordo com a cor que o jogador escolher
	public void setCor() {
			
		switch(comboBox.getName()) {
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
	
	public Color getCor() {
		return cor;
	}
}
