package ui;
import java.util.*;

import enums.ApplicationStatus;
import enums.InternshipLevel;
import repository.Repository;
import service.AccountManager;
import service.InternshipApplicationManager;
import service.InternshipManager;
import model.*;

public class StudentPage implements UserInterface<Student> {
	public final int MAX_OPTION = 6;
	private InternshipManager 			 internMgr;
	private InternshipApplicationManager appMgr;
	private Scanner		   				 sc;

	public StudentPage(InternshipManager internMgr, InternshipApplicationManager appMgr, Scanner sc) {
		this.internMgr = internMgr;
		this.appMgr	   = appMgr;
		this.sc		   = sc;
	}

	@Override
	public int display(Student studentAcc, Repository repo) {
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

		int option = -1;
        outer: while (true) {
            try {
                System.out.print("\nEnter option: ");
                option = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {}

            switch (option) {
                case 1 -> viewInternships(studentAcc.getYear(), studentAcc.getMajor());
                case 2 -> {
                    InternshipApplication application  = applyInternship(studentAcc.getYear(), studentAcc.getUserID(), studentAcc.getName());
                    studentAcc.addApplication(application);
                }
				case 3 -> viewApplications(studentAcc.getApplications(), repo);
				case 4 -> acceptInternship(studentAcc, repo);
				case 5 -> withdrawApplication(studentAcc, repo);
                case 6 -> {
					return 6;
				}
				default -> System.out.print("Please enter a valid option (1-" + MAX_OPTION + "): ");
            }
        }
	}
	
	/* 
	 * Displays internship information by index, title, internship level, company name and description
	 */
	public void displayInternships(List<Internship> display_list) {
		if (display_list == null){
			System.out.println("No internships available.");
		}
		for (Internship internship : display_list){
			System.out.println("Index: " + internship.getIndex() + ", Internship Title: " + internship.getTitle() + ", Internship Level: " + internship.getInternshipLevel() + ", Company Name: " + internship.getCompanyName());
			System.out.println("Description: " + internship.getDescription());
			System.out.println();
		}
	}

	/**
	 * Retrieves all internships student can apply for through internshipmanager
	 */
	public void viewInternships(int yearOfStudy, String major) {
		List<Internship> display_list = internMgr.getInternships(yearOfStudy, major);
		if (display_list.size() == 0) {System.out.println("No internships available.");}
		else displayInternships(display_list);
	}

	/**
	 * Creates internship application for student given student is applicable 
	 */
	public InternshipApplication applyInternship(int yearOfStudy, String id, String name) {
		int index = -1;
		while (true) {
			try {
				System.out.print("Enter internship index: ");
				index = Integer.parseInt(sc.nextLine());
				break;
			}
			catch (NumberFormatException e) {}
		}

		if (!internMgr.checkInternshipExists(index)) {System.out.println("Internship does not exist!"); return null;}

		if ((internMgr.getInternshipLevel(index) != InternshipLevel.Basic) && ((yearOfStudy == 1) || (yearOfStudy == 2))){
			throw new IllegalArgumentException("Application Unaccepted");
		}
		else {
			// create new application
			InternshipApplication application = internMgr.applyInternship(index, id, name);
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
			for (InternshipApplication application: applications){
				System.out.println("All Internship Applications");
				System.out.println("Internship Title: " + application.getInternshipTitle() + ", Application Status: " + appMgr.getApplicationStatus(application, repo));
			}
		}
		System.out.println();
	}

	public void acceptInternship(Student studentAcc, Repository repo){
		List<InternshipApplication> applications = studentAcc.getApplications();
		if (applications == null){
			System.out.println("You have no applications to access.");
			return;
		}

		System.out.println("Internship Applications: ");
		
		int canAccept = 0; 

		for (int i = 0; i < applications.size(); i++){
			InternshipApplication app = applications.get(i);
			ApplicationStatus status = appMgr.getApplicationStatus(app, repo);
			if (status == ApplicationStatus.Successful){
				canAccept += 1; 
			}
			System.out.println("[" + i + 1 + "]");
			System.out.println("Internship Title     : " + app.getInternshipTitle());
			System.out.println("Status               : " + status);
			System.out.println("Accepted             : " + (app.getAccepted() ? "Yes" : "No"));
			System.out.println("Withdrawal Requested : " + (app.getWithdrawalRequested() ? "Yes" : "No"));
			System.out.println();
		}
		
		if (canAccept <= 0){
			System.out.println("No internships can be accepted.");
			return;
		}

		int choice = -1; 
		while (true){
			try{
				System.out.println("Enter the index of the application: ");
				choice = sc.nextInt();
				if ((choice < 1) && (choice > applications.size() + 1)){
					System.out.println("Invaid index. Try again.");
				} else {
					break; 
				}
			} catch (NumberFormatException e){
				System.out.println("Please enter a valid number.")
			}
		}

		choice -= 1; 
		
		InternshipApplication selected = applications.get(choice);
		ApplicationStatus status = appMgr.getApplicationStatus(selected, repo);

		if (selected.getAccepted()){
			System.out.println("Application is already successful.");
			return;
		}
		if (status != ApplicationStatus.Successful){
			System.out.println("Only successful applicants can be accepted,");
			return;
		}

		internMgr.acceptPlacement(selected);
	}

	public void withdrawApplication(Student studentAcc, Repository repo) {
		List<InternshipApplication> applications = studentAcc.getApplications();
		if (applications == null){
			System.out.println("You have no applications to withdraw.");
		}
		System.out.println("Your applications:");
		for (int i = 0; i < applications.size(); i++) {
			InternshipApplication app = applications.get(i);
			ApplicationStatus status = appMgr.getApplicationStatus(app, repo);
	
			System.out.println("[" + i+1 + "]");
			System.out.println("Internship Title     : " + app.getInternshipTitle());
			System.out.println("Status               : " + status);
			System.out.println("Accepted             : " + (app.getAccepted() ? "Yes" : "No"));
			System.out.println("Withdrawal Requested : " + (app.getWithdrawalRequested() ? "Yes" : "No"));
			System.out.println();
		}
		int choice = -1; 
		while (true){
			try{
				System.out.println("Enter the index of the application: ");
				choice = sc.nextInt();
				if ((choice < 1) && (choice > applications.size() + 1)){
					System.out.println("Invaid index. Try again.");
				} else {
					break; 
				}
			} catch (NumberFormatException e){
				System.out.println("Please enter a valid number.")
			}
		}

		choice -= 1; 

		InternshipApplication selected = applications.get(choice);

		if (selected.getStatus() == ApplicationStatus.Withdrawn){
			System.out.println("This application has already been withdrawn.");
			return;
		}
		if (selected.getWithdrawalRequested()){
			System.out.println("A withdrawal request has already been made.");
		}
		if (selected.getAccepted()){
			internMgr.requestWithdrawal(selected);
		} else {
			appMgr.requestWithdrawal(selected);
		}
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