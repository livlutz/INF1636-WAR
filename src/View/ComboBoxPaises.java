package View;

import javax.swing.JComboBox;

//import Model.APIJogo;

class ComboBoxPaises extends JComboBox<String>{
	
	//Instancia a API do jogo
	//APIJogo apiJogo = APIJogo.getAPIJogo();
	
	//Construtor
	public ComboBoxPaises(){
		//adicionaPaises();
	}
	
	//Adiciona os países ao combobox
	public void adicionaPaises(String[] territorios) {
		for (String t: territorios) {
			this.addItem(t);
			//this.removeItem(t);
		}
		
	}
}
