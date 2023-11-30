package Model;

import java.util.ArrayList;
import java.awt.Color;
class Jogador {

	//Guarda a cor do jogador
	private Color cor; 
	
	//Guarda o nome do jogador
	private String nome; 
	
	//Guarda a quantidade de Exércitos que pode posicionar
	private int qtdExercitoPosic; 

	//Guarda a quantidade de territórios em sua posse
	private int qtdTerritorios = 0;  

	//Guarda as cartas que um jogador possui
	private ArrayList <Carta> cartas = new ArrayList<Carta>();  
	
	//Guarda seu objetivo no jogo
	private Objetivo obj;  

	//Guarda os territórios que possui
	private ArrayList <Territorio> territorios = new ArrayList <Territorio> (); 
	
	//Guarda se o jogador foi eliminado nessa rodada
	private boolean eliminadoNessaRodada = false; 
	private Jogador jMatou;

	//Guarda se o jogador conquistou um território nessa rodada
	private boolean conquistouNessaRodada = false; 
	
	//Construtor da classe
	public Jogador(String nome, Color cor) {
		this.nome = nome;
		this.cor = cor;
	}
	
	// Posicionar exércitos em território do jogador
	// Cabe a função que chamou verificar se pode
	public void posicionarExercitos(Territorio t, int qtdExercitos){
		t.alterarQndExercitos(qtdExercitos);
		this.qtdExercitoPosic -= qtdExercitos;
	}
	
	//Verifica se o jogador pode trocar cartas
	public boolean temTroca(){
		int circulos = 0, quadrados = 0, triangulos = 0;

		// Conta quantas cartas de cada formato o jogador possui
		for (Carta c: cartas){
			if (c.f.equals(Carta.Formato.circulo))
				circulos++;
			else if (c.f.equals(Carta.Formato.quadrado))
				quadrados++;
			else if (c.f.equals(Carta.Formato.triangulo))
				triangulos++;
			else{
				circulos++;
				quadrados++;
				triangulos++;
			}
		}

		// Se o jogador possui 3 cartas de um formato ou 1 de cada formato, pode trocar
		if (circulos >= 3 || quadrados >= 3 || triangulos >= 3 || (circulos >= 1 && quadrados >= 1 && triangulos >= 1))
			return true;
		
			return false;
	}

	//Retorna a quantidade de bonus de exércitos que o jogador recebe de bonus na troca
	// Só é chamado se pode trocar
	public Integer trocarCartas (int numDeTrocas) {
		
		ArrayList<Carta> circulos = new ArrayList<Carta>();
		ArrayList<Carta> quadrados = new ArrayList<Carta>();
		ArrayList<Carta> triangulos = new ArrayList<Carta>();
		ArrayList<Carta> coringas = new ArrayList<Carta>();

		// Separa as cartas por formato
		for (Carta carta: cartas){
			if (carta.f.equals(Carta.Formato.circulo))
				circulos.add(carta);
			else if (carta.f.equals(Carta.Formato.quadrado))
				quadrados.add(carta);
			else if (carta.f.equals(Carta.Formato.triangulo))
				triangulos.add(carta);
			else
				coringas.add(carta);
		}
		

		if (circulos.size() >= 3){
			// Troca três cartas de círculo e devolve elas para o baralho
			for (int i = 0; i < 3; i++){
				usaCarta(circulos);
			}
		}

		else if (quadrados.size() >= 3){
			// Troca três cartas de quadrado e devolve elas para o baralho
			for (int i = 0; i < 3; i++){
				usaCarta(quadrados);
			}
		}

		else if (triangulos.size() >= 3){
			// Troca três cartas de triângulo e devolve elas para o baralho
			for (int i = 0; i < 3; i++){
				usaCarta(triangulos);
			}
		}

		else {
			int cont = coringas.size();
			switch (cont){
				case 0:
					// Troca uma de cada e devolve elas para o baralho
					usaCarta(circulos);
					usaCarta(quadrados);
					usaCarta(triangulos);
					break;
				case 1:
					usaCarta(coringas);
					if (circulos.size() == 0){
						// Remove um coringa, um quadrado e um triângulo
						usaCarta(quadrados);
						usaCarta(triangulos);
					}

					else if (quadrados.size() == 0){
						// Remove um coringa, um círculo e um triângulo
						usaCarta(circulos);
						usaCarta(triangulos);
					}

					else{
						// Remove um coringa, um círculo e um quadrado
						usaCarta(circulos);
						usaCarta(quadrados);
					}

					break;

				case 2:
					// Remove dois coringas e uma carta de qualquer formato
					usaCarta(coringas);
					usaCarta(coringas);
					if (circulos.size() == 0 && quadrados.size() == 0){
						usaCarta(triangulos);
					}

					else if (quadrados.size() == 0 && triangulos.size() == 0){
						usaCarta(circulos);
					}

					else if (circulos.size() == 0 && triangulos.size() == 0){
						usaCarta(quadrados);
					}

					else if (circulos.size() == 1){
						usaCarta(circulos);
					}

					else if (quadrados.size() == 1){
						usaCarta(quadrados);
					}

					else{
						usaCarta(triangulos);
					}
			}
		}

		Integer qtd;
		//Quando temos até 5 trocas já efetuadas
		if (numDeTrocas <= 5) {
			qtd = 4 + (2 * (numDeTrocas));
		}

		else if (numDeTrocas == 6) {
			qtd = 15;
		}

		//Temos mais de 6 trocas já efetuadas
		else {
			int diferenca = numDeTrocas - 6;
			qtd = 15 + (diferenca * 5);
		}

		return qtd;
	}

