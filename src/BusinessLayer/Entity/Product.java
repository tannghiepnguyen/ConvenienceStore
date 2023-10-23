/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLayer.Entity;

import java.time.LocalDate;

/**
 *
 * @author tanng
 */
public class Product {
    private String code;
    private String name;
    private LocalDate manufacturingDate;
    private LocalDate expirationDate;
    private int quantity;

    public Product() {
    }

    public Product(String code, String name, LocalDate manufacturingDate, LocalDate expirationDate, int quantity) {
        this.code = code;
        this.name = name;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Code=" + code + ", name=" + name + ", manufacturingDate=" + manufacturingDate + ", expirationDate=" + expirationDate + ", quantity=" + quantity;
    }
}
