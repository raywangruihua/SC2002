package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles writing security and activity logs to AuditLog.csv
 */
public class AuditLogger {
    private static final String AUDIT_FILE = "AuditLog.csv";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String eventType, String userID, String details) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(AUDIT_FILE, true))) {
            String timestamp = LocalDateTime.now().format(dtf);
            // CSV Format: Timestamp, EventType, UserID, Details
            bw.write(String.format("%s,%s,%s,%s", timestamp, eventType, userID, details));
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to audit log: " + e.getMessage());
        }
    }
}