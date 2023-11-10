package Controller;
import View.*;
import Model.*;
public class main {
	
	private static MainFrame mainFrame;
	public static void main(String[] args) {
		APIJogo a = APIJogo.getAPIJogo();
		a.comecaJogo();
		mainFrame = MainFrame.getMainFrame();
	}

}
