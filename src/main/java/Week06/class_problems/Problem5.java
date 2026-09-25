class EventTicket {
    private static int counter = 1000;
    private final String ticketId;
    private double balanceDue;

    public EventTicket(double basePrice) {
        counter++;
        this.ticketId = "TCK-" + counter;
        this.balanceDue = basePrice;
    }

    public void pay(double amount) {
        this.balanceDue -= amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying " + amount + " via " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public String getTicketId() {
        return ticketId;
    }

    public static int getTicketsIssued() {
        return counter - 1000;
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) return false;
        if (code.charAt(0) != 'F') return false;
        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) return false;
        }
        return Character.isUpperCase(code.charAt(4));
    }

    public static String processNightlySettlement(EventTicket[] tickets) {
        int processed = 0, nulls = 0, groups = 0, individuals = 0;
        for (EventTicket t : tickets) {
            if (t == null) { nulls++; continue; }
            processed++;
            if (t instanceof GroupTicket) groups++;
            else individuals++;
        }
        return processed + " processed | " + nulls + " null skipped | " + groups + " group | " + individuals + " individual";
    }
}

class GroupTicket extends EventTicket {
    private int groupSize;
    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }
}

public class Problem5 {
    public static void main(String[] args) {
        EventTicket t1 = new EventTicket(500);
        System.out.println("ID: " + t1.getTicketId());
        System.out.println("Issued: " + EventTicket.getTicketsIssued());
        System.out.println("Promo F123A: " + EventTicket.isValidPromoCode("F123A"));

        t1.pay(200);
        t1.pay(200, "UPI");
        System.out.println("Balance: " + t1.getBalanceDue());

        EventTicket[] batch = { new GroupTicket(2000, 5), null, new EventTicket(500) };
        System.out.println(EventTicket.processNightlySettlement(batch));
    }
}
