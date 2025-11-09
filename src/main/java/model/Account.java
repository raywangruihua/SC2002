package src.main.java.model;

/**
 * Account abstract class implements basic account features inherited by subtypes
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
	 * Initial account csv files do not provide a password, so account information is incomplete
	 * Password can be changed after default account creation for students and staff
	 * 
	 * @param password Default password is "password"
	 */
	public Account(String userID, String name) {
		this.userID   = userID;
		this.name 	  = name;
		this.password = "password";
	}

	public String getUserID() {
		return this.userID;
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.password; 
	}

	public void setPassword(String newPassword) {
		this.password = newPassword; 
	}
}