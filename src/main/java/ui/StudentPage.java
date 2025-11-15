package ui;
import java.util.*;

import enums.InternshipLevel;
import model.Internship;
import model.InternshipApplication;
import repository.Repository;
import service.AccountManager;
import service.InternshipApplicationManager;
import service.InternshipManager;

public class StudentPage extends UserPage {
	private InternshipManager 			 internMgr;
	private InternshipApplicationManager appMgr;
	private Scanner		   				 sc;

	public StudentPage(InternshipManager internMgr, InternshipApplicationManager appMgr, Scanner sc) {
		this.internMgr = internMgr;
		this.appMgr	   = appMgr;
		this.sc		   = sc;
	}

	@Override
	public void display() {
		System.out.print(
			"-------------------------------\n" +
			"|                             |\n" +
			"|   Student Internship Hub    |\n" +
			"|                             |\n" +
			"-------------------------------\n" +
										   "\n" +
			"1. View Internships		    \n" +
			"2. Apply Internships		    \n" +
			"3. View Applications	        \n" +
			"4. Accept Placement		    \n" +
			"5. Withdraw Applications	    \n" +
			"6. Logout		           	    \n" +
										   "\n"
		);
	}
	
	/* 
	 * Displays internship information by index, title, internship level, company name and description
	 */
	public void display(List<Internship> display_list) {
		for (Internship internship : display_list){
			System.out.println("Index: " + internship.getIndex() + ", Internship Title: " + internship.getTitle() + ", Internship Level: " + internship.getInternshipLevel() + ", Company Name: " + internship.getCompanyName());
			System.out.println("Description: " + internship.getDescription());
			System.out.println();
		}
	}

	/**
	 * Retrieves all internships student can apply for through internshipmanager
	 */
	public void viewInternships(int yearOfStudy, String major, Repository repo) {
		InternshipManager im = new InternshipManager();
		List<Internship> display_list = im.getInternships(yearOfStudy, major, repo);
		display(display_list);
	}

	/**
	 * Creates internship application for student given student is applicable 
	 */
	public InternshipApplication applyInternship(int index, int yearOfStudy, String id, String name, Repository repo) {
		InternshipManager im = new InternshipManager();
		
		if ((im.getInternshipLevel(index, repo) != InternshipLevel.Basic) && ((yearOfStudy == 1) || (yearOfStudy == 2))){
			throw new IllegalArgumentException("Application Unaccepted");
		}
		else {
			// create new application
			InternshipApplication application = im.applyInternship(index, id, name, repo);
			// add application to studentpending
			return application;
		}
	}

	/**
	 * Displays all student applications and statuses given student has applied 
	 */
	public void viewApplications(List<InternshipApplication> applications, Repository repo) {
		if (applications.size() == 0){
			System.out.println("No Internship Applications");
		}
		else {
			InternshipApplicationManager iam = new InternshipApplicationManager();
			for (InternshipApplication application: applications){
				System.out.println("All Internship Applications");
				System.out.println("Internship Title: " + application.getInternshipTitle() + ", Application Status: " + iam.getApplicationStatus(application, repo));
			}
		}
		System.out.println();
	}

	public void acceptInternship() {
		// TODO - implement StudentPage.acceptInternship
		throw new UnsupportedOperationException();
	}

	public void withdrawApplication() {
		// TODO - implement StudentPage.withdrawApplication
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param internship
	 */
	public void viewInternship(Internship internship) {
		// TODO - implement StudentPage.viewInternship
		throw new UnsupportedOperationException();
	}

}