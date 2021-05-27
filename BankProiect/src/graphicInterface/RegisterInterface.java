package graphicInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import app.Register;

public class RegisterInterface {

	public static Font labelFont;
	public static Font textFont;
	public static Font titleLabelFont;

	public static JPanel personalDataPanel;
	public static JPanel birthdayPanel;
	public static JPanel loginDataPanel;
	public static JPanel addressPanel;

	private JLabel personalData;
	private JLabel firstName;
	private JLabel lastName;
	private JLabel cnp;
	private JLabel phoneNumber;

	public static JTextField firstNameIn;
	public static JTextField lastNameIn;
	public static JTextField cnpIn;
	public static JTextField phoneNumberIn;

	private JLabel addressLabel;
	private JLabel country;
	private JLabel city;
	private JLabel street;
	private JLabel number;

	public static JTextField countryIn;
	public static JTextField cityIn;
	public static JTextField streetIn;
	public static JTextField numberIn;

	private JLabel dateOfBirth;
	private JLabel day;
	private JLabel month;
	private JLabel year;
	private JLabel gender;

	@SuppressWarnings("rawtypes")
	public static JComboBox dayComboBox;
	@SuppressWarnings("rawtypes")
	public static JComboBox monthComboBox;
	@SuppressWarnings("rawtypes")
	public static JComboBox yearComboBox;
	public static JRadioButton male;
	public static JRadioButton female;

	private JLabel loginData;
	private JLabel username;
	private JLabel password;
	private JLabel rePassword;
	private JLabel email;

	public static JTextField usernameIn;
	public static JTextField emailIn;
	public static JPasswordField passwordIn;
	public static JPasswordField rePasswordIn;

