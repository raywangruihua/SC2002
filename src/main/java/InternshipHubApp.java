import java.util.Scanner;
import service.AccountManager;
import service.CompanyManager;
import service.InternshipApplicationManager;
import service.InternshipManager;
import ui.*;
import model.*;
import repository.*;


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

    private static final String INTERNSHIPS_PATH          = "Internships.csv";
    private static final String APPLICATIONS_PATH         = "applications.csv";

    private static Repository repo = new Repository(
            INTERNSHIPS_PATH, 
            APPLICATIONS_PATH, 
            "PendingInternships.csv" // You need a path for pending internships based on Repository.java signature
        );
    
    private static AccountManager               accMgr      = new AccountManager(STUDENT_ACCOUNTS_PATH, STAFF_ACCOUNTS_PATH, COMPANY_REP_ACCOUNTS_PATH);
    /// Future implementation : Save repository offline
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
        acc = loginPage.display();

        System.out.println();
        /// After successful login, check which account type has logged in
        if      (acc instanceof Student)           student();
        else if (acc instanceof CompanyRep)        companyRep();
        else if (acc instanceof CareerCenterStaff) careerCenterStaff();
        else System.out.println("Unknown account type.");
    }

    public static void student() {
        Student studentAcc = (Student) acc;
        studentPage.display(studentAcc);
        login();
    }

    public static void companyRep() {
        CompanyRep repAcc = (CompanyRep) acc;
        repPage.display(repAcc);
        login();
    }

    public static void careerCenterStaff() {
        CareerCenterStaff staffAcc = (CareerCenterStaff) acc;
        staffPage.display(staffAcc);
        login();
    }

    public static void main(String[] args) {
        login();
    }
}
