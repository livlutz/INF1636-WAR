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
        Objetivo obj;
        // Instancia objetivos de destruir jogador
        for(Jogador j : jogadores){
        obj = new ObjetivoDestruir(j);
        objetivos.add(obj);
        }

        // Instancia objetivos de conquistar continentes    
        obj = new ObjetivoContinentes(Tabuleiro.getContinente("America do Norte"),Tabuleiro.getContinente("Africa"),false);
        objetivos.add(obj);

        obj = new ObjetivoContinentes(Tabuleiro.getContinente("Asia"),Tabuleiro.getContinente("Africa"),false);
        objetivos.add(obj);

        obj = new ObjetivoContinentes(Tabuleiro.getContinente("America do Norte"),Tabuleiro.getContinente("Oceania"),false);
        objetivos.add(obj);

        obj = new ObjetivoContinentes(Tabuleiro.getContinente("Europa"),Tabuleiro.getContinente("America do Sul"),true);
        objetivos.add(obj);

        obj = new ObjetivoContinentes(Tabuleiro.getContinente("Asia"),Tabuleiro.getContinente("America do Sul"),true);
        objetivos.add(obj);

        obj = new ObjetivoContinentes(Tabuleiro.getContinente("Europa"),Tabuleiro.getContinente("Oceania"),true);
        objetivos.add(obj);

        // Instancia objetivos de conquistar territorios
        obj = new ObjetivoTerritorios(24);
        objetivos.add(obj);

        obj = new ObjetivoTerritorios(18);
        objetivos.add(obj);
    
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
