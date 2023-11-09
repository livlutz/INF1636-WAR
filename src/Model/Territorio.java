package Model;
import java.awt.Color;
import java.util.ArrayList;

class Territorio {
	 	//Guarda o jogador que domina o território
		private Jogador jogador;
		
		//Guarda o nome do território
		private String nome;
		
		//Guarda a cor do territorio 
		private Color cor;
		
		//Guarda a quantidade de Exércitos no território
		private int qndExercitos;
		
		//Guarda os territórios adjacentes a este (this)
		private ArrayList<Territorio> adjacentes = new ArrayList<Territorio>();
		
		//Construtor
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
		protected boolean alterarQndExercitos (int qnd) {
			// se tentar subtrair mais exércitos do que tem (sem poder zerar)
			if (qnd < 0)
				if ((qnd * (-1)) >= this.qndExercitos)
					return false;
			this.qndExercitos += qnd;
			return true;
		}

		//Instancia os territórios adjacentes
		protected void instanciaAdjacentes(Territorio t){
			switch(t.nome){
				//América do sul
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
				t.AddAdjacente(new Territorio("Mexico"));
				break;

				//América do Norte
				case "Mexico":
				t.AddAdjacente(new Territorio("Venezuela"));	
				t.AddAdjacente(new Territorio("California"));
				t.AddAdjacente(new Territorio("Texas"));
				break;

				case "California":
				t.AddAdjacente(new Territorio("Mexico"));
				t.AddAdjacente(new Territorio("Texas"));
				t.AddAdjacente(new Territorio("Nova York"));
				break;

				case "Texas":
				t.AddAdjacente(new Territorio("Mexico"));
				t.AddAdjacente(new Territorio("California"));
				t.AddAdjacente(new Territorio("Vancouver"));
				t.AddAdjacente(new Territorio("Quebec"));
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
				t.AddAdjacente(new Territorio("Quebec"));
				t.AddAdjacente(new Territorio("Reio Unido"));
				break;

				case "Nova York":
				t.AddAdjacente(new Territorio("Texas"));
				t.AddAdjacente(new Territorio("Quebec"));
				break;

				case "Quebec":
				t.AddAdjacente(new Territorio("Nova York"));
				t.AddAdjacente(new Territorio("Vancouver"));
				t.AddAdjacente(new Territorio("Groelandia"));
				t.AddAdjacente(new Territorio("Texas"));
				break;

				//África
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
				t.AddAdjacente(new Territorio("Romenia"));
				break;

				case "Angola":
				t.AddAdjacente(new Territorio("Nigeria"));
				t.AddAdjacente(new Territorio("Somalia"));
				t.AddAdjacente(new Territorio("Africa do Sul"));
				break;

				case "Argelia":
				t.AddAdjacente(new Territorio("Nigeria"));
				t.AddAdjacente(new Territorio("Egito"));
				t.AddAdjacente(new Territorio("Espanha"));
				t.AddAdjacente(new Territorio("Italia"));
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

				//Ásia
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
				t.AddAdjacente(new Territorio("Ucrania"));
				break;

				case "Letonia":
				t.AddAdjacente(new Territorio("Cazaquistao"));
				t.AddAdjacente(new Territorio("Russia"));
				t.AddAdjacente(new Territorio("Turquia"));
				t.AddAdjacente(new Territorio("Estonia"));
				t.AddAdjacente(new Territorio("Polonia"));
				t.AddAdjacente(new Territorio("Suecia"));
				t.AddAdjacente(new Territorio("Ucrania"));
				break;

				case "Estonia":
				t.AddAdjacente(new Territorio("Letonia"));
				t.AddAdjacente(new Territorio("Russia"));
				t.AddAdjacente(new Territorio("Suecia"));
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
				t.AddAdjacente(new Territorio("Indonesia"));
				break;

				case "Bangladesh":
				t.AddAdjacente(new Territorio("India"));
				t.AddAdjacente(new Territorio("Coreia do Sul"));
				t.AddAdjacente(new Territorio("Tailandia"));
				t.AddAdjacente(new Territorio("Indonesia"));
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

				//Europa
				case "Espanha":
				t.AddAdjacente(new Territorio("Argelia"));
				t.AddAdjacente(new Territorio("Franca"));
				break;

				case "Franca":
				t.AddAdjacente(new Territorio("Espanha"));
				t.AddAdjacente(new Territorio("Italia"));
				t.AddAdjacente(new Territorio("Reino Unido"));
				t.AddAdjacente(new Territorio("Suecia"));
				break;

				case "Italia":
				t.AddAdjacente(new Territorio("Argelia"));
				t.AddAdjacente(new Territorio("Franca"));
				t.AddAdjacente(new Territorio("Polonia"));
				t.AddAdjacente(new Territorio("Suecia"));
				t.AddAdjacente(new Territorio("Romenia"));
				break;

				case "Reino Unido":
				t.AddAdjacente(new Territorio("Franca"));
				t.AddAdjacente(new Territorio("Groelandia"));
				break;

				case "Polonia":
				t.AddAdjacente(new Territorio("Letonia"));
				t.AddAdjacente(new Territorio("Italia"));
				t.AddAdjacente(new Territorio("Romenia"));
				t.AddAdjacente(new Territorio("Ucrania"));
				break;

				case "Suecia":
				t.AddAdjacente(new Territorio("Estonia"));
				t.AddAdjacente(new Territorio("Letonia"));
				t.AddAdjacente(new Territorio("Italia"));
				t.AddAdjacente(new Territorio("Franca"));
				break;

				case "Romenia":
				t.AddAdjacente(new Territorio("Egito"));	
				t.AddAdjacente(new Territorio("Italia"));
				t.AddAdjacente(new Territorio("Polonia"));
				t.AddAdjacente(new Territorio("Ucrania"));
				break;

				case "Ucrania":
				t.AddAdjacente(new Territorio("Polonia"));
				t.AddAdjacente(new Territorio("Romenia"));
				t.AddAdjacente(new Territorio("Turquia"));
				t.AddAdjacente(new Territorio("Letonia"));
				break;

				//Oceania
				case "Indonesia":
				t.AddAdjacente(new Territorio("India"));
				t.AddAdjacente(new Territorio("Bangladesh"));
				t.AddAdjacente(new Territorio("Nova Zelandia"));
				t.AddAdjacente(new Territorio("Australia"));
				break;

				case "Nova Zelandia":
				t.AddAdjacente(new Territorio("Indonesia"));
				t.AddAdjacente(new Territorio("Australia"));
				break;

				case "Australia":
				t.AddAdjacente(new Territorio("Indonesia"));
				t.AddAdjacente(new Territorio("Nova Zelandia"));
				break;

				case "Perth":
				t.AddAdjacente(new Territorio("Australia"));
				break;
			}
		}
		
		// ---------------------- getters & setters ----------------------
		
		//Retorna o jogador
		public Jogador getJogador() {
			return jogador;
		}

		//Altera o jogador
		public void setJogador(Jogador jogador) {
			this.jogador = jogador;
		}

		//Retorna a quantidade de exércitos
		public int getQntExercitos() {
			return qndExercitos;
		}

		//Altera a quantidade de exércitos
		public void setQntExercitos(int qntExercitos) {
			this.qndExercitos = qntExercitos;
		}
		
		//Retorna o nome do território
		public String getNome() {
			return nome;
		}

		//Retorna os territórios adjacentes (o ArrayList de territórios adjacentes)
		public ArrayList<Territorio> getAdjacentes() {
			return adjacentes;
		}
		
		//Retorna a cor do territorio
		public Color getCor() {
			return cor;
		}
		
		//Altera a cor do territorio para a cor do jogador que o domina
		public void setCor() {
			this.cor = jogador.getCor();
		}
}
