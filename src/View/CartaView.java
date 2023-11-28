package View;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class CartaView {

	HashMap<String,Image> cartas = new HashMap<String,Image>();
 	public CartaView() {
		try {
			cartas.put("AfricaDoSul",ImageIO.read(new File("war_carta_af_africadosul.png")));
			cartas.put("Angola",ImageIO.read(new File("war_carta_af_angola.png")));
			cartas.put("Argelia",ImageIO.read(new File("war_carta_af_argelia.png")));
			cartas.put(ImageIO.read(new File("war_carta_af_egito.png")));
			cartas.put(ImageIO.read(new File("war_carta_af_nigeria.png")));
			cartas.put(ImageIO.read(new File("war_carta_af_somalia.png")));
			
			cartas.put(ImageIO.read(new File("war_carta_an_alasca.png")));
			cartas.put(ImageIO.read(new File("war_carta_an_calgary.png")));
			cartas.put(ImageIO.read(new File("war_carta_an_california.png")));
			cartas.put(ImageIO.read(new File("war_carta_an_groelandia.png")));
			cartas.put(ImageIO.read(new File("war_carta_an_mexico.png")));
			cartas.put(ImageIO.read(new File("war_carta_an_novayork.png")));
			cartas.put(ImageIO.read(new File("war_carta_an_quebec.png")));
			cartas.put(ImageIO.read(new File("war_carta_an_texas.png")));
			cartas.put(ImageIO.read(new File("war_carta_an_vancouver.png")));
			
			cartas.add(ImageIO.read(new File("war_carta_as_arabiasaudita.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_bangladesh.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_cazaquista.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_china.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_coreiadonorte.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_coreiadosul.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_estonia.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_india.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_ira.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_iraque.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_japao.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_jordania.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_letonia.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_mongolia.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_paquistao.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_russia.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_siberia.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_siria.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_tailandia.png")));
			cartas.add(ImageIO.read(new File("war_carta_as_turquia.png")));
			
			cartas.add(ImageIO.read(new File("war_carta_asl_argentina_.png")));
			cartas.add(ImageIO.read(new File("war_carta_asl_brasil.png")));
			cartas.add(ImageIO.read(new File("war_carta_asl_peru.png")));
			cartas.add(ImageIO.read(new File("war_carta_asl_venezuela.png")));
			
			cartas.add(ImageIO.read(new File("war_carta_eu_espanha.png")));
			cartas.add(ImageIO.read(new File("war_carta_eu_franca.png")));
			cartas.add(ImageIO.read(new File("war_carta_eu_italia.png")));
			cartas.add(ImageIO.read(new File("war_carta_eu_polonia.png")));
			cartas.add(ImageIO.read(new File("war_carta_eu_romenia.png")));
			cartas.add(ImageIO.read(new File("war_carta_eu_suecia.png")));
			cartas.add(ImageIO.read(new File("war_carta_eu_ucrania.png")));
			
			cartas.add(ImageIO.read(new File("war_carta_oc_australia.png")));
			cartas.add(ImageIO.read(new File("war_carta_oc_indonesia.png")));
			cartas.add(ImageIO.read(new File("war_carta_oc_novazelandia.png")));
			cartas.add(ImageIO.read(new File("war_carta_oc_perth.png")));
			
		}
		catch (IOException e) {
			System.out.println("Nao foi possivel carregar a imagem das cartas");
		}
	}
}
