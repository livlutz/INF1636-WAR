package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

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

	private ArrayList <Territorio> territorios = new ArrayList <Territorio> (); //Guarda os territórios que possui
	
	private boolean eliminadoNessaRodada = false; //Guarda se o jogador foi eliminado nessa rodada

	//Construtor da classe
	public Jogador(String nome, int cor) {
		this.nome = nome;
		this.cor = cor;
	}
	
	//Permite ver todas as cartas na posse do jogador
	public void verCartas() {
		for(Cartas c : cartas) {
			System.out.printf("Carta: %s - territorio : %s\n", c.getF(),c.getTerritorio().getNome());	
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
		t.alterarQndExercitos(qtdExercitos);
	}

	// Executa todos os passos para a rodada de posicionamento de um jogador - falta implementar
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
	
	//Verifica se o jogador pode trocar cartas
	public boolean temTroca(){
		int circulos = 0, quadrados = 0, triangulos = 0;
		for (Cartas c: cartas){
			if (c.f == Cartas.Formato.circulo)
				circulos++;
			else if (c.f == Cartas.Formato.Quadrado)
				quadrados++;
			else
				triangulos++;
		}
		if (circulos >= 3 || quadrados >= 3 || triangulos >= 3 || (circulos >= 1 && quadrados >= 1 && triangulos >= 1))
			return true;
		return false;
	}

	//Concede exércitos ao jogador apos trocar cartas e conta a qtd de trocas
	public boolean trocarCartas (Cartas a, Cartas b, Cartas c) {
		if(temTroca()) {
			int primTrocaExerc = 4;
			
			//primeira vez trocando
			if(qtdTrocaCartas == 0) {
				qtdExercitoPosic += primTrocaExerc;
			}
			
			//quando temos 2 ate 5 trocas ja efetuadas
			else if (qtdTrocaCartas < 5) {
				qtdExercitoPosic += (primTrocaExerc + 2 * qtdTrocaCartas);
			}
			
			//temos mais de 5 trocas ja efetuadas
			else {
				int diferenca = qtdTrocaCartas - 5;
				qtdExercitoPosic += ((diferenca + 3) * 5);
			}
		
			qtdTrocaCartas++;
			cartas.remove(a);
			cartas.remove(b);
			cartas.remove(c);
			return true;
		}
		
		else {
			return false;
		}
		
	}

	//Adiciona uma carta ao jogador
	public void addCarta(Cartas c) {
		cartas.add(c);
	}

	//Adiciona um território ao jogador
	public void addTerritorio(Territorio t) {
		territorios.add(t);
	}

	//Remove um território do jogador
	public void removeTerritorio(Territorio t) {
		territorios.remove(t);
	}

	// --------------------------- getters & setters ---------------------------

	//Retorna a cor do jogador
	public int getCor() {
		return cor;
	}

	//Retorna o nome do jogador
	public String getNome() {
		return nome;
	}

	//Retorna a quantidade de exércitos que o jogador pode posicionar
	public int getQtdExercitoPosic() {
		return qtdExercitoPosic;
	}

	//Retorna a quantidade de territórios que o jogador possui
	public int getQtdTerritorios(){
		return qtdTerritorios;
	}

	//Retorna o objetivo do jogador
	public String getObj() {
		return obj;
	}

	//Retorna a quantidade de trocas de cartas que o jogador fez
	public int getQtdTrocaCartas() {
		return qtdTrocaCartas;
	}
	
	//Retorna as cartas que o jogador possui (o ArrayList de cartas)
	public ArrayList<Cartas> getCartas() {
		return cartas;
	}

	//Retorna os territórios que o jogador possui (o ArrayList de territórios)
	public ArrayList<Territorio> getTerritorios() {
		return territorios;
	}

	//Retorna se o jogador foi eliminado nessa rodada
	public boolean getEliminadoNessaRodada() {
		return eliminadoNessaRodada;
	}

	//Altera a quantidade de exércitos que o jogador pode posicionar
	public void setQtdExercitoPosic(int qtdExercitoPosic) {
		this.qtdExercitoPosic = qtdExercitoPosic;
	}

	//Altera a quantidade de territórios que o jogador possui
	public void setQtdTerritorios(int qtd){
		qtdTerritorios = qtd;
	}
	
	//Altera o objetivo do jogador
	public void setObj(String obj) {
		this.obj = obj;
	}

	//Altera se o jogador foi eliminado nessa rodada para verificação de objetivos
	public void setEliminadoNessaRodada(boolean eliminadoNessaRodada) {
		this.eliminadoNessaRodada = eliminadoNessaRodada;
	}

}
