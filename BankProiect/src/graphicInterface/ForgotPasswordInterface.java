package graphicInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import app.ForgotPassword;

public class ForgotPasswordInterface {
	public static JPanel forgotPassPanel = new JPanel();

	private JLabel title = new JLabel();
	private JLabel background = new JLabel();
	private JLabel email = new JLabel();

	public static JTextField emailIn = new JTextField();

	public static JButton back = new JButton("Back");
	public static JButton next = new JButton("Next");

	public ForgotPasswordInterface() {
		forgotPassPanel.setSize(1080, 719);
		forgotPassPanel.setLayout(null);
		forgotPassPanel.setSize(1080, 720);
		forgotPassPanel.setVisible(false);
		LoginInterface.frame.getContentPane().add(forgotPassPanel);

		title = new JLabel("Welcome to Revolve");
		title.setBounds(0, 20, 1080, 50);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 50));
		title.setForeground(Color.white);
		forgotPassPanel.add(title);

		email = new JLabel("Email");
		email.setBounds(0, 180, 1080, 50);
		email.setHorizontalAlignment(SwingConstants.CENTER);
		email.setFont(new Font("Tahoma", Font.BOLD, 31));
		email.setForeground(Color.white);
		forgotPassPanel.add(email);

		emailIn.setBounds(390, 250, 300, 30);
		emailIn.setFont(RegisterInterface.textFont);
		emailIn.setBackground(Color.decode("#DFDFE2"));
		forgotPassPanel.add(emailIn);

		back.setBounds(390, 350, 120, 25);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forgotPassPanel.add(back);

		next.setBounds(570, 350, 120, 25);
		next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forgotPassPanel.add(next);

		background = new JLabel("");
		background.setIcon(new ImageIcon("src//Images//Backgrounds//bank_background.jpg"));
		background.setSize(1080, 720);
		forgotPassPanel.add(background);

		new ForgotPassword();

	}

}
