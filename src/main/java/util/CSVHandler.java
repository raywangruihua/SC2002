package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import enums.InternshipLevel;
import enums.InternshipStatus;
import model.*;       

public class CSVHandler {

    // --- READ METHODS ---

    public List<Student> readStudents(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; }
                
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for(int i=0; i<data.length; i++) data[i] = clean(data[i]);

                if (data.length < 5) continue; 

                Student s = new Student(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]);
                students.add(s);
            }
        } catch (Exception e) {
            System.err.println("Error reading students: " + e.getMessage());
        }
        return students;
    }

    public List<CareerCenterStaff> readStaffs(String filePath) {
        List<CareerCenterStaff> staffs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; }
                
                // FIX: Regex split
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for(int i=0; i<data.length; i++) data[i] = clean(data[i]);

                if (data.length < 4) continue;

                // ID, Name, Password, Department
                CareerCenterStaff s = new CareerCenterStaff(data[0], data[1], data[2], data[3]);
                staffs.add(s);
            }
        } catch (Exception e) {
            System.err.println("Error reading staff: " + e.getMessage());
        }
        return staffs;
    }

    public List<CompanyRep> readCompanyReps(String filePath) {
        List<CompanyRep> reps = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; }
                
                // FIX: Regex split
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for(int i=0; i<data.length; i++) data[i] = clean(data[i]);
                
                if (data.length < 6) continue;

                CompanyRep r = new CompanyRep(
                    data[0], data[1], data[2], data[3], data[4], data[5]
                );
                reps.add(r);
            }
        } catch (Exception e) {
            System.err.println("Error reading company reps: " + e.getMessage());
        }
        return reps;
    }

    public List<Internship> readInternships(String filePath) {
        List<Internship> internships = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) { isHeader = false; continue; }
                
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for(int i=0; i<data.length; i++) data[i] = clean(data[i]);

                if (data.length < 13) continue; 

                try {
                    int index = Integer.parseInt(data[0]);
                    String title = data[1];
                    
                    InternshipLevel level = parseLevel(data[2]); 
                    String major = data[3];
                    
                    LocalDate open = parseDate(data[4]); 
                    LocalDate close = parseDate(data[5]);
                    
                    InternshipStatus status = parseStatus(data[6]); 
                    
                    String company = data[7];
                    int slots = Integer.parseInt(data[8]);
                    
                    List<String> reps = parseList(data[9]);
                    List<Integer> appIds = parseIntegerList(data[10]);
                    
                    boolean vis = Boolean.parseBoolean(data[11]);
                    String desc = data[12];

                    Internship i = new Internship(index, title, desc, level, major, open, close, status, company, slots, reps, appIds, vis);
                    internships.add(i);
                } catch (Exception e) {
                    System.err.println("Skipping row due to parsing error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading internships: " + e.getMessage());
        }
        return internships;
    }

    public List<Company> readCompanies(String companiesFilePath) {
        List<Company> companies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(companiesFilePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {isHeader = false; continue;}
                
                // FIX: Regex split
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for(int i=0; i<data.length; i++) data[i] = clean(data[i]);

                if (data.length < 2) continue;

                try {
                    String companyName = data[0];
                    int numInternships = Integer.parseInt(data[1]);
                    List<String> employees = parseList(data[2]);

                    Company newCompany = new Company(companyName, numInternships, employees);
                    companies.add(newCompany);
                } catch (Exception e) {
                    System.err.println("Skipping row due to parsing error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading companies: " + e.getMessage());
        }
        return companies;
    }

    // --- WRITE METHODS (Unchanged) ---

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
                sb.append("email@placeholder.com"); 
                pw.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeInternships(String filePath, List<Internship> internships) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
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
    
    public void writeStaffs(String filePath, List<CareerCenterStaff> staffs) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            pw.println("ID,Name,Password,Department");
            for (CareerCenterStaff s : staffs) {
                StringBuilder sb = new StringBuilder();
                sb.append(sanitize(s.getUserID())).append(",");
                sb.append(sanitize(s.getName())).append(",");
                sb.append(sanitize(s.getPassword())).append(",");
                sb.append(sanitize(s.getDepartment()));
                pw.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCompanyReps(String filePath, List<CompanyRep> reps) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath, false))) {
            pw.println("ID,Name,Password,CompanyName,Department,Position");
            for (CompanyRep r : reps) {
                StringBuilder sb = new StringBuilder();
                sb.append(sanitize(r.getUserID())).append(",");
                sb.append(sanitize(r.getName())).append(",");
                sb.append(sanitize(r.getPassword())).append(",");
                sb.append(sanitize(r.getCompanyName())).append(",");
                sb.append(sanitize(r.getDepartment())).append(",");
                sb.append(sanitize(r.getPosition()));
                pw.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCompanies(String filepath, List<Company> companies) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filepath)))) {
            pw.println("Company Name,Number of Internships,Employees");
            for (Company c : companies) {
                StringBuilder sb = new StringBuilder();
                sb.append(c.getName()).append(",");
                sb.append(c.getNumInternships()).append(",");
                for (String employee : c.getEmployees()) {
                    sb.append(employee).append(";");
                }
                pw.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ---  HELPERS ---

    private String clean(String input) {
        if (input != null && input.startsWith("\"") && input.endsWith("\"")) {
            return input.substring(1, input.length() - 1);
        }
        return input;
    }

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
        return new ArrayList<>(Arrays.asList(input.split("[:/;]"))); 
    }

    private List<Integer> parseIntegerList(String input) {
        List<Integer> list = new ArrayList<>();
        if (input == null || input.equals("null") || input.isEmpty()) return list;
        for (String s : input.split("[:/;]")) {
            try { list.add(Integer.parseInt(s)); } catch (Exception e) {}
        }
        return list;
    }

    private LocalDate parseDate(String input) {
        if (input == null || input.isEmpty()) return null;
        try {
            return LocalDate.parse(input); 
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(input, DateTimeFormatter.BASIC_ISO_DATE); 
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }

    private InternshipStatus parseStatus(String input) {
        try {
            return InternshipStatus.valueOf(input); 
        } catch (IllegalArgumentException e) {
            return InternshipStatus.valueOf(input.toUpperCase()); 
        }
    }

    private InternshipLevel parseLevel(String input) {
        try {
            return InternshipLevel.valueOf(input); 
        } catch (IllegalArgumentException e) {
            return InternshipLevel.Basic; 
        }
    }
}