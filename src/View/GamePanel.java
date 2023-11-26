package View;
import Controller.Gerente;
import Model.APIJogo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	Image cartaObjGrande = null;

	//Componente gráfica
	Graphics2D g2d; 
	
	//Botões e comboboxes
	JButton salvarButton, dadoButton, nextButton, trocarCartasButton, posicButton, addExercito, destinoExercito;
	JComboBox comboBoxAtacante,comboBoxDefensor;
	
	//Lista de territórios no jogo
	String[] territorios;

	//Painel dos dados
	DadosPanel dadosPanel = new DadosPanel();
	
	//Painel das Cartas
	CartaObjPanel cartaObjPanel = new CartaObjPanel();

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
	JComboBox<String> comboBoxOrigemExercitos;
	JComboBox<String> comboBoxDestinoExercitos;
	JLabel labelReposicionamento = new JLabel("Selecione para reposicionar");
	
	String valoresDados[] = {"0","1","2","3","4","5","6"};
	JComboBox dadosAtacante = new JComboBox(valoresDados);
	JComboBox dadosDefensores = new JComboBox(valoresDados);
	APIJogo apiJogo = APIJogo.getAPIJogo();

	//Jogador da vez e cor do jogador
	String jogadorDaVez;
	Color corDoJogador;
	String descricaoObjetivo;
	JLabel jogadorDaVezLabel = new JLabel();

	//Array list de exércitos 
	ArrayList<Exercitos> listaExercitos = new ArrayList<Exercitos>();

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
		dadosDefensores.setBounds(1420,160,50,30);
		add(labelDefensores);
		add(dadosDefensores);
		add(comboBoxDefensores);
		
		// ComboBox e label de colocar e mover exércitos
		comboBoxOrigemExercitos = new JComboBox<String>();
		labelReposicionamento.setBounds(1230,240,200,30);
		add(labelReposicionamento);
		comboBoxOrigemExercitos.setBounds(1220,270,200,30);
		add(comboBoxOrigemExercitos); 
		addExercito = new JButton("+1");
		addExercito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		addExercito.setBounds(1420,270,60,30);
		add(addExercito);
		
		comboBoxDestinoExercitos = new JComboBox<String>();
		comboBoxDestinoExercitos.setBounds(1220,310,200,30);
		add(comboBoxDestinoExercitos); 
		destinoExercito = new JButton("->");
		destinoExercito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		destinoExercito.setBounds(1420,310,60,30);
		add(destinoExercito);
	
		//Cria e adiciona o painel dos dados
		dadosPanel.setBounds(1250,350,200,200);
		add(dadosPanel);

		//Cria e adiciona o painel das cartas
		cartaObjPanel.setBounds(1250,580,200,200);
		add(cartaObjPanel);
		
		//Cria e adiciona os botões
		String filePath = new File("").getAbsolutePath();
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
		
		trocarCartasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		salvarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerente.clicouSalvar();
			}
		});
		
		dadoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int [] dadosAtaque = new int [3];
				int [] dadosDefesa = new int [3];
				if (dadosAtacante.getSelectedItem() == "0" && dadosDefensores.getSelectedItem() == "0") {
					apiJogo.realizaAtaque(comboBoxAtacantes.getSelectedItem().toString(), comboBoxDefensores.getSelectedItem().toString(), dadosAtaque, dadosDefesa);
				}
				else {
					apiJogo.realizaAtaqueForcado(comboBoxAtacantes.getSelectedItem().toString(), comboBoxDefensores.getSelectedItem().toString(),Integer.valueOf((String)dadosAtacante.getSelectedItem()), Integer.valueOf((String)dadosDefensores.getSelectedItem()));
					int dadoAtaque = Integer.valueOf((String) dadosAtacante.getSelectedItem());
					dadosAtaque[0] = dadoAtaque;
					dadosAtaque[1] = dadoAtaque;
					dadosAtaque[2] = dadoAtaque;
					int dadoDefesa = Integer.valueOf((String) dadosDefensores.getSelectedItem());
					dadosDefesa[0] = dadoDefesa;
					dadosDefesa[1] = dadoDefesa;
					dadosDefesa[2] = dadoDefesa;
				}
				dadosPanel.mostrarDados(dadosAtaque, dadosDefesa);
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerente.clicouTerminarRodada();
			}
		});
		
		//Carrega a imagem do tabuleiro
		try {
			tabuleiroImg = ImageIO.read(new File("images/mapaComFundo.png"));
		}
		catch (IOException e) {
			System.out.println("Nao foi possivel carregar a imagem do tabuleiro");
			
		}
		//System.out.println(filePath);
		/*addMouseListener(new MouseListener() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	int x=e.getX();
		        int y=e.getY();
		        System.out.println(x+","+y);//these co-ords are relative to the component
		    }

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		*/
		
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
		this.g2d.drawImage(tabuleiroImg, 0,0,1200,800,null);
		g2d.setColor(corDoJogador);
		g2d.fillOval(580, 650, 50, 50);
		jogadorDaVezLabel.setText(jogadorDaVez);
		desenhaExercitos(this.g2d);
		cartaObjPanel.desenhaCartas(g2d);
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
					e.drawPlayer(g2d);
					cont++;
				}
			}
			// Se tiver específicos, redesenha apenas eles
			else{
				Exercitos e = listaExercitos.get(mod1);
				e.setQntExercitos(qtds.get(mod1));
				e.setCor(cores.get(mod1));
				e.drawPlayer(g2d);
				if (mod2 != -1){
					e = listaExercitos.get(mod2);
					e.setQntExercitos(qtds.get(mod2));
					e.setCor(cores.get(mod2));
					e.drawPlayer(g2d);
				}
			}
		}
		
	}

	// Atualiza view no início da rodada de posicionamento para determinar o primeiro jogador
	public void determinaPrimeiroJogador(String jogadorDaVez, Color corDoJogador, String descricaoObj, String[] territorios, Integer qtd){
		// Adicionar frase "Primeiro jogador: NOME - COR"
		// Adicionar descrição do objetivo em cima da carta
		// NÃO IMPRIMIR AINDA, SÓ ADD PARA IMPRIMIR AO CHAMAR O DRAWCOMPONENT DO MAINFRAME
		this.jogadorDaVez = jogadorDaVez;
		this.corDoJogador = corDoJogador;
		this.descricaoObjetivo = descricaoObj;
		this.atualizaPosicionamento(territorios);
		this.atualizaQtdPosic(qtd);
	}

	// Atualiza view no início da rodada de posicionamento para mudar jogador
	//public void mudaJogador(String nome, String cor, String descricaoObj, Cartas[] cartas){
		//TODO: muda jogador
		// Tira frase, descrição e cartas do jogador anterior 
		// Coloca frase de "Vez de NOME - COR"
		// Coloca descrição do objetivo em cima da carta
		//
		// Imprime as cartas do jogador
		
	//}

	// Atualiza view no início da rodada de posicionamento para atualizar os jogadores atacantes
	public void atualizaAtacantes(String[] atacantes){
		// Adiciona um item nulo para não ocupar o default e o jogador ter que selecionar um território
		comboBoxAtacantes.addItem(null);
		for (String s: atacantes){
			comboBoxAtacantes.addItem(s);
		}
	}

	// Atualiza os territórios a serem atacados
	public void atualizaDefensores(String[] defensores){
		// Esvazia a comboBox de defensores e adiciona os novos territórios
		comboBoxDefensores.removeAllItems();
		for (String s: defensores){
			comboBoxDefensores.addItem(s);
		}
	}

	// Atualiza view no início da rodada de posicionamento para atualizar os territórios para posicionamento
	public void atualizaPosicionamento(String[] territorios){
		for (String s: territorios){
			comboBoxPosicionamento.addItem(s);
		}
	}

	// Atualiza view no início da rodada de reposicionamento para atualizar os territórios para reposicionamento
	public void atualizaReposicionamento(String[] territorios){
		for (String s: territorios){
			comboBoxOrigemExercitos.addItem(s);
		}
	}

	public void atualizaQtdPosic(Integer qtd){
		comboBoxQtdPosicionamento.removeAllItems();
		for (Integer i = 0; i <= qtd; i++){
			comboBoxQtdPosicionamento.addItem(i);
		}
	}

	public void mudaParaPosicionamento(){
		// Deixa invisível elementos que não sejam de posicionamento
		labelAtacantes.setVisible(false);
		comboBoxAtacantes.setVisible(false);
		dadosAtacante.setVisible(false);
		labelDefensores.setVisible(false);
		comboBoxDefensores.setVisible(false);
		dadosDefensores.setVisible(false);
		dadoButton.setVisible(false);

		labelReposicionamento.setVisible(false);
		comboBoxOrigemExercitos.setVisible(false);
		comboBoxDestinoExercitos.setVisible(false);
		addExercito.setVisible(false);
		destinoExercito.setVisible(false);

		// Deixa visível elementos da rodada de posicionamento
		labelPosicionamento.setVisible(true);
		comboBoxPosicionamento.setVisible(true);
		comboBoxQtdPosicionamento.setVisible(true);
		posicButton.setVisible(true);
	}

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
		dadosDefensores.setVisible(true);
		dadoButton.setVisible(true);
	}

	public void mudaParaReposicionamento(){
		// Remove elementos da rodada de ataque
		comboBoxAtacantes.removeAllItems();
		comboBoxDefensores.removeAllItems();
		labelAtacantes.setVisible(false);
		comboBoxAtacantes.setVisible(false);
		dadosAtacante.setVisible(false);
		labelDefensores.setVisible(false);
		comboBoxDefensores.setVisible(false);
		dadosDefensores.setVisible(false);
		dadoButton.setVisible(false);

		// Deixa visível elementos da rodada de reposicionamento
		labelReposicionamento.setVisible(true);
		comboBoxOrigemExercitos.setVisible(true);
		comboBoxDestinoExercitos.setVisible(true);
		addExercito.setVisible(true);
		destinoExercito.setVisible(true);
	}

	//Desenha cada território 
	void desenhaExercitos(Graphics2D g2d) {
		//Pega a lista de territórios
		this.territorios = gerente.getTerritoriosLista();
		Exercitos exercitos;
		//Verifica qual o território e desenha o exército na posição correta
		for (String t: territorios) { 
			switch(t){
				//América do Sul
				case "Brasil":
					exercitos = new Exercitos(355,471,gerente.getCorTerritorio(t));
					break;
				case "Argentina":
					exercitos = new Exercitos(319,580,gerente.getCorTerritorio(t));
					break;
				case "Peru":
					exercitos = new Exercitos(275,510,gerente.getCorTerritorio(t));
					break;
				case "Venezuela":
					exercitos = new Exercitos(232,452,gerente.getCorTerritorio(t));
					break;

				//América do Norte
				case "Nova York":
					exercitos = new Exercitos(242,281,gerente.getCorTerritorio(t));
					break;
				case "Mexico":
					exercitos = new Exercitos(152,361,gerente.getCorTerritorio(t));
					break;
				case "California":
					exercitos = new Exercitos(135,276,gerente.getCorTerritorio(t));
					break;
				case "Groelandia":
					exercitos = new Exercitos(383,120,gerente.getCorTerritorio(t));
					break;
				case "Alasca":
					exercitos = new Exercitos(114,148,gerente.getCorTerritorio(t));
					break;
				case "Vancouver":
					exercitos = new Exercitos(194,201,gerente.getCorTerritorio(t));
					break;
				case "Calgary":
					exercitos = new Exercitos(216,155,gerente.getCorTerritorio(t));
					break;
				case "Quebec":
					exercitos = new Exercitos(325,195,gerente.getCorTerritorio(t));
					break;
				case "Texas":
					exercitos = new Exercitos(185,282,gerente.getCorTerritorio(t));
					break;
					
				//Europa
				case "Polonia":
					exercitos = new Exercitos(666,209,gerente.getCorTerritorio(t));
					break;
				case "Franca":
					exercitos = new Exercitos(570,252,gerente.getCorTerritorio(t));
					break;
				case "Suecia":
					exercitos = new Exercitos(615,152,gerente.getCorTerritorio(t));
					break;
				case "Espanha":
					exercitos = new Exercitos(521,285,gerente.getCorTerritorio(t));
					break;
				case "Reino Unido":
					exercitos = new Exercitos(540,192,gerente.getCorTerritorio(t));
					break;
				case "Romania":
					exercitos = new Exercitos(678,278,gerente.getCorTerritorio(t));
					break;
				case "Ucrania":
					exercitos = new Exercitos(696,244,gerente.getCorTerritorio(t));
					break;
				case "Italia":
					exercitos = new Exercitos(628,235,gerente.getCorTerritorio(t));
					break;
				
				//África
				case "Egito":
					exercitos = new Exercitos(674,406,gerente.getCorTerritorio(t));
					break;
				case "Argelia":
					exercitos = new Exercitos(549,392,gerente.getCorTerritorio(t));
					break;
				case "Nigeria":
					exercitos = new Exercitos(622,459,gerente.getCorTerritorio(t));
					break;
				case "Somalia":
					exercitos = new Exercitos(731,494,gerente.getCorTerritorio(t));
					break;
				case "Angola":
					exercitos = new Exercitos(655,532,gerente.getCorTerritorio(t));
					break;
				case "Africa do Sul":
					exercitos = new Exercitos(679,590,gerente.getCorTerritorio(t));
					break;
				
				//Ásia
				case "Estonia":
					exercitos = new Exercitos(784,150,gerente.getCorTerritorio(t));
					break;
				case "Letonia":
					exercitos = new Exercitos(770,199,gerente.getCorTerritorio(t));
					break;
				case "Russia":
					exercitos = new Exercitos(910,164,gerente.getCorTerritorio(t));
					break;
				case "Siberia":
					exercitos = new Exercitos(1032,157,gerente.getCorTerritorio(t));
					break;
				case "Turquia":
					exercitos = new Exercitos(860,255,gerente.getCorTerritorio(t));
					break;
				case "Cazaquistao":
					exercitos = new Exercitos(982,229,gerente.getCorTerritorio(t));
					break;
				case "Japao":
					exercitos = new Exercitos(1105,286,gerente.getCorTerritorio(t));
					break;
				case "Siria":
					exercitos = new Exercitos(776,298,gerente.getCorTerritorio(t));
					break;
				case "Paquistao":
					exercitos = new Exercitos(879,339,gerente.getCorTerritorio(t));
					break;
				case "China":
					exercitos = new Exercitos(931,311,gerente.getCorTerritorio(t));
					break;
				case "Mongolia":
					exercitos = new Exercitos(1014,262,gerente.getCorTerritorio(t));
					break;
				case "Coreia do Norte":
					exercitos = new Exercitos(1012,315,gerente.getCorTerritorio(t));
					break;
				case "Coreia do Sul":
					exercitos = new Exercitos(1006,340,gerente.getCorTerritorio(t));
					break;
				case "Jordania":
					exercitos = new Exercitos(729,363,gerente.getCorTerritorio(t));
					break;
				case "Iraque":
					exercitos = new Exercitos(790,360,gerente.getCorTerritorio(t));
					break;
				case "Ira":
					exercitos = new Exercitos(846,358,gerente.getCorTerritorio(t));
					break;
				case "India":
					exercitos = new Exercitos(936,401,gerente.getCorTerritorio(t));
					break;
				case "Bangladesh":
					exercitos = new Exercitos(984,392,gerente.getCorTerritorio(t));
					break;
				case "Tailandia":
					exercitos = new Exercitos(1048,386,gerente.getCorTerritorio(t));
					break;
				case "Arabia Saudita":
					exercitos = new Exercitos(796,426,gerente.getCorTerritorio(t));
					break;
				
				//Oceania
				case "Australia":
					exercitos = new Exercitos(1034,629,gerente.getCorTerritorio(t));
					break;
				case "Indonesia":
					exercitos = new Exercitos(1053,520,gerente.getCorTerritorio(t));
					break;
				case "Perth":
					exercitos = new Exercitos(951,616,gerente.getCorTerritorio(t));
					break;
				case "Nova Zelandia":
					exercitos = new Exercitos(1087,672,gerente.getCorTerritorio(t));
					break;
					
				default:
					exercitos = new Exercitos(0,0,gerente.getCorTerritorio(t));
			
			}
			
			//Desenha o território
            exercitos.drawPlayer(g2d);
			//Adiciona exército na lista
			listaExercitos.add(exercitos);
		}
	}
}
