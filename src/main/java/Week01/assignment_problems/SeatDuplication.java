package Week01.assignment_problems;

import java.util.Scanner;
public class SeatDuplication{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter array Values: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        boolean Flag=false;
        for(int i=0;i<n;i++)
        {
            for(int j=i+1; j<n; j++)
            {
                if(arr[i]==arr[j])
                {
                    System.out.println("Duplicate Seat Found :"+arr[i]);
                    Flag=true;
                }
            } 
        }
        if(!Flag)
           System.out.println("No Duplicate Seat Found");
        sc.close();
    }
    
}
