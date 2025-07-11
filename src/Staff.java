public abstract class Staff {
    protected String staffId;
    protected String name;
    protected String department;


    public Staff(String staffId, String name, String department) {
        this.staffId = staffId;
        this.name = name;
        this.department = department;
    }

    public abstract void performDuties();

    public void greet() {
        System.out.println("Hello, welcome to our hotel!");
    }

    public void greet(String guestName) {
        System.out.println("Hello " + guestName + ", welcome to our hotel!");
    }

    protected String getStaffId() { return staffId; }
    protected String getName() { return name; }
    protected String getDepartment() { return department; }

    public String getStaffIdPublic() { return staffId; }
    public String getNamePublic() { return name; }
    public String getDepartmentPublic() { return department; }

    @Override
    public abstract String toString();
}