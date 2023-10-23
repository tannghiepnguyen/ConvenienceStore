/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ApplicationLayer.UI;

import BusinessLayer.Service.ProductService;
import ApplicationLayer.Utilities.DataInput;

/**
 *
 * @author tanng
 */
public class ProductMenu {

    private final ProductService productService;

    public ProductMenu(ProductService productService) {
        this.productService = productService;
    }

    public void processMenuForProduct() {
        boolean stop = false;
        try {
            do {
                Menu.print("******Product Management******|1.Add Product|2.Update Product|3.Remove Product|4.Print Product List|5.Return to main menu|Select: ");
                int choice = Menu.getUserChoice();
                switch (choice) {
                    case 1 -> {
                        boolean continueAddingProduct = true;
                        do {                            
                            productService.add(ProductService.createProduct());
                            String continueChoice = DataInput.getString("Do you want to continue(Y/N): ");
                            if (!continueChoice.equalsIgnoreCase("Y")){
                                continueAddingProduct = false;
                            }
                        } while (continueAddingProduct);
                    }
                    case 2 -> {
                        String code = DataInput.getString("Enter code: ");
                        productService.update(code);
                    }
                    case 3 -> {
                        String code = DataInput.getString("Enter product code: ");
                        productService.delete(code);
                    }
                    case 4 -> {
                        productService.print();
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
