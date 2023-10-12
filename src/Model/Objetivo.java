package Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

class Objetivo {
	
	//Guarda todos os objetivos possíveis de se obter no jogo
	public static ArrayList <String> descricao = new ArrayList <String>();
	
	//Construtor
	public Objetivo() {
		
	}
	
	//Insere objetivos no array
	public static void criaObjetivos() {
		descricao.add("Conquistar na totalidade a EUROPA, a OCEANIA e mais um terceiro");
		descricao.add("Conquistar na totalidade a ÁSIA e a AMÉRICA DO SUL");
		descricao.add("Conquistar na totalidade a EUROPA, a AMÉRICA DO SUL e mais um terceiro");
		descricao.add("Conquistar 18 TERRITÓRIOS e ocupar cada um deles com pelo menos dois exércitos");
		descricao.add("Conquistar na totalidade a ÁSIA e a ÁFRICA");
		descricao.add("Conquistar na totalidade a AMÉRICA DO NORTE e a ÁFRICA");
		descricao.add("Conquistar 24 TERRITÓRIOS à sua escolha");
		descricao.add("Conquistar na totalidade a AMÉRICA DO NORTE e a OCEANIA");
		/*descricao.add("Destruir totalmente OS EXÉRCITOS AZUIS");
		descricao.add("Destruir totalmente OS EXÉRCITOS AMARELOS");
		descricao.add("Destruir totalmente OS EXÉRCITOS VERMELHOS");
		descricao.add("Destruir totalmente OS EXÉRCITOS PRETOS");
		descricao.add("Destruir totalmente OS EXÉRCITOS BRANCOS");
		descricao.add("Destruir totalmente OS EXÉRCITOS VERDES");
		Temos que adaptar isso para as cores de cada jogador*/
	}
	
	//Obtém um objetivo aleatório do array
	public String getObjetivoAleatorio() {
		Collections.shuffle(descricao, new Random());
		String d = descricao.get(0);
		descricao.remove(0);
		return d;
	}
	
	
}
