package model;

import enums.ApplicationStatus;

/**
 * Internship Application class
 * Each application has an application number for easy lookup
 */
public class InternshipApplication {
	private String			  studentID;
	private String			  studentName;
	private String 			  internshipTitle;
	private int				  internshipIndex;
	private int 			  applicationIndex;
	private ApplicationStatus status;

	public InternshipApplication(String studentID, String studentName, String internshipTitle){
		this.studentID 		 = studentID;
		this.studentName 	 = studentName;
		this.internshipTitle = internshipTitle;
		this.status 		 = ApplicationStatus.Pending;
	}

	public String 			 getStudentID() 	   {return studentID;}
	public String 			 getStudentName() 	   {return studentName;}
	public String 			 getInternshipTitle()  {return internshipTitle;}
	public int				 getInternshipIndex()  {return internshipIndex;}
	public int				 getApplicationIndex() {return applicationIndex;}
	public ApplicationStatus getStatus() 		   {return status;}

	@Override
	public String toString() {
		return "Application number: " 	+ applicationIndex +
			   "\nInternship Title: " 	+ internshipTitle  +
			   "\nStudent Name: " 		+ studentName 	   +
			   "\nApplication status: " + status;
	}

	public void setApplicationStatus(ApplicationStatus status) {this.status = status;}
}
