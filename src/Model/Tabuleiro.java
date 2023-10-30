package Model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

class Tabuleiro{

	//Mapa do tabuleiro
	public static HashMap<String,Territorio> mapTerritorios = new HashMap<String,Territorio>();
	private static HashMap<String,Continente> mapContinente = new HashMap<String,Continente>();
	private static Tabuleiro tabuleiro = null;
	private ArrayList<Territorio> listaTerritorios = new ArrayList<Territorio>();
	private ArrayList<Cartas> listaCartas = new ArrayList<Cartas>();
	
	//Guarda a quantidade de jogadores
	private int numJogadores = 5;
	
	//Guarda cada jogador
	private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	
	//Construtor
	private Tabuleiro() {
		InstanciaTerritorios();
		InstanciaContinente();
	}
	
	//Move exercitos por territórios
	public void MoverExercitos(int qtdExercitos,Territorio origem,Territorio destino) {
		
		if (origem.getQntExercitos() > qtdExercitos) {
			origem.setQntExercitos(origem.getQntExercitos() - qtdExercitos);
			destino.setQntExercitos(destino.getQntExercitos() + qtdExercitos);
		}
		else {
			System.out.println("Nao pode mover essa quantidade de exercitos");
		}
	}
	
	// distribui os territórios entre os jogadores, colocando um exército em cada
	public void distribuiTerritorios(ArrayList<Jogador> jogadores){
		// Embaralha a lista de territórios
		Collections.shuffle(listaTerritorios);

		int qtdJogadores = jogadores.size();
		int qtdTerritorios = listaTerritorios.size();

		for (int i = 0; i < qtdTerritorios; i++) {
			Territorio t = listaTerritorios.get(i);
			Jogador j = jogadores.get(i % qtdJogadores);

			// Define o jogador do território
			t.setJogador(j);
			// Define 1 para a quantidade de exércitos do território
			t.setQntExercitos(1);

			// Adiciona o território na lista de territórios do jogador
			j.addTerritorio(t);

		}
	}

	// Retorna hashmap de continentes
	public static HashMap<String, Continente> getContinentes(){
		return mapContinente;
	}

	//Inicializa os territórios
	private void InstanciaTerritorios() {
		Territorio t;
		
		//Territórios América do Sul
		t = new Territorio("Brasil");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);

