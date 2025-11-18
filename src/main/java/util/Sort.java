package util;

import java.util.List;
import java.util.ArrayList;
import enums.InternshipLevel;
import model.Internship;

/**
 * Util class that provides static methods for sorting
 * 
 */
public class Sort {
	/**
	 * returns sorted list of internships by major
	 */
	public static List<Internship> sortByMajor(List<Internship> internships, String major) {
		List<Internship> display_list = new ArrayList<>();

		for (Internship internship: internships) {
			if (internship.getPreferedMajor().equalsIgnoreCase(major)) {
				display_list.add(internship);
			}
		}

		return display_list;
	}
	/**
	 * returns sorted list of internships by year of study
	 */
	public static List<Internship> sortByYearOfStudy(List<Internship> internships, int yearOfStudy) {
		List<Internship> display_list = new ArrayList<>();

		/// Year 3 & 4 can access any internship
		if ((yearOfStudy == 3) || (yearOfStudy == 4)) {display_list = internships;}
		/// Year 1 & 2 can only access basic level internships
		else {
			for (Internship internship : internships){
				if (internship.getInternshipLevel().equals(InternshipLevel.Basic)){
					display_list.add(internship);
				}
			}
		}

		return display_list;
	}

	/**
	 * 
	 * @param companyName
	 */
	public static List<Internship> sortByCompany(String companyName) {
		// TODO - implement Sort.sortByCompany
		throw new UnsupportedOperationException();
	}

}
