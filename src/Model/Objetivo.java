package Model;

abstract class Objetivo{
	//Guarda a descrição do objetivo
	protected String descricao;
	
	//Retorna a descrição do objetivo
	public String getDescricao() {
		return descricao;
	}
	
	//Retorna se o jogador alcançou o objetivo
	public abstract boolean alcancou(Jogador j);

	//Retorna o objetivo aleatório
	public String getObjetivoAleatorio() {
		// TODO Auto-generated method stub
		return null;
	}

	//Cria os objetivos
	public static void criaObjetivos() {
		// TODO Auto-generated method stub
		
	}
		
}

