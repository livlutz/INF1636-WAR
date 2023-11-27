package Model;
import java.util.Random;
import java.util.Scanner;

class Dado {
	
	//Construtor
	public Dado() {}
	
	//Roda o dado que retorna o número de sua face
	public int rodarDado() {
		
		//Roda dado aleatoriamente
		Random rand = new Random();
		return rand.nextInt(6);
	
			
	}
}
