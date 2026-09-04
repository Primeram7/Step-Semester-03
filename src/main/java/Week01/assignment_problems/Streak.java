package Week01.assignment_problems;
public class Streak {
   static void longeststreak(String s) {

    int max = 1;
    int s1 = 1; // 1. Moved outside the loop
    char ch = s.charAt(0);

    for(int i = 0; i < s.length() - 1; i++) 
    {
        if (s.charAt(i) == s.charAt(i + 1)) 
        {
            s1++;
            if (s1 > max) {
                max = s1;
                ch = s.charAt(i);
            }
        } else
            s1 = 1; 
        
    }
    System.out.println("Longest Streak: " + max);
    System.out.println("Character: " + ch);
}

    public static void main(String[] args) {
        String s="RRGGG";
        longeststreak(s);
    }
}
