/**
 * 
 */
package ru.lgi.main;

import javax.swing.JFrame;

/**
 * @author LaughingMaan
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	/**
	 * @constructor
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLocation((this.getX() - 800 / 2), (this.getY() - 600 / 2));
		setSize(800, 600);
		setResizable(false);
		setVisible(true);
	}

}
