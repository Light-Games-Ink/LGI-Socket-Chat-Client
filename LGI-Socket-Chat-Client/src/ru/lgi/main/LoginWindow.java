/**
 * 
 */
package ru.lgi.main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author LaughingMaan
 *
 */
@SuppressWarnings("serial")
public class LoginWindow extends JFrame {
	private JPanel panel;
	private JTextField loginField;
	private JPasswordField passwordField;
	public JButton btnLogIn, btnRegister;
	private JLabel lblUsername, lblPassword;

	/**
	 * @constructor
	 **/
	public LoginWindow() {
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Login");
		setSize(273, 142);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		panel = new JPanel();
		panel.setBounds(0, 0, 267, 110);
		getContentPane().add(panel);
		panel.setLayout(null);

		btnLogIn = new JButton("Log In");
		btnLogIn.setBounds(179, 86, 64, 16);
		panel.add(btnLogIn);

		btnRegister = new JButton("Register");
		btnRegister.setBounds(92, 86, 76, 16);
		panel.add(btnRegister);

		lblUsername = new JLabel("Username");
		lblUsername.setBounds(6, 21, 71, 16);
		panel.add(lblUsername);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(6, 56, 71, 16);
		panel.add(lblPassword);

		loginField = new JTextField();
		loginField.setBounds(89, 16, 156, 26);
		panel.add(loginField);
		loginField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(89, 51, 156, 26);
		panel.add(passwordField);
	}

	public JTextField getLoginField() {
		return loginField;
	}

	public void setLoginField(JTextField loginField) {
		this.loginField = loginField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
}
