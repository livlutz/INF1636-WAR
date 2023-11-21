package Controller;
import View.*;
import Model.*;

public class main {
	//Instância do MainFrame
	private static MainFrame mainFrame;
	public static void main(String[] args) {
		//Inicializa a APIJogo e o MainFrame
		APIJogo a = APIJogo.getAPIJogo();
		mainFrame = MainFrame.getMainFrame();
	}

}
