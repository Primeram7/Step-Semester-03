package Week04.class_problems;
import java.util.Arrays;

public class FareSplitter {

    static class Trip {
        private String tripId;
        private double totalFare;
        private int passengerCount;

        public Trip(String tripId, double totalFare, int passengerCount) {
            if (totalFare < 0) {
                throw new IllegalArgumentException("totalFare cannot be negative");
            }
            if (passengerCount <= 0) {
                throw new IllegalArgumentException("passengerCount must be positive");
            }
            this.tripId = tripId;
            this.totalFare = totalFare;
            this.passengerCount = passengerCount;
        }

        public Trip(String tripId, double totalFare) {
            this(tripId, totalFare, 2);
        }

        public Trip(String tripId) {
            this(tripId, 0.0);
        }

        double[] fareBreakdown() {
            long totalPaisa = Math.round(totalFare * 100);
            long basePaisa = totalPaisa / passengerCount;
            long remainderPaisa = totalPaisa % passengerCount;

            double[] shares = new double[passengerCount];
            for (int i = 0; i < passengerCount; i++) {
                long sharePaisa = basePaisa;
                if (i >= passengerCount - remainderPaisa) {
                    sharePaisa += 1;
                }
                shares[i] = sharePaisa / 100.0;
            }
            return shares;
        }

        boolean isConfirmationOverdue(int confirmed, int expected) {
            return confirmed < expected;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Trip("TRIP001", 100000, 3).fareBreakdown()));
        System.out.println(Arrays.toString(new Trip("TRIP003").fareBreakdown()));
    }
}
