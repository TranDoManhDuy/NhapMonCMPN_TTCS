/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class LostResidentCard {
    private String pk_resident_card;
    private int parking_session_id;

    public LostResidentCard() {
    }

    public LostResidentCard(String pk_resident_card, int parking_session_id) {
        this.pk_resident_card = pk_resident_card;
        this.parking_session_id = parking_session_id;
    }

    public String getPk_resident_card() {
        return pk_resident_card;
    }

    public void setPk_resident_card(String pk_resident_card) {
        this.pk_resident_card = pk_resident_card;
    }

    public int getParking_session_id() {
        return parking_session_id;
    }

    public void setParking_session_id(int parking_session_id) {
        this.parking_session_id = parking_session_id;
    }
}
