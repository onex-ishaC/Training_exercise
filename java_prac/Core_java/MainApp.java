package Core_java;
import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Employee> employeeMap = new HashMap<>();

        while (true) {
            System.out.println("\n=== Employee Appraisal System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Generate Appraisal Report");
            System.out.println("3. Check if Report is Generated");
            System.out.println("4. List All Generated Reports");
            System.out.println("5. View Report by Name");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Tickets Closed: ");
                    int closed = sc.nextInt();
                    System.out.print("Disciplinary Actions: ");
                    int disc = sc.nextInt();
                    System.out.print("Tickets Remaining: ");
                    int remaining = sc.nextInt();
                    System.out.print("Self Review (out of 10): ");
                    double self = sc.nextDouble();
                    System.out.print("Manager Review (out of 10): ");
                    double mgr = sc.nextDouble();
                    System.out.print("Coworker Review (out of 10): ");
                    double coworker = sc.nextDouble();
                    System.out.print("Innovation (out of 10): ");
                    double innov = sc.nextDouble();
                    System.out.print("Punctuality (out of 10): ");
                    double punct = sc.nextDouble();
                    System.out.print("Current Salary (LPA): ");
                    double salary = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Joining Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();

                    Employee emp = new Employee(name, closed, disc, remaining, self, mgr, coworker, innov, punct, salary, date);
                    employeeMap.put(name, emp);
                    System.out.println("Employee added successfully.");
                }
                case 2 -> {
                    System.out.print("Enter employee name: ");
                    String name = sc.nextLine();
                    Employee emp = employeeMap.get(name);
                    if (emp == null) {
                        System.out.println("Employee not found.");
                    } else if (EmployeeService.isReportGenerated(name)) {
                        System.out.println("Report already generated.");
                    } else {
                        AppraisalUtils.generateReport(emp);
                        EmployeeService.markReportGenerated(name);
                    }
                }
                case 3 -> {
                    System.out.print("Enter employee name: ");
                    String name = sc.nextLine();
                    if (EmployeeService.isReportGenerated(name)) {
                        System.out.println("Report is generated for " + name);
                    } else {
                        System.out.println("Report not generated for " + name);
                    }
                }
                case 4 -> EmployeeService.listGeneratedReports();
                case 5 -> {
                    System.out.print("Enter employee name: ");
                    String name = sc.nextLine();
                    Employee emp = employeeMap.get(name);
                    if (emp != null && EmployeeService.isReportGenerated(name)) {
                        AppraisalUtils.generateReport(emp);
                    } else {
                        System.out.println("Either employee not found or report not yet generated.");
                    }
                }
                case 6 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
