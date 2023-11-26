package Model;

import java.util.HashMap;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

class Tabuleiro{

	//Mapa do tabuleiro
	public HashMap<String,Territorio> mapTerritorios = new HashMap<String,Territorio>();
	private HashMap<String,Continente> mapContinente = new HashMap<String,Continente>();
	private static Tabuleiro tabuleiro = null;
	private ArrayList<Territorio> listaTerritorios = new ArrayList<Territorio>();
	
	//Construtor
	private Tabuleiro() {
		InstanciaTerritorios();
	}
	
	//Inicializando o tabuleiro
	public void Inicializa() {
		for (Territorio t: listaTerritorios) {
			t.instanciaAdjacentes();
		}
		InstanciaContinente();
	}
	
	//Retorna a instância do tabuleiro
		public static synchronized Tabuleiro getTabuleiro() {
			if (tabuleiro == null){
				tabuleiro = new Tabuleiro();
			}
			return tabuleiro;
		}
		
	
	//Move exércitos por territórios
	public void MoverExercitos(int qtdExercitos,Territorio origem,Territorio destino) {
		
		if (origem.getQntExercitos() > qtdExercitos) {
			origem.setQntExercitos(origem.getQntExercitos() - qtdExercitos);
			destino.setQntExercitos(destino.getQntExercitos() + qtdExercitos);
		}
		else {
			System.out.println("Nao pode mover essa quantidade de exercitos");
		}
	}
	
	// Distribui os territórios entre os jogadores, colocando um exército em cada
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


	//Inicializa os territórios
	private void InstanciaTerritorios() {
		Territorio t;
		
			//Territórios América do Sul
				t = new Territorio("Brasil");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);

				t = new Territorio("Argentina");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);

				t = new Territorio("Peru");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);

				t = new Territorio("Venezuela");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				

				//Territórios América do Norte
				t = new Territorio("Nova York");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Mexico");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);

				t = new Territorio("California");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Groelandia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);

				t = new Territorio("Alasca");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Vancouver");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Calgary");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);

				t = new Territorio("Quebec");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Texas");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
			//Territórios Europa
				t = new Territorio("Polonia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Franca");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Suecia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Espanha");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);

				t = new Territorio("Reino Unido");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Romenia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Ucrania");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Italia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
			//Territórios África
				t = new Territorio("Egito");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Argelia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Nigeria");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Somalia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Angola");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Africa do Sul");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
			//Territórios Ásia
				t = new Territorio("Estonia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Letonia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Russia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Siberia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Turquia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Cazaquistao");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Japao");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Siria");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Paquistao");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("China");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Mongolia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Coreia do Norte");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Coreia do Sul");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Jordania");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Iraque");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Ira");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("India");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Bangladesh");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Tailandia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Arabia Saudita");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
			//Territórios Oceania
				t = new Territorio("Australia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Indonesia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Perth");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
				
				t = new Territorio("Nova Zelandia");
				mapTerritorios.put(t.getNome(), t);
				listaTerritorios.add(t);
		
	}
	
	//Inicializa os continentes
	private void InstanciaContinente() {
		Territorio t;
		Continente c; 
		
		//América do Sul - Verde 
		c = new Continente("America do Sul", 2, new Color(0,255,0));
		
		t = mapTerritorios.get("Brasil");
		c.territorios.add(t);

		t = mapTerritorios.get("Argentina");
		c.territorios.add(t);

		t = mapTerritorios.get("Peru");
		c.territorios.add(t);

		t = mapTerritorios.get("Venezuela");
		c.territorios.add(t);

		mapContinente.put(c.getNome(), c);

		//América do Norte - Vermelho 
		c = new Continente("America do Norte", 5, new Color (255,0,0));

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

		//Europa - Azul escuro 
		c = new Continente("Europa", 5, new Color (0,0,153));

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

		//África - Roxo 
		c = new Continente("Africa", 3, new Color (102,0,153));

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

		//Ásia -  Amarelo 
		c = new Continente("Asia", 7, new Color (255,255,0));

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

		//Oceania - Azul claro  
		c = new Continente("Oceania", 2, new Color (51,204,255));

		t = mapTerritorios.get("Australia");
		c.territorios.add(t);

		t = mapTerritorios.get("Nova Zelandia");
		c.territorios.add(t);

		t = mapTerritorios.get("Indonesia");
		c.territorios.add(t);

		t = mapTerritorios.get("Perth");
		c.territorios.add(t);

		mapContinente.put(c.getNome(), c);
	}
	
	// ----------------- Getters & Setters -----------------
	
	// Retorna objeto territorio a partir do nome
	public Territorio getTerritorio(String nomeTerritorio){
		return mapTerritorios.get(nomeTerritorio);
	}

	// Retorna objeto continente a partir do nome
	public Continente getContinente(String nomeContinente){
		return mapContinente.get(nomeContinente);
	}

	//Retorna hashmap de territorios
	public HashMap<String, Territorio> getMapTerritorios() {
		return mapTerritorios;
	}
	
	//Retorna hashmap de continentes
	public HashMap<String, Continente> getMapContinentes() {
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
	
}
