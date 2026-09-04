package Week03.assignment_problems;
public class Library
{
    static class BookIssue
    {
        String title;
        String borrower_name;
        int days_overdue;
        BookIssue(String title,String borrower_name,int days_overdue)
        {
            this.title = title;
            this.borrower_name = borrower_name;
            this.days_overdue = days_overdue;
        }
        int FineAmount()
        {
            return(days_overdue > 0 ? 5*days_overdue : 0);
        }
        boolean severoverdue()
        {
            return(days_overdue>14?true:false);
        }
        static int totalFineCollected(BookIssue[] issue){
            int total = 0;
            for (BookIssue b : issue) {
                if (b != null) {
                    total += b.FineAmount();
                }
            }
            return total;
        }
    }
    public static void main(String[] args) {
        BookIssue [] issues = new BookIssue[5];
        issues[0] = new BookIssue("Harry Potter","Pratik",6);
        issues[1] = new BookIssue("Fires of Wings","Hitesh",8);
        issues[2] = new BookIssue("My Experiments With truth","Ritesh",16);
        issues[3] = new BookIssue("Ghost of Physics","Adit",9);
        issues[4] = new BookIssue("The fire Ball","Sabrish",3);
        System.out.println("Total Fine Amount Collected : "+BookIssue.totalFineCollected(issues));
        for(BookIssue b:issues)
        {
            System.out.println("Fine Amount :"+b.FineAmount());
            System.out.println("Severly due : "+b.severoverdue());
        }
    }
}
