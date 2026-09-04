package Week03.assignment_problems;
class LibraryMember {

    // Instance fields
    String name;
    String memberId;
    int booksIssued;

    // Static fields
    static String libraryName = "SRM Library";
    static int memberCount = 1000;

    // Constructor
    LibraryMember(String name, int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;

        // Generate member ID using memberCount
        memberCount++;
        this.memberId = "LM-" + memberCount;
    }

    // Instance method
    void printMemberCard() {

        System.out.println(name + " | " + memberId);
    }

    // Static method
    static void printTotalMembers() {

        System.out.println("Total members: " + (memberCount - 1000));
    }

    public static void main(String[] args) {

        LibraryMember m1 = new LibraryMember("Aditi", 2);
        LibraryMember m2 = new LibraryMember("Rohan", 3);

        m1.printMemberCard();
        m2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}
