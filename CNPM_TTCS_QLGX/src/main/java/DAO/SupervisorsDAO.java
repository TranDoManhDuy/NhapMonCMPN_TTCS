package DAO;

import Model.Supervisors;
import java.util.ArrayList;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SupervisorsDAO {
    public static SupervisorsDAO getInstance() {
        return new SupervisorsDAO();
    }

    public ArrayList<Supervisors> getListSupervisors() {
        ArrayList<Supervisors> list_supervisors = new ArrayList<>();
        String sql = "SELECT * FROM supervisors";
        try (
                Connection conn = OpenConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(sql);
        ) {
            while (result.next()) {
                int manager_id = result.getInt("manager_id");
                int staff_id = result.getInt("staff_id");
                list_supervisors.add(new Supervisors(manager_id, staff_id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_supervisors;
    }
    
    public boolean insert(Supervisors supervisor) {
        String sql = "INSERT INTO supervisors (manager_id, staff_id) VALUES (?, ?)";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, supervisor.getManagerId());
            ptmt.setInt(2, supervisor.getStaffId());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(Supervisors supervisor) {
        String sql = "UPDATE supervisors SET manager_id = ?, staff_id = ? WHERE manager_id = ? AND staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, supervisor.getManagerId());
            ptmt.setInt(2, supervisor.getStaffId());
            ptmt.setInt(3, supervisor.getManagerId());
            ptmt.setInt(4, supervisor.getStaffId());
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Supervisors findById(int manager_id, int staff_id) {
        String sql = "SELECT * FROM supervisors WHERE manager_id = ? AND staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, manager_id);
            ptmt.setInt(2, staff_id);
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                return new Supervisors(rs.getInt("manager_id"), rs.getInt("staff_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean delete(int manager_id, int staff_id) {
        String sql = "DELETE FROM supervisors WHERE manager_id = ? AND staff_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, manager_id);
            ptmt.setInt(2, staff_id);
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
