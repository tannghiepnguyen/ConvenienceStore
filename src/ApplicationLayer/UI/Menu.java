/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ApplicationLayer.UI;

import ApplicationLayer.Utilities.DataInput;
import BusinessLayer.Service.ProductService;
import BusinessLayer.Service.WarehouseService;
import java.util.Arrays;

/**
 *
 * @author tanng
 */
public class Menu {
    public static void print(String str) {
        var menuList = Arrays.asList(str.split("\\|"));
        menuList.forEach(menuItem -> {
            if (menuItem.equalsIgnoreCase("Select")) {
                System.out.print(menuItem);
            } else {
                System.out.println(menuItem);
            }

        });
    }
    
    public static int getUserChoice() {
        int number = 0;
        try {
            number = DataInput.getIntegerNumber();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return number;
    }
    
    public static void manageProduct(ProductService productService){
        ProductMenu productMenu = new ProductMenu(productService);
        productMenu.processMenuForProduct();
    }
    
    public static void manageWarehouse(WarehouseService warehouseService){
        WarehouseMenu warehouseMenu = new WarehouseMenu(warehouseService);
        warehouseMenu.processMenuForWahehouse();
    }
    
    public static void manageReport(ProductService productService, WarehouseService warehouseService){
        ReportMenu reportMenu = new ReportMenu(productService, warehouseService);
        reportMenu.processMenuForReport();
    }
}
