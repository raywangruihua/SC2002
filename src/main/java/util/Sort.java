package util;

import java.util.List;
import enums.InternshipLevel;
import model.Internship;

/**
 * Util class that sorts repository
 * @see Repository
 */
public class Sort {

	/**
	 * returns sorted list of internships by major
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
	 * returns sorted list of internships by year of study
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
