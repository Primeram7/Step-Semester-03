package Week02.assignment_problems;
public class CSVparase 
{
    static void parseInventory(String csvline)
    {
        String[] word = csvline.split(",");
        String[] Final= new String[word.length+5];
        if(word.length!=3)
        {
            System.out.println("Invalid Record");
            return ;
        }
        for(int i=0;i<word.length;i++)
        {
            Final[i]=word[i];
        }
        System.out.println("Product : "+Final[0]+" | SKU : "+Final[1]+" | Qty : "+Final[2]);
    }
    public static void main(String[] args) {
        parseInventory("Wireless Mouse,WM-2201,150");
        parseInventory("Wireless Mouse,150");
    }
    
}
