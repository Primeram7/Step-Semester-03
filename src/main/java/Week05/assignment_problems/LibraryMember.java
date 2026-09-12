package Week05.assignment_problems;

public class LibraryMember
{
    private String membershipId;
    private String branchCode;
    private double finesOwed;
    private String displayName;

    // Check access according to Java visibility rules
    static String classifyAccess(String fieldModifier, String accessorContext)
    {
        String modifier = fieldModifier.trim().toLowerCase();
        String context = accessorContext.trim().toUpperCase();

        if (modifier.equals("private"))
        {
            return context.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        }

        if (modifier.equals("default") || modifier.equals("package-private"))
        {
            return (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";
        }

        if (modifier.equals("protected"))
        {
            return (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";
        }

        if (modifier.equals("public"))
        {
            return "ALLOWED";
        }

        return "DENIED";
    }

    // Group results by modifier
    static String summarizeByModifier(String[][] attempts)
    {
        int privateAllowed = 0;
        int privateDenied = 0;

        int defaultAllowed = 0;
        int defaultDenied = 0;

        int protectedAllowed = 0;
        int protectedDenied = 0;

        int publicAllowed = 0;
        int publicDenied = 0;

        for (String[] attempt : attempts)
        {
            String modifier = attempt[0].trim().toLowerCase();
            String context = attempt[1].trim();

            String result = classifyAccess(modifier, context);

            if (modifier.equals("private"))
            {
                if (result.equals("ALLOWED"))
                    privateAllowed++;
                else
                    privateDenied++;
            }
            else if (modifier.equals("default") ||
                    modifier.equals("package-private"))
            {
                if (result.equals("ALLOWED"))
                    defaultAllowed++;
                else
                    defaultDenied++;
            }
            else if (modifier.equals("protected"))
            {
                if (result.equals("ALLOWED"))
                    protectedAllowed++;
                else
                    protectedDenied++;
            }
            else if (modifier.equals("public"))
            {
                if (result.equals("ALLOWED"))
                    publicAllowed++;
                else
                    publicDenied++;
            }
        }

        return "private: " + privateAllowed + " allowed / "
                + privateDenied + " denied | "
                + "default: " + defaultAllowed + " allowed / "
                + defaultDenied + " denied | "
                + "protected: " + protectedAllowed + " allowed / "
                + protectedDenied + " denied | "
                + "public: " + publicAllowed + " allowed / "
                + publicDenied + " denied";
    }

    public LibraryMember(
            String membershipId,
            String branchCode,
            double finesOwed,
            String displayName)
    {
        if (membershipId == null ||
                membershipId.trim().isEmpty() ||
                membershipId.trim().length() < 4)
        {
            throw new IllegalArgumentException("construction rejected");
        }

        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public static void main(String[] args)
    {
        System.out.println(
                classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
                classifyAccess("protected", "DIFFERENT_PACKAGE")
        );

        String[][] attempts =
                {
                        {"private", "SAME_CLASS"},
                        {"private", "SAME_PACKAGE"},
                        {"default", "SAME_PACKAGE"},
                        {"default", "DIFFERENT_PACKAGE"},
                        {"protected", "SAME_PACKAGE"},
                        {"protected", "SAME_CLASS"},
                        {"public", "DIFFERENT_PACKAGE"}
                };

        System.out.println(summarizeByModifier(attempts));

        try
        {
            new LibraryMember(
                    "LB9",
                    "BR1",
                    0,
                    "Priya Nair"
            );
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        LibraryMember valid =
                new LibraryMember(
                        "LB94",
                        "BR1",
                        0,
                        "Priya Nair"
                );

        System.out.println("Valid member created: "
                + valid.membershipId);
    }
}