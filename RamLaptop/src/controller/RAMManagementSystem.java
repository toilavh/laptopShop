/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Admin
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import utils.Validation;

public class RAMManagementSystem {

    private List<RAMItem> ramItems;

    // Constructor - initializes the list of RAM items
    public RAMManagementSystem() {
        this.ramItems = new ArrayList<>();
    }

    // Add a new RAM item to the system after validating inputs
    public void addRAMItem(RAMItem item) {
        // Validate all the fields using the Validation class
        if (!Validation.validateUniqueCode(item.getCode(), this)) {
            System.out.println("Error: A RAM item with this code already exists.");
            return;
        }
        if (!Validation.validateType(item.getType())) {
            System.out.println("Error: Invalid RAM type.");
            return;
        }
        if (!Validation.validateBus(item.getBus())) {
            System.out.println("Error: Invalid bus speed.");
            return;
        }
        if (!Validation.validateQuantity(item.getQuantity())) {
            System.out.println("Error: Invalid quantity. It must be a positive integer.");
            return;
        }
        if (!Validation.validateDate(item.getProductionMonthYear())) {
            System.out.println("Error: Invalid production date format. Use MM/YYYY.");
            return;
        }
        if (!Validation.validateBrand(item.getBrand())) {
            System.out.println("Error: Brand cannot be empty.");
            return;
        }

        // If all validations pass, add the item to the list
        ramItems.add(item);
        System.out.println("RAM item added successfully!");
    }

    // Search for a RAM item by type, bus, or brand
    public void searchRAMItem(String field, String value) {
        System.out.println("\n--- Search Results ---");
        boolean found = false;

        for (RAMItem item : ramItems) {
            if (!item.isActive()) {
                continue; // Skip inactive items
            }
            // Search by type, bus, or brand
            if ((field.equals("type") && item.getType().equalsIgnoreCase(value))
                    || (field.equals("bus") && item.getBus().equalsIgnoreCase(value))
                    || (field.equals("brand") && item.getBrand().equalsIgnoreCase(value))) {
                found = true;
                System.out.println(item);
            }
        }

        if (!found) {
            System.out.println("No RAM items found for the given " + field + ".");
        }
    }

    // Update the information of an existing RAM item by its code
    public void updateRAMItem(String code, String newType, String newBus, String newBrand, int newQuantity) {
        RAMItem item = getItemByCode(code);

        if (item != null && item.isActive()) {
            // Validate updated fields before applying changes
            if (newType != null && !newType.isEmpty() && !Validation.validateType(newType)) {
                System.out.println("Error: Invalid RAM type.");
                return;
            }
            if (newBus != null && !newBus.isEmpty() && !Validation.validateBus(newBus)) {
                System.out.println("Error: Invalid bus speed.");
                return;
            }
            if (newQuantity > 0 && !Validation.validateQuantity(newQuantity)) {
                System.out.println("Error: Invalid quantity.");
                return;
            }
            if (newBrand != null && !newBrand.isEmpty() && !Validation.validateBrand(newBrand)) {
                System.out.println("Error: Invalid brand.");
                return;
            }

            // Apply the updates to the RAM item
            if (!newType.isEmpty()) {
                item.setType(newType);
            }
            if (!newBus.isEmpty()) {
                item.setBus(newBus);
            }
            if (!newBrand.isEmpty()) {
                item.setBrand(newBrand);
            }
            if (newQuantity > 0) {
                item.setQuantity(newQuantity);
            }

            System.out.println("RAM item updated successfully.");
        } else {
            System.out.println("Error: RAM item not found or is inactive.");
        }
    }

    // Delete a RAM item by marking it as inactive
    public void deleteRAMItem(String code) {
        RAMItem item = getItemByCode(code);

        if (item != null && item.isActive()) {
            item.setActive(false);  // Mark item as inactive
            System.out.println("RAM item deleted successfully.");
        } else {
            System.out.println("Error: RAM item not found or already inactive.");
        }
    }

    // Display all active RAM items sorted by type, bus, and brand
    public void showAllItems() {
        System.out.println("\n--- All Active RAM Items ---");

        // Sort the list of RAM items by type, bus, and brand
        ramItems.stream()
                .filter(RAMItem::isActive)
                .sorted(Comparator.comparing(RAMItem::getType)
                        .thenComparing(RAMItem::getBus)
                        .thenComparing(RAMItem::getBrand))
                .forEach(System.out::println);
    }

    // Find and return a RAM item by its code
    public RAMItem getItemByCode(String code) {
        for (RAMItem item : ramItems) {
            if (item.getCode().equalsIgnoreCase(code) && item.isActive()) {
                return item;
            }
        }
        return null; // No active item found with the specified code
    }

    // Save the current list of RAM items to a binary file
    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("RAMModules.dat"))) {
            out.writeObject(ramItems);
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    // Load the list of RAM items from a binary file at startup
    public void loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("RAMModules.dat"))) {
            ramItems = (List<RAMItem>) in.readObject();
            System.out.println("Data loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }
}
