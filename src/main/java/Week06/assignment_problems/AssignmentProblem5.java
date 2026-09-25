class RaceEntry {
    private static int counter = 0;
    private final String entryCode;
    private double balanceDue;

    public RaceEntry(String bibNumber, double entryFee) {
        counter++;
        this.entryCode = "EC-" + counter;
        this.balanceDue = entryFee;
    }

    public void pay(double amount) {
        this.balanceDue -= amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public static int getBibCounter() {
        return counter;
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) return false;
        if (code.charAt(0) != 'M') return false;
        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) return false;
        }
        return Character.isUpperCase(code.charAt(4));
    }

    public static String settleNight(RaceEntry[] entries) {
        int processed = 0, nulls = 0, relays = 0, individuals = 0;
        for (RaceEntry e : entries) {
            if (e == null) { nulls++; continue; }
            processed++;
            if (e instanceof RelayTeamEntry) relays++;
            else individuals++;
        }
        return processed + " processed | " + nulls + " null skipped | " + relays + " relay | " + individuals + " individual";
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;
    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }
}

public class AssignmentProblem5 {
    public static void main(String[] args) {
        System.out.println("Discount M123A: " + RaceEntry.isValidDiscountCode("M123A"));

        RaceEntry r = new RaceEntry("BIB1001", 100);
        r.pay(10, "UPI");

        RelayTeamEntry relay = new RelayTeamEntry("BIB2001", 300, 4);
        RaceEntry[] entries = { r, null, relay };
        System.out.println(RaceEntry.settleNight(entries));
        System.out.println("Total Entries: " + RaceEntry.getBibCounter());
    }
}
