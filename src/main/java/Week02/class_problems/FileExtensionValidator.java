package Week02.class_problems;
public class FileExtensionValidator {
    public static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1) {
            return "Rejected — invalid file type";
        }
        String extension = filename.substring(dotIndex + 1).toLowerCase();
        if (extension.equals("pdf") || extension.equals("docx") || extension.equals("zip")) {
            return "Accepted";
        } else {
            return "Rejected — invalid file type";
        }
    }
    
    public static void main(String[] args) {
        System.out.println(validateFileExtension("Assignment1.PDF"));
        System.out.println(validateFileExtension("notes.txt"));
    }
}