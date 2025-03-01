/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class ParkingSession {
    private int parking_session_id;
    private String card_id;
    private boolean is_service;
    private LocalDate check_in_time;
    private LocalDate check_out_time; 
    private int check_in_shift_id;
    private int check_out_shift_id;
    private int vehicle_id;
    private int amount; 

    public ParkingSession() {
    }

    public ParkingSession(int parking_session_id, String card_id, boolean is_service, LocalDate check_in_time, LocalDate check_out_time, int check_in_shift_id, int check_out_shift_id, int vehicle_id, int amount) {
        this.parking_session_id = parking_session_id;
        this.card_id = card_id;
        this.is_service = is_service;
        this.check_in_time = check_in_time;
        this.check_out_time = check_out_time;
        this.check_in_shift_id = check_in_shift_id;
        this.check_out_shift_id = check_out_shift_id;
        this.vehicle_id = vehicle_id;
        this.amount = amount;
    }

    public int getParking_session_id() {
        return parking_session_id;
    }

    public void setParking_session_id(int parking_session_id) {
        this.parking_session_id = parking_session_id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public boolean isIs_service() {
        return is_service;
    }

    public void setIs_service(boolean is_service) {
        this.is_service = is_service;
    }

    public LocalDate getCheck_in_time() {
        return check_in_time;
    }

    public void setCheck_in_time(LocalDate check_in_time) {
        this.check_in_time = check_in_time;
    }

    public LocalDate getCheck_out_time() {
        return check_out_time;
    }

    public void setCheck_out_time(LocalDate check_out_time) {
        this.check_out_time = check_out_time;
    }

    public int getCheck_in_shift_id() {
        return check_in_shift_id;
    }

    public void setCheck_in_shift_id(int check_in_shift_id) {
        this.check_in_shift_id = check_in_shift_id;
    }

    public int getCheck_out_shift_id() {
        return check_out_shift_id;
    }

    public void setCheck_out_shift_id(int check_out_shift_id) {
        this.check_out_shift_id = check_out_shift_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
