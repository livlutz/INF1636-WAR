package Controller;
import View.*;
import Model.*;
public class main {
	
	private static MainFrame mainFrame;
	public static void main(String[] args) {
		mainFrame = MainFrame.getMainFrame();
		APIJogo a = APIJogo.getAPIJogo();
		a.comecaJogo();
	}

}
