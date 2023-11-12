package Controller;
import View.*;
import Model.*;

import java.util.ArrayList;

import static org.junit.Assert.fail;

import java.awt.Color;

public class Gerente {
    public static Gerente gerente = null;
    private static APIJogo apiJogo = APIJogo.getAPIJogo();

    // Construtor privado para o singleton
    private Gerente(){
    }

    // Singleton
    public static Gerente getGerente(){
        if(gerente == null){
            gerente = new Gerente();
        }
        return gerente;
    }

    public boolean comecaJogo(ArrayList<String> nomes, ArrayList<Color> cores){
        int cont = 0;
        for (String s: nomes){
            // Verifica se algum nome é nulo
            if (s == null){
                apiJogo.resetJogadores();
                return false;
            }
            // Verifica se algum nome está vazio
            if (s.equals("")){
                apiJogo.resetJogadores();
                return false;
            }
            // Adiciona jogadores, verificando se há repetições de nomes ou cores
            if (apiJogo.addJogador(s, cores.get(cont)) == false){
                apiJogo.resetJogadores();
                return false;
            };
            cont++;
        }
        return apiJogo.comecaJogo();
    }

    public Color getCorTerritorio(String t){
        return apiJogo.getCorTerritorio(t);
    }

    public String[] getTerritoriosLista(){
        return apiJogo.getTerritoriosLista();
    }

    public String[] getNomesJogadores(){
        return apiJogo.getNomesJogadores();
    }
}
