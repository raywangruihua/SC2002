package model;

/**
 * Account subtype that has staff privileges
 */
public class CareerCenterStaff extends Account {
	private String staffDepartment;

	public CareerCenterStaff(String userID, String name, String password, String department) {
		super(userID, name, password);
		this.staffDepartment = department;
	}

	public String getDepartment() {return this.staffDepartment;}

	public void setDepartment(String department) {this.staffDepartment = department;}
}