package model;

import java.util.List;

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
    private List<String>     employees;

    public Company(String name, int numInternships, List<String> employees) {
        this.name           = name;
        this.numInternships = numInternships;
        this.employees      = employees;
    }

    public String           getName()           {return name;}
    public int              getNumInternships() {return numInternships;}
    public List<String>     getEmployees()      {return employees;}

    public void addEmployee(String employee) {employees.add(employee);}
    public void incrementInternships()       {numInternships++;}
    public void decrementInternships()       {numInternships--;}
}
