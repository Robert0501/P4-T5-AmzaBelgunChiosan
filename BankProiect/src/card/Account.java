package card;

import java.util.Random;

import app.ForgotPassword;
import app.Login;
import app.UserApplication;
import database.Database;

public class Account {

	public static boolean isAmountReturned = false;

	public static double cardsAmount[] = new double[4];
	public static String currency[] = new String[4];

	public static String generateIBAN() {
		String iban = "RO";
		Random rand = new Random();
		int nr;
		do {
			nr = rand.nextInt() % 100;
		} while (nr <= 0 || ForgotPassword.digits(nr) != 2);

		iban += String.valueOf(nr);
		iban += " REVL 251V ";

		do {
			nr = rand.nextInt() % 10000;
		} while (nr <= 0 || ForgotPassword.digits(nr) != 4);
		iban += String.valueOf(nr);

		iban += " ";
		do {
			nr = rand.nextInt() % 10000;
		} while (nr <= 0 || ForgotPassword.digits(nr) != 4);
		iban += String.valueOf(nr);
		iban += " 2510";
		return iban;
	}

	public static double calculateTotalAmount() {
		for (int i = 0; i < currency.length; i++) {
			currency[i] = "";
			cardsAmount[i] = 0;
		}

		Database.returnEachCardAmount(Login.email);
		if (!isAmountReturned) {
			Database.returnEachCardCurrency(Login.email);
			isAmountReturned = true;
			UserApplication.addToComboBox = true;
		}
		double sum = 0;

		for (int i = 0; i < currency.length; i++) {
			switch (currency[i]) {
			case "USD":
				cardsAmount[i] *= 0.80;
				break;
			case "RON":
				cardsAmount[i] *= 0.20;
				break;
			case "GBP":
				cardsAmount[i] *= 1.15;
				break;
			case "":
				break;
			}
			sum += cardsAmount[i];
		}

		return sum;

	}

	public static void calculateNewTotalAmount(String recieverCurrency, String senderCurrency, double amount,
			String op) {

		String[] parts = Login.totalAmount.split("EUR");
		String part1 = parts[0];

		double totalAmount = Double.parseDouble(part1);
		double currencyAmount = currencyAmount(recieverCurrency, senderCurrency, amount);
		double total;

		switch (op) {
		case "+":
			total = totalAmount + currencyAmount;
			Login.totalAmount = String.valueOf(total);
			break;
		case "-":
			total = totalAmount - currencyAmount;
			Login.totalAmount = String.valueOf(total);
			break;
		}
	}

	public static double currencyAmount(String recieverCurrency, String senderCurrency, double amount) {
		double finalAmount = 0;
		switch (recieverCurrency) {
		case "RON":
			switch (senderCurrency) {
			case "RON":
				finalAmount = amount;
				break;
			case "EUR":
				finalAmount = amount * 4.90;
				break;
			case "GBP":
				finalAmount = amount * 5.65;
				break;
			case "USD":
				finalAmount = amount * 5.05;
				break;
			}
			break;
		case "EUR":
			switch (senderCurrency) {
			case "RON":
				finalAmount = amount * 0.20;
				break;
			case "EUR":
				finalAmount = amount;
				break;
			case "GBP":
				finalAmount = amount * 1.15;
				break;
			case "USD":
				finalAmount = amount * 0.80;
				break;
			}
			break;
		case "GBP":
			switch (senderCurrency) {
			case "RON":
				finalAmount = amount * 0.20;
				break;
			case "EUR":
				finalAmount = amount * 0.85;
				break;
			case "GBP":
				finalAmount = amount;
				break;
			case "USD":
				finalAmount = amount * 0.70;
				break;
			}
			break;
		case "USD":
			switch (senderCurrency) {
			case "RON":
				finalAmount = amount * 0.25;
				break;
			case "EUR":
				finalAmount = amount * 1.20;
				break;
			case "GBP":
				finalAmount = amount * 1.40;
				break;
			case "USD":
				finalAmount = amount;
				break;
			}
			break;
		}

		return finalAmount;
	}

}
