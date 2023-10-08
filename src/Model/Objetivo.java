package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

class Objetivo {
	public static ArrayList <String> descricao = new ArrayList <String>();
	
	public Objetivo() {
		
	}
	public static void criaObjetivos() {
		descricao.add("Dominar a Ásia");
		descricao.add("Dominar a África");
		descricao.add("Dominar a Oceania");
		descricao.add("Dominar a América do Sul");
		descricao.add("Dominar a América do Norte");
		descricao.add("Dominar a Europa");
	}
	public String getObjetivoAleatorio() {
		Collections.shuffle(descricao, new Random());
		String d = descricao.get(0);
		descricao.remove(0);
		return d;
	}
	
	
}
