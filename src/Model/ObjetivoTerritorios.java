package Model;

class ObjetivoTerritorios extends Objetivo{
        
        private int qtdTerritorios;
        
        public ObjetivoTerritorios(int qtdTerritorios) {
            this.qtdTerritorios = qtdTerritorios;
            this.descricao = "Conquistar " + qtdTerritorios + " TERRITÓRIOS";
            if (qtdTerritorios == 18){
                this.descricao += "e ocupar cada um deles com pelo menos dois exércitos";
            }
        }
        
        public boolean alcancou(Jogador j) {
            // Se o jogador não conquistou a quantidade de territórios necessária, objetivo não alcançado
            if(j.getQtdTerritorios() < this.qtdTerritorios) {
                return false;
            }

            // Se o jogador precisava conquistar 24 territórios e conquistou, objetivo alcançado
            if (this.qtdTerritorios == 24 && j.getQtdTerritorios() >= 24){
                return true;
            }
            int cont = 0;
            // Se o jogador precisava conquistar 18 territórios e conquistou, verifica se 18 deles possuem pelo menos 2 exércitos
            for (Territorio t: j.getTerritorios()){
                if (t.getQtdExercitos() >= 2)
                    cont++;
            }

            // Se 18 territórios tem pelo menos 2 exércitos, objetivo alcançado
            if (cont >= this.qtdTerritorios)
                return true;

            return false;
            
        }
        
}