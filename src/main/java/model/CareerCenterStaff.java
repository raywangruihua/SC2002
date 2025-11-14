package model;

/**
 * Account subtype that has staff privileges
 * 
 * @see Account
 */
public class CareerCenterStaff extends Account {

	private String staffDepartment;

	public CareerCenterStaff(String userID, String name, String password, String department) {
		super(userID, name, password);
		this.staffDepartment = department;
	}

	public String getDepartment() {
		return this.staffDepartment;
	}

	public void setDepartment(String department) {
		this.staffDepartment = department;
	}

	/**
	 * Interface -> Account -> Manager
	 * Add account from pending list to accounts list 
	 * @param rep
	 */
	public void approveAccount(CompanyRep rep) {
		// TODO - implement CareerCenterStaff.authoriseAccount
		
		
	}

	/**
	 * Remove account from pending list
	 * @param c
	 */
	public void rejectAccount(CompanyRep rep) {
		// TODO - implement CareerCenterStaff.rejectAccount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param i
	 */
	public void approveInternship(Internship i) {
		// TODO - implement CareerCenterStaff.approveInternship
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param i
	 */
	public void rejectInternship(Internship i) {
		// TODO - implement CareerCenterStaff.rejectInternship
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param s
	 * @param i
	 */
	public void approveWithdrawal(Student s, Internship i) {
		// TODO - implement CareerCenterStaff.approveWithdrawal
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param s
	 * @param i
	 */
	public void rejectWithdrawal(Student s, Internship i) {
		// TODO - implement CareerCenterStaff.rejectWithdrawal
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param s
	 */
	public void generateReport(Student s) {
		// TODO - implement CareerCenterStaff.generateReport
		throw new UnsupportedOperationException();
	}

}