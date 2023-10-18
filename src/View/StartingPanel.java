package View;

import javax.swing.*;

public class StartingPanel extends JPanel{
	
	JButton iniciar = new JButton("Iniciar novo jogo");
	JButton continuar = new JButton("Continuar jogo");

	public StartingPanel() {
		add(iniciar);
		add(continuar);
	}
}
