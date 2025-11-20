package model;

/**
 * Account subtype with company rep privileges
 * 
 * @see Company
 */
public class CompanyRep extends Account {
	private String department;
	private String position;
	private String companyName;

	public CompanyRep(String userID, String name, String password, String companyName, String department, String position) {
		super(userID, name, password);
		this.companyName = companyName;
		this.department  = department;
		this.position	 = position;
	}

	public String getCompanyName() {return companyName;}
	public String getDepartment()  {return department;}
	public String getPosition()	   {return position;}

	@Override
	public String toString() {
		return "UserID: " + getUserID() + " Name: " + getName() + " Company: " + getCompanyName() + " Department: " + getDepartment() + " Positon: " + getPosition();
	}
	
}