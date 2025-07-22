package Core_java;
import java.util.HashMap;
import java.util.Map;

public class EmployeeService {
    private static final Map<String, Boolean> reportStatus = new HashMap<>();

    public static boolean isReportGenerated(String name) {
        return reportStatus.getOrDefault(name, false);
    }

    public static void markReportGenerated(String name) {
        reportStatus.put(name, true);
    }

    public static void listGeneratedReports() {
        System.out.println("\nEmployees with generated reports:");
        boolean found = false;
        for (Map.Entry<String, Boolean> entry : reportStatus.entrySet()) {
            if (entry.getValue()) {
                System.out.println("- " + entry.getKey());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No reports generated yet.");
        }
    }
}
