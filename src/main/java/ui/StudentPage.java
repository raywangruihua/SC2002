package ui;
import java.util.*;

import enums.ApplicationStatus;
import enums.InternshipLevel;
import service.AccountManager;
import service.InternshipApplicationManager;
import service.InternshipManager;
import model.*;

public class StudentPage extends UserPage<Student> {
	public final int MAX_OPTION = 7;

	private AccountManager				 accMgr;
	private InternshipManager 			 internMgr;
	private InternshipApplicationManager appMgr;

	public StudentPage(Student studentAcc, AccountManager accMgr, InternshipManager internMgr, InternshipApplicationManager appMgr, Scanner sc) {
		super(studentAcc, accMgr, sc);
		this.internMgr  = internMgr;
		this.appMgr	    = appMgr;
	}

	@Override
	public void display() {
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
			"6. Change password             \n" +
			"7. Logout		           	    \n"
		);
	}

	public void start() {
		int option = -1;
        while (true) {
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
                case 1 -> viewInternships(account.getYearOfStudy(), account.getMajor());
                case 2 -> {
                    InternshipApplication application  = applyInternship(account.getYearOfStudy(), account.getUserID(), account.getName());
                    account.addApplication(application);
                }
				case 3 -> viewApplications();
				case 4 -> acceptInternship();
				case 5 -> withdrawApplication(account);
				case 6 -> changePassword();
				case 7 -> {return;}
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
		System.out.println("-----Internships-----");
		List<Internship> display_list = internMgr.getInternships(yearOfStudy, major);
		if (display_list.size() == 0) {System.out.println("No internships available.");}
		else displayInternships(display_list);
	}

	/**
	 * Creates internship application for student given student is applicable 
	 */
	public InternshipApplication applyInternship(int yearOfStudy, String id, String name){
	    long activeApps = account.getApplications().stream()
	        .map(app -> appMgr.getApplicationStatus(app))
	        .filter(s -> s == ApplicationStatus.Pending || s == ApplicationStatus.Successful)
	        .count();

	    if (activeApps >= 3) {
	        System.out.println("FAILURE: You have reached the limit of 3 active applications.");
	        return null;
	    }

	    int index = -1;
	    while (true) {
	        try {
	            System.out.print("Enter internship index: ");
	            index = Integer.parseInt(sc.nextLine());
	            break;
	        }
	        catch (NumberFormatException e) {}
	    }

	    if (!internMgr.checkInternshipExists(index)) {
	        System.out.println("Internship does not exist!"); 
	        return null;
	    }
	    // Logic Check: Year 1/2 cannot apply for Intermediate/Advanced
	    else if ((internMgr.getInternshipLevel(index) != InternshipLevel.Basic) && ((yearOfStudy == 1) || (yearOfStudy == 2))){
	        System.out.println("Internship level too high for student."); 
	        return null;
	    }
	    else if (checkApplied(name, index) == true){
	        System.out.println("Student already applied for internship."); 
	        return null;
	    }
	    else {
	        InternshipApplication application = internMgr.applyInternship(index, id, name);
	        return application;
	    }
	}

	/**
	 * Displays all student applications and statuses given student has applied 
	 */
	public void viewApplications() {
		System.out.println("-----Applications-----");
		for (InternshipApplication application: appMgr.getApplications(account.getUserID())){
			System.out.println("Internship Title: " + application.getInternshipTitle() + ", Application Status: " + appMgr.getApplicationStatus(application));
		}
		System.out.println();
	}

	/**
	 * Checks whether internship to apply exists in student applied internships
	 */
	public boolean checkApplied(String name, int index) {
		List<InternshipApplication> applications = appMgr.getApplications(index);
		for (InternshipApplication application : applications){
			if(application.getStudentName().equals(name)){
				return true;
			}
		}
		return false;
	}

	public void acceptInternship(){
		List<InternshipApplication> applications = account.getApplications();
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
				if ((choice < 1) || (choice > applications.size())){
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
		account.acceptPlacement(selected);
		appMgr.autoWithdrawApplications(account, selected);

		System.out.println("Placement accepted successfully. Other applications have been withdrawn.");
	}

	public void withdrawApplication(Student account) {
		List<InternshipApplication> applications = account.getApplications();
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
            	choice = Integer.parseInt(sc.nextLine());

				if (choice < 1 || choice > withdrawable.size()){
					System.out.println("Invalid index. Try again.");
				} else {
					break;
				}
			} catch (NumberFormatException e){
				System.out.println("Please enter a valid number.");
				sc.nextLine();
			}
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