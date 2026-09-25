import java.util.Arrays;

class EventTicket {
    private double balanceDue;
    private double[] lateFeeHistory = new double[10];
    private int feeCount = 0;

    public EventTicket(double basePrice) {
        this.balanceDue = basePrice;
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

class WorkshopTicket extends EventTicket {
    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Problem3 {
    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket(1200);
        w.pay(1200);
        w.applyLateFee(100);
        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(w.getLateFeeHistory()));
    }
}
