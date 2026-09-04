package Week01.class_problems;
import java.util.Scanner;

public class rockpaper {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IO.println("Enter your choice (rock, paper, or scissors):");
        String choice = sc.nextLine();
        IO.println("Your choice is: " + choice);
        String[] arr = {"rock", "paper", "scissors"};
        int randomIndex = (int) (Math.random() * arr.length);
        IO.println("Computer's choice is: " + arr[randomIndex]);
        for (int i = 0; i < arr.length; i++) {
            if (choice.equals(arr[i]) && randomIndex == i) {
                IO.println("It's a tie!");
            } else if (choice.equals("rock") && arr[randomIndex].equals("scissors")) {
                IO.println("You win!");
            } else if (choice.equals("paper") && arr[randomIndex].equals("rock")) {
                IO.println("You win!");
            } else if (choice.equals("scissors") && arr[randomIndex].equals("paper")) {
                IO.println("You win!");
            } else {
                IO.println("Computer wins!");
            }
        }
        sc.close();
    }
}
