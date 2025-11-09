package enums;

/**
 * Possible outcomes when user enter account information during login
 */
public enum LoginStatus {
    /**
     * Correct user ID and password
     */
    Valid,

    /**
     * Invalid user ID
     */
    Invalid,

    /**
     * Valid user ID but incorrect password
     */
    IncorrectPassword
}
