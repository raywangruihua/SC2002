package repository;

import java.util.ArrayList;
import java.util.List;

import enums.ApplicationStatus;
import model.Internship;
import model.InternshipApplication;
import model.Company;
import service.InternshipApplicationManager;
import service.InternshipManager;
import util.CSVHandler;
import util.Sort;
import model.Student;

/**
 * Repository class stores all pending internships, internships and internships applications
 * This creates a centralised list that all manager classes can access
 * Also stores list of companies
 */
public class Repository {
	private List<Internship> 			internships;
	private List<InternshipApplication> applications;
	private List<Internship> 			pendingInternships;
	private List<Company>				companies;

	private List<Student>               students;
    private CSVHandler                  csvHandler;
    private String                      internshipFilePath;
    private String                      studentFilePath;

    public Repository(String internshipsFilepath, String applicationsFilepath, String pendingInternshipsFilepath, String studentFilePath, String companiesFilePath) {
        this.internshipFilePath = internshipsFilepath;
        this.studentFilePath = studentFilePath;
        
        this.csvHandler = new CSVHandler();

        this.students = csvHandler.readStudents(studentFilePath);
        this.internships = csvHandler.readInternships(internshipFilePath);
		this.companies = csvHandler.readCompanies(companiesFilePath);

        this.applications = new ArrayList<>();
        this.pendingInternships = new ArrayList<>();
    }

	public List<Internship> 		   getInternships() 		   {return internships;}
	public List<InternshipApplication> getInternshipApplications() {return applications;}
	public List<Internship> 		   getPendingInternships() 	   {return pendingInternships;}
	public List<Company> 			   getCompanies() 			   {return companies;}

	/**
	 * Get internship application according to application index
	 * 
	 * @param applicationIndex is passed through to find specific appliction from repo
	 * 
	 * @return application if found
	 * @return null if not found
	 */
	public InternshipApplication getInternshipApplication(int applicationIndex) {
		for (InternshipApplication a : applications) {
			if (a.getApplicationIndex() == applicationIndex) return a;
		}
		return null;
	}

	public void addCompany(Company co) {companies.add(co);}

	/**
	 * When new internship is created, add to pending list to await approval by career center staff
	 */
	public void createInternship(Internship i) {pendingInternships.add(i);}

	/**
	 * Returns internship based off index
	 */
	public Internship getInternshipByIndex(int index){
		for (Internship internship : internships){
			if (internship.getIndex() == index){
				return internship;
			}
		}
		throw new IllegalArgumentException("Internship with index " + index + " not found.");
	}

	/**
	 * Returns list of internships to display for students
	 * 
	 * @param yearOfStudy is the year of study of the student 
	 * @param major is the major of the student 
	 * 
	 * @return the list of sorted internships
	 */
	public List<Internship> getDisplayInternships(int yearOfStudy, String major){
		List<Internship> display_list = Sort.sortByMajor(internships, major);
		display_list = Sort.sortByYearOfStudy(display_list, yearOfStudy);
		display_list = showVisible(display_list);
		return display_list;
	}

	/**
	 * Returns list of visbile internships for viewing
	 * 
	 * @param internships is the list of internships for display that needs sorting based on visbility
	 * @return list of internships that are visible
	 */
	public static List<Internship> showVisible(List<Internship> internships){
		List<Internship> visible_list = new ArrayList<>();
		for (Internship internship: internships){
			visible_list.add(internship);
		}
		return visible_list;
	}

	/**
	 * Adds new internship application to list of applications
	 * 
	 * @param application is the internship application to add into the repo
	 */
	public void addApplication(InternshipApplication application){
		applications.add(application);
	}

	/**
	 * Remove internship application according to application index if it exists
	 * 
	 * @param applicationIndex is the index of the applicaton to remove form the repo
	 */
	public void removeApplication(int applicationIndex) {
		InternshipApplication a = getInternshipApplication(applicationIndex);
		applications.remove(a);
	}

	/**
	 * Remove internship application according to application
	 * 
	 * @param applicaton is the application to remove from repo
	 */
	public void removeApplication(InternshipApplication application) {
		applications.remove(application);
	}

	/**
	 * Returns application status of applications
	 * 
	 * @param application is the application that requires application status
	 * @return application status 
	 * @return null if application not found
	 */
	public ApplicationStatus getApplicationStatus(InternshipApplication application){
		for (InternshipApplication a: applications){
			if (a.equals(application)){
				return a.getStatus();
			}
		}
		System.out.println("No applications available");
		return null;
	}

	/**
	 * adds all information from CSV
	 */
	public void saveAll() {
        csvHandler.writeStudents(studentFilePath, this.students);
        
        List<Internship> allInternships = new ArrayList<>(this.internships);
        allInternships.addAll(this.pendingInternships);
        
        csvHandler.writeInternships(internshipFilePath, allInternships);
        System.out.println("Repository saved to file.");
    }

	/**
	 * Adds new student object into list of students
	 * @param s is the new student
	 */
    public void addStudent(Student s) {
        this.students.add(s);
        saveAll(); 
    }

	/**
	 * Returns the list of students in repo
	 * @return
	 */
    public List<Student> getStudents() {
        return students;
    }

	/**
	 * Returns student based on student ID
	 * 
	 * @param id userID of student in UXXXXXXXA format
	 * @return student object of corresponding student ID
	 * @return null if not found
	 */
	public Student getStudentByID(String id){
		for (Student s : students){
			if (s.getUserID().equals(id)){
				return s; 
			}
		}
		return null;
	}
}
