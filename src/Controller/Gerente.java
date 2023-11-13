package Controller;
import View.*;
import Model.*;

import java.util.ArrayList;

import static org.junit.Assert.fail;

import java.awt.Color;

public class Gerente {
    public static Gerente gerente = null;
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
        if (apiJogo.comecaJogo()){
            // Entrega lista com nomes dos jogadores para a view
            apiView.setNomesJogadores(apiJogo.getNomesJogadores());
            // Posicionamento
            apiView.getInfos()[0] = 0;
            // Primeiro jogador
            apiView.getInfos()[1] = 0;
            // Não está realizando posicionamento ainda
            apiView.getInfos()[2] = 0;

            // Atualiza lista de territórios para a lista de territórios do primeiro jogador
            apiView.setListaTerritorios(apiJogo.getTerritoriosJogador(0));

            apiView.comecaJogo();
            return true;
        }
        return false;
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
