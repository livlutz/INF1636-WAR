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
    private ArrayList<Cartas> listaCartas = new ArrayList<Cartas>();

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
    
	//Valida um ataque
	public boolean VerificarAtaque(Territorio tAtacante) {
		if(tAtacante.getQntExercitos() > 1)
			return true;	
		return false;
	}
	
	//Realiza um ataque -> colocar na API jogo (ou classe jogo)
	public void RealizaAtaque(Jogador jAtacante, Territorio atacante,Territorio defensor, int[]dadosAtaque, int[]dadosDefesa) {
		
		if(VerificarAtaque(atacante)){
			int qtdAtaque = atacante.getQntExercitos() - 1;
			if  (qtdAtaque > 3) {qtdAtaque = 3;}
			int qtdDefesa = defensor.getQntExercitos();
			if  (qtdDefesa > 3) {qtdDefesa = 3;}
			dadosAtaque = new int[qtdAtaque];
			dadosDefesa = new int[qtdDefesa];
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

			//Atualiza os territórios modificados
			mod1 = atacante;
			mod2 = defensor;

			//Notifica os observadores
			for (ObservadorIF o: observadores){
				o.notifica(this);
			}
			
			return;
		}
	
		System.out.println("Nao foi possivel realizar o ataque");
		return;
	}
		public void RealizaAtaqueForcado(Jogador jatacante, Territorio tatacante, Territorio tdefensor, int dadoAtaque,int dadoDefesa) {
			if(VerificarAtaque(tatacante)){
				int qtdAtaque = tatacante.getQntExercitos() - 1;
				if  (qtdAtaque > 3) {qtdAtaque = 3;}
				int qtdDefesa = tdefensor.getQntExercitos();
				if  (qtdDefesa > 3) {qtdDefesa = 3;}
				int dadosAtaque[] = new int[3];
				int dadosDefesa [] = new int[3];
				int qtdAtaquePerdidos = 0;
				int qtdDefesaPerdidos = 0;
				
				for (int i = 0;i < qtdAtaque;i++) {
					dadosAtaque[i] = dadoAtaque;
				}
				
				for (int i = 0;i < qtdDefesa;i++) {
					dadosDefesa[i] = dadoDefesa;
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
				tatacante.setQntExercitos(tatacante.getQntExercitos() - qtdAtaquePerdidos);
				tdefensor.setQntExercitos(tdefensor.getQntExercitos() - qtdDefesaPerdidos);
				
				
				//Atualiza os territórios modificados
				mod1 = tatacante;
				mod2 = tdefensor;

				//Notifica os observadores
				for (ObservadorIF o: observadores){
					o.notifica(this);
				}

				return;
			}

			System.out.println("Nao foi possivel realizar o ataque");
			return;
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

		//América do Norte
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

		//América do Sul
		c = new Cartas(0, mapTerritorios.get("Argentina"));
		listaCartas.add(c);
		c = new Cartas(1, mapTerritorios.get("Brasil"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Peru"));
		listaCartas.add(c);
		c = new Cartas(2, mapTerritorios.get("Venezuela"));
		listaCartas.add(c);

		//Ásia
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
	
	//Dá uma carta a um jogador caso ele conquiste territórios
	public void DaCartas(Jogador j){
		//Condição para dar cartas ao jogador -> conquistar territórios
		//Pegar todos os territórios e cartas que o jogador tem

		ArrayList <Territorio> t = j.getTerritorios();
		ArrayList <Cartas> c = j.getCartas();

		//Se ele conquistou territórios, precisamos dar cartas a ele
		if(j.getConquistouNessaRodada()){
			//Percorre a lista de territórios do jogador
			for(Territorio t1 : t){
				//Percorre a lista de cartas do deck do jogo
				for(Cartas c1 : listaCartas){
					/*Se o nome do território for igual ao nome da carta
					 * e o jogador não tem a carta daquele território, 
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
		//Se o jogador não conquistou territórios, não damos cartas a ele e mostramos uma mensagem
		else{
			System.out.printf("Jogador nao conquistou territorios\n");
			return;
		}
	}
	
	public Jogador getJogador(String jogadorNome) {
		for(Jogador jogador:jogadores) {
			if (jogador.getNome() == jogadorNome) {
				return jogador;
			}
		}
		return null;
	}

	// Método para pegar o jogador da vez
	public Jogador getJogadorVez(int i){
		return jogadores.get(i);
	}
	//Retorna os jogadores
	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}

}
