/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLayer.Service;

import ApplicationLayer.Utilities.DataInput;
import BusinessLayer.Component.DataValidation;
import BusinessLayer.Entity.Product;
import BusinessLayer.Entity.WareHouse;
import DataLayer.Product.ProductDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author tanng
 */
public class ProductService {

    class SortByQuantity implements Comparator<Product> {

        @Override
        public int compare(Product o1, Product o2) {
            return o1.getQuantity() - o2.getQuantity();
        }

    }

    protected static List<Product> productList;
    private final ProductDAO productdao;

    public ProductService() throws Exception {
        this.productdao = new ProductDAO();
        ProductService.productList = productdao.getList();
    }

    public void add(Product product) {
        ProductService.productList.add(product);
        System.out.println("Adding successfully");
    }

    public void update(String code) throws Exception {
        int index = this.indexOf(code);
        Product oldProduct = this.search(code);
        if (index != -1) {
            productList.set(index, ProductService.updateProduct(oldProduct));
            System.out.println("Update successfully");
            WarehouseService.updateItemInformation(code);
            return;
        } else {
            System.out.println("Product does not exist");
        }
    }

    public void delete(String code) {
        String confirmMessage = DataInput.getString("Are you sure you want to delete this?(Y/N): ");
        if (!confirmMessage.equalsIgnoreCase("Y")){
            return;
        }
        int index = this.indexOf(code);
        if (index != -1) {
            for (WareHouse warehouse : WarehouseService.warehouseList){
                for (Product product : warehouse.getItems()){
                    if (product.getCode().equals(code)){
                        System.out.println("Cannot delete due to existence of product in warehouse");
                        return;
                    }
                }
            }
            productList.remove(index);
            System.out.println("Delete successfully");
            return;
        } else {
            System.out.println("Product does not exist");
        }

    }

    public int indexOf(String code) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    public static Product search(String code) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getCode().equals(code)) {
                return productList.get(i);
            } else {
                continue;
            }
        }
        return null;
    }

    public void print() {
        for (Product item : productList) {
            System.out.println(item);
        }
    }

    public void writeProductToFile() throws Exception {
        List<String> result = new ArrayList<>();
        for (Product product : productList) {
            String productString = "";
            productString += (product.getCode() + ",");
            productString += (product.getName() + ",");
            productString += (product.getManufacturingDate().toString() + ",");
            productString += (product.getExpirationDate().toString() + ",");
            productString += (String.valueOf(product.getQuantity()));
            result.add(productString);
        }
        productdao.writeProductToFile(result);
    }

    public static Product createProduct() throws Exception {
        String code, name, mDate, eDate;
        LocalDate manufacturingDate, expirationDate;
        int quantity = 0;
        do {
            code = DataInput.getString("Enter product code: ");
        } while (!DataValidation.isValidProductCode(code) || DataValidation.isExistProductCode(productList, code));
        name = DataInput.getString("Enter product name: ");
        do {
            mDate = DataInput.getString("Enter manufacturing date(yyyy-mm-dd): ");
        } while (!DataValidation.isValidDate(mDate));
        manufacturingDate = LocalDate.parse(mDate);
        do {
            eDate = DataInput.getString("Enter expiration date(yyyy-mm-dd): ");
        } while (!DataValidation.isValidDate(eDate));
        expirationDate = LocalDate.parse(eDate);
        do {
            quantity = DataInput.getIntegerNumber("Enter quality: ");
        } while (quantity < 0);

        return new Product(code, name, manufacturingDate, expirationDate, quantity);
    }

    public static Product updateProduct(Product oldProduct) throws Exception {
        String name, mDate, eDate;
        LocalDate manufacturingDate, expirationDate;
        String quantity;
        name = DataInput.getString("Enter product name: ");
        if (name.equals("")) {
            name = oldProduct.getName();
        }
        do {
            mDate = DataInput.getString("Enter manufacturing date(yyyy-mm-dd): ");
            if (mDate.equals("")) {
                mDate = oldProduct.getManufacturingDate().toString();
            }
        } while (!DataValidation.isValidDate(mDate));
        manufacturingDate = LocalDate.parse(mDate);
        do {
            eDate = DataInput.getString("Enter expiration date(yyyy-mm-dd): ");
            if (eDate.equals("")) {
                eDate = oldProduct.getExpirationDate().toString();
            }
        } while (!DataValidation.isValidDate(eDate));
        expirationDate = LocalDate.parse(eDate);
        do {
            quantity = DataInput.getString("Enter quality: ");
            if (quantity.equals("")) {
                quantity = String.valueOf(oldProduct.getQuantity());
            }
        } while (Integer.parseInt(quantity) < 0);

        return new Product(oldProduct.getCode(), name, manufacturingDate, expirationDate, Integer.parseInt(quantity));
    }

    public void printExpiredProduct() {
        System.out.println("Expired product list: ");
        for (Product product : productList) {
            if (product.getManufacturingDate().isAfter(product.getExpirationDate())) {
                System.out.println(product);
            }
        }
    }

    public void printSellingProduct() {
        System.out.println("Selling product list: ");
        for (Product product : productList) {
            if ((product.getManufacturingDate().isBefore(product.getExpirationDate()) || product.getManufacturingDate().isEqual(product.getExpirationDate())) && product.getQuantity() > 0) {
                System.out.println(product);
            }
        }
    }

    public void printOutofStockProduct() {
        List<Product> outOfStockProductList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getQuantity() <= 3) {
                outOfStockProductList.add(product);
            }
        }
        Collections.sort(outOfStockProductList, new SortByQuantity());
        System.out.println("Out of stock product list: ");
        for (Product product : outOfStockProductList) {
            System.out.println(product);
        }
    }
}
