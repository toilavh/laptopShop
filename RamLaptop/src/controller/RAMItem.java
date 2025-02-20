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
import java.io.Serializable;

public class RAMItem implements Serializable {

    private String code; // Unique identifier
    private String type; // Type of RAM
    private String bus;  // Bus speed
    private String brand; // Brand of RAM
    private int quantity; // Quantity in stock
    private String productionMonthYear; // Production date
    private boolean active; // Active status

    public RAMItem(String code, String type, String bus, String brand, int quantity, String productionMonthYear) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.productionMonthYear = productionMonthYear;
        this.active = true; // Active by default
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductionMonthYear() {
        return productionMonthYear;
    }

    public void setProductionMonthYear(String productionMonthYear) {
        this.productionMonthYear = productionMonthYear;
    }

    public boolean isActive() {
        return active;
    }

    // Getters and Setters
    public void setActive(boolean active) {
        this.active = active;
    }

    // Other methods
    public void update(String type, String bus, String brand, int quantity) {
        if (type != null) {
            this.type = type;
        }
        if (bus != null) {
            this.bus = bus;
        }
        if (brand != null) {
            this.brand = brand;
        }
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }

    @Override
    public String toString() {
        return "RAMItem{"
                + "code='" + code + '\''
                + ", type='" + type + '\''
                + ", bus='" + bus + '\''
                + ", brand='" + brand + '\''
                + ", quantity=" + quantity
                + ", productionMonthYear='" + productionMonthYear + '\''
                + ", active=" + active
                + '}';
    }
}
