package service;

import java.util.List;
import java.util.stream.Collectors;
import enums.ApplicationStatus;
import model.InternshipApplication;
import model.Student;
import repository.Repository;
import java.util.ArrayList;

public class InternshipApplicationManager {
	private Repository repo;

	public InternshipApplicationManager(Repository repo) {this.repo = repo;}

	/**
	 * Get all applications with withdrawal requests
	 */
	public List<InternshipApplication> getWithdrawals() {
		return repo.getInternshipApplications().stream()
											   .filter(InternshipApplication::getWithdrawalRequested)
											   .filter(a -> a.getStatus() != ApplicationStatus.Withdrawn)
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
		application.setWithdrawalRequested(true);
	}

	/**
	 * accept withdrawal for application 
	 * @param application
	 */
	public void approveWithdrawal(InternshipApplication application){
		application.setApplicationStatus((ApplicationStatus.Withdrawn));
		
		String studentID = application.getStudentID();
		Student student = repo.getStudentByID(studentID);

		if(student != null){
			student.getApplications().remove(application);
		}
		
		repo.getInternshipApplications().remove(application);
	}

	/**
	 * reject withdrawal for application 
	 * @param application
	 */
	public void rejectWithdrawal(InternshipApplication application){
		application.setWithdrawalRequested(false);
	}

	public void autoWithdrawApplications(Student student, InternshipApplication acceptedApplication){
		ApplicationStatus status;
		List<InternshipApplication> apps = student.getApplications();
		if (apps == null){
			return;
		}
		List<InternshipApplication> toRemove = new ArrayList<>();

		for (InternshipApplication app: apps){
			if (app == acceptedApplication) continue; 
			
			status = getApplicationStatus(app);
			
			if (status == ApplicationStatus.Pending || status == ApplicationStatus.Successful){
				app.setApplicationStatus(ApplicationStatus.Withdrawn);
				app.setWithdrawalRequested(false);
				toRemove.add(app);
			}
		}
		for (InternshipApplication app: toRemove){
			student.getApplications().remove(app);
			repo.getInternshipApplications().remove(app);
		}
	}
}
