package Week04.assignment_problems;
import java.util.Arrays;

public class CanteenRanking {

    static class Canteen {
        private String canteenCode;
        private String canteenName;
        private int trustScore;

        public Canteen(String canteenCode, String canteenName, int trustScore) {
            this.canteenCode = canteenCode;
            this.canteenName = canteenName;
            this.trustScore = trustScore;
        }

        public Canteen(String canteenCode, String canteenName) {
            this(canteenCode, canteenName, 3);
        }

        public String getCanteenCode() {
            return canteenCode;
        }

        int compareTo(Canteen other) {
            if (this.trustScore != other.trustScore) {
                return other.trustScore - this.trustScore;
            }
            int codeCompare = this.canteenCode.compareToIgnoreCase(other.canteenCode);
            if (codeCompare != 0) {
                return codeCompare;
            }
            return this.canteenName.length() - other.canteenName.length();
        }

        static Canteen[] rankCanteens(Canteen[] canteens) {
            Canteen[] ranked = Arrays.copyOf(canteens, canteens.length);
            for (int i = 1; i < ranked.length; i++) {
                Canteen key = ranked[i];
                int j = i - 1;
                while (j >= 0 && ranked[j].compareTo(key) > 0) {
                    ranked[j + 1] = ranked[j];
                    j--;
                }
                ranked[j + 1] = key;
            }
            return ranked;
        }
    }

    public static void main(String[] args) {
        Canteen[] canteens = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = Canteen.rankCanteens(canteens);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < ranked.length; i++) {
            sb.append("\"").append(ranked[i].getCanteenCode()).append("\"");
            if (i < ranked.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }
}
