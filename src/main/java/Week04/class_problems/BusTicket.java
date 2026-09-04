package Week04.class_problems;
import java.util.HashSet;
import java.util.Set;

public class BusTicket {

    static class Ticket {
        private String passengerName;
        private String destination;
        private boolean checkedIn;

        public Ticket(String passengerName, String destination) {
            this.passengerName = validate(passengerName, "passengerName");
            this.destination = validate(destination, "destination");
            this.checkedIn = false;
        }

        private static String validate(String value, String fieldName) {
            if (value == null) {
                throw new IllegalArgumentException(fieldName + " cannot be null");
            }
            String trimmed = value.trim();
            if (trimmed.isEmpty()) {
                throw new IllegalArgumentException(fieldName + " cannot be blank");
            }
            if (!trimmed.matches("[a-zA-Z ]+")) {
                throw new IllegalArgumentException(fieldName + " must contain only letters and spaces");
            }
            return trimmed;
        }

        void markCheckedIn() {
            if (checkedIn) {
                System.out.println(passengerName + " was already checked in");
            } else {
                checkedIn = true;
                System.out.println(passengerName + " checked in for " + destination);
            }
        }
    }

    static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;
        Set<String> seen = new HashSet<>();

        for (String[] raw : rawBookings) {
            try {
                Ticket ticket = new Ticket(raw[0], raw[1]);
                String key = ticket.passengerName + "|" + ticket.destination;
                if (seen.contains(key)) {
                    duplicates++;
                } else {
                    seen.add(key);
                    valid++;
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {
        String[][] rawBookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };
        processBatch(rawBookings);
    }
}
