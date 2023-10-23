/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ApplicationLayer.UI;

import ApplicationLayer.Utilities.DataInput;
import BusinessLayer.Service.ProductService;
import BusinessLayer.Service.WarehouseService;

/**
 *
 * @author tanng
 */
public class ReportMenu {
    private final ProductService productService;
    private final WarehouseService warehouseService;

    public ReportMenu(ProductService productService, WarehouseService warehouseService) {
        this.productService = productService;
        this.warehouseService = warehouseService;
    }
    public void processMenuForReport() {
        boolean stop = false;
        try {
            do {
                Menu.print("******Report******|1.Products that have expired|2.Products that the store is selling|3.Products that are running out of stock|4.Import/export receipt of a product|5.Return to main menu|Select: ");
                int choice = Menu.getUserChoice();
                switch (choice) {
                    case 1 -> {
                        productService.printExpiredProduct();
                    }
                    case 2 -> {
                        productService.printSellingProduct();
                    }
                    case 3 -> {
                        productService.printOutofStockProduct();
                    }
                    case 4 -> {
                        warehouseService.importExportReceiptProduct();
                    }
                    case 5 -> {
                        stop = true;
                    }
                    default ->
                        System.out.println("Invalid input");
                }
            } while (!stop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}