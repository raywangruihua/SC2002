package src.main.java.util;

import java.util.List;
import src.main.java.model.Account;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import src.main.java.model.Student;

/**
 * Utility class that reads files
 */
public class Read {
    /**
     * Student accounts format: (StudentID, Name, Major, Year, Email)
     * Assigns default password to account
     * Remember to upcast Account back to Student when accessing
     * 
     * @see Student
     * @see Account
     * 
     * @param filepath Preferably use actual path of file
     * @return List<Account>
     */
    public static List<Account> readStudentAccountsCSV(String filepath) {
        List<Account> accounts = new ArrayList<>();

        try {
            Scanner scStream = new Scanner(new File(filepath));
            String inputLine;
            String[] studentInfo;
            
            while (scStream.hasNext()) {
                inputLine = scStream.nextLine();
                studentInfo = inputLine.split("[,]");
                Student newStudent = new Student(studentInfo[0],
                                                 studentInfo[1],
                                                 Integer.parseInt(studentInfo[3]),
                                                 studentInfo[2]
                );
                accounts.add(newStudent);
            }

            scStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File Error!" + e.getMessage());
        }

        return accounts;
    }
}
