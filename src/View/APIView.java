package View;

import java.util.ArrayList;


public class APIView implements ObservadoIF{
	private static APIView APIView = null;
	CharacterSelectionPanel characterSelectionPanel = CharacterSelectionPanel.getCharacterSelectionPanel();
	StartingPanel startingPanel = StartingPanel.getStartingPanel();
	//private Gerente gerente = Gerente.getGerente();

	// Observadores
	private ArrayList<ObservadorIF> observadores = new ArrayList<ObservadorIF>();

	// Array de nomes dos jogadores
	String[] nomesJogadores = new String[6];

	// Array de nomes de territorios 
	String[] listaTerritorios;

	// Array de infos para observer [int, int, int, int, int, String, String, int[]]
	// 0 - Estado para indicar telas (Começando agora = -1; Posicionando = 0; Atacando = 1; Reposicionamento = 2; Passando a vez = 3; Fim de jogo = 4)
	// 1 - Vez (Int pos do array de nomesJogadores)
	// 2 - Realizando posicionamento - Estado (Não está realizando = 0; Selecionou territorio = 1; Posicionamento terminado = 2)
	// 3 - Realizando ataque - Estado (Não está realizando = 0; Selecionou atacante = 1; Selecionou defensor = 2; Rolou Dados = 3; Ataque terminado = 4)
	// 4 - Realizando reposicionamento - Estado (Não está realizando = 0; Selecionou origem = 1; Selecionou destino = 2; Reposicionamento terminado = 3)
	// 5 - String com o nome do territorio principal/origem (Atacante, origem de reposicionamento e receptor de posicionamento de exércitos)
	// 6 - String com o nome do territorio destino (Defensor ou destino de reposicionamento)
	// 7 - Array de int com os resultados dos dados (Primeiros 3 ataque, últimos 3 defesa) - 0 = não rolado, -1 = não pode jogar esse dado, 1-6 = valor do dado
	Object[] infos = new Object[8];

	// Construtor privado para o singleton
	private APIView(){
	}

	// Singleton
	public static APIView getAPIView(){
		if(APIView == null){
			APIView = new APIView();
		}
		return APIView;
	}

	
	@Override
	public void add(ObservadorIF o) {
		observadores.add(o);
	}

	@Override
	public void remove(ObservadorIF o) {
		observadores.remove(o);
	}

	@Override
	public Object get() {
		return infos;
	}

	public void selecionarJogadores(int numJogadores){
		characterSelectionPanel.setNumJogadores(numJogadores);
		characterSelectionPanel.drawJogadores();
	}

	public void iniciarJogo(){
		characterSelectionPanel.setVisible(false);
		startingPanel.setVisible(true);
	}

	public void setNomesJogadores(String[] nomes){
		nomesJogadores = nomes;
	}

	public String[] getNomesJogadores(){
		return nomesJogadores;
	}

	public Object[] getInfos(){
		return infos;
	}

}
