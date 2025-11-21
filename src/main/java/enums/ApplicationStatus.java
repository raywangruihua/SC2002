package enums;

/**
 * After a student creates an application for an internship, they can check its status
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
	 * student withdrawns from application or internship
	 */

	Withdrawn
}