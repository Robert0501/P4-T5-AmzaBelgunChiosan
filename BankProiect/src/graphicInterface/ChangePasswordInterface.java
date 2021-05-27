package graphicInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class ChangePasswordInterface {
	public static JPanel changePasswordPanel = new JPanel();

	private JLabel title = new JLabel();
	private JLabel background = new JLabel();
	private JLabel password = new JLabel();
	private JLabel rePassword = new JLabel();

	public static JPasswordField passwordIn = new JPasswordField();
	public static JPasswordField rePasswordIn = new JPasswordField();

	public static JButton back = new JButton("Back");
	public static JButton change = new JButton("Change Password");

	public ChangePasswordInterface() {
		changePasswordPanel.setSize(1080, 719);
		changePasswordPanel.setLayout(null);
		changePasswordPanel.setSize(1080, 720);
		changePasswordPanel.setVisible(false);
		LoginInterface.frame.getContentPane().add(changePasswordPanel);

		title = new JLabel("Welcome to Revolve");
		title.setBounds(0, 20, 1080, 50);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 50));
		title.setForeground(Color.white);
		changePasswordPanel.add(title);

		password = new JLabel("Password");
		password.setBounds(0, 180, 1080, 50);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("Tahoma", Font.BOLD, 31));
		password.setForeground(Color.white);
		changePasswordPanel.add(password);

		passwordIn.setBounds(390, 250, 300, 30);
		passwordIn.setFont(RegisterInterface.textFont);
		passwordIn.setBackground(Color.decode("#DFDFE2"));
		changePasswordPanel.add(passwordIn);

		rePassword = new JLabel("Retype Password");
		rePassword.setBounds(0, 350, 1080, 50);
		rePassword.setHorizontalAlignment(SwingConstants.CENTER);
		rePassword.setFont(new Font("Tahoma", Font.BOLD, 31));
		rePassword.setForeground(Color.white);
		changePasswordPanel.add(rePassword);

		rePasswordIn.setBounds(390, 420, 300, 30);
		rePasswordIn.setFont(RegisterInterface.textFont);
		rePasswordIn.setBackground(Color.decode("#DFDFE2"));
		changePasswordPanel.add(rePasswordIn);

		back.setBounds(390, 520, 120, 25);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		changePasswordPanel.add(back);

		change.setBounds(570, 520, 120, 25);
		change.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		changePasswordPanel.add(change);

		background = new JLabel("");
		background.setIcon(new ImageIcon("src//Images//Backgrounds//bank_background.jpg"));
		background.setSize(1080, 720);
		changePasswordPanel.add(background);

	}

}
