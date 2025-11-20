package model;

/**
 * @see Student
 * @see CareerCenterStaff
 * @see CompanyRep
*/
public abstract class Account {

	/**
	 * @param userID   
	 * @param name     
	 * @param password 
	 */
	private final String userID;
	private final String name;
	private 	  String password;
	private       int    failedLoginAttempts = 0;
    private static final int MAX_ATTEMPTS = 3;

	/**
	 * @param password Default password is "password"
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
	
	/**
     * Increases the failure counter by 1.
     */
	public void incrementFailedAttempts() {
        this.failedLoginAttempts++;
    }

    /**
     * Resets the failure counter to 0 (used on successful login).
     */
    public void resetFailedAttempts() {
        this.failedLoginAttempts = 0;
    }

    /**
     * Checks if the user is locked out.
     * @return true if attempts >= 3
     */
    public boolean isLocked() {
        return this.failedLoginAttempts >= MAX_ATTEMPTS;
    }
    
    /**
     * Getter for the number of failed attempts (optional, for display).
     */
    public int getFailedAttempts() {
        return failedLoginAttempts;
    }

	@Override
	public String toString() {
		return "UserID: " + getUserID() + ", Name: " + getName();
	}
}