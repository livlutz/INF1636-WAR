package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.awt.Color;

import View.ObservadoIF;
import View.ObservadorIF;

class Jogo implements ObservadoIF{
    private static Jogo jogo = null;

    // Guarda o tabuleiro
    private Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();

	// Guarda cada jogador
	private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();

    // Guarda lista de objetivos
    private ArrayList<Objetivo> objetivos = new ArrayList<Objetivo>();

    // Guarda lista de cartas
    private ArrayList<Carta> listaCartas = new ArrayList<Carta>();

	// Guarda a lista de observadores
	private ArrayList<ObservadorIF> observadores = new ArrayList<ObservadorIF>();

	// Últimos territórios alterados
	private Territorio mod1 = null;
	private Territorio mod2 = null;

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

	// Método para adicionar observador
    public void add(ObservadorIF o){
        observadores.add(o);
    }

    // Método para remover observador
    public void remove(ObservadorIF o){
        observadores.remove(o);
    }

	
    // Método para passar informações observadores
    public Object get(){
        // Array de informações
        Object infos[] = new Object[4];

        // Array de quantidade de exércitos
        ArrayList<String> qtdExercitos = new ArrayList<String>();

        // Array de cores
        ArrayList<Color> cores = new ArrayList<Color>();

        // Preenche arrays com informações atuais do model
        for (Territorio t: tabuleiro.getlistaTerritorios()){
            qtdExercitos.add(((Integer)t.getQntExercitos()).toString());
            cores.add(t.getCor());
        }
        infos[0] = qtdExercitos;
        infos[1] = cores;

        // Preenche no array qual o índice dos territórios que foram modificados
        if (mod1 == null){
            infos[2] = -1;
        }
        else{
            infos[2] = tabuleiro.getlistaTerritorios().indexOf(mod1);
        }
        if (mod2 == null){
            infos[3] = -1;
        }
        else{
            infos[3] = tabuleiro.getlistaTerritorios().indexOf(mod2);
        }
        return infos;
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
    public boolean InicializaJogo(){
		
    	//Jogo não comeca se tiver menos que 3 jogadores
    	if((jogadores.size())<3) {
    		return false;
    	} 

		//Inicializa o tabuleiro
    	tabuleiro.Inicializa();
    	
        // Instancia cartas
        InstanciaCartas(tabuleiro.getMapTerritorios());

        // Instancia objetivos
        InstanciaObjetivos();

        // Embaralha os objetivos
        Collections.shuffle(objetivos);

        // Define o objetivo de cada jogador
        for (int i = 0;i < jogadores.size();i++){
            jogadores.get(i).setObj(objetivos.get(i));
        }

        // Embaralha os jogadores
        Collections.shuffle(jogadores);

        // Distribui os territorios
        tabuleiro.distribuiTerritorios(jogadores);
        
        return true;

    }

	// Método para reposicionar exércitos
	public void reposicionarExercitos(Territorio origem, Territorio destino, Integer qtd){
		origem.alterarQndExercitos(-qtd);
		destino.alterarQndExercitos(qtd);
		mod1 = origem;
		mod2 = destino;
		this.notificaObs();
	}
    
	//Valida um ataque
	public boolean VerificarAtaque(Territorio tAtacante, Territorio tDefensor) {
		// Verifica se o atacante tem mais de um exército e se o defensor não é dele
		if(tAtacante.getQntExercitos() > 1 && tAtacante.getJogador() != tDefensor.getJogador())
			return true;	
		return false;
	}
	
	//Realiza um ataque -> colocar na API jogo (ou classe jogo)
	public int[] RealizaAtaque(Territorio atacante,Territorio defensor, Integer numAtaque, Integer numDefesa) {
		
		if(VerificarAtaque(atacante, defensor)){
			//Verifica se o atacante tem mais de 3 exércitos
			int qtdAtaque = atacante.getQntExercitos() - 1;
			if  (qtdAtaque > 3) {qtdAtaque = 3;}

			//Verifica se o defensor tem mais de 3 exércitos
			int qtdDefesa = defensor.getQntExercitos();
			if  (qtdDefesa > 3) {qtdDefesa = 3;}

			//Cria os arrays de dados
			int[] dadosAtaque = new int[qtdAtaque];
			int[] dadosDefesa = new int[qtdDefesa];
			//Cria um dado
			Dado dado = new Dado();
			//Variáveis para contar quantos exércitos foram perdidos
			int qtdAtaquePerdidos = 0;
			int qtdDefesaPerdidos = 0;
			
			//Verifica se o jogador escolheu um número forçado
			if (numAtaque != 0){
				for (int i = 0;i < qtdAtaque;i++) {
				dadosAtaque[i] = numAtaque;
			}
			}
			else{
				for (int i = 0;i < qtdAtaque;i++) {
					dadosAtaque[i] = dado.rodarDado();
				}
				//Ordena os dados se for aleatório
				Arrays.sort(dadosAtaque);
			}
			if (numDefesa != 0){
				for (int i = 0;i < qtdDefesa;i++) {
				dadosDefesa[i] = numDefesa;
				}
			}
			else{
				for (int i = 0;i < qtdDefesa;i++) {
					dadosDefesa[i] = dado.rodarDado();
				}
				//Ordena os dados se for aleatório
				Arrays.sort(dadosDefesa);
			}
			
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

			//Atualiza os territórios modificados
			mod1 = atacante;
			mod2 = defensor;
			// Se conquistou
			if (defensor.getQntExercitos()==0) {
				// Atualiza defensor
				defensor.getJogador().removeTerritorio(defensor);
				defensor.setJogador(atacante.getJogador());

				// Adiciona território conquistado ao jogador que conquistou
				atacante.getJogador().addTerritorio(defensor);

				// Calcula quantos exércitos o jogador pode colocar no território conquistado (sempre máximo possível)
				//int qtdPassada = atacante.getQntExercitos() - 1;
				//if (qtdPassada > 3) {qtdPassada = 3;}
				int qtdPassada = 1;
				// Altera a quantidade de exércitos dos territórios
				atacante.alterarQndExercitos(-qtdPassada);
				defensor.setQntExercitos(qtdPassada);
			}
			//Notifica os observadores
			this.notificaObs();
			
			// Retorna os dados em um array único 

			int[] dados = new int[6];
			int i;
			for (i = 0;i < dadosAtaque.length;i++) {
				dados[i] = dadosAtaque[i];
			}
			// Completa com zeros caso o atacante tenha menos de 3 dados
			if (i < 3) {
				for (;i < 3;i++) {
					dados[i] = 0;
				}
			}
			for (int j = 0;j < dadosDefesa.length;j++) {
				dados[i] = dadosDefesa[j];
				i++;
			}
			// Completa com zeros caso o defensor tenha menos de 3 dados
			if (i < 6) {
				for (;i < 6;i++) {
					dados[i] = 0;
				}
			}

			return dados;
		}
	
		System.out.println("Nao foi possivel realizar o ataque");
		return new int [] {0,0,0,0,0,0};
	}
	
	//Instancia os objetivos de cada jogador
    public void InstanciaObjetivos(){
        Objetivo obj;
        // Instancia objetivos de destruir jogador
        for(Jogador j : jogadores){
        obj = new ObjetivoDestruir(j);
        objetivos.add(obj);
        }

        // Instancia objetivos de conquistar continentes    
        obj = new ObjetivoContinentes(tabuleiro.getContinente("America do Norte"),tabuleiro.getContinente("Africa"),false);
        objetivos.add(obj);

        obj = new ObjetivoContinentes(tabuleiro.getContinente("Asia"),tabuleiro.getContinente("Africa"),false);
        objetivos.add(obj);

        obj = new ObjetivoContinentes(tabuleiro.getContinente("America do Norte"),tabuleiro.getContinente("Oceania"),false);
        objetivos.add(obj);

        obj = new ObjetivoContinentes(tabuleiro.getContinente("Europa"),tabuleiro.getContinente("America do Sul"),true);
        objetivos.add(obj);

        obj = new ObjetivoContinentes(tabuleiro.getContinente("Asia"),tabuleiro.getContinente("America do Sul"),true);
        objetivos.add(obj);

        obj = new ObjetivoContinentes(tabuleiro.getContinente("Europa"),tabuleiro.getContinente("Oceania"),true);
        objetivos.add(obj);

        // Instancia objetivos de conquistar territorios
        obj = new ObjetivoTerritorios(24);
        objetivos.add(obj);

        obj = new ObjetivoTerritorios(18);
        objetivos.add(obj);
    
    }

	// Instancia as cartas
	void InstanciaCartas(HashMap<String,Territorio> mapTerritorios){
		Carta c;
		//Cartas de Território

		//Africa
		c = new Carta(2, mapTerritorios.get("Africa do Sul"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Angola"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Argelia"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Egito"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Nigeria"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Somalia"));
		listaCartas.add(c);

		//América do Norte
		c = new Carta(2, mapTerritorios.get("Alasca"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("California"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Calgary"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Groelandia"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Mexico"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Nova York"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Quebec"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Texas"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Vancouver"));
		listaCartas.add(c);

		//América do Sul
		c = new Carta(0, mapTerritorios.get("Argentina"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Brasil"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Peru"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Venezuela"));
		listaCartas.add(c);

		//Ásia
		c = new Carta(1, mapTerritorios.get("Arabia Saudita"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Bangladesh"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Cazaquistao"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("China"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Coreia do Norte"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Coreia do Sul"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Estonia"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("India"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Ira"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Iraque"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Japao"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Jordania"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Letonia"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Mongolia"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Paquistao"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Russia"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Siria"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Siberia"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Tailandia"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Turquia"));
		listaCartas.add(c);

		//Europa
		c = new Carta(1, mapTerritorios.get("Espanha"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Franca"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Italia"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Polonia"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Reino Unido"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Romenia"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Suecia"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Ucrania"));
		listaCartas.add(c);

		//Oceania
		c = new Carta(2, mapTerritorios.get("Australia"));
		listaCartas.add(c);
		c = new Carta(2, mapTerritorios.get("Indonesia"));
		listaCartas.add(c);
		c = new Carta(0, mapTerritorios.get("Nova Zelandia"));
		listaCartas.add(c);
		c = new Carta(1, mapTerritorios.get("Perth"));
		listaCartas.add(c);

		//Cartas Coringa
		c = new Carta(3, null);
		listaCartas.add(c);
		c = new Carta(3, null);
		listaCartas.add(c);

	}
	
	//Dá uma carta a um jogador
	//Só é chamado quando o jogador conquista um território na rodada
	public void DaCartas(Jogador j){

		//Da uma carta aleatoria ao jogador
		Collections.shuffle(listaCartas);
		Carta carta = listaCartas.get(0);
		j.addCarta(carta);

		//Remove a carta da lista de cartas
		listaCartas.remove(0);

	}

	public void notificaObs(){
		for (ObservadorIF o: observadores){
			o.notifica(this);
		}
	}
	
	public Jogador getJogador(String jogadorNome) {
		for(Jogador jogador:jogadores) {
			if (jogador.getNome().equals(jogadorNome)) {
				return jogador;
			}
		}
		return null;
	}

	public void reiniciarJogo(){

		for (Jogador j: jogadores){
			// Devolve objetivo do jogador para lista
			objetivos.add(j.getObj());
			// Devolve cartas do jogador para lista
			for (Carta c: j.getCartas()){
				listaCartas.add(c);
			}
			j.resetJogador();
		}
		
		// Embaralha os jogadores
        Collections.shuffle(jogadores);

		// Redistribui territórios
		tabuleiro.distribuiTerritorios(jogadores);

		// Redistribui objetivos
		Collections.shuffle(objetivos);
		for (int i = 0;i < jogadores.size();i++){
			jogadores.get(i).setObj(objetivos.get(i));
		}

	}

	// Método para pegar o jogador da vez
	public Jogador getJogadorVez(int i){
		return jogadores.get(i);
	}
	//Retorna os jogadores
	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}

	public void setMod1(Territorio t){
		mod1 = t;
	}

	public void setMod2(Territorio t){
		mod2 = t;
	}

	public Carta getCartaNome(String nome){
		Territorio t;
		if(nome == null){
			t = null;
		}

		else{
			t = tabuleiro.getTerritorio(nome);
		}

		for(Carta c : listaCartas){
				if(c.getTerritorio() == t){
					return c;
				}
		}

		return null;
	}

	public ArrayList<Carta> getListaCartas(){
		return listaCartas;
	}

}
