package mypackage;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameWindow {
	
	JFrame frame;
	String title;
	int width, height;

	public GameWindow(String title, int width, int height) {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new GameDisplay(width, height));
		
		frame.setVisible(true);
	}

}
