package util;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import model.*;
import enums.*;

/**
 * Utility class that reads files
 */
public class Read {
    /**
     * Student accounts format: (StudentID, Name, Password, Year, Major, Email)
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

    /**
     * Company Representative format : (UserID, Name, Password, Company Name, Department, Position)
     * 
     * @param filepath preferably use actual filepath
     * @return List<Account>
     */
    public static List<Account> readCompanyRepAccountsCSV(String filepath) {
        List<Account> accounts = new ArrayList<>();

        try {
            Scanner scStream = new Scanner(new File(filepath));
            String inputLine;
            String[] info;
            
            inputLine = scStream.nextLine();
            while (scStream.hasNext()) {
                inputLine = scStream.nextLine();
                info = inputLine.split("[,]");
                CompanyRep newAccount = new CompanyRep(
                    info[0],
                    info[1],
                    info[2],
                    info[3],
                    info[4],
                    info[5]
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
     * Internship format : (index, title, internship level, preferred major, open date, close date,
     * status, company name, slots, company representatives, applications received, visibility, description)
     * @param filepath
     * @return
     */
    public static List<Internship> readInternshipsCSV(String filepath) {
        List<Internship> internships = new ArrayList<>();

        try {
            Scanner scStream = new Scanner(new File(filepath));
            String inputLine;
            String[] info;
            
            inputLine = scStream.nextLine();
            while (scStream.hasNext()) {
                inputLine = scStream.nextLine();
                info = inputLine.split("[,]");
                Internship internship = new Internship(
                    Integer.parseInt(info[0]),
                    info[1],
                    info[12],
                    InternshipLevel.valueOf(info[2]),
                    info[3],
                    LocalDate.parse(info[4]),
                    LocalDate.parse(info[5]),
                    InternshipStatus.valueOf(info[6]),
                    info[7],
                    Integer.parseInt(info[8]),
                    Arrays.asList(info[9].split("[/]")),
                    Arrays.stream(info[10].split("[/]"))
                          .map(Integer::parseInt)
                          .collect(Collectors.toList()),
                    Boolean.parseBoolean(info[11])
                );
                internships.add(internship);
            }

            scStream.close();
            System.out.println(filepath + " read successfully.");
        }
        catch (FileNotFoundException e) {
            System.out.println(filepath + " not found.");
        }
        catch (Exception e) {
            System.out.println("An error occured while reading " + filepath + ".");
        }

        return internships;
    }

    /**
     * Internship Application format : (index, title, internship index, student id, student name, status)
     * @param filepath
     * @return
     */
    public static List<InternshipApplication> readApplicationsCSV(String filepath) {
        List<InternshipApplication> applications = new ArrayList<>();

        try {
            Scanner scStream = new Scanner(new File(filepath));
            String inputLine;
            String[] info;
            
            inputLine = scStream.nextLine();
            while (scStream.hasNext()) {
                inputLine = scStream.nextLine();
                info = inputLine.split("[,]");
                InternshipApplication application = new InternshipApplication(
                    Integer.parseInt(info[0]),
                    info[1],
                    Integer.parseInt(info[2]),
                    info[3],
                    info[4],
                    ApplicationStatus.valueOf(info[5])
                );
                applications.add(application);
            }

            scStream.close();
            System.out.println(filepath + " read successfully.");
        }
        catch (FileNotFoundException e) {
            System.out.println(filepath + " not found.");
        }
        catch (Exception e) {
            System.out.println("An error occured while reading " + filepath + ".");
        }

        return applications;
    }
}
