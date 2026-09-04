package Week04.assignment_problems;
public class SurgeCalculator {

    static final class SurgeFeeCalculator {
        private final double minimumSurgePercent;

        public SurgeFeeCalculator(double minimumSurgePercent) {
            this.minimumSurgePercent = minimumSurgePercent;
        }

        final double calculateSurgeFee(double orderValue, int delayMinutes) {
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
            double floorFee = orderValue * minimumSurgePercent / 100;

            return Math.max(tieredFee, floorFee);
        }
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calc = new SurgeFeeCalculator(1); 
        System.out.println("Rs " + calc.calculateSurgeFee(500, 0));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 1));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 16));
    }
}
