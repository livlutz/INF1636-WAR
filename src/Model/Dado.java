package Model;
import java.util.Random;
import java.util.Scanner;

class Dado {
	
	//Construtor
	public Dado() {}
	
	//Roda o dado que retorna o número de sua face
	public int rodarDado() {
		//Pegar um inteiro correspondente a face do dado
		Scanner sc = new Scanner(System.in);
		System.out.println("Deseja escolher a face do dado? (1 - Sim, 0 - Não)");
		String escolha = sc.nextLine();
		if(escolha == "1"){
			System.out.println("Digite um número de 1 a 6 correspondente a face do dado:");
			int face = sc.nextInt();
			return face;
		}
		
		else{
			Random rand = new Random();
			return rand.nextInt(6);
		}
			
	}
}
