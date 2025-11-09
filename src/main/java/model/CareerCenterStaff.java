package src.main.java.model;

/**
 * Account subtype that has staff privileges
 * 
 * @see Account
 */
public class CareerCenterStaff extends Account {

	private String staffDepartment;

	public CareerCenterStaff(String userID, String name, String department) {
		super(userID, name);
		this.staffDepartment = department;
	}

	public String getDepartment() {
		return this.staffDepartment;
	}

	public void setDepartment(String department) {
		this.staffDepartment = department;
	}

	/**
	 * 
	 * @param c
	 */
	public void authoriseAccount(CompanyRep c) {
		// TODO - implement CareerCenterStaff.authoriseAccount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 */
	public void rejectAccount(CompanyRep c) {
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