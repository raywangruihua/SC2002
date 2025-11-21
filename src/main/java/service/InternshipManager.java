package service;

import java.util.List;
import java.util.stream.Collectors;

import enums.ApplicationStatus;
import enums.InternshipLevel;
import enums.InternshipStatus;
import model.Internship;
import model.InternshipApplication;
import model.Student;
import repository.Repository;

public class InternshipManager {
	private Repository repo;

	public InternshipManager(Repository repo) {this.repo = repo;}
	
	/**
	 * get internship based on internship index 
	 * @param index
	 * @return Internship
	 */
	public Internship getInternship(int index) {
		for (Internship i : repo.getInternships()) if (i.getIndex() == index) return i;
        // Also check pending list
        for (Internship i : repo.getPendingInternships()) if (i.getIndex() == index) return i;
		return null;
	}

	/**
	 * Get pending internships according to company's name
	 * @param companyName 
	 */
	public List<Internship> getPendingInternships(String companyName) {
		return repo.getPendingInternships().stream()
										   .filter(i -> i.getCompanyName().equalsIgnoreCase(companyName))
										   .collect(Collectors.toList());
	}

	/**
	 * Get all pending internships
	 * @return listOfInternships 
	 */
	public List<Internship> getPendingInternships() {
		return repo.getPendingInternships();
	}

	/**
	 * Get approved internships according to company
	 * @param companyName 
	 * @return list of internships based on company's name 
	 */
	public List<Internship> getApprovedInternships(String companyName) {
		return repo.getInternships().stream()
									.filter(i -> i.getCompanyName().equalsIgnoreCase(companyName))
									.collect(Collectors.toList());
	}

	/**
	 * check internship exists 
	 * @param index
	 * @return internshipExists 
	 */
	public boolean checkInternshipExists(int index) {
		for (Internship i : repo.getInternships()) if (i.getIndex() == index) return true;
        for (Internship i : repo.getPendingInternships()) if (i.getIndex() == index) return true;
		return false;
	}

	/**
	 * create a new internship
	 * @param newInternship
	 */
    public void submitInternship(Internship newInternship) {
        int maxId = 0;
        
        // 1. Check existing active internships
        for (Internship i : repo.getInternships()) {
            if (i.getIndex() > maxId) maxId = i.getIndex();
        }
        
        // 2. Check pending internships
        for (Internship i : repo.getPendingInternships()) {
            if (i.getIndex() > maxId) maxId = i.getIndex();
        }
        
        // 3. Assign new Auto-Increment ID
        int newId = maxId + 1;
        newInternship.setIndex(newId);
        
        // 4. Save to Repository
        repo.createInternship(newInternship);
        repo.saveAll();
        
        System.out.println("SUCCESS: Internship submitted with new Index: " + newId);
        System.out.println("Please wait for Career Center Staff to approve it.");
    }

	/**
	 * External users should call this method instead of accessing repo directly for encapsulation
	 * * @param i
	 */
	public void createInternship(Internship i) {repo.createInternship(i);}

	/**
	 * Returns list of sorted internships from repo based on student's year of study and major
	 * @param yearOfStudy 
	 * @param major 
	 * @return list of internships based on year of study & major 
	 */
	public List<Internship> getInternships(int yearOfStudy, String major) {
		List<Internship> display_list = repo.getDisplayInternships(yearOfStudy, major);
		return display_list;
	}

	/**
	 * toggle the visibility of the internship 
	 * @param internshipIndex
	 */
	public void toggleVisibility(int internshipIndex) {
		Internship internship = repo.getInternshipByIndex(internshipIndex);
		internship.setVisibility(!(internship.isVisibility()));
	}

	// Print all internships (for staff UI)
	public void viewInternships() {
		List<Internship> internships = repo.getInternships();
        for (Internship i : internships) {
            System.out.println(i);
        }
	}

	/**
	 * retrieve a whole list of internships 
	 * @return list of internships 
	 */
	public List<Internship> getInternships() {
		return repo.getInternships();
	}

	/**
	 * count the number of internships belonging to a particular company
	 * @param companyName
	 * @return number of internships 
	 */
	public int getNumInternships(String companyName) {
		return (int) repo.getInternships().stream()
            .filter(i -> i.getCompanyName().equalsIgnoreCase(companyName))
            .count();
	}

	/**
	 * add internship to the list 
	 * @param i
	 */
	public void addInternship(Internship i) {
		repo.getInternships().add(i);
	}

	/**
	 * check the number of internships 
	 * @param companyName
	 */
	public void checkNumInternships(String companyName) {
		int count = getNumInternships(companyName);
        System.out.println("Total internships for company " + companyName + ": " + count);
	}

