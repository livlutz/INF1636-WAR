package Controller;
import View.*;
import Model.*;

import java.util.ArrayList;

import static org.junit.Assert.fail;

import java.awt.Color;

public class Gerente {
    public static Gerente gerente = null;
    // Instâncias de APIJogo e APIView
    private APIJogo apiJogo = APIJogo.getAPIJogo();
    private APIView apiView = APIView.getAPIView();

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

    // Método para iniciar o jogo
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

    // Método para gerenciar jogo
    public void mainJogo(){
        while(true){
            
        }
    }

    // Método que retorna a cor de um território
    public Color getCorTerritorio(String t){
        return apiJogo.getCorTerritorio(t);
    }

    // Método que retorna a lista de territórios do jogo
    public String[] getTerritoriosLista(){
        return apiJogo.getTerritoriosLista();
    }

    // Método que retorna a lista de nomes dos jogadores
    public String[] getNomesJogadores(){
        return apiJogo.getNomesJogadores();
    }
}
