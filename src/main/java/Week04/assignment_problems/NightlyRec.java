package Week04.assignment_problems;
public class NightlyRec {

    static class DeliveryAccount {
        private final String studentId;
        private double orderValue;

        static final double MINIMUM_SURGE_PERCENT;
        static {
            MINIMUM_SURGE_PERCENT = 1.0;
        }

        public DeliveryAccount(String studentId, double orderValue) {
            this.studentId = studentId;
            this.orderValue = orderValue;
        }

        public DeliveryAccount(String studentId) {
            this(studentId, 0.0);
        }

        final double calculateSurgeFee(int delayMinutes) {
            if (orderValue < 0 || delayMinutes < 0) {
                throw new IllegalArgumentException("orderValue and delayMinutes must be non-negative");
            }
            if (delayMinutes == 0) {
                return 0.0;
            }
            int bracket1 = Math.min(delayMinutes, 5);
            int bracket2 = Math.min(Math.max(delayMinutes - 5, 0), 10);
            int bracket3 = Math.max(delayMinutes - 15, 0);
            double tieredPercent = bracket1 * 0.5 + bracket2 * 1.0 + bracket3 * 2.0;
            double tieredFee = orderValue * tieredPercent / 100;
            double floorFee = orderValue * MINIMUM_SURGE_PERCENT / 100;
            return Math.max(tieredFee, floorFee);
        }
    }

    static class PremiumDeliveryAccount extends DeliveryAccount {
        public PremiumDeliveryAccount(String studentId, double orderValue) {
            super(studentId, orderValue);
        }

        public PremiumDeliveryAccount(String studentId) {
            super(studentId);
        }
    }

    static class ReconciliationEngine {
        private int processedCount = 0;
        private int nullSkipped = 0;
        private int premiumCount = 0;
        private int regularCount = 0;
        private double grandTotalSurgeFees = 0;

        void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
            if (account == null) {
                nullSkipped++;
                return;
            }
            account.orderValue = amount;
            double fee = account.calculateSurgeFee(delayMinutes);
            if (account instanceof PremiumDeliveryAccount) {
                fee *= 0.9; 
                premiumCount++;
            } else {
                regularCount++;
            }
            grandTotalSurgeFees += fee;
            processedCount++;
        }

        static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
            ReconciliationEngine engine = new ReconciliationEngine();
            int minLen = Math.min(accounts.length, Math.min(amounts.length, delayMinutesArray.length));
            for (int i = 0; i < minLen; i++) {
                engine.processAccount(accounts[i], amounts[i], delayMinutesArray[i]);
            }
            System.out.println(engine.processedCount + " processed | " + engine.nullSkipped + " null skipped | "
                + engine.premiumCount + " premium | " + engine.regularCount + " regular | grand total surge fees = Rs "
                + String.format("%.2f", engine.grandTotalSurgeFees));
        }
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new PremiumDeliveryAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };
        double[] amounts = { 500, 400, 300 };
        int[] delayMinutesArray = { 10, 5, 0 };

        ReconciliationEngine.processBatch(accounts, amounts, delayMinutesArray);
    }
}
