import java.io.PrintStream;

public class FrontDeskStaff extends Staff {
    private boolean canHandleComplaints;

    public FrontDeskStaff(String staffId, String name, boolean canHandleComplaints) {
        super(staffId, name, "Front Desk");
        this.canHandleComplaints = canHandleComplaints;
    }

    public void performDuties() {
        System.out.println(this.getName() + " is checking in guests, managing reservations, and providing customer service.");
    }

    public void assistGuest(String issue) {
        PrintStream var10000 = System.out;
        String var10001 = this.getName();
        var10000.println(var10001 + " is assisting with: " + issue);
    }

    public void assistGuest(String guestName, String issue) {
        System.out.println(this.getName() + " is assisting " + guestName + " with: " + issue);
    }

    public void assistGuest(Guest guest, String issue) {
        PrintStream var10000 = System.out;
        String var10001 = this.getName();
        var10000.println(var10001 + " is assisting " + guest.getFullName() + " with: " + issue);
    }

    public boolean canHandleComplaints() {
        return this.canHandleComplaints;
    }

    public String toString() {
        return String.format("Front Desk Staff - ID: %s, Name: %s, Department: %s, Can Handle Complaints: %s]", this.getStaffId(), this.getName(), this.getDepartment(), this.canHandleComplaints);
    }

    public void handleComplaints(String[] complaints) {
        if (complaints != null) {
            for(String complaint : complaints) {
                switch (complaint.toLowerCase()) {
                    case "wifi":
                        System.out.println("Contacting IT department about WiFi issues");
                        break;
                    case "cleanliness":
                        System.out.println("Notifying housekeeping about cleanliness concerns");
                        break;
                    case "noise":
                        System.out.println("Sending security to address noise complaint");
                        break;
                    default:
                        System.out.println("Forwarding general complaint to manager: " + complaint);
                }
            }

        }
    }
}
