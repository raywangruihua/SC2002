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
	private Internship 					internship;

	/**
	 * Constructor used when reading student account details from csv file
	 */
	public Student(String userID, String name, String password, int year, String major) {
		super(userID, name, password);
		this.yearOfStudy = year;
		this.major 		 = major;
		this.applications = new ArrayList<>();
		this.internship = new Internship();
	}

	/**
	 * 
	 * @param a
	 */
	public void acceptPlacement(InternshipApplication a) {
		// check

		if (a.getStatus() == ApplicationStatus.Successful) {

		}
	}

	/**
	 * Student applies for internship through User Interface and adds to list of pending applications
	 */
	public void applyInternship(int index, Repository repo) {
		if (applications.size() > 3){
			System.out.println("Application Limit Exceeded");
		}
		else {
			StudentPage ui = new StudentPage();
			InternshipApplication application = ui.applyInternship(index, yearOfStudy, getUserID(), getName(), repo);

			// add application to pending
			applications.add(application);
		}
	}

	/**
	 * 
	 * @param a
	 */
	public void withdrawApplication(InternshipApplication a) {
		// TODO - implement Student.withdrawApplication
		throw new UnsupportedOperationException();
	}

	/**
	 * Student views applications applied through User Interface
	 */
	public void viewApplications(Repository repo) {
		// TODO - implement Student.viewApplications
		StudentPage ui = new StudentPage();
		ui.viewApplications(applications, repo);
	}
	
	/*
	 * Student views internships available for application through User Interface
	 */
	public void viewInternships(Repository repo){
		StudentPage ui = new StudentPage();
		ui.viewInternships(yearOfStudy, major, repo);
	}
}

