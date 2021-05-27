package app;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import card.Account;
import database.Database;
import email.EmailThread;
import graphicInterface.LoginInterface;
import graphicInterface.RegisterInterface;
import regex.RegEx;

public class Register {

	static Pattern pattern;
	EmailThread sendEmail[] = new EmailThread[20];
	int sendEmailCounter = 0;

	public Register() {
		for (int i = 0; i < 20; i++) {
			sendEmail[i] = new EmailThread();
		}

		checkPersonalData();
		checkAddressData();
		checkBirthdayData();
		checkLoginData();
	}

	public void checkPersonalData() {
		RegisterInterface.personalDataPanelNextButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (RegisterInterface.firstNameIn.getText().equals("")) {
					JOptionPane.showMessageDialog(RegisterInterface.personalDataPanel, "Please enter your First Name");
				} else if (RegisterInterface.lastNameIn.getText().equals("")) {
					JOptionPane.showMessageDialog(RegisterInterface.personalDataPanel, "Please enter your Last Name");
				} else if (!RegEx.checkCNP(RegisterInterface.cnpIn.getText())) {
					JOptionPane.showMessageDialog(RegisterInterface.personalDataPanel, "Please enter a valid CNP");
				} else if (!RegEx.checkPhone(RegisterInterface.phoneNumberIn.getText())) {
					JOptionPane.showMessageDialog(RegisterInterface.personalDataPanel,
							"Please enter a valid Phone Number");
				} else if (Database.checkCNPUsage(RegisterInterface.cnpIn.getText())) {
					JOptionPane.showMessageDialog(RegisterInterface.personalDataPanel,
							"There already exists an account using this CNP");
				} else {
					RegisterInterface.personalDataPanel.setVisible(false);
					RegisterInterface.addressPanel.setVisible(true);
				}

			}
		});

		RegisterInterface.personalDataPanelBackButton.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				LoginInterface.frame.setTitle("Login");
				LoginInterface.loginPanel.setVisible(true);
				RegisterInterface.personalDataPanel.setVisible(false);
			}

		});
	}

	public void checkAddressData() {
		RegisterInterface.addressPanelNextButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (RegisterInterface.countryIn.getText().equals("")) {
					JOptionPane.showMessageDialog(RegisterInterface.addressPanel, "Please enter your Country");
				} else if (RegisterInterface.cityIn.getText().equals("")) {
					JOptionPane.showMessageDialog(RegisterInterface.addressPanel, "Please enter your City");
				} else if (RegisterInterface.streetIn.getText().equals("")) {
					JOptionPane.showMessageDialog(RegisterInterface.addressPanel, "Please enter your Street");
				} else if (RegisterInterface.numberIn.getText().equals("")) {
					JOptionPane.showMessageDialog(RegisterInterface.addressPanel, "Please enter your Number");
				} else {
					RegisterInterface.addressPanel.setVisible(false);
					RegisterInterface.birthdayPanel.setVisible(true);

				}
			}
		});

		RegisterInterface.addressPanelBackButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				RegisterInterface.addressPanel.setVisible(false);
				RegisterInterface.personalDataPanel.setVisible(true);
			}

		});
	}

	public void checkBirthdayData() {
		RegisterInterface.birthdayPanelNextButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (RegisterInterface.dayComboBox.getSelectedItem().equals("Day")) {
					JOptionPane.showMessageDialog(RegisterInterface.birthdayPanel, "Please enter your Day of Birth");
				} else if (RegisterInterface.monthComboBox.getSelectedItem().equals("Month")) {
					JOptionPane.showMessageDialog(RegisterInterface.birthdayPanel, "Please enter your Month of Birth");
				} else if (RegisterInterface.yearComboBox.getSelectedItem().equals("Year")) {
					JOptionPane.showMessageDialog(RegisterInterface.birthdayPanel, "Please enter your Year of Birth");
				} else if (RegisterInterface.genderGroup.getSelection() == null) {
					JOptionPane.showMessageDialog(RegisterInterface.birthdayPanel, "Please select your Gender");
				} else {
					String month = "";
					switch (RegisterInterface.monthComboBox.getSelectedItem().toString()) {
					case "January":
						month = "01";
						break;
					case "February":
						month = "02";
						break;
					case "March":
						month = "03";
						break;
					case "April":
						month = "04";
						break;
					case "May":
						month = "05";
						break;
					case "June":
						month = "06";
						break;
					case "July":
						month = "07";
						break;
					case "August":
						month = "08";
						break;
					case "September":
						month = "09";
						break;
					case "October":
						month = "10";
						break;
					case "November":
						month = "11";
						break;
					case "December":
						month = "12";
						break;
					}

					String day = "";
					String gen;
					if (RegisterInterface.male.isSelected()) {
						gen = "M";
					} else {
						gen = "F";
					}

					if (Integer.parseInt(RegisterInterface.dayComboBox.getSelectedItem().toString()) < 10) {
						day = "0" + RegisterInterface.dayComboBox.getSelectedItem().toString();
					} else {
						day = RegisterInterface.dayComboBox.getSelectedItem().toString();
					}
					String dateOfBirth = RegisterInterface.yearComboBox.getSelectedItem().toString() + "-" + month + "-"
							+ day;
					LocalDate birthday = LocalDate.parse(dateOfBirth.toString());
					LocalDate today = LocalDate.now();
					Period p = Period.between(birthday, today);
					if (!checkCnp(RegisterInterface.dayComboBox.getSelectedItem().toString(),
							RegisterInterface.monthComboBox.getSelectedItem().toString(),
							RegisterInterface.yearComboBox.getSelectedItem().toString(), gen,
							RegisterInterface.cnpIn.getText())) {
						JOptionPane.showMessageDialog(RegisterInterface.birthdayPanel,
								"CNP and birthday data doesn't match");
					} else if (p.getYears() >= 18) {
						RegisterInterface.birthdayPanel.setVisible(false);
						RegisterInterface.loginDataPanel.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(RegisterInterface.birthdayPanel,
								"You can not create an account becouse you are under 18!");
						RegisterInterface.birthdayPanel.setVisible(false);
						LoginInterface.loginPanel.setVisible(true);
					}

				}
			}
		});

		RegisterInterface.birthdayPanelBackButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				RegisterInterface.birthdayPanel.setVisible(false);
				RegisterInterface.addressPanel.setVisible(true);
			}
		});
	}

	public void checkLoginData() {
		RegisterInterface.loginDataNextButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			public void mouseClicked(MouseEvent e) {

				if (RegisterInterface.usernameIn.getText().equals("")) {
					JOptionPane.showMessageDialog(RegisterInterface.loginDataPanel, "Please enter an Username");
				} else if (!RegEx.checkEmail(RegisterInterface.emailIn.getText())) {
					JOptionPane.showMessageDialog(RegisterInterface.loginDataPanel, "Please enter a valid Email");
				} else if (!RegEx.checkPassword(RegisterInterface.passwordIn.getText())) {
					JOptionPane.showMessageDialog(RegisterInterface.loginDataPanel, "Please enter a Strong Passowrd");
				} else if (RegisterInterface.rePasswordIn.getText().equals("")) {
					JOptionPane.showMessageDialog(RegisterInterface.loginDataPanel, "Please retype your Password");
				} else if (!RegisterInterface.passwordIn.getText().equals(RegisterInterface.rePasswordIn.getText())) {
					JOptionPane.showMessageDialog(RegisterInterface.loginDataPanel, "Please enter the same Password");
				} else if (Database.checkUsernameUsage(RegisterInterface.usernameIn.getText())) {
					JOptionPane.showMessageDialog(RegisterInterface.loginDataPanel,
							"There already exists an account with this username");
				} else if (Database.checkEmailUsage(RegisterInterface.emailIn.getText())) {
					JOptionPane.showMessageDialog(RegisterInterface.loginDataPanel,
							"There already exists an account with this email");
				} else {
					String gen;
					if (RegisterInterface.male.isSelected()) {
						gen = "M";
					} else {
						gen = "F";
					}

					Database.insertIntoClient(RegisterInterface.firstNameIn.getText(),
							RegisterInterface.lastNameIn.getText(),
							RegisterInterface.firstNameIn.getText() + " " + RegisterInterface.lastNameIn.getText(),
							RegisterInterface.cnpIn.getText(), RegisterInterface.phoneNumberIn.getText(), gen,
							RegisterInterface.emailIn.getText());

					Database.insertIntoAddress(RegisterInterface.countryIn.getText(),
							RegisterInterface.cityIn.getText(), RegisterInterface.streetIn.getText(),
							RegisterInterface.numberIn.getText(), RegisterInterface.emailIn.getText());

					Database.insertIntoDateOfBirth(
							Integer.parseInt(RegisterInterface.dayComboBox.getSelectedItem().toString()),
							RegisterInterface.monthComboBox.getSelectedItem().toString(),
							Integer.parseInt(RegisterInterface.yearComboBox.getSelectedItem().toString()),
							RegisterInterface.emailIn.getText());

					Database.insertIntoAccount(RegisterInterface.usernameIn.getText(),
							RegisterInterface.emailIn.getText(), RegisterInterface.passwordIn.getText());

					Database.insertIntoBankAccount(Account.generateIBAN(), "EUR", 0.0,
							RegisterInterface.emailIn.getText());

					Login.index = 1;
					sendEmail[sendEmailCounter].start();
					sendEmailCounter++;
					JOptionPane.showMessageDialog(RegisterInterface.loginDataPanel,
							"Account Succesfully Created. You can login now");
					LoginInterface.usernameIn.setText("");
					LoginInterface.passwordIn.setText("");

					RegisterInterface.loginDataPanel.setVisible(false);
					LoginInterface.loginPanel.setVisible(true);
				}
			}
		});

		RegisterInterface.loginDataBackButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				RegisterInterface.loginDataPanel.setVisible(false);
				RegisterInterface.birthdayPanel.setVisible(true);
			}
		});
	}

	private boolean checkCnp(String zi, String luna, String an, String gender, String cnp) {
		int anInteger = Integer.valueOf(an);
		String dayDigits = cnp.charAt(5) + "" + cnp.charAt(6);
		String monthDigits = cnp.charAt(3) + "" + cnp.charAt(4);
		String cnpanDigits = cnp.charAt(1) + "" + cnp.charAt(2);
		String insertAnDigits = an.charAt(2) + "" + an.charAt(3);
		String month = "";
		switch (luna) {
		case "January":
			month = "01";
			break;
		case "February":
			month = "02";
			break;
		case "March":
			month = "03";
			break;
		case "April":
			month = "04";
			break;
		case "May":
			month = "05";
			break;
		case "June":
			month = "06";
			break;
		case "July":
			month = "07";
			break;
		case "August":
			month = "08";
			break;
		case "September":
			month = "09";
			break;
		case "October":
			month = "10";
			break;
		case "November":
			month = "11";
			break;
		case "December":
			month = "12";
			break;
		}

		if ((anInteger < 2000 && gender.equals("M") && cnp.charAt(0) != '1')
				|| (anInteger < 2000 && gender.equals("F") && cnp.charAt(0) != '2')
				|| (anInteger >= 2000 && gender.equals("M") && cnp.charAt(0) != '5')
				|| (anInteger >= 2000 && gender.equals("F") && cnp.charAt(0) != '6') || (!dayDigits.equals(zi))
				|| (!monthDigits.equals(month)) || (!cnpanDigits.equals(insertAnDigits))) {
			return false;
		} else {
			return true;
		}
	}

}
