package src.main.java.service;

import java.util.List;
import java.util.ArrayList;
import src.main.java.model.Account;
import src.main.java.util.Read;
import src.main.java.enums.LoginStatus;

/**
 * Loads all existing accounts from csv file and stores them in a list
 * Stores registered company representative accounts pending approval
 * Each account is identified by their unique user ID
 * 
 * @see Account
 * @see CareerCenterStaff
 * @see CompanyRep
 * @see Student
 */
public class AccountManager {
	private List<Account> accounts;
	private List<Account> pending;

	/**
	 * Load all pre-exisiting accounts into memory
	 * Accounts without password are assigned default passwords
	 * 
	 * @param studentsFilepath Filepath of student accounts csv
	 * @param staffsFilepath Filepath of staff accounts csv
	 */
	public AccountManager(String studentsFilepath, String staffsFilepath) {
		accounts = new ArrayList<>();
		accounts.addAll(Read.readStudentAccountsCSV(studentsFilepath));
		accounts.addAll(Read.readStaffAccountsCSV(staffsFilepath));

		pending = new ArrayList<>();
	}

	/**
	 * Check if login information is valid
	 * 
	 * @see LoginStatus
	 * 
	 * @return Validity of login information
	 * 
	 */
	public LoginStatus checkValid(String userID, String password) {
		for (Account acc: accounts){
			// correct userID and password
			if ((acc.getUserID().equals(userID)) && (acc.getPassword().equals(password))){
				return LoginStatus.Valid;
			}
			// correct userID but incorrect password
			if ((acc.getUserID().equals(userID)) && !(acc.getPassword().equals(password))){
				return LoginStatus.IncorrectPassword; 
			}
		}
		// userID doesnt exist 
		return LoginStatus.Invalid;
	}

	/**
	 * Add account to pending list
	 */
	public void register(Account acc) {
		pending.add(acc);
	}

	/**
	 * Check if account already exists by searching for user ID
	 * 
	 * @return Whether account exists
	 */
	public boolean checkExists(Account acc) {
		for (Account existingAccount : accounts){
			// acc exists 
			if (acc.getUserID().equals(existingAccount.getUserID())){
				return true;
			}
		}
		// acc doesn't exist 
		return false; 
	}

	/**
	 * @return Whether user ID is taken
	 */
	public boolean checkExists(String userID) {
		for (Account acc : accounts){
			// user ID exists
			if (acc.getUserID().equals(userID)){
				return true;
			}
		}
		// user ID doesn't exist 
		return false; 
	}

	/**
	 * @return Whether account is pending approval
	 */
	public boolean checkPending(String userID) {
		for (Account acc : pending){
			// account pending
			if (acc.getUserID().equals(userID)){
				return true;
			}
		}
		// account not pending 
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
	 * Move company representative account from pending list to account list
	 * 
	 * @param userID 
	 */
	public void approveAccount(String userID) {
		
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