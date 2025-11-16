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
		Repository repo = new Repository();  
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
	public ApplicationStatus getApplicationStatus(InternshipApplication application, Repository repo) {
		return repo.getApplicationStatus(application);
	}

	public void acceptApplication(){}

	public void loadApplication() {
		// TODO - implement InternshipApplicationManager.loadApplication
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param s
	 */
	public void listStudentApplied(Student s) {
		// TODO - implement InternshipApplicationManager.listStudentApplied
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param a The internship application to withdraw
	 */
	public void requestWithdrawal(InternshipApplication a) {
		// TODO - implement InternshipApplicationManager.requestWithdrawal
		throw new UnsupportedOperationException();
	}

	public void acceptWithdrawal(InternshipApplication app){
		// 
	}
	public void rejectWithdrawal() {
		// TODO - implement InternshipApplicationManager.rejectWithdrawal
		throw new UnsupportedOperationException();
	}

	public void checkIfAlreadyAccepted() {
		// TODO - implement InternshipApplicationManager.checkIfAlreadyAccepted
		throw new UnsupportedOperationException();
	}

}
