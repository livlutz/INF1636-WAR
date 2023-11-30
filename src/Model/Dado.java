package Model;
import java.util.Random;

class Dado {
	
	//Construtor
	public Dado() {}
	
	//Roda o dado que retorna o número de sua face
	public int rodarDado() {
		//Roda dado aleatoriamente
		Random rand = new Random();
		//Retorna um número aleatório entre 1 e 6
		return rand.nextInt(6) + 1;
	}
}
