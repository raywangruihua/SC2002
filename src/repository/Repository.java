package src.repository;
import java.util.*;

import src.model.Internship;
import src.model.InternshipApplication;
import src.util.Sort;
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
