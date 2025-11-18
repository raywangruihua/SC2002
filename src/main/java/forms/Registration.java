package forms;

import java.util.Scanner;

import model.CompanyRep;
import model.Company;
import service.AccountManager;
import service.CompanyManager;

/**
 * Registration form for company representatives to submit information and create an account
 */
public class Registration implements Base<CompanyRep> {
    private AccountManager accMgr;
    private CompanyManager companyMgr;
    private Scanner        sc;

    public Registration(AccountManager accMgr, Scanner sc) {
        this.accMgr = accMgr;
        this.sc     = sc;
    }

    /**
     * Registration form fields:
     * 1. UserID
     * 2. Name
     * 3. Password
     * 4. Company Name
     * 5. Department
     * 6. Position
     * 
     * Ensures that the new account made does not already exist or is pending for approval
     * 
     * @return Creates a new CompanyRep account object
     */
    @Override
    public CompanyRep submit() {
        System.out.print("\nEnter user ID: ");
        String userID = sc.nextLine();

        while (accMgr.checkExists(userID) || accMgr.checkPending(userID)) {
            System.out.print(
                "User ID taken\n\n" +
                "Enter user ID: "
            );
            userID = sc.nextLine();
        }
        
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();
        
        System.out.print("Enter company name: ");
        String companyName = sc.nextLine();
        
        System.out.print("Enter department name: ");
        String department = sc.nextLine();
        
        System.out.print("Enter position: ");
        String position = sc.nextLine();
        
        CompanyRep newAcc = new CompanyRep(userID, name, password, companyName, department, position);
        
        /// Check if company already exists
        /// If not, add the new company to repository
        Company company = companyMgr.getCompany(companyName);
        if (company == null) {
            company = new Company(companyName);
            companyMgr.addCompany(company);
        }
        /// Add new employee to company
        companyMgr.addNewEmployee(newAcc, companyName);
        

        return newAcc;
    }
}
