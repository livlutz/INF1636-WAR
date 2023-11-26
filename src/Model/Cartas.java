package Model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Cartas {
	
	//enum com a forma geométrica presente em cada carta
	public enum Formato{
		Quadrado,circulo,triangulo,coringa;
	}
	
	//Guarda o formato presente na carta
	protected Formato f;

	//Território da carta
	private Territorio territorio;

	//Imagem da carta
	Image imagem;
	
	//Construtor
	public Cartas(int i, Territorio t) {
		f = Formato.values()[i];
		this.territorio = t;
		try {
			if(t == null) {
				imagem = ImageIO.read(new File("imagens/war_carta_coringa.png"));
			}
			else{
				switch(t.getNome()){
					// África
					case "Africa do Sul":
						imagem = ImageIO.read(new File("imagens/war_carta_af_africadosul.png"));
						break;
					case "Angola":
						imagem = ImageIO.read(new File("imagens/war_carta_af_angola.png"));
						break;
					case "Argelia":
						imagem = ImageIO.read(new File("imagens/war_carta_af_argelia.png"));
						break;
					case "Egito":
						imagem = ImageIO.read(new File("imagens/war_carta_af_egito.png"));
						break;
					case "Nigeria":
						imagem = ImageIO.read(new File("imagens/war_carta_af_nigeria.png"));
						break;
					case "Somalia":
						imagem = ImageIO.read(new File("imagens/war_carta_af_somalia.png"));
						break;
					// América do Norte
					case "Alasca":
						imagem = ImageIO.read(new File("imagens/war_carta_an_alasca.png"));
						break;
					case "California":
						imagem = ImageIO.read(new File("imagens/war_carta_an_california.png"));
						break;
					case "Calgary":
						imagem = ImageIO.read(new File("imagens/war_carta_an_calgary.png"));
						break;
					case "Groelandia":
						imagem = ImageIO.read(new File("imagens/war_carta_an_groelandia.png"));
						break;
					case "Mexico":
						imagem = ImageIO.read(new File("imagens/war_carta_an_mexico.png"));
						break;
					case "Nova York":
						imagem = ImageIO.read(new File("imagens/war_carta_an_novayork.png"));
						break;
					case "Quebec":
						imagem = ImageIO.read(new File("imagens/war_carta_an_quebec.png"));
						break;
					case "Texas":
						imagem = ImageIO.read(new File("imagens/war_carta_an_texas.png"));
						break;
					case "Vancouver":
						imagem = ImageIO.read(new File("imagens/war_carta_an_vancouver.png"));
						break;
					// América do Sul
					case "Argentina":
						imagem = ImageIO.read(new File("imagens/war_carta_asl_argentina.png"));
						break;
					case "Brasil":
						imagem = ImageIO.read(new File("imagens/war_carta_asl_brasil.png"));
						break;
					case "Peru":
						imagem = ImageIO.read(new File("imagens/war_carta_asl_peru.png"));
						break;
					case "Venezuela":
						imagem = ImageIO.read(new File("imagens/war_carta_asl_venezuela.png"));
						break;
					// Ásia
					case "Arabia Saudita":
						imagem = ImageIO.read(new File("imagens/war_carta_as_arabiasaudita.png"));
						break;
					case "Bangladesh":
						imagem = ImageIO.read(new File("imagens/war_carta_as_bangladesh.png"));
						break;
					case "Cazaquistao":
						imagem = ImageIO.read(new File("imagens/war_carta_as_cazaquistao.png"));
						break;
					case "China":
						imagem = ImageIO.read(new File("imagens/war_carta_as_china.png"));
						break;
					case "Coreia do Norte":
						imagem = ImageIO.read(new File("imagens/war_carta_as_coreiadonorte.png"));
						break;
					case "Coreia do Sul":
						imagem = ImageIO.read(new File("imagens/war_carta_as_coreiadosul.png"));
						break;
					case "Estonia":
						imagem = ImageIO.read(new File("imagens/war_carta_as_estonia.png"));
						break;
					case "India":
						imagem = ImageIO.read(new File("imagens/war_carta_as_india.png"));
						break;
					case "Ira":
						imagem = ImageIO.read(new File("imagens/war_carta_as_ira.png"));
						break;
					case "Iraque":
						imagem = ImageIO.read(new File("imagens/war_carta_as_iraque.png"));
						break;
					case "Japao":
						imagem = ImageIO.read(new File("imagens/war_carta_as_japao.png"));
						break;
					case "Jordania":
						imagem = ImageIO.read(new File("imagens/war_carta_as_jordania.png"));
						break;
					case "Letonia":
						imagem = ImageIO.read(new File("imagens/war_carta_as_letonia.png"));
						break;
					case "Mongolia":
						imagem = ImageIO.read(new File("imagens/war_carta_as_mongolia.png"));
						break;
					case "Paquistao":
						imagem = ImageIO.read(new File("imagens/war_carta_as_paquistao.png"));
						break;
					case "Russia":
						imagem = ImageIO.read(new File("imagens/war_carta_as_russia.png"));
						break;
					case "Siria":
						imagem = ImageIO.read(new File("imagens/war_carta_as_siria.png"));
						break;
					case "Siberia":
						imagem = ImageIO.read(new File("imagens/war_carta_as_siberia.png"));
						break;
					case "Tailandia":
						imagem = ImageIO.read(new File("imagens/war_carta_as_tailandia.png"));
						break;
					case "Turquia":
						imagem = ImageIO.read(new File("imagens/war_carta_as_turquia.png"));
						break;
					// Europa
					case "Espanha":
						imagem = ImageIO.read(new File("imagens/war_carta_eu_espanha.png"));
						break;
					case "Franca":
						imagem = ImageIO.read(new File("imagens/war_carta_eu_franca.png"));
						break;
					case "Italia":
						imagem = ImageIO.read(new File("imagens/war_carta_eu_italia.png"));
						break;
					case "Polonia":
						imagem = ImageIO.read(new File("imagens/war_carta_eu_polonia.png"));
						break;
					case "Reino Unido":
						imagem = ImageIO.read(new File("imagens/war_carta_eu_reinounido.png"));
						break;
					case "Romenia":
						imagem = ImageIO.read(new File("imagens/war_carta_eu_romenia.png"));
						break;
					case "Suecia":
						imagem = ImageIO.read(new File("imagens/war_carta_eu_suecia.png"));
						break;
					case "Ucrania":
						imagem = ImageIO.read(new File("imagens/war_carta_eu_ucrania.png"));
						break;
					// Oceania
					case "Australia":
						imagem = ImageIO.read(new File("imagens/war_carta_oc_australia.png"));
						break;
					case "Indonesia":
						imagem = ImageIO.read(new File("imagens/war_carta_oc_indonesia.png"));
						break;
					case "Nova Zelandia":
						imagem = ImageIO.read(new File("imagens/war_carta_oc_novazelandia.png"));
						break;
					case "Perth":
						imagem = ImageIO.read(new File("imagens/war_carta_oc_perth.png"));
						break;
				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao carregar imagem da carta");
		}
	}
	
	//Verifica se o território da carta pertence ao jogador
	public boolean terrPertenceJogador(Jogador j) {
		for (Territorio t : j.getTerritorios()) {
			if(this.territorio == t) {
				return true;
			}
		}
		return false;
	}

	//----------------- Getters & Setters -----------------

	//Retorna o formato da carta
	public Formato getF() {
		return f;
	}

	//Altera o formato da carta
	public void setF(Formato f) {
		this.f = f;
	}

	//Retorna o território da carta
	public Territorio getTerritorio() {
		return territorio;
	}

	//Altera o território da carta
	public void setTerritorio(Territorio territorio) {
		this.territorio = territorio;
	}

	public Image getImagem() {
		return imagem;
	}
}
