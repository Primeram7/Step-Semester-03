package Week04.class_problems;
public class BoardingPenalty {

    static final class BoardingPenaltyCalculator {
        private final double minimumPenaltyPercent;

        public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
            this.minimumPenaltyPercent = minimumPenaltyPercent;
        }

        final double calculatePenalty(double ticketFare, int minutesLate) {
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
            double floorPenalty = ticketFare * minimumPenaltyPercent / 100;

            return Math.max(tieredPenalty, floorPenalty);
        }
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calc = new BoardingPenaltyCalculator(1); 
        System.out.println("Rs " + calc.calculatePenalty(1000, 0));
        System.out.println("Rs " + calc.calculatePenalty(1000, 1));
        System.out.println("Rs " + calc.calculatePenalty(1000, 16));
    }
}
