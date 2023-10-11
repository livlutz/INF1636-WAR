package Model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class Tabuleiro{
	HashMap<String,Territorio> map = new HashMap<String,Territorio>();
	int numJogadores = 5;
	ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	Objetivo objetivo = new Objetivo();
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
	public boolean VerificarAtaque(Territorio atacante, Territorio defensor) {
		if (atacante.verificaAdjacencia(defensor)) {
			if(atacante.qntExercitos > 1) {
				return true;
			}
		}
		return false;
	}
	
	public void RealizaAtaque(Territorio atacante,Territorio defensor) {
		//TODO
	}
	public void inicializarContinente()	{
		Continente continente = new Continente("America do Sul", 2);
		continente.territorios.add(null);
	}
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