	private String[] dayChooses = new String[32];
	private String[] monthChooses = { "Month", "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	private String[] yearChooses = new String[73];

	public static ButtonGroup genderGroup;

	private JLabel background;

	public static JButton personalDataPanelNextButton;
	public static JButton addressPanelNextButton;
	public static JButton birthdayPanelNextButton;
	public static JButton loginDataNextButton;

	public static JButton personalDataPanelBackButton;
	public static JButton addressPanelBackButton;
	public static JButton birthdayPanelBackButton;
	public static JButton loginDataBackButton;

	private void putPanel(JPanel panel) {
		panel.setLayout(null);
		panel.setVisible(false);
		panel.setSize(1080, 720);
		LoginInterface.frame.getContentPane().add(panel);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RegisterInterface() {

		labelFont = new Font("Tahoma", Font.BOLD, 22);
		textFont = new Font("Tahoma", Font.PLAIN, 13);
		titleLabelFont = new Font("Tahoma", Font.BOLD, 35);

		LoginInterface.frame.setTitle("Register");

		addressPanel = new JPanel();

		personalDataPanel = new JPanel();
		birthdayPanel = new JPanel();
		loginDataPanel = new JPanel();
		putPanel(personalDataPanel);
		putPanel(addressPanel);
		putPanel(birthdayPanel);
		putPanel(loginDataPanel);
		personalDataPanel.setVisible(true);

		personalData = new JLabel("Personal Data");
		personalData.setBounds(700, 150, 250, 30);
		personalData.setFont(titleLabelFont);

		firstName = new JLabel("First Name");
		firstName.setBounds(600, 250, 150, 25);
		firstName.setFont(labelFont);

		firstNameIn = new JTextField();
		firstNameIn.setBounds(750, 250, 250, 25);
		firstNameIn.setFont(textFont);
		firstNameIn.setBackground(Color.LIGHT_GRAY);
		firstNameIn.setBackground(Color.decode("#DFDFE2"));

		lastName = new JLabel("Last Name");
		lastName.setBounds(600, 300, 150, 25);
		lastName.setFont(labelFont);

		lastNameIn = new JTextField();
		lastNameIn.setBounds(750, 300, 250, 25);
		lastNameIn.setFont(textFont);
		lastNameIn.setBackground(Color.decode("#DFDFE2"));

		cnp = new JLabel("CNP");
		cnp.setBounds(600, 350, 150, 25);
		cnp.setFont(labelFont);

		cnpIn = new JTextField();
		cnpIn.setBounds(750, 350, 250, 25);
		cnpIn.setFont(textFont);
		cnpIn.setBackground(Color.decode("#DFDFE2"));

		phoneNumber = new JLabel("Phone");
		phoneNumber.setBounds(600, 400, 150, 25);
		phoneNumber.setFont(labelFont);

		phoneNumberIn = new JTextField();
		phoneNumberIn.setBounds(750, 400, 250, 25);
		phoneNumberIn.setFont(textFont);
		phoneNumberIn.setBackground(Color.decode("#DFDFE2"));

		background = new JLabel("");
		background.setIcon(new ImageIcon("src//Images//Backgrounds//cards.jpg"));
		background.setSize(1080, 720);

		personalDataPanelBackButton = new JButton("Back");
		personalDataPanelBackButton.setBounds(650, 500, 150, 30);
		personalDataPanelBackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		personalDataPanelNextButton = new JButton("Next");
		personalDataPanelNextButton.setBounds(850, 500, 150, 30);
		personalDataPanelNextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		personalDataPanel.add(personalData);
		personalDataPanel.add(firstName);
		personalDataPanel.add(firstNameIn);
		personalDataPanel.add(lastName);
		personalDataPanel.add(lastNameIn);
		personalDataPanel.add(cnp);
		personalDataPanel.add(cnpIn);
		personalDataPanel.add(phoneNumber);
		personalDataPanel.add(phoneNumberIn);
		personalDataPanel.add(personalDataPanelBackButton);
		personalDataPanel.add(personalDataPanelNextButton);
		personalDataPanel.add(background);

		addressLabel = new JLabel("Address");
		addressLabel.setBounds(700, 150, 250, 30);
		addressLabel.setFont(RegisterInterface.titleLabelFont);

		country = new JLabel("Country");
		country.setBounds(600, 250, 150, 25);
		country.setFont(RegisterInterface.labelFont);

		countryIn = new JTextField();
		countryIn.setBounds(750, 250, 250, 25);
		countryIn.setFont(RegisterInterface.textFont);
		countryIn.setBackground(Color.decode("#DFDFE2"));

		city = new JLabel("City");
		city.setBounds(600, 300, 150, 25);
		city.setFont(RegisterInterface.labelFont);

		cityIn = new JTextField();
		cityIn.setBounds(750, 300, 250, 25);
		cityIn.setFont(RegisterInterface.textFont);
		cityIn.setBackground(Color.decode("#DFDFE2"));

		street = new JLabel("Street");
		street.setBounds(600, 350, 150, 25);
		street.setFont(RegisterInterface.labelFont);

		streetIn = new JTextField();
		streetIn.setBounds(750, 350, 250, 25);
		streetIn.setFont(RegisterInterface.textFont);
		streetIn.setBackground(Color.decode("#DFDFE2"));

		number = new JLabel("Number");
		number.setBounds(600, 400, 150, 25);
		number.setFont(RegisterInterface.labelFont);

		numberIn = new JTextField();
		numberIn.setBounds(750, 400, 250, 25);
		numberIn.setFont(RegisterInterface.textFont);
		numberIn.setBackground(Color.decode("#DFDFE2"));

		background = new JLabel("");
		background.setIcon(new ImageIcon("src//Images//Backgrounds//cards.jpg"));
		background.setSize(1080, 720);

		addressPanelBackButton = new JButton("Back");
		addressPanelBackButton.setBounds(650, 500, 150, 30);
		addressPanelBackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addressPanelNextButton = new JButton("Next");
		addressPanelNextButton.setBounds(850, 500, 150, 30);
		addressPanelNextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addressPanel.add(addressLabel);
		addressPanel.add(country);
		addressPanel.add(countryIn);
		addressPanel.add(country);
		addressPanel.add(countryIn);
		addressPanel.add(city);
		addressPanel.add(cityIn);
		addressPanel.add(street);
		addressPanel.add(streetIn);
		addressPanel.add(number);
		addressPanel.add(numberIn);
		addressPanel.add(addressPanelNextButton);
		addressPanel.add(addressPanelBackButton);
		addressPanel.add(background);

		dateOfBirth = new JLabel("Date of Birth");
		dateOfBirth.setBounds(700, 150, 250, 30);
		dateOfBirth.setFont(RegisterInterface.titleLabelFont);

		day = new JLabel("Day");
		day.setBounds(600, 250, 150, 25);
		day.setFont(RegisterInterface.labelFont);

		dayChooses[0] = "Day";
		for (int i = 1; i < 32; i++) {
			dayChooses[i] = String.valueOf(i);
		}
		dayComboBox = new JComboBox(dayChooses);
		dayComboBox.setBounds(750, 250, 250, 25);

		month = new JLabel("Month");
		month.setBounds(600, 300, 150, 25);
		month.setFont(RegisterInterface.labelFont);

		monthComboBox = new JComboBox(monthChooses);
		monthComboBox.setBounds(750, 300, 250, 25);

		year = new JLabel("Year");
		year.setBounds(600, 350, 150, 25);
		year.setFont(RegisterInterface.labelFont);

		yearChooses[0] = "Year";
		int j = 2021;
		for (int i = 1; i < 73; i++) {
			yearChooses[i] = String.valueOf(j);
			j--;
		}

		yearComboBox = new JComboBox(yearChooses);
		yearComboBox.setBounds(750, 350, 250, 25);

		background = new JLabel("");
		background.setIcon(new ImageIcon("src//Images//Backgrounds//cards.jpg"));
		background.setSize(1080, 720);

		gender = new JLabel("Gender");
		gender.setBounds(600, 400, 150, 25);
		gender.setFont(RegisterInterface.labelFont);

		male = new JRadioButton("Male");
		male.setBounds(750, 400, 125, 25);
		male.setBackground(Color.decode("#DFDFE2"));
		female = new JRadioButton("Female");
		female.setBounds(875, 400, 125, 25);
		female.setBackground(Color.decode("#DFDFE2"));

		genderGroup = new ButtonGroup();
		genderGroup.add(male);
		genderGroup.add(female);

		birthdayPanelBackButton = new JButton("Back");
		birthdayPanelBackButton.setBounds(650, 500, 150, 30);
		birthdayPanelBackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		birthdayPanelNextButton = new JButton("Next");
		birthdayPanelNextButton.setBounds(850, 500, 150, 30);
		birthdayPanelNextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		birthdayPanel.add(dateOfBirth);
		birthdayPanel.add(day);
		birthdayPanel.add(dayComboBox);
		birthdayPanel.add(month);
		birthdayPanel.add(monthComboBox);
		birthdayPanel.add(year);
		birthdayPanel.add(yearComboBox);
		birthdayPanel.add(gender);
		birthdayPanel.add(male);
		birthdayPanel.add(female);
		birthdayPanel.add(birthdayPanelBackButton);
		birthdayPanel.add(birthdayPanelNextButton);
		birthdayPanel.add(background);

		loginData = new JLabel("Login Data");
		loginData.setBounds(700, 150, 250, 55);
		loginData.setFont(RegisterInterface.titleLabelFont);

		username = new JLabel("Username");
		username.setBounds(600, 250, 150, 25);
		username.setFont(RegisterInterface.labelFont);

		usernameIn = new JTextField();
		usernameIn.setBounds(750, 250, 250, 25);
		usernameIn.setFont(RegisterInterface.textFont);
		usernameIn.setBackground(Color.decode("#DFDFE2"));

		email = new JLabel("Email");
		email.setBounds(600, 300, 150, 25);
		email.setFont(RegisterInterface.labelFont);

		emailIn = new JTextField();
		emailIn.setBounds(750, 300, 250, 25);
		emailIn.setFont(RegisterInterface.textFont);
		emailIn.setBackground(Color.decode("#DFDFE2"));

		password = new JLabel("Password");
		password.setBounds(600, 350, 150, 25);
		password.setFont(RegisterInterface.labelFont);

		passwordIn = new JPasswordField();
		passwordIn.setBounds(750, 350, 250, 25);
		passwordIn.setFont(RegisterInterface.textFont);
		passwordIn.setBackground(Color.decode("#DFDFE2"));

		rePassword = new JLabel("Confirm Pass");
		rePassword.setBounds(600, 400, 150, 25);
		rePassword.setFont(RegisterInterface.labelFont);

		rePasswordIn = new JPasswordField();
		rePasswordIn.setBounds(750, 400, 250, 25);
		rePasswordIn.setFont(RegisterInterface.textFont);
		rePasswordIn.setBackground(Color.decode("#DFDFE2"));

		background = new JLabel("");
		background.setIcon(new ImageIcon("src//Images//Backgrounds//cards.jpg"));
		background.setSize(1080, 720);

		loginDataBackButton = new JButton("Back");
		loginDataBackButton.setBounds(650, 500, 150, 30);
		loginDataBackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		loginDataNextButton = new JButton("Finish");
		loginDataNextButton.setBounds(850, 500, 150, 30);
		loginDataNextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		loginDataPanel.add(loginData);
		loginDataPanel.add(username);
		loginDataPanel.add(usernameIn);
		loginDataPanel.add(email);
		loginDataPanel.add(emailIn);
		loginDataPanel.add(password);
		loginDataPanel.add(passwordIn);
		loginDataPanel.add(rePassword);
		loginDataPanel.add(rePasswordIn);
		loginDataPanel.add(loginDataBackButton);
		loginDataPanel.add(loginDataNextButton);
		loginDataPanel.add(background);

		new Register();
	}

}
