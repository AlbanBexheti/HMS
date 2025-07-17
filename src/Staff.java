public abstract class Staff {
    protected String staffId;
    protected String name;
    protected String department;
    protected int tasksCompleted;

    public Staff(String staffId, String name, String department) {
        this.staffId = staffId;
        this.name = name;
        this.department = department;
        this.tasksCompleted = 0;
    }

    public abstract void performDuties();

    public void greet() {
        System.out.println("Hello, welcome to our hotel!");
    }

    public void greet(String guestName) {
        System.out.println("Hello " + guestName + ", welcome to our hotel!");
    }

    public void incrementTasksCompleted() {
        this.tasksCompleted++;
    }

    public int getTasksCompleted() {
        return this.tasksCompleted;
    }

    protected String getStaffId() {
        return this.staffId;
    }

    protected String getName() {
        return this.name;
    }

    protected String getDepartment() {
        return this.department;
    }

    public String getStaffIdPublic() {
        return this.staffId;
    }

    public String getNamePublic() {
        return this.name;
    }

    public String getDepartmentPublic() {
        return this.department;
    }

    public abstract String toString();

    public int findHighestPriorityTask(int[] taskPriorities) {
        if (taskPriorities == null || taskPriorities.length == 0) {
            return -1;
        }

        int highestPriorityIndex = 0;
        for(int i = 1; i < taskPriorities.length; i++) {
            if (taskPriorities[i] < taskPriorities[highestPriorityIndex]) {
                highestPriorityIndex = i;
            }
        }
        return highestPriorityIndex;
    }
}