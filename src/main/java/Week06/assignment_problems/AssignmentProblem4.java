class RaceEntry {
    protected double balanceDue;
    public RaceEntry(String bibNumber, double entryFee) { this.balanceDue = entryFee; }
    public String announce() { return "Race Entry | Balance: " + balanceDue; }
    public double getBalanceDue() { return balanceDue; }
}

class RunnerEntry extends RaceEntry {
    private String category;
    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }
    @Override
    public String announce() { return "Runner Entry | Bib: BIB2001 | Category: " + category + " | Balance: " + balanceDue; }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;
    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }
    public int getTeamSize() { return teamSize; }
    @Override
    public String announce() { return "Relay Team | Bib: BIB4001 | Team Size: " + teamSize + " | Balance: " + balanceDue; }
}

public class AssignmentProblem4 {
    public static String announceAll(RaceEntry[] entries) {
        StringBuilder sb = new StringBuilder();
        for (RaceEntry e : entries) {
            sb.append(e.announce()).append(" | ");
            if (e instanceof RelayTeamEntry) {
                RelayTeamEntry rte = (RelayTeamEntry) e;
                sb.append("[Team size via downcast: ").append(rte.getTeamSize()).append("] | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RunnerEntry runner = new RunnerEntry("BIB2001", 80, "Open 10K");
        RelayTeamEntry relay = new RelayTeamEntry("BIB4001", 300, 4);
        RaceEntry[] fleet = { runner, relay };
        System.out.println(announceAll(fleet));
    }
}
