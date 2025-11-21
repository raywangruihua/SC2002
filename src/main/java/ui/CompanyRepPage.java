package ui;

import java.util.Scanner;
import model.*;
import repository.Repository;
import service.*;
import forms.InternshipCreation;

public class CompanyRepPage extends UserPage<CompanyRep> {
	public final int MAX_OPTION = 8;

	private InternshipManager 			 internMgr;
	private InternshipApplicationManager appMgr;
	private CompanyManager				 coMgr;

	public CompanyRepPage(CompanyRep acc, AccountManager accMgr, InternshipManager internMgr, InternshipApplicationManager appMgr, CompanyManager coMgr, Scanner sc) {
		super(acc, accMgr, sc);
		this.internMgr = internMgr;
		this.appMgr    = appMgr;
		this.coMgr	   = coMgr;
	}

	@Override
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
			"7. Change  Password                           \n" +
			"8. Logout                                     \n"
		);
	}

	public void start() {
		int option = -1;
        while (option != 7) {
			display();
			
			while (true) {
				try {
					System.out.print("\nEnter option: ");
					option = Integer.parseInt(sc.nextLine());
					break;
				}
				catch (NumberFormatException e) {}
			}

            switch (option) {
                case 1 -> createInternship();
                case 2 -> viewInternships(account.getCompanyName());
                case 3 -> {
                    while (true) {
                        try {
                            System.out.print("Enter internship index: ");
                            option = Integer.parseInt(sc.nextLine());
                            toggleInternship(option);
                            break;
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Invalid input format.");
                        }
                        catch (NullPointerException e)  {
                            System.out.println("Internship " + option + " does not exist.");
                            break;
                        }
                    }
                }
                case 4 -> {
                    while (true) {
                        try {
                            System.out.print("Enter internship index: ");
                            option = Integer.parseInt(sc.nextLine());
                            viewApplications(option);
                            break;
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Invalid input format.");
                        }
                    }
                }
                case 5 -> {
                    while (true) {
                        try {
                            System.out.print("Enter application index: ");
                            option = Integer.parseInt(sc.nextLine());
                            approveApplication(option);
                            break;
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Invalid input format.");
                        }
                    }
                }
                case 6 -> {
                    while (true) {
                        try {
                            System.out.print("Enter application index: ");
                            option = Integer.parseInt(sc.nextLine());
                            rejectApplication(option);
                            break;
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Invalid input format.");
                        }
                    }
                }
                case 7 -> super.changePassword();
				case 8 ->{return;}
                default -> System.out.print("Please enter a valid option (1-" + MAX_OPTION + "): ");
            }
        }
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
	 * 
	 */
	public void createInternship() {
	    // 1. Use the form to gather data (it creates an object with Index -1)
	    forms.InternshipCreation form = new forms.InternshipCreation(sc, account.getCompanyName(), account.getName());
	    Internship newInternship = form.submit();
	    
	    if (newInternship != null) {
	        // 2. Pass it to Manager to Fix ID and Save
	        internMgr.submitInternship(newInternship);
	        // Success message is printed inside the manager
	    } else {
	        System.out.println("Internship creation cancelled.");
	    }
	}

	/**
	 * View internships (pending or approved)
	 * @param companyName
	 */
	public void viewInternships(String companyName) {
		System.out.print("\nApproved Internships");
		for (Internship i : internMgr.getApprovedInternships(companyName)) System.out.println(i);

		System.out.print("\nPending Internships");
		for (Internship i : internMgr.getPendingInternships(companyName)) System.out.println(i);
	}

	/**
	 * Toggle approved internship visibility by index
	 * @param index
	 * @throws NullPointerException
	 */
	public void toggleInternship(int index) throws NullPointerException {	
		internMgr.getInternship(index).toggleVisibility();
	}

	/**
	 * View applications for internship chosen
	 * @param internshipIndex
	 */
	public void viewApplications(int internshipIndex) {
		System.out.println("-----Applications-----");
		for (InternshipApplication a : appMgr.getApplications(internshipIndex)) {
			System.out.println(a);
		}
		System.out.println();
	}

	/**
	 * Approve application via InternshipApplication Manager
	 * @param applicationIndex
	 */
	public void approveApplication(int applicationIndex) {appMgr.approveApplication(applicationIndex);}

	/**
	 * Reject application via InternshipApplication Manager
	 * @param applicationIndex
	 */
	public void rejectApplication(int applicationIndex) {appMgr.rejectApplication(applicationIndex);}
}