package app;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JOptionPane;

import database.Database;
import email.EmailThread;
import graphicInterface.ChangePasswordInterface;
import graphicInterface.ForgotPassCodeInterface;
import graphicInterface.ForgotPasswordInterface;
import graphicInterface.LoginInterface;
import regex.RegEx;

public class ForgotPassword {

	public static int code;

	EmailThread codeEmail[] = new EmailThread[10];
	int codeEmailCounter = 0;

	EmailThread newPasswordEmail[] = new EmailThread[10];
	int newPasswordEmailCounter = 0;

	public ForgotPassword() {
		for (int i = 0; i < 10; i++) {
			codeEmail[i] = new EmailThread();
			newPasswordEmail[i] = new EmailThread();
		}
		backButton();
		nextButtonToCode();
		nextButtonToChangePassword();
		changePasswordButton();
	}

	private void backButton() {
		ForgotPasswordInterface.back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ForgotPasswordInterface.forgotPassPanel.setVisible(false);
				LoginInterface.loginPanel.setVisible(true);
				Login.failedAttempt = 0;
			}
		});

		ForgotPassCodeInterface.back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ForgotPassCodeInterface.forgotPassCodePanel.setVisible(false);
				LoginInterface.loginPanel.setVisible(true);
				Login.failedAttempt = 0;
			}
		});

		ChangePasswordInterface.back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ChangePasswordInterface.changePasswordPanel.setVisible(false);
				LoginInterface.loginPanel.setVisible(true);
				Login.failedAttempt = 0;
				ForgotPassCodeInterface.codeIn.setText("");
			}

		});
	}

	private void checkEmail() {
		if (ForgotPasswordInterface.emailIn.getText().equals("")) {
			JOptionPane.showMessageDialog(ForgotPasswordInterface.forgotPassPanel, "Please enter your Email");
		} else if (Database.checkUsernameAndEmail(LoginInterface.usernameIn.getText(),
				ForgotPasswordInterface.emailIn.getText())) {
			code = generateChangePassCode();
			codeEmail[codeEmailCounter].start();
			codeEmailCounter++;
			JOptionPane.showMessageDialog(ForgotPasswordInterface.forgotPassPanel,
					"You'll shortly receive an email with the code in order to change your password");
			ForgotPasswordInterface.forgotPassPanel.setVisible(false);
			ForgotPassCodeInterface.forgotPassCodePanel.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(ForgotPasswordInterface.forgotPassPanel, "We could't find username: "
					+ LoginInterface.usernameIn.getText() + " for email: " + ForgotPasswordInterface.emailIn.getText());

		}
	}

	private void nextButtonToCode() {
		ForgotPasswordInterface.next.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				checkEmail();
			}
		});

		ForgotPasswordInterface.emailIn.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkEmail();
				}
			}
		});
	}

	private void checkCode() {
		if (code == Integer.parseInt(ForgotPassCodeInterface.codeIn.getText())) {
			ForgotPassCodeInterface.forgotPassCodePanel.setVisible(false);
			ChangePasswordInterface.changePasswordPanel.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(ForgotPassCodeInterface.forgotPassCodePanel,
					"The code you provided is incorrect!");
		}
	}

	private void nextButtonToChangePassword() {
		ForgotPassCodeInterface.next.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				checkCode();
			}
		});

		ForgotPassCodeInterface.codeIn.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkCode();
				}
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void checkEnteredPassword() {
		if (ChangePasswordInterface.passwordIn.getText().equals("")) {
			JOptionPane.showMessageDialog(ChangePasswordInterface.changePasswordPanel, "Enter a new Password");
		} else if (ChangePasswordInterface.rePasswordIn.getText().equals("")) {
			JOptionPane.showMessageDialog(ChangePasswordInterface.changePasswordPanel, "Enter retype the new Password");
		} else if (!ChangePasswordInterface.passwordIn.getText()
				.equals(ChangePasswordInterface.rePasswordIn.getText())) {
			JOptionPane.showMessageDialog(ChangePasswordInterface.changePasswordPanel,
					"Both passwords must be the same");
		} else if (!RegEx.checkPassword(ChangePasswordInterface.passwordIn.getText())) {
			JOptionPane.showMessageDialog(ChangePasswordInterface.changePasswordPanel,
					"The Password is not strong enough.");
		} else {
			Login.index = 3;
			Login.failedAttempt = 0;
			newPasswordEmail[newPasswordEmailCounter].start();
			newPasswordEmailCounter++;
			Database.updatePassword(ChangePasswordInterface.passwordIn.getText(), LoginInterface.usernameIn.getText());
			JOptionPane.showMessageDialog(ChangePasswordInterface.changePasswordPanel,
					"Your password has been successfully changed!");
			ChangePasswordInterface.changePasswordPanel.setVisible(false);
			LoginInterface.loginPanel.setVisible(true);
			LoginInterface.usernameIn.setText("");
			LoginInterface.passwordIn.setText("");
			ForgotPasswordInterface.emailIn.setText("");
			ForgotPassCodeInterface.codeIn.setText("");
			ChangePasswordInterface.passwordIn.setText("");
			ChangePasswordInterface.rePasswordIn.setText("");
		}
	}

	private void changePasswordButton() {
		ChangePasswordInterface.change.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				checkEnteredPassword();
			}
		});

		ChangePasswordInterface.rePasswordIn.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkEnteredPassword();
				}
			}
		});
	}

	private static int generateChangePassCode() {
		int code = -1;
		Random rand = new Random();
		while (code < 0 || digits(code) != 6) {
			code = rand.nextInt() % 1000000;
		}
		return code;
	}

	public static int digits(int nr) {
		int digits = 0;
		while (nr != 0) {
			digits++;
			nr /= 10;
		}

		return digits;
	}
}
