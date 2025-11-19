package ui;

import model.Account;
import repository.Repository;

/**
 * Interface class for command line display interface
 */
public interface UserInterface <T extends Account> {
	/**
	 * All user interfaces must implement a display to show and implement all menu options
	 */
	abstract voidS display(T userAcct);
}