package Week07.class_problems;

abstract class StaffMember {
    private double baseSalary;
    protected double bonusRate;

    public StaffMember(double baseSalary) {
        this(baseSalary, 0.10); // constructor chaining
    }

    public StaffMember(double baseSalary, double bonusRate) {
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.baseSalary = baseSalary;
        this.bonusRate = bonusRate;
    }

    public abstract double calculateBonus();

    public double getSalary() {
        return this.baseSalary;
    }

    public void setSalary(double baseSalary) {
        if (baseSalary >= 0) {
            this.baseSalary = baseSalary;
        } else {
            System.out.println("Rejected: Salary cannot be negative.");
        }
    }
}

interface Auditable {
    String auditRecord();
}

class TeamLead extends StaffMember implements Auditable {
    private int teamSize;

    public TeamLead(double baseSalary, int teamSize) {
        super(baseSalary);
        this.teamSize = teamSize;
    }

    public TeamLead(double baseSalary, double bonusRate, int teamSize) {
        super(baseSalary, bonusRate);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateBonus() {
        return getSalary() * bonusRate;
    }

    @Override
    public String auditRecord() {
        return "TeamLead audit: " + teamSize + " team members, salary $" + getSalary();
    }
}

public class Problem3 {
    public static String getAuditIfApplicable(StaffMember s) {
        if (s instanceof Auditable) {
            return ((Auditable) s).auditRecord();
        }
        return "No audit is required.";
    }

    public static void main(String[] args) {
        TeamLead t1 = new TeamLead(60000, 5);
        System.out.println("Bonus 1: " + t1.calculateBonus());

        TeamLead t2 = new TeamLead(60000, 0.20, 5);
        System.out.println("Bonus 2: " + t2.calculateBonus());

        t1.setSalary(-5000); // Should be rejected

        StaffMember ref = t1; // upcasting
        System.out.println(getAuditIfApplicable(ref));
    }
}
