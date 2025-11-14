import java.util.Scanner;
import service.AccountManager;
import ui.*;
import model.Account;
import model.Student;
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
    private static final String STUDENT_ACCOUNTS_PATH = "";
    private static final String STAFF_ACCOUNTS_PATH   = "";
    public static void main(String[] args) {
        AccountManager accMgr = new AccountManager(STUDENT_ACCOUNTS_PATH, STAFF_ACCOUNTS_PATH);
        // reminder to add CSV info into repo
        Repository repo = new Repository();
        Scanner        sc     = new Scanner(System.in);

        LoginPage login = new LoginPage(accMgr, sc);
        login.display();

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
			                "1. View Internships		   \n" +
			                "2. Apply Internships		   \n" +
			                "3. View Applications			   \n" +
                            "4. Accept Placement			   \n" +
                            "5. Withdraw Applications			   \n" +
                            "6. Exit			   \n" +
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
    }
}
