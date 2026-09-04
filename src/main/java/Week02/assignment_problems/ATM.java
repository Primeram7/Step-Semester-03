package Week02.assignment_problems;
public class ATM
{
    static String pinCheck(String pin)
    {
        return((pin.length()==4)?"Valid Pin Number":"Not Valid Pin Number");
    }
    public static void main(String[] args) {
        System.out.println(pinCheck("4837"));
    }
}