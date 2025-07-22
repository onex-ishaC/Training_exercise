package Core_java;
public class Employee {
    private String name;
    private int ticketsClosed;
    private int disciplinaryActions;
    private int ticketsRemaining;
    private double selfReview;
    private double managerReview;
    private double coworkerReview;
    private double innovation;
    private double punctuality;
    private double currentSalaryLPA;
    private String joiningDate;

    public Employee(String name, int ticketsClosed, int disciplinaryActions, int ticketsRemaining,
                    double selfReview, double managerReview, double coworkerReview,
                    double innovation, double punctuality, double currentSalaryLPA, String joiningDate) {
        this.name = name;
        this.ticketsClosed = ticketsClosed;
        this.disciplinaryActions = disciplinaryActions;
        this.ticketsRemaining = ticketsRemaining;
        this.selfReview = selfReview;
        this.managerReview = managerReview;
        this.coworkerReview = coworkerReview;
        this.innovation = innovation;
        this.punctuality = punctuality;
        this.currentSalaryLPA = currentSalaryLPA;
        this.joiningDate = joiningDate;
    }

    // Getters
    public String getName() { return name; }
    public int getTicketsClosed() { return ticketsClosed; }
    public int getDisciplinaryActions() { return disciplinaryActions; }
    public int getTicketsRemaining() { return ticketsRemaining; }
    public double getSelfReview() { return selfReview; }
    public double getManagerReview() { return managerReview; }
    public double getCoworkerReview() { return coworkerReview; }
    public double getInnovation() { return innovation; }
    public double getPunctuality() { return punctuality; }
    public double getCurrentSalaryLPA() { return currentSalaryLPA; }
    public String getJoiningDate() { return joiningDate; }
}
