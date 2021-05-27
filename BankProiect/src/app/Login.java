package app;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import card.Account;
import database.Database;
import graphicInterface.ChangePasswordInterface;
import graphicInterface.ForgotPassCodeInterface;
import graphicInterface.ForgotPasswordInterface;
import graphicInterface.LoginInterface;
import graphicInterface.RegisterInterface;
import graphicInterface.UserApplicationInterface;

public class Login {

	public static int failedAttempt = 0;
	public static int index = 0;
	public static String email;
	public static String userFirstName;
	public static String totalAmount;

	public Login() {
		loginDataCheck();
		signUp();

		new ChangePasswordInterface();
		new ForgotPassCodeInterface();
	}

	private void loginDataCheck() {
		// check by clicking login button
		LoginInterface.loginButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				checkLoginData();
			}
		});

		// check by clicking enter
		LoginInterface.passwordIn.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkLoginData();
				}
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void checkLoginData() {
		if (LoginInterface.usernameIn.getText().equals("")) {
			JOptionPane.showMessageDialog(LoginInterface.loginPanel, "Please enter your Username");
		} else if (LoginInterface.passwordIn.getText().equals("")) {
			JOptionPane.showMessageDialog(LoginInterface.loginPanel, "Please enter your Password");
		} else if (Database.checkLoginData(LoginInterface.usernameIn.getText(), LoginInterface.passwordIn.getText())) {
			JOptionPane.showMessageDialog(LoginInterface.loginPanel, "Login succesfully");
			LoginInterface.frame.setVisible(false);
			failedAttempt = 0;
			email = Database.getUserEmail(LoginInterface.usernameIn.getText());

			new UserApplicationInterface();
			new UserApplication();
			userFirstName = Database.getUserName();
			UserApplicationInterface.title.setText("Welcome, " + userFirstName + "!");
			totalAmount = Account.calculateTotalAmount() + "EUR";
			UserApplicationInterface.amountTitle.setText("Amount: " + totalAmount);
			LoginInterface.frame.setVisible(false);
			Database.returnTransactionData(Login.email);

		} else {
			JOptionPane.showMessageDialog(LoginInterface.loginPanel, "Username or Password are wrong");
			failedAttempt++;
		}
		if (failedAttempt == 3) {
			int dialogButton = 0;
			dialogButton = JOptionPane.showConfirmDialog(null, "Do you want to change your password?",
					"Recover Password", dialogButton);
			if (dialogButton == JOptionPane.YES_OPTION) {
				LoginInterface.loginPanel.setVisible(false);
				ForgotPasswordInterface.forgotPassPanel.setVisible(true);
				Login.index = 2;
			} else {
				LoginInterface.loginPanel.setVisible(true);
				ForgotPasswordInterface.forgotPassPanel.setVisible(false);
				failedAttempt = 0;
			}
		}
	}

	public void signUp() {
		LoginInterface.signUp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				LoginInterface.loginPanel.setVisible(false);
				new RegisterInterface();
			}
		});
	}
}