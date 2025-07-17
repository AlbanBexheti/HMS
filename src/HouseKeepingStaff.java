import java.io.PrintStream;

public class HouseKeepingStaff extends Staff {
    private int roomsAssigned;

    public HouseKeepingStaff(String staffId, String name, int roomsAssigned) {
        super(staffId, name, "Housekeeping");
        this.roomsAssigned = roomsAssigned;
    }

    public void performDuties() {
        PrintStream var10000 = System.out;
        String var10001 = this.getName();
        var10000.println(var10001 + " is cleaning and maintaining " + this.roomsAssigned + " rooms.");
    }

    public void cleanRoom(int roomNumber) {
        PrintStream var10000 = System.out;
        String var10001 = this.getName();
        var10000.println(var10001 + " is cleaning room " + roomNumber);
    }

    public void cleanRoom(Room room) {
        PrintStream var10000 = System.out;
        String var10001 = this.getName();
        var10000.println(var10001 + " is cleaning " + room.getRoomType() + " room " + room.getRoomId());
    }

    public int getRoomsAssigned() {
        return this.roomsAssigned;
    }

    public String toString() {
        return String.format("Housekeeping Staff [ID: %s, Name: %s, Department: %s, Rooms Assigned: %d]", this.getStaffId(), this.getName(), this.getDepartment(), this.roomsAssigned);
    }

    public int countRoomsNeedingCleaning(char[] cleanlinessStatus) {
        if (cleanlinessStatus == null) {
            return 0;
        } else {
            int dirtyCount = 0;

            for(char status : cleanlinessStatus) {
                if (status == 'D') {
                    ++dirtyCount;
                }
            }

            return dirtyCount;
        }
    }
}
