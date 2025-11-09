import java.util.Scanner;
import service.AccountManager;
import ui.*;

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
        Scanner        sc     = new Scanner(System.in);

        LoginPage login = new LoginPage(accMgr, sc);
        login.display();

        System.out.print("Enter option: ");
        int option;
        while (true) {
            switch (option = Integer.parseInt(sc.nextLine())) {
                case 1  -> {
                    if (login.login()) {
                        System.out.print("Nice\n");
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
}
