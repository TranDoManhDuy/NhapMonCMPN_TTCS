package DAO;

import Model.RolesRights;
import DatabaseHelper.OpenConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RolesRightsDAO {

    public static RolesRightsDAO getInstance() {
        return new RolesRightsDAO();
    }
    
    public ArrayList<RolesRights> getAllRolesRights() {
        ArrayList<RolesRights> list = new ArrayList<>();
        String sql = "SELECT * FROM roles_rights";

        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                int roleId = rs.getInt("role_id");
                int rightId = rs.getInt("right_id");
                list.add(new RolesRights(roleId, rightId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(RolesRights rolesRights) {
        String sql = "INSERT INTO roles_rights (role_id, right_id) VALUES (?, ?)";

        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, rolesRights.getRoleId());
            ptmt.setInt(2, rolesRights.getRightId());

            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int roleId, int rightId) {
        String sql = "DELETE FROM roles_rights WHERE role_id = ? AND right_id = ?";

        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, roleId);
            ptmt.setInt(2, rightId);

            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Integer> getRightsByRoleId(int roleId) {
        ArrayList<Integer> rightsList = new ArrayList<>();
        String sql = "SELECT right_id FROM roles_rights WHERE role_id = ?";

        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, roleId);
            ResultSet rs = ptmt.executeQuery();

            while (rs.next()) {
                rightsList.add(rs.getInt("right_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rightsList;
    }
}
