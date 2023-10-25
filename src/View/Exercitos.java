package View;

import java.awt.*;
import javax.swing.*;

public class Exercitos extends JComponent{

		public static Exercitos exercitos = null;
		int posX = 10;
		int posY = 10;
		private Exercitos() {

		}
		protected void paintCOmponente(Graphics g) {
			Graphics2D g2d = (Graphics2D)g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(Color.WHITE);
			g2d.fillOval(posX, posY, 10, 10);
		}
		public static Exercitos getExercitos() {
			if (exercitos == null){
				exercitos = new Exercitos();
			}
			return exercitos;
		}
}
