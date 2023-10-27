package Model;

public abstract class Objetivo{
	protected String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
	public abstract boolean alcancou(Jogador j);
		
}

