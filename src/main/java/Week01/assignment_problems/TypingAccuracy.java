package Week01.assignment_problems;
public class TypingAccuracy {
    public static void checkTypingAccuracy(String original, String typed) {
        if (original.length() != typed.length()) {
            System.out.println("Error: Strings must be of equal length.");
            return;
        }

        int matched = 0;
        int firstMismatchPos = -1; // 0-based index

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else {
                if (firstMismatchPos == -1) {
                    firstMismatchPos = i;
                }
            }
        }

        double accuracy = (double) matched / original.length() * 100;

        System.out.print("Matched: " + matched + "/" + original.length() + " | ");
        System.out.printf("Accuracy: %.2f%% | ", accuracy);

        if (firstMismatchPos == -1) {
            System.out.println("No Mismatches");
        } else {
            // Convert to 1-based position for output
            int pos = firstMismatchPos + 1;
            System.out.println("First Mismatch at position " + pos + " ('" 
                    + original.charAt(firstMismatchPos) + "' vs '" 
                    + typed.charAt(firstMismatchPos) + "')");
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1:");
        checkTypingAccuracy("hello world", "hello worlt");
        System.out.println();
        System.out.println("Test 2:");
        checkTypingAccuracy("coding", "coding");
        System.out.println();
        System.out.println("Test 3:");
        checkTypingAccuracy("java", "jvaa");
        System.out.println();
    }
}