package ui;

import java.util.Scanner;

import model.Account;
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
		// TODO - implement CareerCenterStaffPage.display
		throw new UnsupportedOperationException();
	}

	public void viewAccounts() {
		// TODO - implement CareerCenterStaffPage.viewAccounts
		throw new UnsupportedOperationException();
	}

	public void acceptAccount() {
		// TODO - implement CareerCenterStaffPage.acceptAccount
		throw new UnsupportedOperationException();
	}

	public void rejectAccount() {
		// TODO - implement CareerCenterStaffPage.rejectAccount
		throw new UnsupportedOperationException();
	}

	public void viewInternships() {
		// TODO - implement CareerCenterStaffPage.viewInternships
		throw new UnsupportedOperationException();
	}

	public void approveInternship() {
		// TODO - implement CareerCenterStaffPage.approveInternship
		throw new UnsupportedOperationException();
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

	public void rejectWithdrawal() {
		// TODO - implement CareerCenterStaffPage.rejectWithdrawal
		throw new UnsupportedOperationException();
	}

	public void generateReport() {
		// TODO - implement CareerCenterStaffPage.generateReport
		throw new UnsupportedOperationException();
	}

}