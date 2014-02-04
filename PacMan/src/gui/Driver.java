package gui;

import javax.swing.JFrame;

/**
 * Starting point of the program.
 * 
 * @author Sanket Chandorkar
 */
public class Driver {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JFrame frame = new ClientFrame();
		frame.show();
	}
}