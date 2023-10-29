package Model;

import java.util.ArrayList;
import java.util.Collections;

class Jogo {
    private static Jogo jogo = null;

    // Guarda o tabuleiro
    private Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
    
	// Guarda a quantidade de jogadores
	private int numJogadores;
	
	// Guarda cada jogador
	private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();

    // Guarda lista de objetivos
    private ArrayList<Objetivo> objetivos = new ArrayList<Objetivo>();

    // Construtor privado para o singleton
    private Jogo(){

    }

    // Singleton
    public static Jogo getJogo(){
        if(jogo == null){
            jogo = new Jogo();
        }
        return jogo;
    }

    public void InstanciaObjetivos(){
         
    }

    	
	 //Inicializa cada jogador
	public void InstanciaJogadores() {
		for (int i = 0;i < numJogadores;i++) {
			Jogador jogador = new Jogador("jogador",i);
			//falta pegar o objetivo
			String obj = objetivo.getObjetivoAleatorio();
			
			jogador.setObj(obj);
			
			jogadores.add(jogador);
		}
		Collections.shuffle(jogadores);
	}



}
