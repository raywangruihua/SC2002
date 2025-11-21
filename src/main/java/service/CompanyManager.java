package service;


import model.Company;
import repository.Repository;


/**
 * Manages the company list in repository
 * Adds new employees, updates company internships, etc
 */
public class CompanyManager {
    private Repository repo;

    public CompanyManager(Repository repo) {
        this.repo = repo;
    }

    /**
     * get company based on company's name 
     * @param companyName name of company
     * @return company 
     */
    public Company getCompany(String companyName) {
        for (Company c : repo.getCompanies()) {
            if (c.getName().equalsIgnoreCase(companyName)) return c;
        }
        return null;
    }

    /** 
     * get number of internships 
     * @param companyName company's name 
     * @return number of internships
     */
    public int  getNumInternships(String companyName)    {return getCompany(companyName).getNumInternships();}

    /**
     * increase number of internships under company 
     * @param companyName company's name 
     */
    public void incrementInternships(String companyName) {getCompany(companyName).incrementInternships();}
    
    /**
     * decrease number of internships under company 
     * @param companyName company's name 
     */
    public void decrementInternships(String companyName) {getCompany(companyName).decrementInternships();}

    /**
     * add company to the list 
     * @param co company to be added 
     */
    public void addCompany(Company co) {repo.addCompany(co);}

    /**
     * add a new employee to the company 
     * @param employee name of employee
     * @param companyName company's name 
     */
    public void addNewEmployee(String employee, String companyName) {
        Company company = getCompany(companyName);
        company.addEmployee(employee);
    }
}
