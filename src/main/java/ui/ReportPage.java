package ui;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import enums.InternshipStatus;
import model.Internship;

/**
 * Generates internship report for career center staff
 * Can filter internships based on : Status, Preferred Major, Internship Leve, etc...
 * Can also sort internships based on the same parameters
 */
public class ReportPage {
    List<Internship> originalList;
    List<Internship> displayList;
    Scanner          sc;

    public ReportPage(List<Internship> internships, Scanner sc) {
        this.originalList = internships;
        this.displayList  = internships;
        this.sc           = sc;
    }

    public void start() {
        display();

        int option = -1;
        while (true) {
            try {
                System.out.print("\nEnter option: ");
                option = Integer.parseInt(sc.nextLine());
                break;
            }
            catch (NumberFormatException e) {System.out.println("Invalid input.");}

            switch (option) {
                case 1 -> displayReport();
                case 2 -> filter();
                case 3 -> sort();
                case 4 -> displayList = originalList;
                case 5 -> {return;}
                default -> System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Make this look a bit nicer in the future
     */
    public void displayReport() {
        for (Internship i : displayList) {System.out.println(i);}
    }

    /**
     * Filter list based on user input
     */
    public void filter() {
        System.out.println("\n-----Filter by-----");
        displayOptions();

        int option = -1;
        while (true) {
            try {
                System.out.print("\nEnter option: ");
                option = Integer.parseInt(sc.nextLine());
                break;
            }
            catch (NumberFormatException e) {System.out.println("Invalid input.");}

            switch (option) {
                case 1 -> filterByStatus();
                case 2 -> filterByMajor();
                case 3 -> filterByInternshipLevel();
                case 4 -> filterByCompanyName();
                case 5 -> filterByCurrentlyOpen();
                case 6 -> filterByApplicationsReceived();
                case 7 -> {return;}
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public void filterByStatus() {
        System.out.print(
            "\n1. Pending\n" +
            "2. Approved\n" +
            "3. Rejected\n" +
            "4. Filled\n" +
            "5. Return\n"
        );

        int option = -1;
        while (true) {
            try {
                System.out.print("\nEnter option: ");
                option = Integer.parseInt(sc.nextLine());
                break;
            }
            catch (NumberFormatException e) {System.out.println("Invalid input.");}

            switch (option) {
                case 1 -> displayList = displayList.stream().filter(i -> i.getStatus() == InternshipStatus.PENDING).collect(Collectors.toList());
                case 2 -> displayList = displayList.stream().filter(i -> i.getStatus() == InternshipStatus.APPROVED).collect(Collectors.toList());
                case 3 -> displayList = displayList.stream().filter(i -> i.getStatus() == InternshipStatus.REJECTED).collect(Collectors.toList());
                case 4 -> displayList = displayList.stream().filter(i -> i.getStatus() == InternshipStatus.FILLED).collect(Collectors.toList());
                case 5 -> {return;}
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public void filterByMajor() {
        System.out.print("\nEnter major: ");
        String major = sc.nextLine();
        displayList = displayList.stream().filter(i -> i.getPreferedMajor().equalsIgnoreCase(major)).collect(Collectors.toList());
    }

    /**
     * Filter internships by their level, e.g., "Basic", "Advanced", etc.
     */
    public void filterByInternshipLevel() {
        System.out.print("\nEnter internship level (e.g., Basic, Intermediate, Advanced): ");
        String level = sc.nextLine();
        displayList = displayList.stream()
            .filter(i -> i.getInternshipLevel().name().equalsIgnoreCase(level))
            .collect(Collectors.toList());
    }

    /**
     * Filter internships by the company name.
     */
    public void filterByCompanyName() {
        System.out.print("\nEnter company name: ");
        String name = sc.nextLine();
        displayList = displayList.stream()
            .filter(i -> i.getCompanyName().equalsIgnoreCase(name))
            .collect(Collectors.toList());
    }

    /**
     * Filter internships with status 'approved' and visibility true (currently open).
     */
    public void filterByCurrentlyOpen() {
        System.out.print("\nFiltering visible internships with status 'approved'");
        displayList = displayList.stream()
            .filter(i -> i.getStatus().name().equalsIgnoreCase("APPROVED") && i.getVisibility())
            .collect(Collectors.toList());
    }

    





    public void display() {
        System.out.print(
            "-----Report Page------\n" +
            "1. Display report\n" +
            "2. Filter\n" +
            "3. Sort\n" +
            "4. Reset\n" +
            "5. Return\n"
        );
    }

    public void displayOptions() {
        System.out.print(
            "1. Status\n" +
            "2. Preferred Major\n" +
            "3. Internship Level\n" +
            "4. Company name\n" +
            "5. Currently open\n" +
            "6. Applications received\n" +
            "7. Return\n"
        );
    }
}
