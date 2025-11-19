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


	public void toggleVisibility(int internshipIndex) {
		Internship internship = repo.getInternshipByIndex(internshipIndex);
		internship.setVisibility(!(internship.getVisibility()));
	}

	// Print all internships (for staff UI)
	public void viewInternships() {
		List<Internship> internships = repo.getInternships();
        for (Internship i : internships) {
            System.out.println(i);
        }
	}
	// return all internships (for generate report in CareerCenterStaffPage)
	public List<Internship> getAllInternships() {
		return repo.getInternships();
        
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
            internship.setStatus(InternshipStatus.REJECTED);
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
	 * @param application The internship application to withdraw
	 */
	public void requestWithdrawal(InternshipApplication application) {
		if (!(application.getAccepted())){
			System.out.println("Use InternshipApplicationManager instead.");
			return;
		}
		if (application.getWithdrawalRequested()){
			System.out.println("Withdrawal request has already been submitted for this application.");
			return;
		}
		ApplicationStatus currentStatus = application.getStatus();
		switch (currentStatus){
			case Unsuccessful -> {
				System.out.println("Withdrawal request failed. Application is already unsuccessful.");
			}
			case Successful -> {
				System.out.println("Application has already been withdrawn.");
			}
			default -> {
				application.setWithdrawalRequested(true);
				System.out.println("Withdrawal request submitted.");
			}
		}
	}

	/**
	 * Approve a withdrawal request for an application accepted by student
	 * @param application
	 */
	public void approveWithdrawal(InternshipApplication application) {
		if (application.getWithdrawalRequested()){
			System.out.println("No withdrawal request submitted.");
			return;
		}
		
		if (!application.getAccepted()){
			System.out.println("Use InternshipApplicationManager instead.");
			return;
		}

		int index = application.getInternshipIndex();
		Internship internship = repo.getInternshipByIndex(index);

		application.setApplicationStatus(ApplicationStatus.Withdrawn);
		internship.incrementSlots(1);

		if (internship.getStatus() == InternshipStatus.FILLED){
			internship.setStatus(InternshipStatus.APPROVED);
		}

		application.setApplicationStatus(ApplicationStatus.Withdrawn);
		application.setWithdrawalRequested(false);

		System.out.println("Withdrawal approved. Placement is withdrawn.");
	}

	/**
	 * 
	 * @param application The internship application to reject withdrawal for
	 */
	public void rejectWithdrawal(InternshipApplication application) {
		if (!application.getWithdrawalRequested()){
			System.out.println("No withdrawal request.");
			return;
		}
		if (!application.getAccepted()){
			System.out.println("Use InternshipApplicationManager instead.");
			return;
		}
		application.setWithdrawalRequested(false);
		System.out.println("Withdrawal rejected.");
	}

	/**
	 * Creates internship application for students 
	 */
	public InternshipApplication applyInternship(int index, String id, String name) {
		Internship internship = repo.getInternshipByIndex(index);
		InternshipApplication application = new InternshipApplication(index, internship.getTitle(), internship.getIndex(), id, name, ApplicationStatus.Pending);

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
	
   // Retrieve withdrawal status of a given application
	public ApplicationStatus getWithdrawalStatus(InternshipApplication a) {
		return a != null ? a.getStatus() : null;
	}

	/**
	 * student accepts a placement for internship
	 */
	public void acceptPlacement(InternshipApplication application){
		if (application.getAccepted()){
			System.out.println("You have already accepted this placement.");
			return;
		}
		if (application.getStatus() != ApplicationStatus.Successful){
			System.out.println("Only successful applicants can be accepted.");
			return;
		}
		int index = application.getInternshipIndex();
		Internship internship = repo.getInternshipByIndex(index);

		if (internship == null){
			System.out.println("Error: Internship applicationnt found for this application.");
			return;
		}
		
		application.setAccepted(true);
		internship.incrementSlots(-1);
		

		if (internship.getNumSlots() == 0){
			internship.setStatus(InternshipStatus.FILLED);
			for (InternshipApplication app : repo.getInternshipApplications()){
				if (app != application 
				   && app.getInternshipIndex() == index
				   && app.getStatus() == ApplicationStatus.Pending){
					app.setApplicationStatus(ApplicationStatus.Unsuccessful);
				}
			}
		}
			System.out.println("Placement accepted successfully.");
		}
		
	}

