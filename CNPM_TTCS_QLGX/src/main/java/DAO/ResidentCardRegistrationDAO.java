package DAO;

import DatabaseHelper.OpenConnection;
import InterfaceDAO.InterfaceDAO;
import Model.ResidentCardRegistration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ResidentCardRegistrationDAO implements InterfaceDAO<ResidentCardRegistration> {
    public static ResidentCardRegistrationDAO getInstance() {
        return new ResidentCardRegistrationDAO();
    }

    @Override
    public ArrayList<ResidentCardRegistration> getList() {
        ArrayList<ResidentCardRegistration> lstRegistrations = new ArrayList<>();
        String sql = "SELECT * FROM resident_card_registrations";
        try (
                Connection con = OpenConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lstRegistrations.add(new ResidentCardRegistration(
                        rs.getString("pk_resident_card"),
                        rs.getInt("registration_id")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstRegistrations;
    }

    @Override
    public boolean insert(ResidentCardRegistration registration) {
        String sql = "INSERT INTO resident_card_registrations (pk_resident_card, registration_id) VALUES (?, ?)";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, registration.getPk_resident_card());
            ps.setInt(2, registration.getRegistration_id());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ResidentCardRegistration registration) {
        String sql = "UPDATE resident_card_registrations SET registration_id = ? WHERE pk_resident_card = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, registration.getRegistration_id());
            ps.setString(2, registration.getPk_resident_card());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(ResidentCardRegistration registration) {
        String sql = "DELETE FROM resident_card_registrations WHERE pk_resident_card = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, registration.getPk_resident_card());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResidentCardRegistration findById(int pk_resident_card) {
        String sql = "SELECT * FROM resident_card_registrations WHERE pk_resident_card = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(pk_resident_card));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ResidentCardRegistration(
                            rs.getString("pk_resident_card"),
                            rs.getInt("registration_id")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
