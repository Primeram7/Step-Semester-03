package Week02.assignment_problems;
public class WordEncoder {

    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return sentence;
        }
        String[] words = sentence.split(" ");
        
    
        StringBuilder finalResult = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder reversedWord = new StringBuilder();
            for (int j = word.length() - 1; j >= 0; j--) {
                reversedWord.append(word.charAt(j));
            }
            finalResult.append(reversedWord);
            if (i < words.length - 1) {
                finalResult.append(" ");
            }
        }

        return finalResult.toString();
    }

    public static void main(String[] args) {
        // Test with the Sample Input
        String input = "hello club";
        String output = reverseEachWord(input);
        
        System.out.println("Input:  \"" + input + "\"");
        System.out.println("Output: \"" + output + "\"");
    }
}
