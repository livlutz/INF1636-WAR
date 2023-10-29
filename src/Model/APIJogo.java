package Model;

public class APIJogo {
    private static Jogo APIJogo = null;

    // Construtor privado para o singleton
    private APIJogo(){

    }

    // Singleton
    public static Jogo getAPIJogo(){
        if(APIJogo == null){
            APIJogo = new APIJogo();
        }
        return APIJogo;
    }
}
