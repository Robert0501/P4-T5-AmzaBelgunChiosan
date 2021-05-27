package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import org.postgresql.util.PSQLException;

import app.Login;
import app.UserApplication;
import card.Account;
import card.Card;
import card.Transactions;
import graphicInterface.LoginInterface;
import graphicInterface.UserApplicationInterface;

public class Database {
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet rs = null;

	public Database() {
		connectDB();
		createClientTable();
		createAddressTable();
		createBirthdayTable();
		createAccountTable();
		createBankAccountTable();
		createCardTable();
		createTransactionsTable();
	}

	public static void connectDB() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Revolve", "postgres",
					"Laurentiu02");
			if (connection == null) {
				JOptionPane.showMessageDialog(LoginInterface.loginPanel, "Something went wrong.Please try again");
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createClientTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE Client ( FirstName varchar(50) not null, LastName varchar(25) not null, fullname varchar(70), CNP varchar(13) not null , PhoneNumber varchar(13) not null, Gender varchar(2),email varchar(50), primary key(CNP) );";
			statement.executeUpdate(query);
		} catch (PSQLException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createAddressTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE Address( Country varchar (25) not null, City varchar(25) not null, Street varchar(50) not null, Number varchar(50) not null, email varchar(50));";
			statement.executeUpdate(query);
		} catch (PSQLException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createBirthdayTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE DateOfBirth(Day int not null, month varchar(15) not null, year int not null, email varchar(50)); ";
			statement.executeUpdate(query);
		} catch (PSQLException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createAccountTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE Account(Username varchar(50), Email varchar(50), Password varchar(50), primary key(Username)) ;";
			statement.executeUpdate(query);
		} catch (PSQLException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createBankAccountTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE BankAccount(iban varchar(30), currency varchar(3), amount real, email varchar(50), cardsNumber int );";
			statement.executeUpdate(query);
		} catch (PSQLException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createCardTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE Card(email varchar(50), tipCard varchar(6), cardNumber varchar(25), expirationDate varchar(6), cvv int, currency varchar(4), pin int, amount real); ";
			statement.executeUpdate(query);
		} catch (PSQLException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createTransactionsTable() {
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE Transactions(email varchar(50), type varchar(15), amount real, transactionDate date, transactionHour time, name varchar(50), currency varchar(4)); ";
			statement.executeUpdate(query);
		} catch (PSQLException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoClient(String firstName, String lastName, String fullName, String cnp,
			String phoneNumber, String gender, String email) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO Client VALUES('" + firstName + "' , '" + lastName + "' , '" + fullName + "' , '"
					+ cnp + "' , '" + phoneNumber + "' , ' " + gender + "' , '" + email + "');";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoAddress(String country, String city, String street, String number, String email) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO Address VALUES(' " + country + "' , '" + city + "' , '" + street + "' , '"
					+ number + "' , '" + email + "');";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoDateOfBirth(int day, String month, int year, String email) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO DateOfBirth VALUES(" + day + ", '" + month + "' , " + year + " , '" + email
					+ "');";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoAccount(String username, String email, String password) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO Account VALUES('" + username + "' , '" + email + "' , '" + password + "'); ";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoBankAccount(String iban, String currency, double amount, String email) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO BankAccount VALUES('" + iban + "' , '" + currency + "' , " + amount + ", '"
					+ email + "'," + 0 + ");";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoCard(String email, String tipCard, String cardNumber, String expirationDate, int cvv,
			String currency, int pin, double amount) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO Card VALUES('" + email + "' , ' " + tipCard + "' , '" + cardNumber + "' , '"
					+ expirationDate + "'," + cvv + ", '" + currency + "' , " + pin + ", " + amount + ");";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insertIntoTransactions(String email, String type, double amount, LocalDate transactionDate,
			String time, String name, String currency) {
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO Transactions VALUES('" + email + "' , '" + type + "', " + amount + ", '"
					+ transactionDate + "', '" + time + "' , ' " + name + "' , '" + currency + "');";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkLoginData(String username, String password) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM Account WHERE username = '" + username + "' AND password = '" + password
					+ "';";
			rs = statement.executeQuery(query);
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean checkUsernameAndEmail(String username, String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT * FROM Account WHERE username = '" + username + "' AND email = '" + email + "';";
			rs = statement.executeQuery(query);
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String checkUserPassword(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT password FROM account WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean checkCNPUsage(String cnp) {
		try {
			statement = connection.createStatement();
			String query = "SELECT cnp FROM client WHERE cnp = '" + cnp + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean checkUsernameUsage(String username) {
		try {
			statement = connection.createStatement();
			String query = "SELECT username FROM account WHERE username = '" + username + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean checkEmailUsage(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT email FROM account WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean existCard(String cardNumber, String expirationDate, int cvv) {
		try {
			statement = connection.createStatement();
			String query = "SELECT email FROM card WHERE cardnumber = '" + cardNumber + "' AND expirationDate = '"
					+ expirationDate + "' AND cvv = " + cvv + ";";
			rs = statement.executeQuery(query);
			String email;
			while (rs.next()) {
				email = rs.getString("email");
				if (email.equals(Login.email))
					UserApplication.sameEmail = true;
				UserApplication.senderEmail = rs.getString("email");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean alreadyExistCurrency(String email, String currency) {
		try {
			statement = connection.createStatement();
			String query = "SELECT currency FROM card WHERE currency = '" + currency + "' AND email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean existRecieverCard(String cardNumber) {
		try {
			statement = connection.createStatement();
			String query = "SELECT fullname FROM client WHERE email = (SELECT email FROM card WHERE cardNumber = '"
					+ cardNumber + "');";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static String getUserEmail(String username) {
		try {
			statement = connection.createStatement();
			String query = "SELECT email FROM account WHERE username = '" + username + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("email");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getUserName() {
		try {
			statement = connection.createStatement();
			String query = "SELECT firstName FROM client WHERE email = '"
					+ getUserEmail(LoginInterface.usernameIn.getText()) + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("firstName");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getUserFullName(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT firstName, lastName FROM client WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("firstName") + " " + rs.getString("lastName");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String returnRecieverCurrency(String cardNumber, String fullName) {
		try {
			statement = connection.createStatement();
			String query = "SELECT currency FROM card WHERE cardNumber = '" + cardNumber
					+ "' AND email = (SELECT email FROM client WHERE fullname = '" + fullName + "');";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("currency");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String returnCardCurrency(String cardNumber) {
		try {
			statement = connection.createStatement();
			String query = "SELECT currency FROM card WHERE cardNumber = '" + cardNumber + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("currency");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String returnRecieverName(String cardNumber) {
		try {
			statement = connection.createStatement();
			String query = "SELECT fullname FROM client WHERE email = (SELECT email FROM card WHERE cardNumber = '"
					+ cardNumber + "');";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("fullname");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String returnIban(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT iban FROM bankAccount WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getString("iban");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static int returnCreatedCardsNumber(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT cardsNumber FROM bankAccount WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getInt("cardsNumber");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static int returnTransactionsNumber(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT COUNT (*) FROM transactions where email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static int returnPin(String email, String currency) {
		try {
			statement = connection.createStatement();
			String query = "SELECT pin FROM card WHERE email = '" + email + "' AND currency = '" + currency + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getInt("pin");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static double returnAllCardSAmount(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT amount FROM bankAccount WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getDouble("amount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static double returnAmount(String email, String cardNumber) {
		try {
			statement = connection.createStatement();
			String query = "SELECT amount FROM card WHERE email = '" + email + "' AND cardNumber = '" + cardNumber
					+ "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getDouble("amount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static double returnCardAmount(String email, String currency) {
		try {
			statement = connection.createStatement();
			String query = "SELECT amount FROM card WHERE email = '" + email + "' AND currency = '" + currency + "';";
			rs = statement.executeQuery(query);
			while (rs.next()) {
				return rs.getDouble("amount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static void returnEachCardAmount(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT amount FROM card WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			int i = 0;
			while (rs.next()) {
				Account.cardsAmount[i] = rs.getDouble("amount");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void returnEachCardCurrency(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT currency FROM card WHERE email = '" + email + "';";
			rs = statement.executeQuery(query);
			int i = 0;
			while (rs.next()) {
				Account.currency[i] = rs.getString("currency");
				if (UserApplication.addToComboBox == false) {
					UserApplicationInterface.sendMoneyCardComboBox.addItem(rs.getString("currency"));
					UserApplicationInterface.cardsComboBox.addItem(rs.getString("currency"));
					UserApplicationInterface.payUtilitiesCardComboBox.addItem(rs.getString("currency"));
					UserApplicationInterface.changePinComboBox.addItem(rs.getString("currency"));
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void returnTransactionData(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT type, amount, transactiondate, transactionhour, name, currency FROM transactions WHERE email = '"
					+ email + "';";
			rs = statement.executeQuery(query);
			int i = 0;
			while (rs.next()) {
				UserApplicationInterface.tranzactii[i] = new Transactions(rs.getString("amount"), rs.getString("type"),
						rs.getString("transactiondate"), rs.getString("transactionhour"), rs.getString("currency"),
						rs.getString("name"));
				i++;
				if (i == UserApplicationInterface.tranzactii.length) {
					i = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void returnCardData(String email) {
		try {
			statement = connection.createStatement();
			String query = "SELECT expirationdate, currency, cardnumber, pin, cvv, amount FROM card WHERE email = '"
					+ email + "';";
			rs = statement.executeQuery(query);
			int i = 0;
			while (rs.next()) {
				UserApplicationInterface.cards[i] = new Card(rs.getString("expirationdate"), rs.getString("currency"),
						rs.getString("cardnumber"), Integer.parseInt(rs.getString("pin")),
						Integer.parseInt(rs.getString("cvv")), Double.parseDouble(rs.getString("amount")));
				i++;
				if (i == UserApplicationInterface.tranzactii.length) {
					i = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void incrementCardNumber(String email) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE bankAccount SET cardsNumber = cardsNumber + 1 WHERE email = '" + email + "';";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updatePassword(String password, String username) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE Account SET password = '" + password + "' WHERE username = '" + username + "';";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateAmountTopUp(double amount, String emailReciever, String emailSender,
			String recieverCurrency, String senderCurrency) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE card SET amount = amount + " + amount + " WHERE email = '" + emailReciever
					+ "' AND currency = '" + recieverCurrency + "';" + "UPDATE card SET amount = amount - " + amount
					+ " WHERE email = '" + emailSender + "' AND currency = '" + senderCurrency + "';";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateAmountSendMoney(double minusAmount, double plusAmount, String currency, String email,
			String cardNumber, String fullName) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE card SET amount = amount - " + minusAmount + " WHERE currency = '" + currency
					+ "' AND email = '" + email + "';" + " UPDATE card SET amount = amount + " + plusAmount
					+ " WHERE cardNumber = '" + cardNumber
					+ "' AND email = (SELECT email FROM client WHERE fullname = '" + fullName + "');";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateAmountPayUtilities(double amount, String email, String currency) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE card SET amount = amount - " + amount + " WHERE email = '" + email
					+ "' AND currency = '" + currency + "';";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changePin(int pin, String email, String currency) {
		try {
			statement = connection.createStatement();
			String query = "UPDATE card SET pin = " + pin + "WHERE email = '" + email + "'AND currency = '" + currency
					+ "';";
			statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
