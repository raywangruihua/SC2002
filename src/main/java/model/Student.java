package src.main.java.model;

import java.util.List;
import java.util.ArrayList;
import src.main.java.enums.ApplicationStatus;
import src.main.java.ui.StudentPage;

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
	 * 
	 * @param i
	 */
	public void applyInternship(int index) {
		// TODO - implement Student.applyInternship 
		if (applications.size() > 3){
			System.out.println("Application Limit Exceeded");
		}
		else {
			StudentPage ui = new StudentPage();
			InternshipApplication application = ui.applyInternship(index, yearOfStudy, super.getName());

			// add application to pending
			applications.add(application);
		}
		throw new UnsupportedOperationException();
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
	 * 
	 * @param a
	 */
	public void viewApplications() {
		// TODO - implement Student.viewApplications
		StudentPage ui = new StudentPage();
		ui.viewApplications(applications);
		throw new UnsupportedOperationException();
	}

	public void viewInternships(){
		StudentPage ui = new StudentPage();
		ui.viewInternships(yearOfStudy, major);
	}
}
