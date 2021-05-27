package app;

import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import card.Account;
import card.Card;
import card.Transactions;
import database.Database;
import email.EmailThread;
import graphicInterface.UserApplicationInterface;

public class UserApplication {

	EmailThread newCardEmail[] = new EmailThread[10];
	int newCardEmailCounter = 0;

	public static boolean sameEmail = false;
	public static String senderEmail = "";
	public static LocalDate date;
	public static String time;
	DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

	public static JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	public static File file = new File("");
	public static FileWriter myWriter;

	private Transactions currencyTransactions[];
	int j = 0;

	public static boolean addToComboBox = false;

	Transactions newTransaction;

	public UserApplication() {

		for (int i = 0; i < 10; i++) {
			newCardEmail[i] = new EmailThread();
		}
		confirmCreateDebitCard();
		topUpButton();
		sendMoneyButton();
		payUtilitiesButton();
		seeDetailButton();
		changePinButton();
		printReceiptButton();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	private void createDebitCard() {
		if (Database.returnCreatedCardsNumber(Login.email) == 4) {
			JOptionPane.showMessageDialog(UserApplicationInterface.cardTypePanel,
					"You reached the maximum cards number you can create");
		} else {
			String currency = "";
			switch (UserApplicationInterface.currencyBox.getSelectedItem().toString()) {
			case "RON":
				currency = "RON";
				break;
			case "EUR":
				currency = "EUR";
				break;
			case "USD":
				currency = "USD";
				break;
			case "GBP":
				currency = "GBP";
				break;
			}
			if (UserApplicationInterface.currencyBox.getSelectedItem().equals("Choose Currency")) {
				JOptionPane.showMessageDialog(UserApplicationInterface.cardTypePanel, "Please select a currency");

			} else if (Database.alreadyExistCurrency(Login.email, currency)) {
				JOptionPane.showMessageDialog(UserApplicationInterface.cardTypePanel,
						"You already have a card for this currency");

			} else if (Database.checkUserPassword(Login.email).equals(UserApplicationInterface.passwordIn.getText())) {
				Card.pin = Card.generatePin();
				Card.cardNumber = Card.genereazaNrCard();
				Login.index = 4;
				Database.insertIntoCard(Login.email, "Debit", Card.cardNumber, Card.generateExpirationDate(),
						Card.generateCVV(), currency, Card.pin, 0.0);
				Database.incrementCardNumber(Login.email);
				newCardEmail[newCardEmailCounter].start();
				newCardEmailCounter++;
				UserApplicationInterface.cardsComboBox
						.addItem(UserApplicationInterface.currencyBox.getSelectedItem().toString());
				UserApplicationInterface.sendMoneyCardComboBox
						.addItem(UserApplicationInterface.currencyBox.getSelectedItem().toString());
				UserApplicationInterface.payUtilitiesCardComboBox
						.addItem(UserApplicationInterface.currencyBox.getSelectedItem().toString());
				accountDetail();
				JOptionPane.showMessageDialog(UserApplicationInterface.cardTypePanel,
						"Card successfully created. Check your email for PIN");
				UserApplicationInterface.currencyBox.setSelectedItem("Choose Currency");
				UserApplicationInterface.passwordIn.setText("");
				UserApplicationInterface.cardTypeConfirmPanel.setVisible(false);
				UserApplicationInterface.cardTypePanel.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(UserApplicationInterface.cardTypePanel, "Wrong Password. Try Again");
			}
		}

	}

	private void confirmCreateDebitCard() {
		UserApplicationInterface.confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				createDebitCard();
			}
		});

		UserApplicationInterface.passwordIn.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					createDebitCard();
				}
			}
		});
	}

	private void topUp() {
		try {
			if (UserApplicationInterface.topUpAmountIn.getText().equals("")) {
				JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel,
						"Insert the amount you want to top-up");
			} else if (UserApplicationInterface.cardNumberIn.getText().equals("")) {
				JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel,
						"Insert the card's number you want to top-up from");

			} else if (UserApplicationInterface.cvvIn.getText().equals("")) {
				JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel,
						"Insert the card's CVV you want to top-up from");

			} else if (UserApplicationInterface.expirationDateIn.getText().equals("")) {
				JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel,
						"Insert the card's expiration date you want to top-up from");

			} else if (Database.existCard(UserApplicationInterface.cardNumberIn.getText(),
					UserApplicationInterface.expirationDateIn.getText(),
					Integer.parseInt(UserApplicationInterface.cvvIn.getText())) && sameEmail) {
				JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel, "Can not top-up from your own card");

			} else if (Database.existCard(UserApplicationInterface.cardNumberIn.getText(),
					UserApplicationInterface.expirationDateIn.getText(),
					Integer.parseInt(UserApplicationInterface.cvvIn.getText())) && !sameEmail
					&& (Database.returnAmount(senderEmail, UserApplicationInterface.cardNumberIn.getText()) >= Double
							.parseDouble(UserApplicationInterface.topUpAmountIn.getText()))) {

				date = LocalDate.now();
				time = LocalTime.now().format(format);
				String stringDate = date.toString();
				Database.insertIntoTransactions(Login.email, "Top-Up      ",
						Double.parseDouble(UserApplicationInterface.topUpAmountIn.getText()), date, time,
						Database.getUserFullName(senderEmail),
						UserApplicationInterface.cardsComboBox.getSelectedItem().toString());

				Database.updateAmountTopUp(
						Account.currencyAmount(UserApplicationInterface.cardsComboBox.getSelectedItem().toString(),
								Database.returnCardCurrency(UserApplicationInterface.cardNumberIn.getText()),
								Double.parseDouble(UserApplicationInterface.topUpAmountIn.getText())),
						Login.email, senderEmail, UserApplicationInterface.cardsComboBox.getSelectedItem().toString(),
						Database.returnCardCurrency(UserApplicationInterface.cardNumberIn.getText()));

				newTransaction = new Transactions(UserApplicationInterface.topUpAmountIn.getText(), "Top Up      ",
						stringDate, time, UserApplicationInterface.cardsComboBox.getSelectedItem().toString(),
						Database.getUserFullName(senderEmail));

				addToTransactions();

				Account.calculateNewTotalAmount(UserApplicationInterface.cardsComboBox.getSelectedItem().toString(),
						Database.returnCardCurrency(UserApplicationInterface.cardNumberIn.getText()),
						Double.parseDouble(UserApplicationInterface.topUpAmountIn.getText()), "+");

				UserApplicationInterface.amountTitle.setText("Amount: " + Login.totalAmount + "EUR");
				JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel, "Top-Up was successfully made");

			} else if (Database.existCard(UserApplicationInterface.cardNumberIn.getText(),
					UserApplicationInterface.expirationDateIn.getText(),
					Integer.parseInt(UserApplicationInterface.cvvIn.getText())) && !sameEmail
					&& Database.returnAmount(senderEmail, UserApplicationInterface.cardNumberIn.getText()) < Double
							.parseDouble(UserApplicationInterface.topUpAmountIn.getText())) {
				JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel, "Not enough money");

			} else {

				JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel,
						"Account doesn't exists. Check data again!");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel,
					"Make sure you are entering numbers as amount!");
		}
	}

	private void topUpButton() {
		UserApplicationInterface.topup.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				topUp();
			}
		});

		UserApplicationInterface.expirationDateIn.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					topUp();
				}
			}
		});
	}

	private void sendMoney() {
		try {
			if (UserApplicationInterface.sendMoneyAmountIn.getText().equals("")) {
				JOptionPane.showMessageDialog(UserApplicationInterface.sendMoneyPanel,
						"Insert the amount you want to send");
			} else if (UserApplicationInterface.sendMoneyCardNumberIn.getText().equals("")) {
				JOptionPane.showMessageDialog(UserApplicationInterface.sendMoneyPanel,
						"Insert the reciever's card number");
			} else if (UserApplicationInterface.recieverNameIn.getText().equals("")) {
				JOptionPane.showMessageDialog(UserApplicationInterface.sendMoneyPanel, "Insert the reciever's name");
			} else if (Double.parseDouble(UserApplicationInterface.sendMoneyAmountIn.getText()) > Database
					.returnCardAmount(Login.email,
							UserApplicationInterface.sendMoneyCardComboBox.getSelectedItem().toString())) {
				JOptionPane.showMessageDialog(UserApplicationInterface.sendMoneyPanel,
						"You don't have enough money on this card");

			} else if (!Database.existRecieverCard(UserApplicationInterface.sendMoneyCardNumberIn.getText())) {
				JOptionPane.showMessageDialog(UserApplicationInterface.sendMoneyPanel,
						"Account doesn't exists. Check data again!");

			} else if (!Database.returnRecieverName(UserApplicationInterface.sendMoneyCardNumberIn.getText())
					.equals(UserApplicationInterface.recieverNameIn.getText())) {
				JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel,
						"Account doesn't exists. Check data again!");
			} else {
				date = LocalDate.now();
				time = LocalTime.now().format(format);
				String stringDate = date.toString();
				Database.insertIntoTransactions(Login.email, "Money Sent",
						Double.parseDouble(UserApplicationInterface.sendMoneyAmountIn.getText()), date, time,
						UserApplicationInterface.recieverNameIn.getText(),
						UserApplicationInterface.sendMoneyCardComboBox.getSelectedItem().toString());

				Database.updateAmountSendMoney(Double.parseDouble(UserApplicationInterface.sendMoneyAmountIn.getText()),
						Account.currencyAmount(
								Database.returnRecieverCurrency(
										UserApplicationInterface.sendMoneyCardNumberIn.getText(),
										UserApplicationInterface.recieverNameIn.getText()),
								UserApplicationInterface.sendMoneyCardComboBox.getSelectedItem().toString(),
								Double.parseDouble(UserApplicationInterface.sendMoneyAmountIn.getText())),
						UserApplicationInterface.sendMoneyCardComboBox.getSelectedItem().toString(), Login.email,
						UserApplicationInterface.sendMoneyCardNumberIn.getText(),
						UserApplicationInterface.recieverNameIn.getText());

				newTransaction = new Transactions(UserApplicationInterface.sendMoneyAmountIn.getText(), "Money Sent",
						stringDate, time, UserApplicationInterface.sendMoneyCardComboBox.getSelectedItem().toString(),
						UserApplicationInterface.recieverNameIn.getText());
				addToTransactions();

				Account.calculateNewTotalAmount(
						Database.returnRecieverCurrency(UserApplicationInterface.sendMoneyCardNumberIn.getText(),
								UserApplicationInterface.recieverNameIn.getText()),
						UserApplicationInterface.sendMoneyCardComboBox.getSelectedItem().toString(),
						Double.parseDouble(UserApplicationInterface.sendMoneyAmountIn.getText()), "-");

				UserApplicationInterface.sendMoneyAmountIn.setText("");
				UserApplicationInterface.sendMoneyCardNumberIn.setText("");
				UserApplicationInterface.recieverNameIn.setText("");

				UserApplicationInterface.amountTitle.setText("Amount: " + Login.totalAmount + "EUR");
				JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel, "Money were successfully sent");
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel,
					"Make sure you are entering numbers as amount!");
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel,
					"Account doesn't exists. Check data again!");
		}
	}

	private void sendMoneyButton() {
		UserApplicationInterface.send.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sendMoney();
			}
		});

		UserApplicationInterface.recieverNameIn.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMoney();
				}
			}
		});
	}

	private void payUtilites() {
		try {
			if (UserApplicationInterface.payUtilitiesAmountIn.getText().equals("")) {
				JOptionPane.showMessageDialog(UserApplicationInterface.payUtilitiesPanel,
						"Insert the amount you have to pay");
			} else if (UserApplicationInterface.utilitiesBox.getSelectedItem().equals("Choose Provider")) {
				JOptionPane.showMessageDialog(UserApplicationInterface.payUtilitiesPanel, "Choose your provider");
			} else if (Double.parseDouble(UserApplicationInterface.payUtilitiesAmountIn.getText()) > Database
					.returnCardAmount(Login.email,
							UserApplicationInterface.payUtilitiesCardComboBox.getSelectedItem().toString())) {
				JOptionPane.showMessageDialog(UserApplicationInterface.sendMoneyPanel,
						"You don't have enough money on this card");
			} else {
				date = LocalDate.now();
				time = LocalTime.now().format(format);
				String stringDate = String.valueOf(date);
				String utility = "";
				switch (UserApplicationInterface.utilitiesBox.getSelectedItem().toString()) {
				case "Phone Provider":
					utility = "Phone";
					break;
				case "Water Provider":
					utility = "Water";
					break;
				case "Gas Provider    ":
					utility = "Gas    ";
					break;
				}
				Database.insertIntoTransactions(Login.email, "Paid " + utility,
						Double.parseDouble(UserApplicationInterface.payUtilitiesAmountIn.getText()), date, time,
						UserApplicationInterface.utilitiesBox.getSelectedItem().toString(),
						UserApplicationInterface.payUtilitiesCardComboBox.getSelectedItem().toString());

				Database.updateAmountPayUtilities(
						Account.currencyAmount("EUR",
								UserApplicationInterface.payUtilitiesCardComboBox.getSelectedItem().toString(),
								Double.parseDouble(UserApplicationInterface.payUtilitiesAmountIn.getText())),
						Login.email, UserApplicationInterface.payUtilitiesCardComboBox.getSelectedItem().toString());

				newTransaction = new Transactions(UserApplicationInterface.payUtilitiesAmountIn.getText(),
						"Paid " + utility, stringDate, time,
						UserApplicationInterface.payUtilitiesCardComboBox.getSelectedItem().toString(),
						UserApplicationInterface.utilitiesBox.getSelectedItem().toString());

				addToTransactions();

				Account.calculateNewTotalAmount("EUR",
						UserApplicationInterface.payUtilitiesCardComboBox.getSelectedItem().toString(),
						Double.parseDouble(UserApplicationInterface.payUtilitiesAmountIn.getText()), "-");

				UserApplicationInterface.payUtilitiesAmountIn.setText("");
				UserApplicationInterface.utilitiesBox.setSelectedItem("Choose Provider");

				UserApplicationInterface.amountTitle.setText("Amount: " + Login.totalAmount + "EUR");
				JOptionPane.showMessageDialog(UserApplicationInterface.sendMoneyPanel,
						"Your pay was successfully made");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(UserApplicationInterface.topUpPanel,
					"Make sure you are entering numbers as amount!");
		}
	}

	private void addToTransactions() {
		int position = UserApplicationInterface.lastTransactionLabels.length;
		for (int i = 0; i < position - 1; i++) {
			UserApplicationInterface.lastTransactionLabels[i]
					.setText(UserApplicationInterface.lastTransactionLabels[i + 1].getText());
		}
		UserApplicationInterface.lastTransactionLabels[position - 1].setText(newTransaction.getTransactionType() + "   "
				+ newTransaction.getDate() + "   " + newTransaction.getHour() + "   " + newTransaction.getName() + "   "
				+ newTransaction.getAmount() + "" + newTransaction.getCard());
	}

	private void payUtilitiesButton() {
		UserApplicationInterface.pay.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				payUtilites();
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void accountDetail() {
		if (Database.checkUserPassword(Login.email)
				.equals(UserApplicationInterface.accountDetailPasswordIn.getText())) {
			UserApplicationInterface.seeAccountDetailPanel.setVisible(true);
			UserApplicationInterface.passwordAccountDetailPanel.setVisible(false);

			UserApplicationInterface.accountDetailPasswordIn.setText("");

			Database.returnCardData(Login.email);

			switch (Database.returnCreatedCardsNumber(Login.email)) {
			case 1:
				firstCard();
				break;
			case 2:
				secondCard();
				break;
			case 3:
				thirdCard();
				break;
			case 4:
				forthCard();
				break;
			}
		} else if (!Database.checkUserPassword(Login.email)
				.equals(UserApplicationInterface.accountDetailPasswordIn.getText())
				&& UserApplicationInterface.accountDetailPanel.isVisible()) {
			System.out.println("ici");
			JOptionPane.showMessageDialog(UserApplicationInterface.passwordAccountDetailPanel, "Wrong Password");
		}
	}

	private void firstCard() {
		UserApplicationInterface.firstCardAccountDetailLabels[0]
				.setText("No. Card: " + UserApplicationInterface.cards[0].getCardNo());
		UserApplicationInterface.firstCardAccountDetailLabels[1]
				.setText("Amount: " + UserApplicationInterface.cards[0].getAmount() + " "
						+ UserApplicationInterface.cards[0].getCurrency());
		UserApplicationInterface.firstCardAccountDetailLabels[2]
				.setText("Valabil Until: " + UserApplicationInterface.cards[0].getExpirationDate() + " CVV: "
						+ UserApplicationInterface.cards[0].getCvv());
		UserApplicationInterface.firstCardAccountDetailLabels[3]
				.setText("PIN: " + UserApplicationInterface.cards[0].getCardPin());
	}

	private void secondCard() {
		firstCard();

		UserApplicationInterface.secondCardAccountDetailLabels[0]
				.setText("No. Card: " + UserApplicationInterface.cards[1].getCardNo());
		UserApplicationInterface.secondCardAccountDetailLabels[1]
				.setText("Amount: " + UserApplicationInterface.cards[1].getAmount() + " "
						+ UserApplicationInterface.cards[1].getCurrency());
		UserApplicationInterface.secondCardAccountDetailLabels[2]
				.setText("Valabil Until: " + UserApplicationInterface.cards[1].getExpirationDate() + " CVV: "
						+ UserApplicationInterface.cards[1].getCvv());
		UserApplicationInterface.secondCardAccountDetailLabels[3]
				.setText("PIN: " + UserApplicationInterface.cards[1].getCardPin());
	}

	private void thirdCard() {
		secondCard();

		UserApplicationInterface.thirdCardAccountDetailLabels[0]
				.setText("No. Card: " + UserApplicationInterface.cards[2].getCardNo());
		UserApplicationInterface.thirdCardAccountDetailLabels[1]
				.setText("Amount: " + UserApplicationInterface.cards[2].getAmount() + " "
						+ UserApplicationInterface.cards[2].getCurrency());
		UserApplicationInterface.thirdCardAccountDetailLabels[2]
				.setText("Valabil Until: " + UserApplicationInterface.cards[2].getExpirationDate() + " CVV: "
						+ UserApplicationInterface.cards[2].getCvv());
		UserApplicationInterface.thirdCardAccountDetailLabels[3]
				.setText("PIN: " + UserApplicationInterface.cards[2].getCardPin());
	}

	private void forthCard() {
		thirdCard();
		UserApplicationInterface.forthCardAccountDetailLabels[0]
				.setText("No. Card: " + UserApplicationInterface.cards[3].getCardNo());
		UserApplicationInterface.forthCardAccountDetailLabels[1]
				.setText("Amount: " + UserApplicationInterface.cards[3].getAmount() + " "
						+ UserApplicationInterface.cards[3].getCurrency());
		UserApplicationInterface.forthCardAccountDetailLabels[2]
				.setText("Valabil Until: " + UserApplicationInterface.cards[3].getExpirationDate() + " CVV: "
						+ UserApplicationInterface.cards[3].getCvv());
		UserApplicationInterface.forthCardAccountDetailLabels[3]
				.setText("PIN: " + UserApplicationInterface.cards[3].getCardPin());
	}

	private void seeDetailButton() {
		UserApplicationInterface.accountDetailNextButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				accountDetail();
			}
		});

		UserApplicationInterface.accountDetailPasswordIn.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					accountDetail();
				}
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void changePin() {
		try {
			if (!Database.checkUserPassword(Login.email)
					.equals(UserApplicationInterface.changePinPasswordIn.getText())) {
				JOptionPane.showMessageDialog(UserApplicationInterface.changePinPanel, "Wrong Password");
			} else if (!String
					.valueOf(Database.returnPin(Login.email,
							UserApplicationInterface.changePinComboBox.getSelectedItem().toString()))
					.equals(UserApplicationInterface.oldPinIn.getText())) {
				JOptionPane.showMessageDialog(UserApplicationInterface.changePinPanel, "Wrong old PIN");
			} else {
				Database.changePin(Integer.parseInt(UserApplicationInterface.newPinIn.getText()), Login.email,
						UserApplicationInterface.changePinComboBox.getSelectedItem().toString());
				JOptionPane.showMessageDialog(UserApplicationInterface.changePinPanel,
						"Your PIN has been successfully changed");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(UserApplicationInterface.changePinPanel,
					"Make sure you enter you PIN as numbers");
		}
	}

	private void changePinButton() {
		UserApplicationInterface.change.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				changePin();
			}
		});

		UserApplicationInterface.change.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					changePin();
				}
			}
		});
	}

	private void currencyTransactions(String currency) {
		j = 0;
		for (int i = 0; i < UserApplicationInterface.tranzactii.length; i++) {
			if (UserApplicationInterface.tranzactii[i].getCard().equals(currency)) {
				currencyTransactions[j] = new Transactions(UserApplicationInterface.tranzactii[i].getAmount(),
						UserApplicationInterface.tranzactii[i].getTransactionType(),
						UserApplicationInterface.tranzactii[i].getDate(),
						UserApplicationInterface.tranzactii[i].getHour(),
						UserApplicationInterface.tranzactii[i].getCard(),
						UserApplicationInterface.tranzactii[i].getName());
				j++;
			}
		}
	}

	private void printReceipt() {
		if (UserApplicationInterface.browseIn.getText().equals("")) {
			JOptionPane.showMessageDialog(UserApplicationInterface.printReceiptPanel,
					"Choose the path where you want to save the receipt");
		} else {
			file = new File(UserApplicationInterface.browseIn.getText() + "\\receipt.txt");
			try {
				myWriter = new FileWriter(file);

				Database.returnEachCardCurrency(Login.email);
				Database.returnTransactionData(Login.email);

				myWriter.write("Nume: " + Database.getUserFullName(Login.email) + "\nIBAN: "
						+ Database.returnIban(Login.email) + "\nTotal Amount: " + Login.totalAmount + "\nTotal Cards: "
						+ Database.returnCreatedCardsNumber(Login.email));

				switch (Database.returnCreatedCardsNumber(Login.email)) {
				case 1:
					oneCard();
					break;
				case 2:
					twoCard();
					break;
				case 3:
					threeCard();
					break;
				case 4:
					fourCard();
					break;
				}

				Desktop desktop = Desktop.getDesktop();
				desktop.open(file);
				myWriter.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(UserApplicationInterface.printReceiptPanel,
						"Please select a folder not a file");
			}
		}
	}

	private void writeToFile(int nrCard) {
		try {
			myWriter.write("\n\n\n\t\t*****" + Account.currency[nrCard] + "*****");

			for (int i = 0; i < j; i++) {

				myWriter.write("\n" + currencyTransactions[i].getTransactionType() + "   "
						+ currencyTransactions[i].getDate() + "   " + currencyTransactions[i].getHour() + "   "
						+ currencyTransactions[i].getName() + "   " + currencyTransactions[i].getAmount() + ""
						+ currencyTransactions[i].getCard() + "   ");
			}
			myWriter.write("\n\t\t*************");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void oneCard() {
		currencyTransactions = new Transactions[UserApplicationInterface.tranzactii.length];
		currencyTransactions(Account.currency[0]);
		writeToFile(0);
	}

	private void twoCard() {
		oneCard();

		currencyTransactions = new Transactions[UserApplicationInterface.tranzactii.length];
		currencyTransactions(Account.currency[1]);
		writeToFile(1);
	}

	private void threeCard() {
		twoCard();

		currencyTransactions = new Transactions[UserApplicationInterface.tranzactii.length];
		currencyTransactions(Account.currency[2]);

		writeToFile(2);
	}

	private void fourCard() {
		threeCard();

		currencyTransactions = new Transactions[UserApplicationInterface.tranzactii.length];
		currencyTransactions(Account.currency[3]);

		writeToFile(3);
	}

	private void printReceiptButton() {
		UserApplicationInterface.print.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				printReceipt();
			}
		});
	}
}
