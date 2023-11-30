package Model;

import java.util.HashMap;
import java.util.Map;

class ObjetivoContinentes extends Objetivo{
    //Guarda os continentes e a quantidade de continentes que o jogador deve dominar
    private Continente cont1;
    private Continente cont2;
    private int qtdContinentes;

    //Define o objetivo
    public ObjetivoContinentes(Continente cont1, Continente cont2, boolean cont3){
        this.cont1 = cont1;
        this.cont2 = cont2;
        if (cont3 == true){
            this.qtdContinentes = 3;
            this.descricao = "Conquistar os continentes " + cont1.getNome() + ", " + cont2.getNome() + " e mais um continente à sua escolha";
        }
        else{
            this.qtdContinentes = 2;
            this.descricao = "Conquistar os continentes " + cont1.getNome() + " e " + cont2.getNome();
        }
    }

    // Verifica se o jogador alcançou o objetivo
    public boolean alcancou(Jogador j){

        // Se o jogador não dominar os dois continentes não alcançou o objetivo
        if (cont1.dominado(j) != true) 
            return false;
        if (cont2.dominado(j) != true)
            return false;

        // Se dominou os dois e só tinham dois, alcançou o objetivo
        if (qtdContinentes == 2)
            return true;

        HashMap <String, Continente> continentes = Tabuleiro.getTabuleiro().getMapContinentes();
        
		// para cada continente, verifica se o jogador dominou se não for os já verificados
		for (Map.Entry<String, Continente> c: continentes.entrySet()){
			if (c.getValue() != cont1 && c.getValue() != cont2 && c.getValue().dominado(j) == true)
                return true;
		}

        // Se não dominou nenhum outro continente além dos dois, não alcançou o objetivo
        return false;

    }

    //Retorna os continentes que o jogador deve dominar
    public Continente getCont1(){
        return this.cont1;
    }

    public Continente getCont2(){
        return this.cont2;
    }

    //Retorna a quantidade de continentes que o jogador deve dominar
    public int getQtdContinentes(){
        return this.qtdContinentes;
    }

}
