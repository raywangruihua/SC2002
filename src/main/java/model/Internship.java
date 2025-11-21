package model;

import java.util.List;
import java.time.LocalDate;
import enums.InternshipLevel;
import enums.InternshipStatus;

/**
 * Internship class
 * Each internship created has a unique index assigned to it
 */
public class Internship {
	private int 						index; 
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
	private List<Integer> 				applicationsReceived;
	private boolean 					visibility;

	public Internship(int index, String title, String desc, InternshipLevel internshipLevel, String major,
					  LocalDate openDate, LocalDate closeDate, InternshipStatus status, String companyName,
					  int slots, List<String> reps, List<Integer> apps, boolean visibility) {
		this.title 					= title;
		this.description 			= desc;
		this.internshipLevel 		= internshipLevel;
		this.preferredMajor 		= major;
		this.openDate 				= openDate;
		this.closingDate 			= closeDate;
		this.status 				= status;
		this.companyName 			= companyName;
		this.slots					= slots;
		this.companyRepresentatives = reps;
		this.applicationsReceived   = apps;
		this.visibility 			= visibility;
		this.index 					= index;
	}

	/**
	 * Prints in this order: title, desc, level, major, openDate, closeDate, companyName, slots
	 */
	@Override
	public String toString() {
		return "Company Name: "				 + companyName			  +
			   "\nTitle: " 			  		 + title 				  +
			   "\nDescription: " 	  		 + description 			  +
			   "\nInternship Level: "  		 + internshipLevel 		  +
			   "\nPreferred Major: "  		 + preferredMajor 		  +
			   "\nOpen date: " 		  		 + openDate 			  +
			   "\nClose date: " 	  		 + closingDate 			  +
			   "\nStatus: " 		  		 + status 				  +
			   "\nSlots: " 			  		 + slots 				  + 
			   "\nCompany Representatives: " + companyRepresentatives +
			   "\nApplications Received: "   + applicationsReceived   +
			   "\nVisibility: " 			 + visibility 			  +
			   "\nIndex: " 					 + index;
	}

	public String 		    getTitle() 			 {return title;}
	public String 		    getDescription() 	 {return description;}
	public InternshipLevel  getInternshipLevel() {return internshipLevel;}
	public String 		    getPreferedMajor() 	 {return preferredMajor;}	
	public String 		    getCompanyName() 	 {return companyName;}
	public int 			    getIndex() 			 {return index;}
	public InternshipStatus getStatus()			 {return status;}
	public void 			toggleVisibility()   {visibility = !visibility;}

	/**
	 * increase num of slots by 1 
	 * @param addedSlots no of slots to be added
	 */
	public void incrementSlots(int addedSlots) {slots = slots + addedSlots;}

	/**
	 * adds application to list of applications received
	 * @param application
	 */
	public void addApplication(InternshipApplication application) {
		applicationsReceived.add(application.getApplicationIndex());
	}

	/**
	 * change InternshipStatus from pending to approve or reject
	 * @param status
	 */
	public void setStatus(InternshipStatus status) {
		this.status = status;
	}
	
	/**
	 * 
	 * @param index
	 */
	public void setIndex(int index) {
	    this.index = index;
	}

	/**
	 * change the visibility 
	 * @param visibility 
	 */
	public void setVisibility(boolean visibility){
		this.visibility = visibility;
	}

    public int getNumSlots() { return slots; }
    public LocalDate getOpenDate() { return openDate; }
    public LocalDate getClosingData() { return closingDate; }
    public List<String> getCompanyRepresentatives() { return companyRepresentatives; }
    public List<Integer> getApplicationsReceived() { return applicationsReceived; }
    public boolean isVisibility() { return visibility; }
    public boolean isApprovalStatus() { return status == InternshipStatus.APPROVED; }

}
