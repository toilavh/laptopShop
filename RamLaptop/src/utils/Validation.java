/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Admin
 */
import controller.RAMManagementSystem;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    // Validate RAM type (ensure it's one of the predefined types)
    public static boolean validateType(String type) {
        List<String> validTypes = Arrays.asList("LPDDR5", "DDR5", "LPDDR4", "DDR4");
        return validTypes.contains(type);
    }

    // Validate bus speed (ensure it's a valid numeric MHz value)
    public static boolean validateBus(String bus) {
        try {
            // Extract numeric value from the bus speed string (e.g., "5600MHz" -> 5600)
            int busSpeed = Integer.parseInt(bus.replaceAll("[^0-9]", ""));
            return busSpeed > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate production date format MM/YYYY
    public static boolean validateDate(String date) {
        // Use regular expression to validate MM/YYYY format
        String regex = "^(0[1-9]|1[0-2])/([0-9]{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    // Validate quantity (positive integer)
    public static boolean validateQuantity(int quantity) {
        return quantity > 0;
    }

    // Validate code uniqueness (call this from RAMManagementSystem)
    public static boolean validateUniqueCode(String code, RAMManagementSystem system) {
        return system.getItemByCode(code) == null;
    }

    // Validate the brand (ensure it's non-empty)
    public static boolean validateBrand(String brand) {
        return brand != null && !brand.trim().isEmpty();
    }
}
