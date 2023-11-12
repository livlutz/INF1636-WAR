package View;
import Model.APIJogo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements ObservadoIF {
	public static GamePanel gamePanel = null;
	
	//Imagens do tabuleiro, fundo e cartas de objetivo
	Image tabuleiroImg = null;
	Image background = null;
	Image cartaObj = null;
	Image cartaObjGrande = null;

	Graphics2D g2d; //Componente grafica
	
	// Chamando API aqui enquanto Controller não está pronto
	APIJogo	apiJogo = APIJogo.getAPIJogo();
	
	//Botoes e comboboxes
	JButton salvarButton,dadoButton, nextButton;
	JComboBox comboBoxAtacante,comboBoxDefensor;
	
	//Lista de territorios no jogo
	String[] territorios;

	// Observadores
	private ArrayList<ObservadorIF> observadores = new ArrayList<ObservadorIF>();

	// Painel dos dados
	DadosPanel painelDosDados = new DadosPanel();
	
	//Painel das Cartas
	CartasPanel painelCartas = new CartasPanel();

	// Nomes dos jogadores
	String[] nomesJogadores;

	// Array de infos para observer [int, int, int, int, int, String, String, int[]]
	// 0 - Estado para indicar telas (Começando agora = -1; Posicionando = 0; Atacando = 1; Reposicionamento = 2; Passando a vez = 3; Fim de jogo = 4)
	// 1 - Vez (Int pos do array de nomesJogadores)
	// 2 - Realizando posicionamento - Estado (Não está realizando = 0; Selecionou territorio = 1; Posicionamento terminado = 2)
	// 3 - Realizando ataque - Estado (Não está realizando = 0; Selecionou atacante = 1; Selecionou defensor = 2; Rolou Dados = 3; Ataque terminado = 4)
	// 4 - Realizando reposicionamento - Estado (Não está realizando = 0; Selecionou origem = 1; Selecionou destino = 2; Reposicionamento terminado = 3)
	// 5 - String com o nome do territorio principal/origem (Atacante, origem de reposicionamento e receptor de posicionamento de exércitos)
	// 6 - String com o nome do territorio destino (Defensor ou destino de reposicionamento)
	// 7 - Array de int com os resultados dos dados (Primeiros 3 ataque, últimos 3 defesa) - 0 = não rolado, -1 = não pode jogar esse dado, 1-6 = valor do dado

	Object[] infos = new Object[8];

	//Construtor
	private GamePanel() {
		setLayout(null);

		JLabel atacantes = new JLabel("Atacantes");
		atacantes.setBounds(1250,150,200,30);
		add(atacantes);
		JComboBox<String> comboBoxAtacantes = new ComboBoxPaises();
		comboBoxAtacantes.setBounds(1250,180,200,30);
		add(comboBoxAtacantes);
		
		JLabel defensores = new JLabel("Defensores");
		defensores.setBounds(1250,230,200,30);
		add(defensores);
		JComboBox<String> comboBoxDefensores = new ComboBoxPaises();
		comboBoxDefensores.setBounds(1250,260,200,30);
		add(comboBoxDefensores);
		
		painelDosDados.setBounds(1250,400,200,200);
		add(painelDosDados);

		painelCartas.setBounds(1220,400,200,200);
		add(painelCartas);
		
		String filePath = new File("").getAbsolutePath();
		salvarButton = new JButton("Salvar o jogo");
		dadoButton = new JButton("Jogar os dados");
		nextButton = new JButton("Terminar a rodada");
		
		salvarButton.setBounds(1250,20,200,30);
		dadoButton.setBounds(1250, 300,200,30);
		nextButton.setBounds(1250, 80,200,30);
		
		add(salvarButton);
		add(dadoButton);
		add(nextButton);
		
		salvarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO salvar jogo
			}
		});
		
		dadoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO lançar os dados
			}
		});
		
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO passar a rodada
			}
		});
		
		//territorios = getTerritoriosLista();
		
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
	
	//Desenha as imagens de tabuleiro e fundo
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g2d = (Graphics2D) g;
		this.g2d.drawImage(tabuleiroImg, 0,0,1200,800,null);
		desenhaExercitos(this.g2d);
	}

	//desenha cada territorio 
	void desenhaExercitos(Graphics2D g2d) {
		this.territorios = apiJogo.getTerritoriosLista();
		Exercitos exercitos;
		for (String t: territorios) { 
			switch(t){
				//America do Sul
				case "Brasil":
					exercitos = new Exercitos(355,471,apiJogo.getCorTerritorio(t));
					break;
				case "Argentina":
					exercitos = new Exercitos(319,580,apiJogo.getCorTerritorio(t));
					break;
				case "Peru":
					exercitos = new Exercitos(275,510,apiJogo.getCorTerritorio(t));
					break;
				case "Venezuela":
					exercitos = new Exercitos(232,452,apiJogo.getCorTerritorio(t));
					break;

				//America do Norte
				case "Nova York":
					exercitos = new Exercitos(242,281,apiJogo.getCorTerritorio(t));
					break;
				case "Mexico":
					exercitos = new Exercitos(152,361,apiJogo.getCorTerritorio(t));
					break;
				case "California":
					exercitos = new Exercitos(135,276,apiJogo.getCorTerritorio(t));
					break;
				case "Groelandia":
					exercitos = new Exercitos(383,120,apiJogo.getCorTerritorio(t));
					break;
				case "Alasca":
					exercitos = new Exercitos(114,148,apiJogo.getCorTerritorio(t));
					break;
				case "Vancouver":
					exercitos = new Exercitos(194,201,apiJogo.getCorTerritorio(t));
					break;
				case "Calgary":
					exercitos = new Exercitos(216,155,apiJogo.getCorTerritorio(t));
					break;
				case "Quebec":
					exercitos = new Exercitos(325,195,apiJogo.getCorTerritorio(t));
					break;
				case "Texas":
					exercitos = new Exercitos(185,282,apiJogo.getCorTerritorio(t));
					break;
					
				//Europa
				case "Polonia":
					exercitos = new Exercitos(666,209,apiJogo.getCorTerritorio(t));
					break;
				case "Franca":
					exercitos = new Exercitos(570,252,apiJogo.getCorTerritorio(t));
					break;
				case "Suecia":
					exercitos = new Exercitos(615,152,apiJogo.getCorTerritorio(t));
					break;
				case "Espanha":
					exercitos = new Exercitos(521,285,apiJogo.getCorTerritorio(t));
					break;
				case "Reino Unido":
					exercitos = new Exercitos(540,192,apiJogo.getCorTerritorio(t));
					break;
				case "Romania":
					exercitos = new Exercitos(678,278,apiJogo.getCorTerritorio(t));
					break;
				case "Ucrania":
					exercitos = new Exercitos(696,244,apiJogo.getCorTerritorio(t));
					break;
				case "Italia":
					exercitos = new Exercitos(628,235,apiJogo.getCorTerritorio(t));
					break;
				
				//Africa
				case "Egito":
					exercitos = new Exercitos(674,406,apiJogo.getCorTerritorio(t));
					break;
				case "Argelia":
					exercitos = new Exercitos(549,392,apiJogo.getCorTerritorio(t));
					break;
				case "Nigeria":
					exercitos = new Exercitos(622,459,apiJogo.getCorTerritorio(t));
					break;
				case "Somalia":
					exercitos = new Exercitos(731,494,apiJogo.getCorTerritorio(t));
					break;
				case "Angola":
					exercitos = new Exercitos(655,532,apiJogo.getCorTerritorio(t));
					break;
				case "Africa do Sul":
					exercitos = new Exercitos(679,590,apiJogo.getCorTerritorio(t));
					break;
				
				//Asia
				case "Estonia":
					exercitos = new Exercitos(784,150,apiJogo.getCorTerritorio(t));
					break;
				case "Letonia":
					exercitos = new Exercitos(770,199,apiJogo.getCorTerritorio(t));
					break;
				case "Russia":
					exercitos = new Exercitos(910,164,apiJogo.getCorTerritorio(t));
					break;
				case "Siberia":
					exercitos = new Exercitos(1032,157,apiJogo.getCorTerritorio(t));
					break;
				case "Turquia":
					exercitos = new Exercitos(860,255,apiJogo.getCorTerritorio(t));
					break;
				case "Cazaquistao":
					exercitos = new Exercitos(982,229,apiJogo.getCorTerritorio(t));
					break;
				case "Japao":
					exercitos = new Exercitos(1105,286,apiJogo.getCorTerritorio(t));
					break;
				case "Siria":
					exercitos = new Exercitos(776,298,apiJogo.getCorTerritorio(t));
					break;
				case "Paquistao":
					exercitos = new Exercitos(879,339,apiJogo.getCorTerritorio(t));
					break;
				case "China":
					exercitos = new Exercitos(931,311,apiJogo.getCorTerritorio(t));
					break;
				case "Mongolia":
					exercitos = new Exercitos(1014,262,apiJogo.getCorTerritorio(t));
					break;
				case "Coreia do Norte":
					exercitos = new Exercitos(1012,315,apiJogo.getCorTerritorio(t));
					break;
				case "Coreia do Sul":
					exercitos = new Exercitos(1006,340,apiJogo.getCorTerritorio(t));
					break;
				case "Jordania":
					exercitos = new Exercitos(729,363,apiJogo.getCorTerritorio(t));
					break;
				case "Iraque":
					exercitos = new Exercitos(790,360,apiJogo.getCorTerritorio(t));
					break;
				case "Ira":
					exercitos = new Exercitos(846,358,apiJogo.getCorTerritorio(t));
					break;
				case "India":
					exercitos = new Exercitos(936,401,apiJogo.getCorTerritorio(t));
					break;
				case "Bangladesh":
					exercitos = new Exercitos(984,392,apiJogo.getCorTerritorio(t));
					break;
				case "Tailandia":
					exercitos = new Exercitos(1048,386,apiJogo.getCorTerritorio(t));
					break;
				case "Arabia Saudita":
					exercitos = new Exercitos(796,426,apiJogo.getCorTerritorio(t));
					break;
				
				//Oceania
				case "Australia":
					exercitos = new Exercitos(1034,629,apiJogo.getCorTerritorio(t));
					break;
				case "Indonesia":
					exercitos = new Exercitos(1053,520,apiJogo.getCorTerritorio(t));
					break;
				case "Perth":
					exercitos = new Exercitos(951,616,apiJogo.getCorTerritorio(t));
					break;
				case "Nova Zelandia":
					exercitos = new Exercitos(1087,672,apiJogo.getCorTerritorio(t));
					break;
					
				default:
					exercitos = new Exercitos(0,0,apiJogo.getCorTerritorio(t));
			
			}
			
			//desenha o territorio
            exercitos.drawPlayer(g2d);
            
		}
	}
	

	@Override
	public void add(ObservadorIF o) {
		observadores.add(o);
	}

	@Override
	public void remove(ObservadorIF o) {
		observadores.remove(o);
	}

	@Override
	public Object get() {
		// TODO 
		return null;
	}
		
}
