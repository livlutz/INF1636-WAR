package Model;

import java.awt.Color;

class ObjetivoDestruir extends Objetivo{
    // Guarda o jogador que deve ser destruído
    private Jogador jAlvo;

    // Construtor
    public ObjetivoDestruir(Jogador jogador){
        this.jAlvo = jogador;
        String descricao = "Destruir totalmente os EXÉRCITOS ";
        String cor = "";
        //cores : branco, preto, azul, verde, vermelho, amarelo
        if (jAlvo.getCor().equals(new Color(255, 255, 255))) {
            cor = "BRANCOS";
        } 

        else if (jAlvo.getCor().equals(new Color(0, 0, 0))) {
            cor = "PRETOS";
        } 

        else if (jAlvo.getCor().equals(new Color(255, 255, 0))) {
            cor = "AMARELOS";
        } 

        else if (jAlvo.getCor().equals(new Color(0, 0, 255))) {
            cor = "AZUIS";
        } 

        else if (jAlvo.getCor().equals(new Color(0, 255, 0))) {
            cor = "VERDES";
        } 

        else if (jAlvo.getCor().equals(new Color(255, 0, 0))) {
            cor = "VERMELHOS";
        }
        
       //Adiciona a cor do jogador alvo ao objetivo
        descricao += cor + ", se é você quem possui os exércitos " + cor 
            + " ou se o jogador que os possui for elimidado por um outro jogador, o seu objetivo passa a ser automaticamente conquistar 24 TERRITÓRIOS";

        /* descricao += cor + "se é você quem possui os exércitos " + cor 
            + " ou se o jogador que os possui for elimidado por um outro jogador, o seu objetivo passa a ser automaticamente conquistar 24 TERRITÓRIOS"; */
        this.descricao = descricao;
    }

    //Verifica se o objetivo foi alcançado
    public boolean alcancou(Jogador j){
        // Se o jogador não pegou o objetivo de se destruir
        if (j.getCor() != jAlvo.getCor()){
            // Se o alvo ainda não foi eliminado
            if (jAlvo.getQtdTerritorios() > 0)
            // Não alcançou o objetivo ainda
                return false;

            // Se o alvo foi eliminado nessa rodada
             if (jAlvo.getEliminadoNessaRodada() == true && jAlvo.getJMatou() == j)
             // Objetivo alcançado
                return true;
        }
        
        // Se o jogador pegou o objetivo de se destruir ou se seu alvo já foi destruído por outro jogador
        if (j.getQtdTerritorios() >= 24)
            return true;

        return false;
           
    }

    //Retorna o jogador que deve ser destruído
    public Jogador getJAlvo(){
        return this.jAlvo;
    }
}
