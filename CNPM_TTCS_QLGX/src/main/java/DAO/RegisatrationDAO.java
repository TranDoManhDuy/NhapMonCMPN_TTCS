/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DatabaseHelper.OpenConnection;
import Model.Regisatration;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author manhh
 */
public class RegisatrationDAO implements InterfaceDAO.InterfaceDAO<Regisatration>{
    public static RegisatrationDAO getInstance() {
        return new RegisatrationDAO();
    }
    @Override
    public ArrayList<Regisatration> getList() {
        ArrayList<Regisatration> listRegistration = new ArrayList<>();
        String sql = "SELECT * FROM registration";
        try (
            Connection conn = OpenConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                int regisatration_id = rs.getInt("registration_id");
                LocalDate regisatration_date = rs.getDate("registration_date").toLocalDate();
                int vehicle_id = rs.getInt("vehicle_id");
                char state = rs.getString("state").charAt(0);
                Regisatration registration = new Regisatration(customer_id, regisatration_id, regisatration_date, vehicle_id, state);
                listRegistration.add(registration);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listRegistration;
    }
    @Override
    public boolean insert(Regisatration regisatration) {
        String sql = "INSERT INTO Registration (customer_id, registration_date, vehicle_id, state) VALUES (?,?,?,?)";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt =  conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, regisatration.getCustomer_id());
            ptmt.setDate(2, Date.valueOf(regisatration.getRegistration_date()));
            ptmt.setInt(3 ,regisatration.getVehicle_id());
            ptmt.setString(4, String.valueOf(regisatration.getState()));
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean update(Regisatration registration) {
        String sql = "UPDATE registration SET customer_id = ?, registration_date = ?, vehicle_id = ?, state = ? WHERE registration_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt =  conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, registration.getCustomer_id());
            ptmt.setInt(5, registration.getRegistration_id());
            ptmt.setDate(2, Date.valueOf(registration.getRegistration_date()));
            ptmt.setInt(3, registration.getVehicle_id());
            ptmt.setString(4, String.valueOf(registration.getState()));
            
            return ptmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
   @Override
    public Regisatration findbyID(int id) {
       String sql = "SELECT * FROM registration WHERE registration_id = ?";
       try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
           ptmt.setInt(1, id);
           ResultSet rs = ptmt.executeQuery();
           
          if (rs.next()) {
               int customer_id = rs.getInt("customer_id");
                int registration_id = rs.getInt("registration_id");
                LocalDate registration_date = rs.getDate("registration_date").toLocalDate();
                int vehicle_id = rs.getInt("vehicle_id");
                char state = rs.getString("state").charAt(0);
                Regisatration registration = new Regisatration(customer_id, registration_id, registration_date, vehicle_id, state);
                
                return registration;
          }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Registration WHERE registration_id = ?";
        try (
            Connection conn = OpenConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
        ) {
            ptmt.setInt(1, id);
            return ptmt.executeUpdate()> 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        ArrayList<Regisatration> list = RegisatrationDAO.getInstance().getList();
        RegisatrationDAO.getInstance().delete(0);
        list.forEach(x -> {
            System.out.println(x.getRegistration_id());
        });
    }
}