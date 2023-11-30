package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class CartaView {

	HashMap<String,Image> cartas = new HashMap<String,Image>();

	//Componente gráfico
	Graphics2D g2d;

	//Construtor
 	public CartaView() {
		// Carrega as imagens das cartas
		try {
			// África 
			cartas.put("Africa Do Sul",ImageIO.read(new File("imagens/war_carta_af_africadosul.png")));
			cartas.put("Angola",ImageIO.read(new File("imagens/war_carta_af_angola.png")));
			cartas.put("Argelia",ImageIO.read(new File("imagens/war_carta_af_argelia.png")));
			cartas.put("Egito",ImageIO.read(new File("imagens/war_carta_af_egito.png")));
			cartas.put("Nigeria",ImageIO.read(new File("imagens/war_carta_af_nigeria.png")));
			cartas.put("Somalia",ImageIO.read(new File("imagens/war_carta_af_somalia.png")));

			// América do Norte
			cartas.put("Alasca",ImageIO.read(new File("imagens/war_carta_an_alasca.png")));
			cartas.put("Calgary",ImageIO.read(new File("imagens/war_carta_an_calgary.png")));
			cartas.put("California",ImageIO.read(new File("imagens/war_carta_an_california.png")));
			cartas.put("Groelandia",ImageIO.read(new File("imagens/war_carta_an_groelandia.png")));
			cartas.put("Mexico",ImageIO.read(new File("imagens/war_carta_an_mexico.png")));
			cartas.put("Nova York",ImageIO.read(new File("imagens/war_carta_an_novayork.png")));
			cartas.put("Quebec",ImageIO.read(new File("imagens/war_carta_an_quebec.png")));
			cartas.put("Texas",ImageIO.read(new File("imagens/war_carta_an_texas.png")));
			cartas.put("Vancouver",ImageIO.read(new File("imagens/war_carta_an_vancouver.png")));

			// Ásia
			cartas.put("Arabia Saudita",ImageIO.read(new File("imagens/war_carta_as_arabiasaudita.png")));
			cartas.put("Bangladesh",ImageIO.read(new File("imagens/war_carta_as_bangladesh.png")));
			cartas.put("Cazaquistao",ImageIO.read(new File("imagens/war_carta_as_cazaquistao.png")));
			cartas.put("China",ImageIO.read(new File("imagens/war_carta_as_china.png")));
			cartas.put("Coreia Do Norte",ImageIO.read(new File("imagens/war_carta_as_coreiadonorte.png")));
			cartas.put("Coreia Do Sul",ImageIO.read(new File("imagens/war_carta_as_coreiadosul.png")));
			cartas.put("Estonia",ImageIO.read(new File("imagens/war_carta_as_estonia.png")));
			cartas.put("India",ImageIO.read(new File("imagens/war_carta_as_india.png")));
			cartas.put("Ira",ImageIO.read(new File("imagens/war_carta_as_ira.png")));
			cartas.put("Iraque",ImageIO.read(new File("imagens/war_carta_as_iraque.png")));
			cartas.put("Japao",ImageIO.read(new File("imagens/war_carta_as_japao.png")));
			cartas.put("Jordania",ImageIO.read(new File("imagens/war_carta_as_jordania.png")));
			cartas.put("Letonia",ImageIO.read(new File("imagens/war_carta_as_letonia.png")));
			cartas.put("Mongolia",ImageIO.read(new File("imagens/war_carta_as_mongolia.png")));
			cartas.put("Paquistao",ImageIO.read(new File("imagens/war_carta_as_paquistao.png")));
			cartas.put("Russia",ImageIO.read(new File("imagens/war_carta_as_russia.png")));
			cartas.put("Siberia",ImageIO.read(new File("imagens/war_carta_as_siberia.png")));
			cartas.put("Siria",ImageIO.read(new File("imagens/war_carta_as_siria.png")));
			cartas.put("Tailandia",ImageIO.read(new File("imagens/war_carta_as_tailandia.png")));
			cartas.put("Turquia",ImageIO.read(new File("imagens/war_carta_as_turquia.png")));

			// América do Sul
			cartas.put("Argentina",ImageIO.read(new File("imagens/war_carta_asl_argentina.png")));
			cartas.put("Brasil",ImageIO.read(new File("imagens/war_carta_asl_brasil.png")));
			cartas.put("Peru",ImageIO.read(new File("imagens/war_carta_asl_peru.png")));
			cartas.put("Venezuela",ImageIO.read(new File("imagens/war_carta_asl_venezuela.png")));
			
			// Europa
			cartas.put("Espanha",ImageIO.read(new File("imagens/war_carta_eu_espanha.png")));
			cartas.put("Franca",ImageIO.read(new File("imagens/war_carta_eu_franca.png")));
			cartas.put("Italia",ImageIO.read(new File("imagens/war_carta_eu_italia.png")));
			cartas.put("Polonia",ImageIO.read(new File("imagens/war_carta_eu_polonia.png")));
			cartas.put("Romenia",ImageIO.read(new File("imagens/war_carta_eu_romenia.png")));
			cartas.put("Suecia",ImageIO.read(new File("imagens/war_carta_eu_suecia.png")));
			cartas.put("Ucrania",ImageIO.read(new File("imagens/war_carta_eu_ucrania.png")));
			
			// Oceania
			cartas.put("Australia",ImageIO.read(new File("imagens/war_carta_oc_australia.png")));
			cartas.put("Indonesia",ImageIO.read(new File("imagens/war_carta_oc_indonesia.png")));
			cartas.put("Nova Zelandia",ImageIO.read(new File("imagens/war_carta_oc_novazelandia.png")));
			cartas.put("Perth",ImageIO.read(new File("imagens/war_carta_oc_perth.png")));

			// Coringa
			cartas.put("Coringa",ImageIO.read(new File("imagens/war_carta_coringa.png")));
			
		}
		
		// Caso nao consiga carregar a imagem, imprime uma mensagem de erro
		catch (IOException e) {
			System.out.println("Nao foi possivel carregar a imagem das cartas");
		}
	}

	//Desenha as cartas
	public void drawCarta(String nome, int x, int y, int width, int height, Graphics2D g) {
		this.g2d = (Graphics2D) g;
		this.g2d.drawImage(cartas.get(nome), x, y, width, height, null);
	}
}
