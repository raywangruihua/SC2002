package ui;
import java.util.*;

import enums.InternshipLevel;
import model.Internship;
import model.InternshipApplication;
import service.InternshipApplicationManager;
import service.InternshipManager;

public class StudentPage extends UserPage {

	@Override
	public void display() {

	}
	/* 
	 * displays internship information by index, title, internship level, company name and description
	 */
	public void display(List<Internship> display_list) {
		for (Internship internship : display_list){
			System.out.println("Index: " + internship.getIndex() + "Internship Title: " + internship.getTitle() + ", Internship Level: " + internship.getInternshipLevel() + ", Company Name: " + internship.getCompanyName());
			System.out.println("Description: " + internship.getDescription());
			System.out.println();
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * retrieves all internships student can apply for through internshipmanager
	 */
	public void viewInternships(int yearOfStudy, String major) {
		InternshipManager im = new InternshipManager();
		List<Internship> display_list = im.getInternships(yearOfStudy, major);
		display(display_list);
	}

	/**
	 * creates internship application for student given student is applicable 
	 */
	public InternshipApplication applyInternship(int index, int yearOfStudy, String name) {
		InternshipManager im = new InternshipManager();
		
		if ((im.getInternshipLevel(index) != InternshipLevel.Basic) && ((yearOfStudy == 1) | (yearOfStudy == 2))){
			System.out.println("Application Unaccepted");
		}
		else {
			// create new application
			InternshipApplication application = im.applyInternship(index, name);
			// add application to studentpending
			return application;
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * displays all student applications and statuses given student has applied 
	 */
	public void viewApplications(List<InternshipApplication> applications) {
		if (applications.size() == 0){
			System.out.println("No Internship Applications");
		}
		else {
			InternshipApplicationManager am = new InternshipApplicationManager();
			for (InternshipApplication application: applications){
				System.out.println("All Internship Applications");
				System.out.println("Internship Title: " + application.getInternshipTitle() + ", Application Status: " + am.getApplicationStatus(application));
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