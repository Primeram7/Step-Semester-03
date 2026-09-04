package Week01.class_problems;
import java.util.HashMap;
public class Unique {
    public static void main(String args[])
    {
        String s = "swiss";
        HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        for(char ch:s.toCharArray())
        {
            if(charCountMap.containsKey(ch))
            {
                charCountMap.put(ch, charCountMap.get(ch)+1);
            }
            else
            {
                charCountMap.put(ch, 1);
            }
        }
        for(char ch:charCountMap.keySet())
        {
            if(charCountMap.get(ch)==1)
            {
                System.out.println("The first non-repeating character is: "+ch);
                break;
            }
        }
    }
}