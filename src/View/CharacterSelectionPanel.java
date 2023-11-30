package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controller.Gerente;

import java.util.ArrayList;

class CharacterSelectionPanel extends JPanel {

	public static CharacterSelectionPanel csPanel = null;
	
	//Guarda o número de jogadores
	private int numJogadores;

	//Guarda os nomes e cores dos jogadores em um array
	private ArrayList <String> nomesJogadores = new ArrayList<String>();
    private ArrayList <Color> coresJogadores = new ArrayList<Color>();

	//Guarda os componentes de seleção de personagem
	private ArrayList <SelectionComponent> selectionComponents = new ArrayList<SelectionComponent>();
	
	//Botão de iniciar jogo
	JButton btn = new JButton("Iniciar Jogo");
	
	//Constrói o painel se puder começar o jogo
	private CharacterSelectionPanel() {	
		//Adiciona o botão de iniciar jogo
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Seleciona os nomes e cores dos jogadores
				for (SelectionComponent s : selectionComponents) {
					nomesJogadores.add(s.getNome());
					coresJogadores.add(s.getCor());
				}

				//Se puder começar o jogo, vai para o painel do jogo
				if (Gerente.getGerente().comecaJogo(nomesJogadores, coresJogadores)) {
					MainFrame.getMainFrame().goToGamePanel();
				}

				else {
					// Se não puder começar, limpa os arrays e mostra mensagem
					nomesJogadores.clear();
					coresJogadores.clear();
					JOptionPane.showMessageDialog(null, "Nomes ou cores inválidos ou repetidos", "Erro", JOptionPane.ERROR_MESSAGE);
				}
	        }
		}
		);

		//Adiciona o botão de iniciar jogo
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(btn);
	}
	
	//Singleton
	public static CharacterSelectionPanel getCharacterSelectionPanel() {
		if (csPanel==null) {
			csPanel = new CharacterSelectionPanel();
		}
		return csPanel;
	}
	
	//Desenha os jogadores de acordo com o número selecionado
	public void drawJogadores() {
		for(int i = 0;i < numJogadores;i++) {
			SelectionComponent selection = new SelectionComponent();
			add(selection);
			selectionComponents.add(selection);
		}
	}
	
	//---------------------- getters & setters -------------------//
	
	//Retorna o número de jogadores
	public int getJogadores() {
		return numJogadores;
	}
	
	//Altera o número de jogadores
	public void setNumJogadores(int numJogadores) {
		this.numJogadores = numJogadores;
	}

	//Retorna os nomes dos jogadores
	public ArrayList<String> getNomesJogadores() {
		return nomesJogadores;
	}

	//Retorna as cores dos jogadores
	public ArrayList<Color> getCoresJogadores() {
		return coresJogadores;
	}
}
