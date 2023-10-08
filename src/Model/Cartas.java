package Model;
import java.util.Random;

class Cartas {

	public enum Formato{
		Quadrado,ciruclo,retangulo;
	}
	
	private Formato f;
	
	public Cartas() {
		Random r = new Random ();
		f = Formato.values() [r.nextInt(Formato.values().length)];
	}

}
