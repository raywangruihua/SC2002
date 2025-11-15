package ui;

import java.util.Scanner;
import service.AccountManager;
import forms.Registration;
import model.Account;

/**
 * The start page of the app
 * Filepath for student and staff account csv must be entered before starting the app
 */
public class LoginPage implements UserInterface {
	public final int MAX_OPTION = 3;
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
			"3. Exit			   \n"
		);
	}

	/**
	 * Refer to csv for userID of account
	 * Default password is ""
	 * Company representatives must register for account before login
	 * 
	 * Returns account when successful
	 * Throws error when unsuccessful
	 */
	public Account login() {
		System.out.print("Enter user ID: ");
		String userID = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		switch (accMgr.checkValid(userID, password)) {
			case Valid : return getAccount(userID);
			case IncorrectPassword : {
				throw new IllegalArgumentException("Incorrect password\n");
			}
			case Invalid : {
				throw new IllegalArgumentException("Invalid user ID\n");
			}
			default : {
				throw new IllegalArgumentException("Something went wrong\n");
			}
		}
	}
	
	/*
	 * Returns account based on userID
	 */
	public Account getAccount(String userID){
		if (userID.matches("^U\\\\d{7}[A-Z]$")){
			return accMgr.getAccount(userID);
		}
		else if (userID.contains("@ntu.edu.sg")){
			return accMgr.getAccount(userID);
		}
		else {
			return accMgr.getAccount(userID);
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