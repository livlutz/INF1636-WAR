package Model;

import java.util.ArrayList;
import java.util.Random;

class Jogador {
	
	/*Faltando: 
		Adiconar cartas no array
	*/

	// enum com as cores que um jogador pode escolher
	public static enum Cor {
		branco,preto,vermelho,azul,amarelo,verde;
	}
	
	private int cor; //Guarda a cor do jogador
	
	private String nome; //Guarda o nome do jogador
	
	private int qtdExercitoPosic; //Guarda a quantidade de Exércitos que pode posicionar
	
	private static int qtdTrocaCartas = 0; //Guarda a quantidade de cartas que pode trocar
	
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
	
	//Altera a quantidade de territórios 
	protected boolean alterarQtdTerritorios (int qtd) {
			// se tentar subtrair mais territórios do que tem 
			if (qtd < 0)
				if ((qtd * (-1)) > this.qtdTerritorios)
					return false;
			this.qtdTerritorios += qtd;
			return true;
		}

	// getters e setters padrão
	public int getQtdExecPos() {
		return qtdExercitoPosic;
	}

	public static void trocarCartas (Cartas a, Cartas b, Cartas c) {
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

	public static int getQtdTrocaCartas() {
		return qtdTrocaCartas;
	}
	
	//pra testar precisamos do metodo de add cartas no array
	public ArrayList<Cartas> getCartas() {
		return cartas;
	}

	//vamos pensar se precisamos desse metodo
	public static Cor Cor(int i) {
		return Cor(i);
	}

}
