package service;


import repository.Repository;
import model.Company;


/**
 * Manages the company list in repository
 * Adds new employees, updates company internships, etc
 */
public class CompanyManager {
    private Repository repo;

    public CompanyManager(Repository repo) {
        this.repo = repo;
    }

    public Company getCompany(String companyName) {
        for (Company c : repo.getCompanies()) {
            if (c.getName().equalsIgnoreCase(companyName)) return c;
        }
        return null;
    }

    public int  getNumInternships(String companyName)    {return getCompany(companyName).getNumInternships();}

    public void incrementInternships(String companyName) {getCompany(companyName).incrementInternships();}
    public void decrementInternships(String companyName) {getCompany(companyName).decrementInternships();}

    public void addCompany(Company co) {repo.addCompany(co);}

    /**
     * New employee is updated in repository company list
     * 
     * @param acc
     * @param companyName
     */
    public void addNewEmployee(String employee, String companyName) {
        Company company = getCompany(companyName);
        company.addEmployee(employee);
    }
}
