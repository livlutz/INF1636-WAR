package Model;

public class APIJogo {
    private static APIJogo APIJogo = null;

    // Construtor privado para o singleton
    private APIJogo(){

    }

    // Singleton
    public static APIJogo getAPIJogo(){
        if(APIJogo == null){
            APIJogo = new APIJogo();
        }
        return APIJogo;
    }
}
