package service;

import java.util.List;
import java.util.stream.Collectors;
import enums.ApplicationStatus;
import enums.InternshipLevel;
import enums.InternshipStatus;
import model.Internship;
import model.InternshipApplication;
import repository.Repository;

public class InternshipManager {
	private Repository repo;

	public InternshipManager(Repository repo) {this.repo = repo;}

	public Internship getInternship(int index) {
		for (Internship i : repo.getInternships()) if (i.getIndex() == index) return i;
		return null;
	}

	/**
	 * Get pending internships according to company
	 */
	public List<Internship> getPendingInternships(String companyName) {
		return repo.getPendingInternships().stream()
										   .filter(i -> i.getCompanyName().equalsIgnoreCase(companyName))
										   .collect(Collectors.toList());
	}

	/**
	 * Get approved internships according to company
	 */
	public List<Internship> getApprovedInternships(String companyName) {
		return repo.getInternships().stream()
									.filter(i -> i.getCompanyName().equalsIgnoreCase(companyName))
									.collect(Collectors.toList());
	}

	public boolean checkInternshipExists(int index) {
		for (Internship i : repo.getInternships()) if (i.getIndex() == index) return true;
		return false;
	}

	/**
	 * External users should call this method instead of accessing repo directly for encapsulation
	 * 
	 * @param i
	 */
	public void createInternship(Internship i) {repo.createInternship(i);}

	/**
	 * Returns list of sorted internships from repo based on student's year of study and major
	 */
	public List<Internship> getInternships(int yearOfStudy, String major) {
		List<Internship> display_list = repo.getDisplayInternships(yearOfStudy, major);
		return display_list;
	}


	public void toggleVisibility() {
		// TODO - implement InternshipManager.toggleVisibility
		throw new UnsupportedOperationException();
	}

	// Print all internships (for staff UI)
	public void viewInternships() {
		List<Internship> internships = repo.getInternships();
        for (Internship i : internships) {
            System.out.println(i);
        }
	}

	/**
	 * Count the number of internships belonging to a particular company
	 * @param companyName
	 */
	public int getNumInternships(String companyName) {
		return (int) repo.getInternships().stream()
            .filter(i -> i.getCompanyName().equalsIgnoreCase(companyName))
            .count();
	}

	/**
	 * 
	 * @param i
	 */
	public void addInternship(Internship i) {
		repo.getInternships().add(i);
	}

	// Print out how many internships a company owns
	public void checkNumInternships(String companyName) {
		int count = getNumInternships(companyName);
        System.out.println("Total internships for company " + companyName + ": " + count);
	}

	/**
	 * Returns internship level of internship by index 
	 */
	public InternshipLevel getInternshipLevel(int index) {
		Internship internship = repo.getInternshipByIndex(index);
		return internship.getInternshipLevel();
	}

	public ApplicationStatus addApplicationStatus() {
		// TODO - implement InternshipManager.addApplicationStatus
		throw new UnsupportedOperationException();
	}

	// career staff reject the publish of internships
	public void rejectInternship(int index) {
		Internship internship = getInternship(index);
        if (internship != null) {
            internship.setStatus(InternshipStatus.Rejected);
        }
	}

	/**
	 * Set the status (eg. approved, rejected, pending) for an internship
	 * @param i
	 */
	public void setInternshipStatus(int index, InternshipStatus status) {
		Internship internship = getInternship(index);
        if (internship != null) {
            internship.setStatus(status);
        }
	}

	/**
	 * Request withdrawal for an application by student
	 * @param a The internship application to withdraw
	 */
	public void requestWithdrawal(InternshipApplication a) {
		if (a != null) {
            a.setApplicationStatus(ApplicationStatus.WITHDRAWAL_REQUESTED);
        }
	}

	/**
	 * 
	 * @param a The internship application to reject withdrawal for
	 */
	public void rejectWithdrawal(InternshipApplication a) {
		// TODO - implement InternshipManager.rejectWithdrawal
		throw new UnsupportedOperationException();
	}

	/**
	 * Creates internship application for students 
	 */
	public InternshipApplication applyInternship(int index, String id, String name) {
		Internship internship = repo.getInternshipByIndex(index);
		InternshipApplication application = new InternshipApplication(id, name, internship.getTitle());

		// add application to repo
		repo.addApplication(application);
		// add application to internship
		internship.addApplication(application);
		System.out.println("Application Successful");

		// return application to add to student's list of pending applications
		return application;
	}
	// Reject an application and mark with rejected status
	public void rejectApplication(InternshipApplication a) {
		if (a != null) {
            a.setApplicationStatus(ApplicationStatus.Unsuccessful);
        }
	}
	// Approve a withdrawal request and mark as withdrawn
	public void approveWithdrawal(InternshipApplication a) {
		if (a != null) {
            a.setApplicationStatus(ApplicationStatus.Successful);
        }
	}
   // Retrieve withdrawal status of a given application
	public ApplicationStatus getWithdrawalStatus(InternshipApplication a) {
		return a != null ? a.getStatus() : null;
	}

}
