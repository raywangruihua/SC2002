package model;

/**
 * Base class for account subtypes
 * Implements basic account features inherited by subtypes
 * 
 * @see Student
 * @see CareerCenterStaff
 * @see CompanyRep
*/
public abstract class Account {

	/**
	 * @param userID   Fixed at account creation
	 * @param name     Name of account holder should not change
	 * @param password Password can be changed when required
	 */
	private final String userID;
	private final String name;
	private 	  String password;

	/**
	 * Initial account csv files do not provide a password
	 * Password can be changed after default account creation for students and staff
	 * @param password Default password is ""
	 */
	public Account(String userID, String name, String password) {
		this.userID   = userID;
		this.name 	  = name;
		this.password = password;
	}

	/**
	 * @return userID
	 */
	public String getUserID() {
		return this.userID;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return this.password; 
	}
	
	/**
	 * Change user's password
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword; 
	}
}