package Controller;
import View.*;
import Model.*;

import java.util.ArrayList;

import java.awt.Color;

public class Gerente {
    public static Gerente gerente = null;
    // Instâncias de APIJogo e APIView
    private APIJogo apiJogo = APIJogo.getAPIJogo();
    private APIView apiView = APIView.getAPIView();

    // Estado do jogo
    private int estado = 0; // 0 = posicionamento, 1 = ataque, 2 = reposicionamento

    // Pode salvar? (Somente com estado 0 e sem ter posicionado exércitos)
    private boolean podeSalvar = true;

    // Guarda o jogador da vez
	private int vez = 0;

    // Guarda identificador de continentes 
    private int continente = 1;

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
        if (apiJogo.comecaJogo()){
            apiView.determinaPrimeiroJogador(apiJogo.getNomeJogadorVez(0), apiJogo.getCorJogadorVez(0), apiJogo.getDescObjJogadorVez(0));
            return true;
        }
        return false;
    }

    public void clicouSalvar(){
        if (estado == 0 && podeSalvar){
            apiJogo.salvarJogo();
            apiView.mostraAviso("Jogo salvo com sucesso!");
        }
        else{
            apiView.mostraAviso("Não é possível salvar o jogo agora.");
        }
    }

    public void clicouCarregar(){
        if (!apiJogo.carregarJogo()){
            apiView.mostraAviso("Não foi encontrado nenhum jogo salvo.");
        }
        else{
            apiView.mostraAviso("Jogo carregado com sucesso!");
        }
    }

    public void clicouPosicionar(String territorio, Integer qtd){
        if (estado == 0){
            apiJogo.posicionarExercitos(territorio, qtd, vez);
            Integer qtdEx = apiJogo.getJogadorExPosic(vez);
            if (qtdEx == 0){
                
            }
            else{
                apiView.atualizaQtdPosic(qtdEx);
            }
            return;
        }
        return;
    }

    public void clicouTerminarRodada(){
        // Se estiver na etapa de posicionamento
        if (estado == 0){
            if (true){
                // Atualiza a view para ataque
                apiView.atualizaAtacantes(apiJogo.getTerritoriosJogador(vez));
                estado = 1;
            }
            else{
                apiView.mostraAviso("Não é possível terminar a rodada agora.");
            }
            return;
        }

        // Se estiver na etapa de ataque
        if(estado == 1){
            String[] territorios = apiJogo.getTerritoriosReposicionamento(vez);
            // Se tiver algum território com mais de 1 exército para reposicionar
            if (territorios != null){
                apiView.atualizaReposicionamento(territorios);
                estado = 2;
                return;
            }   
        }
        // Se não retornou ainda, ou está na etapa de reposicionamento 
        // ou não tem mais exércitos para reposicionar
        estado = 0;
        vez = (vez + 1) % apiJogo.getQtdJogadores();
        //apiView.mudaJogador(apiJogo.getNomeJogadorVez(vez), apiJogo.getCorJogadorVez(vez), apiJogo.getDescObjJogadorVez(vez), apiJogo.getCartasJogador(vez));
        apiView.atualizaPosicionamento(apiJogo.getTerritoriosJogador(vez));

    }

    public void selecionouAtacante(String atacante){
        // Se estiver na etapa de ataque
        if (estado == 1){
            if(atacante != null){
            // Atualiza comboBox dos defensores com os adjacentes 

            apiView.atualizaDefensores(apiJogo.getTerritoriosDefensores(atacante, vez));
            }
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

    //get vez
    public int getVez(){
        return vez;
    }

    //set vez
    public void setVez(int vez){
        this.vez = vez;
    }
}
