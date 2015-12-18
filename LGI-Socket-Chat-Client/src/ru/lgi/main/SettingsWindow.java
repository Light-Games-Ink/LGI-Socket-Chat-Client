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

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
	private JPanel panel;

	public SettingsWindow() {
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Settings");
		setSize(300, 170);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		c = null;
		
		panel = new JPanel();
		panel.setBounds(0, 0, 294, 138);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(10, 11, 75, 20);
		panel.add(lblNickname);
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 14));
				
		userNickname = new JTextField();
		userNickname.setBounds(92, 11, 86, 20);
		panel.add(userNickname);
		userNickname.setBackground(new Color(255, 255, 255));
		userNickname.setForeground(Color.black);
		userNickname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		userNickname.setHorizontalAlignment(SwingConstants.CENTER);
		userNickname.setColumns(10);
						
		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(10, 42, 75, 20);
		panel.add(lblColor);
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 14));
						
		selectedColor = new JPanel();
		selectedColor.setBounds(92, 42, 35, 18);
		panel.add(selectedColor);
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
								
		applyButton = new JButton("Apply");
		applyButton.setBounds(10, 98, 117, 29);
		panel.add(applyButton);
										
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(167, 98, 117, 29);
		panel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

	public void setAdmin() {
		AdminWindow admin = new AdminWindow();
		adminButton = new JButton("Admin");
		adminButton.setBounds(167, 58, 117, 29);
		panel.add(adminButton);
		panel.updateUI();
		adminButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Admin window
				admin.setVisible(true);
				}
		});
	}
}
