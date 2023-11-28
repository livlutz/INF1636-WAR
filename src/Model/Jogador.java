package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.awt.Color;
class Jogador {

	//Guarda a cor do jogador
	private Color cor; 
	
	//Guarda o nome do jogador
	private String nome; 
	
	//Guarda a quantidade de Exércitos que pode posicionar
	private int qtdExercitoPosic; 
	
	//Guarda a quantidade de cartas que pode trocar
	private int qtdTrocaCartas = 0; 
	
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

	//Guarda se o jogador conquistou um território nessa rodada
	private boolean conquistouNessaRodada = false; 
	
	
	//Construtor da classe
	public Jogador(String nome, Color cor) {
		this.nome = nome;
		this.cor = cor;
	}
	
	//Permite ver todas as cartas na posse do jogador
	public void verCartas() {
		for(Carta c : cartas) {
			System.out.printf("Carta: %s - territorio : %s\n", c.getF(),c.getTerritorio().getNome());	
		}
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
		for (Carta c: cartas){
			if (c.f == Carta.Formato.circulo)
				circulos++;
			else if (c.f == Carta.Formato.Quadrado)
				quadrados++;
			else
				triangulos++;
		}
		if (circulos >= 3 || quadrados >= 3 || triangulos >= 3 || (circulos >= 1 && quadrados >= 1 && triangulos >= 1))
			return true;
		return false;
	}

	//Concede exércitos ao jogador apos trocar cartas e conta a qtd de trocas
	public boolean trocarCartas (Carta a, Carta b, Carta c) {
		if(temTroca()) {
			int primTrocaExerc = 4;
			
			//Primeira vez trocando
			if(qtdTrocaCartas == 0) {
				qtdExercitoPosic += primTrocaExerc;
			}
			
			//Quando temos 2 até 5 trocas já efetuadas
			else if (qtdTrocaCartas < 5) {
				qtdExercitoPosic += (primTrocaExerc + 2 * qtdTrocaCartas);
			}
			
			//Temos mais de 5 trocas já efetuadas
			else {
				int diferenca = qtdTrocaCartas - 5;
				qtdExercitoPosic += ((diferenca + 3) * 5);
			}
			
			//Aumenta a quantidade de trocas de cartas
			qtdTrocaCartas++;

			//Remove as cartas do jogador
			cartas.remove(a);
			cartas.remove(b);
			cartas.remove(c);
			return true;
		}
		
		else {
			return false;
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

	public void atualizaQtdExPosicGeral(){
		this.qtdExercitoPosic = this.qtdTerritorios/2;
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

	//Retorna a quantidade de trocas de cartas que o jogador fez
	public int getQtdTrocaCartas() {
		return qtdTrocaCartas;
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

}
