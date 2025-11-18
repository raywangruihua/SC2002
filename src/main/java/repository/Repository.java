package repository;

import java.util.ArrayList;
import java.util.List;

import enums.ApplicationStatus;
import model.Internship;
import model.InternshipApplication;
import model.Company;
import service.InternshipApplicationManager;
import service.InternshipManager;
import util.Sort;

/**
 * Repository class stores all pending internships, internships and internships applications
 * This creates a centralised list that all manager classes can access
 * Also stores list of companies
 * 
 * @see InternshipManager
 * @see InternshipApplicationManager
 */
public class Repository {
	private List<Internship> 			internships;
	private List<InternshipApplication> applications;
	private List<Internship> 			pendingInternships;
	private List<Company>				companies;

	public Repository(String internshipsFilepath, String applicationsFilepath, String pendingInternshipsFilepath) {
		this.internships 		= new ArrayList<>();
		this.applications 		= new ArrayList<>();
		this.pendingInternships = new ArrayList<>();
		this.companies 			= new ArrayList<>();
	}

	public List<Internship> 		   getInternships() 		   {return internships;}
	public List<InternshipApplication> getInternshipApplications() {return applications;}
	public List<Internship> 		   getPendingInternships() 	   {return pendingInternships;}
	public List<Company> 			   getCompanies() 			   {return companies;}

	public void addCompany(Company co) {companies.add(co);}

	/**
	 * When new internship is created, add to pending list to await approval by career center staff
	 * 
	 * @see Internship
	 * @see CareerCenterStaff
	 */
	public void createInternship(Internship i) {pendingInternships.add(i);}

	/* 
	 * Returns internship based off index
	 */
	public Internship getInternshipByIndex(int index){
		for (Internship internship : internships){
			if (internship.getIndex() == index){
				return internship;
			}
		}
		throw new IllegalArgumentException("Internship with index " + index + " not found.");
	}

	/* 
	 * Returns list of internships to display for students
	 */
	public List<Internship> getDisplayInternships(int yearOfStudy, String major){
		List<Internship> display_list = Sort.sortByMajor(internships, major);
		display_list = Sort.sortByYearOfStudy(display_list, yearOfStudy);
		return display_list;
	}

	/* 
	 * Adds new internship application to list of applications
	 */
	public void addApplication(InternshipApplication application){
		applications.add(application);
	}

	/* 
	 * Returns application status of applications
	 */
	public ApplicationStatus getApplicationStatus(InternshipApplication application){
		for (InternshipApplication a: applications){
			if (a.equals(application)){
				return a.getStatus();
			}
		}
		throw new IllegalArgumentException("Application with internship title " + application.getInternshipTitle() + " not found.");
	}

}
