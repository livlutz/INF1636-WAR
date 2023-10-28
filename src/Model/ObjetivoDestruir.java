package Model;


class ObjetivoDestruir extends Objetivo{
    private Jogador jAlvo;

    public ObjetivoDestruir(Jogador jogador){
        this.jAlvo = jogador;
        String descricao = "Destruir totalmente os EXÉRCITOS ";
        String cor = "";
        switch(jogador.getCor()){
            case 0:
                cor = "BRANCOS";
                break;
            case 1:
                cor = "PRETOS";
                break;
            case 2:
                cor = "VERMELHOS";
                break;
            case 3:
                cor = "AZUIS";
                break;
            case 4:
                cor = "AMARELOS";
                break;
            case 5:
                cor = "VERDES";
                break;
        }
        descricao += cor + "se é você quem possui os exércitos " + cor 
            + " ou se o jogador que os possui for elimidado por um outro jogador, o seu objetivo passa a ser automaticamente conquistar 24 TERRITÓRIOS";
        this.descricao = descricao;
    }

    public boolean alcancou(Jogador j){
        // Se o jogador não pegou o objetivo de se destruir
        if (j.getCor() != jAlvo.getCor()){
            // Se o alvo ainda não foi eliminado
            if (jAlvo.getQtdTerritorios() > 0)
            // Não alcançou o objetivo ainda
                return false;

            // Se o alvo foi eliminado nessa rodada
             if (jAlvo.getEliminadoNessaRodada() == true)
             // Objetivo alcançado
                return true;
        }
        
        // Se o jogador pegou o objetivo de se destruir ou se seu alvo já foi destruído por outro jogador
        if (j.getQtdTerritorios() >= 24)
            return true;

        return false;
           
    }
}
