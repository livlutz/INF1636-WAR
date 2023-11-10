package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.ArrayList;

public class CharacterSelectionPanel extends JPanel {

	public static CharacterSelectionPanel csPanel = null;
	private int numJogadores;
	private ArrayList <String> nomesJogadores = new ArrayList<String>();
    private ArrayList <Color> coresJogadores = new ArrayList<Color>();
	private ArrayList <SelectionComponent> selectionComponents = new ArrayList<SelectionComponent>();
	JButton btn = new JButton("Iniciar Jogo");
	
	//Constroi o painel
	private CharacterSelectionPanel() {	
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				for (SelectionComponent s : selectionComponents) {
					nomesJogadores.add(s.getNome());
					coresJogadores.add(s.getCor());
				}
				if (APIView.getAPIView().podeComecarJogo()) {
					MainFrame.getMainFrame().goToGamePanel();
				}
				else {
					JOptionPane.showMessageDialog(null, "Nomes ou cores inválidos ou repetidos");
				}
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
	
	//Desenha os jogadores de acordo com o numero selecionado
	public void drawJogadores() {
		for(int i = 0;i < numJogadores;i++) {
			SelectionComponent selection = new SelectionComponent();
			add(selection);
			selectionComponents.add(selection);
		}
	}
	
	//---------------------- getters & setters -------------------//
	
	public int getJogadores() {
		return numJogadores;
	}
	
	public void setNumJogadores(int numJogadores) {
		this.numJogadores = numJogadores;
	}

	public ArrayList<String> getNomesJogadores() {
		return nomesJogadores;
	}

	public ArrayList<Color> getCoresJogadores() {
		return coresJogadores;
	}

}
