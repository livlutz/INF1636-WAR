package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CharacterSelectionPanel extends JPanel {

	public static CharacterSelectionPanel csPanel = null;
	private int numJogadores;
	JButton btn = new JButton("Iniciar Jogo");
	
	//Constroi o painel
	private CharacterSelectionPanel() {	
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
	            MainFrame.getMainFrame().goToGamePanel();
	        }
		}
		);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(btn);
	}
	
	public static CharacterSelectionPanel getCharacterSelectionPanel() {
		if (csPanel==null) {
			csPanel = new CharacterSelectionPanel();
		}
		return csPanel;
	}
	
	//Desenha a selecao de jogadores de acordo com o numero selecionado
	public void drawJogadores() {
		for(int i = 0;i < numJogadores;i++) {
			add(new SelectionComponent());
		}
	}
	
	//---------------------- getters & setters -------------------//
	
	public int getJogadores() {
		return numJogadores;
	}
	
	public void setNumJogadores(int numJogadores) {
		this.numJogadores = numJogadores;
	}
}
