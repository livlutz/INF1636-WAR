package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

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

    // Guarda lista de cartas
    private ArrayList<Cartas> listaCartas = new ArrayList<Cartas>();

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

    // Adiciona jogador na partida
    public boolean addJogador(Jogador jogador){
        for (Jogador j: jogadores){
            if (j.getNome().equals(jogador.getNome()) || j.getCor() == jogador.getCor())
                return false;
        }
        jogadores.add(jogador);
        return true;
    }

    // Inicializa o jogo
    public void InicializaJogo(){
        // Instancia cartas
        InstanciaCartas(Tabuleiro.getMapTerritorios());

        // Instancia objetivos
        InstanciaObjetivos();

        // Embaralha os objetivos
        Collections.shuffle(objetivos);

        // Define o objetivo de cada jogador
        for (int i = 0;i < numJogadores;i++){
            jogadores.get(i).setObj(objetivos.get(i));
        }

        // Embaralha os jogadores
        Collections.shuffle(jogadores);

        // Distribui os territorios
        tabuleiro.distribuiTerritorios(jogadores);

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
	public void RealizaAtaque(Jogador jAtacante, Territorio atacante,Territorio defensor) {
		
		if(VerificarAtaque(jAtacante, atacante, defensor)){
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

	
	// Instancia as cartas
	private void InstanciaCartas(HashMap<String,Territorio> mapTerritorios){
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
		if(j.getConquistouNessaRodada()){
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

}
