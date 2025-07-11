public class FrontDeskStaff extends Staff {
    private boolean canHandleComplaints;

    public FrontDeskStaff(String staffId, String name, boolean canHandleComplaints) {
        super(staffId, name, "Front Desk");
        this.canHandleComplaints = canHandleComplaints;
    }

    @Override
    public void performDuties() {
        System.out.println(getName() + " is checking in guests, managing reservations, and providing customer service.");
    }

    public void assistGuest(String issue) {
        System.out.println(getName() + " is assisting with: " + issue);
    }

    public void assistGuest(String guestName, String issue) {
        System.out.println(this.getName() + " is assisting " + guestName + " with: " + issue);
    }

    public void assistGuest(Guest guest, String issue) {
        System.out.println(getName() + " is assisting " + guest.getFullName() + " with: " + issue);
    }

    public boolean canHandleComplaints() {
        return canHandleComplaints;
    }

    @Override
    public String toString() {
        return String.format("Front Desk Staff - ID: %s, Name: %s, Department: %s, Can Handle Complaints: %s]",
                getStaffId(), getName(), getDepartment(), canHandleComplaints);
    }
}
