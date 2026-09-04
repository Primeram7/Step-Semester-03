package Week03.assignment_problems;
public class Company1 {

    // ================= EMPLOYEE =================

    static class Employee {

        private int empid;
        String ename;
        double salary;

        Employee(int id, String name, double s) {
            empid = id;
            ename = name;
            salary = s;
        }

        double getsalary() {
            return salary;
        }
    }


    // ================= MANAGER =================

    static class Manager extends Employee {

        private double teamBonus;

        Manager(int id, String name, double s, double bonus) {
            super(id, name, s);
            teamBonus = bonus;
        }

        double EffectiveSalary() {
            return getsalary() + teamBonus;
        }
    }


    // ================= INTERN =================

    static class Intern extends Employee {

        private double stipend;

        Intern(int id, String name, double s, double stipend) {
            super(id, name, s);
            this.stipend = stipend;
        }

        double EffectiveSalary() {
            return (getsalary() < stipend ? getsalary() : stipend);
        }
    }


    // ================= PARKING SLOT =================

    static class ParkingSlot {

        String slotNo;
        int capacity;
        int occupiedCount;

        ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        void allot(String vehicleNo) {
            if (occupiedCount < capacity) {
                occupiedCount++;
                System.out.println(
                    vehicleNo + " allotted to slot " + slotNo
                );
            }
        }

        static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

            for (ParkingSlot slot : slots) {

                if (slot.occupiedCount < slot.capacity) {
                    return slot;
                }
            }

            return null;
        }

        static void safeAllot(ParkingSlot[] slots, String vehicleNo) {

            ParkingSlot slot = findAvailableSlot(slots);

            if (slot != null) {
                slot.allot(vehicleNo);
            }
            else {
                System.out.println(
                    "No slots available for " + vehicleNo
                );
            }
        }
    }


    // ================= COMPANY EMPLOYEE RECORD =================

    static class CompanyEmployeeRecord {

        String name;
        String empId;

        // Objects inside another object
        Employee employee;
        ParkingSlot slot;

        // Shared by all records
        static int totalRecords = 0;


        CompanyEmployeeRecord(String name,
                              String empId,
                              Employee employee,
                              ParkingSlot slot) {

            this.name = name;
            this.empId = empId;
            this.employee = employee;
            this.slot = slot;

            totalRecords++;
        }


        String fullProfile() {

            double pay;

            // Manager has special salary calculation
            if (employee instanceof Manager) {

                pay = ((Manager) employee).EffectiveSalary();

            }
            // Intern has special salary calculation
            else if (employee instanceof Intern) {

                pay = ((Intern) employee).EffectiveSalary();

            }
            // Normal Employee
            else {

                pay = employee.getsalary();
            }


            String parking;

            // Check whether parking is assigned
            if (slot != null) {

                parking = slot.slotNo;

            }
            else {

                parking = "no parking assigned";
            }


            return name + " | Pay: Rs " + pay +
                   " | Slot: " + parking;
        }
    }


    // ================= MAIN =================

    public static void main(String[] args) {

        // Create employees

        Manager manager =
            new Manager(101, "Divya", 70000, 8000);

        Employee employee =
            new Employee(102, "Karan", 40000);

        Intern intern =
            new Intern(103, "Meera", 12000, 10000);


        // Create parking slots

        ParkingSlot slot1 =
            new ParkingSlot("A1", 4, 0);

        ParkingSlot slot2 =
            new ParkingSlot("A2", 4, 0);


        ParkingSlot[] slots = {slot1, slot2};


        // Allot parking to only two employees

        ParkingSlot.safeAllot(slots, "DIVYA-101");

        ParkingSlot.safeAllot(slots, "KARAN-102");


        // Create employee records

        CompanyEmployeeRecord r1 =
            new CompanyEmployeeRecord(
                "Divya",
                "E101",
                manager,
                slot1
            );


        CompanyEmployeeRecord r2 =
            new CompanyEmployeeRecord(
                "Karan",
                "E102",
                employee,
                slot2
            );


        // Meera deliberately has no parking

        CompanyEmployeeRecord r3 =
            new CompanyEmployeeRecord(
                "Meera",
                "E103",
                intern,
                null
            );


        // Display profiles

        System.out.println();
        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());


        // Display total records

        System.out.println(
            "Total records: " +
            CompanyEmployeeRecord.totalRecords
        );
    }
}