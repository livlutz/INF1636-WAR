package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

class Jogador {

	// enum com as cores que um jogador pode escolher
	public static enum Cor {
		branco,preto,vermelho,azul,amarelo,verde;
	}
	
	private int cor; //Guarda a cor do jogador
	
	private String nome; //Guarda o nome do jogador
	
	private int qtdExercitoPosic; //Guarda a quantidade de Exércitos que pode posicionar
	
	private int qtdTrocaCartas = 0; //Guarda a quantidade de cartas que pode trocar
	
	private int qtdTerritorios = 0;  //Guarda a quantidade de territórios em sua posse
	
	private ArrayList <Cartas> cartas = new ArrayList <Cartas> ();  //Guarda as cartas que possui
	
	private String obj;  //Guarda seu objetivo no jogo
	
	//Construtor da classe
	public Jogador(String nome, int cor) {
		this.nome = nome;
		this.cor = cor;
	}
	
	//Permite ver todas as cartas na posse do jogador
	public void verCartas() {
		for(Cartas c : cartas) {
			System.out.println(c.f);
		}
	}

	// Posicionar exércitos em continente dominado
	// Deve ser chamado no início da jogada se o jogador dominar um continente
	public void posicionarExercCont(Continente cont){
		// objeto para pegar input de nome
		Scanner input = new Scanner(System.in);

		this.qtdExercitoPosic = cont.getQtdExerc();
		while (this.qtdExercitoPosic > 0){
			System.out.println("Você tem " + this.qtdExercitoPosic + " exércitos para posicionar em " + cont.getNome() + ".");
			
			// pede ao usuário o nome do território
			System.out.println("Digite o nome do território: ");
			String nomeTerritorio = input.nextLine();

			// procura o território no hashmap do tabuleiro 
			Territorio t = Tabuleiro.getTerritorio(nomeTerritorio);
			
			// verifica se o território existe
			if (t == null){
				System.out.println("Território não encontrado.");
				continue;
			}

			// verifica se o território pertence ao continente
			if (cont.noContinente(t)){

				// pede ao usuário a quantidade de exércitos a serem posicionados
				System.out.println("Digite a quantidade de exércitos a serem posicionados: ");
				int qtdExercitos = input.nextInt();
				input.nextLine();

				// verifica se a quantidade de exércitos é menor ou igual a quantidade de exércitos que o jogador pode posicionar
				if (qtdExercitos <= this.qtdExercitoPosic && qtdExercitos > 0){

					// posiciona os exércitos no território
					this.posicionarExercitos(t, qtdExercitos);

					// subtrai a quantidade de exércitos posicionados da quantidade de exércitos que o jogador pode posicionar
					this.qtdExercitoPosic -= qtdExercitos;
				}
				else
					System.out.println("Você não pode posicionar essa quantidade de exércitos.");
				
			}
		}
		input.close();
	}

	// Posicionar exércitos em território do jogador
	// Cabe a função que chamou verificar se pode
	public void posicionarExercitos(Territorio t, int qtdExercitos){
	
		t.alterarQtdExercitos(qtdExercitos);

	}

