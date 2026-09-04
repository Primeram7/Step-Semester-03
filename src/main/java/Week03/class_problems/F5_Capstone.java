package Week03.class_problems;
public class F5_Capstone {

    static class FeeAccount {
        private String regNo;
        private double totalFee;
        private double amountPaid;

        public FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
        }

        public void pay(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Payment must be positive");
            }
            amountPaid += amount;
        }

        public double getDue() {
            return totalFee - amountPaid;
        }
    }

    static class HostelFeeAccount extends FeeAccount {
        public HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }

        public void payInTwoInstallments(double amount) {
            double half = amount / 2;
            pay(half);
            pay(amount - half);
        }
    }

    static class HostelRoom {
        private String roomNo;
        private int beds;
        private int occupied;

        public HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        public void allot(String name) {
            if (occupied < beds) {
                occupied++;
            }
        }

        public String getRoomNo() {
            return roomNo;
        }

        public int getBeds() {
            return beds;
        }

        public int getOccupied() {
            return occupied;
        }
    }

    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (HostelRoom room : rooms) {
            if (room.getOccupied() < room.getBeds()) {
                return room;
            }
        }
        return null;
    }

    static class SrmStudent {
        private String name;
        private String regNo;
        private HostelFeeAccount feeAccount;
        private HostelRoom room;

        static int totalStudents = 0;

        public SrmStudent(String name, String regNo, HostelFeeAccount feeAccount) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;
            this.room = null;
            totalStudents++;
        }

        public String getName() {
            return name;
        }

        public void setRoom(HostelRoom room) {
            this.room = room;
        }

        public String fullStatus() {
            String roomInfo = (room == null) ? "unallotted" : room.getRoomNo();
            return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomInfo;
        }
    }

    public static void main(String[] args) {
        HostelFeeAccount ravAcc = new HostelFeeAccount("RA231", 200000, 0);
        ravAcc.payInTwoInstallments(60000);

        HostelFeeAccount anithaAcc = new HostelFeeAccount("RA232", 200000, 0);
        anithaAcc.payInTwoInstallments(20000);

        HostelFeeAccount karthikAcc = new HostelFeeAccount("RA233", 200000, 0);
        try {
            karthikAcc.pay(-500); // rejected payment
        } catch (IllegalArgumentException e) {
            System.out.println("Payment rejected: " + e.getMessage());
        }

        SrmStudent ravi = new SrmStudent("Ravi", "RA231", ravAcc);
        SrmStudent anitha = new SrmStudent("Anitha", "RA232", anithaAcc);
        SrmStudent karthik = new SrmStudent("Karthik", "RA233", karthikAcc);

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1, 0),
            new HostelRoom("C-507", 1, 0)
        };

        HostelRoom room1 = findAvailableRoom(rooms);
        if (room1 != null) {
            room1.allot(ravi.getName());
            ravi.setRoom(room1);
        }

        HostelRoom room2 = findAvailableRoom(rooms);
        if (room2 != null) {
            room2.allot(anitha.getName());
            anitha.setRoom(room2);
        }
        // Karthik is deliberately left unallotted

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
