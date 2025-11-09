package src.main.java.service;

import java.util.List;
import src.main.java.model.Account;

/**
 * Loads all existing accounts from .csv file
 * Stores all accounts
 * Stores registered company representative accounts pending approval
 * 
 * @see Account
 * @see CareerCenterStaff
 * @see CompanyRep
 * @see Student
 */
public class AccountManager {
	/**
	 * 
	 */
	private List<Account> accounts;
	private List<Account> pending;

	public AccountManager() {
		
	}

	public boolean checkValid(String userID, String password) {
		// TODO - implement AccountManager.checkValid
		for (Account acc: accounts){
			// correct userID and password
			if ((acc.getUserID().equals(userID)) && (acc.getPassword().equals(password))){
				return true;
			}
			// correct userID but incorrect password
			if ((acc.getUserID().equals(userID)) && !(acc.getPassword().equals(password))){
				return false; 
			}
		}
		// userID doesnt exist 
		return false;
	}

	/**
	 * 
	 * @param acc
	 */
	public boolean checkExists(Account acc) {
		// TODO - implement AccountManager.checkExists
		for (Account acc: accounts){
			// acc exists 
			if (acc.getUserID().equals(userID)){
				return true;
			}
		}
		// acc doesnt exist 
		return false; 
	}

	/**
	 * 
	 * @param userID
	 * @param newPassword
	 */
	public void updatePassword(String userID, String newPassword) {
		// TODO - implement AccountManager.updatePassword
		for (Account acc: accounts){
			if (acc.getUserID().equals(userID)){
				acc.setPassword(newPassword);
			}
		}
		System.out.println("Account doesn't exist!");
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