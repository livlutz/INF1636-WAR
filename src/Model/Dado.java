package Model;
import java.util.Random;

public class Dado {

	Random rand = new Random();
	
	public Dado() {

	}
	
	int rodarDado(){
		return rand.nextInt(6);
	}
}
