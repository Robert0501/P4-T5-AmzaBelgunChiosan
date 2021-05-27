package tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import app.ForgotPassword;
import graphicInterface.ChangePasswordInterface;
import graphicInterface.ForgotPassCodeInterface;
import graphicInterface.ForgotPasswordInterface;
import graphicInterface.LoginInterface;
import graphicInterface.RegisterInterface;
import graphicInterface.UserApplicationInterface;

public class Tests {

	@SuppressWarnings("deprecation")
	public void click(Robot bot) {
		bot.mousePress(InputEvent.BUTTON1_MASK);
		try {
			Thread.sleep(100);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void logout(Robot bot) {
		bot.mouseMove(500, 950);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		click(bot);
	}

	public boolean checkLogin() {

		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);

			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);

			LoginInterface.passwordIn.setText("Laurentiu@02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			if (!LoginInterface.frame.isVisible() && UserApplicationInterface.lastTransactionsPanel.isVisible()) {
				logout(bot);
				if (LoginInterface.frame.isVisible())
					return true;
			}
			logout(bot);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean checkChangePassword() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);
			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);
			LoginInterface.passwordIn.setText("Laurentiu02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			if (ForgotPasswordInterface.forgotPassPanel.isVisible()) {
				bot.mouseMove(850, 550);
				Thread.sleep(1000);
				click(bot);
				if (LoginInterface.loginPanel.isVisible()) {
					return true;
				}
			}
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean checkRegisterButton() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 720);

			click(bot);
			if (RegisterInterface.personalDataPanel.isVisible()) {
				bot.mouseMove(1100, 700);
				Thread.sleep(1000);
				click(bot);
				if (LoginInterface.loginPanel.isVisible())
					return true;
			}
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkTopUpInterface() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);

			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);

			LoginInterface.passwordIn.setText("Laurentiu@02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.mouseMove(1000, 720);

			click(bot);

			bot.mouseMove(700, 250);
			click(bot);
			if (UserApplicationInterface.topUpPanel.isVisible()) {
				logout(bot);
				click(bot);
				if (LoginInterface.frame.isVisible())
					return true;
			}
			logout(bot);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkSendMoneyInterface() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);

			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);

			LoginInterface.passwordIn.setText("Laurentiu@02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.mouseMove(1000, 720);

			click(bot);

			bot.mouseMove(700, 350);
			click(bot);
			if (UserApplicationInterface.sendMoneyPanel.isVisible()) {
				logout(bot);
				if (LoginInterface.frame.isVisible())
					return true;
			}
			logout(bot);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkPayUtilitiesInterface() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);

			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);

			LoginInterface.passwordIn.setText("Laurentiu@02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.mouseMove(1000, 720);

			click(bot);

			bot.mouseMove(700, 450);
			click(bot);
			if (UserApplicationInterface.payUtilitiesPanel.isVisible()) {
				logout(bot);
				if (LoginInterface.frame.isVisible())
					return true;
			}
			logout(bot);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkLastTransactionsInterface() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);

			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);

			LoginInterface.passwordIn.setText("Laurentiu@02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.mouseMove(1000, 720);

			click(bot);

			bot.mouseMove(700, 550);
			click(bot);
			if (UserApplicationInterface.lastTransactionsPanel.isVisible()) {
				logout(bot);
				if (LoginInterface.frame.isVisible())
					return true;
			}
			logout(bot);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkAccountDetailInterface() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);

			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);

			LoginInterface.passwordIn.setText("Laurentiu@02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.mouseMove(1000, 720);

			click(bot);

			bot.mouseMove(700, 650);
			click(bot);
			if (UserApplicationInterface.accountDetailPanel.isVisible()) {
				logout(bot);
				if (LoginInterface.frame.isVisible())
					return true;
			}
			logout(bot);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean printReceiptInterface() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);

			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);

			LoginInterface.passwordIn.setText("Laurentiu@02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.mouseMove(1000, 720);

			click(bot);

			bot.mouseMove(700, 750);
			click(bot);
			if (UserApplicationInterface.printReceiptPanel.isVisible()) {
				logout(bot);
				if (LoginInterface.frame.isVisible())
					return true;
			}
			logout(bot);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean changePinInterface() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);

			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);

			LoginInterface.passwordIn.setText("Laurentiu@02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.mouseMove(1000, 720);

			click(bot);

			bot.mouseMove(700, 850);
			click(bot);
			if (UserApplicationInterface.changePinPanel.isVisible()) {
				logout(bot);
				if (LoginInterface.frame.isVisible())
					return true;
			}
			logout(bot);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean changePassword() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);
			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);
			LoginInterface.passwordIn.setText("Laurentiu02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			if (ForgotPasswordInterface.forgotPassPanel.isVisible()) {
				ForgotPasswordInterface.emailIn.setText("robert_amza@yahoo.com");
				Thread.sleep(1000);
				bot.mouseMove(1050, 550);
				Thread.sleep(1000);
				click(bot);
				bot.keyPress(KeyEvent.VK_ENTER);
				bot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(3000);
				if (ForgotPassCodeInterface.forgotPassCodePanel.isVisible()) {
					ForgotPassCodeInterface.codeIn.setText(String.valueOf(ForgotPassword.code));
					Thread.sleep(1000);
					bot.mouseMove(1050, 550);
					click(bot);
					bot.keyPress(KeyEvent.VK_ENTER);
					bot.keyRelease(KeyEvent.VK_ENTER);
					if (ChangePasswordInterface.changePasswordPanel.isVisible()) {
						ChangePasswordInterface.passwordIn.setText("Laurentiu@02");
						ChangePasswordInterface.rePasswordIn.setText("Laurentiu@02");
						Thread.sleep(100);
						bot.mouseMove(1000, 625);
						click(bot);
						bot.keyPress(KeyEvent.VK_ENTER);
						bot.keyRelease(KeyEvent.VK_ENTER);
						Thread.sleep(1000);
						bot.keyPress(KeyEvent.VK_ENTER);
						Thread.sleep(1000);

						if (LoginInterface.loginPanel.isVisible()) {
							System.out.println("heee");
							return true;
						}
					}
				}
			}
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean payUtilities() {

		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);

			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);

			LoginInterface.passwordIn.setText("Laurentiu@02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.mouseMove(1000, 720);

			click(bot);

			bot.mouseMove(700, 450);
			click(bot);
			if (UserApplicationInterface.payUtilitiesPanel.isVisible()) {
				UserApplicationInterface.payUtilitiesAmountIn.setText(String.valueOf(100));
				UserApplicationInterface.utilitiesBox.setSelectedItem("Gas Provider    ");
				UserApplicationInterface.payUtilitiesCardComboBox.setSelectedItem("USD");
				bot.mouseMove(1100, 750);
				click(bot);
				bot.keyPress(KeyEvent.VK_ENTER);
				bot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(1000);
				logout(bot);
				if (LoginInterface.frame.isVisible())
					return true;
			}
			logout(bot);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean seeAccountDetail() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);

			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);

			LoginInterface.passwordIn.setText("Laurentiu@02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.mouseMove(1000, 720);

			click(bot);

			bot.mouseMove(700, 650);
			click(bot);
			if (UserApplicationInterface.accountDetailPanel.isVisible()) {
				UserApplicationInterface.accountDetailPasswordIn.setText("Laurentiu@02");
				bot.mouseMove(1150, 630);
				Thread.sleep(100);
				click(bot);
				if (UserApplicationInterface.accountDetailPanel.isVisible()) {
					logout(bot);
					if (LoginInterface.frame.isVisible())
						return true;
				}

			}
			logout(bot);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean topUp() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);

			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);

			LoginInterface.passwordIn.setText("Laurentiu@02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.mouseMove(1000, 720);

			click(bot);

			bot.mouseMove(700, 250);
			click(bot);
			if (UserApplicationInterface.topUpPanel.isVisible()) {
				UserApplicationInterface.topUpAmountIn.setText(String.valueOf(1));
				UserApplicationInterface.cardNumberIn.setText("4225300170054287");
				UserApplicationInterface.cvvIn.setText("558");
				UserApplicationInterface.expirationDateIn.setText("05/26");
				bot.mouseMove(1100, 840);
				Thread.sleep(100);
				click(bot);
				bot.keyPress(KeyEvent.VK_ENTER);
				bot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(1000);
				logout(bot);
				click(bot);
				if (LoginInterface.frame.isVisible())
					return true;
			}
			logout(bot);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean sendMoney() {
		try {
			Robot bot = new Robot();
			bot.mouseMove(1000, 450);
			click(bot);

			LoginInterface.usernameIn.setText("Robert0501");
			Thread.sleep(1000);
			bot.mouseMove(1000, 625);
			click(bot);

			LoginInterface.passwordIn.setText("Laurentiu@02");
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			bot.mouseMove(1000, 720);

			click(bot);

			bot.mouseMove(700, 350);
			click(bot);
			if (UserApplicationInterface.sendMoneyPanel.isVisible()) {
				UserApplicationInterface.sendMoneyAmountIn.setText("1");
				UserApplicationInterface.sendMoneyCardNumberIn.setText("4225300170054287");
				UserApplicationInterface.recieverNameIn.setText("Robert Amza");
				bot.mouseMove(1100, 750);
				click(bot);
				bot.keyPress(KeyEvent.VK_ENTER);
				bot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(1000);
				logout(bot);
				if (LoginInterface.frame.isVisible())
					return true;
			}
			logout(bot);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

}