		t = new Territorio("Argentina");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);

		t = new Territorio("Peru");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);

		t = new Territorio("Venezuela");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		

		//Territórios América do Norte
		t = new Territorio("Nova York");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Mexico");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);

		t = new Territorio("California");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Groelandia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);

		t = new Territorio("Alasca");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Vancouver");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Calgary");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);

		t = new Territorio("Quebec");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Texas");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		//Territórios Europa
		t = new Territorio("Polonia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Franca");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Suecia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Espanha");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);

		t = new Territorio("Reino Unido");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Romania");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Ucrania");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Italia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		//Territórios África
		t = new Territorio("Egito");
		mapTerritorios.put(t.getNome(), t);
		t.AddAdjacente(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Argelia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Nigeria");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Somalia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Angola");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Africa do Sul");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		//Territórios Ásia
		t = new Territorio("Estonia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Letonia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Russia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Siberia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Turquia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Cazaquistao");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Japao");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Siria");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Paquistao");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("China");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Mongolia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Coreia do Norte");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Coreia do Sul");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Jordania");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Iraque");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Ira");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("India");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Bangladesh");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Tailandia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Arabia Saudita");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		//Territórios Oceania
		t = new Territorio("Australia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Indonesia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Perth");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
		t = new Territorio("Nova Zelandia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		
	}
	
	//Inicializa os continentes
	private void InstanciaContinente() {
		Territorio t;
		Continente c; 
		
		//America do Sul
		c = new Continente("America do Sul", 2, Continente.CorCont.verde);
		
		t = mapTerritorios.get("Brasil");
		c.territorios.add(t);

		t = mapTerritorios.get("Argentina");
		c.territorios.add(t);

		t = mapTerritorios.get("Peru");
		c.territorios.add(t);

		t = mapTerritorios.get("Venezuela");
		c.territorios.add(t);

		mapContinente.put(c.getNome(), c);

		//America do Norte
		c = new Continente("America do Norte", 5, Continente.CorCont.laranja);

		t = mapTerritorios.get("Nova York");
		c.territorios.add(t);

		t = mapTerritorios.get("Mexico");
		c.territorios.add(t);

		t = mapTerritorios.get("California");
		c.territorios.add(t);

		t = mapTerritorios.get("Groelandia");
		c.territorios.add(t);

		t = mapTerritorios.get("Texas");
		c.territorios.add(t);

		t = mapTerritorios.get("Alasca");
		c.territorios.add(t);

		t = mapTerritorios.get("Calgary");
		c.territorios.add(t);

		t = mapTerritorios.get("Vancouver");
		c.territorios.add(t);

		t = mapTerritorios.get("Quebec");
		c.territorios.add(t);

		mapContinente.put(c.getNome(), c);

		//Europa
		c = new Continente("Europa", 5, Continente.CorCont.azul);

		t = mapTerritorios.get("Espanha");
		c.territorios.add(t);

		t = mapTerritorios.get("Reino Unido");
		c.territorios.add(t);

		t = mapTerritorios.get("Franca");
		c.territorios.add(t);

		t = mapTerritorios.get("Italia");
		c.territorios.add(t);

		t = mapTerritorios.get("Polonia");
		c.territorios.add(t);

		t = mapTerritorios.get("Romenia");
		c.territorios.add(t);

		t = mapTerritorios.get("Suecia");
		c.territorios.add(t);

		t = mapTerritorios.get("Ucrania");
		c.territorios.add(t);

		mapContinente.put(c.getNome(), c);

		//Africa
		c = new Continente("Africa", 3, Continente.CorCont.rosa);

		t = mapTerritorios.get("Egito");
		c.territorios.add(t);

		t = mapTerritorios.get("Nigeria");
		c.territorios.add(t);

		t = mapTerritorios.get("Africa do Sul");
		c.territorios.add(t);

		t = mapTerritorios.get("Somalia");
		c.territorios.add(t);

		t = mapTerritorios.get("Argelia");
		c.territorios.add(t);

		t = mapTerritorios.get("Angola");
		c.territorios.add(t);

		mapContinente.put(c.getNome(), c);

		//Asia
		c = new Continente("Asia", 7, Continente.CorCont.amarelo);

		t = mapTerritorios.get("Arabia Saudita");
		c.territorios.add(t);

		t = mapTerritorios.get("India");
		c.territorios.add(t);

		t = mapTerritorios.get("Siberia");
		c.territorios.add(t);

		t = mapTerritorios.get("China");
		c.territorios.add(t);

		t = mapTerritorios.get("Mongolia");
		c.territorios.add(t);

		t = mapTerritorios.get("Japao");
		c.territorios.add(t);

		t = mapTerritorios.get("Coreia do Norte");
		c.territorios.add(t);

		t = mapTerritorios.get("Coreia do Sul");
		c.territorios.add(t);

		t = mapTerritorios.get("Bangladesh");
		c.territorios.add(t);

		t = mapTerritorios.get("Tailandia");
		c.territorios.add(t);

		t = mapTerritorios.get("Jordania");
		c.territorios.add(t);

		t = mapTerritorios.get("Iraque");
		c.territorios.add(t);

		t = mapTerritorios.get("Ira");
		c.territorios.add(t);

		t = mapTerritorios.get("Paquistao");
		c.territorios.add(t);

		t = mapTerritorios.get("Turquia");
		c.territorios.add(t);

		t = mapTerritorios.get("Cazaquistao");
		c.territorios.add(t);

		t = mapTerritorios.get("Siria");
		c.territorios.add(t);

		t = mapTerritorios.get("Letonia");
		c.territorios.add(t);

		t = mapTerritorios.get("Estonia");
		c.territorios.add(t);

		t = mapTerritorios.get("Russia");
		c.territorios.add(t);

		mapContinente.put(c.getNome(), c);

		//Oceania
		c = new Continente("Oceania", 2, Continente.CorCont.vermelho);

		t = mapTerritorios.get("Australia");
		c.territorios.add(t);

		t = mapTerritorios.get("Nova Zelandia");
		c.territorios.add(t);

		t = mapTerritorios.get("Indoneia");
		c.territorios.add(t);

		t = mapTerritorios.get("Perth");
		c.territorios.add(t);

		mapContinente.put(c.getNome(), c);
	}
	
	// ----------------- Getters & Setters -----------------
	
	// Retorna objeto territorio a partir do nome
	public static Territorio getTerritorio(String nomeTerritorio){
		return mapTerritorios.get(nomeTerritorio);
	}

	// Retorna objeto continente a partir do nome
	public static Continente getContinente(String nomeContinente){
		return mapContinente.get(nomeContinente);
	}

	//Retorna a instancia do tabuleiro
	public static Tabuleiro getTabuleiro() {
		if (tabuleiro == null){
			tabuleiro = new Tabuleiro();
		}
		return tabuleiro;
	}
	
	//Retorna hashmap de territorios
	public static HashMap<String, Territorio> getMapTerritorios() {
		return mapTerritorios;
	}
	
	//Retorna hashmap de continentes
	public static HashMap<String, Continente> getMapContinentes() {
		return mapContinente;
	}
	
	//Retorna o array de territorios
	public ArrayList<Territorio> getlistaTerritorios() {
		return listaTerritorios;
	}
	
	//Altera lista de territorios
	public void setlistaTerritorios(ArrayList<Territorio> listaTerritorios) {
		this.listaTerritorios = listaTerritorios;
	}
	
	//Altera hashmap de territorios
	public static void setMapTerritorios(HashMap<String, Territorio> mapTerritorios) {
		Tabuleiro.mapTerritorios = mapTerritorios;
	}
	
	//Altera hashmap de continentes
	public static void setMapContinente(HashMap<String, Continente> mapContinente) {
		Tabuleiro.mapContinente = mapContinente;
	}

}
