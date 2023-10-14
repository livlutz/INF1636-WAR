package Model;
import java.util.Random;

class Cartas {
	
	//enum com a forma geométrica presente em cada carta
	public enum Formato{
		Quadrado,ciruclo,retangulo;
	}
	
	//Guarda o formato presente na carta
	protected Formato f;
	
	//Construtor
	public Cartas() {
		Random r = new Random ();
		f = Formato.values() [r.nextInt(Formato.values().length)];
	}

}
