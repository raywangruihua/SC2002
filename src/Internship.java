package src;
import java.util.*;
import java.time.LocalDate;
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

	public InternshipLevel getInternshipLevel(){
		return internshipLevel;
	}

	public String getPreferedMajor(){
		return preferredMajor;
	}

	public String getTitle(){
		return title;
	}

	public String getDescription(){
		return description;
	}

	public String getCompanyName(){
		return companyName;
	}
}
