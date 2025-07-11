public class Housekeepingstaff extends Staff {
    private int roomsAssigned;

    public Housekeepingstaff(String staffId, String name, int roomsAssigned) {
        super(staffId, name, "Housekeeping");
        this.roomsAssigned = roomsAssigned;
    }

    @Override
    public void performDuties() {
        System.out.println(getName() + " is cleaning and maintaining " + roomsAssigned + " rooms.");
    }

    public void cleanRoom(int roomNumber) {
        System.out.println(getName() + " is cleaning room " + roomNumber);
    }

    public void cleanRoom(Room room) {
        System.out.println(getName() + " is cleaning " + room.getRoomType() + " room " + room.getRoomId());
    }

    public int getRoomsAssigned() {
        return roomsAssigned;
    }

    @Override
    public String toString() {
        return String.format("Housekeeping Staff [ID: %s, Name: %s, Department: %s, Rooms Assigned: %d]",
                getStaffId(), getName(), getDepartment(), roomsAssigned);
    }
}
