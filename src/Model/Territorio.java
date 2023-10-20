package Model;
import java.util.ArrayList;

class Territorio {
	 	//Guarda o jogador que domina o território
		private Jogador jogador;
		
		//Guarda o nome do território
		private String nome;
		
		// Posição posicao;
		
		//Guarda a quantidade de Exércitos no território
		private int qntExercitos;
		
		//Guarda os territórios adjacentes a este (this)
		private ArrayList<Territorio> adjacentes = new ArrayList<Territorio>();
		
		public Territorio(String nome){
			// inicializa para se chamar getters não ter problema
			this.jogador = null;
			this.nome = nome;
		}
			
		// Adicionar território adjacente para inicialização
		protected void AddAdjacente (Territorio adj) {
			this.adjacentes.add(adj);
		}
		
		//Verifica se um território é adjacente a outro
		protected boolean verificaAdjacencia (Territorio ter) {
			for (Territorio t: adjacentes)
				if (ter == t)
					return true;
			return false;
		}
		
		// Alterar quantidade de exércitos sem precisar saber o valor antes
		// Int positivo add exercitos e negativo subtrai 
		protected boolean alterarQtdExercitos (int qtd) {
			// se tentar subtrair mais exércitos do que tem (sem poder zerar)
			if (qtd < 0)
				if ((qtd * (-1)) >= this.qntExercitos)
					return false;
			this.qntExercitos += qtd;
			return true;
		}
		
		// getters e setters padrão
		public Jogador getJogador() {
			return jogador;
		}

		public void setJogador(Jogador jogador) {
			this.jogador = jogador;
		}

		public int getQntExercitos() {
			return qntExercitos;
		}

		public void setQntExercitos(int qntExercitos) {
			this.qntExercitos = qntExercitos;
		}
		
		public String getNome() {
			return nome;
		}

		public ArrayList<Territorio> getAdjacentes() {
			return adjacentes;
		}

