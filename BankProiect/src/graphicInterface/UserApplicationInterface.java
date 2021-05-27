package graphicInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import app.Login;
import app.UserApplication;
import card.Account;
import card.Card;
import card.Transactions;
import database.Database;

public class UserApplicationInterface {
	Font cardDetailFont = new Font("Tahoma", Font.BOLD, 22);

	public static JFrame frame;
	public static JPanel applicationPanel;
	public static JPanel titlePanel;
	public static JLabel amountTitle;

	private JPanel createNewCardButton;
	private JPanel topUpButton;
	private JPanel sendMoneyButton;
	private JPanel payUtilitesButton;
	private JPanel lastTransactionsButton;
	private JPanel accountDetailButton;
	private JPanel printReceiptButton;
	private JPanel changePinButton;
	private JPanel logoutButton;

	public static JPanel createNewCardPanel;
	public static JPanel topUpPanel;
	public static JPanel sendMoneyPanel;
	public static JPanel payUtilitiesPanel;
	public static JPanel lastTransactionsPanel;
	public static JPanel accountDetailPanel;
	public static JPanel printReceiptPanel;
	public static JPanel changePinPanel;
	public static JPanel logoutPanel;
	public static JLabel title;

	private JLabel createNewCard;
	private JLabel topUp;
	private JLabel sendMoney;
	private JLabel payUtilites;
	private JLabel lastTransactions;
	private JLabel accountDetail;
	private JLabel printReceipt;
	private JLabel changePin;
	private JLabel logout;

	private JLabel cardNumber;
	private JLabel cvv;
	private JLabel expirationDate;
	private JLabel topUpAmount;
	private JLabel recieverCard;
	public static JTextField cardNumberIn;
	public static JTextField cvvIn;
	public static JTextField holderNameIn;
	public static JTextField expirationDateIn;
	public static JTextField topUpAmountIn;
	@SuppressWarnings("rawtypes")
	public static JComboBox cardsComboBox;
	public static JButton topup;

	private JLabel sendMoneyCardNumber;
	private JLabel sendMoneyAmount;
	private JLabel recieverName;
	private JLabel sendMoneyCurrencyChoose;
	public static JTextField sendMoneyAmountIn;
	public static JTextField sendMoneyCardNumberIn;
	public static JTextField recieverNameIn;
	public static JButton send;
	@SuppressWarnings("rawtypes")
	public static JComboBox sendMoneyCardComboBox;

	private JLabel payUtilitiesAmount;
	private JLabel provider;
	private JLabel payUtilitiesChooseCard;
	@SuppressWarnings("rawtypes")
	public static JComboBox utilitiesBox;
	@SuppressWarnings("rawtypes")
	public static JComboBox payUtilitiesCardComboBox;
	private String[] utilities = { "Choose Provider", "Phone Provider", "Water Provider", "Gas Provider    " };
	public static JTextField payUtilitiesAmountIn;
	public static JButton pay;

	public static JPanel cardTypePanel;
	public static JPanel cardTypeConfirmPanel;
	public static JLabel cardType;
	public static JLabel confirmLabel;
	public static JLabel passwordLabel;
	public static JLabel debitCard;
	public static JLabel creditCard;
	@SuppressWarnings("rawtypes")
	public static JComboBox currencyBox;
	private String[] currencyArray = { "Choose Currency", "RON", "EUR", "USD", "GBP" };
	public static JPasswordField passwordIn;

	private JLabel accountDetailPassword;
	public static JPasswordField accountDetailPasswordIn;
	public static JButton accountDetailNextButton;
	public static JPanel passwordAccountDetailPanel;
	public static JPanel seeAccountDetailPanel;

	public static JButton confirmButton;
	public static JButton backButton;

	private JPanel panels[] = new JPanel[8];

	public static int transactionNumber;
	public static Transactions tranzactii[];
	public static Card cards[];

	private JLabel background;
	public static JLabel lastTransactionLabels[];
	public static JLabel firstCardAccountDetailLabels[];
	public static JLabel secondCardAccountDetailLabels[];
	public static JLabel thirdCardAccountDetailLabels[];
	public static JLabel forthCardAccountDetailLabels[];

