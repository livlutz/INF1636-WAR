package Model;

import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import View.APIView;
import View.ObservadoIF;
import View.ObservadorIF;


 public class APIJogo{
    private static APIJogo APIJogo = null;
    private Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
    private Jogo jogo = Jogo.getJogo();
    private APIView apiView = APIView.getAPIView();

    // Construtor privado para o singleton
    private APIJogo(){
    }

    // Singleton
    public static APIJogo getAPIJogo(){
        if(APIJogo == null){
            APIJogo = new APIJogo();
        }
        return APIJogo;
    }


    //Método de inicializar jogo
    public boolean comecaJogo() {
    	boolean r = jogo.InicializaJogo();
        // Associando observador GamePanel ao observado Jogo
        jogo.add(apiView.getObs());
    	return r;
    }

    // Retorna lista de nomes de territórios
    public String[] getTerritoriosLista() {
		String [] terr = new String[51]; 
		int cont = 0;
		for (String t: Tabuleiro.mapTerritorios.keySet()) {
            terr[cont] = t;
            cont ++;
		}

		return terr;
	}
    
    //Método de realizar ataque
    public void realizaAtaque(Jogador Jatacante,String atacante, String defensor, int[]dadosAtaque, int[]dadosDefesa) {
    	Territorio Tatacante = Tabuleiro.mapTerritorios.get(atacante);
    	Territorio Tdefensor = Tabuleiro.mapTerritorios.get(defensor);
    	jogo.RealizaAtaque(Jatacante, Tatacante, Tdefensor,dadosAtaque, dadosDefesa);
    	
    }
    
    //Método de realizar ataque forcado
    public void realizaAtaqueForcado(Jogador Jatacante,String atacante, String defensor, int dadoAtaque, int dadoDefesa) {
    	Territorio Tatacante = Tabuleiro.mapTerritorios.get(atacante);
    	Territorio Tdefensor = Tabuleiro.mapTerritorios.get(defensor);
    	jogo.RealizaAtaqueForcado(Jatacante, Tatacante, Tdefensor, dadoAtaque, dadoDefesa);
    }

    // Retorna cor do jogador que domina aquele território
    public Color getCorTerritorio(String t){
    	return Tabuleiro.mapTerritorios.get(t).getCor();
    }

    // Retorna quantidade de exércitos no território em formato de string
    public String getQtdExTerritorio(String t){
        return String.valueOf(Tabuleiro.mapTerritorios.get(t).getQntExercitos());
    }

    // Tenta adicionar jogador
    public boolean addJogador(String nome, Color cor) {
        Jogador j = new Jogador(nome, cor);
    	return jogo.addJogador(j);
    }

    // Método de remover os jogadores
    public void resetJogadores(){
        jogo.getJogadores().clear();
    }
    
    // Método que retorna a lista de nomes dos jogadores
    public String[] getNomesJogadores() {
    	String[] nomes = new String[jogo.getJogadores().size()];
    	int cont = 0;
    	for (Jogador j: jogo.getJogadores()) {
    		nomes[cont] = j.getNome();
    		cont++;
    	}
    	return nomes;
    }

    // Método que retorna a lista de nomes dos territórios de um jogador
    public String[] getTerritoriosJogador(int i) {
        ArrayList<Territorio> ter = jogo.getJogadores().get(i).getTerritorios();
    	String[] listaTerritorios = new String[ter.size()];
    	int cont = 0;
    	for (Territorio t: ter) {
    		listaTerritorios[cont] = t.getNome();
    		cont++;
    	}
    	return listaTerritorios;
    }

    // Método que retorna adjacentes de um território por string
    public String[] getTerritoriosAdjacentes(String t) {
        ArrayList<Territorio> adjacentes = Tabuleiro.mapTerritorios.get(t).getAdjacentes();
    	String[] listaTerritorios = new String[adjacentes.size()];
    	int cont = 0;
    	for (Territorio ter: adjacentes) {
    		listaTerritorios[cont] = ter.getNome();
    		cont++;
    	}
    	return listaTerritorios;
    }

    // Método que salva jogo em arquivo
    public void salvarJogo(){
        //TODO salvar jogo
    }
}
