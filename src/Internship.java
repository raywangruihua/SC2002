package src;
import java.util.*;
public class Internship {

	private String title;
	private String description;
	private InternshipLevel internshipLevel;
	private String preferredMajor;
	private LocalDate openDate;
	private LocalDate closingData;
	private InternshipStatus status;
	private String companyName;
	private List<String> companyRepresentatives;
	private boolean approvalStatus;
	private List<InternshipApplication> applicationsReceived;
	private boolean visibility;
	private int index; 


	public int getIndex(){
		return index;
	}
}