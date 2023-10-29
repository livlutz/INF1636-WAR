package Model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class Tabuleiro{

	//Mapa do tabuleiro
	public static HashMap<String,Territorio> mapTerritorios = new HashMap<String,Territorio>();
	private static HashMap<String,Continente> mapContinente = new HashMap<String,Continente>();
	private static Tabuleiro tabuleiro = null;
	private ArrayList<Territorio> cartasTerritorios = new ArrayList<Territorio>();
	
	//Guarda a quantidade de jogadores
	private int numJogadores = 5;
	
	//Guarda cada jogador
	private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	
	//Inicializa os objetivos
	private Objetivo objetivo = new Objetivo();

	//Construtor
	private Tabuleiro() {
		InstanciaTerritorios();
		InstanciaContinente();
		Objetivo.criaObjetivos();
		InstanciaJogadores();
		distribuiTerritorios();
	}
	
	//Valida um ataque
	public boolean VerificarAtaque(Jogador atacante, Territorio tAtacante, Territorio tDefensor) {
		if(tAtacante.getJogador() == atacante)
			if (atacante != tDefensor.getJogador()) 
				if (tAtacante.verificaAdjacencia(tDefensor))
					if(tAtacante.getQntExercitos() > 1)
						return true;	
		return false;
	}
	
	//Realiza um ataque -> colocar na API jogo (ou classe jogo)
	public void RealizaAtaque(Territorio atacante,Territorio defensor) {
		
		if(VerificarAtaque(atacante.getJogador(), atacante, defensor)){
			//TODO
		}

		return;
	}
	
	//Move exercitos por territórios
	public void MoverExercitos(int qntExercitos,Territorio origem,Territorio destino) {
		
		if (origem.getQntExercitos() > qntExercitos) {
			origem.setQntExercitos(origem.getQntExercitos() - qntExercitos);
			destino.setQntExercitos(destino.getQntExercitos() + qntExercitos);
		}
		else {
			System.out.println("Nao pode mover essa quantidade de exercitos");
		}
	}
	
	 //Inicializa cada jogador
	public void InstanciaJogadores() {
		for (int i = 0;i < numJogadores;i++) {
			Jogador jogador = new Jogador("jogador",i);
			
			String obj = objetivo.getObjetivoAleatorio();
			
			jogador.setObj(obj);
			
			jogadores.add(jogador);
		}
		Collections.shuffle(jogadores);
	}
	
	// distribui os territórios entre os jogadores, colocando um exército em cada
	void distribuiTerritorios(){
		Collections.shuffle(cartasTerritorios);
		int qndJogadores = jogadores.size();
		int qndTerritorios = cartasTerritorios.size();

		for (int i = 0; i < qndTerritorios; i++) {
			Territorio t = cartasTerritorios.get(i);
			Jogador j = jogadores.get(i % qndJogadores);
			System.out.println("Jogador " + j.getNome() + " recebeu territorio " + t.getNome());
			t.setJogador(j);
			t.setQntExercitos(1);
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
		cartasTerritorios.add(t);
		t.setPosX(355);
		t.setPosY(471);

		t = new Territorio("Argentina");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(319);
		t.setPosY(580);

		t = new Territorio("Peru");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(275);
		t.setPosY(510);

		t = new Territorio("Venezuela");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(232);
		t.setPosY(452);
		
		//Territórios América do Norte
		t = new Territorio("Nova York");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(251);
		t.setPosY(281);
		
		t = new Territorio("Mexico");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(166);
		t.setPosY(361);

		t = new Territorio("California");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(135);
		t.setPosY(276);
		
		t = new Territorio("Groelandia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(383);
		t.setPosY(118);

		t = new Territorio("Alasca");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(114);
		t.setPosY(148);
		
		t = new Territorio("Vancouver");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(194);
		t.setPosY(201);
		
		t = new Territorio("Calgary");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(216);
		t.setPosY(155);

		t = new Territorio("Quebec");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(325);
		t.setPosY(195);
		
		t = new Territorio("Texas");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(189);
		t.setPosY(282);
		
		//Territórios Europa
		t = new Territorio("Polonia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(675);
		t.setPosY(208);
		
		t = new Territorio("Franca");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(570);
		t.setPosY(252);
		
		t = new Territorio("Suecia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(628);
		t.setPosY(152);
		
		t = new Territorio("Espanha");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(527);
		t.setPosY(296);
		
		t = new Territorio("Reino Unido");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(544);
		t.setPosY(192);
		
		t = new Territorio("Romania");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(678);
		t.setPosY(278);
		
		t = new Territorio("Ucrania");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(703);
		t.setPosY(244);
		
		t = new Territorio("Italia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(628);
		t.setPosY(248);
		
		//Territórios África
		t = new Territorio("Egito");
		mapTerritorios.put(t.getNome(), t);
		t.AddAdjacente(t);
		cartasTerritorios.add(t);
		t.setPosX(674);
		t.setPosY(406);
		
		t = new Territorio("Argelia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(549);
		t.setPosY(392);
		
		t = new Territorio("Nigeria");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(622);
		t.setPosY(459);
		
		t = new Territorio("Somalia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(731);
		t.setPosY(494);
		
		t = new Territorio("Angola");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(655);
		t.setPosY(532);
		
		t = new Territorio("Africa do Sul");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(679);
		t.setPosY(590);
		
		//Territórios Ásia
		t = new Territorio("Estonia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(784);
		t.setPosY(150);
		
		t = new Territorio("Letonia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(770);
		t.setPosY(199);
		
		t = new Territorio("Russia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(910);
		t.setPosY(164);
		
		t = new Territorio("Siberia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(1046);
		t.setPosY(160);
		
		t = new Territorio("Turquia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(860);
		t.setPosY(255);
		
		t = new Territorio("Cazaquistao");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(982);
		t.setPosY(229);
		
		t = new Territorio("Japao");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(1111);
		t.setPosY(286);
		
		t = new Territorio("Siria");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(776);
		t.setPosY(305);
		
		t = new Territorio("Paquistao");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(879);
		t.setPosY(339);
		
		t = new Territorio("China");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(931);
		t.setPosY(311);
		
		t = new Territorio("Mongolia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(1014);
		t.setPosY(265);
		
		t = new Territorio("Coreia do Norte");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(1012);
		t.setPosY(315);
		
		t = new Territorio("Coreia do Sul");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(1018);
		t.setPosY(344);
		
		t = new Territorio("Jordania");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(732);
		t.setPosY(363);
		
		t = new Territorio("Iraque");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(801);
		t.setPosY(360);
		
		t = new Territorio("Ira");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(850);
		t.setPosY(358);
		
		t = new Territorio("India");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(936);
		t.setPosY(401);
		
		t = new Territorio("Bangladesh");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(997);
		t.setPosY(392);
		
		t = new Territorio("Tailandia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(1055);
		t.setPosY(386);
		
		t = new Territorio("Arabia Saudita");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(796);
		t.setPosY(426);
		
		//Territórios Oceania
		t = new Territorio("Australia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(1034);
		t.setPosY(629);
		
		t = new Territorio("Indonesia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(1053);
		t.setPosY(526);
		
		t = new Territorio("Perth");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(951);
		t.setPosY(616);
		
		t = new Territorio("Nova Zelandia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		cartasTerritorios.add(t);
		t.setPosX(1089);
		t.setPosY(672);
		
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

	private void InstanciaCartas(){
		Cartas c;
		//TODO
	}
	
	// ----------------- Getters & Setters -----------------
	
	// Retorna objeto territorio a partir do nome
	public static Territorio getTerritorio(String nomeTerritorio){
		return mapTerritorios.get(nomeTerritorio);
	}

	//Retorna a instancia do tabuleiro
	public static Tabuleiro getTabuleiro() {
		if (tabuleiro == null){
			tabuleiro = new Tabuleiro();
		}
		return tabuleiro;
	}

	public static HashMap<String, Territorio> getMapTerritorios() {
		return mapTerritorios;
	}
	
	public static HashMap<String, Continente> getMapContinente() {
		return mapContinente;
	}
	
	public ArrayList<Territorio> getCartasTerritorios() {
		return cartasTerritorios;
	}
	
	public void setCartasTerritorios(ArrayList<Territorio> cartasTerritorios) {
		this.cartasTerritorios = cartasTerritorios;
	}
	
	public static void setMapTerritorios(HashMap<String, Territorio> mapTerritorios) {
		Tabuleiro.mapTerritorios = mapTerritorios;
	}

	public static void setMapContinente(HashMap<String, Continente> mapContinente) {
		Tabuleiro.mapContinente = mapContinente;
	}

	public int getNumJogadores() {
		return numJogadores;
	}
	
	public void setNumJogadores(int numJogadores) {
		this.numJogadores = numJogadores;
	}
	
	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}
	
	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	
	public Objetivo getObjetivo() {
		return objetivo;
	}
	
	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}
}
