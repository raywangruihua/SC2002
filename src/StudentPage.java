package src;
import java.util.*;
public class StudentPage extends UserPage {

	public void display(List<Internship> display_list) {
		// TODO - implement StudentPage.display

		//shows title, description, internship level, company name
		for (Internship internship : display_list){
			System.out.println("Internship Title: " + internship.getTitle() + ", Internship Level: " + internship.getInternshipLevel() + ", Company Name: " + internship.getCompanyName());
			System.out.println("Description: " + internship.getDescription());
			System.out.println();
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param yearOfStudy
	 * @param major
	 */
	public void viewInternships(int yearOfStudy, String major) {
		// TODO - implement StudentPage.viewInternships
		Repository repo = new Repository();
		List<Internship> display_list = repo.getDisplayInternships(yearOfStudy, major);
		display(display_list);
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param numApplications
	 */
	public InternshipApplication applyInternship(int index, int yearOfStudy, String name) {
		// TODO - implement StudentPage.applyInternship
		// get internship from repo
		Repository repo = new Repository();
		Internship internship = repo.getInternshipByIndex(index);
		
		if ((internship.getInternshipLevel() != InternshipLevel.Basic) && ((yearOfStudy == 1) | (yearOfStudy == 2))){
			System.out.println("Application Unaccepted");
		}
		else {
			// create new application
			InternshipApplication application = new InternshipApplication(name, internship.getTitle());

			// add application to repo
			repo.addApplication(application);
			System.out.println("Application Successful");
			
			// add application to studentpending
			return application;
		}
		throw new UnsupportedOperationException();
	}

	public void viewApplications(List<InternshipApplication> applications) {
		// TODO - implement StudentPage.viewApplications
		InternshipApplicationManager am = new InternshipApplicationManager();
		for (InternshipApplication application: applications){
			System.out.println("Internship Title: " + application.getInternshipTitle() + ", Application Status: " + am.getApplicationStatus(application));
		}
		throw new UnsupportedOperationException();
	}

	public void acceptInternship() {
		// TODO - implement StudentPage.acceptInternship
		throw new UnsupportedOperationException();
	}

	public void withdrawApplication() {
		// TODO - implement StudentPage.withdrawApplication
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param internship
	 */
	public void viewInternship(Internship internship) {
		// TODO - implement StudentPage.viewInternship
		throw new UnsupportedOperationException();
	}

}