package Week04.class_problems;
public class FleetRec {

    static class BusTicketAccount {
        private final String bookingId;
        private double ticketFare;

        static final double MINIMUM_PENALTY_PERCENT;
        static {
            MINIMUM_PENALTY_PERCENT = 1.0;
        }

        public BusTicketAccount(String bookingId, double ticketFare) {
            this.bookingId = bookingId;
            this.ticketFare = ticketFare;
        }

        public BusTicketAccount(String bookingId) {
            this(bookingId, 0.0);
        }

        final double calculatePenalty(int minutesLate) {
            if (ticketFare < 0 || minutesLate < 0) {
                throw new IllegalArgumentException("ticketFare and minutesLate must be non-negative");
            }
            if (minutesLate == 0) {
                return 0.0;
            }
            int bracket1 = Math.min(minutesLate, 5);
            int bracket2 = Math.min(Math.max(minutesLate - 5, 0), 10);
            int bracket3 = Math.max(minutesLate - 15, 0);
            double tieredPercent = bracket1 * 0.5 + bracket2 * 1.0 + bracket3 * 2.0;
            double tieredPenalty = ticketFare * tieredPercent / 100;
            double floorPenalty = ticketFare * MINIMUM_PENALTY_PERCENT / 100;
            return Math.max(tieredPenalty, floorPenalty);
        }
    }

    static class SleeperTicketAccount extends BusTicketAccount {
        public SleeperTicketAccount(String bookingId, double ticketFare) {
            super(bookingId, ticketFare);
        }

        public SleeperTicketAccount(String bookingId) {
            super(bookingId);
        }
    }

    static class RecEngine {
        private int processedCount = 0;
        private int nullSkipped = 0;
        private int sleeperCount = 0;
        private int regularCount = 0;
        private double grandTotalPenalties = 0;

        void processAccount(BusTicketAccount account, double amount, int minutesLate) {
            if (account == null) {
                nullSkipped++;
                return;
            }
            account.ticketFare = amount;
            double penalty = account.calculatePenalty(minutesLate);
            if (account instanceof SleeperTicketAccount) {
                penalty *= 1.15;
                sleeperCount++;
            } else {
                regularCount++;
            }
            grandTotalPenalties += penalty;
            processedCount++;
        }

        static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
            RecEngine engine = new RecEngine();
            int minLen = Math.min(accounts.length, Math.min(amounts.length, minutesLateArray.length));
            for (int i = 0; i < minLen; i++) {
                engine.processAccount(accounts[i], amounts[i], minutesLateArray[i]);
            }
            System.out.println(engine.processedCount + " processed | " + engine.nullSkipped + " null skipped | "
                + engine.sleeperCount + " sleeper | " + engine.regularCount + " regular | grand total penalties = Rs "
                + String.format("%.2f", engine.grandTotalPenalties));
        }
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new SleeperTicketAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };
        double[] amounts = { 1200, 900, 700 };
        int[] minutesLateArray = { 10, 5, 0 };

        RecEngine.processBatch(accounts, amounts, minutesLateArray);
    }
}
