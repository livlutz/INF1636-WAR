package Model;

import java.util.ArrayList;

class Continente {

	// enum com as cores que um jogador pode escolher
	public static enum CorCont {
		verde, laranja, azul, rosa, amarelo, vermelho;
	}

	//Guarda o nome do continente
	private String nome; 
	
	//Guarda a quantidade de exércitos obtidos pela sua conquista
	private int qtdExercitoConquista; 
	
	//Guarda os territórios presentes no continente
	protected ArrayList <Territorio> territorios = new ArrayList <Territorio>();

	//Guarda a cor do continente
	private CorCont cor;

	//Construtor
	public Continente(String nome,int execConquista, CorCont corCont) {
		this.nome = nome;
		qtdExercitoConquista = execConquista;
		this.cor = corCont;
	}

	// Adicionar Territorios no continente
	public void addTerritorio(Territorio ter){
		this.territorios.add(ter);
	}

	// Verifica se o continente está inteiramente dominado por algum jogador
	public boolean dominado(Jogador jogador){
		for (Territorio t: territorios){
			if (t.getJogador() != jogador)
				return false;
		}
		return true;
	}

	// Verifica se determinado território pertence ao continente
	public boolean noContinente(Territorio ter){
		for (Territorio t: territorios){
			if (t == ter)
				return true;
		}
		return false;
	}

	// ----------------- Getters & Setters -----------------

	//Retorna os territórios do continente
	public ArrayList <Territorio> getTerritorios(){
		return this.territorios;
	}

	//Retorna o nome do continente
	public String getNome(){
		return this.nome;
	}

	//Retorna a qtd de exércitos obtidos pela conquista do continente
	public int getQtdExerc(){
		return this.qtdExercitoConquista;
	}

	
}
 
