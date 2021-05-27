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

public class ForgotPassCodeInterface {
	public static JPanel forgotPassCodePanel = new JPanel();

	private JLabel title = new JLabel();
	private JLabel background = new JLabel();
	private JLabel code = new JLabel();

	public static JTextField codeIn = new JTextField();

	public static JButton back = new JButton("Back");
	public static JButton next = new JButton("Next");

	public ForgotPassCodeInterface() {
		forgotPassCodePanel.setSize(1080, 719);
		forgotPassCodePanel.setLayout(null);
		forgotPassCodePanel.setSize(1080, 720);
		forgotPassCodePanel.setVisible(false);
		LoginInterface.frame.getContentPane().add(forgotPassCodePanel);

		title = new JLabel("Welcome to Revolve");
		title.setBounds(0, 20, 1080, 50);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 50));
		title.setForeground(Color.white);
		forgotPassCodePanel.add(title);

		code = new JLabel("Code");
		code.setBounds(0, 180, 1080, 50);
		code.setHorizontalAlignment(SwingConstants.CENTER);
		code.setFont(new Font("Tahoma", Font.BOLD, 31));
		code.setForeground(Color.white);
		forgotPassCodePanel.add(code);

		codeIn.setBounds(390, 250, 300, 30);
		codeIn.setFont(RegisterInterface.textFont);
		codeIn.setBackground(Color.decode("#DFDFE2"));
		forgotPassCodePanel.add(codeIn);

		back.setBounds(390, 350, 120, 25);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forgotPassCodePanel.add(back);

		next.setBounds(570, 350, 120, 25);
		next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forgotPassCodePanel.add(next);

		background = new JLabel("");
		background.setIcon(new ImageIcon("src//Images//Backgrounds//bank_background.jpg"));
		background.setSize(1080, 720);
		forgotPassCodePanel.add(background);

	}

}
