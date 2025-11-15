package ui;

import model.InternshipApplication;
import service.InternshipApplicationManager;
import service.InternshipManager;
import java.util.Scanner;

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
		// TODO - implement CompanyRepPage.display
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numInternships
	 */
	public void createInternship(int numInternships) {
		// TODO - implement CompanyRepPage.createInternship
		throw new UnsupportedOperationException();
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