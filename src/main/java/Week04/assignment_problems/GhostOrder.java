package Week04.assignment_problems;
public class GhostOrder {

    static class FoodOrder {
        private String studentName;
        private String dishName;
        private boolean delivered;

        public FoodOrder(String studentName, String dishName) {
            this.studentName = validate(studentName, "studentName");
            this.dishName = validate(dishName, "dishName");
            this.delivered = false;
        }

        private static String validate(String value, String fieldName) {
            if (value == null || value.trim().isEmpty()) {
                throw new IllegalArgumentException(fieldName + " cannot be blank");
            }
            return value.trim();
        }

        void markDelivered() {
            if (delivered) {
                System.out.println(studentName + "'s order was already marked delivered");
            } else {
                delivered = true;
                System.out.println(studentName + "'s order marked delivered");
            }
        }
    }

    static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;
        for (String[] raw : rawOrders) {
            try {
                new FoodOrder(raw[0], raw[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };
        processBatch(rawOrders);

        FoodOrder order = new FoodOrder("Ravi", "Paneer Butter Masala");
        order.markDelivered();
        order.markDelivered();
    }
}
