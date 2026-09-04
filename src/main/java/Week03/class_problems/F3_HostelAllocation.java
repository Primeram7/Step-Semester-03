package Week03.class_problems;
public class F3_HostelAllocation {

    static class HostelRoom {
        private String roomNo;
        private int beds;
        private int occupied;

        public HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        public void allot(String name) {
            if (occupied < beds) {
                occupied++;
            }
        }

        public String getRoomNo() {
            return roomNo;
        }

        public int getBeds() {
            return beds;
        }

        public int getOccupied() {
            return occupied;
        }
    }

    // Arrays in Java hold references, not copies. Passing a HostelRoom[] into
    // these methods passes the same references the caller has, so mutating a
    // room inside the method (occupied++) is visible back in main — nothing
    // about the rooms themselves gets duplicated.
    public static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (HostelRoom room : rooms) {
            if (room.getOccupied() < room.getBeds()) {
                return room;
            }
        }
        return null;
    }

    public static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);
        if (room == null) {
            System.out.println("No rooms available for " + studentName);
        } else {
            room.allot(studentName);
            System.out.println(studentName + " allotted to room " + room.getRoomNo());
        }
    }

    public static void main(String[] args) {
        HostelRoom[] roomsWithSpace = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };
        safeAllot(roomsWithSpace, "Divya");

        HostelRoom[] fullRooms = {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };
        safeAllot(fullRooms, "Divya");
    }
}
