package Week05.assignment_problems;

public class LoanReceipt
{
    private final String memberId;
    private final String[] bookIds;

    private static final String BOOK_ID_PREFIX;
    private static final int BOOK_ID_LENGTH;

    // Static block
    static
    {
        BOOK_ID_PREFIX = "BK-";
        BOOK_ID_LENGTH = 6;
    }

    public LoanReceipt(
            String memberId,
            String[] bookIds)
    {
        if (!isValidBookIds(bookIds))
        {
            throw new IllegalArgumentException(
                    "construction rejected"
            );
        }

        this.memberId = memberId;

        // Defensive copy
        this.bookIds = bookIds.clone();
    }

    private static boolean isValidBookIds(
            String[] bookIds)
    {
        if (bookIds == null)
        {
            return false;
        }

        for (String id : bookIds)
        {
            if (!isValidBookId(id))
            {
                return false;
            }
        }

        return true;
    }

    private static boolean isValidBookId(
            String id)
    {
        if (id == null ||
                id.length() != BOOK_ID_LENGTH)
        {
            return false;
        }

        if (!id.startsWith(BOOK_ID_PREFIX))
        {
            return false;
        }

        for (int i = BOOK_ID_PREFIX.length();
             i < id.length();
             i++)
        {
            if (!Character.isDigit(id.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    public String getMemberId()
    {
        return memberId;
    }

    // Defensive copy
    public String[] getBookIds()
    {
        return bookIds.clone();
    }

    // Wither method
    public LoanReceipt withCorrectedBookId(
            int index,
            String newId)
    {
        String[] corrected = bookIds.clone();

        corrected[index] = newId;

        return new LoanReceipt(
                memberId,
                corrected
        );
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts)
    {
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts != null)
        {
            for (LoanReceipt receipt : receipts)
            {
                if (receipt == null)
                {
                    nullSkipped++;
                }
                else if (receipt
                        instanceof ReferenceOnlyLoanReceipt)
                {
                    referenceOnly++;
                }
                else
                {
                    regular++;
                }
            }
        }

        int processed =
                referenceOnly + regular;

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + referenceOnly
                + " reference-only | "
                + regular
                + " regular";
    }

    public static void main(String[] args)
    {
        // 1. Invalid book ID
        try
        {
            new LoanReceipt(
                    "LIB-8841",
                    new String[]
                            {
                                    "BK-100",
                                    "bad"
                            }
            );
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        // 2. Defensive copying
        LoanReceipt r =
                new LoanReceipt(
                        "LIB-8841",
                        new String[]
                                {
                                        "BK-100",
                                        "BK-101"
                                }
                );

        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(
                r.getBookIds()[0]
        );

        // 3. Wither
        LoanReceipt corrected =
                r.withCorrectedBookId(
                        0,
                        "BK-999"
                );

        // Original unchanged
        System.out.println(
                r.getBookIds()[0]
        );

        // New object corrected
        System.out.println(
                corrected.getBookIds()[0]
        );

        // 4. Nightly processing
        String result =
                processNightlyCirculation(
                        new LoanReceipt[]
                                {
                                        new ReferenceOnlyLoanReceipt(
                                                "LIB-001",
                                                new String[]
                                                        {
                                                                "BK-200"
                                                        },
                                                "Reading Room 3"
                                        ),

                                        null,

                                        new LoanReceipt(
                                                "LIB-002",
                                                new String[]
                                                        {
                                                                "BK-201"
                                                        }
                                        )
                                }
                );

        System.out.println(result);
    }
}


// Reference-only variant
class ReferenceOnlyLoanReceipt
        extends LoanReceipt
{
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber)
    {
        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }

    public String getRoomNumber()
    {
        return roomNumber;
    }
}