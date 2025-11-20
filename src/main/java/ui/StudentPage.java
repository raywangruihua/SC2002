package ui;
import java.util.*;

import enums.ApplicationStatus;
import enums.InternshipLevel;
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
	public void display(Student studentAcc) {
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
        while (true) {
            try {
                System.out.print("\nEnter option: ");
                option = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {}

            switch (option) {
                case 1 -> viewInternships(studentAcc.getYearOfStudy(), studentAcc.getMajor());
                case 2 -> {
                    InternshipApplication application  = applyInternship(studentAcc.getYearOfStudy(), studentAcc.getUserID(), studentAcc.getName());
                    studentAcc.addApplication(application);
                }
				case 3 -> viewApplications(studentAcc.getApplications());
				case 4 -> acceptInternship(studentAcc);
				case 5 -> withdrawApplication(studentAcc);
                case 6 -> {
					break;
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
	public InternshipApplication applyInternship(int yearOfStudy, String id, String name){
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
	public void viewApplications(List<InternshipApplication> applications) {
		if (applications.size() == 0){
			System.out.println("No Internship Applications");
		}
		else {
			for (InternshipApplication application: applications){
				System.out.println("All Internship Applications");
				System.out.println("Internship Title: " + application.getInternshipTitle() + ", Application Status: " + appMgr.getApplicationStatus(application));
			}
		}
		System.out.println();
	}

	public void acceptInternship(Student studentAcc ){
		List<InternshipApplication> applications = studentAcc.getApplications();
		if (applications == null || applications.isEmpty()){
			System.out.println("You have no applications to access.");
			return;
		}

		List<InternshipApplication> acceptable = new ArrayList<>();

		for (InternshipApplication app: applications){
			ApplicationStatus status = appMgr.getApplicationStatus(app);

			if (status == ApplicationStatus.Successful && !(app.getAccepted())){
				acceptable.add(app);
			}
		}

		if (acceptable.isEmpty()){
			System.out.println("No internships can be accepted.");
			return;
		}
		System.out.println("Internship Applications that can be accepted:");

		for (int i = 0; i < acceptable.size(); i++){
			InternshipApplication app = acceptable.get(i);
			ApplicationStatus status = appMgr.getApplicationStatus(app);
			System.out.println("[" + (i + 1) + "]");
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
					System.out.println("Invalid index. Try again.");
				} else {
					break; 
				}
			} catch (NumberFormatException e){
				System.out.println("Please enter a valid number.");
				sc.nextLine(); 
			}
		}

		InternshipApplication selected = acceptable.get(choice - 1);

		internMgr.acceptPlacement(selected);
		studentAcc.acceptPlacement(selected);
		appMgr.autoWithdrawApplications(studentAcc, selected);

		System.out.println("Placement accepted successfully. Other applications have been withdrawn.");
	}

	public void withdrawApplication(Student studentAcc){
		List<InternshipApplication> applications = studentAcc.getApplications();
		if (applications == null || applications.isEmpty()){
			System.out.println("You have no applications to withdraw.");
			return;
		}
		List<InternshipApplication> withdrawable = new ArrayList<>();
		
		for (InternshipApplication app: applications){
			ApplicationStatus status = appMgr.getApplicationStatus(app);

			if (status != ApplicationStatus.Unsuccessful && status != ApplicationStatus.Withdrawn && !app.getWithdrawalRequested()){
				withdrawable.add(app);
			}
			return;
		}

		if (withdrawable.isEmpty()){
			System.out.println("No applications are eligible for withdrawal.");
			return;
		}

		System.out.println("Applications that can be withdrawn: ");
		for (int i = 0; i < withdrawable.size(); i ++){
			InternshipApplication app = withdrawable.get(i);
			ApplicationStatus status = appMgr.getApplicationStatus(app);

			System.out.println("[" + (i+1) + "]");
			System.out.println("Internship Title     : " + app.getInternshipTitle());
			System.out.println("Status               : " + status);
			System.out.println("Accepted             : " + (app.getAccepted() ? "Yes" : "No"));
			System.out.println("Withdrawal Requested : " + (app.getWithdrawalRequested() ? "Yes" : "No"));
			System.out.println();
		}
		int choice = -1; 
		while (true){
			try{
				System.out.print("Enter the index of the application to withdraw: ");
            	choice = sc.nextInt();

				if (choice < 1 || choice > withdrawable.size()){
					System.out.println("Invalid index. Try again.");
				} else {
					break;
				}
			} catch (InputMismatchException e){}
				System.out.println("Please enter a valid number.");
				sc.nextLine(); 
		}
		InternshipApplication selected = withdrawable.get(choice - 1);
		if (selected.getAccepted()){
			internMgr.requestWithdrawal(selected);
		} else {
			appMgr.requestWithdrawal(selected);
		}
		System.out.println("Withdrawal request submitted.");
	}
}