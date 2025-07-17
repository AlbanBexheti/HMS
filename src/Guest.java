public class Guest {
    private int guestId;
    private String guestName;
    private String guestLastname;
    private String guestEmail;

    public Guest() {
        this.guestId = 0;
        this.guestName = " ";
        this.guestLastname = " ";
        this.guestEmail = " ";
    }

    public Guest(int guestId, String guestName, String guestLastname, String guestEmail) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.guestLastname = guestLastname;
        this.guestEmail = guestEmail;
    }

    public int getGuestId() {
        return this.guestId;
    }

    public String getGuestName() {
        return this.guestName;
    }

    public String getGuestLastname() {
        return this.guestLastname;
    }

    public String getGuestEmail() {
        return this.guestEmail;
    }

    public String getFullName() {
        return this.guestName + " " + this.guestLastname;
    }

    public String toString() {
        return "Guest{ID: " + this.guestId + ", Name: " + this.guestName + ", Lastname: " + this.guestLastname + ", Email: " + this.guestEmail + "}";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            Guest guest = (Guest)object;
            return this.guestId == guest.guestId;
        } else {
            return false;
        }
    }

    public String buildGreetingMessage(char[] template) {
        if (template == null) {
            return "";
        } else {
            StringBuilder greeting = new StringBuilder();

            for(char c : template) {
                if (c == '*') {
                    greeting.append(this.getGuestName().charAt(0));
                } else if (c == '#') {
                    greeting.append(this.getGuestLastname().charAt(0));
                } else if (c == '@') {
                    greeting.append(this.getGuestEmail().charAt(0));
                } else {
                    greeting.append(c);
                }
            }

            return greeting.toString();
        }
    }
}