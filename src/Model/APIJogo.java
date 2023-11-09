package Model;

import java.awt.Color;
import java.util.Map;

public class APIJogo {
    private static APIJogo APIJogo = null;
    private Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();;

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
		String[] arrayTerritorios = new String[51];
		int cont = 0;
		for (Territorio t: Tabuleiro.mapTerritorios.values()) {
            arrayTerritorios[cont] = t.getNome();
            cont++;
		}

		return arrayTerritorios;
	}

    // Retorna cor do jogador que domina aquele território
    public Color getCorTerritorio(String t){
    	return Tabuleiro.mapTerritorios.get(t).getCor();
    }

    // Retorna quantidade de exércitos no território em formato de string
    public String getQtdExTerritorio(String t){
        return String.valueOf(Tabuleiro.mapTerritorios.get(t).getQntExercitos());
    }

    

}
