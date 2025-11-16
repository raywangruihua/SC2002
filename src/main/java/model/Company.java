package model;

import java.util.List;
import java.util.ArrayList;


/**
 * Company class
 * Contains list of employee accounts
 * A list of companies is kept in repository
 * 
 * @see CompanyRep
 * @see Repository
 */
public class Company {
    private String           name;
    private int              numInternships;
    private List<CompanyRep> employees;

    public Company(String name) {
        this.name      = name;
        numInternships = 0; 
        employees      = new ArrayList<CompanyRep>(); 
    }

    public String           getName()           {return name;}
    public int              getNumInternships() {return numInternships;}
    public List<CompanyRep> getEmployees()      {return employees;}

    public void addEmployee(CompanyRep acc) {employees.add(acc);}
    public void incrementInternships()      {numInternships++;}
    public void decrementInternships()      {numInternships--;}
}
