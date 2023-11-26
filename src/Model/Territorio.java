package Model;
import java.awt.Color;
import java.util.ArrayList;

class Territorio {
	 	//Guarda o jogador que domina o território
		private Jogador jogador;
		
		//Guarda o nome do território
		private String nome;
		
		//Guarda a cor do território 
		private Color cor;
		
		//Guarda a quantidade de Exércitos no território
		private int qndExercitos;
		
		//Guarda os territórios adjacentes a este (this)
		private ArrayList<Territorio> adjacentes = new ArrayList<Territorio>();
		
		//Construtor
		public Territorio(String nome) {
			// Inicializa para se chamar getters não ter problema
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
		protected void instanciaAdjacentes(){
			Tabuleiro tabuleiro = Tabuleiro.getTabuleiro();
			Territorio t = this;
			switch(t.nome){
				//América do sul
				case "Brasil":
				t.AddAdjacente(tabuleiro.getTerritorio("Argentina"));
				t.AddAdjacente(tabuleiro.getTerritorio("Peru"));
				t.AddAdjacente(tabuleiro.getTerritorio("Venezuela")); 
				t.AddAdjacente(tabuleiro.getTerritorio("Nigeria"));
				break;

				case "Argentina":
				t.AddAdjacente(tabuleiro.getTerritorio("Brasil"));
				t.AddAdjacente(tabuleiro.getTerritorio("Peru"));
				break;

				case "Peru":
				t.AddAdjacente(tabuleiro.getTerritorio("Brasil"));
				t.AddAdjacente(tabuleiro.getTerritorio("Argentina"));
				t.AddAdjacente(tabuleiro.getTerritorio("Venezuela"));
				break;

				case "Venezuela":
				t.AddAdjacente(tabuleiro.getTerritorio("Brasil"));
				t.AddAdjacente(tabuleiro.getTerritorio("Peru"));
				t.AddAdjacente(tabuleiro.getTerritorio("Mexico"));
				break;

				//América do Norte
				case "Mexico":
				t.AddAdjacente(tabuleiro.getTerritorio("Venezuela"));	
				t.AddAdjacente(tabuleiro.getTerritorio("California"));
				t.AddAdjacente(tabuleiro.getTerritorio("Texas"));
				break;

				case "California":
				t.AddAdjacente(tabuleiro.getTerritorio("Mexico"));
				t.AddAdjacente(tabuleiro.getTerritorio("Texas"));
				t.AddAdjacente(tabuleiro.getTerritorio("Nova York"));
				break;

				case "Texas":
				t.AddAdjacente(tabuleiro.getTerritorio("Mexico"));
				t.AddAdjacente(tabuleiro.getTerritorio("California"));
				t.AddAdjacente(tabuleiro.getTerritorio("Vancouver"));
				t.AddAdjacente(tabuleiro.getTerritorio("Quebec"));
				t.AddAdjacente(tabuleiro.getTerritorio("Nova York"));
				break;

				case "Vancouver":
				t.AddAdjacente(tabuleiro.getTerritorio("California"));
				t.AddAdjacente(tabuleiro.getTerritorio("Texas"));
				t.AddAdjacente(tabuleiro.getTerritorio("Alasca"));
				t.AddAdjacente(tabuleiro.getTerritorio("Calgary"));
				t.AddAdjacente(tabuleiro.getTerritorio("Quebec"));
				break;

				case "Alasca":
				t.AddAdjacente(tabuleiro.getTerritorio("Vancouver"));
				t.AddAdjacente(tabuleiro.getTerritorio("Calgary"));
				t.AddAdjacente(tabuleiro.getTerritorio("Siberia"));
				break;

				case "Calgary":
				t.AddAdjacente(tabuleiro.getTerritorio("Alasca"));
				t.AddAdjacente(tabuleiro.getTerritorio("Vancouver"));
				t.AddAdjacente(tabuleiro.getTerritorio("Groelandia"));
				break;

				case "Groelandia":
				t.AddAdjacente(tabuleiro.getTerritorio("Calgary"));
				t.AddAdjacente(tabuleiro.getTerritorio("Quebec"));
				t.AddAdjacente(tabuleiro.getTerritorio("Reino Unido"));
				break;

				case "Nova York":
				t.AddAdjacente(tabuleiro.getTerritorio("Texas"));
				t.AddAdjacente(tabuleiro.getTerritorio("Quebec"));
				break;

				case "Quebec":
				t.AddAdjacente(tabuleiro.getTerritorio("Nova York"));
				t.AddAdjacente(tabuleiro.getTerritorio("Vancouver"));
				t.AddAdjacente(tabuleiro.getTerritorio("Groelandia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Texas"));
				break;

				//África
				case "Nigeria":	
				t.AddAdjacente(tabuleiro.getTerritorio("Brasil"));
				t.AddAdjacente(tabuleiro.getTerritorio("Egito"));
				t.AddAdjacente(tabuleiro.getTerritorio("Angola"));
				t.AddAdjacente(tabuleiro.getTerritorio("Argelia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Somalia"));
				break;

				case "Egito":
				t.AddAdjacente(tabuleiro.getTerritorio("Nigeria"));
				t.AddAdjacente(tabuleiro.getTerritorio("Argelia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Somalia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Jordania"));
				t.AddAdjacente(tabuleiro.getTerritorio("Romenia"));
				break;

				case "Angola":
				t.AddAdjacente(tabuleiro.getTerritorio("Nigeria"));
				t.AddAdjacente(tabuleiro.getTerritorio("Somalia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Africa do Sul"));
				break;

				case "Argelia":
				t.AddAdjacente(tabuleiro.getTerritorio("Nigeria"));
				t.AddAdjacente(tabuleiro.getTerritorio("Egito"));
				t.AddAdjacente(tabuleiro.getTerritorio("Espanha"));
				t.AddAdjacente(tabuleiro.getTerritorio("Italia"));
				break;

				case "Somalia":
				t.AddAdjacente(tabuleiro.getTerritorio("Nigeria"));
				t.AddAdjacente(tabuleiro.getTerritorio("Egito"));
				t.AddAdjacente(tabuleiro.getTerritorio("Angola"));
				t.AddAdjacente(tabuleiro.getTerritorio("Africa do Sul"));
				t.AddAdjacente(tabuleiro.getTerritorio("Arabia Saudita"));
				break;

				case "Africa do Sul":
				t.AddAdjacente(tabuleiro.getTerritorio("Angola"));
				t.AddAdjacente(tabuleiro.getTerritorio("Somalia"));
				break;

				//Ásia
				case "Siberia":
				t.AddAdjacente(tabuleiro.getTerritorio("Alasca"));
				t.AddAdjacente(tabuleiro.getTerritorio("Russia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Cazaquistao"));
				break;

				case "Cazaquistao":
				t.AddAdjacente(tabuleiro.getTerritorio("Siberia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Russia"));
				t.AddAdjacente(tabuleiro.getTerritorio("China"));
				t.AddAdjacente(tabuleiro.getTerritorio("Mongolia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Letonia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Turquia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Japao"));
				break;

				case "Russia":
				t.AddAdjacente(tabuleiro.getTerritorio("Siberia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Cazaquistao"));
				t.AddAdjacente(tabuleiro.getTerritorio("Estonia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Letonia"));
				break;

				case "China":
				t.AddAdjacente(tabuleiro.getTerritorio("Cazaquistao"));	
				t.AddAdjacente(tabuleiro.getTerritorio("Mongolia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Coreia do Norte"));
				t.AddAdjacente(tabuleiro.getTerritorio("Coreia do Sul"));
				t.AddAdjacente(tabuleiro.getTerritorio("Turquia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Paquistao"));
				t.AddAdjacente(tabuleiro.getTerritorio("India"));
				break;

				case "Mongolia":
				t.AddAdjacente(tabuleiro.getTerritorio("Cazaquistao"));
				t.AddAdjacente(tabuleiro.getTerritorio("China"));
				t.AddAdjacente(tabuleiro.getTerritorio("Japao"));
				break;

				case "Coreia do Norte":
				t.AddAdjacente(tabuleiro.getTerritorio("China"));
				t.AddAdjacente(tabuleiro.getTerritorio("Coreia do Sul"));
				t.AddAdjacente(tabuleiro.getTerritorio("Japao"));
				break;

				case "Coreia do Sul":
				t.AddAdjacente(tabuleiro.getTerritorio("China"));
				t.AddAdjacente(tabuleiro.getTerritorio("Coreia do Norte"));
				t.AddAdjacente(tabuleiro.getTerritorio("India"));
				t.AddAdjacente(tabuleiro.getTerritorio("Bangladesh"));
				t.AddAdjacente(tabuleiro.getTerritorio("Tailandia"));
				break;

				case "Japao":
				t.AddAdjacente(tabuleiro.getTerritorio("Mongolia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Coreia do Norte"));
				t.AddAdjacente(tabuleiro.getTerritorio("Cazaquistao"));
				break;

				case "Turquia":
				t.AddAdjacente(tabuleiro.getTerritorio("Cazaquistao"));
				t.AddAdjacente(tabuleiro.getTerritorio("China"));
				t.AddAdjacente(tabuleiro.getTerritorio("Letonia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Siria"));
				t.AddAdjacente(tabuleiro.getTerritorio("Paquistao"));
				t.AddAdjacente(tabuleiro.getTerritorio("Ucrania"));
				break;

				case "Letonia":
				t.AddAdjacente(tabuleiro.getTerritorio("Cazaquistao"));
				t.AddAdjacente(tabuleiro.getTerritorio("Russia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Turquia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Estonia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Polonia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Suecia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Ucrania"));
				break;

				case "Estonia":
				t.AddAdjacente(tabuleiro.getTerritorio("Letonia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Russia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Suecia"));
				break;

				case "Paquistao":
				t.AddAdjacente(tabuleiro.getTerritorio("China"));
				t.AddAdjacente(tabuleiro.getTerritorio("Turquia"));
				t.AddAdjacente(tabuleiro.getTerritorio("India"));
				t.AddAdjacente(tabuleiro.getTerritorio("Siria"));
				t.AddAdjacente(tabuleiro.getTerritorio("Ira"));
				break;

				case "India":
				t.AddAdjacente(tabuleiro.getTerritorio("Paquistao"));
				t.AddAdjacente(tabuleiro.getTerritorio("Coreia do Sul"));
				t.AddAdjacente(tabuleiro.getTerritorio("Bangladesh"));
				t.AddAdjacente(tabuleiro.getTerritorio("China"));
				t.AddAdjacente(tabuleiro.getTerritorio("Indonesia"));
				break;

				case "Bangladesh":
				t.AddAdjacente(tabuleiro.getTerritorio("India"));
				t.AddAdjacente(tabuleiro.getTerritorio("Coreia do Sul"));
				t.AddAdjacente(tabuleiro.getTerritorio("Tailandia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Indonesia"));
				break;

				case "Tailandia":
				t.AddAdjacente(tabuleiro.getTerritorio("Bangladesh"));
				t.AddAdjacente(tabuleiro.getTerritorio("Coreia do Sul"));
				break;

				case "Siria":
				t.AddAdjacente(tabuleiro.getTerritorio("Turquia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Paquistao"));
				t.AddAdjacente(tabuleiro.getTerritorio("Ira"));
				t.AddAdjacente(tabuleiro.getTerritorio("Iraque"));
				t.AddAdjacente(tabuleiro.getTerritorio("Jordania"));
				break;

				case "Ira":
				t.AddAdjacente(tabuleiro.getTerritorio("Paquistao"));
				t.AddAdjacente(tabuleiro.getTerritorio("Siria"));
				t.AddAdjacente(tabuleiro.getTerritorio("Iraque"));
				break;

				case "Iraque":
				t.AddAdjacente(tabuleiro.getTerritorio("Ira"));
				t.AddAdjacente(tabuleiro.getTerritorio("Siria"));
				t.AddAdjacente(tabuleiro.getTerritorio("Jordania"));
				t.AddAdjacente(tabuleiro.getTerritorio("Arabia Saudita"));
				break;

				case "Jordania":
				t.AddAdjacente(tabuleiro.getTerritorio("Siria"));
				t.AddAdjacente(tabuleiro.getTerritorio("Iraque"));
				t.AddAdjacente(tabuleiro.getTerritorio("Egito"));
				t.AddAdjacente(tabuleiro.getTerritorio("Arabia Saudita"));
				break;

				case "Arabia Saudita":
				t.AddAdjacente(tabuleiro.getTerritorio("Iraque"));
				t.AddAdjacente(tabuleiro.getTerritorio("Jordania"));
				t.AddAdjacente(tabuleiro.getTerritorio("Somalia"));
				break;

				//Europa
				case "Espanha":
				t.AddAdjacente(tabuleiro.getTerritorio("Argelia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Franca"));
				break;

				case "Franca":
				t.AddAdjacente(tabuleiro.getTerritorio("Espanha"));
				t.AddAdjacente(tabuleiro.getTerritorio("Italia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Reino Unido"));
				t.AddAdjacente(tabuleiro.getTerritorio("Suecia"));
				break;

				case "Italia":
				t.AddAdjacente(tabuleiro.getTerritorio("Argelia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Franca"));
				t.AddAdjacente(tabuleiro.getTerritorio("Polonia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Suecia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Romenia"));
				break;

				case "Reino Unido":
				t.AddAdjacente(tabuleiro.getTerritorio("Franca"));
				t.AddAdjacente(tabuleiro.getTerritorio("Groelandia"));
				break;

				case "Polonia":
				t.AddAdjacente(tabuleiro.getTerritorio("Letonia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Italia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Romenia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Ucrania"));
				break;

				case "Suecia":
				t.AddAdjacente(tabuleiro.getTerritorio("Estonia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Letonia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Italia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Franca"));
				break;

				case "Romenia":
				t.AddAdjacente(tabuleiro.getTerritorio("Egito"));	
				t.AddAdjacente(tabuleiro.getTerritorio("Italia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Polonia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Ucrania"));
				break;

				case "Ucrania":
				t.AddAdjacente(tabuleiro.getTerritorio("Polonia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Romenia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Turquia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Letonia"));
				break;

				//Oceania
				case "Indonesia":
				t.AddAdjacente(tabuleiro.getTerritorio("India"));
				t.AddAdjacente(tabuleiro.getTerritorio("Bangladesh"));
				t.AddAdjacente(tabuleiro.getTerritorio("Nova Zelandia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Australia"));
				break;

				case "Nova Zelandia":
				t.AddAdjacente(tabuleiro.getTerritorio("Indonesia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Australia"));
				break;

				case "Australia":
				t.AddAdjacente(tabuleiro.getTerritorio("Indonesia"));
				t.AddAdjacente(tabuleiro.getTerritorio("Nova Zelandia"));
				break;

				case "Perth":
				t.AddAdjacente(tabuleiro.getTerritorio("Australia"));
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
			this.cor = jogador.getCor();
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
}
