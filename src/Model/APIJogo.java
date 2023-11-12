package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;


 public class APIJogo {
    private static APIJogo APIJogo = null;
    private Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
    private Jogo jogo = Jogo.getJogo();

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
    	Jogo j = Jogo.getJogo();
    	boolean r = j.InicializaJogo();
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

    public void resetJogadores(){
        jogo.getJogadores().clear();
    }
    
    public String[] getNomesJogadores() {
    	String[] nomes = new String[jogo.getJogadores().size()];
    	int cont = 0;
    	for (Jogador j: jogo.getJogadores()) {
    		nomes[cont] = j.getNome();
    		cont++;
    	}
    	return nomes;
    }
}
