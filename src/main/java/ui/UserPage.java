package src.main.java.ui;

/** 
 * Base class for user pages subtypes
 * Implements basic user functions
 */
public abstract class UserPage implements UserInterface {

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