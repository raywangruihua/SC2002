package service;

import enums.ApplicationStatus;
import model.InternshipApplication;
import model.Student;
import repository.Repository;
import java.util.List;

public class InternshipApplicationManager {
	/**
	 * Accept the student's internship application 
	 * @param app
	 */
	public void approveApplication(InternshipApplication app) {
		if (app == null){
			System.out.println("Invalid Application.");
		} else {
			app.setApplicationStatus(ApplicationStatus.Successful);
		}
	}
	/**
	 * Reject the student's internship application 
	 * @param app
	 */
	public void rejectApplication(InternshipApplication app) {
		if (app == null){
			System.out.println("Invalid Application.");
			System.out.println("Application accepted.");
		} else {
			app.setApplicationStatus(ApplicationStatus.Unsuccessful);
			System.out.println("Application rejected.");
		}
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
