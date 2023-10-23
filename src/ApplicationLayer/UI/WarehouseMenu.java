/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ApplicationLayer.UI;

import BusinessLayer.Service.WarehouseService;

/**
 *
 * @author tanng
 */
public class WarehouseMenu {

    private final WarehouseService warehouseService;

    public WarehouseMenu(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public void processMenuForWahehouse() {
        boolean stop = false;
        try {
            do {
                Menu.print("******Warehouse Management******|1.Create import receipt|2.Create export receipt|3.Return to main menu|Select: ");
                int choice = Menu.getUserChoice();
                switch (choice) {
                    case 1 -> {
                        warehouseService.add(WarehouseService.createReceiptWarehouse("import"));
                    }
                    case 2 ->{
                        warehouseService.add(WarehouseService.createReceiptWarehouse("export"));
                    }
                    case 3 -> {
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
