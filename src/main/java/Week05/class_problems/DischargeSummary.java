package Week05.class_problems;

public class DischargeSummary
{
    private final String patientId;
    private final String[] medicationCodes;

    private static final String MED_PREFIX;
    private static final int MED_CODE_LENGTH;

    // Static block
    static
    {
        MED_PREFIX = "MED-";
        MED_CODE_LENGTH = 5;
    }

    public DischargeSummary(
            String patientId,
            String[] medicationCodes)
    {
        if (!isValidMedicationCodes(
                medicationCodes))
        {
            throw new IllegalArgumentException(
                    "construction rejected"
            );
        }

        this.patientId = patientId;

        // Defensive copy
        this.medicationCodes =
                medicationCodes.clone();
    }

    private static boolean isValidMedicationCodes(
            String[] medicationCodes)
    {
        if (medicationCodes == null)
        {
            return false;
        }

        for (String code : medicationCodes)
        {
            if (!isValidMedicationCode(code))
            {
                return false;
            }
        }

        return true;
    }

    private static boolean isValidMedicationCode(
            String code)
    {
        if (code == null ||
                code.length() != MED_CODE_LENGTH)
        {
            return false;
        }

        if (!code.startsWith(MED_PREFIX))
        {
            return false;
        }

        char last =
                code.charAt(
                        code.length() - 1
                );

        return last >= 'A' && last <= 'Z';
    }

    public String getPatientId()
    {
        return patientId;
    }

    public String[] getMedicationCodes()
    {
        // Defensive copy
        return medicationCodes.clone();
    }

    public DischargeSummary
    withCorrectedMedication(
            int index,
            String newCode)
    {
        String[] corrected =
                medicationCodes.clone();

        corrected[index] = newCode;

        return new DischargeSummary(
                patientId,
                corrected
        );
    }

    static String processNightlyBatch(
            DischargeSummary[] summaries)
    {
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        if (summaries != null)
        {
            for (DischargeSummary summary :
                    summaries)
            {
                if (summary == null)
                {
                    nullSkipped++;
                }
                else if (summary
                        instanceof CriticalCareDischargeSummary)
                {
                    criticalCare++;
                }
                else
                {
                    routine++;
                }
            }
        }

        int processed =
                criticalCare + routine;

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + criticalCare
                + " critical-care | "
                + routine
                + " routine";
    }

    public static void main(String[] args)
    {
        // -----------------------------------------
        // 1. Invalid medication code
        // -----------------------------------------
        try
        {
            new DischargeSummary(
                    "MT2026-0142",
                    new String[]
                            {
                                    "MED-A",
                                    "bad"
                            }
            );
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(
                    e.getMessage()
            );
        }

        // -----------------------------------------
        // 2. Defensive copying
        // -----------------------------------------
        DischargeSummary d =
                new DischargeSummary(
                        "MT2026-0142",
                        new String[]
                                {
                                        "MED-A",
                                        "MED-B"
                                }
                );

        String[] codes =
                d.getMedicationCodes();

        codes[0] = "TAMPERED";

        System.out.println(
                d.getMedicationCodes()[0]
        );

        // -----------------------------------------
        // 3. Wither method
        // -----------------------------------------
        DischargeSummary corrected =
                d.withCorrectedMedication(
                        0,
                        "MED-Z"
                );

        // Original unchanged
        System.out.println(
                d.getMedicationCodes()[0]
        );

        // New object changed
        System.out.println(
                corrected.getMedicationCodes()[0]
        );

        // -----------------------------------------
        // 4. Nightly batch
        // -----------------------------------------
        String result =
                processNightlyBatch(
                        new DischargeSummary[]
                                {
                                        new CriticalCareDischargeSummary(
                                                "MT001",
                                                new String[]
                                                        {
                                                                "MED-X"
                                                        },
                                                4
                                        ),

                                        null,

                                        new DischargeSummary(
                                                "MT002",
                                                new String[]
                                                        {
                                                                "MED-Y"
                                                        }
                                        )
                                }
                );

        System.out.println(result);
    }
}


// Critical-care variant
class CriticalCareDischargeSummary
        extends DischargeSummary
{
    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays)
    {
        super(
                patientId,
                medicationCodes
        );

        this.icuDays = icuDays;
    }

    public int getIcuDays()
    {
        return icuDays;
    }
}