package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CharacterSelectionPanel extends JPanel {

	public static CharacterSelectionPanel csPanel = null;
	private int numJogadores = 4;
	SelectionComponent sc = new SelectionComponent();
	SelectionComponent sc1 = new SelectionComponent();
	SelectionComponent sc2 = new SelectionComponent();
	SelectionComponent sc3 = new SelectionComponent();
	SelectionComponent sc4 = new SelectionComponent();
	SelectionComponent sc5 = new SelectionComponent();
	SelectionComponent sc6 = new SelectionComponent();
	JButton btn = new JButton("Iniciar Jogo");
	
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
	
	public void setNumJogadores(int numJogadores) {
		this.numJogadores = numJogadores;
	}
	public void drawJogadores() {
		for(int i = 0;i < numJogadores;i++) {
			add(new SelectionComponent());
		}
	}
}