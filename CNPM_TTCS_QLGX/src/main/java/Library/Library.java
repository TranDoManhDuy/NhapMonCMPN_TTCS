/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author manhh
 */
public class Library {
    // Ten chi gom a - z, A - Z
    public static boolean isValidString(String str) {
        return str.matches("[a-zA-Z ]+");
    }
    
    // email validate
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
    
    // kiem tra cccd
    public static boolean isValidSSN(String str) {
        return str.matches("\\d{12}");
    }
    
    // in dinh dang tien te
    public static String formatCurrency(int amount) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US); // Định dạng theo US (1,000,000)
        return formatter.format(amount);
    }
    
    public static void main(String[] args) {
        
    }
}