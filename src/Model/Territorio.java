package Model;
import java.util.ArrayList;

class Territorio {

		Jogador jogador;
		// Posição posicao;
		int qntExercitos;
		private ArrayList<Territorio> adjacentes = new ArrayList<Territorio>();
		
		protected Territorio(){
			// inicializa para se chamar getters não ter problema
			this.jogador = NULL;
			this.qntExercitos = 0;
		}
			
		// Adicionar território adjacente para inicialização
		protected void AddAdjacente (Territorio adj) {
			this.adjacentes.add(adj);
		}
		
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

		public ArrayList<Territorio> getAdjacentes() {
			return adjacentes;
		}

	
}
