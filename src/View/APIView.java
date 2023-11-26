package View;


import java.awt.Color;
import java.awt.Image;

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
		GamePanel.getGamePanel().mudaParaAtaque();
		GamePanel.getGamePanel().atualizaAtacantes(atacantes);
	}

	public void atualizaDefensores(String[] defensores){
		GamePanel.getGamePanel().atualizaDefensores(defensores);
	}

	/*public void mudaJogador(String jogador, Color cor, String descricaoObj, Cartas[] cartas){
		GamePanel.getGamePanel().mudaJogador(jogador, cor.toString(), descricaoObj, cartas);
	}*/

	public void determinaPrimeiroJogador(String nome, Color cor, String descricaoObj, String[] territorios, Integer qtd){
		GamePanel.getGamePanel().determinaPrimeiroJogador(nome, cor, descricaoObj, territorios, qtd);
	}
	
	public void atualizaPosicionamento(String[] territorios){
		GamePanel.getGamePanel().mudaParaPosicionamento();
		GamePanel.getGamePanel().atualizaPosicionamento(territorios);
	}

	public void atualizaReposicionamento(String[] territorios){
		GamePanel.getGamePanel().mudaParaReposicionamento();
		GamePanel.getGamePanel().atualizaReposicionamento(territorios);
	}

	public void atualizaQtdPosic(Integer qtd){
		GamePanel.getGamePanel().atualizaQtdPosic(qtd);
	}

	public void atualizaQtdRepos(Integer qtd){
		GamePanel.getGamePanel().atualizaQtdRepos(qtd);
	}

	public void atualizaDestinos(String[] destinos){
		GamePanel.getGamePanel().atualizaDestinos(destinos);
	}

	public void mudaJogador(String jogador, Color cor, String descricaoObj, Image[] cartas){
		GamePanel.getGamePanel().mudaJogador(jogador, cor, descricaoObj, cartas);
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
