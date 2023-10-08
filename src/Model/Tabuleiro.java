import java.util.HashMap;

class Tabuleiro{
	HashMap<String,Territorio> map = new HashMap<String,Territorio>();
	public Tabuleiro() {
		Territorio t = new Territorio("Brasil");
		map.put(t.nome, t);
		t = new Territorio("EUA");
		map.put(t.nome, t);
		t = new Territorio("Angola");
		map.put(t.nome, t);
		t = new Territorio("Alaska");
		map.put(t.nome, t);
	}
	public boolean VerificarAtaque(Territorio atacante, Territorio defensor) {
		if (atacante.verificaAdjacencia(defensor)) {
			if(atacante.qntExercitos > 1) {
				return true;
			}
		}
		return false;
	}
	
	public void RealizaAtaque(Territorio atacante,Territorio defensor) {
		//TODO
	}
	
	public void MoverExercitos(int qntExercitos,Territorio origem,Territorio destino) {
		
		if (origem.qntExercitos > qntExercitos) {
			origem.qntExercitos -= qntExercitos;
			destino.qntExercitos += qntExercitos;
		}
		else {
			System.out.println("Nao pode mover essa quantidade de exercitos");
		}
	}
	
	public boolean VerificarObjetivoConcluido() {//TODO
		
	}
	
	public void VerificarExercitosASeremAdicionados() {//TODO
		
	}
	
	
	
	
}
