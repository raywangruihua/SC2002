package src.enums;

/**
 * After a student creates an application for an internship, they can view the status of the application
 */

public enum ApplicationStatus {
	/**
	 * The application has not been updated by company rep
	 */
	Pending,

	/**
	 * The application has been approved
	 */
	Successful,

	/**
	 * The application has been rejected
	 */
	Unsuccessful
}