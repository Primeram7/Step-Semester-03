class RaceEntry {
    protected double balanceDue;

    public RaceEntry(String bibNumber, double entryFee) {
        this.balanceDue = entryFee;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public String announce() {
        return "Race Entry | Balance: " + balanceDue;
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + "BIB2001" + " | Category: " + category + " | Balance: " + getBalanceDue();
    }
}

class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    @Override
    public String announce() {
        return "Elite Runner | Bib: " + "BIB3001" + " | Category: " + "Elite Full Marathon" + " | Sponsor Bonus: " + sponsorBonus + " | Balance: " + getBalanceDue();
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + "BIB4001" + " | Team Size: " + teamSize + " | Balance: " + getBalanceDue();
    }
}

public class AssignmentProblem2 {
    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) return "Multilevel descendant (3 generations deep)";
        if (entry instanceof RelayTeamEntry) return "Hierarchical sibling (independent branch)";
        return "Other";
    }

    public static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0;
        for (RaceEntry e : entries) total += e.getBalanceDue();
        return total;
    }

    public static void main(String[] args) {
        RunnerEntry runner = new RunnerEntry("BIB2001", 80, "Open 10K");
        EliteRunnerEntry elite = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relay = new RelayTeamEntry("BIB4001", 300, 4);

        System.out.println(runner.announce());
        System.out.println(elite.announce());
        System.out.println(relay.announce());
        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(relay));
        System.out.println("Total Balance: " + getTotalBalanceDue(new RaceEntry[]{runner, elite, relay}));
    }
}
