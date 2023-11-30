package View;
import Controller.Gerente;
import Model.APIJogo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

class GamePanel extends JPanel implements ObservadorIF {
	public static GamePanel gamePanel = null;
	
	//Imagens do tabuleiro, fundo e cartas de objetivo
	Image tabuleiroImg = null;
	Image background = null;
	Image cartaObj = null;

	//Componente gráfica
	Graphics2D g2d; 
	
	//Botões e comboboxes
	JButton salvarButton, dadoButton, nextButton, trocarCartasButton, posicButton, reposButton;
	
	//Lista de territórios no jogo
	String[] territorios;

	//Painel dos dados
	DadosPanel dadosPanel = new DadosPanel();
	
	//Painel das Cartas
	CartaObj cartaObjPanel = new CartaObj();
	CartaView cartaView = new CartaView();

	//Gerente
	Gerente gerente = Gerente.getGerente();

	//ComboBoxes e labels
	JComboBox<String> comboBoxPosicionamento;
	JLabel labelPosicionamento = new JLabel("Posicionar exercitos em");
	JComboBox<Integer> comboBoxQtdPosicionamento;
	JComboBox<String> comboBoxAtacantes;
	JLabel labelAtacantes = new JLabel("Escolher atacante");
	JComboBox<String> comboBoxDefensores;
	JLabel labelDefensores = new JLabel("Escolher defensor");
	JComboBox<String> comboBoxOrigemRepos;
	JComboBox<String> comboBoxDestinoRepos;
	JComboBox<Integer> comboBoxQtdRepos;
	JLabel labelReposicionamento = new JLabel("Selecione para reposicionar");
	
	String valoresDados[] = {"0","1","2","3","4","5","6"};
	JComboBox dadosAtacante = new JComboBox(valoresDados);
	JComboBox dadosDefensor = new JComboBox(valoresDados);
	APIJogo apiJogo = APIJogo.getAPIJogo();

	//Informações do jogador da vez
	String jogadorDaVez;
	Color corDoJogador;
	String descricaoObjetivo;
	String[] cartas; 
	JLabel jogadorDaVezLabel = new JLabel();

	//Array list de exércitos 
	ArrayList<Exercitos> listaExercitos = new ArrayList<Exercitos>();

	//Boolean para saber se os exércitos já foram criados
	Boolean ExercitosNaoCriados = true;

