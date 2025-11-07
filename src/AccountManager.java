package src;
public class AccountManager {

	private List<Account> accounts;
	private List<Account> pending;

	/**
	 * 
	 * @param userID
	 * @param password
	 */
	public boolean checkValid(String userID, String password) {
		// TODO - implement AccountManager.checkValid
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param acc
	 */
	public boolean checkExists(Account acc) {
		// TODO - implement AccountManager.checkExists
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param newPassword
	 */
	public void updatePassword(String userID, String newPassword) {
		// TODO - implement AccountManager.updatePassword
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param acc
	 */
	public void addPending(Account acc) {
		// TODO - implement AccountManager.addPending
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 */
	public void approveAccount(String userID) {
		// TODO - implement AccountManager.approveAccount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 */
	public void removePending(String userID) {
		// TODO - implement AccountManager.removePending
		throw new UnsupportedOperationException();
	}

}