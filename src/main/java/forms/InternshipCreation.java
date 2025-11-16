package forms;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import model.Internship;
import enums.InternshipLevel;

public class InternshipCreation implements Base<Internship> {
    private Scanner sc;

    public InternshipCreation(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public Internship submit() {
        System.out.print("\nEnter internship title: ");
        String title  = sc.nextLine();

        System.out.print("Enter short description: ");
        String desc = sc.nextLine();

        int option;
        InternshipLevel level;
        outer : while (true) {
            try {
                System.out.print("Enter internship level (1 - Basic, 2 - Intermediate, 3 - Advanced): ");
                option = Integer.parseInt(sc.nextLine());
                switch (option) {
                    case 1 : level = InternshipLevel.Basic;        break outer;
                    case 2 : level = InternshipLevel.Intermediate; break outer;
                    case 3 : level = InternshipLevel.Advanced;     break outer;
                }
            }
            catch (NumberFormatException e) {}
        }
        
        System.out.print("Enter preferred major: ");
        String major = sc.nextLine();

        LocalDate openDate;
        while (true) {
            try {
                System.out.print("Enter opening date (YYYY-MM-DD): ");
                openDate = LocalDate.parse(sc.nextLine());
                break;
            }
            catch (DateTimeParseException e) {}
        }

        LocalDate closeDate;
        while (true) {
            try {
                System.out.print("Enter opening date (YYYY-MM-DD): ");
                closeDate = LocalDate.parse(sc.nextLine());
                break;
            }
            catch (DateTimeParseException e) {}
        }

        System.out.print("Enter company name: ");
        String companyName = sc.nextLine();

        int slots;
        while (true) {
            try {
                System.out.print("Enter number of slots: ");
                slots = Integer.parseInt(sc.nextLine());
                break;
            }
            catch (NumberFormatException e) {}
        }

        return new Internship(title, desc, level, major, openDate, closeDate, companyName, slots);
    }
}
