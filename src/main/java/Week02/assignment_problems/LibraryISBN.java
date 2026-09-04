package Week02.assignment_problems;
public class LibraryISBN {
    public static String normalizeCode(String raw) {
        String trimmed = raw.trim();
        String firstThree = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);
        return firstThree + rest;
    }

    public static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }
        String publisher = code.substring(0, 3);
        for (int i = 0; i < publisher.length(); i++) {
            if (!Character.isLetter(publisher.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }
        String body = code.substring(3);
        for (int i = 0; i < body.length(); i++) {
            if (!Character.isDigit(body.charAt(i))) {
                return "Invalid: body must be digits";
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[').append(publisher).append(']')
          .append(" YEAR: 20").append(body.substring(0, 2))
          .append(" | CATALOG: ").append(body.substring(2));
        return sb.toString();
    }

    public static void main(String[] args) {
        String input1 = " pen2026004251 ";
        String normalized1 = normalizeCode(input1);
        System.out.println(validateAndFormat(normalized1));

        String input2 = "12N2026004251";
        String normalized2 = normalizeCode(input2);
        System.out.println(validateAndFormat(normalized2));
    }
}