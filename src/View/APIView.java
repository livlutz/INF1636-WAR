package View;


import java.awt.Color;
import java.awt.Image;

import javax.swing.JOptionPane;

import Controller.Gerente;

public class APIView{

	// Singleton
	private static APIView APIView = null;

	// Instancia das telas
	CharacterSelectionPanel characterSelectionPanel = CharacterSelectionPanel.getCharacterSelectionPanel();
	StartingPanel startingPanel = StartingPanel.getStartingPanel();

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

	//Retorna o observador
	public ObservadorIF getObs(){
		return (ObservadorIF) GamePanel.getGamePanel();
	}

	//Mostra uma janela com uma mensagem de aviso
	public void mostraAviso(String aviso){
		JOptionPane.showMessageDialog(null, aviso);
	}

	//Atalixa os territorios atancantes no painel de ataque
	public void atualizaAtacantes(String[] atacantes){
		GamePanel.getGamePanel().mudaParaAtaque();
		GamePanel.getGamePanel().atualizaAtacantes(atacantes);
	}

	//Atualiza os territorios defensores no painel de ataque
	public void atualizaDefensores(String[] defensores){
		GamePanel.getGamePanel().atualizaDefensores(defensores);
	}
	
	//Atualiza a quantidade de exércitos que o jogador pode posicionar
	public void atualizaPosicionamento(String[] territorios){
		GamePanel.getGamePanel().mudaParaPosicionamento();
		GamePanel.getGamePanel().atualizaPosicionamento(territorios);
	}

	//Atualiza a quantidade de exércitos que o jogador pode reposicionar
	public void atualizaReposicionamento(String[] territorios){
		GamePanel.getGamePanel().mudaParaReposicionamento();
		GamePanel.getGamePanel().atualizaReposicionamento(territorios);
	}

	//Atualiza a quantidade para posicionar
	public void atualizaQtdPosic(Integer qtd){
		GamePanel.getGamePanel().atualizaQtdPosic(qtd);
	}

	//Atualiza a quantidade para reposicionar
	public void atualizaQtdRepos(Integer qtd){
		GamePanel.getGamePanel().atualizaQtdRepos(qtd);
	}

	//Atualiza os territorios de destino
	public void atualizaDestinos(String[] destinos){
		GamePanel.getGamePanel().atualizaDestinos(destinos);
	}

	//Muda o jogador atual
	public void mudaJogador(String jogador, Color cor, String descricaoObj, String[] cartas){
		GamePanel.getGamePanel().mudaJogador(jogador, cor, descricaoObj, cartas);
	}

	//Determina o primeiro jogador
	public void determinaPrimeiroJogador(String nome, Color cor, String descricaoObj, String[] territorios, Integer qtd){
		GamePanel.getGamePanel().determinaPrimeiroJogador(nome, cor, descricaoObj, territorios, qtd);
	}

	//Atualiza as cartas no painel de cartas
	public void atualizaCartas(String[] cartas){
		GamePanel.getGamePanel().atualizaCartas(cartas);
	}

	//Verifica se o jogador ganhou a partida
	public void jogadorGanhou(String nome, Color cor){
		String nomeCor;

		//Verifica a cor do jogador
		switch (cor.getRGB()) {
			case -16776961:
				nomeCor = "Azul";
				break;
			case -16777216:
				nomeCor = "Preto";
				break;
			case -65536:
				nomeCor = "Vermelho";
				break;
			case -16711936:
				nomeCor = "Verde";
				break;
			case -256:
				nomeCor = "Amarelo";
				break;
			default:
				nomeCor = "Branco";
				break;
		}
		
		//Mostra uma janela com a mensagem de fim de jogo
		JOptionPane.showMessageDialog(null, nome + " - " + nomeCor + " ganhou o jogo!", "Fim de jogo", JOptionPane.INFORMATION_MESSAGE);
		
		//Pergunta se o jogador deseja continuar jogando

		//Se sim, reinicia o jogo
		if (JOptionPane.showConfirmDialog(null, "Deseja continuar jogando?", "Fim de jogo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			Gerente.getGerente().reiniciarJogo();
		} 
		
		//Se não, fecha o jogo
		else {
			System.exit(0);
		}
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
