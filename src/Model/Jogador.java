package Model;

import java.util.ArrayList;
import java.util.Random;

class Jogador {

	// enum com as cores que um jogador pode escolher
	public enum Cor {
		branco,preto,vermelho,azul,amarelo,verde;
	}
	
	private Cor cor;
	private String nome;
	private int qtdExercitoPosic;
	private static int qtdTrocaCartas = 0;
	private int qtdTerritorios = 0;
	private ArrayList <Cartas> cartas = new ArrayList <Cartas> ();
	private String obj;

	public Jogador(String nome, Cor cor) {
		this.nome = nome;
		this.cor = cor;
	}
	

	public void verCartas() {
		for(Cartas c : cartas) {
			System.out.println(c);
		}
	}

	protected boolean alterarQtdTerritorios (int qtd) {
			// se tentar subtrair mais territorios do que tem 
			if (qtd < 0)
				if ((qtd * (-1)) > this.qtdTerritorios)
					return false;
			this.qtdTerritorios += qtd;
			return true;
		}

	// getters e setters padrão
	public int getQtdExecPos() {
		return qtdExercitoPosic;
	}

	public static void trocarCartas (Cartas a, Cartas b, Cartas c) {
		qtdTrocaCartas++;
	}

	public Cor getCor() {
		return cor;
	}

	public String getNome() {
		return nome;
	}

	public int getQtdExercitoPosic() {
		return qtdExercitoPosic;
	}

	public void setQtdExercitoPosic(int qtdExercitoPosic) {
		this.qtdExercitoPosic = qtdExercitoPosic;
	}

	public int getQtdTerritorios(){
		return qtdTerritorios;
	}

	public void setQtdTerritorios(int qtd){
		qtdTerritorios = qtd;
	}
	
	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	public static int getQtdTrocaCartas() {
		return qtdTrocaCartas;
	}

	public ArrayList<Cartas> getCartas() {
		return cartas;
	}

	/*Faltando: 
   		Adiconar cartas no array
     		Territorios e ver qtd de territorios
 		*/
}
