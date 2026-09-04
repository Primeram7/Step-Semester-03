package Week01.class_problems;
import java.util.Scanner;
public class Vip {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Customer Name:");
        String name = sc.nextLine();
        System.out.println("Customer Name: " + name);
        if(name.startsWith("VIP"))
        {
            System.out.println("Customer is a VIP");
        }
        else
        {
            System.out.println("Customer is not a VIP");
        }   
        sc.close();
    }   
    
}
