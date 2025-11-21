package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import enums.ApplicationStatus;
import model.InternshipApplication;
import model.Student;
import repository.Repository;

public class InternshipApplicationManager {
	private Repository repo;
	
	/**
	 * @param repo the shared repository that stores internship application
	 */
	public InternshipApplicationManager(Repository repo) {this.repo = repo;}

	/**
	 * get all internship applications with a requested withdrawal
	 * @return a list of applications with pending withdrawal requests
	 */
	public List<InternshipApplication> getWithdrawals() {
		return repo.getInternshipApplications().stream()
											   .filter(InternshipApplication::getWithdrawalRequested)
											   .filter(a -> a.getStatus() != ApplicationStatus.Withdrawn)
											   .collect(Collectors.toList());
	}

	/**
	 * get internship applications to a specific internship according to its index
	 * @param internshipIndex the index of the internship
	 * @return a list of applications for that internship index 
	 */
	public List<InternshipApplication> getApplications(int internshipIndex) {
		return repo.getInternshipApplications().stream()
											   .filter(a -> a.getInternshipIndex() == internshipIndex)
											   .collect(Collectors.toList());
	}

	/**
	 * get list of applications based on the student's id 
	 * @param studentID unique ID of the student
	 * @return a list of applications submitted by the given student
	 */
	public List<InternshipApplication> getApplications(String studentID) {
		return repo.getInternshipApplications().stream()
											   .filter(a -> a.getStudentID().equals(studentID))
											   .collect(Collectors.toList());
	}

	/**
	 * returns the internship application with the given application index
	 * @param applicationIndex the application index 
	 * @return the corresponding internship application
	 */
	public InternshipApplication getApplication(int applicationIndex) {
		for (InternshipApplication a : repo.getInternshipApplications()) {
			if (a.getApplicationIndex() == applicationIndex) return a;
		}
		return null;
	}

	/**
	 * removes an internship application with the given application index from the repository
	 * used when an application is approved for withdrawal
	 * @param applicationIndex the index of the application to remove 
	 */
	public void removeApplication(int applicationIndex) {
		repo.removeApplication(applicationIndex);
	}

	/**
	 * approves a student's internship application by setting status to successful
	 * @param applicationIndex the index of the application to approve
	 */
	public void approveApplication(int applicationIndex) {
		getApplication(applicationIndex).setApplicationStatus(ApplicationStatus.Successful);
	}

	/**
	 * reject the student's application 
	 * @param applicationIndex of the application
	 */
	public void rejectApplication(int applicationIndex) {
		getApplication(applicationIndex).setApplicationStatus(ApplicationStatus.Unsuccessful);		
	}

	/**
	 * view list of internships applied by a student
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
	 * returns application status of certain application through repo
	 * @param application
	 * @return the application status of the application
	 */
	public ApplicationStatus getApplicationStatus(InternshipApplication application) {
		return repo.getApplicationStatus(application);
	}

	/**
	 * student requests withdrawal for an application not yet accepted
	 * @param application to be withdrawn
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
		
		repo.removeApplication(application);
	}

	/**
	 * reject student's request to withdraw
	 * @param application the application for which withdrawal is requested
	 */
	public void rejectWithdrawal(InternshipApplication application){
		application.setWithdrawalRequested(false);
	}

	/**
	 * auto-withdraw student's application when accepted internship
	 * @param student that accepted an application
	 * @param acceptedApplication the student has accepted
	 */
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