	// Remove a carta do topo do baralho e adiciona ao jogador
	private void usaCarta(ArrayList<Carta> lista){
		Carta c = lista.get(0);
		cartas.remove(c);
		Jogo.getJogo().addCarta(c);
		// Se o território da carta pertence ao jogador, aumenta em 2 a quantidade de exércitos
		if (c.getTerritorio() != null){
			if (c.getTerritorio().getJogador() == this){
				c.getTerritorio().alterarQndExercitos(2);
			}
		}
	}

	//Adiciona uma carta ao jogador
	public void addCarta(Carta c) {
		cartas.add(c);
	}

	//Adiciona um território ao jogador
	public void addTerritorio(Territorio t) {
		territorios.add(t);
		// Aumenta em 1 a quantidade de territórios
		this.qtdTerritorios++;
	}

	//Remove um território do jogador
	public void removeTerritorio(Territorio t) {
		territorios.remove(t);
		// Diminui em 1 a quantidade de territórios
		this.qtdTerritorios--;
	}

	//Atualiza a quantidade de exércitos que o jogador pode posicionar
	public void atualizaQtdExPosicGeral(Integer bonusTroca){
		int qtd = this.qtdTerritorios/2;
		if (qtd < 3)
			qtd = 3;
		this.qtdExercitoPosic = qtd + bonusTroca;
	}

	// Apaga todos os dados do jogador menos nome e cor
	public void resetJogador(){
		this.qtdExercitoPosic = 0;
		this.qtdTerritorios = 0;
		this.cartas.clear();
		this.territorios.clear();
		this.obj = null;
		this.eliminadoNessaRodada = false;
		this.conquistouNessaRodada = false;
	}

	// --------------------------- getters & setters ---------------------------

	//Retorna a cor do jogador
	public Color getCor() {
		return cor;
	}

	//Altera a cor do jogador
	public void setCor(Color cor) {
		this.cor = cor;
	}
	
	//Retorna o nome do jogador
	public String getNome() {
		return nome;
	}

	//Retorna a quantidade de exércitos que o jogador pode posicionar
	public int getQtdExercitoPosic() {
		return qtdExercitoPosic;
	}

	//Retorna a quantidade de territórios que o jogador possui
	public int getQtdTerritorios(){
		return qtdTerritorios;
	}

	//Retorna o objetivo do jogador
	public Objetivo getObj() {
		return obj;
	}
	
	//Retorna as cartas que o jogador possui (o ArrayList de cartas)
	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	//Retorna os territórios que o jogador possui (o ArrayList de territórios)
	public ArrayList<Territorio> getTerritorios() {
		return territorios;
	}

	//Retorna se o jogador foi eliminado nessa rodada
	public boolean getEliminadoNessaRodada() {
		return eliminadoNessaRodada;
	}

	//Retorna se o jogador conquistou um território nessa rodada
	public boolean getConquistouNessaRodada() {
		return conquistouNessaRodada;
	}

	//Altera a quantidade de exércitos que o jogador pode posicionar
	public void setQtdExercitoPosic(int qtdExercitoPosic) {
		this.qtdExercitoPosic = qtdExercitoPosic;
	}
	
	//Altera o objetivo do jogador
	public void setObj(Objetivo obj) {
		this.obj = obj;
	}

	//Altera se o jogador foi eliminado nessa rodada para verificação de objetivos
	public void setEliminadoNessaRodada(boolean eliminadoNessaRodada) {
		this.eliminadoNessaRodada = eliminadoNessaRodada;
	}

	//Altera se o jogador conquistou um território nessa rodada para recepção de cartas
	public void setConquistouNessaRodada(boolean conquistouNessaRodada) {
		this.conquistouNessaRodada = conquistouNessaRodada;
	}

	public Jogador getJMatou() {
		return jMatou;
	}

	public void setJMatou(Jogador jMatou) {
		this.jMatou = jMatou;
	}

}
