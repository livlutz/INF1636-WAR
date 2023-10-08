package Model;

import java.util.ArrayList;

class Continente {
	private String nome;
	private int qtdExercitoConquista;
	
	protected ArrayList <Territorio> territorios = new ArrayList <Territorio>();
	
	public Continente(String nome,int execConquista) {
		this.nome = nome;
		qtdExercitoConquista = execConquista;
		//colocar todos os territorios que um certo continente possui
		
	}
	
	//falta o metodo de se e inteiramente dominado por algm
}
