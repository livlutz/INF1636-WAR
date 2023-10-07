package Model;
import java.util.Random;

class Dado {
	public Dado() {}
	
	public int rodarDado() {
		Random rand = new Random();
		return rand.nextInt(6);
	}
}
