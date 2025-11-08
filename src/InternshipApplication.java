package src;
public class InternshipApplication {

	private String studentName;
	private String internshipTitle;
	private ApplicationStatus status;

	public InternshipApplication(String studentName, String internshipTitle){
		this.studentName = studentName;
		this.internshipTitle = internshipTitle;
		this.status = ApplicationStatus.Pending;
	}

	public String getInternshipTitle(){
		return internshipTitle;
	}
}
