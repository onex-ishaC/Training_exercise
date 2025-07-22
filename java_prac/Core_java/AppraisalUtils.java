package Core_java;
import java.time.LocalDate;

public class AppraisalUtils {

    public static int getYearsWorked(String joiningDate) {
        LocalDate joinDate = LocalDate.parse(joiningDate);
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() - joinDate.getYear();
    }

    public static double calculateAppraisalPercentage(Employee emp) {
        double avgReviewScore = (emp.getSelfReview() + emp.getManagerReview() + emp.getCoworkerReview()) / 3;
        double ticketEfficiency = emp.getTicketsClosed() + emp.getTicketsRemaining() > 0
                ? (emp.getTicketsClosed() * 1.0 / (emp.getTicketsClosed() + emp.getTicketsRemaining())) * 10 : 0;

        double performanceScore = avgReviewScore * 0.4 +
                                  emp.getInnovation() * 0.2 +
                                  emp.getPunctuality() * 0.1 +
                                  ticketEfficiency * 0.2;

        if (getYearsWorked(emp.getJoiningDate()) > 3) {
            performanceScore += 1;
        }

        if (emp.getDisciplinaryActions() > 0) {
            performanceScore -= emp.getDisciplinaryActions() * 0.5;
        }

        performanceScore = Math.max(0, Math.min(10, performanceScore));
        int roundedScore = (int) Math.floor(performanceScore);

        return switch (roundedScore) {
            case 9, 10 -> 25.0;
            case 8 -> 20.0;
            case 7 -> 15.0;
            case 6 -> 10.0;
            case 5 -> 5.0;
            default -> 0.0;
        };
    }

    public static void generateReport(Employee emp) {
        double appraisalPercent = calculateAppraisalPercentage(emp);
        double newSalary = emp.getCurrentSalaryLPA() * (1 + appraisalPercent / 100);

        System.out.println("\n===== Employee Appraisal Report =====");
        System.out.println("Name: " + emp.getName());
        System.out.println("Joining Date: " + emp.getJoiningDate());
        System.out.println("Years Worked: " + getYearsWorked(emp.getJoiningDate()));
        System.out.println("Current Salary: " + emp.getCurrentSalaryLPA() + " LPA");
        System.out.println("Appraisal Percentage: " + appraisalPercent + " %");
        System.out.println("New Salary: " + String.format("%.2f", newSalary) + " LPA");
        System.out.println("=====================================");
    }
}
