package View;
import Model.Tabuleiro;
import Model.Territorio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel {
	public static GamePanel gamePanel = null;
	Image tabuleiroImg = null;
	Image background = null;
	Graphics2D g2d;
	Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
	JButton salvarButton,dadoButton, nextButton;
	JComboBox comboBoxAtacante,comboBoxDefensor;
	String[] territorios;
	private GamePanel() {
		String filePath = new File("").getAbsolutePath();
		salvarButton = new JButton("Salvar o jogo");
		dadoButton = new JButton("Jogar os dados");
		nextButton = new JButton("Terminar a rodada");
		
		salvarButton.setLocation(1250,20);
		dadoButton.setLocation(1250, 40);
		nextButton.setLocation(1250,60);
		
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
			background = ImageIO.read(new File("images/war_tabuleiro_fundo.png"));
			
		}
		catch (IOException e) {
			System.out.println("Erro na leitura do plano de fundo");
		}
		
		try {
			tabuleiroImg = ImageIO.read(new File("images/war_tabuleiro_mapa.png"));
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
	
	public static GamePanel getGamePanel() {
		if (gamePanel == null) {
			gamePanel = new GamePanel();
		}
		return gamePanel;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		g2d.drawImage(background, 0,0,1200,800,null);
		g2d.drawImage(tabuleiroImg, 0,0,1200,800,null);
		desenhaExercitos(g2d);
	}

	void desenhaExercitos(Graphics2D g2d) {
		for (Map.Entry<String,Territorio> entry:Tabuleiro.mapTerritorios.entrySet()) {
			String key = entry.getKey();
            Territorio value = entry.getValue();
            //System.out.println("Key=" + key + ", Value=" + value.getNome());
            Exercitos exercitos = new Exercitos(value.getPosX(),value.getPosY(),value.getCor(),String.valueOf(value.getQntExercitos()));
            exercitos.drawPlayer(g2d);
            
            
		}
	}
	
	String[] getTerritoriosLista() {
		String[] arrayTerritorios = new String[50];
		int cont = 0;
		for (Map.Entry<String,Territorio> entry:Tabuleiro.mapTerritorios.entrySet()) {
            arrayTerritorios[cont] = entry.getKey();
            cont++;
		}
		System.out.println(arrayTerritorios);
		return arrayTerritorios;
	}
		
}
