package forms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import enums.InternshipLevel;
import enums.InternshipStatus;
import model.Internship;

public class InternshipCreation {
    private Scanner sc;
    private String companyName;
    private String repName;

    /**
    * Constructor to create InternshipCreation object
    *
    * @param sc is the Scanner object created used throughout this class
    * @param companyName is the name of company that is creating a new internship
    * @param repName is the name of the company representative that created the new internship
    */
    public InternshipCreation(Scanner sc, String companyName, String repName) {
        this.sc = sc;
        this.companyName = companyName;
        this.repName = repName;
    }

    /**
    * Submits new internship created by company representative by providing prompts
    * Displays error when wrong value/parameter
    */
    public Internship submit() {
        try {
            System.out.println("\n--- Create New Internship ---");
            
            System.out.print("Enter Internship Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Description (e.g. Python, Java): ");
            String description = sc.nextLine();

            System.out.print("Enter Internship Level (Basic/Intermediate/Advanced): ");
            String levelStr = sc.nextLine();
            InternshipLevel level = InternshipLevel.Basic;
            try {
                // Capitalize first letter to match Enum
                level = InternshipLevel.valueOf(levelStr.substring(0, 1).toUpperCase() + levelStr.substring(1).toLowerCase());
            } catch (Exception e) {
                System.out.println("Invalid level. Defaulting to Basic.");
            }

            System.out.print("Enter Preferred Major: ");
            String major = sc.nextLine();

            int slots = 0;
            while (true) {
                try {
                    System.out.print("Enter Number of Slots: ");
                    slots = Integer.parseInt(sc.nextLine());
                    if (slots > 0 && slots <= 10) break;
                    System.out.println("Slots must be between 1 and 10.");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }

            // Auto-set dates for simplicity (Open: Now, Close: +1 Month)
            LocalDate openDate = LocalDate.now();
            LocalDate closeDate = openDate.plusMonths(1);

            // Create Reps List containing the current user
            List<String> reps = new ArrayList<>();
            reps.add(repName);

            // Create Empty Applications List
            List<Integer> appIds = new ArrayList<>();

            // Create the Internship Object
            // Index is set to -1 here; InternshipManager.submitInternship() will fix it later
            return new Internship(
                -1, 
                title, 
                description, 
                level, 
                major, 
                openDate, 
                closeDate, 
                InternshipStatus.PENDING, 
                companyName, 
                slots, 
                reps, 
                appIds, 
                true // Default Visibility: true
            );

        } catch (Exception e) {
            System.out.println("Error creating internship: " + e.getMessage());
            return null;
        }
    }
}