	//Construtor
	private GamePanel() {
		//Define o layout como null para poder posicionar os componentes
		setLayout(null);

		//Cria e adiciona os comboBoxes

		// ComboBox e label de posicionamento
		comboBoxPosicionamento = new JComboBox<String>();
		comboBoxPosicionamento.setBounds(1220,40,150,30);

		comboBoxQtdPosicionamento = new JComboBox<Integer>();
		comboBoxQtdPosicionamento.setBounds(1370,40,50,30);

		posicButton = new JButton("Add");
		posicButton.setBounds(1420,40,60,30);

		labelPosicionamento.setBounds(1230,10,200,30);
		add(labelPosicionamento);
		add(comboBoxPosicionamento);
		add(comboBoxQtdPosicionamento);
		add(posicButton);

		// Adiciona ação ao botão de posicionar
		posicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerente.clicouPosicionar(comboBoxPosicionamento.getSelectedItem().toString(), (Integer) comboBoxQtdPosicionamento.getSelectedItem());
			}
		});

		// ComboBox e label de atacantes
		comboBoxAtacantes = new JComboBox<String>();
		labelAtacantes.setBounds(1230,70,200,30);
		comboBoxAtacantes.setBounds(1220,100,200,30);
		dadosAtacante.setBounds(1420,100,50,30);
		add(labelAtacantes);
		add(dadosAtacante);    
		add(comboBoxAtacantes);

		// Adiciona ação ao selecionar um atacante
		comboBoxAtacantes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				gerente.selecionouAtacante((String) comboBoxAtacantes.getSelectedItem());
	        }
		}
		); 

		// ComboBox e label de defensores
		comboBoxDefensores = new JComboBox<String>();
		labelDefensores.setBounds(1230,130,200,30);
		comboBoxDefensores.setBounds(1220,160,200,30);
		dadosDefensor.setBounds(1420,160,50,30);
		add(labelDefensores);
		add(dadosDefensor);
		add(comboBoxDefensores);
		
		// ComboBox e label de reposicionar exércitos
		comboBoxOrigemRepos = new JComboBox<String>();
		labelReposicionamento.setBounds(1230,240,200,30);
		add(labelReposicionamento);
		comboBoxOrigemRepos.setBounds(1220,270,200,30);
		add(comboBoxOrigemRepos); 
		comboBoxQtdRepos = new JComboBox<Integer>();
		comboBoxQtdRepos.setBounds(1420,270,50,30);
		add(comboBoxQtdRepos);

		// Adiciona ação ao selecionar um território de origem
		comboBoxOrigemRepos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				gerente.selecionouOrigem((String) comboBoxOrigemRepos.getSelectedItem());
	        }
		}
		); 
		
		comboBoxDestinoRepos = new JComboBox<String>();
		comboBoxDestinoRepos.setBounds(1220,310,200,30);
		add(comboBoxDestinoRepos); 
		reposButton = new JButton("=>");
		reposButton.setBounds(1420,310,60,30);
		add(reposButton);

		// Adiciona ação ao clicar no botão de reposicionar
		reposButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxOrigemRepos.getSelectedItem() != null && comboBoxDestinoRepos.getSelectedItem() != null){
					gerente.clicouReposicionar(comboBoxOrigemRepos.getSelectedItem().toString(), comboBoxDestinoRepos.getSelectedItem().toString(), (Integer) comboBoxQtdRepos.getSelectedItem());
				}
			}
		});
		
	
		//Cria e adiciona o painel dos dados
		dadosPanel.setBounds(1250,350,200,200);
		add(dadosPanel);
		
		//Cria e adiciona os botões
		salvarButton = new JButton("Salvar o jogo");
		dadoButton = new JButton("Jogar os dados");
		nextButton = new JButton("Passar rodada");
		trocarCartasButton = new JButton("Trocar cartas");
		
		salvarButton.setBounds(50,20,200,30);
		dadoButton.setBounds(1250,200,200,30);
		nextButton.setBounds(980,730,200,30);
		trocarCartasButton.setBounds(1250,535,200,30);
		
		add(trocarCartasButton);
		add(salvarButton);
		add(dadoButton);
		add(nextButton);

		//Cria e adiciona o label do jogador da vez
		jogadorDaVezLabel.setFont(new Font("Arial", Font.BOLD, 20));
		jogadorDaVezLabel.setForeground(Color.WHITE);
		jogadorDaVezLabel.setBounds(640,660,200,30);
		add(jogadorDaVezLabel);

		//Adiciona ação ao clicar no botão de trocar cartas
		trocarCartasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerente.clicouTrocar();
			}
		});
		
		//Adiciona ação ao clicar no botão de salvar
		salvarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerente.clicouSalvar();
			}
		});
		
		//Adiciona ação ao clicar no botão de jogar os dados
		dadoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int [] dadosAtaque = new int [3];
				int [] dadosDefesa = new int [3];

				//Chama a função de jogar os dados
				int[] valoresDado = apiJogo.realizaAtaque(comboBoxAtacantes.getSelectedItem().toString(), comboBoxDefensores.getSelectedItem().toString(),Integer.valueOf((String)dadosAtacante.getSelectedItem()), Integer.valueOf((String)dadosDefensor.getSelectedItem()));
				dadosAtaque[0] = valoresDado[0];
				dadosAtaque[1] = valoresDado[1];
				dadosAtaque[2] = valoresDado[2];
				dadosDefesa[0] = valoresDado[3];
				dadosDefesa[1] = valoresDado[4];
				dadosDefesa[2] = valoresDado[5];
				
				//Mostra os dados na tela
				dadosPanel.mostrarDados(dadosAtaque, dadosDefesa);
			}
		});
		
		//Adiciona ação ao clicar no botão de passar rodada
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerente.clicouTerminarRodada();
			}
		});
		
		//Carrega a imagem do tabuleiro
		try {
			tabuleiroImg = ImageIO.read(new File("imagens/mapaComFundo.png"));
		}

		//Caso não consiga carregar a imagem, mostra uma mensagem de erro
		catch (IOException e) {
			System.out.println("Nao foi possivel carregar a imagem do tabuleiro");
			
		}
		
	}
	
	//Singleton
	public static GamePanel getGamePanel() {
		if (gamePanel == null) {
			gamePanel = new GamePanel();
		}
		return gamePanel;
	}
	
	//Desenha as imagens de tabuleiro e exércitos
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;

		//Desenha o fundo
		this.g2d.drawImage(tabuleiroImg, 0,0,1200,800,null);
		g2d.setColor(corDoJogador);
		g2d.fillOval(580, 650, 50, 50);

		//Desenha os exércitos
		jogadorDaVezLabel.setText(jogadorDaVez);
		if(ExercitosNaoCriados) {
			criaExercitos(g2d);
			ExercitosNaoCriados = false;
		}
		desenhaExercitos(this.g2d);

		//Desenha as cartas
		cartaObjPanel.desenhaCartas(g2d);
		//escrever o objetivo na carta usando drawString
		cartaObjPanel.escreveObjetivo(g2d, descricaoObjetivo);

		//Desenha as cartas do jogador da vez
		desenhaCartasJogador(g2d);
		
	}

	// Notifica o observador
	@Override
	public void notifica(ObservadoIF o){
		// Ao ser notificado, o observador recebe um objeto do tipo Object
		Object[] infos = (Object[]) o.get();

		// Conferindo se o objeto recebido é do tipo esperado, podemos converter os tipos
		if (infos[0] instanceof ArrayList<?> && infos[1] instanceof ArrayList<?> && infos[2] instanceof Integer && infos[3] instanceof Integer){
			ArrayList<String> qtds = (ArrayList<String>) infos[0];
			ArrayList<Color> cores = (ArrayList<Color>) infos[1];
			Integer mod1 = (Integer) infos[2];
			Integer mod2 = (Integer) infos[3];

			// Se nenhum território em específico foi modificado, então redesenha todos
			if (mod1 == -1 && mod2 == -1){
				int cont = 0;
				for(Exercitos e: listaExercitos){
					e.setQntExercitos(qtds.get(cont));
					e.setCor(cores.get(cont));
					cont++;
					//redesenhar todos os exércitos
					e.repaint();
					e.drawPlayer(g2d);
					e.repaint();
				}
			}
			// Se tiver específicos, redesenha apenas eles
			else{
				// Redesenha o primeiro modificado
				Exercitos e = listaExercitos.get(mod1);
				e.setQntExercitos(qtds.get(mod1));
				e.setCor(cores.get(mod1));
				e.repaint();
				e.drawPlayer(g2d);
				repaint();

				// Se tiver um segundo modificado, redesenha ele também
				if (mod2 != -1){
					e = listaExercitos.get(mod2);
					e.setQntExercitos(qtds.get(mod2));
					e.setCor(cores.get(mod2));
					e.repaint();
					e.drawPlayer(g2d);
				}
			}
		}
		
	}

	// Atualiza view no início da rodada de posicionamento para determinar o primeiro jogador
	public void determinaPrimeiroJogador(String jogadorDaVez, Color corDoJogador, String descricaoObj, String[] territorios, Integer qtd){
		// Muda informações da view relacionadas ao jogador da vez (para o primeiro jogador)
		this.jogadorDaVez = jogadorDaVez;
		this.corDoJogador = corDoJogador;
		this.descricaoObjetivo = descricaoObj;
		this.atualizaPosicionamento(territorios);
		this.atualizaQtdPosic(qtd);
	}

	// Muda o jogador da vez na view
	public void mudaJogador(String nome, Color cor, String descricaoObj, String[] cartas){
		// Muda informações da view relacionadas ao jogador da vez
		this.jogadorDaVez = nome;
		this.corDoJogador = cor;
		this.descricaoObjetivo = descricaoObj;
		this.cartas = cartas;
		repaint();
	}

	public void atualizaCartas(String[] cartas){
		this.cartas = cartas;
		repaint();
	}

	// Atualiza view no início da rodada de posicionamento para atualizar os jogadores atacantes
	public void atualizaAtacantes(String[] atacantes){
		// Esvazia a comboBox de atacantes e adiciona os novos territórios
		comboBoxAtacantes.removeAllItems();
		// Adiciona um item nulo para não ocupar o default e o jogador ter que selecionar um território
		comboBoxAtacantes.addItem(null);
		for (String s: atacantes){
			comboBoxAtacantes.addItem(s);
		}
	}

	// Atualiza os territórios a serem atacados a partir do território selecionado
	public void atualizaDefensores(String[] defensores){
		// Esvazia a comboBox de defensores e adiciona os novos territórios
		comboBoxDefensores.removeAllItems();
		for (String s: defensores){
			comboBoxDefensores.addItem(s);
		}
	}

	// Atualiza view no início da rodada de posicionamento para atualizar os territórios para posicionamento
	public void atualizaPosicionamento(String[] territorios){
		// Esvazia a comboBox de origem e adiciona os novos territórios
		comboBoxPosicionamento.removeAllItems();
		for (String s: territorios){
			comboBoxPosicionamento.addItem(s);
		}
	}

	// Atualiza view no início da rodada de reposicionamento para atualizar os territórios para reposicionamento
	public void atualizaReposicionamento(String[] territorios){
		// Esvazia a comboBox de origem e adiciona os novos territórios
		comboBoxOrigemRepos.removeAllItems();
		// Adiciona um item nulo para não ocupar o default e o jogador ter que selecionar um território
		comboBoxOrigemRepos.addItem(null);
		for (String s: territorios){
			comboBoxOrigemRepos.addItem(s);
		}
	}

	// Atualiza a quantidade de exércitos a serem posicionados
	public void atualizaQtdPosic(Integer qtd){
		comboBoxQtdPosicionamento.removeAllItems();
		for (Integer i = 0; i <= qtd; i++){
			comboBoxQtdPosicionamento.addItem(i);
		}
	}

	// Atualiza a quantidade de exércitos a serem reposicionados a partir do território selecionado
	public void atualizaQtdRepos(Integer qtd){
		comboBoxQtdRepos.removeAllItems();
		for (Integer i = 0; i <= qtd; i++){
			comboBoxQtdRepos.addItem(i);
		}
	}

	// Atualiza a comboBox de destinos de reposicionamento a partir do território selecionado
	public void atualizaDestinos(String[] destinos){
		comboBoxDestinoRepos.removeAllItems();
		for (String s: destinos){
			comboBoxDestinoRepos.addItem(s);
		}
	}

	// Muda a view para a rodada de posicionamento
	public void mudaParaPosicionamento(){
		// Deixa invisível elementos que não sejam de posicionamento
		labelAtacantes.setVisible(false);
		comboBoxAtacantes.setVisible(false);
		dadosAtacante.setVisible(false);
		labelDefensores.setVisible(false);
		comboBoxDefensores.setVisible(false);
		dadosDefensor.setVisible(false);
		dadoButton.setVisible(false);
		labelReposicionamento.setVisible(false);
		comboBoxOrigemRepos.setVisible(false);
		comboBoxDestinoRepos.setVisible(false);
		comboBoxQtdRepos.setVisible(false);
		reposButton.setVisible(false);

		// Deixa visível elementos da rodada de posicionamento
		labelPosicionamento.setVisible(true);
		comboBoxPosicionamento.setVisible(true);
		comboBoxQtdPosicionamento.setVisible(true);
		posicButton.setVisible(true);
	}

	// Muda a view para a rodada de ataque
	public void mudaParaAtaque(){
		// Remove elementos da rodada de posicionamento
		comboBoxPosicionamento.removeAllItems();
		comboBoxQtdPosicionamento.removeAllItems();
		labelPosicionamento.setVisible(false);
		comboBoxPosicionamento.setVisible(false);
		comboBoxQtdPosicionamento.setVisible(false);
		posicButton.setVisible(false);

		// Deixa visível elementos da rodada de ataque
		labelAtacantes.setVisible(true);
		comboBoxAtacantes.setVisible(true);
		dadosAtacante.setVisible(true);
		labelDefensores.setVisible(true);
		comboBoxDefensores.setVisible(true);
		dadosDefensor.setVisible(true);
		dadoButton.setVisible(true);
	}

	// Muda a view para a rodada de reposicionamento
	public void mudaParaReposicionamento(){
		// Remove elementos da rodada de ataque
		comboBoxAtacantes.removeAllItems();
		comboBoxDefensores.removeAllItems();
		labelAtacantes.setVisible(false);
		comboBoxAtacantes.setVisible(false);
		dadosAtacante.setVisible(false);
		labelDefensores.setVisible(false);
		comboBoxDefensores.setVisible(false);
		dadosDefensor.setVisible(false);
		dadoButton.setVisible(false);

		// Deixa visível elementos da rodada de reposicionamento
		labelReposicionamento.setVisible(true);
		comboBoxOrigemRepos.setVisible(true);
		comboBoxDestinoRepos.setVisible(true);
		comboBoxQtdRepos.setVisible(true);
		reposButton.setVisible(true);
	}
		
	// Desenha cada bolinha nos territórios
	void desenhaExercitos(Graphics2D g2d) {
		for (Exercitos e : listaExercitos) {
			e.drawPlayer(g2d);
		}
	}

	//Desenha as cartas do jogador da vez
	public void desenhaCartasJogador(Graphics2D g2d) {
		int x = 1210;
		int y = 575;
		int width = 70;
		int height = 110;

		//Se o jogador não tiver cartas, não desenha nada
		if (cartas == null) {
			return;
		}

		if(cartas.length == 0) {
			return;
		}

		int cont = 0;

		//Desenha as cartas
		for (String c : cartas) {
			cartaView.drawCarta(c, x, y, width, height, g2d);
			x+=85;
			cont++;
			if (cont == 3){
				// Reinicia o x e aumenta o y para desenhar as cartas lado a lado
				x = 1210;
				y += 115;
			}
		}
	}
	
	// Instancia os objetos dos exércitos
	void criaExercitos(Graphics2D g2d) {
		//Pega a lista de territórios
		this.territorios = gerente.getTerritoriosLista();
		Exercitos exercitos;
		
		//Verifica qual o território e desenha o exército na posição correta
		for (String t: territorios) { 
			switch(t){
				//América do Sul
				case "Brasil":
					exercitos = new Exercitos(355,471,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Argentina":
					exercitos = new Exercitos(319,580,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Peru":
					exercitos = new Exercitos(275,510,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Venezuela":
					exercitos = new Exercitos(232,452,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;

				//América do Norte
				case "Nova York":
					exercitos = new Exercitos(242,281,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Mexico":
					exercitos = new Exercitos(152,361,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "California":
					exercitos = new Exercitos(135,276,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Groelandia":
					exercitos = new Exercitos(383,120,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Alasca":
					exercitos = new Exercitos(114,148,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Vancouver":
					exercitos = new Exercitos(194,201,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Calgary":
					exercitos = new Exercitos(216,155,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Quebec":
					exercitos = new Exercitos(325,195,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Texas":
					exercitos = new Exercitos(185,282,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
					
				//Europa
				case "Polonia":
					exercitos = new Exercitos(666,209,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Franca":
					exercitos = new Exercitos(570,252,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Suecia":
					exercitos = new Exercitos(615,152,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Espanha":
					exercitos = new Exercitos(521,285,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Reino Unido":
					exercitos = new Exercitos(540,192,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Romenia":
					exercitos = new Exercitos(678,278,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Ucrania":
					exercitos = new Exercitos(696,244,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Italia":
					exercitos = new Exercitos(628,235,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				
				//África
				case "Egito":
					exercitos = new Exercitos(674,406,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Argelia":
					exercitos = new Exercitos(549,392,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Nigeria":
					exercitos = new Exercitos(622,459,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Somalia":
					exercitos = new Exercitos(731,494,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Angola":
					exercitos = new Exercitos(655,532,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Africa do Sul":
					exercitos = new Exercitos(679,590,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				
				//Ásia
				case "Estonia":
					exercitos = new Exercitos(784,150,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Letonia":
					exercitos = new Exercitos(770,199,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Russia":
					exercitos = new Exercitos(910,164,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Siberia":
					exercitos = new Exercitos(1032,157,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Turquia":
					exercitos = new Exercitos(860,255,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Cazaquistao":
					exercitos = new Exercitos(982,229,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Japao":
					exercitos = new Exercitos(1105,286,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Siria":
					exercitos = new Exercitos(776,298,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Paquistao":
					exercitos = new Exercitos(879,339,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "China":
					exercitos = new Exercitos(931,311,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Mongolia":
					exercitos = new Exercitos(1014,262,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Coreia do Norte":
					exercitos = new Exercitos(1012,315,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Coreia do Sul":
					exercitos = new Exercitos(1006,340,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Jordania":
					exercitos = new Exercitos(729,363,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Iraque":
					exercitos = new Exercitos(790,360,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Ira":
					exercitos = new Exercitos(846,358,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "India":
					exercitos = new Exercitos(936,401,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Bangladesh":
					exercitos = new Exercitos(984,392,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Tailandia":
					exercitos = new Exercitos(1048,386,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Arabia Saudita":
					exercitos = new Exercitos(796,426,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				
				//Oceania
				case "Australia":
					exercitos = new Exercitos(1034,629,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Indonesia":
					exercitos = new Exercitos(1053,520,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Perth":
					exercitos = new Exercitos(951,616,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
				case "Nova Zelandia":
					exercitos = new Exercitos(1087,672,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
					break;
					
				default:
					exercitos = new Exercitos(0,0,gerente.getCorTerritorio(t), gerente.getQtdExercitos(t).toString());
			
			}
			
			//Desenha o território
            exercitos.drawPlayer(g2d);
			//Adiciona exército na lista
			listaExercitos.add(exercitos);
		}
	}
}
