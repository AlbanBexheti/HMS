public class Manager extends Staff {
    private String managementLevel;
    private int yearsExperience;

    public Manager(String staffId, String name, String managementLevel, int yearsExperience) {
        super(staffId, name, "Management");
        this.managementLevel = managementLevel;
        this.yearsExperience = yearsExperience;
    }

    @Override
    public void performDuties() {
        System.out.println(getName() + " is overseeing hotel operations and managing staff.");
    }

    public void conductMeeting() {
        System.out.println(getName() + " is conducting a general staff meeting.");
    }

    public void conductMeeting(String topic) {
        System.out.println(getName() + " is conducting a meeting about: " + topic);
    }

    public void conductMeeting(String topic, int duration) {
        System.out.println(getName() + " is conducting a " + duration + "-minute meeting about: " + topic);
    }

    public String getManagementLevel() {
        return managementLevel;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    @Override
    public String toString() {
        return String.format("Manager [ID: %s, Name: %s, Level: %s, Experience: %d years]",
                getStaffId(), getName(), managementLevel, yearsExperience);
    }
}