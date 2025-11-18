package enums;

import model.InternshipApplication;

/**
 * After a student creates an application for an internship, they can check its status
 * 
 * @see InternshipApplication
 */ 
public enum ApplicationStatus {
	/**
	 * The application has not been updated by company representative
	 */
	Pending,

	/**
	 * The application has been approved
	 */
	Successful,

	/**
	 * The application has been rejected
	 */
	Unsuccessful,

	/**
	 * student requests for withdrawnal
	 */
	WITHDRAWAL_REQUESTED,

	Withdrawn


}