package ui;

import java.util.Scanner;

import model.Account;
import model.Internship;
import service.AccountManager;
import service.InternshipApplicationManager;
import service.InternshipManager;

public class CareerCenterStaffPage extends UserPage {
	private AccountManager				 accMgr;
	private InternshipManager			 internMgr;
	private InternshipApplicationManager appMgr;
	private Scanner						 sc;

	public CareerCenterStaffPage(AccountManager accMgr, InternshipManager internMgr, InternshipApplicationManager appMgr, Scanner sc) {
		this.accMgr    = accMgr;
		this.internMgr = internMgr;
		this.appMgr    = appMgr;
		this.sc 	   = sc;
	}

	public void display() {
		System.out.print(
			"----------------------------------------------\n" +
			"|                          			      |\n" +
			"|     Career-Center Staff Internship Hub     |\n" +
			"|                            				  |\n" +
			"----------------------------------------------\n" +
										   				  "\n" +
			"1. View      Company account				  	\n" +
			"2. Accept    Company account				 	\n" +
			"3. Reject    Company account					\n" +
			"4. View 	  Internships						\n" +
			"5. Approve   Internship					    \n" +
			"6. Reject    Internship					    \n" +
			"7. View 	  Withdrawal Requests				\n" +
			"8. Accept    Withdrawal						\n" +
			"9. Reject    Withdrawal						\n" +
			"10. Generate    Report							\n" +
			"11. Logout									   \n"
		);
	}
	/**
	 * View Accounts (pending or approved) done via Account Manager
	 */
	public void viewAccounts() {
		System.out.print("\nAll Accounts");
		accMgr.printAccounts();
	}

	/**
	 * Approve Account via Account Manager
	 */
	public void acceptAccount(String userID) {
		accMgr.approveAccount(userID);
	}
	/**
	 * Reject Account via Account Manager
	 */
	public void rejectAccount(String userID) {
		accMgr.removePending(userID);
	}
	// List all internships (pending, approved, rejected)
	public void viewInternships() {
		internMgr.viewInternships();
	}

	public void approveInternship(int index) {
		internMgr.setInternshipStatus(index, enums.InternshipStatus.Approved);
        System.out.println("Internship " + index + " approved.");
	}

	public void rejectInternship() {
		// TODO - implement CareerCenterStaffPage.rejectInternship
		throw new UnsupportedOperationException();
	}

	public void viewWithdrawals() {
		// TODO - implement CareerCenterStaffPage.viewWithdrawals
		throw new UnsupportedOperationException();
	}

	public void acceptWithdrawal() {
		// TODO - implement CareerCenterStaffPage.acceptWithdrawal
		throw new UnsupportedOperationException();
	}

	public void rejectWithdrawal(int appIndex) {
		// TODO - implement CareerCenterStaffPage.generateReport
		throw new UnsupportedOperationException();
	}

	public void generateReport() {
		// TODO - implement CareerCenterStaffPage.generateReport
		throw new UnsupportedOperationException();
	}

}