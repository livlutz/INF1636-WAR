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
    private int continente;

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
            String[] territorios;
            Integer qtd;
            for (continente = 0; continente < 6; continente++){
                if (apiJogo.dominaCont(0, apiJogo.getContinentesLista()[continente])){
                    territorios = apiJogo.getTerritoriosCont(apiJogo.getContinentesLista()[continente]);
                    qtd = apiJogo.getExCont(0, apiJogo.getContinentesLista()[continente]);
                    apiView.determinaPrimeiroJogador(apiJogo.getNomeJogadorVez(0), apiJogo.getCorJogadorVez(0), apiJogo.getDescObjJogadorVez(0), territorios, qtd);
                    return true;
                }
            }
            // Atualiza identificador de continentes, para indicar que já testou todos
            continente++;
            territorios = apiJogo.getTerritoriosJogador(0);
            apiJogo.atualizaQtdExPosicGeral(0);
            qtd = apiJogo.getJogadorExPosic(0);

            apiView.determinaPrimeiroJogador(apiJogo.getNomeJogadorVez(0), apiJogo.getCorJogadorVez(0), apiJogo.getDescObjJogadorVez(0), territorios, qtd);
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
            // Abre game panel
            MainFrame.getMainFrame().goToGamePanel();
            // Notifica obs de jogo para redesenhar exércitos
            apiJogo.notificaObsJogo();
            //TODO
        }
    }

    public void clicouTerminarRodada(){
        // Se estiver na etapa de posicionamento
        if (estado == 0){
            if (apiJogo.getJogadorExPosic(vez) == 0){
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

    public void clicouPosicionar(String territorio, Integer qtd){
        // Verifica se está na rodada de posicionamento para agir
        if (estado == 0){

            // Posiciona os exércitos e atualiza a quantidade a posicionar do jogador
            apiJogo.posicionarExercitos(territorio, qtd, vez);
            Integer qtdEx = apiJogo.getJogadorExPosic(vez);

            // Se não tiver mais exércitos para posicionar
            if (qtdEx == 0){
                
                // Se ainda não testou todos os continentes
                if (continente < 6){
                    // Atualiza a view para posicionar no próximo continente se for dominado
                    for (; continente < 6; continente++){
                        if (apiJogo.dominaCont(vez, apiJogo.getContinentesLista()[continente])){
                            String[] territorios = apiJogo.getTerritoriosCont(apiJogo.getContinentesLista()[continente]);
                            Integer qtdExCont = apiJogo.getExCont(vez, apiJogo.getContinentesLista()[continente]);
                            apiView.atualizaPosicionamento(territorios);
                            apiView.atualizaQtdPosic(qtdExCont);
                            return;
                        }
                    }
                }

                // Se já posicionou em todos os continentes, posiciona no resto dos territórios
                if (continente == 6){
                    apiJogo.atualizaQtdExPosicGeral(vez);
                    apiView.atualizaPosicionamento(apiJogo.getTerritoriosJogador(vez));
                    apiView.atualizaQtdPosic(apiJogo.getJogadorExPosic(vez));
                    continente++;
                    return;
                }
                // Se não tiver mais exércitos para posicionar
                // Zera contador de continentes
                continente = 0;
                // Atualiza a view para ataque
                apiView.atualizaAtacantes(apiJogo.getTerritoriosJogador(vez));
                estado = 1;
                return;
            }
            // Se ainda tiver exércitos para posicionar
            apiView.atualizaQtdPosic(qtdEx);
            return;
        }
        return;
    }

    public void selecionouAtacante(String atacante){
        // Se estiver na etapa de ataque
        if (estado == 1){
            if(atacante != null){
            // Atualiza comboBox dos defensores com os adjacentes 

            apiView.atualizaDefensores(apiJogo.getTerritoriosAdjDominados(atacante, vez));
            }
        }
    }

    public void selecionouOrigem(String origem){
        //TODO: METODO INCOMPLETO - N VERIFIQUEI A LOGICA
        // Se estiver na etapa de reposicionamento
        if (estado == 2){
            // Atualiza comboBox do destino com adjacentes e quantidade de exércitos
            apiView.atualizaDestinos(apiJogo.getTerritoriosAdjDominados(origem, vez));
            apiView.atualizaQtdReposicionamento(apiJogo.getQtdExercitos(origem));
        }
    }

    public void clicouReposicionar(String origem, String destino, Integer qtd){
        // Se estiver na etapa de reposicionamento
        if (estado == 2){
            //TODO
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
