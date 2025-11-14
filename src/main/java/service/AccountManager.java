package service;

import java.util.List;
import java.util.ArrayList;
import model.Account;
import model.CareerCenterStaff;
import model.CompanyRep;
import model.Student;
import util.Read;
import enums.LoginStatus;

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
	 * @param acc
	 */
	public void register(Account acc) {
		pending.add(acc);
	}

	/**
	 * Check if account already exists by searching for user ID
	 * @param acc
	 * @return Whether account exists
	 */
	public boolean checkExists(Account acc) {
		for (Account existingAccount : accounts){
			if (acc.getUserID().equals(existingAccount.getUserID())){
				return true;
			}
		}
		return false; 
	}

	/**
	 * @param userID
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
	 * @param userID
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
	 * @param userID 
	 */
	public void approveAccount(String userID) {
		Account toApprove = null; 
		for (Account acc: pending){
			if (acc.getUserID().equals(userID)){
				toApprove = acc; 
				break; 
			}
		}
		if (toApprove != null){
			pending.remove(toApprove);
			accounts.add(toApprove);
			System.out.println("Account " + userID + " is approved.");
		} else {
			System.out.println("No pending account found with userID " + userID + ".");
		}
	}

	/**
	 * Remove company representative account from pending list
	 * @param userID
	 */
	public void removePending(String userID) {
		// TODO - implement AccountManager.removePending
		Account toReject = null; 
		for (Account acc: pending){
			if (acc.getUserID().equals(userID)){
				toReject = acc; 
				break; 
			}
		}
		if (toReject != null){
			pending.remove(toReject);
			System.out.println("Account " + userID + " is rejected.");
		} else {
			System.out.println("No pending account found with userID " + userID +".");
		}
	}
	/**
	 * Returns account based on UserID
	 */
	public Account getAccount(String userID){
		for (Account account: accounts){
			if (account.getUserID() == userID){
				return account;
			}
		}
		throw new IllegalArgumentException("Account does not exist");
	}

}