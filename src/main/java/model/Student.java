package model;

import java.util.List;
import java.util.ArrayList;
import enums.ApplicationStatus;
import repository.Repository;
import ui.StudentPage;

/**
 * Account subtype that has student privieges
 * 
 * @see Account
 */
public class Student extends Account {
	private int 						yearOfStudy;
	private String 						major;
	private List<InternshipApplication> applications;
	private InternshipApplication 		accepted;

	/**
	 * Constructor used when reading student account details from csv file
	 */
	public Student(String userID, String name, String password, int year, String major) {
		super(userID, name, password);
		this.yearOfStudy  = year;
		this.major 		  = major;
		this.applications = new ArrayList<>();
		this.accepted   = null;
	}

	public int 	  getYear()  {return yearOfStudy;}
	public String getMajor() {return major;}
	public List<InternshipApplication> getApplications() {return applications;}

	/**
	 * 
	 * @param a
	 */
	public void acceptPlacement(InternshipApplication a) {
		accepted = a;
	}

	/**
	 * Add application to list of applications 
	 */
	public void addApplication(InternshipApplication a) {
		applications.add(a);
	}
}
