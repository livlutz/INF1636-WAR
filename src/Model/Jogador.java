package Model;

import java.util.ArrayList;
import java.util.Random;

class Jogador {

	// enum com as cores que um jogador pode escolher
	public enum Cor {
		branco,preto,vermelho,azul,amarelo,verde;
	}
	
	private Cor cor;
	private String nome;
	private int numExercitoPosic;
	private static int qtdTrocaCartas = 0;
	private ArrayList <Cartas> cartas = new ArrayList <Cartas> ();
	Objetivo o = new Objetivo();
	
	public void setCor() {
		Random random = new Random();
		cor = Cor.values()[random.nextInt(Cor.values().length)];
	}
	
	public Jogador(String nome) {
		this.nome = nome;
		setCor();
	}
	
	public void VerObjetivo() {
		o.exibeObjetivo();
	}

	public void verCartas() {
		for(Carta c : cartas) {
			System.out.println(c);
		}
	}

	public int getQtdExecPos() {
		return numExercitoPosic;
	}

	public static void trocarCartas (Cartas a, Cartas b, Cartas c) {
		qtdTrocaCartas++;
	}

	/*Faltando: 
 		Public Getters e setters
   		Adiconar cartas no array
     		Territorios e ver qtd de territorios
 		*/
}
