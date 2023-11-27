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

    // Guarda se é a primeira rodada
    private boolean primeiraRodada = true;

    // Arrays para guardar os dados de reposicionamento (bloquear reposicionamento múltiplo)
    private String[] nomesTerritoriosReposicionamento;
    private Integer[] qtdExercitosRepos;

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
                    // Atualiza continente para não testar o mesmo
                    continente++;
                    return true;
                }
            }
            // Atualiza identificador de continentes, para indicar que já testou todos
            continente++;
            territorios = apiJogo.getTerritoriosJogador(0);
            apiJogo.atualizaQtdExPosicGeral(0);
            qtd = apiJogo.getQtdExercitosPosic(0);

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
            // Atualiza a view para posicionamento do jogador da vez
            apiView.mudaJogador(apiJogo.getNomeJogadorVez(vez), apiJogo.getCorJogadorVez(vez), apiJogo.getDescObjJogadorVez(vez), apiJogo.getImgCartasJogador(vez));
            primeiroPosicionamento();
        }
    }

    public void clicouTerminarRodada(){
        // Se estiver na etapa de posicionamento
        if (estado == 0){
            if (apiJogo.getQtdExercitosPosic(vez) == 0){
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
            nomesTerritoriosReposicionamento = apiJogo.getTerritoriosReposicionamento(vez);
            // Se tiver algum território com mais de 1 exército para reposicionar
            if (nomesTerritoriosReposicionamento != null){
                qtdExercitosRepos = new Integer[nomesTerritoriosReposicionamento.length];
                for (int i = 0; i < nomesTerritoriosReposicionamento.length; i++){
                    qtdExercitosRepos[i] = (apiJogo.getQntExTerritorio(nomesTerritoriosReposicionamento[i]) - 1);
                }
                apiView.atualizaReposicionamento(nomesTerritoriosReposicionamento);
                estado = 2;
                return;
            }   
        }
        // Se não retornou ainda, ou está na etapa de reposicionamento 
        // ou não tem mais exércitos para reposicionar
        estado = 0;
        vez = (vez + 1) % apiJogo.getQtdJogadores();
        apiView.mudaJogador(apiJogo.getNomeJogadorVez(vez), apiJogo.getCorJogadorVez(vez), apiJogo.getDescObjJogadorVez(vez), apiJogo.getImgCartasJogador(vez));
        primeiroPosicionamento();
    }

    // Método chamado quando é o primeiro posicionamento do jogador naquela rodada, para verificar se tem algum continente dominado por ele
    private void primeiroPosicionamento(){
        String[] territorios;
        Integer qtd;
        // Verifica se tem algum continente dominado para posicionar exércitos nele primeiro
        for (continente = 0; continente < 6; continente++){
            if (apiJogo.dominaCont(vez, apiJogo.getContinentesLista()[continente])){
                territorios = apiJogo.getTerritoriosCont(apiJogo.getContinentesLista()[continente]);
                qtd = apiJogo.getExCont(vez, apiJogo.getContinentesLista()[continente]);
                // Atualiza a view para posicionar no continente dominado
                apiView.atualizaPosicionamento(territorios);
                apiView.atualizaQtdPosic(qtd);
                // Atualiza identificador de continentes, para não testar o mesmo
                continente++;
                return;
            }
        }
        // Atualiza identificador de continentes, para indicar que já testou todos
        continente++;
        // Se não tiver nenhum continente dominado, posiciona no resto dos territórios
        territorios = apiJogo.getTerritoriosJogador(vez);
        apiJogo.atualizaQtdExPosicGeral(vez);
        qtd = apiJogo.getQtdExercitosPosic(vez);
        
        // Atualiza a view para posicionar no resto dos territórios
        apiView.atualizaPosicionamento(territorios);
        apiView.atualizaQtdPosic(qtd);
        return;
    }

    public void clicouPosicionar(String territorio, Integer qtd){
        // Verifica se está na rodada de posicionamento para agir
        if (estado == 0){

            // Posiciona os exércitos e atualiza a quantidade a posicionar do jogador
            apiJogo.posicionarExercitos(territorio, qtd, vez);
            Integer qtdEx = apiJogo.getQtdExercitosPosic(vez);

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
                    apiView.atualizaQtdPosic(apiJogo.getQtdExercitosPosic(vez));
                    continente++;
                    return;
                }
                // Se não tiver mais exércitos para posicionar
                // Zera contador de continentes
                continente = 0;
                // Se for a primeira rodada, só pode posicionamento para todos
                if (primeiraRodada){
                    vez = (vez + 1) % apiJogo.getQtdJogadores();
                    apiView.mudaJogador(apiJogo.getNomeJogadorVez(vez), apiJogo.getCorJogadorVez(vez), apiJogo.getDescObjJogadorVez(vez), apiJogo.getImgCartasJogador(vez));
                    primeiroPosicionamento();
                    if (vez == 0){
                        primeiraRodada = false;
                    }
                    return;
                }
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

            apiView.atualizaDefensores(apiJogo.getTerritoriosAdjNaoDominados(atacante, vez));
            }
        }
    }

    public void selecionouOrigem(String origem){
        // Se selecionado for nulo não faz nada
        if (origem == null){
            return;
        }
        // Se estiver na etapa de reposicionamento
        if (estado == 2){
            // Atualiza comboBox do destino com adjacentes
            apiView.atualizaDestinos(apiJogo.getTerritoriosAdjDominados(origem, vez));

            // Pega o index do território selecionado para ver quantidade que ainda pode reposicionar
            int i = 0;
            for (; i < nomesTerritoriosReposicionamento.length; i++) {
                if (nomesTerritoriosReposicionamento[i].equals(origem)) {
                    break;
                }
            }
            // Atualiza a quantidade de exércitos que ainda pode reposicionar
            apiView.atualizaQtdRepos(qtdExercitosRepos[i]);
        }
    }

    public void clicouReposicionar(String origem, String destino, Integer qtd){
        // Se estiver na etapa de reposicionamento
        if (estado == 2){
            // Reposiciona os exércitos
            apiJogo.reposicionarExercitos(origem, destino, qtd);

             // Pega o index do território selecionado para diminuir a quantidade que ainda pode reposicionar
            int i = 0;
            for (; i < nomesTerritoriosReposicionamento.length; i++) {
                if (nomesTerritoriosReposicionamento[i].equals(origem)) {
                    break;
                }
            }
            qtdExercitosRepos[i] -= qtd;

            // Se tiver exércitos para reposicionar continua na etapa de reposicionamento
            for (int j = 0; j < nomesTerritoriosReposicionamento.length; j++){
                if (qtdExercitosRepos[j] > 0){
                    apiView.atualizaReposicionamento(nomesTerritoriosReposicionamento);
                    apiView.atualizaQtdRepos(0);
                    return;
                }
            }
            // Se não dá carta se for o caso e passa a vez
            //TODO Dá carta se for o caso
            estado = 0;
            vez = (vez + 1) % apiJogo.getQtdJogadores();
            apiView.mudaJogador(apiJogo.getNomeJogadorVez(vez), apiJogo.getCorJogadorVez(vez), apiJogo.getDescObjJogadorVez(vez), apiJogo.getImgCartasJogador(vez));
            primeiroPosicionamento();
        }
    }

    // Método que retorna a cor de um território
    public Color getCorTerritorio(String t){
        return apiJogo.getCorTerritorio(t);
    }

    public Integer getQtdExercitos(String t){
        return apiJogo.getQntExTerritorio(t);
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

    public boolean getPrimeiraRodada(){
        return primeiraRodada;
    }

    public void setPrimeiraRodada(boolean primeiraRodada){
        this.primeiraRodada = primeiraRodada;
    }
}
