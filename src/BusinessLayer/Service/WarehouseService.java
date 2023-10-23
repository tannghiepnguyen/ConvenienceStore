/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLayer.Service;

import ApplicationLayer.Utilities.DataInput;
import BusinessLayer.Component.DataValidation;
import BusinessLayer.Entity.Product;
import BusinessLayer.Entity.WareHouse;
import DataLayer.WareHouse.WareHouseDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tanng
 */
public class WarehouseService {

    protected static List<WareHouse> warehouseList;
    private final WareHouseDAO warehousedao;

    public WarehouseService() throws Exception {
        this.warehousedao = new WareHouseDAO();
        WarehouseService.warehouseList = warehousedao.getList();
    }
    
    public void print(){
        for (WareHouse a : warehouseList){
            System.out.println(a);
        }
    }

    public void add(WareHouse warehouse) {
        WarehouseService.warehouseList.add(warehouse);
        System.out.println("Adding successfully");
    }

    public static void updateItemInformation(String code) {
        for (int i = 0; i < warehouseList.size(); i++) {
            List<Product> warehouseListItem = warehouseList.get(i).getItems();
            for (int j = 0; j < warehouseListItem.size(); j++) {
                Product oldProduct = warehouseListItem.get(j);
                Product updatedProduct = ProductService.search(code);
                if (oldProduct.getCode().equals(code)) {
                    warehouseListItem.set(j, new Product(code, updatedProduct.getName(), updatedProduct.getManufacturingDate(), updatedProduct.getExpirationDate(), oldProduct.getQuantity()));
                }
            }
        }
    }

    public static WareHouse createReceiptWarehouse(String type) throws Exception {
        String code;
        int selfGeneratenumber = 0;
        do {
            code = "";
            if (type.equals("import")) {
                String importType = "1";
                code += (importType + String.format("%06d", selfGeneratenumber));
            } else {
                String importType = "2";
                code += (importType + String.format("%06d", selfGeneratenumber));
            }
            selfGeneratenumber++;
        } while (DataValidation.isExistWarehouseCode(warehouseList, code));
        LocalDate timeSlip = LocalDate.now();
        List<Product> items = new ArrayList<>();
        boolean continueEnterProduct = true;
        String productCode;
        int quantity;
        while (continueEnterProduct) {
            do {
                productCode = DataInput.getString("Enter product code: ");
            } while (ProductService.search(productCode) == null);
            do {
                quantity = DataInput.getIntegerNumber("Enter quantity: ");
            } while (quantity < 1);
            Product addProduct = new Product(productCode, ProductService.search(productCode).getName(), ProductService.search(productCode).getManufacturingDate(), ProductService.search(productCode).getExpirationDate(), quantity);
            items.add(addProduct);
            String answer = DataInput.getString("Do you want to continue? Y/N: ");
            if (!answer.equalsIgnoreCase("Y")) {
                continueEnterProduct = false;
            }
        }
        return new WareHouse(code, timeSlip, items);
    }

    public void writeWarehouseToFile() throws Exception {
        List<String> result = new ArrayList<>();
        for (WareHouse warehouse : warehouseList) {
            String warehouseString = "";
            warehouseString += (warehouse.getCode() + ",");
            warehouseString += (warehouse.getTimeSlip() + ",");
            for (Product productItem : warehouse.getItems()) {
                String productItemString = "";
                productItemString += (productItem.getCode() + "/");
                productItemString += String.valueOf(productItem.getQuantity());
                warehouseString += (productItemString + ",");
            }
            result.add(warehouseString);
        }
        warehousedao.writeWarehouseToFile(result);
    }

    public void importExportReceiptProduct() {
        String code = DataInput.getString("Enter code product: ");
        if (ProductService.search(code) == null) {
            System.out.println("Product not found");
        } else {
            for (int i=0 ; i<warehouseList.size() ; i++) {
                List<Product> currentWarehouseItem = warehouseList.get(i).getItems();
                for (int j=0 ; j<currentWarehouseItem.size() ; j++) {
                    if (currentWarehouseItem.get(j).getCode().equals(code)) {
                        System.out.println(String.format("%s, %s, %s/%s/%d", warehouseList.get(i).getCode(), warehouseList.get(i).getTimeSlip().toString(), currentWarehouseItem.get(j).getCode(), currentWarehouseItem.get(j).getName(), currentWarehouseItem.get(j).getQuantity()));
                    }
                }
            }
        }
    }
}
