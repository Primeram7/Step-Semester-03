package Week02.class_problems;
public class BankTransactionReference {
    public static String normalizeReference(String raw) {
        if (raw == null) {
            return "";
        }
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        String firstThree = trimmed.substring(0, 3).toUpperCase();
        String rest = trimmed.substring(3);
        return firstThree + rest;
    }
    
    public static String validateAndFormat(String reference) {
        String normalized = normalizeReference(reference);
        if (normalized.length() != 14) {
            return "Invalid: wrong length";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(normalized.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(normalized.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }
        String bankCode = normalized.substring(0, 3);
        String datePart = normalized.substring(3, 9);
        String seqPart = normalized.substring(9);
        String formattedDate = datePart.substring(0, 2) + "/" + 
                               datePart.substring(2, 4) + "/" + 
                               datePart.substring(4, 6);
        StringBuilder result = new StringBuilder();
        result.append("[").append(bankCode).append("] DATE: ")
              .append(formattedDate).append(" | SEQ: ").append(seqPart);
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(validateAndFormat(" hdf03022600042 "));
        System.out.println(validateAndFormat("12F03022600042"));
    }
}