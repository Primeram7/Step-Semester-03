package Week07.assignment_problems;

interface Exportable {
    String exportData();
}

class ExportTracker {
    private static int totalExports = 0;
    public static void increment() { totalExports++; }
    public static int getTotalExports() { return totalExports; }
}

class ReportGenerator implements Exportable {
    private String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        ExportTracker.increment();
        return "Exported report: " + reportName;
    }
}

class UserProfile implements Exportable {
    private String username;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        ExportTracker.increment();
        return "Exported profile: " + username;
    }
}

public class AssignmentProblem2 {
    public static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    public static int getTotalExports() {
        return ExportTracker.getTotalExports();
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        Exportable[] items = { r, u };
        exportAll(items);
        System.out.println("Total Exports: " + getTotalExports());
    }
}
