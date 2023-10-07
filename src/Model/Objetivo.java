package Model;

import java.util.ArrayList;
import java.util.Random;

public class Objetivo {
  private ArrayList <String> descricao = new ArrayList <String>();
	
	public Objetivo() {
		descricao.add("Dominar a Ásia");
		descricao.add("Dominar a África");
		descricao.add("Dominar a Oceania");
		descricao.add("Dominar a América do Sul");
		descricao.add("Dominar a América do Norte");
		descricao.add("Dominar a Europa");
	}
	
	public String getObjetivo() {
		Random r = new Random();
		return descricao.get(r.nextInt(descricao.size()));
	}
	
}
