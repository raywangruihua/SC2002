package src.util;
import java.util.*;

import src.enums.InternshipLevel;
import src.model.Internship;
public class Sort {

	/**
	 * 
	 * @param major
	 */
	public List<Internship> sortByMajor(List<Internship> internships, String major) {
		// TODO - implement Sort.sortByMajor
		List<Internship> display_list = null;
		for (Internship internship: internships){
			if (internship.getPreferedMajor().equals(major)){
				display_list.add(internship);
			}
		}
		return display_list;
	}
	/**
	 * 
	 * @param yearOfStudy
	 */
	public List<Internship> sortByYearOfStudy(List<Internship> internships, int yearOfStudy) {
		// TODO - implement Sort.sortByYearOfStudy
		List<Internship> display_list = null;
		if ((yearOfStudy == 3) || (yearOfStudy == 4)){
			return internships;
		}
		else {
			for (Internship internship : internships){
				if (internship.getInternshipLevel().equals(InternshipLevel.Basic)){
					display_list.add(internship);
				}
			}
			return display_list;
		}
	}

	/**
	 * 
	 * @param companyName
	 */
	public List<Internship> sortByCompany(String companyName) {
		// TODO - implement Sort.sortByCompany
		throw new UnsupportedOperationException();
	}

}
