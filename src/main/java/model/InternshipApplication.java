package model;

import enums.ApplicationStatus;

public class InternshipApplication {
	
	private String studentName;
	private String internshipTitle;
	private ApplicationStatus status;

	public InternshipApplication(String studentName, String internshipTitle){
		this.studentName = studentName;
		this.internshipTitle = internshipTitle;
		this.status = ApplicationStatus.Pending;
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
}
