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

	
}
