package Model;
class Cartas {
	
	//enum com a forma geométrica presente em cada carta
	public enum Formato{
		Quadrado,circulo,triangulo;
	}
	
	//Guarda o formato presente na carta
	protected Formato f;

	//Território da carta
	private Territorio territorio;

	//Imagem da carta
	
	//Construtor
	public Cartas(int i, Territorio t) {
		f = Formato.values()[i];
		territorio = t;
	}
	
	//Verifica se o território da carta pertence ao jogador
	public boolean terrPertenceJogador(Jogador j) {
		for (Territorio t : j.getTerritorios()) {
			if(this.territorio == t) {
				return true;
			}
		}
		return false;
	}

	//----------------- Getters & Setters -----------------

	//Retorna o formato da carta
	public Formato getF() {
		return f;
	}

	//Altera o formato da carta
	public void setF(Formato f) {
		this.f = f;
	}

	//Retorna o território da carta
	public Territorio getTerritorio() {
		return territorio;
	}

	//Altera o território da carta
	public void setTerritorio(Territorio territorio) {
		this.territorio = territorio;
	}
}
