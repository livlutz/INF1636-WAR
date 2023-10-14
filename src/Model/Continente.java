package Model;

import java.util.ArrayList;

class Continente {
	//Guarda o nome do continente
	private String nome; 
	
	//Guarda a quantidade de exércitos obtidos pela sua conquista
	private int qtdExercitoConquista; 
	
	//Guarda os territórios presentes no continente
	protected ArrayList <Territorio> territorios = new ArrayList <Territorio>();
	
	//Construtor
	public Continente(String nome,int execConquista) {
		this.nome = nome;
		qtdExercitoConquista = execConquista;
	}

	public ArrayList <Territorio> getTerritorios(){
		return this.territorios;
	}

	public String getNome(){
		return this.nome;
	}

	public int getQtdExerc(){
		return this.qtdExercitoConquista;
	}

	// Adicionar Territorios no continente
	public void addTerritorio(Territorio ter){
		this.territorios.add(ter);
	}

	// Verifica se o continente está inteiramente dominado por algum jogador
	public Jogador dominado(){
		Jogador jogador = territorios.get(0).getJogador();
		for (Territorio t: territorios){
			if (t.getJogador() != jogador)
				return null;
		}
		return jogador;
	}
}
 
