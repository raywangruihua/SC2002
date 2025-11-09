package repository;

import java.util.List;
import model.Internship;
import model.InternshipApplication;
import util.Sort;

/**
 * Repository class stores all pending internships, internships and internships applications
 * This creates a centralised list that all manager classes can access
 * 
 * @see InternshipManager
 * @see InternshipApplicationManager
 */
public class Repository {

	private List<Internship> internships;
	private List<InternshipApplication> applications;
	private List<Internship> pendingInternships;

	public Internship getInternshipByIndex(int index){
		for (Internship internship : internships){
			if (internship.getIndex() == index){
				return internship;
			}
		}
		throw new IllegalArgumentException("Internship with index " + index + " not found.");
	}

	public List<Internship> getDisplayInternships(int yearOfStudy, String major){
		Sort sort =  new Sort();
		List<Internship> display_list = sort.sortByMajor(internships, major);
		display_list = sort.sortByYearOfStudy(display_list, yearOfStudy);
		return display_list;
	}

	public void addApplication(InternshipApplication application){
		applications.add(application);
	}

}
