package Week05.assignment_problems;

public class LibraryChain
{
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;

    // No-argument constructor
    public LibraryChain()
    {
        this(null, null);
    }

    // Name-only constructor
    public LibraryChain(String name)
    {
        this(null, name);
    }

    // Main constructor
    public LibraryChain(
            String membershipId,
            String name)
    {
        this.membershipId = membershipId;
        this.name = name;
    }

    public String getMembershipId()
    {
        return membershipId;
    }

    // Write-once
    public void setMembershipId(String id)
    {
        if (this.membershipId == null)
        {
            this.membershipId = id;
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isPremiumMember()
    {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium)
    {
        this.premiumMember = premium;
    }

    // Write-only security answer
    public void setSecurityAnswer(String answer)
    {
        if (answer == null)
        {
            securityAnswerHash = null;
        }
        else
        {
            securityAnswerHash =
                    answer.trim().toLowerCase().hashCode()
                            + "";
        }
    }

    public static void main(String[] args)
    {
        System.out.println(
                new LibraryChain("Priya Nair")
                        .getMembershipId()
        );

        LibraryChain m2 =
                new LibraryChain(
                        "LIB-8841",
                        "Priya Nair"
                );

        System.out.println(m2.getMembershipId());

        LibraryChain m3 =
                new LibraryChain();

        m3.setMembershipId("LIB-8841");
        m3.setMembershipId("FAKE-0000");

        System.out.println(m3.getMembershipId());

        m3.setPremiumMember(true);

        System.out.println(m3.isPremiumMember());

        m3.setSecurityAnswer(
                "Mother's maiden name"
        );

        // No getter for securityAnswer
    }
}