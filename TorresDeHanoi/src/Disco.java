import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Disco extends JLabel{
	public int posX, posY, width, heigth, centro;
	public Disco(int posX, int posY, int width, int heigth) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.heigth = heigth;
		
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
		
	}
}
