package View;

import Controller.Gerente;

public class APIView {
	private static APIView APIView = null;
	CharacterSelectionPanel characterSelectionPanel = CharacterSelectionPanel.getCharacterSelectionPanel();
	StartingPanel startingPanel = StartingPanel.getStartingPanel();
	private Gerente gerente = Gerente.getGerente();

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

	public void selecionarJogadores(int numJogadores){
		characterSelectionPanel.setNumJogadores(numJogadores);
		characterSelectionPanel.drawJogadores();
	}

	public void iniciarJogo(){
		characterSelectionPanel.setVisible(false);
		startingPanel.setVisible(true);
	}

	public boolean podeComecarJogo(){
		System.out.println(characterSelectionPanel.getNomesJogadores());
		if (gerente.verificaComeco(characterSelectionPanel.getNomesJogadores(), characterSelectionPanel.getCoresJogadores()) == false){
			characterSelectionPanel.getNomesJogadores().clear();
			characterSelectionPanel.getCoresJogadores().clear();
			return false;
		}
		return true;
	}

}
