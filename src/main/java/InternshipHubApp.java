import java.util.Scanner;
import service.AccountManager;
import service.CompanyManager;
import service.InternshipApplicationManager;
import service.InternshipManager;
import ui.*;
import model.*;
import repository.Repository;


/**
 * Internship Hub App
 * 
 * All accounts are loaded from csv files at start up
 * One scanner is used to prevent input conficts
 * Only use Scanner.nextLine() for simiplicity, prevent data input mismatch
 * 
 * Login page is called
 * Users can login or register for an account
 */
public class InternshipHubApp {
    private static final String STUDENT_ACCOUNTS_PATH     = "StudentAccounts.csv";
    private static final String STAFF_ACCOUNTS_PATH       = "StaffAccounts.csv";
    private static final String COMPANY_REP_ACCOUNTS_PATH = "CompanyRepAccounts.csv";

    private static AccountManager               accMgr      = new AccountManager(STUDENT_ACCOUNTS_PATH, STAFF_ACCOUNTS_PATH, COMPANY_REP_ACCOUNTS_PATH);
    /// Future implementation : Save repository offline
    private static Repository                   repo        = new Repository();
    /// Managers have access to repo
    private static CompanyManager               coMgr       = new CompanyManager(repo);
    private static InternshipManager            internMgr   = new InternshipManager(repo);
    private static InternshipApplicationManager appMgr      = new InternshipApplicationManager(repo);
    private static Scanner                      sc          = new Scanner(System.in);
    /// UI pages
    private static LoginPage                    loginPage   = new LoginPage(accMgr, sc);
    private static StudentPage                  studentPage = new StudentPage(internMgr, appMgr, sc);
    private static CareerCenterStaffPage        staffPage   = new CareerCenterStaffPage(accMgr, internMgr, appMgr, sc);
    private static CompanyRepPage               repPage     = new CompanyRepPage(internMgr, appMgr, coMgr, sc);
    private static Account                      acc;

    public static void login() {
        loginPage.display();

        int option = -1;
        outer: while (true) {
            try {
                System.out.print("\nEnter option: ");
                option = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {}

            switch (option) {
                case 1  -> {
                    try {
                        acc = loginPage.login();
                        break outer;
                    }
                    catch (Exception e) {
                        System.out.print(e.getMessage());
                    }
                }
                case 2  -> loginPage.register();
                case 3  -> System.exit(0);
                default -> System.out.println("Please enter a valid option (1-" + loginPage.MAX_OPTION + ")");
            }
        }

        System.out.println();
        /// After successful login, check which account type has logged in
        if      (acc instanceof Student)           student();
        else if (acc instanceof CompanyRep)        companyRep();
        else if (acc instanceof CareerCenterStaff) careerCenterStaff();
        else System.out.println("Unknown account type.");
    }

    public static void student() {
        Student studentAcc = (Student) acc;
        studentPage.display();
        
        int option = -1;
        outer: while (true) {
            try {
                System.out.print("\nEnter option: ");
                option = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {}

            switch (option) {
                case 1 -> studentPage.viewInternships(studentAcc.getYear(), studentAcc.getMajor());
                case 2 -> studentPage.applyInternship(studentAcc.getYear(), studentAcc.getUserID(), studentAcc.getName());
                case 6 -> login();
            }
        }
    }

    public static void companyRep() {
        CompanyRep repAcc = (CompanyRep) acc;

        repPage.display();

        int option = -1;
        while (true) {
            try {
                System.out.print("\nEnter option: ");
                option = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {}

            switch (option) {
                case 1 -> repPage.createInternship(repAcc.getCompanyName());
                case 2 -> repPage.viewInternships(repAcc.getCompanyName());
                case 3 -> {
                    while (true) {
                        try {
                            System.out.print("Enter internship index: ");
                            option = Integer.parseInt(sc.nextLine());
                            repPage.toggleInternship(option);
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
                            repPage.viewApplications(option);
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
                            repPage.approveApplication(option);
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
                            repPage.rejectApplication(option);
                            break;
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Invalid input format.");
                        }
                    }
                }
                case 7 -> login();
                default -> System.out.print("Please enter a valid option (1-" + repPage.MAX_OPTION + "): ");
            }
        }

        
    }

    public static void careerCenterStaff() {

    }

    public static void main(String[] args) {
        login();

        /*
        System.out.print("Enter option: ");
        int option = sc.nextInt();
        switch (option = Integer.parseInt(sc.nextLine())) {
            case 1  -> {
                Account acct = login.login();
                if (acct instanceof Student) {
                    Student student = (Student) acct;
                    int choice = 0;
                    while (choice != 6){
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
                            "6. Exit		           	    \n" +
			                                               "\n"
		                );

                        System.out.println("Enter choice: ");
                        choice = sc.nextInt();
                        StudentPage sp = new StudentPage();

                        switch(choice){
                            case 1 -> student.viewApplications(repo);
                            case 2 -> {
                                System.out.println("Enter index of internship to apply for: ");
                                int index = sc.nextInt();
                                student.applyInternship(index, repo);
                            }
                            case 3 -> student.viewApplications(repo);
                            case 4 -> sp.acceptInternship();
                            case 5 -> sp.withdrawApplication();
                        }
                    }
                }

                else {
                    System.out.print("\nEnter option: ");
                }
            }
            case 2  -> {
                login.register();
                System.out.print("\nEnter option: ");
            }
            case 3  -> System.exit(0);
            default -> System.out.print("Enter option: ");
        }
        */
    }
}
