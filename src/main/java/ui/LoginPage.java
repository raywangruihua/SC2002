package src.main.java.ui;

import java.util.Scanner;
import src.main.java.service.AccountManager;
import src.main.java.forms.Registration;

/**
 * The start page of the app
 * Filepath for student and staff account csv must be entered before starting the app
 */
public class LoginPage implements UserInterface {
	private AccountManager accMgr;
	private Scanner 	   sc;

	public LoginPage(AccountManager accMgr, Scanner sc) {
		this.accMgr = accMgr;
		this.sc 	= sc;
	}

	public void display() {	
		System.out.print(
			"----------------------\n" +
			"|                    |\n" +
			"|   Internship Hub   |\n" +
			"|                    |\n" +
			"----------------------\n" +
			                      "\n" +
			"1. Login			   \n" +
			"2. Register		   \n" +
			"3. Exit			   \n" +
			                      "\n"
		);
	}

	/**
	 * Refer to csv for userID of account
	 * Default password is ""
	 * Company representatives must register for account before login
	 * 
	 * @return Login success/failure
	 */
	public boolean login() {
		System.out.print("Enter user ID: ");
		String userID = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		switch (accMgr.checkValid(userID, password)) {
			case Valid : return true;
			case IncorrectPassword : {
				System.out.print("Incorrect password\n");
				return false;
			}
			case Invalid : {
				System.out.print("Invalid user ID\n");
				return false;
			}
			default : {
				System.out.print("Something went wrong\n");
				return false;
			}
		}
	}

	/**
	 * Only company representatives must register
	 * Fill in register form, then submit account for approval
	 * 
	 * @see Registration
	 */
	public void register() {
		Registration form = new Registration(accMgr, sc);
		accMgr.register(form.submit());
		System.out.print("Account submitted for approval\n");
	}
}