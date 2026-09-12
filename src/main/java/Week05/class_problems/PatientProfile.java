package Week05.class_problems;

public class PatientProfile
{
    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPinHash;

    // No-argument constructor
    public PatientProfile()
    {
        this(null, null);
    }

    // Name-only constructor
    public PatientProfile(String name)
    {
        this(null, name);
    }

    // Main constructor
    public PatientProfile(
            String patientId,
            String name)
    {
        this.patientId = patientId;
        this.name = name;
    }

    public String getPatientId()
    {
        return patientId;
    }

    // Write-once setter
    public void setPatientId(String id)
    {
        if (this.patientId == null)
        {
            this.patientId = id;
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

    public boolean isDischarged()
    {
        return discharged;
    }

    public void setDischarged(
            boolean discharged)
    {
        this.discharged = discharged;
    }

    // Write-only property
    public void setLockerPin(String pin)
    {
        if (pin == null)
        {
            lockerPinHash = null;
            return;
        }

        if (pin.matches("\\d{4,6}"))
        {
            lockerPinHash =
                    Integer.toHexString(
                            pin.hashCode()
                    );
        }
    }

    public static void main(String[] args)
    {
        // Name-only constructor
        PatientProfile p1 =
                new PatientProfile(
                        "Arjun Iyer"
                );

        System.out.println(
                p1.getPatientId()
        );

        // ID + name constructor
        PatientProfile p2 =
                new PatientProfile(
                        "MT2026-0142",
                        "Arjun Iyer"
                );

        System.out.println(
                p2.getPatientId()
        );

        // Write-once test
        PatientProfile p3 =
                new PatientProfile();

        p3.setPatientId(
                "MT2026-0142"
        );

        p3.setPatientId(
                "HACKED-0000"
        );

        System.out.println(
                p3.getPatientId()
        );

        // Boolean JavaBean property
        p3.setDischarged(true);

        System.out.println(
                p3.isDischarged()
        );

        // Write-only PIN
        p3.setLockerPin("1234");
    }
}