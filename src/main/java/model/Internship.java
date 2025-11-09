package model;

import java.util.List;
import java.time.LocalDate;
import enums.InternshipLevel;
import enums.InternshipStatus;

public class Internship {

	private String 						title;
	private String 						description;
	private InternshipLevel 			internshipLevel;
	private String 						preferredMajor;
	private LocalDate 					openDate;
	private LocalDate 					closingData;
	private InternshipStatus 			status;
	private String 						companyName;
	private List<String> 				companyRepresentatives;
	private boolean 					approvalStatus;
	private List<InternshipApplication> applicationsReceived;
	private boolean 					visibility;
	private int 						index; 

	public Internship() {}

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
