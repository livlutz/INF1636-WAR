package Model;

abstract class Objetivo{
	protected String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
	public abstract boolean alcancou(Jogador j);

	public String getObjetivoAleatorio() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void criaObjetivos() {
		// TODO Auto-generated method stub
		
	}
		
}

