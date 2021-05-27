package card;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import app.ForgotPassword;

public class Card {

	public static String cardNumber;
	public static int pin;

	private String expirationDate;
	private String currency;
	private String cardNo;
	private int cardPin;
	private int cvv;
	private double amount;

	public Card(String expirationDate, String currency, String cardNo, int cardPin, int cvv, double amount) {
		super();
		this.expirationDate = expirationDate;
		this.currency = currency;
		this.cardNo = cardNo;
		this.cardPin = cardPin;
		this.cvv = cvv;
		this.amount = amount;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public int getCardPin() {
		return cardPin;
	}

	public void setCardPin(int cardPin) {
		this.cardPin = cardPin;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	private static boolean checkCardNumber(String card) {
		boolean ok = false;
		long nr = Long.parseLong(card);
		long lastDigit = 0;
		long sumaPar = 0;
		long sumaImpar = 0;

		while (nr != 0) {
			lastDigit = nr % 10;
			sumaImpar += lastDigit;
			nr /= 100;
		}

		nr = Long.parseLong(card) / 10;

		while (nr != 0) {
			lastDigit = nr % 10;
			lastDigit *= 2;
			if (lastDigit * 2 >= 10) {
				sumaPar += (lastDigit % 10);
				lastDigit /= 10;
				sumaPar += (lastDigit % 10);
			} else {
				sumaPar += (lastDigit);
			}
			nr /= 100;
		}
		if ((sumaPar + sumaImpar) % 10 == 0) {
			ok = true;
		}
		return ok;
	}

	public static int nrDigits(long nr) {
		int counter = 0;
		long temp = nr;

		while (temp != 0) {
			temp /= 10;
			counter++;
		}

		return counter;
	}

	private static String generateNrCard() {
		Random rand = new Random();
		long number = 4;
		long nr;

		number = 4;
		while (nrDigits(number) != 16) {
			nr = rand.nextInt() % 9;
			while (nr < 0)
				nr = rand.nextInt() % 9;
			number = number * 10 + nr;
		}
		if (number < 0)
			number = -number;

		return String.valueOf(number);

	}

	public static String genereazaNrCard() {
		long nrCard = 0;
		nrCard = Long.parseLong(generateNrCard());
		while (!checkCardNumber(String.valueOf(nrCard))) {
			nrCard = Long.parseLong(generateNrCard());
		}
		return String.valueOf(nrCard);
	}

	public static int generateCVV() {
		int number = -1;
		Random rand = new Random();
		do {
			number = rand.nextInt() % 1000;
		} while (number < 0 || ForgotPassword.digits(number) != 3);
		return number;
	}

	public static String generateExpirationDate() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/yy");
		dateTime = dateTime.plusYears(5);
		String formattedDate = dateTime.format(dateFormatter);
		return formattedDate;
	}

	public static int generatePin() {
		int pin = 0;
		Random rand = new Random();
		do {
			pin = rand.nextInt() % 10000;
		} while (pin < 0 || ForgotPassword.digits(pin) != 4);
		return pin;
	}

}
