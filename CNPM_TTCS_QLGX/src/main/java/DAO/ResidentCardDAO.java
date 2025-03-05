package DAO;

import DatabaseHelper.OpenConnection;
import InterfaceDAO.InterfaceDAO;
import Model.Customer;
import Model.ResidentCard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * DAO cho bảng resident_cards
 */
public class ResidentCardDAO implements InterfaceDAO<ResidentCard> {
    
    public static ResidentCardDAO getInstance() {
        return new ResidentCardDAO();
    }

    @Override
    public ArrayList<ResidentCard> getList() {
        ArrayList<ResidentCard> lstCards = new ArrayList<>();
        String sql = "SELECT * FROM resident_cards";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                String pk_resident_card = rs.getString("pk_resident_card");
                int qr_index = rs.getInt("qr_index");
                String resident_card_id = rs.getString("resident_card_id");
                int customer_id = rs.getInt("customer_id");
                boolean is_active = rs.getBoolean("is_active");

                ResidentCard card = new ResidentCard(pk_resident_card, qr_index, resident_card_id, customer_id, is_active);
                lstCards.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCards;
    }

    @Override
    public boolean insert(ResidentCard card) {
        String sql = "INSERT INTO resident_cards (resident_card_id, customer_id, is_active) VALUES (?, ?, ?)";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, card.getResident_card_id());
            ps.setInt(2, card.getCustomer_id());
            ps.setBoolean(3, card.isIs_active());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ResidentCard card) {
        String sql = "UPDATE resident_cards SET resident_card_id = ?, customer_id = ?, is_active = ? WHERE pk_resident_card = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, card.getResident_card_id());
            ps.setInt(2, card.getCustomer_id());
            ps.setBoolean(3, card.isIs_active());
            ps.setString(4, card.getPk_resident_card());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(ResidentCard card) {
        String sql = "DELETE FROM resident_cards WHERE pk_resident_card = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, card.getPk_resident_card());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResidentCard findById(int id) {
        String sql = "SELECT * FROM resident_cards WHERE pk_resident_card = ?";
        try (
                Connection con = OpenConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, String.valueOf(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String pk_resident_card = rs.getString("pk_resident_card");
                    int qr_index = rs.getInt("qr_index");
                    String resident_card_id = rs.getString("resident_card_id");
                    int customer_id = rs.getInt("customer_id");
                    boolean is_active = rs.getBoolean("is_active");

                    return new ResidentCard(pk_resident_card, qr_index, resident_card_id, customer_id, is_active);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        ResidentCard resident1 = new ResidentCard("11", 1, "1", 5, false);
        ResidentCard resident2 = new ResidentCard("12", 3, "1", 9, true);
        ResidentCard r = new ResidentCard("3", 11, true);
        ResidentCardDAO reDao = ResidentCardDAO.getInstance();
        reDao.insert(r);
//        reDao.update(resident2);
//        ResidentCard re1 = reDao.findById(Integer.valueOf("12"));
//        if (re1 != null) {
//            System.out.println(re1.getCustomer_id());
//        }
//
//        ArrayList<ResidentCard> lstRe = reDao.getList();
//        
//        if (lstRe != null) {
//            for (ResidentCard resident : lstRe) {
//                System.out.println(resident.getCustomer_id());
//            }
//        }
//        reDao.delete(resident2);
    }
}
