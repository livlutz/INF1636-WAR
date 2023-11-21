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

	//Componente grafica
	Graphics2D g2d; 
	
	//Botoes e comboboxes
	JButton salvarButton,dadoButton, nextButton;
	JComboBox comboBoxAtacante,comboBoxDefensor;
	
	//Lista de territorios no jogo
	String[] territorios;

	// Painel dos dados
	DadosPanel dadosPanel = new DadosPanel();
	
	//Painel das Cartas
	CartasPanel painelCartas = new CartasPanel();

	// Gerente
	Gerente gerente = Gerente.getGerente();

	// ComboBoxes
	JComboBox<String> comboBoxAtacantes = new ComboBoxPaises();
	JComboBox<String> comboBoxDefensores = new ComboBoxPaises();
	String valoresDados[] = {"0","1","2","3","4","5","6"};
	JComboBox dadosAtacante = new JComboBox(valoresDados);
	JComboBox dadosDefensores = new JComboBox(valoresDados);
	APIJogo apiJogo = APIJogo.getAPIJogo();

	//Array list de exercitos 
	ArrayList<Exercitos> listExercitos = new ArrayList<Exercitos>();

	//Construtor
	private GamePanel() {
		//Define o layout como null para poder posicionar os componentes
		setLayout(null);

		//Cria e adiciona os comboBoxes
		JLabel atacantes = new JLabel("Atacantes");
		atacantes.setBounds(1250,120,200,30);
		add(atacantes);
		//JComboBox<String> comboBoxAtacantes = new ComboBoxPaises();
		comboBoxAtacantes.setBounds(1250,150,200,30);
		dadosAtacante.setBounds(1450,150,50,30);
		add(dadosAtacante);	
		add(comboBoxAtacantes);
		
		JLabel defensores = new JLabel("Defensores");
		defensores.setBounds(1250,200,200,30);
		add(defensores);
		//JComboBox<String> comboBoxDefensores = new ComboBoxPaises();
		comboBoxDefensores.setBounds(1250,230,200,30);
		dadosDefensores.setBounds(1450,230,50,30);
		add(dadosDefensores);
		add(comboBoxDefensores);
		
		//Cria e adiciona o painel dos dados
		dadosPanel.setBounds(1250,350,200,200);
		add(dadosPanel);

		//Cria e adiciona o painel das cartas
		painelCartas.setBounds(1250,580,200,200);
		add(painelCartas);
		
		//Cria e adiciona os botões
		String filePath = new File("").getAbsolutePath();
		salvarButton = new JButton("Salvar o jogo");
		dadoButton = new JButton("Jogar os dados");
		nextButton = new JButton("Terminar a rodada");
		
		salvarButton.setBounds(1250,20,200,30);
		dadoButton.setBounds(1250,290,200,30);
		nextButton.setBounds(1250,80,200,30);
		
		add(salvarButton);
		add(dadoButton);
		add(nextButton);
		
		//Adiciona ações aos botões
		salvarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO salvar jogo
			}
		});
		
		dadoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int [] dadosAtaque = new int [3];
				int [] dadosDefesa = new int [3];
				if (dadosAtacante.getSelectedItem() == "0" && dadosDefensores.getSelectedItem() == "0") {
					//apiJogo.realizaAtaque(null, comboBoxAtacantes.getSelectedItem(), comboBoxDefensores.getSelectedItem(), dadosAtaque, dadosDefesa);
				}
				else {
					//apiJogo.realizaAtaqueForçado(null, comboBoxAtacantes.getSelectedItem(), comboBoxDefensores.getSelectedItem(),Integer.valueOf(dadosAtacante.getSelectedItem()), Integer.valueOf(dadosDefensores.getSelectedItem()));
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
				//TODO passar a rodada
			}
		});
		
		//territorios = getTerritoriosLista();
		
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
	
	//Desenha as imagens de tabuleiro e exercitos
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;
		this.g2d.drawImage(tabuleiroImg, 0,0,1200,800,null);
		desenhaExercitos(this.g2d);
	}

	// Array de infos para observer [int, int, int, int, int, String, String, int[]]
	// 0 - Estado para indicar telas (Posicionando = 0; Atacando = 1; Reposicionamento = 2; Passando a vez = 3; Fim de jogo = 4)
	// 1 - Vez (Int pos do array de nomesJogadores)
	// 2 - Realizando posicionamento - Estado (Não está realizando = 0; Selecionou territorio = 1; Posicionamento terminado = 2)
	// 3 - Realizando ataque - Estado (Não está realizando = 0; Selecionou atacante = 1; Selecionou defensor = 2; Rolou Dados = 3; Ataque terminado = 4)
	// 4 - Realizando reposicionamento - Estado (Não está realizando = 0; Selecionou origem = 1; Selecionou destino = 2; Reposicionamento terminado = 3)
	// 5 - String com o nome do territorio principal/origem (Atacante, origem de reposicionamento e receptor de posicionamento de exércitos)
	// 6 - String com o nome do territorio destino (Defensor ou destino de reposicionamento)
	// 7 - Array de int com os resultados dos dados (Primeiros 3 ataque, últimos 3 defesa) - 0 = não rolado, -1 = não pode jogar esse dado, 1-6 = valor do dado
	
	// Notifica o observador
	@Override
	public void notifica(ObservadoIF o){
		APIView apiView = (APIView) o;
		Object[] infos = (Object[]) apiView.get();
		if ((int) infos[0] == 0){
			// Posicionamento
		}
		else if ((int) infos[0] == 1){
			// Ataque
		}
		else if ((int) infos[0] == 2){
			// Reposicionamento
		}
		else if ((int) infos[0] == 3){
			// Passando a vez
		}
		else if ((int) infos[0] == 4){
			// Fim de jogo
		}
	}

	//desenha cada territorio 
	void desenhaExercitos(Graphics2D g2d) {
		//Pega a lista de territorios
		this.territorios = gerente.getTerritoriosLista();
		Exercitos exercitos;
		//Verifica qual o territorio e desenha o exercito na posicao correta
		for (String t: territorios) { 
			switch(t){
				//America do Sul
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

				//America do Norte
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
				
				//Africa
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
				
				//Asia
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
			
			//desenha o territorio
            exercitos.drawPlayer(g2d);
			//Adiciona exercito na lista
			listExercitos.add(exercitos);
		}
	}
	
	
		
}
