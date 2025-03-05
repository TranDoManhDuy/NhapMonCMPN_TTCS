package Model;

public class Supervisors {
    private int manager_id;
    private int staff_id;

    public Supervisors() {}

    public Supervisors(int manager_id, int staff_id) {
        this.manager_id = manager_id;
        this.staff_id = staff_id;
    }

    public int getManagerId() {
        return manager_id;
    }

    public void setManagerId(int manager_id) {
        this.manager_id = manager_id;
    }

    public int getStaffId() {
        return staff_id;
    }

    public void setStaffId(int staff_id) {
        this.staff_id = staff_id;
    }

    @Override
    public String toString() {
        return "Supervisors{" +
                "manager_id=" + manager_id +
                ", staff_id=" + staff_id +
                '}';
    }
}
