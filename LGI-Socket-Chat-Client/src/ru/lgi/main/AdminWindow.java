/**
 * 
 */

package ru.lgi.main;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * @author tails217
 *
 */

@SuppressWarnings("serial")
public class AdminWindow extends JFrame {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AdminWindow() {
		setResizable(false);
		setTitle("Admin Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(375,300);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ban",
		TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(6, 6, 172, 100);
		getContentPane().add(panel);
		panel.setLayout(null);

		JComboBox usersOnlineList = new JComboBox();
		usersOnlineList.setBounds(6, 20, 160, 27);
		panel.add(usersOnlineList);
		usersOnlineList.setModel(new DefaultComboBoxModel(new String[] { "", "Petush", "Cock", "Pipi", "Chlen",
				"Nigga-" + "-nigga", "uesr1", "user2", "user3", "user4", "user5", "user6", "seru7", "user8" }));
		usersOnlineList.setSelectedIndex(0);
		usersOnlineList.setEditable(true);

		JButton banButton = new JButton("Ban");
		banButton.setBounds(6, 59, 160, 29);
		panel.add(banButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Banned Users", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(188, 6, 172, 100);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnUnban = new JButton("Unban");
		btnUnban.setBounds(6, 63, 160, 29);
		panel_1.add(btnUnban);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(6, 24, 160, 27);
		panel_1.add(comboBox);
		
		JButton btnClearHistory = new JButton("Clear History");
		btnClearHistory.setBounds(6, 113, 354, 29);
		getContentPane().add(btnClearHistory);

	}
}
