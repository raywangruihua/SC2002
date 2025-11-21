package enums;

/**
 * After a company representative creates an internship, they must wait for the career center staff to approve it
 */
public enum InternshipStatus {
	/**
	 * The internship has not been updated by the career center staff
	 */
	PENDING,

	/**
	 * The internship has been approved
	 */
	APPROVED,

	/**
	 * The internship has been rejected
	 */
	REJECTED,

	/**
	 * The internship has been filled by a student (after it has been approved)
	 */
	FILLED
}