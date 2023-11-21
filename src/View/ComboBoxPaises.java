package View;

import javax.swing.JComboBox;

import Model.APIJogo;

class ComboBoxPaises extends JComboBox<String>{
	
	//Instancia a API do jogo
	APIJogo apiJogo = APIJogo.getAPIJogo();
	
	//Construtor
	public ComboBoxPaises(){
		adicionaPaises();
	}
	
	//Adiciona os países ao combobox
	private void adicionaPaises() {
		String[] territorios = apiJogo.getTerritoriosLista();
		for (String t: territorios) {
			this.addItem(t);
			//this.removeItem(t);
		}
		
	}
}
