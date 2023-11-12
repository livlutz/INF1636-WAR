package View;

import javax.swing.JComboBox;

import Model.APIJogo;

public class ComboBoxPaises extends JComboBox<String>{
	APIJogo	apiJogo = APIJogo.getAPIJogo();
	
	public ComboBoxPaises(){
		adicionaPaises();
	}
	
	private void adicionaPaises() {
		String[] territorios = apiJogo.getTerritoriosLista();
		for (String t: territorios) {
			System.out.printf("%s\n",t);
			this.addItem(t);
		}
		
	}
}
