package Week03.assignment_problems;
class ParkingSlot {

    String slotNo;
    int capacity;
    int occupiedCount;

    // Constructor
    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    // Allot a vehicle to this slot
    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
        }
    }

    // Find the first available parking slot
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {

            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }

        // No slot is available
        return null;
    }

    // Safely allot a vehicle
    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {

        ParkingSlot slot = findAvailableSlot(slots);

        // Check for null before using the object
        if (slot != null) {
            slot.allot(vehicleNo);
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }

    public static void main(String[] args) {

        // Test 1: One slot is available
        ParkingSlot slot1 = new ParkingSlot("A1", 4, 3);
        ParkingSlot slot2 = new ParkingSlot("A2", 5, 5);

        ParkingSlot[] slots = {slot1, slot2};

        safeAllot(slots, "TN09AB1234");

        // Test 2: All slots are full
        ParkingSlot full1 = new ParkingSlot("A1", 4, 4);
        ParkingSlot full2 = new ParkingSlot("A2", 5, 5);

        ParkingSlot[] fullSlots = {full1, full2};

        safeAllot(fullSlots, "TN09AB1234");
    }
}