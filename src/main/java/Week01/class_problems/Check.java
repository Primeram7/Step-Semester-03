package Week01.class_problems;
import java.util.Scanner;

public class Check {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Write a sentence:");
        String sentence = sc.nextLine();

        String[] words = sentence.split(" ");
        int max = words[0].length();
        int min = words[0].length();

        for (String word : words) {
            max = Math.max(max, word.length());
            min = Math.min(min, word.length());
        }

        System.out.println("The longest word length is: " + max);
        System.out.println("The shortest word length is: " + min);
        sc.close();
    }
}