package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import enums.InternshipLevel;
import enums.InternshipStatus;
import model.Internship;
import model.InternshipApplication;
import model.Student;

public class CSVHandler {

    // --- READ METHODS ---

    public List<Student> readStudents(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; }
                String[] data = line.split(",");
                if (data.length < 6) continue;

                // Assumes CSV: StudentID,Name,Password,Year,Major,Email
                Student s = new Student(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]);
                students.add(s);
            }
        } catch (Exception e) {
            System.err.println("Error reading students: " + e.getMessage());
        }
        return students;
    }

    public List<Internship> readInternships(String filePath) {
        List<Internship> internships = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; }
                
                // Splitting by comma, but logic needed if description contains commas
                String[] data = line.split(",");
                
                // Strict check for your 13 columns
                if (data.length < 13) continue; 

                try {
                    int index = Integer.parseInt(data[0]);
                    String title = data[1];
                    InternshipLevel level = InternshipLevel.valueOf(data[2]); 
                    String major = data[3];
                    LocalDate open = parseDate(data[4]);
                    LocalDate close = parseDate(data[5]);
                    InternshipStatus status = InternshipStatus.valueOf(data[6]); 
                    String company = data[7];
                    int slots = Integer.parseInt(data[8]);
                    
                    List<String> reps = parseList(data[9]);

                    List<Integer> appIds = parseIntegerList(data[10]);

                    boolean vis = Boolean.parseBoolean(data[11]);
                    String desc = data[12];


                    Internship i = new Internship(index, title, desc, level, major, open, close, status, company, slots, reps, appIds, vis);
                    internships.add(i);
                } catch (Exception e) {
                    System.err.println("Skipping row due to error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading internships: " + e.getMessage());
        }
        return internships;
    }

    // --- WRITE METHODS ---

    public void writeStudents(String filePath, List<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            pw.println("StudentID,Name,Password,Year,Major,Email");
            for (Student s : students) {
                StringBuilder sb = new StringBuilder();
                sb.append(sanitize(s.getUserID())).append(",");
                sb.append(sanitize(s.getName())).append(",");
                sb.append(sanitize(s.getPassword())).append(",");
                sb.append(s.getYearOfStudy()).append(",");
                sb.append(sanitize(s.getMajor())).append(",");
                sb.append("email@placeholder.com"); // Placeholder if no getEmail() exists
                pw.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeInternships(String filePath, List<Internship> internships) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            // 13 Columns Header
            pw.println("Index,Title,Level,PreferredMajor,OpenDate,CloseDate,Status,CompanyName,Slots,Representatives,ApplicationsReceived,Visibility,Description");
            
            for (Internship i : internships) {
                StringBuilder sb = new StringBuilder();
                sb.append(i.getIndex()).append(",");
                sb.append(sanitize(i.getTitle())).append(",");
                sb.append(i.getInternshipLevel()).append(",");
                sb.append(sanitize(i.getPreferedMajor())).append(",");
                sb.append(i.getOpenDate()).append(",");
                sb.append(i.getClosingData()).append(",");
                sb.append(i.getStatus()).append(",");
                sb.append(sanitize(i.getCompanyName())).append(",");
                sb.append(i.getNumSlots()).append(",");
                
                sb.append(listToString(i.getCompanyRepresentatives())).append(",");
                sb.append(integerListToString(i.getApplicationsReceived())).append(",");
                
                sb.append(i.isVisibility()).append(",");
                sb.append(sanitize(i.getDescription()));

                pw.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- HELPERS ---

    private String sanitize(String input) { return input == null ? "null" : input.replace(",", ";"); }
    
    private String listToString(List<String> list) {
        if (list == null || list.isEmpty()) return "null";
        return String.join(";", list);
    }

    private String integerListToString(List<Integer> list) {
        if (list == null || list.isEmpty()) return "null";
        return list.stream().map(String::valueOf).collect(Collectors.joining(";"));
    }

    private List<String> parseList(String input) {
        if (input == null || input.equals("null") || input.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(input.split(";")));
    }

    private List<Integer> parseIntegerList(String input) {
        List<Integer> list = new ArrayList<>();
        if (input == null || input.equals("null") || input.isEmpty()) return list;
        for (String s : input.split(";")) {
            try { list.add(Integer.parseInt(s)); } catch (Exception e) {}
        }
        return list;
    }

    private LocalDate parseDate(String input) {
        try { return LocalDate.parse(input); } catch (Exception e) { return null; }
    }
}