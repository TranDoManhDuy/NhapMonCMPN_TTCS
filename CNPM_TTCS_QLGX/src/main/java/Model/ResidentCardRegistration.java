/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class ResidentCardRegistration {
    private String pk_resident_card; 
    private int registration_id;

    public ResidentCardRegistration() {
    }

    public ResidentCardRegistration(String pk_resident_card, int registration_if) {
        this.pk_resident_card = pk_resident_card;
        this.registration_id = registration_if;
    }

    public String getPk_resident_card() {
        return pk_resident_card;
    }

    public void setPk_resident_card(String pk_resident_card) {
        this.pk_resident_card = pk_resident_card;
    }

    public int getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(int registration_if) {
        this.registration_id = registration_if;
    }
}
