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
		//colocar todos os territorios que um certo continente possui
		
	}
	
	//falta o metodo de se e inteiramente dominado por algm
}