	/**
	 * Returns internship level of internship by index 
	 * @param index of the internship 
	 * @return InternshipLevel for that internship 
	 */
	public InternshipLevel getInternshipLevel(int index) {
		Internship internship = getInternship(index); // Updated to use the smarter getInternship
        if (internship == null) return InternshipLevel.Basic; // Fallback
		return internship.getInternshipLevel();
	}

	/**
	 * reject the internship made
	 * @param index
	 */
	public void rejectInternship(int index) {
		Internship internship = getInternship(index);
        if (internship != null) {
            setInternshipStatus(index, InternshipStatus.REJECTED);
        }
	}

	/**
	 * Set the status (eg. approved, rejected, pending) for an internship
     * Moves approved internships from Pending -> Active list
	 * @param i
	 */
	public void setInternshipStatus(int index, InternshipStatus status) {
		Internship target = null;
        boolean isPending = false;
        
        // Check pending list first
        for (Internship i : repo.getPendingInternships()) {
            if (i.getIndex() == index) {
                target = i;
                isPending = true;
                break;
            }
        }
        
        // If not in pending, check active
        if (target == null) {
             target = getInternship(index);
        }
        
        if (target == null) {
            System.out.println("Internship not found.");
            return;
        }

        target.setStatus(status);
        
        // Move from Pending to Active if Approved
        if (isPending && status == InternshipStatus.APPROVED) {
            repo.getPendingInternships().remove(target);
            repo.getInternships().add(target);
        }
        // Remove if Rejected
        else if (isPending && status == InternshipStatus.REJECTED) {
            repo.getPendingInternships().remove(target);
        }
        
        repo.saveAll();
	}

	/**
	 * Request withdrawal for an application by student
	 * @param application The internship application to withdraw
	 */
	public void requestWithdrawal(InternshipApplication application){
		application.setWithdrawalRequested(true);
        repo.saveAll(); // Added Save
	}

	/**
	 * Approve a withdrawal request for an application accepted by student
	 * @param application
	 */
	public void approveWithdrawal(InternshipApplication application){
		int index = application.getInternshipIndex();
		Internship internship = repo.getInternshipByIndex(index);

		application.setApplicationStatus(ApplicationStatus.Withdrawn);
		application.setAccepted(false);
		internship.incrementSlots(1);
		if (internship.getStatus() == InternshipStatus.FILLED){
			internship.setStatus(InternshipStatus.APPROVED);
		}

		Student student = repo.getStudentByID(application.getStudentID());
		if (student != null){
			student.getApplications().remove(application);
			student.acceptPlacement(null);
		}
		
		repo.removeApplication(application);
        repo.saveAll(); // Added Save
	}

	/**
	 * reject a withdrawal from an internship
	 *  @param application The internship application to reject withdrawal for
	 */
	public void rejectWithdrawal(InternshipApplication application) {
		application.setWithdrawalRequested(false);
        repo.saveAll(); // Added Save
	}

	/**
	 * student apply an internship
	 * @param index 
	 * @param id 
	 * @param name 
	 * @return internshipapplication
	 */
	public InternshipApplication applyInternship(int index, String id, String name) {
		Internship internship = repo.getInternshipByIndex(index);
        
        // Generate Unique Application ID based on list size
        int appId = repo.getInternshipApplications().size() + 1;
        
		InternshipApplication application = new InternshipApplication(appId, internship.getTitle(), internship.getIndex(), id, name, ApplicationStatus.Pending);

		// add application to repo
		repo.addApplication(application);
		// add application to internship
		internship.addApplication(application);
		System.out.println("Application Successful");
        
        repo.saveAll(); // Added Save

		// return application to add to student's list of pending applications
		return application;
	}

	/**
	 * reject an application for a student 
	 * @param a
	 */
	public void rejectApplication(InternshipApplication a) {
		if (a != null) {
            a.setApplicationStatus(ApplicationStatus.Unsuccessful);
            repo.saveAll();
        }
	}
	
   /**
	* get withdrawal status of an internship application
	* @param a
	* @return withdrawal status 
    */
	public ApplicationStatus getWithdrawalStatus(InternshipApplication a) {
		return a != null ? a.getStatus() : null;
	}

	/**
	 * student accepts a placement 
	 * @param application
	 */
	public void acceptPlacement(InternshipApplication application){
		int index = application.getInternshipIndex();
		Internship internship = repo.getInternshipByIndex(index);
		
		application.setAccepted(true);
		internship.incrementSlots(-1);

		if (internship.getNumSlots() == 0){
			internship.setStatus(InternshipStatus.FILLED);
			for (InternshipApplication app : repo.getInternshipApplications()){
				if (app != application){
					app.setApplicationStatus(ApplicationStatus.Unsuccessful);
				}
			}
		}
        repo.saveAll(); // Added Save
	}
}