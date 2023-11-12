package View;

import Controller.Gerente;

public class APIView implements ObservadorIF{
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

	// Notifica o observador
	@Override
	public void notifica(ObservadoIF o){
		GamePanel obj = (GamePanel)o;
		Object[] infos = (Object[])obj.get();
		if ((int)infos[0] == -1){
			obj.setNomesJogadores(gerente.getNomesJogadores());
			obj.infos[0] = 0;
			//TODO
		}
		//TODO
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
		if (!gerente.comecaJogo(characterSelectionPanel.getNomesJogadores(), characterSelectionPanel.getCoresJogadores())){
			characterSelectionPanel.getNomesJogadores().clear();
			characterSelectionPanel.getCoresJogadores().clear();
			return false;
		}
		return true;
	}

}
