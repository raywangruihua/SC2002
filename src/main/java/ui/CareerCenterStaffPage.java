package ui;

import java.util.List;
import java.util.Scanner;

import enums.InternshipStatus;
import model.*;
import repository.Repository;
import service.*;
import util.Sort;

public class CareerCenterStaffPage extends UserPage<CareerCenterStaff> {
	public final int MAX_OPTION = 11;

	private InternshipManager			 internMgr;
	private InternshipApplicationManager appMgr;

	public CareerCenterStaffPage(CareerCenterStaff acc, AccountManager accMgr, InternshipManager internMgr, InternshipApplicationManager appMgr, Scanner sc) {
		super(acc, accMgr, sc);
		this.internMgr = internMgr;
		this.appMgr    = appMgr;
	}

	@Override
	public void display() {
		System.out.print(
			"----------------------------------------------\n" +
			"|                                            |\n" +
			"|     Career-Center Staff Internship Hub     |\n" +
			"|                                            |\n" +
			"----------------------------------------------\n" +
										   				  "\n" +
			"1.  View      Company account                 \n" +
			"2.  Accept    Company account                 \n" +
			"3.  Reject    Company account                 \n" +
			"4.  View      Internships                     \n" +
			"5.  Approve   Internship                      \n" +
			"6.  Reject    Internship                      \n" +
			"7.  View      Withdrawal Requests             \n" +
			"8.  Accept    Withdrawal                      \n" +
			"9.  Reject    Withdrawal                      \n" +
			"10. Generate  Report                          \n" +
			"11. Change    Password                        \n" +
			"12. Logout                                    \n"
		);

		int option = -1;
        while (option != 12) {
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
				case 4 ->  viewInternships();
				case 5 ->  approveInternship();
                case 6 ->  rejectInternship();
				case 7 ->  viewWithdrawals();
				case 8 ->  acceptWithdrawal();
				case 9 ->  rejectWithdrawal();
				case 10 -> generateReport();
				case 11 -> changePassword();
				case 12 -> {return;}
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
		System.out.print("\nCompany" + userID + "has been approve");
	}
	/**
	 * Reject Account via Account Manager
	 */
	public void rejectAccount(String userID) {
		accMgr.removePending(userID);
		System.out.print("\nCompany" + userID + "has been Rejected");
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
			internMgr.setInternshipStatus(index, InternshipStatus.APPROVED);
        	System.out.println("Internship " + index + " approved.");
		}
	}

	public void rejectInternship() {
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
			internMgr.setInternshipStatus(index, InternshipStatus.REJECTED);
        	System.out.println("Internship " + index + " rejected.");
		}
	}

	/**
	 * View application withdrawals
	 */
	public void viewWithdrawals() {
		List<InternshipApplication> withdrawals = appMgr.getWithdrawals();

		if (withdrawals.isEmpty()){
			System.out.println("There are no withdrawal requests at the moment.");
			return;
		}

		System.out.println("Pending Withdrawal Requests:");
		for (int i = 0; i < withdrawals.size(); i ++){
			InternshipApplication app = withdrawals.get(i);
			System.out.println("[" + (i + 1) + "]");
			System.out.println("  Application ID    : " + app.getApplicationIndex());
			System.out.println("  Student ID        : " + app.getStudentID());
			System.out.println("  Internship Index  : " + app.getInternshipIndex());
			System.out.println("  Internship Title  : " + app.getInternshipTitle());
			System.out.println("  Status            : " + app.getStatus());
			System.out.println("  Accepted          : " + (app.getAccepted() ? "Yes" : "No"));
			System.out.println();
		}
	}

	/**
	 * If withdrawal is accepted, delete application from repository
	 * 
	 * @see Repository
	 */
	public void acceptWithdrawal() {
		List<InternshipApplication> withdrawals = appMgr.getWithdrawals();

		if (withdrawals.isEmpty()){
			System.out.println("There are no withdrawal requests at the moment.");
			return;
		}

		viewWithdrawals();

		int choice = -1; 
		while (true){
			try{
				System.out.print("Enter the withdrawal request index to approve.");
				choice = Integer.parseInt(sc.nextLine());

				if (choice < 1 || choice > withdrawals.size()){
					System.out.println("Invalid index. Try again.");
				} else {
					break; 
				}
			} catch (NumberFormatException e){
				System.out.println("Please enter a valid number.");
			}
		}

		InternshipApplication selected = withdrawals.get(choice - 1);

		if (selected.getAccepted()){
			internMgr.approveWithdrawal(selected);
		} else {
			appMgr.approveWithdrawal(selected);
		}
		System.out.println("Withdrawal approved for application ID: " + selected.getApplicationIndex());
	}

	public void rejectWithdrawal() {
		List<InternshipApplication> withdrawals = appMgr.getWithdrawals();

		if (withdrawals.isEmpty()){
			System.out.println("There are no withdrawal requests at the moment.");
			return;
		}

		viewWithdrawals();

		int choice = -1; 
		while (true){
			try{
				System.out.print("Enter the withdrawal request index to reject.");
				choice = Integer.parseInt(sc.nextLine());

				if (choice < 1 || choice > withdrawals.size()){
					System.out.println("Invalid index. Try again.");
				} else {
					break; 
				}
			} catch (NumberFormatException e){
				System.out.println("Please enter a valid number.");
			}

			InternshipApplication selected = withdrawals.get(choice -1);

			if (selected.getAccepted()){
				internMgr.rejectWithdrawal(selected);
			} else {
				appMgr.rejectWithdrawal(selected);
			}
			System.out.println("Withdrawal rejected for application ID: " + selected.getApplicationIndex());
		}

	}

	/**
	 * Generate report regarding internships
	 * Can filter internships based on : Status, Preferred Major, Internship Level, etc...
	 * Can also sort based on the same parameters
	 * Dynamically filers and sorts a list of internships
	 * 
	 * go to report page
	 * @see Sort
	 */
	public void generateReport() {
		List<Internship> internships = internMgr.getAllInternships();
        ReportPage reportPage = new ReportPage(internships, sc);
        reportPage.start();
	}

}