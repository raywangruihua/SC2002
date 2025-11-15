package ui;

import model.InternshipApplication;
import service.InternshipApplicationManager;
import service.InternshipManager;
import java.util.Scanner;

import forms.InternshipCreation;

public class CompanyRepPage extends UserPage {
	private InternshipManager 			 internMgr;
	private InternshipApplicationManager appMgr;
	private Scanner 					 sc;

	public CompanyRepPage(InternshipManager internMgr, InternshipApplicationManager appMgr, Scanner sc) {
		this.internMgr = internMgr;
		this.appMgr    = appMgr;
		this.sc 	   = sc;
	}

	public void display() {
		System.out.print(
			"----------------------------------------------\n" +
			"|                          			      |\n" +
			"|   Company Representative Internship Hub    |\n" +
			"|                            				  |\n" +
			"----------------------------------------------\n" +
										   				  "\n" +
			"1. Create  Internship						   \n" +
			"2. Toggle  Internship						   \n" +
			"2. View    Applications					   \n" +
			"3. Approve Application						   \n" +
			"4. Reject  Application						   \n" +
			"3. Logout									   \n"
		);
	}

	/**
	 * Submit internship creation form
	 * Add new internship to pending list in repo for Career Center Staff approval
	 * Prevents new internships from being created if current company already has 5
	 * 
	 * @see InternshipCreation
	 * @see Repository
	 * @see CareerCenterStaff
	 * 
	 * @param numInternships
	 */
	public void createInternship(int numInternships) {
		InternshipCreation form = new InternshipCreation(sc);
		internMgr.createInternship(form.submit());
	}

	public void checkInternshipApproval() {
		// TODO - implement CompanyRepPage.checkInternshipApproval
		throw new UnsupportedOperationException();
	}

	public void toggleInternshipVisibility() {
		// TODO - implement CompanyRepPage.toggleInternshipVisibility
		throw new UnsupportedOperationException();
	}

	public void viewInternshipApplications() {
		// TODO - implement CompanyRepPage.viewInternshipApplications
		throw new UnsupportedOperationException();
	}

	public void approveInternshipApplication() {
		// TODO - implement CompanyRepPage.approveInternshipApplication
		throw new UnsupportedOperationException();
	}

	public void rejectInternshipApplication() {
		// TODO - implement CompanyRepPage.rejectInternshipApplication
		throw new UnsupportedOperationException();
	}

}