package Model;


class Carta {
	
	//enum com a forma geométrica presente em cada carta
	public enum Formato{
		quadrado,circulo,triangulo,coringa;
	}
	
	//Guarda o formato presente na carta
	protected Formato f;

	//Território da carta
	private Territorio territorio;
	
	//Construtor
	public Carta(int i, Territorio t) {
		f = Formato.values()[i];
		this.territorio = t;
		
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
