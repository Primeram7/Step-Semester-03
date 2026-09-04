package Week02.class_problems;
public class VowelConsonantCounter {
    public static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;
        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                continue;
            }
            char lowerCh = Character.toLowerCase(ch);
            if (lowerCh == 'a' || lowerCh == 'e' || lowerCh == 'i' || lowerCh == 'o' || lowerCh == 'u') {
                vowels++;
            } else {
                consonants++;
            }
        }
        
        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }
    
    public static void main(String[] args) {
        countVowelsAndConsonants("Java Programming");
    }
}