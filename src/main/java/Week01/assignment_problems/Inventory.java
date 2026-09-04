package Week01.assignment_problems;
import java.util.Scanner;

public class Inventory {

    public static void AnalyzeInventory(int[] sectionA, int[] sectionB) {
        int n1 = sectionA.length;
        int sum1 = 0, sum2 = 0;

        for (int i = 0; i < n1; i++) {
            sum1 += sectionA[i];
            sum2 += sectionB[i];
        }

        if (sum1 == sum2)
            System.out.println("Section are Balanced");
        else
            System.out.println("Not Balanced");

        int max1 = sectionA[0], max2 = sectionB[0];
        for (int i = 0; i < n1; i++) {
            if (sectionA[i] > max1) max1 = sectionA[i];
            if (sectionB[i] > max2) max2 = sectionB[i];
        }

        System.out.println("Highest Quantity in Section A is: " + max1);
        System.out.println("Highest Quantity in Section B is: " + max2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] sectionA = new int[5];
        int[] sectionB = new int[5];

        System.out.print("Enter array Values of section A: ");
        for (int i = 0; i < 5; i++) sectionA[i] = sc.nextInt();

        System.out.print("Enter array Values of section B: ");
        for (int i = 0; i < 5; i++) sectionB[i] = sc.nextInt();

        AnalyzeInventory(sectionA, sectionB);

        sc.close();
    }
}