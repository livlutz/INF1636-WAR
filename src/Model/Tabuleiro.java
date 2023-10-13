package Model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class Tabuleiro{
	//Mapa do tabuleiro
	HashMap<String,Territorio> mapTerritorios = new HashMap<String,Territorio>();
	HashMap<String,Continente> mapContinente = new HashMap<String,Continente>();
	
	//Guarda a quantidade de jogadores
	int numJogadores = 5;
	
	//Guarda cada jogador
	ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	
	//Inicializa os objetivos
	Objetivo objetivo = new Objetivo();
	
	//Construtor
	public Tabuleiro() {
		InstanciaTerritorios();
		
		Objetivo.criaObjetivos();
		
		InstanciaJogadores();
	}
	
	//Valida um ataque
	public boolean VerificarAtaque(Territorio atacante, Territorio defensor) {
		if (atacante.verificaAdjacencia(defensor)) {
			if(atacante.qntExercitos > 1) {
				return true;
			}
		}
		return false;
	}
	
	//Realiza um ataque
	public void RealizaAtaque(Territorio atacante,Territorio defensor) {
		//TODO
	}
	
	//Inicializa continentes no tabuleiro
	public void inicializarContinente()	{
		Continente continente = new Continente("America do Sul", 2);
		
		continente.territorios.add(null);
	}
	
	//Move exercitos por territórios
	public void MoverExercitos(int qntExercitos,Territorio origem,Territorio destino) {
		
		if (origem.qntExercitos > qntExercitos) {
			origem.qntExercitos -= qntExercitos;
			destino.qntExercitos += qntExercitos;
		}
		else {
			System.out.println("Nao pode mover essa quantidade de exercitos");
		}
	}
	
	public boolean VerificarObjetivoConcluido() {//TODO
		return false;//TODO remover
	}
	
	public void VerificarExercitosASeremAdicionados() {//TODO
		
	}
	
	 //Inicializa cada jogador
	public void InstanciaJogadores() {
		for (int i = 0;i<numJogadores;i++) {
			Jogador jogador = new Jogador("jogador",i);
			
			String obj = objetivo.getObjetivoAleatorio();
			
			jogador.setObj(obj);
			
			jogadores.add(jogador);
		}
		Collections.shuffle(jogadores);
	}
	void InstanciaTerritorios() {
		Territorio t = new Territorio("Brasil");
		
		mapTerritorios.put(t.getNome(), t);
		
		t = new Territorio("EUA");
		
		mapTerritorios.put(t.getNome(), t);
		
		t = new Territorio("Angola");
		
		mapTerritorios.put(t.getNome(), t);
		
		t = new Territorio("Alaska");
		
		mapTerritorios.put(t.getNome(), t);
		
	}
	
	void InstanciaContinente() {
		
		Continente c = new Continente("America do Sul", 2);
		mapContinente.put(c.getNome(), c);
		
		
		
	}
	
	
	
}
