package service;

import java.util.List;

import enums.ApplicationStatus;
import enums.InternshipLevel;
import enums.InternshipStatus;
import model.Internship;
import model.InternshipApplication;
import repository.Repository;

public class InternshipManager {

	public Internship createInternship() {
		// TODO - implement InternshipManager.createInternship
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns list of sorted internships from repo based on student's year of study and major
	 */
	public List<Internship> getInternships(int yearOfStudy, String major) {
		Repository repo = new Repository();
		List<Internship> display_list = repo.getDisplayInternships(yearOfStudy, major);
		return display_list;
	}

	public void toggleVisibility() {
		// TODO - implement InternshipManager.toggleVisibility
		throw new UnsupportedOperationException();
	}

	public void viewInternships() {
		// TODO - implement InternshipManager.viewInternships
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param companyName
	 */
	public int getNumInternships(String companyName) {
		// TODO - implement InternshipManager.getNumInternships
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param i
	 */
	public void addInternship(Internship i) {
		// TODO - implement InternshipManager.addInternship
		throw new UnsupportedOperationException();
	}

	public void checkNumInternships() {
		// TODO - implement InternshipManager.checkNumInternships
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns internship level of internship by index 
	 */
	public InternshipLevel getInternshipLevel(int index) {
		Repository repo = new Repository();
		Internship internship = repo.getInternshipByIndex(index);
		return internship.getInternshipLevel();
	}

	public ApplicationStatus addApplicationStatus() {
		// TODO - implement InternshipManager.addApplicationStatus
		throw new UnsupportedOperationException();
	}

	public void rejectInternship() {
		// TODO - implement InternshipManager.rejectInternship
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param i
	 */
	public void setInternshipStatus(InternshipStatus i) {
		// TODO - implement InternshipManager.setInternshipStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param a The internship application to withdraw
	 */
	public void requestWithdrawal(InternshipApplication a) {
		// TODO - implement InternshipManager.requestWithdrawal
		throw new UnsupportedOperationException();
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
	public InternshipApplication applyInternship(int index, String name) {
		Repository repo = new Repository();
		Internship internship = repo.getInternshipByIndex(index);
		InternshipApplication application = new InternshipApplication(name, internship.getTitle());

		// add application to repo
		repo.addApplication(application);
		// add application to internship
		internship.addApplication(application);
		System.out.println("Application Successful");

		// return application to add to student's list of pending applications
		return application;
	}

	public void rejectApplication() {
		// TODO - implement InternshipManager.rejectApplication
		throw new UnsupportedOperationException();
	}

	public void approveWithdrawal() {
		// TODO - implement InternshipManager.approveWithdrawal
		throw new UnsupportedOperationException();
	}

	public void getWithdrawalStatus() {
		// TODO - implement InternshipManager.getWithdrawalStatus
		throw new UnsupportedOperationException();
	}

}
