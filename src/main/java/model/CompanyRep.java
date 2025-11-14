package model;

import java.util.List;
import java.util.ArrayList;

public class CompanyRep extends Account {

	private String 			 companyName;
	private String 			 department;
	private String 			 position;
	private List<Internship> internships;

	public CompanyRep(String userID, String name, String password, String companyName, String department, String position) {
		super(userID, name, password);
		this.companyName = companyName;
		this.department  = department;
		this.position	 = position;
		this.internships = new ArrayList<>();
	}

	public void viewInternship() {
		// TODO - implement CompanyRep.viewInternship
		if (internships.isEmpty()){
			System.out.println("No internships available");
		}
		else{
			System.out.println("List of internships:");
			for (Internship i: interships){
				System.out.println(i);
			}
		}
		
	}

	public void submitInfo() {
		// TODO - implement CompanyRep.submitInfo
		throw new UnsupportedOperationException();
	}

}