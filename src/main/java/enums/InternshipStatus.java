package enums;

/**
 * After a company representative creates an internship, they must wait for the career center staff to approve it
 * 
 * @see Internship
 */
public enum InternshipStatus {
	/**
	 * The internship has not been updated by the career center staff
	 */
	Pending,

	/**
	 * The internship has been approved
	 */
	Approved,

	/**
	 * The internship has been rejected
	 */
	Rejected,

	/**
	 * The internship has been filled by a student (after it has been approved)
	 */
	Filled
}