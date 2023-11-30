package Model;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Controller.Gerente;

import java.io.*;

import View.APIView;

 public class APIJogo{
    // Atributos

    // Singleton
    private static APIJogo APIJogo = null;

    private Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
    private Jogo jogo = Jogo.getJogo();
    private APIView apiView = APIView.getAPIView();

    // JFileChooser para salvar e carregar jogo
    private JFileChooser chooser = new JFileChooser();

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

    public void comecaJogoCarregado(){
		//Inicializa o tabuleiro
    	tabuleiro.Inicializa();
    	
        // Instancia cartas
        jogo.InstanciaCartas(tabuleiro.getMapTerritorios());

        // Associando observador GamePanel ao observado Jogo
        jogo.add(apiView.getObs());
    }

    // Retorna lista de nomes de territórios
    public String[] getTerritoriosLista() {
		String [] terr = new String[51]; 
		int cont = 0;

        // Adiciona na lista os nomes dos territórios
		for (Territorio t: tabuleiro.getlistaTerritorios()) {
            terr[cont] = t.getNome();
            cont ++;
		}

		return terr;
	}
    
    //Método de realizar ataque
    public int[] realizaAtaque(String atacante,String defensor, Integer numAtaque, Integer numDefesa) {
    	Territorio Tatacante = tabuleiro.mapTerritorios.get(atacante);
    	Territorio Tdefensor = tabuleiro.mapTerritorios.get(defensor);

        // Realiza ataque e retorna array com os resultados
        int[] array = jogo.RealizaAtaque(Tatacante, Tdefensor, numAtaque, numDefesa);

        // Verifica se jogador ganhou após essa rodada
		Gerente.getGerente().verificaGanhou(Gerente.getGerente().getVez());
        return array;
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

        // Adiciona na lista os nomes dos jogadores
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

        // Adiciona na lista os nomes dos territórios
    	for (Territorio t: ter) {
    		listaTerritorios[cont] = t.getNome();
    		cont++;
    	}
    	return listaTerritorios;
    }

    // Método que retorna adjacentes não dominados de um território por string
    public String[] getTerritoriosAdjNaoDominados(String t, int vez) {
        ArrayList<Territorio> adjacentes = tabuleiro.mapTerritorios.get(t).getAdjacentes();
    	String[] listaTerritorios = new String[adjacentes.size()];
    	int cont = 0;

        // Adiciona na lista os territórios que não são dominados pelo jogador
    	for (Territorio ter: adjacentes) {
    		// Se o jogador não dominar o adjacente, adiciona na lista
            if (ter.getJogador().getNome() != jogo.getJogadorVez(vez).getNome()){
                listaTerritorios[cont] = ter.getNome();
                cont++;
            }
    	}

        // Se não tiver nenhum território não dominado, retorna null
        if (cont == 0) {
            String[] lista= {null};
            return lista;
        }

        // Copia a lista para uma lista final, para não ficar nenhum espaço vazio
        String[] listaTerritoriosFinal = new String[cont];

        for (int i = 0; i < cont; i++) {
            listaTerritoriosFinal[i] = listaTerritorios[i];
        }

    	return listaTerritoriosFinal;
    }

    // Método que retorna adjacentes dominados de um território por string
    public String[] getTerritoriosAdjDominados(String t, int vez) {
        ArrayList<Territorio> adjacentes = tabuleiro.mapTerritorios.get(t).getAdjacentes();
    	String[] listaTerritorios = new String[adjacentes.size()];
    	int cont = 0;

        // Adiciona na lista os territórios que são dominados pelo jogador
    	for (Territorio ter: adjacentes) {
    		// Se o jogador dominar o adjacente, adiciona na lista
            if (ter.getJogador().getNome() == jogo.getJogadorVez(vez).getNome()){
                listaTerritorios[cont] = ter.getNome();
                cont++;
            }
    	}

        // Se não tiver nenhum território dominado, retorna null
        if (cont == 0) {
            String[] lista= {null};
            return lista;
        }

        // Copia a lista para uma lista final, para não ficar nenhum espaço vazio
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

    //Verifica se o jogador da vez ganhou o jogo
    public boolean verificaGanhou(int vez){
        // Se a vez for -1, verifica todos os jogadores
        if (vez == -1){
            for (Jogador j: jogo.getJogadores()) {
                if (j.getObj().alcancou(j)){
                    // Se algum jogador ganhou, atualiza a vez
                    Gerente.getGerente().setVez(jogo.getJogadores().indexOf(j));
                    return true;
                }
            }
            return false;
        }
        // Se não, verifica se o jogador da vez cumpriu seu objetivo, condicao para ganhar o jogo
        Jogador j = jogo.getJogadorVez(vez);
        return j.getObj().alcancou(j);
    }

    // Método que retorna lista com os territórios do jogador que têm mais de um exército
    public String[] getTerritoriosMaisDeUm(int vez){
        ArrayList<Territorio> ter = jogo.getJogadorVez(vez).getTerritorios();
        String[] listaTerritorios = new String[ter.size()];
        int cont = 0;

        // Adiciona na lista os territórios que têm mais de um exército
        for (Territorio t: ter) {
            if (t.getQntExercitos() > 1){
                listaTerritorios[cont] = t.getNome();
                cont++;
            }
        }

        // Se não tiver nenhum território com mais de um exército, retorna null
        if (cont == 0){
            String[] lista = {};
            return lista;
        }

        // Copia a lista para uma lista final, para não ficar nenhum espaço vazio
        String[] listaTerritoriosFinal = new String[cont];
        for (int i = 0; i < cont; i++) {
            listaTerritoriosFinal[i] = listaTerritorios[i];
        }
        return listaTerritoriosFinal;
    }

    // Posiciona exércitos no território e chama método para atualizar view pelos observadores
    public void posicionarExercitos(String t, int qtd, int vez){
        // Chama método de posicionar exércitos de Jogo
        jogo.getJogadorVez(vez).posicionarExercitos(tabuleiro.mapTerritorios.get(t), qtd);

        // Atualiza variável de exércitos a posicionar no jogador
        jogo.setMod1(tabuleiro.mapTerritorios.get(t));
        jogo.setMod2(null);

        // Notifica observadores de jogo
        jogo.notificaObs();
    }

    // Chama reposicionarExercitos de Jogo
    public void reposicionarExercitos(String origem, String destino, Integer qtd){
        jogo.reposicionarExercitos(tabuleiro.mapTerritorios.get(origem), tabuleiro.mapTerritorios.get(destino), qtd);
    }

    // Método que retorna a quantidade de exércitos que o jogador pode posicionar
    public int getQtdExercitosPosic(int vez){
        return jogo.getJogadorVez(vez).getQtdExercitoPosic();
    }

    // Retorna lista de nomes dos continentes
    public String[] getContinentesLista(){
        String[] listaContinentes = new String[tabuleiro.getMapContinentes().size()];
        int cont = 0;

        // Adiciona na lista os nomes dos continentes
        for (Continente c: tabuleiro.getMapContinentes().values()) {
            listaContinentes[cont] = c.getNome();
            cont++;
        }

        return listaContinentes;
    }

    // Retorna lista de nomes dos territórios de um continente
    public String[] getTerritoriosCont(String c){
        String[] listaTerritorios = new String[tabuleiro.getMapContinentes().get(c).getTerritorios().size()];
        int cont = 0;

        // Adiciona na lista os nomes dos territórios
        for (Territorio t: tabuleiro.getMapContinentes().get(c).getTerritorios()) {
            listaTerritorios[cont] = t.getNome();
            cont++;
        }

        return listaTerritorios;
    }

    // Retorna quantidade de exércitos a posicionar no continente e atualiza a variável de exércitos a posicionar no jogador
    public Integer getExCont(int vez, String c){
        // Atualiza variável de exércitos a posicionar no jogador
        Integer qtd = tabuleiro.getContinente(c).getQtdExerc();

        // Atualiza variável de exércitos a posicionar no jogador
        jogo.getJogadorVez(vez).setQtdExercitoPosic(qtd);

        return qtd;
    }

    // Retorna array com os nomes dos territórios das cartas do jogador
    public String[] getNomesCartasJogador(int vez){
        ArrayList<Carta> cartas = jogo.getJogadorVez(vez).getCartas();
        String[] arrayCartas = new String[cartas.size()];
        int cont = 0;

        // Adiciona na lista os nomes dos territórios das cartas
        for (Carta c: cartas) {
            Territorio t = c.getTerritorio();

            // Se a carta for de coringa, adiciona "Coringa" na lista
            if (t == null){
                arrayCartas[cont] = "Coringa";
            }

            // Se não, adiciona o nome do território na lista
            else{
                arrayCartas[cont] = t.getNome();
            }

            cont++;
        }

        return arrayCartas;
    }

    // Verifica se conquistou nessa rodada para dar carta
    public boolean analisarDarCarta(int vez){

        // Se o jogador conquistou nessa rodada, dá carta
    	if (jogo.getJogadorVez(vez).getConquistouNessaRodada()){
            jogo.DaCarta(jogo.getJogadorVez(vez));

            // Atualiza variável de conquistou nessa rodada
            jogo.getJogadorVez(vez).setConquistouNessaRodada(false);
            return true;
        }

        return false;
    }

    // Retorna se o jogador atingiu o máximo de cartas permitido (6 cartas)
    public boolean maxCartas(int vez){
        return jogo.getJogadorVez(vez).getCartas().size() == 6;
    }

    // Troca cartas do jogador da vez
    public Integer trocarCartas(int vez, int numDeTrocas){
        Jogador j = jogo.getJogadorVez(vez);

        // Se o jogador puder trocar cartas, chama o método de trocar cartas de Jogo
        if (j.temTroca()){
            return j.trocarCartas(numDeTrocas);
        }

        return 0;
    }

    // Altera o estado de eliminação do jogador
    public void retiraEliminado(String jogador){
        jogo.getJogador(jogador).setEliminadoNessaRodada(false);
    }

    // Retorna quantidade de exércitos que tem em um território
    public Integer getQntExTerritorio(String territorio){
            Territorio t = tabuleiro.mapTerritorios.get(territorio);
            return t.getQntExercitos();
    }

    // Retorna se o jogador domina o continente
    public boolean dominaCont(int vez, String c){
        return tabuleiro.getContinente(c).dominado(jogo.getJogadorVez(vez));
    }

    // Atualiza a variável de exércitos a posicionar no jogador
    public void atualizaQtdExPosicGeral(int vez, Integer bonusTroca){
        jogo.getJogadorVez(vez).atualizaQtdExPosicGeral(bonusTroca);
    }

    // Método que retorna a quantidade de jogadores
    public int getQtdJogadores(){
        return jogo.getJogadores().size();
    }

    // Notifica observadores de jogo
    public void notificaObsJogo(){
        jogo.notificaObs();
    }

    // Método para reiniciar o jogo
    public void reiniciarJogo(){
        jogo.reiniciarJogo();
        jogo.setMod1(null);
        jogo.setMod2(null);
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
     * -qtd de troca de cartas que o jogador fez
    */
    public void salvarJogo(){
        
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                inputStream = new FileWriter(chooser.getSelectedFile(),false);
                //Escreve se está na primeira rodada
                inputStream.write(String.valueOf(Gerente.getGerente().getPrimeiraRodada()));
                inputStream.write("\n");

                //Escreve qtd de jogadores e vez do jogador
                inputStream.write(String.valueOf(jogo.getJogadores().size()));
                inputStream.write("\n");
                inputStream.write(String.valueOf(Gerente.getGerente().getVez()));
                inputStream.write("\n");
                
                //Escreve o nome dos jogadores
                for (Jogador j: jogo.getJogadores()) {
                    inputStream.write(j.getNome());
                    //Se não for o último jogador, escreve um espaço
                    if (j != jogo.getJogadores().get(jogo.getJogadores().size()-1))
                        inputStream.write(";");
                }
                
                inputStream.write("\n");
                
                //Escreve as cores dos jogadores
                for (Jogador j: jogo.getJogadores()) {
                    inputStream.write(String.valueOf(j.getCor().getRGB()));
                    // Se não for o último jogador, escreve um espaço
                    if (j != jogo.getJogadores().get(jogo.getJogadores().size()-1))
                        inputStream.write(";");
                }
                inputStream.write("\n");

                //Escreve a qtd de exercitos em cada territorio e o nome do jogador que o domina
                for (Territorio t: tabuleiro.mapTerritorios.values()) {
                    inputStream.write(t.getNome() + ";");
                    inputStream.write(String.valueOf(t.getQntExercitos()) + ";" + t.getJogador().getNome());
                    inputStream.write("\n");
                }

                //Escreve os objetivos dos jogadores
                for (Jogador j: jogo.getJogadores()) {
                    switch(j.getObj().getClass().getName()){
                        case "Model.ObjetivoContinentes":
                            inputStream.write("1;");
                            inputStream.write(((ObjetivoContinentes)j.getObj()).getCont1().getNome() + ";");
                            inputStream.write(((ObjetivoContinentes)j.getObj()).getCont2().getNome() + ";");
                            inputStream.write(String.valueOf(((ObjetivoContinentes)j.getObj()).getQtdContinentes()));
                            break;

                        case "Model.ObjetivoDestruir":
                        inputStream.write("2;");
                        inputStream.write(((ObjetivoDestruir)j.getObj()).getJAlvo().getNome());
                        break;

                        case "Model.ObjetivoTerritorios":
                        inputStream.write("3;");
                        inputStream.write(String.valueOf(((ObjetivoTerritorios)j.getObj()).getQtdTerritorios()));
                        break;
                    }
                    inputStream.write("\n");
                }

                //Escreve as cartas dos jogadores
                for (Jogador j: jogo.getJogadores()) {

                    if(j.getCartas().size() == 0) {
                        inputStream.write("0");
                        inputStream.write("\n");
                        continue;
                    }

                    inputStream.write(String.valueOf(j.getCartas().size()) + ";");

                    for (Carta c: j.getCartas()) {
                        // Carta de coringa
                        if(c.getTerritorio() == null){
                            inputStream.write("Coringa");
                        }

                        // Carta de território
                        else{
                            inputStream.write(c.getTerritorio().getNome());
                        }

                        // Se não for a última carta, escreve um ;
                        if( c != j.getCartas().get(j.getCartas().size()-1)){
                            inputStream.write(";");
                        }
            
                    }
		    inputStream.write("\n");
                    
                }
                //Escreve a quantidade de trocas de cartas que o jogador fez
                inputStream.write(Gerente.getGerente().getNumDeTrocas().toString());
                    
            } 

            //Caso ocorra algum erro
            catch (IOException ex) {
                System.out.println("Erro ao abrir arquivo para salvar jogo");
            }

            //No fim da leitura, fecha o arquivo
            finally {
                if (inputStream != null) {

                    try {
                        inputStream.close();
                    } 

                    //Caso ocorra algum erro
                    catch (IOException ex) {
                        System.out.println("Erro ao fechar arquivo para salvar jogo");
                    }
                }
            }
        }
    }

    // Método que carrega jogo de arquivo
    public boolean carregarJogo(){
        int option = chooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                outputStream = new FileReader(chooser.getSelectedFile());
                BufferedReader br = new BufferedReader(outputStream);

                //Lê se está na primeira rodada
                String linha = br.readLine();
                Gerente.getGerente().setPrimeiraRodada(Boolean.parseBoolean(linha));

                //Lê a qtd de jogadores
                linha = br.readLine();
                int qtdJogadores = Integer.parseInt(linha);

                //Lê a vez do jogador
                linha = br.readLine();
                Gerente.getGerente().setVez(Integer.parseInt(linha));

                //Lê os nomes e cores dos jogadores
                String[] nomes;
                linha = br.readLine();
                nomes = linha.split(";");

                String[] cores;
                linha = br.readLine();
                cores = linha.split(";");

                //Adiciona os jogadores
                resetJogadores();
                for (int i = 0; i < qtdJogadores; i++) {
                    Color cor;
                    switch(Integer.parseInt(cores[i])){
                        case -16777216:
                            cor = Color.BLACK;
                            break;
                        case -16776961:
                            cor = Color.BLUE;
                            break;
                        case -16711681:
                            cor = Color.CYAN;
                            break;
                        case -16711936:
                            cor = Color.GREEN;
                            break;
                        case -65536:
                            cor = Color.RED;
                            break;
                        case -256:
                            cor = Color.YELLOW;
                            break;
                        default:
                            cor = Color.WHITE;
                            break;
                    }
                    addJogador(nomes[i], cor);
                    ;
                }

                // Inicializar objetos necessários para carregar o jogo
                this.comecaJogoCarregado();

                //Lê os territórios, a quantidade de exércitos e o jogador que domina
                for (int i = 0; i < 51; i++) {
                    linha = br.readLine();
                    String[] dados = linha.split(";");
                    int qtdEx = Integer.parseInt(dados[1]);
                    Jogador j = jogo.getJogador(dados[2]);
                    tabuleiro.mapTerritorios.get(dados[0]).setQntExercitos(qtdEx);
                    tabuleiro.mapTerritorios.get(dados[0]).setJogador(j);
                    j.addTerritorio(tabuleiro.mapTerritorios.get(dados[0]));
                }

                //Lê os objetivos dos jogadores
                jogo.InstanciaObjetivos();
                for (Jogador j: jogo.getJogadores()) {
                    linha = br.readLine();
                    String[] dados = linha.split(";");
                    Objetivo obj;
                    switch(Integer.parseInt(dados[0])){
                        case 1:
                            if(Integer.parseInt(dados[3]) == 3){
                                obj = new ObjetivoContinentes(tabuleiro.getMapContinentes().get(dados[1]), tabuleiro.getMapContinentes().get(dados[2]), true);
                                j.setObj(obj);
                            }

                            else{
                                obj = new ObjetivoContinentes(tabuleiro.getMapContinentes().get(dados[1]), tabuleiro.getMapContinentes().get(dados[2]), false);
                                j.setObj(obj);
                            }

                            break;
                        
                        case 2:
                            obj = new ObjetivoDestruir(jogo.getJogador(dados[1]));
                            j.setObj(obj);
                            break;

                        case 3:
                            obj = new ObjetivoTerritorios(Integer.parseInt(dados[1]));
                            j.setObj(obj);
                            break;

                        default:
                            obj = null;
                    }

                    // Substitui o objetivo do jogador pelo objetivo lido
                    for (Objetivo o: jogo.getObjetivos()){
                        if (obj.getDescricao().equals(o.getDescricao())){
                            jogo.getObjetivos().remove(o);
                            jogo.getObjetivos().add(obj);
                            break;
                        }
                    }
                }

                //Lê as cartas dos jogadores
                for (Jogador j: jogo.getJogadores()) {
                    linha = br.readLine();

                    String[] infos = linha.split(";");

                    int qtdCartas = Integer.parseInt(infos[0]);

                    if(qtdCartas == 0){
                        continue;
                    }

                    for (int i = 1; i < qtdCartas + 1; i++) {
                        Carta c;
                        if(infos[i].equals("Coringa")){
                            c = jogo.getCartaNome(null);
                        }
                        else{
                           c = jogo.getCartaNome(infos[i]);
                        }

                        j.addCarta(c);
                        jogo.getListaCartas().remove(c);
                    }

                }

                //Lê a quantidade de trocas de cartas do jogo
                linha = br.readLine();
                Gerente.getGerente().setNumDeTrocas(Integer.parseInt(linha));
                // Indica que todos os exércitos foram modificados
                jogo.setMod1(null);
                jogo.setMod2(null);
                
                return true;
            } 

            //Caso ocorra algum erro ao abrir o arquivo
            catch (IOException e) {
                System.out.println("Erro ao abrir arquivo para carregar jogo");
                return false;
            }
        }
        
        return false;
    }

}
