package Model;
import java.util.Random;

class Cartas {
	
	//enum com a forma geométrica presente em cada carta
	public enum Formato{
		Quadrado,circulo,retangulo;
	}
	
	//Guarda o formato presente na carta
	protected Formato f;
	
	//Construtor
	public Cartas() {
		Random r = new Random ();
		f = Formato.values() [r.nextInt(Formato.values().length)];
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


}
