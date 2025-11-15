package model;

import java.util.List;
import java.util.ArrayList;


/**
 * Company class
 * Contains list of employee accounts
 * Contains list of internships associated
 * A list of companies is kept in repository
 * 
 * @see CompanyRep
 * @see Repository
 */
public class Company {
    private String           name;
    private int              numInternships;
    private List<CompanyRep> employees;
    private List<Internship> internships;

    public Company(String name) {
        this.name      = name;
        numInternships = 0; 
        employees      = new ArrayList<CompanyRep>(); 
        internships    = new ArrayList<Internship>();
    }

    public String           getName()           {return name;}
    public int              getNumInternships() {return numInternships;}
    public List<CompanyRep> getEmployees()      {return employees;}
    public List<Internship> getInternships()    {return internships;}

    public void setNumInternships(int num)  {this.numInternships = num;}
    
    public void addInternship(Internship i) {internships.add(i); numInternships++;}

    public void addEmployee(CompanyRep acc) {employees.add(acc);}
}
