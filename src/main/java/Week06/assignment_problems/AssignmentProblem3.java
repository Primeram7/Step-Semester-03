import java.util.Arrays;

class RaceEntry {
    private double balanceDue;
    private double[] lateFeeHistory = new double[10];
    private int feeCount = 0;

    public RaceEntry(String bibNumber, double entryFee) {
        this.balanceDue = entryFee;
    }

    protected void applyLateFee(double amount) {
        if (feeCount < 10) lateFeeHistory[feeCount++] = amount;
        this.balanceDue += amount;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public void pay(double amount) {
        this.balanceDue -= amount;
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }
}

class RunnerEntry extends RaceEntry {
    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class AssignmentProblem3 {
    public static void main(String[] args) {
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println("Balance Due: " + r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        history[0] = 999;
        System.out.println("History after tampering: " + Arrays.toString(r.getLateFeeHistory()));
    }
}
