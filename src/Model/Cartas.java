package Model;
import java.util.Random;

class Cartas {
	
	//enum com a forma geométrica presente em cada carta
	public enum Formato{
		Quadrado,circulo,retangulo;
	}
	
	//Guarda o formato presente na carta
	protected Formato f;

	//Territorio da carta
	private Territorio territorio;
	
	//Construtor
	public Cartas() {
		Random r = new Random ();
		f = Formato.values() [r.nextInt(Formato.values().length)];
		territorio = new Territorio(null);
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

	//Retorna o territorio da carta
	public Territorio getTerritorio() {
		return territorio;
	}

	//Altera o territorio da carta
	public void setTerritorio(Territorio territorio) {
		this.territorio = territorio;
	}
}
