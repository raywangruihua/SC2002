package model;

import enums.ApplicationStatus;

/**
 * Internship Application class
 * Each application has an application number for easy lookup
 */
public class InternshipApplication {
	private int 			  applicationIndex;
	private String 			  internshipTitle;
	private int				  internshipIndex;
	private String			  studentID;
	private String			  studentName;
	private ApplicationStatus status;

	public InternshipApplication(int index, String title, int internIndex, String id, String name, ApplicationStatus status) {
		this.applicationIndex = index;
		this.internshipTitle  = title;
		this.internshipIndex  = internIndex;
		this.studentID 		  = id;
		this.studentName 	  = name;
		this.status 		  = status;
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
