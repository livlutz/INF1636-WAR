package Model;

import java.util.ArrayList;
import java.awt.Color;

class Continente {

	//Guarda a cor do continente
	private Color corCont;
	
	//Guarda o nome do continente
	private String nome; 
	
	//Guarda a quantidade de exércitos obtidos pela sua conquista
	private int qtdExercitoConquista; 
	
	//Guarda os territórios presentes no continente
	protected ArrayList <Territorio> territorios = new ArrayList <Territorio>();

	//Construtor
	public Continente(String nome,int execConquista, Color corCont) {
		this.nome = nome;
		qtdExercitoConquista = execConquista;
		this.corCont = corCont;
	}

	// Adicionar Territórios no continente
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

	//Retorna a cor do continente
	public Color getCorCont(){
		return this.corCont;
	}

	//Altera a cor do continente
	public void setCorCont(Color corCont){
		this.corCont = corCont;
	}
}
 
