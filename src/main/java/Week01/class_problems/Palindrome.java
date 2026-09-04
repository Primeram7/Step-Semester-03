package Week01.class_problems;

public class Palindrome{
    public static String reverse(String s, int size)
    {
        if(size<0)
            return "";
        String rev = s.charAt(size) + reverse(s, size-1);
        return rev;
    }
    public static void main(String args[])
    {
        String s = "racecar";
        int size = s.length()-1;
        String rev = reverse(s, size);
        if(s.equals(rev))
            System.out.println("The given string is a palindrome");
        else
            System.out.println("The given string is not a palindrome");
    }
} 