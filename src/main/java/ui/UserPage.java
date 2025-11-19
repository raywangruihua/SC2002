package ui;
import model.Account;

/** 
 * Base class for user pages subtypes
 * Implements basic user functions
 */
public abstract class UserPage implements UserInterface<Account> {

	public abstract void display();

	public void logout() {
		// TODO - implement UserPage.logout
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param newPassword
	 */
	public void changePassword(String newPassword) {
		// TODO - implement UserPage.changePassword
		throw new UnsupportedOperationException();
	}

}