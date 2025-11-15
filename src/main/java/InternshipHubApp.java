import java.util.Scanner;
import service.AccountManager;
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
    private static final String STUDENT_ACCOUNTS_PATH = "StudentAccounts.csv";
    private static final String STAFF_ACCOUNTS_PATH   = "StaffAccounts.csv";

    private static AccountManager               accMgr      = new AccountManager(STUDENT_ACCOUNTS_PATH, STAFF_ACCOUNTS_PATH);
    private static Repository                   repo        = new Repository();
    private static InternshipManager            internMgr   = new InternshipManager(repo);
    private static InternshipApplicationManager appMgr      = new InternshipApplicationManager(repo);
    private static Scanner                      sc          = new Scanner(System.in);
    private static LoginPage                    loginPage   = new LoginPage(accMgr, sc);
    private static StudentPage                  studentPage = new StudentPage(internMgr, appMgr, sc);
    private static CareerCenterStaffPage        staffPage   = new CareerCenterStaffPage(accMgr, internMgr, appMgr, sc);
    private static CompanyRepPage               repPage     = new CompanyRepPage(internMgr, appMgr, sc);
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
                        System.out.println(e.getMessage());
                    }
                }
                case 2  -> loginPage.register();
                case 3  -> System.exit(0);
                default -> System.out.println("Please enter a valid option (1-" + loginPage.MAX_OPTION + ")");
            }
        }
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
            }
        }
    }

    public static void companyRep() {
        
    }

    public static void careerCenterStaff() {

    }

    public static void main(String[] args) {
        login();

        /// After successful login, check which account type has logged in
        if      (acc instanceof Student)           student();
        else if (acc instanceof CompanyRep)        companyRep();
        else if (acc instanceof CareerCenterStaff) careerCenterStaff();
        else System.out.println("Invalid account type.");

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
