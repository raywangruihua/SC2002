package service;


import model.Company;
import repository.Repository;


/**
 * Manages the company list in repository
 * Adds new employees, updates company internships, etc
 */
public class CompanyManager {
    private Repository repo;

    /**
     * constructor
     */
    public CompanyManager(Repository repo) {
        this.repo = repo;
    }

    public Company getCompany(String companyName) {
        for (Company c : repo.getCompanies()) {
            if (c.getName().equalsIgnoreCase(companyName)) return c;
        }
        return null;
    }

    /** 
     * get number of internships 
     * @param companyName 
     * @return number of internships
     */
    public int  getNumInternships(String companyName)    {return getCompany(companyName).getNumInternships();}

    /**
     * increase number of internships under company 
     * @param companyName
     */
    public void incrementInternships(String companyName) {getCompany(companyName).incrementInternships();}
    
    /**
     * decrease number of internships under company 
     * @param companyName
     */
    public void decrementInternships(String companyName) {getCompany(companyName).decrementInternships();}

    /**
     * add company to the list 
     * @param co
     */
    public void addCompany(Company co) {repo.addCompany(co);}

    /**
     * add a new employee to the company 
     * @param acc
     * @param companyName
     */
    public void addNewEmployee(String employee, String companyName) {
        Company company = getCompany(companyName);
        company.addEmployee(employee);
    }
}