	private JLabel changePinPassword;
	private JLabel oldPin;
	private JLabel newPin;
	private JLabel changePinCard;

	public static JPasswordField changePinPasswordIn;
	public static JPasswordField oldPinIn;
	public static JPasswordField newPinIn;
	@SuppressWarnings("rawtypes")
	public static JComboBox changePinComboBox;
	public static JButton change;

	private JLabel saveToLabel;
	public static JTextField browseIn;
	public static JButton browseButton;
	public static JButton print;

	private int width = 85;
	public static int position;

	public UserApplicationInterface() {
		frame();
		panelsDeclaration();
		putButtons();
		titlePanel();
		createPanels();
		populatePanelVector();
		newCard();
		topUp();
		sendMoney();
		payUtilites();
		lastTransactions();
		accountDetail();
		printReceipt();
		changePin();
		logout();
	}

	private void applyLeftButtons(JPanel panel, JLabel label, String text) {
		panel.setLayout(null);
		panel.setBounds(10, width, 300, 95);
		panel.setBackground(Color.black);
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		applicationPanel.add(panel);

		width += 100;

		label = new JLabel(text);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.white);
		label.setBounds(0, 0, 300, 95);
		label.setFont(cardDetailFont);
		panel.add(label);
	}

	private void setBackground(JPanel panel) {
		background = new JLabel();
		background.setIcon(new ImageIcon("src//Images//Backgrounds//woodBackground.jpg"));
		background.setBounds(0, 0, 735, 895);
		panel.add(background);
	}

	private void panelsDeclaration() {
		createNewCardButton = new JPanel();
		topUpButton = new JPanel();
		sendMoneyButton = new JPanel();
		payUtilitesButton = new JPanel();
		lastTransactionsButton = new JPanel();
		accountDetailButton = new JPanel();
		printReceiptButton = new JPanel();
		changePinButton = new JPanel();
		logoutButton = new JPanel();
	}

	private void frame() {
		applicationPanel = new JPanel();
		applicationPanel.setLayout(null);
		frame = new JFrame("Revolve");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1080, 1025);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(applicationPanel);
	}

	private void putButtons() {
		applyLeftButtons(createNewCardButton, createNewCard, "Create New Card");
		applyLeftButtons(topUpButton, topUp, "Top-Up");
		applyLeftButtons(sendMoneyButton, sendMoney, "Send Money");
		applyLeftButtons(payUtilitesButton, payUtilites, "Pay Utilities");
		applyLeftButtons(lastTransactionsButton, lastTransactions, "Last Transactions");
		applyLeftButtons(accountDetailButton, accountDetail, "Account Detail");
		applyLeftButtons(printReceiptButton, printReceipt, "Print Receipt");
		applyLeftButtons(changePinButton, changePin, "Change PIN");
		applyLeftButtons(logoutButton, logout, "Logout");
	}

	private void putPanels(JPanel panel) {
		panel.setBounds(320, 85, 735, 895);
		panel.setVisible(false);
		panel.setBackground(Color.black);
		panel.setLayout(null);
		applicationPanel.add(panel);
	}

	private void putTitles(JPanel panel, JLabel label, String text) {
		label = new JLabel(text);
		label.setBounds(0, 50, 735, 35);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 30));
		label.setForeground(Color.white);
		panel.add(label);
	}

	private void titlePanel() {
		titlePanel = new JPanel();
		titlePanel.setLayout(null);
		titlePanel.setBounds(10, 10, 1045, 70);
		titlePanel.setBackground(Color.black);
		applicationPanel.add(titlePanel);

		title = new JLabel("Welcome");
		title.setBounds(40, 15, 1080, 30);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 35));
		title.setForeground(Color.white);
		titlePanel.add(title);

		amountTitle = new JLabel("Amount: ");
		amountTitle.setBounds(10, 35, 1080, 30);
		amountTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		amountTitle.setForeground(Color.white);
		titlePanel.add(amountTitle);
	}

	private void createPanels() {
		createNewCardPanel();
		createTopUpPanel();
		createSendMoneyPanel();
		createPayUtilitesPanel();
		createLastTransactionsPanel();
		createAccountDetailPanel();
		createPrinReceiptPanel();
		createChangePinPanel();
	}

	private void createNewCardPanel() {
		createNewCardPanel = new JPanel();
		putPanels(createNewCardPanel);
		putTitles(createNewCardPanel, createNewCard, "Choose Card Type");

		cardTypePanel = new JPanel();
		cardTypePanel.setLayout(null);
		cardTypePanel.setVisible(false);
		cardTypePanel.setBounds(0, 0, 735, 895);
		createNewCardPanel.add(cardTypePanel);

		cardTypeConfirmPanel = new JPanel();
		cardTypeConfirmPanel.setLayout(null);
		cardTypeConfirmPanel.setVisible(false);
		cardTypeConfirmPanel.setBounds(0, 0, 735, 895);
		createNewCardPanel.add(cardTypeConfirmPanel);
	}

	private void createTopUpPanel() {
		topUpPanel = new JPanel();
		putPanels(topUpPanel);
		putTitles(topUpPanel, topUp, "Top-Up");
	}

	private void createSendMoneyPanel() {
		sendMoneyPanel = new JPanel();
		putPanels(sendMoneyPanel);
		putTitles(sendMoneyPanel, sendMoney, "Send Money");
	}

	private void createPayUtilitesPanel() {
		payUtilitiesPanel = new JPanel();
		putPanels(payUtilitiesPanel);
		putTitles(payUtilitiesPanel, payUtilites, "Pay Utilites");
	}

	private void createLastTransactionsPanel() {
		lastTransactionsPanel = new JPanel();
		putPanels(lastTransactionsPanel);
		putTitles(lastTransactionsPanel, lastTransactions, "Last Transactions");
		lastTransactionsPanel.setVisible(true);
	}

	private void createAccountDetailPanel() {
		accountDetailPanel = new JPanel();
		putPanels(accountDetailPanel);
		putTitles(accountDetailPanel, accountDetail, "Account Details");
	}

	private void createPrinReceiptPanel() {
		printReceiptPanel = new JPanel();
		putPanels(printReceiptPanel);
		putTitles(printReceiptPanel, printReceipt, "Print Receipt");
	}

	private void createChangePinPanel() {
		changePinPanel = new JPanel();
		putPanels(changePinPanel);
		putTitles(changePinPanel, changePin, "Change PIN");
	}

	private void populatePanelVector() {
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
		}

		panels[0] = createNewCardPanel;
		panels[1] = topUpPanel;
		panels[2] = sendMoneyPanel;
		panels[3] = payUtilitiesPanel;
		panels[4] = lastTransactionsPanel;
		panels[5] = accountDetailPanel;
		panels[6] = printReceiptPanel;
		panels[7] = changePinPanel;

	}

	private void setVisible(JPanel panel) {
		for (int i = 0; i < panels.length; i++) {
			if (panels[i].equals(panel)) {
				panel.setVisible(true);
			} else {
				panels[i].setVisible(false);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void newCard() {
		createNewCardButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(createNewCardPanel);
				cardTypePanel.setVisible(true);
			}
		});

		debitCard = new JLabel();
		debitCard.setIcon(new ImageIcon("src//Images//Images//debitCard.png"));
		debitCard.setBounds(192, 350, 350, 220);
		debitCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cardTypePanel.add(debitCard);

		debitCard.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cardTypePanel.setVisible(false);
				cardTypeConfirmPanel.setVisible(true);

			}
		});

		currencyBox = new JComboBox(currencyArray);
		currencyBox.setBounds(267, 350, 200, 30);
		cardTypeConfirmPanel.add(currencyBox);

		passwordLabel = new JLabel();
		passwordLabel.setBounds(100, 400, 200, 22);
		passwordLabel.setText("Password");
		passwordLabel.setForeground(Color.white);
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		cardTypeConfirmPanel.add(passwordLabel);

		backButton = new JButton("Back");
		backButton.setBounds(267, 450, 90, 25);
		backButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cardTypeConfirmPanel.setVisible(false);
				cardTypePanel.setVisible(true);
				passwordIn.setText("");
			}
		});
		cardTypeConfirmPanel.add(backButton);

		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(377, 450, 90, 25);
		cardTypeConfirmPanel.add(confirmButton);

		passwordIn = new JPasswordField();
		passwordIn.setBounds(267, 400, 200, 25);
		cardTypeConfirmPanel.add(passwordIn);

		setBackground(cardTypePanel);
		setBackground(createNewCardPanel);
		setBackground(cardTypeConfirmPanel);
	}

	@SuppressWarnings("rawtypes")
	private void topUp() {
		topUpButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(topUpPanel);
			}
		});

		topUpAmount = new JLabel("Amount");
		topUpAmount.setBounds(100, 300, 180, 25);
		topUpAmount.setFont(new Font("Tahoma", Font.BOLD, 20));
		topUpAmount.setForeground(Color.white);
		topUpPanel.add(topUpAmount);

		topUpAmountIn = new JTextField();
		topUpAmountIn.setBounds(300, 300, 250, 25);
		topUpPanel.add(topUpAmountIn);

		cardNumber = new JLabel("Card Number");
		cardNumber.setBounds(100, 385, 150, 25);
		cardNumber.setFont(new Font("Tahoma", Font.BOLD, 20));
		cardNumber.setForeground(Color.white);
		topUpPanel.add(cardNumber);

		cardNumberIn = new JTextField();
		cardNumberIn.setBounds(300, 385, 250, 25);
		topUpPanel.add(cardNumberIn);

		cvv = new JLabel("CVV");
		cvv.setBounds(100, 460, 150, 25);
		cvv.setFont(new Font("Tahoma", Font.BOLD, 20));
		cvv.setForeground(Color.white);
		topUpPanel.add(cvv);

		cvvIn = new JTextField();
		cvvIn.setBounds(300, 460, 250, 25);
		topUpPanel.add(cvvIn);

		expirationDate = new JLabel("Expiration Date");
		expirationDate.setBounds(100, 545, 180, 25);
		expirationDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		expirationDate.setForeground(Color.white);
		topUpPanel.add(expirationDate);

		expirationDateIn = new JTextField();
		expirationDateIn.setBounds(300, 545, 250, 25);
		topUpPanel.add(expirationDateIn);

		topup = new JButton("Top-Up");
		topup.setBounds(320, 700, 100, 25);
		topUpPanel.add(topup);

		recieverCard = new JLabel("Reciever Card");
		recieverCard.setBounds(100, 630, 250, 25);
		recieverCard.setFont(new Font("Tahoma", Font.BOLD, 20));
		recieverCard.setForeground(Color.white);
		topUpPanel.add(recieverCard);

		cardsComboBox = new JComboBox();
		cardsComboBox.setBounds(300, 630, 250, 25);
		topUpPanel.add(cardsComboBox);

		setBackground(topUpPanel);
	}

	@SuppressWarnings("rawtypes")
	private void sendMoney() {
		sendMoneyButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(sendMoneyPanel);
			}
		});

		sendMoneyAmount = new JLabel("Amount");
		sendMoneyAmount.setBounds(100, 300, 180, 25);
		sendMoneyAmount.setFont(new Font("Tahoma", Font.BOLD, 20));
		sendMoneyAmount.setForeground(Color.white);
		sendMoneyPanel.add(sendMoneyAmount);

		sendMoneyAmountIn = new JTextField();
		sendMoneyAmountIn.setBounds(300, 300, 250, 25);
		sendMoneyPanel.add(sendMoneyAmountIn);

		sendMoneyCardNumber = new JLabel("Reciever No.Card");
		sendMoneyCardNumber.setBounds(100, 385, 180, 25);
		sendMoneyCardNumber.setFont(new Font("Tahoma", Font.BOLD, 20));
		sendMoneyCardNumber.setForeground(Color.white);
		sendMoneyPanel.add(sendMoneyCardNumber);

		sendMoneyCardNumberIn = new JTextField();
		sendMoneyCardNumberIn.setBounds(300, 385, 250, 25);
		sendMoneyPanel.add(sendMoneyCardNumberIn);

		recieverName = new JLabel("Reciever Name");
		recieverName.setBounds(100, 460, 180, 25);
		recieverName.setFont(new Font("Tahoma", Font.BOLD, 20));
		recieverName.setForeground(Color.white);
		sendMoneyPanel.add(recieverName);

		recieverNameIn = new JTextField();
		recieverNameIn.setBounds(300, 460, 250, 25);
		sendMoneyPanel.add(recieverNameIn);

		sendMoneyCurrencyChoose = new JLabel("Sender Card");
		sendMoneyCurrencyChoose.setBounds(100, 545, 180, 25);
		sendMoneyCurrencyChoose.setFont(new Font("Tahoma", Font.BOLD, 20));
		sendMoneyCurrencyChoose.setForeground(Color.white);
		sendMoneyPanel.add(sendMoneyCurrencyChoose);

		sendMoneyCardComboBox = new JComboBox();
		sendMoneyCardComboBox.setBounds(300, 545, 250, 25);
		sendMoneyPanel.add(sendMoneyCardComboBox);

		send = new JButton("Send");
		send.setBounds(320, 625, 100, 25);
		sendMoneyPanel.add(send);

		setBackground(sendMoneyPanel);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void payUtilites() {
		payUtilitesButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(payUtilitiesPanel);
			}
		});
		payUtilitiesAmount = new JLabel("Amount");
		payUtilitiesAmount.setBounds(100, 385, 180, 25);
		payUtilitiesAmount.setFont(new Font("Tahoma", Font.BOLD, 20));
		payUtilitiesAmount.setForeground(Color.white);
		payUtilitiesPanel.add(payUtilitiesAmount);

		payUtilitiesAmountIn = new JTextField();
		payUtilitiesAmountIn.setBounds(300, 385, 250, 25);
		payUtilitiesPanel.add(payUtilitiesAmountIn);

		provider = new JLabel("Provider");
		provider.setBounds(100, 460, 180, 25);
		provider.setFont(new Font("Tahoma", Font.BOLD, 20));
		provider.setForeground(Color.white);
		payUtilitiesPanel.add(provider);

		utilitiesBox = new JComboBox(utilities);
		utilitiesBox.setBounds(300, 460, 250, 25);
		payUtilitiesPanel.add(utilitiesBox);

		payUtilitiesChooseCard = new JLabel("Choose Card");
		payUtilitiesChooseCard.setBounds(100, 545, 250, 25);
		payUtilitiesChooseCard.setFont(new Font("Tahoma", Font.BOLD, 20));
		payUtilitiesChooseCard.setForeground(Color.white);
		payUtilitiesPanel.add(payUtilitiesChooseCard);

		payUtilitiesCardComboBox = new JComboBox();
		payUtilitiesCardComboBox.setBounds(300, 545, 250, 25);
		payUtilitiesPanel.add(payUtilitiesCardComboBox);

		pay = new JButton("Pay");
		pay.setBounds(320, 625, 100, 25);
		payUtilitiesPanel.add(pay);

		setBackground(payUtilitiesPanel);
	}

	private void lastTransactions() {
		lastTransactionsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(lastTransactionsPanel);
			}
		});

		lastTransactionLabels = new JLabel[16];

		transactionNumber = Database.returnTransactionsNumber(Login.email);
		tranzactii = new Transactions[transactionNumber];
		Database.returnTransactionData(Login.email);
		for (int i = 0; i < lastTransactionLabels.length; i++) {
			lastTransactionLabels[i] = new JLabel();
		}

		int width = 900;

		for (int i = 0; i < lastTransactionLabels.length; i++) {
			lastTransactionLabels[i].setBounds(0, width, 735, 23);
			width -= 50;
			lastTransactionLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			lastTransactionLabels[i].setFont(new Font("Tahoma", Font.BOLD, 20));
			lastTransactionLabels[i].setForeground(Color.white);
			lastTransactionsPanel.add(lastTransactionLabels[i]);
		}

		position = lastTransactionLabels.length - 1;
		for (int i = transactionNumber - 1; i >= 0; i--) {
			lastTransactionLabels[position].setText(UserApplicationInterface.tranzactii[i].getTransactionType() + "   "
					+ UserApplicationInterface.tranzactii[i].getDate() + "   "
					+ UserApplicationInterface.tranzactii[i].getHour() + "   "
					+ UserApplicationInterface.tranzactii[i].getName() + "   "
					+ UserApplicationInterface.tranzactii[i].getAmount() + ""
					+ UserApplicationInterface.tranzactii[i].getCard());
			position--;
			if (position == 0) {
				break;
			}
		}

		setBackground(lastTransactionsPanel);
	}

	private void accountDetail() {
		accountDetailButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(accountDetailPanel);
				passwordAccountDetailPanel.setVisible(true);
				seeAccountDetailPanel.setVisible(false);
			}
		});

		passwordAccountDetailPanel = new JPanel();
		passwordAccountDetailPanel.setLayout(null);
		passwordAccountDetailPanel.setVisible(true);
		passwordAccountDetailPanel.setBounds(0, 0, 735, 895);
		accountDetailPanel.add(passwordAccountDetailPanel);

		accountDetailPassword = new JLabel("Password");
		accountDetailPassword.setBounds(100, 385, 180, 25);
		accountDetailPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		accountDetailPassword.setForeground(Color.white);
		passwordAccountDetailPanel.add(accountDetailPassword);

		accountDetailPasswordIn = new JPasswordField();
		accountDetailPasswordIn.setBounds(300, 385, 250, 25);
		passwordAccountDetailPanel.add(accountDetailPasswordIn);

		accountDetailNextButton = new JButton("See Details");
		accountDetailNextButton.setBounds(350, 500, 100, 25);
		passwordAccountDetailPanel.add(accountDetailNextButton);

		seeAccountDetailPanel = new JPanel();
		seeAccountDetailPanel.setLayout(null);
		seeAccountDetailPanel.setVisible(false);
		seeAccountDetailPanel.setBounds(0, 0, 735, 895);
		accountDetailPanel.add(seeAccountDetailPanel);

		firstCardAccountDetailLabels = new JLabel[4];
		secondCardAccountDetailLabels = new JLabel[4];
		thirdCardAccountDetailLabels = new JLabel[4];
		forthCardAccountDetailLabels = new JLabel[4];

		cards = new Card[4];

		for (int i = 0; i < firstCardAccountDetailLabels.length; i++) {
			firstCardAccountDetailLabels[i] = new JLabel();
			secondCardAccountDetailLabels[i] = new JLabel();
			thirdCardAccountDetailLabels[i] = new JLabel();
			forthCardAccountDetailLabels[i] = new JLabel();
		}

		int leftWidth = 200;

		for (int i = 0; i < firstCardAccountDetailLabels.length; i++) {
			firstCardAccountDetailLabels[i].setBounds(0, leftWidth, 735 / 2, 23);
			leftWidth += 50;
			firstCardAccountDetailLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			firstCardAccountDetailLabels[i].setFont(new Font("Tahoma", Font.BOLD, 20));
			firstCardAccountDetailLabels[i].setForeground(Color.white);
			seeAccountDetailPanel.add(firstCardAccountDetailLabels[i]);
		}

		leftWidth += 100;

		for (int i = 0; i < secondCardAccountDetailLabels.length; i++) {
			thirdCardAccountDetailLabels[i].setBounds(0, leftWidth, 735 / 2, 23);
			leftWidth += 50;
			thirdCardAccountDetailLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			thirdCardAccountDetailLabels[i].setFont(new Font("Tahoma", Font.BOLD, 20));
			thirdCardAccountDetailLabels[i].setForeground(Color.white);
			seeAccountDetailPanel.add(thirdCardAccountDetailLabels[i]);
		}

		int rightWidth = 200;

		for (int i = 0; i < thirdCardAccountDetailLabels.length; i++) {
			secondCardAccountDetailLabels[i].setBounds(735 / 2, rightWidth, 735 / 2, 23);
			rightWidth += 50;
			secondCardAccountDetailLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			secondCardAccountDetailLabels[i].setFont(new Font("Tahoma", Font.BOLD, 20));
			secondCardAccountDetailLabels[i].setForeground(Color.white);
			seeAccountDetailPanel.add(secondCardAccountDetailLabels[i]);
		}

		rightWidth += 100;

		for (int i = 0; i < forthCardAccountDetailLabels.length; i++) {
			forthCardAccountDetailLabels[i].setBounds(735 / 2, rightWidth, 735 / 2, 23);
			rightWidth += 50;
			forthCardAccountDetailLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			forthCardAccountDetailLabels[i].setFont(new Font("Tahoma", Font.BOLD, 20));
			forthCardAccountDetailLabels[i].setForeground(Color.white);
			seeAccountDetailPanel.add(forthCardAccountDetailLabels[i]);
		}

		setBackground(seeAccountDetailPanel);
		setBackground(passwordAccountDetailPanel);
	}

	@SuppressWarnings("rawtypes")
	private void changePin() {
		changePinButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(changePinPanel);
				changePinPasswordIn.setText("");
				oldPinIn.setText("");
				newPinIn.setText("");
			}
		});

		changePinPassword = new JLabel("Password");
		changePinPassword.setBounds(100, 300, 180, 25);
		changePinPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		changePinPassword.setForeground(Color.white);
		changePinPanel.add(changePinPassword);

		changePinPasswordIn = new JPasswordField();
		changePinPasswordIn.setBounds(300, 300, 250, 25);
		changePinPanel.add(changePinPasswordIn);

		oldPin = new JLabel("Actual PIN");
		oldPin.setBounds(100, 385, 180, 25);
		oldPin.setFont(new Font("Tahoma", Font.BOLD, 20));
		oldPin.setForeground(Color.white);
		changePinPanel.add(oldPin);

		oldPinIn = new JPasswordField();
		oldPinIn.setBounds(300, 385, 250, 25);
		changePinPanel.add(oldPinIn);

		newPin = new JLabel("New PIN");
		newPin.setBounds(100, 460, 180, 25);
		newPin.setFont(new Font("Tahoma", Font.BOLD, 20));
		newPin.setForeground(Color.white);
		changePinPanel.add(newPin);

		newPinIn = new JPasswordField();
		newPinIn.setBounds(300, 460, 250, 25);
		changePinPanel.add(newPinIn);

		changePinCard = new JLabel("Card");
		changePinCard.setBounds(100, 545, 180, 25);
		changePinCard.setFont(new Font("Tahoma", Font.BOLD, 20));
		changePinCard.setForeground(Color.white);
		changePinPanel.add(changePinCard);

		changePinComboBox = new JComboBox();
		changePinComboBox.setBounds(300, 545, 250, 25);
		changePinPanel.add(changePinComboBox);

		change = new JButton("Change PIN");
		change.setBounds(320, 615, 100, 25);
		changePinPanel.add(change);

		setBackground(changePinPanel);
	}

	private void printReceipt() {
		printReceiptButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(printReceiptPanel);
				browseIn.setText("");
			}
		});

		saveToLabel = new JLabel("Save to");
		saveToLabel.setBounds(100, 400, 180, 25);
		saveToLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		saveToLabel.setForeground(Color.white);
		printReceiptPanel.add(saveToLabel);

		browseIn = new JTextField();
		browseIn.setBounds(300, 400, 250, 25);
		printReceiptPanel.add(browseIn);

		browseButton = new JButton("Browse");
		browseButton.setBounds(600, 400, 100, 25);
		printReceiptPanel.add(browseButton);

		browseButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				UserApplication.fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				UserApplication.fileChooser.showSaveDialog(null);
				browseIn.setText(UserApplication.fileChooser.getSelectedFile().getAbsolutePath());
			}
		});

		print = new JButton("Print");
		print.setBounds(350, 500, 100, 25);
		printReceiptPanel.add(print);

		setBackground(printReceiptPanel);
	}

	private void logout() {
		logoutButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				LoginInterface.frame.setVisible(true);
				Account.isAmountReturned = false;
				UserApplication.addToComboBox = false;
				for (int i = 0; i < tranzactii.length; i++) {
					tranzactii[i] = null;
				}
				LoginInterface.usernameIn.setText("");
				LoginInterface.passwordIn.setText("");
			}
		});
	}

}
