import java.io.PrintStream;

public class Manager extends Staff {
    private String managementLevel;
    private int yearsExperience;

    public Manager(String staffId, String name, String managementLevel, int yearsExperience) {
        super(staffId, name, "Management");
        this.managementLevel = managementLevel;
        this.yearsExperience = yearsExperience;
    }

    public void performDuties() {
        System.out.println(this.getName() + " is overseeing hotel operations and managing staff.");
    }

    public void conductMeeting() {
        System.out.println(this.getName() + " is conducting a general staff meeting.");
    }

    public void conductMeeting(String topic) {
        PrintStream var10000 = System.out;
        String var10001 = this.getName();
        var10000.println(var10001 + " is conducting a meeting about: " + topic);
    }

    public void conductMeeting(String topic, int duration) {
        System.out.println(this.getName() + " is conducting a " + duration + "-minute meeting about: " + topic);
    }

    public String getManagementLevel() {
        return this.managementLevel;
    }

    public int getYearsExperience() {
        return this.yearsExperience;
    }

    public String toString() {
        return String.format("Manager [ID: %s, Name: %s, Level: %s, Experience: %d years]", this.getStaffId(), this.getName(), this.managementLevel, this.yearsExperience);
    }
}