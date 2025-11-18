package service;

import java.util.List;
import java.util.stream.Collectors;
import enums.ApplicationStatus;
import model.Internship;
import model.InternshipApplication;
import model.Student;
import repository.Repository;

public class InternshipApplicationManager {
	private Repository repo;

	public InternshipApplicationManager(Repository repo) {this.repo = repo;}

	/**
	 * Get all applications with withdrawal requests
	 */
	public List<InternshipApplication> getWithdrawals() {
		return repo.getInternshipApplications().stream()
											   .filter(a -> a.getStatus() == ApplicationStatus.WITHDRAWAL_REQUESTED)
											   .collect(Collectors.toList());
	}

	/**
	 * Get internship applications to a specific internship according to its index
	 */
	public List<InternshipApplication> getApplications(int internshipIndex) {
		return repo.getInternshipApplications().stream()
											   .filter(a -> a.getInternshipIndex() == internshipIndex)
											   .collect(Collectors.toList());
	}

	/**
	 * Get internship application using its index
	 */
	public InternshipApplication getApplication(int applicationIndex) {
		for (InternshipApplication a : repo.getInternshipApplications()) {
			if (a.getApplicationIndex() == applicationIndex) return a;
		}
		return null;
	}

	/**
	 * Remove internship applicaton according to its index
	 * Used when an application is approved for withdrawal
	 */
	public void removeApplication(int applicationIndex) {
		repo.removeApplication(applicationIndex);
	}

	/**
	 * Accept the student's internship application 
	 */
	public void approveApplication(int applicationIndex) {
		getApplication(applicationIndex).setApplicationStatus(ApplicationStatus.Successful);
	}

	/**
	 * Reject the student's internship application 
	 */
	public void rejectApplication(int applicationIndex) {
		getApplication(applicationIndex).setApplicationStatus(ApplicationStatus.Unsuccessful);		
	}

	/**
	 * View list of internships applied by a student
	 * @param student 
	 */
	public void viewInternshipApplied(Student student){
		List<InternshipApplication> apps = repo.getInternshipApplications();

		System.out.println("Internships Applied by " + student.getName());
		for (InternshipApplication a: apps){
			if (a.getStudentID() == student.getUserID()){
				System.out.println("- " + a.getInternshipTitle() + "(" + a.getStatus());
			}
		}
	}

	/**
	 * Returns application status of certain application through repo
	 */
	public ApplicationStatus getApplicationStatus(InternshipApplication application) {
		return repo.getApplicationStatus(application);
	}

	/**
	 * student requests withdrawal for an application not yet accepted
	 * @param application
	 */
	public void requestWithdrawal(InternshipApplication application){
		if (application.getAccepted()){
			System.out.println("Use InternshipManager for accepted placements.");
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
			case Withdrawn -> {
				System.out.println("Application has already been withdrawn.");
			}
			default -> {
				application.setWithdrawalRequested(true);
				System.out.println("Withdrawal request submitted.");
			}
		}
	}
	/**
	 * accept withdrawal for application 
	 * @param application
	 */
	public void approveWithdrawal(InternshipApplication application){
		if (!application.getWithdrawalRequested()){
			System.out.println("No withdrawal requested.");
			return; 
		}
		if (application.getAccepted()){
			System.out.println("Use InternshipManager for accepted placements.");
		}
		application.setApplicationStatus((ApplicationStatus.Withdrawn));
		application.setWithdrawalRequested(false);
		System.out.println("Withdrawal request approved.");
	}

	/**
	 * reject withdrawal for application 
	 * @param application
	 */
	public void rejectWithdrawal(InternshipApplication application){
		if (!application.getWithdrawalRequested()){
			System.out.println("No withdrawal requested.");
		}
		if (application.getAccepted()){
			System.out.println("Use InternshipManager for accepted placements.");
			return;
		}
		application.setWithdrawalRequested(false);
		System.out.println("Withdrawal request rejected.");
	}
}
