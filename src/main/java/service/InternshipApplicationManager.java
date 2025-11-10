package service;

import enums.ApplicationStatus;
import model.InternshipApplication;
import model.Student;
import repository.Repository;

public class InternshipApplicationManager {

	public void approveApplcation() {
		// TODO - implement InternshipApplicationManager.approveApplcation
		throw new UnsupportedOperationException();
	}

	public void rejectApplication() {
		// TODO - implement InternshipApplicationManager.rejectApplication
		throw new UnsupportedOperationException();
	}

	public void viewInternshipApplied() {
		// TODO - implement InternshipApplicationManager.viewInternshipApplied
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns application status of certain application through repo
	 */
	public ApplicationStatus getApplicationStatus(InternshipApplication application) {
		Repository repo = new Repository();
		return repo.getApplicationStatus(application);
	}

	public void acceptApplication() {
		// TODO - implement InternshipApplicationManager.acceptApplication
		throw new UnsupportedOperationException();
	}

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

	public void rejectWithdrawal() {
		// TODO - implement InternshipApplicationManager.rejectWithdrawal
		throw new UnsupportedOperationException();
	}

	public void checkIfAlreadyAccepted() {
		// TODO - implement InternshipApplicationManager.checkIfAlreadyAccepted
		throw new UnsupportedOperationException();
	}

}
