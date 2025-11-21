package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Account subtype that has student privieges
 */
public class Student extends Account {
	private int 						yearOfStudy;
	private String 						major;
	private List<InternshipApplication> applications;
	private InternshipApplication 		accepted;

	/**
	 * Constructor used when reading student account details from csv file
	 * @param userID 	the student's unique userID
	 * @param name 		the student's full name 
	 * @param password 	the student's account password
	 * @param year 		the student's year of study (1-4)
	 * @param major 	the student's major 
	 */
	public Student(String userID, String name, String password, int year, String major) {
		super(userID, name, password);
		this.yearOfStudy  = year;
		this.major 		  = major;
		this.applications = new ArrayList<>();
		this.accepted   = null;
	}
	/**
	 * returns the student's year of study 
	 * @return the year of study
	 */
	public int 	  getYearOfStudy()  {return yearOfStudy;}

	/**
	 * returns the student's major
	 * @return major
	 */
	public String getMajor() {return major;}
	public List<InternshipApplication> getApplications() {return applications;}

	public void acceptPlacement(InternshipApplication a) {
		accepted = a;
	}

	public void addApplication(InternshipApplication a) {
		applications.add(a);
	}

	public boolean hasAcceptedPlacement(){
		return (accepted != null);
	}

	public void removeApplication(InternshipApplication a){
		this.applications.remove(a);
	}
}
