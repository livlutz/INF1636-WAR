package View;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class APIView{
	private static APIView APIView = null;
	CharacterSelectionPanel characterSelectionPanel = CharacterSelectionPanel.getCharacterSelectionPanel();
	StartingPanel startingPanel = StartingPanel.getStartingPanel();
	//private Gerente gerente = Gerente.getGerente();

	// Array de nomes dos jogadores
	String[] nomesJogadores = new String[6];

	// Array de nomes de territorios 
	String[] listaTerritorios;


	// Construtor privado para o singleton
	private APIView(){
	}

	// Singleton
	public static APIView getAPIView(){
		if(APIView == null){
			APIView = new APIView();
		}
		return APIView;
	}

	//Desenha a tela de seleção de personagens
	public void selecionarJogadores(int numJogadores){
		characterSelectionPanel.setNumJogadores(numJogadores);
		characterSelectionPanel.drawJogadores();
	}

	//Inicia o jogo desenhando a tela de seleção de personagens e escondendo a tela de início
	public void iniciarJogo(){
		characterSelectionPanel.setVisible(false);
		startingPanel.setVisible(true);
	}

	public ObservadorIF getObs(){
		return (ObservadorIF) GamePanel.getGamePanel();
	}

	public void mostraAviso(String aviso){
		JOptionPane.showMessageDialog(null, aviso);
	}

	public void atualizaAtacantes(String[] atacantes){
		GamePanel.getGamePanel().atualizaAtacantes(atacantes);
	}

	public void atualizaDefensores(String[] defensores){
		GamePanel.getGamePanel().atualizaDefensores(defensores);
	}

	

	// ---------------------- getters & setters ----------------------
	
	//Altera os nomes dos jogadores
	public void setNomesJogadores(String[] nomes){
		nomesJogadores = nomes;
	}

	//Retorna os nomes dos jogadores
	public String[] getNomesJogadores(){
		return nomesJogadores;
	}

	//Altera a lista de territórios
	public void setListaTerritorios(String[] l){
		listaTerritorios = l;
	}

	//Retorna a lista de territórios
	public String[] getListaTerritorios(){
		return listaTerritorios;
	}
}
