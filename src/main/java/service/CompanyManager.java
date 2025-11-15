package service;


import java.util.List;
import repository.Repository;
import model.Company;
import model.CompanyRep;


/**
 * Manages the company list in repository
 * Adds new employees, updates company internships, etc
 */
public class CompanyManager {
    private Repository repo;

    public CompanyManager(Repository repo) {
        this.repo = repo;
    }

    public Company find(String companyName) {
        for (Company c : repo.getCompanies()) {
            if (c.getName().equalsIgnoreCase(companyName)) return c;
        }
        return null;
    }

    public void addCompany(Company co) {repo.addCompany(co);}

    /**
     * New employee is updated in repository company list
     * 
     * @param acc
     * @param companyName
     */
    public void addNewEmployee(CompanyRep acc, String companyName) {
        Company company = find(companyName);
        company.addEmployee(acc);
    }
}
