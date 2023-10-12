package Model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class Tabuleiro{
	//Mapa do tabuleiro
	HashMap<String,Territorio> map = new HashMap<String,Territorio>();
	
	//Guarda a quantidade de jogadores
	int numJogadores = 5;
	
	//Guarda cada jogador
	ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	
	//Inicializa os objetivos
	Objetivo objetivo = new Objetivo();
	
	//Construtor
	public Tabuleiro() {
		Territorio t = new Territorio("Brasil");
		
		map.put(t.nome, t);
		
		t = new Territorio("EUA");
		
		map.put(t.nome, t);
		
		t = new Territorio("Angola");
		
		map.put(t.nome, t);
		
		t = new Territorio("Alaska");
		
		map.put(t.nome, t);
		
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

	
	
}
