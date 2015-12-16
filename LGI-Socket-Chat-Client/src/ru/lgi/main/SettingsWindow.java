/**
 *
 */
package ru.lgi.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * @author tails217
 *
 */

@SuppressWarnings("serial")
public class SettingsWindow extends JFrame {
	public JTextField userNickname;
	public JPanel selectedColor;
	public JButton applyButton;
	private JButton cancelButton;
	private JButton adminButton;
	public Color c;

	public SettingsWindow() {
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Settings");
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNickname.setBounds(10, 11, 75, 20);
		getContentPane().add(lblNickname);

		userNickname = new JTextField();
		userNickname.setBackground(new Color(255, 255, 255));
		userNickname.setForeground(Color.black);
		userNickname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		userNickname.setHorizontalAlignment(SwingConstants.CENTER);
		userNickname.setBounds(95, 13, 86, 20);
		getContentPane().add(userNickname);
		userNickname.setColumns(10);
		c = null;
		JLabel lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblColor.setBounds(10, 42, 75, 20);
		getContentPane().add(lblColor);

		selectedColor = new JPanel();
		selectedColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				c = JColorChooser.showDialog(rootPane, // Dialog appears over
														// this
						"Pick a Color", // Dialog title
						Color.black); // Default color selection
				try {
					selectedColor.setBackground(c.darker());
					userNickname.setForeground(c.darker());
				} catch (Exception ex) {

				}
			}
		});
		selectedColor.setBackground(Color.black);
		selectedColor.setBounds(95, 44, 20, 18);
		getContentPane().add(selectedColor);

		applyButton = new JButton("Apply");
		applyButton.setBounds(10, 73, 117, 29);
		getContentPane().add(applyButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(167, 73, 117, 29);
		getContentPane().add(cancelButton);

	}

	public void setAdmin() {
		adminButton = new JButton("Admin");
		adminButton.setBounds(177, 40, 117, 29);
		getContentPane().add(adminButton);
		adminButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Admin window
			}

		});
	}
}
