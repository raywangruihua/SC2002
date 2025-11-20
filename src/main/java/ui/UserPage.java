package ui;

import java.util.Scanner;

import model.Account;
import service.AccountManager;

/** 
 * Base class for user pages subtypes
 * Implements basic user functions
 */
public abstract class UserPage<T extends Account> implements UserInterface {
	protected T 			 account;
	protected AccountManager accMgr;
	protected Scanner 		 sc;

	public UserPage(T acc, AccountManager mgr, Scanner sc) {
		this.account = acc;
		this.accMgr  = mgr;
		this.sc 	 = sc;
	}

	/**
	 * Change password
	 */
	public void changePassword() {
		System.out.print("Enter new password: ");
		String newPassword = sc.nextLine();
		try {
			accMgr.updatePassword(account.getUserID(), newPassword);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}