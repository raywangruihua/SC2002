package service;
import util.CSVHandler;
import java.util.List;
import java.util.ArrayList;
import model.Account;
import model.CareerCenterStaff;
import model.CompanyRep;
import model.Student;
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
	private String studentPath, staffPath, repPath;
	private List<Account> accounts;
	private List<Account> pending;

	/**
	 * Load all pre-exisiting accounts into memory
	 * Accounts without password are assigned default passwords
	 * 
	 * @param studentsFilepath Filepath of student accounts csv
	 * @param staffsFilepath Filepath of staff accounts csv
	 */
	public AccountManager(String studentsFilepath, String staffsFilepath, String companyRepsFilepath) {
		this.studentPath = studentsFilepath;
		this.staffPath = staffsFilepath;
		this.repPath = companyRepsFilepath;
		
		accounts = new ArrayList<>();
		CSVHandler csvHandler = new CSVHandler();
		accounts.addAll(csvHandler.readStudents(studentsFilepath));
        accounts.addAll(csvHandler.readStaffs(staffsFilepath));       
        accounts.addAll(csvHandler.readCompanyReps(companyRepsFilepath));

		pending = new ArrayList<>();
	}

	/**
	 * Get all pending accounts
	 * @return
	 */
	public List<Account> getPendingAccounts() {
		return pending;
	}

	/**
	 * Get pending Company Representative account
	 */
	public CompanyRep getPendingAccount(String userID) {
		for (Account acc : pending) {
			if (acc.getUserID().equals(userID) && acc instanceof CompanyRep) return (CompanyRep) acc;
		}
		return null;
	}

	public void printAccounts() {
		for (Account acc : accounts) {
			System.out.println(acc);
		}
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
	    if (checkPending(userID)) {
	        System.out.println("LOGIN FAILED: Your account is pending approval from Career Center Staff.");
	        util.AuditLogger.log("LOGIN_ATTEMPT_PENDING", userID, "User tried to log in while pending");
	        return LoginStatus.Invalid;
	    }
	    // ---------------------------------

	    for (Account acc : accounts) {
	        if (acc.getUserID().equals(userID)) {
	            if (acc.isLocked()) {
	                 System.out.println("Account is locked due to too many failed attempts.");
	                 return LoginStatus.Invalid; 
	            }
	            if (acc.getPassword().equals(password)) {
	                acc.resetFailedAttempts();
	                return LoginStatus.Valid;
	            } else {
	                acc.incrementFailedAttempts();
	                return LoginStatus.IncorrectPassword;
	            }
	        }
	    }
	    util.AuditLogger.log("LOGIN_UNKNOWN", userID, "User ID not found");
	    return LoginStatus.Invalid;
	}

	/**
	 * Add account to pending list if it isn't a duplicate
	 * @param acc
	 */
	public void register(Account acc) {
		if (checkPending(acc.getUserID())) {
			return;
		}
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
	public void updatePassword(String userID, String newPassword) throws Exception {
	    boolean found = false;
	    for (Account acc : accounts) {
	        if (acc.getUserID().equals(userID)) {
	            acc.setPassword(newPassword);
	            found = true;
	            break;
	        }
	    }

	    if (found) {
	        saveAllAccounts(); // <--- THIS IS THE CRITICAL MISSING LINE
	        util.AuditLogger.log("PASSWORD_CHANGE", userID, "Password updated successfully");
	        System.out.println("Password changed and saved to file.");
	    } else {
	        throw new Exception("Password could not be changed: User not found.");
	    }
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
			saveAllAccounts();
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
	public Account getAccount(String userID) throws IllegalArgumentException {
		for (Account account: accounts) {
			if (account.getUserID().equals(userID)) {
				return account;
			}
		}
		throw new IllegalArgumentException("Account does not exist");
	}
	
	private void saveAllAccounts() {
	    List<Student> students = new ArrayList<>();
	    List<CareerCenterStaff> staffs = new ArrayList<>();
	    List<CompanyRep> reps = new ArrayList<>();

	    // Separate the single list back into types
	    for (Account acc : accounts) {
	        if (acc instanceof Student) students.add((Student) acc);
	        else if (acc instanceof CareerCenterStaff) staffs.add((CareerCenterStaff) acc);
	        else if (acc instanceof CompanyRep) reps.add((CompanyRep) acc);
	    }

	    CSVHandler csvHandler = new CSVHandler();
	    csvHandler.writeStudents(studentPath, students);
	    csvHandler.writeStaffs(staffPath, staffs);
	    csvHandler.writeCompanyReps(repPath, reps);
	}

}