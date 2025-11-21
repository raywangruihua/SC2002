package model;

/**
 * @see Student
 * @see CareerCenterStaff
 * @see CompanyRep
*/
public abstract class Account {
	private final String userID;
	private final String name;
	private 	  String password;
	private       int    failedLoginAttempts = 0;
    private static final int MAX_ATTEMPTS = 3;

	/**
	 * Default constructor
	 * @param userID
	 * @param name
	 * @param password
	 */
	public Account(String userID, String name, String password) {
		this.userID   = userID;
		this.name 	  = name;
		this.password = password;
	}

	public String getUserID() 					  {return this.userID;}
	public String getName() 					  {return this.name;}
	public String getPassword() 				  {return this.password;}
	public void   setPassword(String newPassword) {this.password = newPassword;}

	public void   incrementFailedAttempts() 	  {this.failedLoginAttempts++;}

	/**
	 * Resets the failure counter to 0 (used on successful login).
	 */
    public void resetFailedAttempts() {this.failedLoginAttempts = 0;}

    /**
     * Checks if the user is locked out.
     * @return true if attempts >= 3
     */
    public boolean isLocked() {return this.failedLoginAttempts >= MAX_ATTEMPTS;}
    
    /**
     * Getter for the number of failed attempts (optional, for display).
	 * @return
     */
    public int getFailedAttempts() {return failedLoginAttempts;}

	@Override
	public String toString() {
		return "UserID: " + getUserID() + ", Name: " + getName();
	}
}