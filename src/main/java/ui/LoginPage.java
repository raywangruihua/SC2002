package ui;

import java.util.Scanner;
import service.AccountManager;
import forms.Registration;
import model.Account;
import repository.Repository;

/**
 * The start page of the app
 * Filepath for student and staff account csv must be entered before starting the app
 */
public class LoginPage {
	public final int MAX_OPTION = 3;

	private AccountManager accMgr;
	private Scanner 	   sc;

	public LoginPage(AccountManager accMgr, Scanner sc) {
		this.accMgr = accMgr;
		this.sc 	= sc;
	}

	public Account display() {	
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
		int option = -1;
        outer: while (true) {
            try {
                System.out.print("\nEnter option: ");
                option = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {}

            switch (option) {
                case 1  -> {
                    try {
                        Account acc = login();
						return acc;
                    }
                    catch (Exception e) {
                        System.out.print(e.getMessage());
                    }
                }
                case 2  -> register();
                case 3  -> System.exit(0);
                default -> System.out.println("Please enter a valid option (1-" + MAX_OPTION + ")");
            }
		}

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
			case Valid : return accMgr.getAccount(userID);
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