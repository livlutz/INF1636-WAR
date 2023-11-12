package View;

import java.util.ArrayList;

import javax.swing.JComboBox;

import Model.APIJogo;

public class ComboBoxPaises extends JComboBox<String>{
	APIJogo	apiJogo = APIJogo.getAPIJogo();
	String[] territorios = apiJogo.getTerritoriosLista();
	
	public ComboBoxPaises(){
		adicionaPaises();
	}
	
	void adicionaPaises() {
		for (String t: territorios) {
			this.addItem(t);
			System.out.printf("%s\n",t);
		}
		
	}
}
