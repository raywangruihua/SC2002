package ui;

import java.util.Scanner;
import service.AccountManager;
import service.CompanyManager;
import forms.Registration;
import model.Account;

public class LoginPage {
    public final int MAX_OPTION = 3;

    private AccountManager accMgr;
    private CompanyManager companyManager;
    private Scanner        sc;

    public LoginPage(AccountManager accMgr, CompanyManager companyManager, Scanner sc) {
        this.accMgr         = accMgr;
        this.companyManager = companyManager;
        this.sc             = sc;
    }

    public Account display() {  
        int option = -1;
        while (true) {
            System.out.print(
                "\n----------------------\n" +
                "|                    |\n" +
                "|   Internship Hub   |\n" +
                "|                    |\n" +
                "----------------------\n" +
                                      "\n" +
                "1. Login               \n" +
                "2. Register           \n" +
                "3. Exit               \n"
            );

            try {
                System.out.print("\nEnter option: ");
                String input = sc.nextLine().trim(); 
                
                if (input.isEmpty()) continue; 
                
                option = Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1-3).");
                continue;
            }

            switch (option) {
                case 1  -> {
                    try {
                        Account acc = login();
                        if (acc != null) return acc;
                    }
                    catch (Exception e) {
                        System.out.println("Login Error: " + e.getMessage());
                    }
                }
                case 2  -> register();
                case 3  -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Please enter a valid option (1-" + MAX_OPTION + ")");
            }
        }
    }

    public Account login() {
        String userID = getNonEmptyInput("Enter user ID: ");
        String password = getNonEmptyInput("Enter password: ");

        switch (accMgr.checkValid(userID, password)) {
            case Valid : return accMgr.getAccount(userID);
            case IncorrectPassword : {
                throw new IllegalArgumentException("Incorrect password");
            }
            case Invalid : {
                throw new IllegalArgumentException("Invalid user ID");
            }
            default : {
                throw new IllegalArgumentException("Something went wrong");
            }
        }
    }

    public void register() {
        Registration form = new Registration(accMgr, sc);
        accMgr.register(form.submit());
        System.out.println("Account submitted for approval.");
    }

    private String getNonEmptyInput(String prompt) {
        String input = "";
        while (input.isEmpty()) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
        return input;
    }
}
