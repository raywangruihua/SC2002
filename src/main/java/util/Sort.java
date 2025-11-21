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
	 * 
	 * @param internships is the list of internships to sort 
	 * @param major is the major with relevant internships to display
	 * @return list of internships with corresponding major 
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
	 * 
	 * @param internships is the list of internships to sort 
	 * @param yearOfStudy is the year of study to sort by 
	 * @return list of internships with relevant internship levels based on year of study 
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

}
