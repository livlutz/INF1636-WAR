package Model;
import java.util.Random;

class Dado {
	
	//Construtor
	public Dado() {}
	
	//Roda o dado que retorna o número de sua face
	public int rodarDado() {
		Random rand = new Random();
		return rand.nextInt(6);
	}
}
