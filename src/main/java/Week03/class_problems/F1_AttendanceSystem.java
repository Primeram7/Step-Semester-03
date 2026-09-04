package Week03.class_problems;
public class F1_AttendanceSystem {

    static class SrmStudent {
        private String name;
        private String regNo;
        private int attendance;

        public SrmStudent(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        public void addAttendanceUpdate(int newAttendance) {
            this.attendance = newAttendance;
        }

        public boolean isEligible() {
            return attendance >= 75;
        }

        public String getName() {
            return name;
        }

        public int getAttendance() {
            return attendance;
        }

        // classAverage is static because it operates on a whole collection of
        // SrmStudent objects at once — it belongs to the class, not to any one
        // student. isEligible() is instance-level because its answer depends on
        // the attendance field of one specific object.
        public static double classAverage(SrmStudent[] students) {
            int total = 0;
            for (SrmStudent s : students) {
                total += s.getAttendance();
            }
            return (double) total / students.length;
        }
    }

    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA231", 82),
            new SrmStudent("Anitha", "RA232", 68),
            new SrmStudent("Karthik", "RA233", 91),
            new SrmStudent("Meera", "RA234", 74),
            new SrmStudent("Suresh", "RA235", 60)
        };

        for (SrmStudent s : students) {
            String status = s.isEligible() ? "Eligible" : "Detained";
            System.out.println(s.getName() + " - " + s.getAttendance() + "% - " + status);
        }

        System.out.println("Class average: " + SrmStudent.classAverage(students) + "%");
    }
}
