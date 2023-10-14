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
		Territorio t;
		
		// territorios América do Sul
		t = new Territorio("Brasil");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Argentina");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Peru");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Venezuela");
		mapTerritorios.put(t.getNome(), t);


		// territorios América do Norte
		t = new Territorio("Nova Iorque");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Mexico");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("California");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Groelandia");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Ottawa");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Alasca");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Labrador");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Vancouver");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Mackenzie");
		mapTerritorios.put(t.getNome(), t);

		// territorios Europa

		t = new Territorio("Moscou");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Islandia");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Inglaterra");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Alemanha");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Polonia");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Franca");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Suecia");
		mapTerritorios.put(t.getNome(), t);


		// territorios Africa
		t = new Territorio("Egito");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Congo");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Africa do Sul");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Madagascar");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Argelia");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Sudao");
		mapTerritorios.put(t.getNome(), t);


		// territorios Asia
		t = new Territorio("Oriente Medio");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("India");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Siberia");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("China");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Mongolia");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Japao");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Aral");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Omsk");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Tchita");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Vladivostok");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Dudinka");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Vietna");
		mapTerritorios.put(t.getNome(), t);


		// territorios Oceania
		t = new Territorio("Australia");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Nova Guine");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Sumatra");
		mapTerritorios.put(t.getNome(), t);

		t = new Territorio("Borneo");
		mapTerritorios.put(t.getNome(), t);

	}
	
	void InstanciaContinente() {
		Continente c = new Continente("America do Sul", 2);
		Territorio t;
		t = mapTerritorios.get("Brasil");
		c.territorios.add(t);

		mapContinente.put(c.getNome(), c);


	}
	
	
	
}
