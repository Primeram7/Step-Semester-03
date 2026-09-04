package Week03.class_problems;
public class F4_StaticBoundary {

    // Broken version: every field is static, so there is only ONE copy of
    // name/regNo/attendance shared by the whole class. Every "new" call
    // overwrites the same shared slots instead of creating independent state.
    // name        — wrong: each student needs their own name.
    // regNo       — wrong: each student needs a unique registration number.
    // attendance  — wrong: attendance is per-student data, not shared data.
    static class BrokenStudent {
        static String name;
        static String regNo;
        static int attendance;

        public BrokenStudent(String name, String regNo, int attendance) {
            BrokenStudent.name = name;
            BrokenStudent.regNo = regNo;
            BrokenStudent.attendance = attendance;
        }
    }

    // Fixed version: name/regNo/attendance are instance fields (one copy per
    // object). university and admissionCount are genuinely shared across every
    // student, so those stay static.
    static class SrmStudent {
        private String name;
        private String regNo;
        private int attendance;

        static String university = "SRMIST";
        static int admissionCount = 0;

        public SrmStudent(String name, int attendance) {
            this.name = name;
            admissionCount++;
            this.regNo = "RA23110030101" + admissionCount;
            this.attendance = attendance;
        }

        public void printIdCard() {
            System.out.println(name + " | " + regNo);
        }

        public static void printTotalAdmissions() {
            System.out.println("Students admitted so far: " + admissionCount);
        }
    }

    public static void main(String[] args) {
        BrokenStudent b1 = new BrokenStudent("Ravi", "RA231", 82);
        BrokenStudent b2 = new BrokenStudent("Meera", "RA232", 74);
        System.out.println(BrokenStudent.name);
        System.out.println(BrokenStudent.name);
        System.out.println("(Ravi's data was overwritten — both students now show \"" + BrokenStudent.name + "\")");

        System.out.println();

        SrmStudent s1 = new SrmStudent("Ravi", 82);
        SrmStudent s2 = new SrmStudent("Meera", 74);
        s1.printIdCard();
        s2.printIdCard();
        SrmStudent.printTotalAdmissions();
    }
}
