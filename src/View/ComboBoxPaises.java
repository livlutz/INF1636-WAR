package View;

import javax.swing.JComboBox;

import Model.APIJogo;

class ComboBoxPaises extends JComboBox<String>{
	
	APIJogo apiJogo = APIJogo.getAPIJogo();
	
	public ComboBoxPaises(){
		adicionaPaises();
	}
	
	private void adicionaPaises() {
		String[] territorios = apiJogo.getTerritoriosLista();
		for (String t: territorios) {
			this.addItem(t);
			//this.removeItem(t);
		}
		
	}
}
