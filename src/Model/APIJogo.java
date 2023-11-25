package Model;

import java.awt.Color;
import java.util.ArrayList;

import Controller.Gerente;

import java.io.*;

import View.APIView;

 public class APIJogo{
    private static APIJogo APIJogo = null;
    private Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
    private Jogo jogo = Jogo.getJogo();
    private APIView apiView = APIView.getAPIView();

    //Arquivo para salvar o jogo
    FileReader outputStream = null;
    FileWriter inputStream = null;

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
    public void realizaAtaque(String atacante, String defensor, int[]dadosAtaque, int[]dadosDefesa) {
    	Territorio Tatacante = tabuleiro.mapTerritorios.get(atacante);
    	Territorio Tdefensor = tabuleiro.mapTerritorios.get(defensor);
    	jogo.RealizaAtaque(Tatacante, Tdefensor,dadosAtaque, dadosDefesa);
    	
    }
    
    //Método de realizar ataque forcado
    public void realizaAtaqueForcado(String atacante, String defensor, int dadoAtaque, int dadoDefesa) {
    	Territorio Tatacante = tabuleiro.mapTerritorios.get(atacante);
    	Territorio Tdefensor = tabuleiro.mapTerritorios.get(defensor);
    	jogo.RealizaAtaqueForcado(Tatacante, Tdefensor, dadoAtaque, dadoDefesa);
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

    // Método que retorna adjacentes não dominados de um território por string
    public String[] getTerritoriosDefensores(String t, int vez) {
        ArrayList<Territorio> adjacentes = tabuleiro.mapTerritorios.get(t).getAdjacentes();
    	String[] listaTerritorios = new String[adjacentes.size()];
    	int cont = 0;
    	for (Territorio ter: adjacentes) {
    		// Se o jogador não dominar o adjacente, adiciona na lista
            if (ter.getJogador().getNome() != jogo.getJogadorVez(vez).getNome()){
                listaTerritorios[cont] = ter.getNome();
                cont++;
            }
    	}
        String[] listaTerritoriosFinal = new String[cont];
        for (int i = 0; i < cont; i++) {
            listaTerritoriosFinal[i] = listaTerritorios[i];
        }
    	return listaTerritoriosFinal;
    }

    // Método que retorna o nome do jogador da vez
    public String getNomeJogadorVez(int i){
        return jogo.getJogadorVez(i).getNome();
    }

    // Método que retorna a cor do jogador da vez
    public Color getCorJogadorVez(int i){
        return jogo.getJogadorVez(i).getCor();
    }

    // Método que retorna a descrição do objetivo do jogador da vez
    public String getDescObjJogadorVez(int i){
        return jogo.getJogadorVez(i).getObj().getDescricao();
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
     * -vez do jogador
    */
    public void salvarJogo(){
        try {
            inputStream = new FileWriter("jogo.txt",false);
            
            //Escreve qtd de jogadores e vez do jogador
            inputStream.write(String.valueOf(jogo.getJogadores().size()));
            inputStream.write("\n");
            inputStream.write(String.valueOf(Gerente.getGerente().getVez()));
            inputStream.write("\n");
            
            //Escreve o nome dos jogadores
            for (Jogador j: jogo.getJogadores()) {
                inputStream.write(j.getNome() + " ");
            }
            
            inputStream.write("\n");
            
            //Escreve as cores dos jogadores
            for (Jogador j: jogo.getJogadores()) {
                inputStream.write(j.getNome() + " ");
                inputStream.write(String.valueOf(j.getCor().getRGB()));
                inputStream.write("\n");
            }

            //Escreve a qtd de exercitos em cada territorio e o nome do jogador que o domina
            for (Territorio t: tabuleiro.mapTerritorios.values()) {
                inputStream.write(t.getNome() + " ");
                inputStream.write(String.valueOf(t.getQntExercitos()) + " " + t.getJogador().getNome());
                inputStream.write("\n");
            }

            //Escreve os objetivos dos jogadores
            for (Jogador j: jogo.getJogadores()) {
                inputStream.write(j.getNome() + " ");
                switch(j.getObj().getClass().getName()){
                    case "Model.ObjetivoContinentes":
                        inputStream.write("1 ");
                        break;
                    case "Model.ObjetivoDestruir":
                        inputStream.write("2 ");
                        break;
                    case "Model.ObjetivoTerritorios":
                        inputStream.write("3 ");
                        break;
                }
                inputStream.write(j.getObj().getDescricao());
                inputStream.write("\n");
            }

            //Escreve as cartas dos jogadores
            for (Jogador j: jogo.getJogadores()) {
                for (Cartas c: j.getCartas()) {
                    inputStream.write(j.getNome() + " ");
                    inputStream.write(c.getTerritorio().getNome()+ " ");
                    inputStream.write(String.valueOf(c.getF().ordinal()));
                    inputStream.write("\n");
                }
            }
        } 

        catch (IOException e) {
            System.out.println("Erro ao abrir arquivo para salvar jogo");
        }

        //Fechar arquivo
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } 
                catch (IOException e) {
                    System.out.println("Erro ao fechar arquivo para salvar jogo");
                }
            }
        }
    }

    // Método que carrega jogo de arquivo
    public boolean carregarJogo(){
        try {
            outputStream = new FileReader("jogo.txt");
            BufferedReader br = new BufferedReader(outputStream);
            String linha = br.readLine();

            //Lê a qtd de jogadores
            int qtdJogadores = Integer.parseInt(linha);

            //Lê a vez do jogador
            linha = br.readLine();
            Gerente.getGerente().setVez(Integer.parseInt(linha));

            //Lê os nomes e cores dos jogadores
            String[] nomes = new String[qtdJogadores];
            Color[] cores = new Color[qtdJogadores];
            int cont = 0;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ");
                nomes[cont] = dados[0];
                cores[cont] = new Color(Integer.parseInt(dados[1]));
                cont++;
            }

            //Adiciona os jogadores
            resetJogadores();
            for (int i = 0; i < qtdJogadores; i++) {
                addJogador(nomes[i], cores[i]);
            }

            //Lê a qtd de exercitos em cada territorio e o nome do jogador que o domina
            for (Jogador j: jogo.getJogadores()) {
                linha = br.readLine();
                String[] dados = linha.split(" ");
                for (Territorio t: tabuleiro.mapTerritorios.values()) {
                    if (t.getNome().equals(dados[0])) {
                        t.setQntExercitos(Integer.parseInt(dados[1]));
                        t.setJogador(j);
                        j.addTerritorio(t);
                    }
                }
            }

            //Lê os objetivos dos jogadores
            for (Jogador j: jogo.getJogadores()) {
                linha = br.readLine();
                String[] dados = linha.split(" ");
                switch(Integer.parseInt(dados[1])){
                    case 1:
                        j.setObj(new ObjetivoContinentes(tabuleiro.getMapContinentes().get(dados[2]), tabuleiro.getMapContinentes().get(dados[3]), true));
                        break;
                    case 2:
                        j.setObj(new ObjetivoDestruir(jogo.getJogador(dados[2])));
                        break;
                    case 3:
                        j.setObj(new ObjetivoTerritorios(Integer.parseInt(dados[2])));
                        break;
                }
            }

            //Lê as cartas dos jogadores
            for (Jogador j: jogo.getJogadores()) {
                linha = br.readLine();
                String[] dados = linha.split(" ");
                for (int i = 0; i < dados.length; i+=2) {
                    for (Cartas c: j.getCartas()) {
                        if (c.getTerritorio().getNome().equals(dados[i])) {
                            c.setF(Cartas.Formato.values()[Integer.parseInt(dados[i+1])]);
                        }
                    }
                }
            }
            return true;
        } 
        catch (IOException e) {
            System.out.println("Erro ao abrir arquivo para carregar jogo");
            return false;
        }
    }

    // Método que retorna a quantidade de exércitos que o jogador pode posicionar
    public int getQtdExercitosPosic(int vez){
        return jogo.getJogadorVez(vez).getQtdExercitoPosic();
    }

    // Método que retorna a quantidade de jogadores
    public int getQtdJogadores(){
        return jogo.getJogadores().size();
    }
}