		protected void instanciaAdjacentes(Territorio t){
			switch(t.nome){
				//America do sul
				case "Brasil":
				t.AddAdjacente(new Territorio("Argentina"));
				t.AddAdjacente(new Territorio("Peru"));
				t.AddAdjacente(new Territorio("Venezuela")); 
				t.AddAdjacente(new Territorio("Nigeria"));
				break;

				case "Argentina":
				t.AddAdjacente(new Territorio("Brasil"));
				t.AddAdjacente(new Territorio("Peru"));
				break;

				case "Peru":
				t.AddAdjacente(new Territorio("Brasil"));
				t.AddAdjacente(new Territorio("Argentina"));
				t.AddAdjacente(new Territorio("Venezuela"));
				break;

				case "Venezuela":
				t.AddAdjacente(new Territorio("Brasil"));
				t.AddAdjacente(new Territorio("Peru"));
				t.AddAdjacente(new Territorio("Aregentina"));
				t.AddAdjacente(new Territorio("Mexico"));
				break;

				//America do norte
				case "Mexico":
				t.AddAdjacente(new Territorio("Venezuela"));	
				t.AddAdjacente(new Territorio("California"));
				t.AddAdjacente(new Territorio("Texas"));
				break;

				case "California":
				t.AddAdjacente(new Territorio("Mexico"));
				t.AddAdjacente(new Territorio("Texas"));
				t.AddAdjacente(new Territorio("Vancouver"));
				break;

				case "Texas":
				t.AddAdjacente(new Territorio("Mexico"));
				t.AddAdjacente(new Territorio("California"));
				t.AddAdjacente(new Territorio("Vancouver"));
				t.AddAdjacente(new Territorio("Nova York"));
				break;

				case "Vancouver":
				t.AddAdjacente(new Territorio("California"));
				t.AddAdjacente(new Territorio("Texas"));
				t.AddAdjacente(new Territorio("Alaska"));
				t.AddAdjacente(new Territorio("Calgary"));
				t.AddAdjacente(new Territorio("Quebec"));
				break;

				case "Alaska":
				t.AddAdjacente(new Territorio("Vancouver"));
				t.AddAdjacente(new Territorio("Calgary"));
				t.AddAdjacente(new Territorio("Siberia"));
				break;

				case "Calgary":
				t.AddAdjacente(new Territorio("Alaska"));
				t.AddAdjacente(new Territorio("Vancouver"));
				t.AddAdjacente(new Territorio("Groelandia"));
				break;

				case "Groelandia":
				t.AddAdjacente(new Territorio("Calgary"));
				//algum territorio da europa
				break;

				//Africa
				case "Nigeria":	
				t.AddAdjacente(new Territorio("Brasil"));
				t.AddAdjacente(new Territorio("Egito"));
				t.AddAdjacente(new Territorio("Angola"));
				t.AddAdjacente(new Territorio("Argelia"));
				t.AddAdjacente(new Territorio("Somalia"));
				break;

				case "Egito":
				t.AddAdjacente(new Territorio("Nigeria"));
				t.AddAdjacente(new Territorio("Argelia"));
				t.AddAdjacente(new Territorio("Somalia"));
				t.AddAdjacente(new Territorio("Jordania"));
				//algum pais da europa
				break;

				case "Angola":
				t.AddAdjacente(new Territorio("Nigeria"));
				t.AddAdjacente(new Territorio("Somalia"));
				t.AddAdjacente(new Territorio("Africa do Sul"));
				break;

				case "Argelia":
				t.AddAdjacente(new Territorio("Nigeria"));
				t.AddAdjacente(new Territorio("Egito"));
				//2 paises da europa
				break;

				case "Somalia":
				t.AddAdjacente(new Territorio("Nigeria"));
				t.AddAdjacente(new Territorio("Egito"));
				t.AddAdjacente(new Territorio("Angola"));
				t.AddAdjacente(new Territorio("Africa do Sul"));
				t.AddAdjacente(new Territorio("Arabia Saudita"));
				break;

				case "Africa do Sul":
				t.AddAdjacente(new Territorio("Angola"));
				t.AddAdjacente(new Territorio("Somalia"));
				break;

				//Asia
				case "Siberia":
				t.AddAdjacente(new Territorio("Alaska"));
				t.AddAdjacente(new Territorio("Russia"));
				t.AddAdjacente(new Territorio("Cazaquistao"));
				break;

				case "Cazaquistao":
				t.AddAdjacente(new Territorio("Siberia"));
				t.AddAdjacente(new Territorio("Russia"));
				t.AddAdjacente(new Territorio("China"));
				t.AddAdjacente(new Territorio("Mongolia"));
				t.AddAdjacente(new Territorio("Letonia"));
				t.AddAdjacente(new Territorio("Turquia"));
				t.AddAdjacente(new Territorio("Japao"));
				break;

				case "Russia":
				t.AddAdjacente(new Territorio("Siberia"));
				t.AddAdjacente(new Territorio("Cazaquistao"));
				t.AddAdjacente(new Territorio("Estonia"));
				t.AddAdjacente(new Territorio("Letonia"));
				break;

				case "China":
				t.AddAdjacente(new Territorio("Cazaquistao"));	
				t.AddAdjacente(new Territorio("Mongolia"));
				t.AddAdjacente(new Territorio("Coreia do Norte"));
				t.AddAdjacente(new Territorio("Coreia do Sul"));
				t.AddAdjacente(new Territorio("Turquia"));
				t.AddAdjacente(new Territorio("Paquistao"));
				t.AddAdjacente(new Territorio("India"));
				break;

				case "Mongolia":
				t.AddAdjacente(new Territorio("Cazaquistao"));
				t.AddAdjacente(new Territorio("China"));
				t.AddAdjacente(new Territorio("Japao"));
				break;

				case "Coreia do Norte":
				t.AddAdjacente(new Territorio("China"));
				t.AddAdjacente(new Territorio("Coreia do Sul"));
				t.AddAdjacente(new Territorio("Japao"));
				break;

				case "Coreia do Sul":
				t.AddAdjacente(new Territorio("China"));
				t.AddAdjacente(new Territorio("Coreia do Norte"));
				t.AddAdjacente(new Territorio("India"));
				t.AddAdjacente(new Territorio("Bangladesh"));
				t.AddAdjacente(new Territorio("Tailandia"));
				break;

				case "Japao":
				t.AddAdjacente(new Territorio("Mongolia"));
				t.AddAdjacente(new Territorio("Coreia do Norte"));
				t.AddAdjacente(new Territorio("Cazaquistao"));
				break;

				case "Turquia":
				t.AddAdjacente(new Territorio("Cazaquistao"));
				t.AddAdjacente(new Territorio("China"));
				t.AddAdjacente(new Territorio("Letonia"));
				t.AddAdjacente(new Territorio("Siria"));
				t.AddAdjacente(new Territorio("Paquistao"));
				break;

				case "Letonia":
				t.AddAdjacente(new Territorio("Cazaquistao"));
				t.AddAdjacente(new Territorio("Russia"));
				t.AddAdjacente(new Territorio("Turquia"));
				t.AddAdjacente(new Territorio("Estonia"));
				//2 paises da europa
				break;

				case "Estonia":
				t.AddAdjacente(new Territorio("Letonia"));
				t.AddAdjacente(new Territorio("Russia"));
				//1 pais da europa
				break;

				case "Paquistao":
				t.AddAdjacente(new Territorio("China"));
				t.AddAdjacente(new Territorio("Turquia"));
				t.AddAdjacente(new Territorio("India"));
				t.AddAdjacente(new Territorio("Siria"));
				t.AddAdjacente(new Territorio("Ira"));
				break;

				case "India":
				t.AddAdjacente(new Territorio("Paquistao"));
				t.AddAdjacente(new Territorio("Coreia do Sul"));
				t.AddAdjacente(new Territorio("Bangladesh"));
				t.AddAdjacente(new Territorio("China"));
				//1 pais da oceania
				break;

				case "Bangladesh":
				t.AddAdjacente(new Territorio("India"));
				t.AddAdjacente(new Territorio("Coreia do Sul"));
				t.AddAdjacente(new Territorio("Tailandia"));
				//1 pais da oceania
				break;

				case "Tailandia":
				t.AddAdjacente(new Territorio("Bangladesh"));
				t.AddAdjacente(new Territorio("Coreia do Sul"));
				break;

				case "Siria":
				t.AddAdjacente(new Territorio("Turquia"));
				t.AddAdjacente(new Territorio("Paquistao"));
				t.AddAdjacente(new Territorio("Ira"));
				t.AddAdjacente(new Territorio("Iraque"));
				t.AddAdjacente(new Territorio("Jordania"));
				break;

				case "Ira":
				t.AddAdjacente(new Territorio("Paquistao"));
				t.AddAdjacente(new Territorio("Siria"));
				t.AddAdjacente(new Territorio("Iraque"));
				break;

				case "Iraque":
				t.AddAdjacente(new Territorio("Ira"));
				t.AddAdjacente(new Territorio("Siria"));
				t.AddAdjacente(new Territorio("Jordania"));
				t.AddAdjacente(new Territorio("Arabia Saudita"));
				break;

				case "Jordania":
				t.AddAdjacente(new Territorio("Siria"));
				t.AddAdjacente(new Territorio("Iraque"));
				t.AddAdjacente(new Territorio("Egito"));
				t.AddAdjacente(new Territorio("Arabia Saudita"));
				break;

				case "Arabia Saudita":
				t.AddAdjacente(new Territorio("Iraque"));
				t.AddAdjacente(new Territorio("Jordania"));
				t.AddAdjacente(new Territorio("Somalia"));
				break;

				//falta europa e oceania
			}
		}

}
