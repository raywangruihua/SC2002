package util;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import model.Account;
import model.Student;
import model.CareerCenterStaff;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Utility class that reads files
 */
public class Read {
    /**
     * Student accounts format: (StudentID, Name, Password, Year, Major, Email)
     * Remember to downcast Account back to Student when accessing privileges
     * 
     * @see Account
     * @see Student
     * 
     * @param filepath Preferably use actual filepath
     * @return List<Account>
     */
    public static List<Account> readStudentAccountsCSV(String filepath) {
        List<Account> accounts = new ArrayList<>();

        try {
            Scanner scStream = new Scanner(new File(filepath));
            String inputLine;
            String[] info;
            
            inputLine = scStream.nextLine();
            while (scStream.hasNext()) {
                inputLine = scStream.nextLine();
                info = inputLine.split("[,]");
                Student newAccount = new Student(
                    info[0],
                    info[1],
                    info[2],
                    Integer.parseInt(info[3]),
                    info[4]
                );
                accounts.add(newAccount);
            }

            scStream.close();
            System.out.println(filepath + " read successfully.");
        }
        catch (FileNotFoundException e) {
            System.out.println(filepath + " not found.");
        }

        return accounts;
    }

    /**
     * Staff accounts format: (StaffID, Name, Password, Department, Role, Email)
     * Remember to downcast Account back to CareerStaffAccount when accessing privileges
     * 
     * @see Account
     * @see CareerStaffAccount
     * 
     * @param filepath Preferably use the actual filepath
     * @return List<Account>
     */
    public static List<Account> readStaffAccountsCSV(String filepath) {
        List<Account> accounts = new ArrayList<>();

        try {
            Scanner scStream = new Scanner(new File(filepath));
            String inputLine;
            String[] info;
            
            inputLine = scStream.nextLine();
            while (scStream.hasNext()) {
                inputLine = scStream.nextLine();
                info = inputLine.split("[,]");
                CareerCenterStaff newAccount = new CareerCenterStaff(
                    info[0],
                    info[1],
                    info[2],
                    info[3]
                );
                accounts.add(newAccount);
            }

            scStream.close();
            System.out.println(filepath + " read successfully.");
        }
        catch (FileNotFoundException e) {
            System.out.println(filepath + " not found.");
        }

        return accounts;
    }
}
