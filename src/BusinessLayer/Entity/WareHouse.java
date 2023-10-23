/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLayer.Entity;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author tanng
 */
public class WareHouse {
    private String code;
    private LocalDate timeSlip;
    private List<Product> items;

    public WareHouse() {
    }

    public WareHouse(String code, LocalDate timeSlip, List<Product> items) {
        this.code = code;
        this.timeSlip = timeSlip;
        this.items = items;
    }

    @Override
    public String toString() {
        String result = "Code=" + code + ", timeSlip=" + timeSlip + ", ";
        result += "[";
        for (Product item : items){
            result += item;
        }
        result += "]";
        return result;
    }
    
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getTimeSlip() {
        return timeSlip;
    }

    public void setTimeSlip(LocalDate timeSlip) {
        this.timeSlip = timeSlip;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }
    
}
