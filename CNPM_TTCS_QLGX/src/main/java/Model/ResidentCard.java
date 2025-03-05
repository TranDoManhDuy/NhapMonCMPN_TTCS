/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class ResidentCard {
    private String pk_resident_card;
    private int qr_index; 
    private String resident_card_id;    
    private int customer_id; 
    private boolean is_active;

    public ResidentCard() {
    }

    public ResidentCard(String resident_card_id, int customer_id, boolean is_active) {
        this.resident_card_id = resident_card_id;
        this.customer_id = customer_id;
        this.is_active = is_active;
    }
    
    public ResidentCard(String pk_resident_card, int qr_index, String resident_card_id, int customer_id, boolean is_active) {
        this.pk_resident_card = pk_resident_card;
        this.qr_index = qr_index;
        this.resident_card_id = resident_card_id;
        this.customer_id = customer_id;
        this.is_active = is_active;
    }

    public String getPk_resident_card() {
        return pk_resident_card;
    }

    public void setPk_resident_card(String pk_resident_card) {
        this.pk_resident_card = pk_resident_card;
    }

    public int getQr_index() {
        return qr_index;
    }

    public void setQr_index(int qr_index) {
        this.qr_index = qr_index;
    }

    public String getResident_card_id() {
        return resident_card_id;
    }

    public void setResident_card_id(String resident_card_id) {
        this.resident_card_id = resident_card_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    
    @Override
    public String toString() {
        return  pk_resident_card + " " + String.valueOf(qr_index) + " " + resident_card_id
                + " " + String.valueOf(customer_id) + " " + String.valueOf(is_active);
    }
}
