package model;

import enums.ApplicationStatus;

public class InternshipApplication {
	
	private String			  studentID;
	private String			  studentName;
	private String 			  internshipTitle;
	private ApplicationStatus status;

	public InternshipApplication(String studentID, String studentName, String internshipTitle){
		this.studentID 		 = studentID;
		this.studentName 	 = studentName;
		this.internshipTitle = internshipTitle;
		this.status 		 = ApplicationStatus.Pending;
	}

	public String getStudentID() {
		return studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getInternshipTitle(){
		return internshipTitle;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setApplicationStatus(ApplicationStatus status){ 
		this.status = status;
	}
}
