/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ApplicationLayer.UI;

import java.util.Collections;
import BusinessLayer.Service.ProductService;
import BusinessLayer.Service.WarehouseService;

/**
 *
 * @author tanng
 */
public class Program {

    public static void main(String[] args) {
        int choice;
        System.out.println(String.join("", Collections.nCopies(10, "**********")));
        try {
            ProductService productService = new ProductService();
            WarehouseService warehouseService = new WarehouseService();
            warehouseService.print();
            do {                
                Menu.print("1.Product Management|2.Warehouse Management|3.Report|4.Save to files|5.Exit|Select:");
                choice = Menu.getUserChoice();
                switch (choice) {
                    case 1 -> {
                        Menu.manageProduct(productService);
                    }
                    case 2 -> {
                        Menu.manageWarehouse(warehouseService);
                    }
                    case 3 -> {
                        Menu.manageReport(productService, warehouseService);
                    }
                    case 4 -> {
                        productService.writeProductToFile();
                        warehouseService.writeWarehouseToFile();
                        System.out.println("Saving successfully");
                    }
                    default -> {
                        System.out.println("Good bye !");
                        System.exit(0);
                    }
                }
            } while (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
