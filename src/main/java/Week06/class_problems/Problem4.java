class EventTicket {
    protected double balanceDue;
    public EventTicket(double basePrice) { this.balanceDue = basePrice; }
    public String printTicket() { return "Standard | Balance: " + balanceDue; }
    public double getBalanceDue() { return balanceDue; }
}

class WorkshopTicket extends EventTicket {
    private String track;
    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }
    public String getTrack() { return track; }
    @Override
    public String printTicket() { return "Workshop | Track: " + track + " | Balance: " + balanceDue; }
}

public class Problem4 {
    public static String batchPrint(EventTicket[] tickets) {
        StringBuilder sb = new StringBuilder();
        for (EventTicket t : tickets) {
            sb.append(t.printTicket()).append(" | ");
            if (t instanceof WorkshopTicket) {
                WorkshopTicket wt = (WorkshopTicket) t;
                sb.append("[Track via downcast: ").append(wt.getTrack()).append("] | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        EventTicket[] tickets = { new EventTicket(500), new WorkshopTicket(1200, "AI/ML") };
        System.out.println(batchPrint(tickets));
    }
}
