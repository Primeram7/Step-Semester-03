class EventTicket {
    private String attendeeId;
    private double basePrice;
    private double balanceDue;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().isEmpty() || attendeeId.length() < 4) {
            throw new IllegalArgumentException("Invalid attendee ID");
        }
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public void pay(double amount) {
        this.balanceDue -= amount;
    }

    public double getBalanceDue() {
        return this.balanceDue;
    }

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;
        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
}

public class Problem1 {
    public static void main(String[] args) {
        System.out.println(EventTicket.registerBatch(new String[]{"STU1", "ST1", "STU2", " ", "STU3"}, 500));
    }
}
