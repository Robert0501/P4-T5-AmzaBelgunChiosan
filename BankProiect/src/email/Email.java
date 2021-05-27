package email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import app.ForgotPassword;
import app.Login;
import card.Card;
import graphicInterface.RegisterInterface;

public class Email {
	public static void sendMail(String recepient) {
		System.out.println("Preparing to send email");
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String myAccountEmail = "revolvebank@gmail.com";
		String myPassowrd = "REVOLVEbank";

		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, myPassowrd);
			}
		});
		session.setDebug(true);

		try {
			switch (Login.index) {
			case 1:
				Message newAccountMessage = newAccountEmail(session, myAccountEmail, recepient);
				Transport.send(newAccountMessage);
				break;
			case 2:
				Message forgotPasswordEmail = forgotPasswordEmail(session, myAccountEmail, recepient);
				Transport.send(forgotPasswordEmail);
				break;
			case 3:
				Message changePasswordEmail = changePasswordEmail(session, myAccountEmail, recepient);
				Transport.send(changePasswordEmail);
				break;
			case 4:
				Message createNewCardEmail = createNewCardEmail(session, myAccountEmail, recepient);
				Transport.send(createNewCardEmail);
				break;
			}

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("Message sent");

	}

	private static Message newAccountEmail(Session session, String myAccountEmail, String recepient) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Revolve Registration");
			message.setText("Welcome to Revolve, " + RegisterInterface.lastNameIn.getText() + " "
					+ RegisterInterface.firstNameIn.getText()
					+ "!\n\nThanks for using our bank.\n\nHope for the best!\nRevolveBank Stuff");
			return message;

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Message forgotPasswordEmail(Session session, String myAccountEmail, String recepient) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Revolve Forgot Password");
			message.setText(
					"We are sorry to hear that!\n\nHere is the code you have to provide in order to change your password.\nCode: "
							+ ForgotPassword.code + "\nThanks for using our bank.\n\nHope for the best!"
							+ "\nRevolveBank Stuff");
			return message;

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Message changePasswordEmail(Session session, String myAccountEmail, String recepient) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Revolve Password Changed");
			message.setText("You have succesffully changed your password!\nTry to remember this one :)"
					+ "\nThanks for using our bank.\n\nHope for the best!" + "\nRevolveBank Stuff");
			return message;

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Message createNewCardEmail(Session session, String myAccountEmail, String recepient) {
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Revolve New Card");
			message.setText("Hello, " + Login.userFirstName
					+ "!\n\nWe are happy to see you trust us and created a card.\nYour card details are the following:\nCard Number: "
					+ Card.cardNumber + "\nPIN: " + Card.pin
					+ "\n\nWe strongly recommand to memorize your PIN\n and delete this email or to change it to one easly for you to memorize.\n\nThanks for using Revolve Bank\nHope for the best!\nRevolveBank Stuff");
			return message;

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
