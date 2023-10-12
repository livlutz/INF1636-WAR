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
	
	//verifica se o continente é inteiramente dominado por 1 jogador
	public boolean continenteDominado(Jogador j) {
		int contaMesmoJogador = 0;
		for(Territorio t:territorios) {
			if(t.getJogador() == j) {
				contaMesmoJogador++;
			}
		}
		return contaMesmoJogador == territorios.size();
	}
}
