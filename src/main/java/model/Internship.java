package model;

import java.util.ArrayList;
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
	private LocalDate 					closingDate;
	private InternshipStatus 			status;
	private String 						companyName;
	private int							slots;
	private List<String> 				companyRepresentatives;
	// private boolean 					approvalStatus;
	private List<InternshipApplication> applicationsReceived;
	private boolean 					visibility;
	private int 						index; 

	public Internship(String title, String desc, InternshipLevel lvl, String major, LocalDate openDate, LocalDate closeDate, String companyName, int slots) {
		this.title 					= title;
		this.description 			= desc;
		this.internshipLevel 		= lvl;
		this.preferredMajor 		= major;
		this.openDate 				= openDate;
		this.closingDate 			= closeDate;
		this.status 				= InternshipStatus.Pending;
		this.companyName 			= companyName;
		this.slots					= slots;
		this.companyRepresentatives = new ArrayList<String>();
		this.applicationsReceived   = new ArrayList<InternshipApplication>();
		this.visibility 			= false;
		this.index 					= -1;
	}

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

	/*
	 * adds application to list of applications received
	 */
	public void addApplication(InternshipApplication application){
		applicationsReceived.add(application);
	}
}
