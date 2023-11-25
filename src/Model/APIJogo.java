package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.io.*;

import View.APIView;


 public class APIJogo{
    private static APIJogo APIJogo = null;
    private Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
    private Jogo jogo = Jogo.getJogo();
    private APIView apiView = APIView.getAPIView();

    //Arquivo para salvar o jogo
    FileReader inputStream = null;
    FileWriter outputStream = null;

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
		for (String t: tabuleiro.mapTerritorios.keySet()) {
            terr[cont] = t;
            cont ++;
		}

		return terr;
	}
    
    //Método de realizar ataque
    public void realizaAtaque(Jogador Jatacante,String atacante, String defensor, int[]dadosAtaque, int[]dadosDefesa) {
    	Territorio Tatacante = tabuleiro.mapTerritorios.get(atacante);
    	Territorio Tdefensor = tabuleiro.mapTerritorios.get(defensor);
    	jogo.RealizaAtaque(Jatacante, Tatacante, Tdefensor,dadosAtaque, dadosDefesa);
    	
    }
    
    //Método de realizar ataque forcado
    public void realizaAtaqueForcado(Jogador Jatacante,String atacante, String defensor, int dadoAtaque, int dadoDefesa) {
    	Territorio Tatacante = tabuleiro.mapTerritorios.get(atacante);
    	Territorio Tdefensor = tabuleiro.mapTerritorios.get(defensor);
    	jogo.RealizaAtaqueForcado(Jatacante, Tatacante, Tdefensor, dadoAtaque, dadoDefesa);
    }

    // Retorna cor do jogador que domina aquele território
    public Color getCorTerritorio(String t){
    	return tabuleiro.mapTerritorios.get(t).getCor();
    }

    // Retorna quantidade de exércitos no território em formato de string
    public String getQtdExTerritorio(String t){
        return String.valueOf(tabuleiro.mapTerritorios.get(t).getQntExercitos());
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
        ArrayList<Territorio> adjacentes = tabuleiro.mapTerritorios.get(t).getAdjacentes();
    	String[] listaTerritorios = new String[adjacentes.size()];
    	int cont = 0;
    	for (Territorio ter: adjacentes) {
    		listaTerritorios[cont] = ter.getNome();
    		cont++;
    	}
    	return listaTerritorios;
    }

    // Método que salva jogo em arquivo
    /*Informacoes para salvar
     * -qtd de jogadores
     * -qtd de exercitos em cada territorio
     * -nome dos jogadores
     * - jogadores que dominam cada territorio
     * -objetivos dos jogadores
     * -cores dos jogadores
     * -cartas dos jogadores
    */
    public void salvarJogo(){
        try {
            outputStream = new FileWriter("jogo.txt");
            //Escreve qtd de jogadores
            outputStream.write(String.valueOf(jogo.getJogadores().size()));
            outputStream.write("\n");

            //Escreve a qtd de exercitos em cada territorio
            for (Territorio t: tabuleiro.mapTerritorios.values()) {
                outputStream.write(String.valueOf(t.getQntExercitos()));
                outputStream.write("\n");
            }

            //Escreve o nome dos jogadores
            for (Jogador j: jogo.getJogadores()) {
                outputStream.write(j.getNome());
                outputStream.write("\n");
            }

            //Escreve os jogadores que dominam cada territorio
            for (Territorio t: tabuleiro.mapTerritorios.values()) {
                outputStream.write(t.getJogador().getNome());
                outputStream.write("\n");
            }

            //Escreve os objetivos dos jogadores
            for (Jogador j: jogo.getJogadores()) {
                outputStream.write(j.getObj().getDescricao());
                outputStream.write("\n");
            }

            //Escreve as cores dos jogadores
            for (Jogador j: jogo.getJogadores()) {
                outputStream.write(String.valueOf(j.getCor().getRGB()));
                outputStream.write("\n");
            }

            //Escreve as cartas dos jogadores
            for (Jogador j: jogo.getJogadores()) {
                for (Cartas c: j.getCartas()) {
                    outputStream.write(c.getTerritorio().getNome());
                    outputStream.write("\n");
                    outputStream.write(String.valueOf(c.getF().ordinal()));
                    outputStream.write("\n");
                }
            }
        } 

        catch (IOException e) {
            System.out.println("Erro ao abrir arquivo para salvar jogo");
        }

        //Fechar arquivo
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } 
                catch (IOException e) {
                    System.out.println("Erro ao fechar arquivo para salvar jogo");
                }
            }
        }
    }
}
