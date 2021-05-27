package email;

import app.Login;
import graphicInterface.ForgotPasswordInterface;
import graphicInterface.RegisterInterface;

public class EmailThread extends Thread {
	public void run() {
		try {
			switch (Login.index) {
			case 1:
				Email.sendMail(RegisterInterface.emailIn.getText());
				break;
			case 2:
				Email.sendMail(ForgotPasswordInterface.emailIn.getText());
				break;
			case 3:
				Email.sendMail(ForgotPasswordInterface.emailIn.getText());
				break;
			case 4:
				Email.sendMail(Login.email);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
