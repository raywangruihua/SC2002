package src.model;

/**
 * Account abstract class implements basic account features inherited by sub types
*/
public abstract class Account {

	private String userID;
	private String name;
	private String password;

	/**
	 * Constructor for Account
	 * @param userID User ID
	 * @param name Account holder name
	 * @param password Password
	 */
	public Account(String userID, String name, String password) {
		this.userID = userID; 
		this.name = name; 
		this.password = password; 
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