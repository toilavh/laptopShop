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
import java.util.Scanner;
import utils.Validation;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RAMManagementSystem system = new RAMManagementSystem();

        while (true) {
            System.out.println("\n--- RAM Management System ---");
            System.out.println("1. Add RAM Item");
            System.out.println("2. Update RAM Item");
            System.out.println("3. Delete RAM Item");
            System.out.println("4. Show All Active Items");
            System.out.println("5. Save and Exit");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Add RAM Item
                    System.out.print("Enter code (e.g., RAM1_1): ");
                    String code = scanner.nextLine();

                    System.out.print("Enter type (e.g., LPDDR5, DDR5): ");
                    String type = scanner.nextLine();

                    System.out.print("Enter bus speed (e.g., 5600MHz): ");
                    String bus = scanner.nextLine();

                    System.out.print("Enter brand: ");
                    String brand = scanner.nextLine();

                    int quantity = -1;
                    while (quantity < 0) {
                        System.out.print("Enter quantity (positive integer): ");
                        quantity = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        if (quantity < 0) {
                            System.out.println("Error: Quantity must be a positive integer.");
                        }
                    }

                    String productionMonthYear = "";
                    while (!Validation.validateDate(productionMonthYear)) {
                        System.out.print("Enter production month/year (MM/YYYY): ");
                        productionMonthYear = scanner.nextLine();
                        if (!Validation.validateDate(productionMonthYear)) {
                            System.out.println("Error: Invalid date format. Use MM/YYYY.");
                        }
                    }

                    RAMItem newItem = new RAMItem(code, type, bus, brand, quantity, productionMonthYear);
                    system.addRAMItem(newItem);
                    break;

                case 2:
                    // Update RAM Item
                    System.out.print("Enter code of item to update: ");
                    String updateCode = scanner.nextLine();

                    System.out.print("Enter new type (or press Enter to keep current): ");
                    String updateType = scanner.nextLine();

                    System.out.print("Enter new bus speed (or press Enter to keep current): ");
                    String updateBus = scanner.nextLine();

                    System.out.print("Enter new brand (or press Enter to keep current): ");
                    String updateBrand = scanner.nextLine();

                    System.out.print("Enter new quantity (or -1 to keep current): ");
                    int updateQuantity = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    system.updateRAMItem(updateCode,
                            updateType.isEmpty() ? null : updateType,
                            updateBus.isEmpty() ? null : updateBus,
                            updateBrand.isEmpty() ? null : updateBrand,
                            updateQuantity == -1 ? -1 : updateQuantity);
                    break;

                // Rest of the cases remain the same (delete, show, etc.)
                case 3:
                    // Delete RAM Item
                    System.out.print("Enter code of item to delete: ");
                    String deleteCode = scanner.nextLine();
                    system.deleteRAMItem(deleteCode);
                    break;

                case 4:
                    // Show All Active Items
                    system.showAllItems();
                    break;

                case 5:
                    // Save and Exit
                    system.saveToFile();
                    System.out.println("Data saved. Exiting...");
                    return;

                case 6:
                    // Quit without saving
                    System.out.println("Exiting without saving.");
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
