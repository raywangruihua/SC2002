package ui;

import java.util.Scanner;

import enums.InternshipLevel;
import model.Account;
import model.CareerCenterStaff;
import model.Internship;
import model.InternshipApplication;
import repository.Repository;
import service.AccountManager;
import service.InternshipApplicationManager;
import service.InternshipManager;

public class CareerCenterStaffPage implements UserInterface<CareerCenterStaff> {
	public final int MAX_OPTION = 11;
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

	public int display(CareerCenterStaff staffAcc, Repository repo) {
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

		int option = -1;
        outer: while (true) {
            try {
                System.out.print("\nEnter option: ");
                option = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {}

            switch (option) {
                case 1 -> viewAccounts();
                case 2 -> {
                    System.out.print("Enter User ID: ");
                    String userid = sc.nextLine();
					acceptAccount(userid);
				}
				case 3 -> {
                    System.out.print("Enter User ID: ");
                    String userid = sc.nextLine();
					rejectAccount(userid);
				}
				case 4 -> viewInternships();
				case 5 -> approveInternship();
                case 6 -> rejectInternship();
				case 7 -> viewWithdrawals();
				case 8 -> acceptWithdrawal();
				case 9 -> rejectWithdrawal();
				case 10 -> generateReport();
				case 11 -> {
					return 11;
				}
				default -> System.out.print("Please enter a valid option (1-" + MAX_OPTION + "): ");
            }
        }
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

	public void approveInternship() {
		int index = -1;
		while (true) {
			try {
				System.out.print("Enter internship index: ");
				index = Integer.parseInt(sc.nextLine());
				break;
			}
			catch (NumberFormatException e) {}
		}

		if (!internMgr.checkInternshipExists(index)) {System.out.println("Internship does not exist!");}
		else {
			internMgr.setInternshipStatus(index, enums.InternshipStatus.Approved);
        System.out.println("Internship " + index + " approved.");
		}
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
		// TODO - implement CareerCenterStaffPage.generateReport
		throw new UnsupportedOperationException();
	}

	public void generateReport() {
		// TODO - implement CareerCenterStaffPage.generateReport
		throw new UnsupportedOperationException();
	}

}