	// Executa todos os passos para a rodada de posicionamento de um jogador
	public void rodadaDePosicionamento(){
		HashMap <String, Continente> continentes = Tabuleiro.getContinentes();
		
		// para cada continente, se o jogador dominar, posiciona os exércitos
		for (Map.Entry<String, Continente> e: continentes.entrySet()){
			if (e.getValue().dominado(this))
				this.posicionarExercCont(e.getValue());
		}

		this.qtdExercitoPosic = this.qtdTerritorios/2;

		// objeto para pegar input
		Scanner input = new Scanner(System.in);

		if (this.temTroca()){
			this.verCartas();
			System.out.println("Você quer trocar cartas? (S/N)");
			String resp = input.nextLine();
			if (resp.equals("S") || resp.equals("s")){
				System.out.println("Digite os três formatos das cartas que deseja trocar (C/Q/T): ");
				String a = input.nextLine();
				String b = input.nextLine();
				String c = input.nextLine();


				// implementar achar as cartas para usar no método trocarCartas

				
			}
		}
		

		// posiciona os exércitos em territórios do jogador
		while (this.qtdExercitoPosic > 0){
			System.out.println("Você tem " + this.qtdExercitoPosic + " exércitos para posicionar.");
			
			// pede ao usuário o nome do território
			System.out.println("Digite o nome do território: ");
			String nomeTerritorio = input.nextLine();

			// procura o território no hashmap do tabuleiro 
			Territorio t = Tabuleiro.getTerritorio(nomeTerritorio);
			
			// verifica se o território existe
			if (t == null){
				System.out.println("Território não encontrado.");
				continue;
			}

			// verifica se o território pertence ao jogador
			if (t.getJogador() == this){

				// pede ao usuário a quantidade de exércitos a serem posicionados
				System.out.println("Digite a quantidade de exércitos a serem posicionados: ");
				int qtdExercitos = input.nextInt();
				input.nextLine();

				// verifica se a quantidade de exércitos é menor ou igual a quantidade de exércitos que o jogador pode posicionar
				if (qtdExercitos <= this.qtdExercitoPosic && qtdExercitos > 0){

					// posiciona os exércitos no território
					this.posicionarExercitos(t, qtdExercitos);

					// subtrai a quantidade de exércitos posicionados da quantidade de exércitos que o jogador pode posicionar
					this.qtdExercitoPosic -= qtdExercitos;
				}
				
				else {
					System.out.println("Você não pode posicionar essa quantidade de exércitos.");
				}
			}
		}
		input.close();
	}
	
	//Altera a quantidade de territórios 
	protected boolean alterarQtdTerritorios (int qtd) {
			// se tentar subtrair mais territórios do que tem 
			if (qtd < 0) {
				if ((qtd * (-1)) > this.qtdTerritorios) {
					return false;
				}
			}
			this.qtdTerritorios += qtd;
			return true;
	}
	
	//
	public boolean temTroca(){
		int circulos = 0, quadrados = 0, retangulos = 0;
		for (Cartas c: cartas){
			if (c.f == Cartas.Formato.ciruclo)
				circulos++;
			else if (c.f == Cartas.Formato.Quadrado)
				quadrados++;
			else
				retangulos++;
		}
		if (circulos >= 3 || quadrados >= 3 || retangulos >= 3 || (circulos >= 1 && quadrados >= 1 && retangulos >= 1))
			return true;
		return false;
	}

	//Concede exércitos ao jogador apos trocar cartas e conta a qtd de trocas
	public void trocarCartas (Cartas a, Cartas b, Cartas c) {
		int primTrocaExerc = 4;
		
		if(qtdTrocaCartas == 0) {
			qtdExercitoPosic += primTrocaExerc;
		}
		
		else {
			qtdExercitoPosic += (primTrocaExerc+2*qtdTrocaCartas);
		}
		
		qtdTrocaCartas++;
	}

	public int getCor() {
		return cor;
	}

	public String getNome() {
		return nome;
	}

	public int getQtdExercitoPosic() {
		return qtdExercitoPosic;
	}

	public void setQtdExercitoPosic(int qtdExercitoPosic) {
		this.qtdExercitoPosic = qtdExercitoPosic;
	}

	public int getQtdTerritorios(){
		return qtdTerritorios;
	}

	public void setQtdTerritorios(int qtd){
		qtdTerritorios = qtd;
	}
	
	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	public int getQtdTrocaCartas() {
		return qtdTrocaCartas;
	}
	
	//pra testar precisamos do metodo de add cartas no array
	public ArrayList<Cartas> getCartas() {
		return cartas;
	}
	
	public void addCarta() {
		//condicao add carta -> conquistar territorios em cada jogada
		if(this.alterarQtdTerritorios(qtdExercitoPosic)) {
			cartas.add(new Cartas());
		}
	}

}
