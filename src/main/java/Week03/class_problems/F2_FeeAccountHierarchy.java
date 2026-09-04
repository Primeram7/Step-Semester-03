package Week03.class_problems;
public class F2_FeeAccountHierarchy {

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

        public String getRegNo() {
            return regNo;
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

    static class ScholarshipFeeAccount extends FeeAccount {
        private double scholarshipPercent;

        public ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
            super(regNo, totalFee, amountPaid);
            this.scholarshipPercent = scholarshipPercent;
        }

        public double effectiveDue() {
            return getDue() * (1 - scholarshipPercent / 100);
        }
    }

    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("PLAIN01", 150000, 150000);

        HostelFeeAccount hostel = new HostelFeeAccount("HOST01", 200000, 0);
        hostel.payInTwoInstallments(60000);

        ScholarshipFeeAccount scholarship = new ScholarshipFeeAccount("SCH01", 180000, 0, 20);

        FeeAccount[] accounts = { plain, hostel, scholarship };

        for (FeeAccount acc : accounts) {
            if (acc instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount s = (ScholarshipFeeAccount) acc;
                System.out.println("Scholarship account effective due: Rs " + s.effectiveDue());
            } else if (acc instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " + acc.getDue());
            } else {
                System.out.println("Plain account due: Rs " + acc.getDue());
            }
        }
    }
}
