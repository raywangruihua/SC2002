package ui;

import java.util.Scanner;
import model.Internship;
import model.InternshipApplication;
import service.CompanyManager;
import service.InternshipApplicationManager;
import service.InternshipManager;
import forms.InternshipCreation;

public class CompanyRepPage extends UserPage {
	public final int MAX_OPTION = 7;

	private InternshipManager 			 internMgr;
	private InternshipApplicationManager appMgr;
	private CompanyManager				 coMgr;
	private Scanner 					 sc;

	public CompanyRepPage(InternshipManager internMgr, InternshipApplicationManager appMgr, CompanyManager coMgr, Scanner sc) {
		this.internMgr = internMgr;
		this.appMgr    = appMgr;
		this.coMgr	   = coMgr;
		this.sc 	   = sc;
	}

	public void display() {
		System.out.print(
			"----------------------------------------------\n" +
			"|                                            |\n" +
			"|   Company Representative Internship Hub    |\n" +
			"|                                            |\n" +
			"----------------------------------------------\n" +
			                                              "\n" +
			"1. Create  Internship                         \n" +
			"2. View    Internships                        \n" +
			"3. Toggle  Internship                         \n" +
			"4. View    Applications                       \n" +
			"5. Approve Application                        \n" +
			"6. Reject  Application                        \n" +
			"7. Logout                                     \n"
		);
	}

	/**
	 * Submit internship creation form
	 * Add new internship to pending list in repo for Career Center Staff approval
	 * Prevents new internships from being created if current company already has 5 (pending or approved)
	 * 
	 * @see InternshipCreation
	 * @see Repository
	 * @see CareerCenterStaff
	 * 
	 * @param numInternships
	 */
	public void createInternship(String companyName) {
		if (coMgr.getNumInternships(companyName) >= 5) System.out.println("\nNumber of internships exceeded.");

		InternshipCreation form = new InternshipCreation(sc);
		internMgr.createInternship(form.submit());
		coMgr.incrementInternships(companyName);
		System.out.println("Internship submitted for approval.");
	}

	/**
	 * View internships (pending or approved)
	 */
	public void viewInternships(String companyName) {
		System.out.print("\nApproved Internships");
		for (Internship i : internMgr.getApprovedInternships(companyName)) System.out.println(i);

		System.out.print("\nPending Internships");
		for (Internship i : internMgr.getPendingInternships(companyName)) System.out.println(i);
	}

	/**
	 * Toggle approved internship visibility by index
	 */
	public void toggleInternship(int index) throws NullPointerException {	
		internMgr.getInternship(index).toggleVisibility();
	}

	/**
	 * View applications for internship chosen
	 */
	public void viewApplications(int internshipIndex) {
		for (InternshipApplication a : appMgr.getApplications(internshipIndex)) {
			System.out.println(a);
		}
	}

	/**
	 * Approve application via InternshipApplication Manager
	 */
	public void approveApplication(int applicationIndex) {appMgr.approveApplication(applicationIndex);}

	/**
	 * Reject application via InternshipApplication Manager
	 */
	public void rejectApplication(int applicationIndex) {appMgr.rejectApplication(applicationIndex);}
}