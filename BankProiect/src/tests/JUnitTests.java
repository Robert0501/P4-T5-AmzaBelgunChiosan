package tests;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.junit.jupiter.api.Test;

import graphicInterface.LoginInterface;

class JUnitTests {

	Tests loginTest = new Tests();

	@Test
	void loginTest() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new LoginInterface();

		boolean ok = false;
		ok = loginTest.checkLogin();
		assertEquals(true, ok);
	}

	@Test
	void changePassTest() {
		boolean ok = false;
		ok = loginTest.checkChangePassword();
		assertEquals(true, ok);
	}

	@Test
	void checkRegister() {
		boolean ok = false;
		ok = loginTest.checkRegisterButton();
		assertEquals(true, ok);
	}

	@Test
	void checkTopUpInterface() {
		boolean ok = false;
		ok = loginTest.checkTopUpInterface();
		assertEquals(true, ok);
	}

	@Test
	void checkSendMoneyInterface() {
		boolean ok = false;
		ok = loginTest.checkSendMoneyInterface();
		assertEquals(true, ok);
	}

	@Test
	void checkPayUtilitiesInterface() {
		boolean ok = false;
		ok = loginTest.checkPayUtilitiesInterface();
		assertEquals(true, ok);
	}

	@Test
	void checkLastTransactionInterface() {
		boolean ok = false;
		ok = loginTest.checkLastTransactionsInterface();
		assertEquals(true, ok);
	}

	@Test
	void checkAccountDetailInterface() {
		boolean ok = false;
		ok = loginTest.checkAccountDetailInterface();
		assertEquals(true, ok);
	}

	@Test
	void checkPrintReceiptInterface() {
		boolean ok = false;
		ok = loginTest.printReceiptInterface();
		assertEquals(true, ok);
	}

	@Test
	void checkChangePinInterface() {
		boolean ok = false;
		ok = loginTest.changePinInterface();
		assertEquals(true, ok);
	}

	@Test
	void changePassword() {
		boolean ok = false;
		ok = loginTest.changePassword();
		assertEquals(true, ok);
	}

	@Test
	void payUtilites() {
		boolean ok = false;
		ok = loginTest.payUtilities();
		assertEquals(true, ok);
	}

	@Test
	void seeAccountDetails() {
		boolean ok = false;
		ok = loginTest.seeAccountDetail();
		assertEquals(true, ok);
	}
	
	@Test
	void topUp() {
		boolean ok = false;
		ok = loginTest.topUp();
		assertEquals(true, ok);
	}
	
	@Test
	void sendMoney() {
		boolean ok = false;
		ok = loginTest.sendMoney();
		assertEquals(true, ok);
	}
}
