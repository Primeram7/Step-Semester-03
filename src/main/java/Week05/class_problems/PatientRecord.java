package Week05.class_problems;

public class PatientRecord
{
    private String patientId;
    private String wardCode;
    private double vitalsScore;
    private String facilityName;

    public PatientRecord(
            String patientId,
            String wardCode,
            double vitalsScore,
            String facilityName)
    {
        if (patientId == null ||
                patientId.trim().isEmpty() ||
                patientId.trim().length() < 4)
        {
            throw new IllegalArgumentException(
                    "construction rejected"
            );
        }

        this.patientId = patientId;
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }

    /*
     * Java visibility rules:
     *
     * private   -> same class only
     * default   -> same class + same package
     * protected -> same class + same package
     * public    -> everywhere
     */
    static String classifyAccess(
            String fieldModifier,
            String accessorContext)
    {
        String modifier =
                fieldModifier.trim().toLowerCase();

        String context =
                accessorContext.trim().toUpperCase();

        if (modifier.equals("private"))
        {
            if (context.equals("SAME_CLASS"))
            {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (modifier.equals("default"))
        {
            if (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE"))
            {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (modifier.equals("protected"))
        {
            if (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE"))
            {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (modifier.equals("public"))
        {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeBatch(
            String[][] attempts)
    {
        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts)
        {
            String result = classifyAccess(
                    attempt[0],
                    attempt[1]
            );

            if (result.equals("ALLOWED"))
            {
                allowed++;
            }
            else
            {
                denied++;
            }
        }

        return "Allowed: " + allowed
                + " | Denied: " + denied;
    }

    public static void main(String[] args)
    {
        System.out.println(
                classifyAccess(
                        "private",
                        "SAME_CLASS"
                )
        );

        System.out.println(
                classifyAccess(
                        "default",
                        "DIFFERENT_PACKAGE"
                )
        );

        String[][] attempts =
                {
                        {"protected", "SAME_PACKAGE"},
                        {"protected", "DIFFERENT_PACKAGE"},
                        {"public", "DIFFERENT_PACKAGE"}
                };

        System.out.println(
                summarizeBatch(attempts)
        );

        try
        {
            new PatientRecord(
                    "MT9",
                    "W3",
                    98.2,
                    "MediTrack Central"
            );
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }

        PatientRecord valid =
                new PatientRecord(
                        "MT94",
                        "W3",
                        98.2,
                        "MediTrack Central"
                );

        System.out.println(
                "Valid patient created"
        );
    }
}