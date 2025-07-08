public class Guest {
    private int guestId;
    private String guestName;
    private String guestLastname;
    private String guestEmail;

    //Default Constructor
    public Guest() {
        this.guestId = 0;
        this.guestName = " ";
        this.guestLastname = " ";
        this.guestEmail = " ";
    }

    //Paramaetrized Constructor
    public Guest(int guestId, String guestName, String guestLastname, String guestEmail) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.guestLastname = guestLastname;
        this.guestEmail = guestEmail;
    }

    //Getter Method
    public int getGuestId() {
        return guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getGuestLastname() {
        return guestLastname;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public String getFullName() {
        return guestName + " " + guestLastname;
    }

    @Override
    public String toString() {
        return "Guest{" + "ID: " + guestId +
                ", Name: " + guestName +
                ", Lastname: " + guestLastname +
                ", Email: " + guestEmail + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Guest guest = (Guest) object;
        return guestId == guest.guestId;

    }
}
