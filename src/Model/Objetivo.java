package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

class Objetivo {
  public static ArrayList <String> descricao = new ArrayList <String>();
	
	public static Objetivo() {
		descricao.add("Dominar a Ásia");
		descricao.add("Dominar a África");
		descricao.add("Dominar a Oceania");
		descricao.add("Dominar a América do Sul");
		descricao.add("Dominar a América do Norte");
		descricao.add("Dominar a Europa");
	}
	
	public String getObjetivoAleatorio() {
		Collections.shuffle(descricao, new Random());
		return descricao.pop();
	}
	
	
}
