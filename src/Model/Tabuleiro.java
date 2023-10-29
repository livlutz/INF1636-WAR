package Model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Tabuleiro{

	//Mapa do tabuleiro
	public static HashMap<String,Territorio> mapTerritorios = new HashMap<String,Territorio>();
	private static HashMap<String,Continente> mapContinente = new HashMap<String,Continente>();
	private static Tabuleiro tabuleiro = null;
	private ArrayList<Territorio> listaTerritorios = new ArrayList<Territorio>();
	private ArrayList<Cartas> listaCartas = new ArrayList<Cartas>();
	

	//Construtor
	private Tabuleiro() {
		InstanciaTerritorios();
		InstanciaContinente();
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
			int qtdAtaque = atacante.getQntExercitos() - 1;
			int qtdDefesa = defensor.getQntExercitos();
			int[] dadosAtaque = new int[qtdAtaque];
			int[] dadosDefesa = new int[qtdDefesa];
			Dado dado = new Dado();
			int qtdAtaquePerdidos = 0;
			int qtdDefesaPerdidos = 0;
			
			for (int i = 0;i < qtdAtaque;i++) {
				dadosAtaque[i] = dado.rodarDado();
			}
			
			for (int i = 0;i < qtdDefesa;i++) {
				dadosDefesa[i] = dado.rodarDado();
			}
			
			//Ordena os dados
			Arrays.sort(dadosAtaque);
			Arrays.sort(dadosDefesa);
			
			//Compara os dados
			for (int i = 0;i < Math.min(qtdAtaque, qtdDefesa);i++) {
				if (dadosAtaque[i] > dadosDefesa[i]) {
					qtdDefesaPerdidos++;
				}
				else {
					qtdAtaquePerdidos++;
				}
			}
			
			//Atualiza os exércitos
			atacante.setQntExercitos(atacante.getQntExercitos() - qtdAtaquePerdidos);
			defensor.setQntExercitos(defensor.getQntExercitos() - qtdDefesaPerdidos);

		}
		
		System.out.println("Nao foi possivel realizar o ataque");
		return;
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
	void distribuiTerritorios(ArrayList<Jogador> jogadores){
		Collections.shuffle(listaTerritorios);
		int qtdJogadores = jogadores.size();
		int qtdTerritorios = listaTerritorios.size();

		for (int i = 0; i < qtdTerritorios; i++) {
			Territorio t = listaTerritorios.get(i);
			Jogador j = jogadores.get(i % qtdJogadores);
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
		listaTerritorios.add(t);
		t.setPosX(355);
		t.setPosY(471);
		listaTerritorios.add(t);

		t = new Territorio("Argentina");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(319);
		t.setPosY(580);
		listaTerritorios.add(t);

		t = new Territorio("Peru");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(275);
		t.setPosY(510);
		listaTerritorios.add(t);

		t = new Territorio("Venezuela");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(232);
		t.setPosY(452);
		
		listaTerritorios.add(t);

		//Territórios América do Norte
		t = new Territorio("Nova York");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(251);
		t.setPosY(281);
		
		t = new Territorio("Mexico");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(166);
		t.setPosY(361);
		listaTerritorios.add(t);

		t = new Territorio("Mexico");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);

		t = new Territorio("California");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(135);
		t.setPosY(276);
		
		t = new Territorio("Groelandia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(383);
		t.setPosY(118);

		t = new Territorio("Alasca");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(114);
		t.setPosY(148);
		
		t = new Territorio("Vancouver");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(194);
		t.setPosY(201);
		
		t = new Territorio("Calgary");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(216);
		t.setPosY(155);

		t = new Territorio("Quebec");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(325);
		t.setPosY(195);
		
		t = new Territorio("Texas");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(189);
		t.setPosY(282);
		
		//Territórios Europa
		t = new Territorio("Polonia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(675);
		t.setPosY(208);
		
		t = new Territorio("Franca");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(570);
		t.setPosY(252);
		
		t = new Territorio("Suecia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(628);
		t.setPosY(152);
		
		t = new Territorio("Espanha");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(527);
		t.setPosY(296);
		
		t = new Territorio("Reino Unido");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(544);
		t.setPosY(192);
		
		t = new Territorio("Romania");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(678);
		t.setPosY(278);
		
		t = new Territorio("Ucrania");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(703);
		t.setPosY(244);
		
		t = new Territorio("Italia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(628);
		t.setPosY(248);
		
		//Territórios África
		t = new Territorio("Egito");
		mapTerritorios.put(t.getNome(), t);
		t.AddAdjacente(t);
		listaTerritorios.add(t);
		t.setPosX(674);
		t.setPosY(406);
		
		t = new Territorio("Argelia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(549);
		t.setPosY(392);
		
		t = new Territorio("Nigeria");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(622);
		t.setPosY(459);
		
		t = new Territorio("Somalia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(731);
		t.setPosY(494);
		
		t = new Territorio("Angola");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(655);
		t.setPosY(532);
		
		t = new Territorio("Africa do Sul");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(679);
		t.setPosY(590);
		
		//Territórios Ásia
		t = new Territorio("Estonia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(784);
		t.setPosY(150);
		
		t = new Territorio("Letonia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(770);
		t.setPosY(199);
		
		t = new Territorio("Russia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(910);
		t.setPosY(164);
		
		t = new Territorio("Siberia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(1046);
		t.setPosY(160);
		
		t = new Territorio("Turquia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(860);
		t.setPosY(255);
		
		t = new Territorio("Cazaquistao");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(982);
		t.setPosY(229);
		
		t = new Territorio("Japao");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(1111);
		t.setPosY(286);
		
		t = new Territorio("Siria");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(776);
		t.setPosY(305);
		
		t = new Territorio("Paquistao");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(879);
		t.setPosY(339);
		
		t = new Territorio("China");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(931);
		t.setPosY(311);
		
		t = new Territorio("Mongolia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(1014);
		t.setPosY(265);
		
		t = new Territorio("Coreia do Norte");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(1012);
		t.setPosY(315);
		
		t = new Territorio("Coreia do Sul");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(1018);
		t.setPosY(344);
		
		t = new Territorio("Jordania");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(732);
		t.setPosY(363);
		
		t = new Territorio("Iraque");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(801);
		t.setPosY(360);
		
		t = new Territorio("Ira");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(850);
		t.setPosY(358);
		
		t = new Territorio("India");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(936);
		t.setPosY(401);
		
		t = new Territorio("Bangladesh");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(997);
		t.setPosY(392);
		
		t = new Territorio("Tailandia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(1055);
		t.setPosY(386);
		
		t = new Territorio("Arabia Saudita");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(796);
		t.setPosY(426);
		
		//Territórios Oceania
		t = new Territorio("Australia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(1034);
		t.setPosY(629);
		
		t = new Territorio("Indonesia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(1053);
		t.setPosY(526);
		
		t = new Territorio("Perth");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
		t.setPosX(951);
		t.setPosY(616);
		
		t = new Territorio("Nova Zelandia");
		mapTerritorios.put(t.getNome(), t);
		t.instanciaAdjacentes(t);
		listaTerritorios.add(t);
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
	
	//Inicializa as cartas
	private void InstanciaCartas(){
		Cartas c;
		//Cartas de Território
		//Africa
		c = new Cartas(2, mapTerritorios.get("Africa do Sul"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Angola"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Argelia"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Egito"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Nigeria"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Somalia"));
		listaCartas.add(c);

		//America do Norte
		c = new Cartas(2, mapTerritorios.get("Alasca"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("California"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Calgary"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Groelandia"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Mexico"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Nova York"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Quebec"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Texas"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Vancouver"));
		listaCartas.add(c);

		//America do Sul
		c = new Cartas(0, mapTerritorios.get("Argentina"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Brasil"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Peru"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Venezuela"));
		listaCartas.add(c);

		//Asia
		c = new Cartas(1, mapTerritorios.get("Arabia Saudita"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Bangladesh"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Cazaquistao"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("China"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Coreia do Norte"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Coreia do Sul"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Estonia"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("India"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Ira"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Iraque"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Japao"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Jordania"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Letonia"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Mongolia"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Paquistao"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Russia"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Siria"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Siberia"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Tailandia"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Turquia"));
		listaCartas.add(c);

		//Europa
		c = new Cartas(1, mapTerritorios.get("Espanha"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Franca"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Italia"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Polonia"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Reino Unido"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Romenia"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Suecia"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Ucrania"));
		listaCartas.add(c);

		//Oceania
		c = new Cartas(2, mapTerritorios.get("Australia"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Indonesia"));
		listaCartas.add(c);
		c = new Cartas(0, mapTerritorios.get("Nova Zelandia"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Perth"));
		listaCartas.add(c);
	}
	
	//Da uma carta a um jogador caso ele conquiste territorios
	public void DaCartas(Jogador j, int qtdTerritoriosConquistados){
		//condicao para dar cartas ao jogador -> conquistar territorios
		//pegar todos os territorios e cartas q o jogador tem
		ArrayList <Territorio> t = j.getTerritorios();
		ArrayList <Cartas> c = j.getCartas();

		//se ele conquistou territorios, precisamos dar cartas a ele
		if(j.alterarQtdTerritorios(qtdTerritoriosConquistados)){
			//percorre a lista de territorios do jogador
			for(Territorio t1 : t){
				//percorre a lista de cartas do deck do jogo
				for(Cartas c1 : listaCartas){
					/*se o nome do territorio for igual ao nome da carta
					 * e o jogador nao tem a carta daquele territorio, 
					 * teremos que dar a carta a ele
					*/
					if(t1.getNome().equals(c1.getTerritorio().getNome())){
						if(!c.contains(c1)){
							j.addCarta(c1);
						}
					}
				}
			}
		}
		//se o jogador nao conquistou territorios, nao damos cartas a ele e mostramos uma mensagemq
		else{
			System.out.printf("Jogador nao conquistou territorios\n");
			return;
		}
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
