/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer.Product;
import BusinessLayer.Entity.Product;
import DataLayer.FileManager;
import java.util.List;
import java.util.ArrayList;
import DataLayer.IFileManager;
import java.util.Arrays;
import java.time.LocalDate;

/**
 *
 * @author tanng
 */

public class ProductDAO implements IProductDAO{
    private IFileManager<Product> fileManager;
    private List<Product> productList;

    public ProductDAO() throws Exception {
        this.fileManager = new FileManager("product.txt");
        this.productList = new ArrayList<>();
        loadProductFromFile();
    }
    
    @Override
    public void loadProductFromFile() throws Exception{
        String code, name;
        LocalDate manufacturingDate, expirationDate;
        int quantity;
        List<String> productData = fileManager.readDataFromFile();
        if (productData.isEmpty()){
            return;
        }
        for (String a : productData){
            List<String> subData = Arrays.asList(a.split(","));
            code = subData.get(0).trim();
            name = subData.get(1).trim();
            manufacturingDate = LocalDate.parse(subData.get(2).trim());
            expirationDate = LocalDate.parse(subData.get(3).trim());
            quantity = Integer.parseInt(subData.get(4).trim());
            Product newProduct = new Product(code, name, manufacturingDate, expirationDate, quantity);
            addNew(newProduct);
        }
        
    }
    
    @Override
    public List<Product> getList() throws Exception{
        return productList;
    }

    @Override
    public void addNew(Product product) throws Exception {
        productList.add(product);
    }
    
    public void writeProductToFile(List<String>result) throws Exception{
        if (result.isEmpty()){
            return;
        }
        fileManager.writeDataToFile(result);
    }
}
