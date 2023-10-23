/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer.WareHouse;

import BusinessLayer.Entity.Product;
import BusinessLayer.Entity.WareHouse;
import BusinessLayer.Service.ProductService;
import DataLayer.FileManager;
import DataLayer.IFileManager;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author tanng
 */
public class WareHouseDAO implements IWareHouseDAO{
    IFileManager<WareHouse> fileManager;
    List<WareHouse> warehouseList;

    public WareHouseDAO() throws Exception {
        this.warehouseList = new ArrayList<>();
        this.fileManager = new FileManager("wareHouse.txt");  
        loadWareHouseFromFile();
    }
    
    @Override
    public void loadWareHouseFromFile() throws Exception{
        String code;
        LocalDate timeSlip;
        List<Product> items = new ArrayList<>();
        List<String> warehouseData = fileManager.readDataFromFile();
        for (int j=0 ; j<warehouseData.size() ; j++){
            List<String> subData = Arrays.asList(warehouseData.get(j).split(","));
            code = subData.get(0);
            timeSlip = LocalDate.parse(subData.get(1));
            
            for (int i=2 ; i<subData.size() ; i++){
                List<String> productComponent = Arrays.asList(subData.get(i).split("/"));
                Product searchProduct = ProductService.search(productComponent.get(0));
                items.add(new Product(productComponent.get(0), searchProduct.getName(), searchProduct.getManufacturingDate(), searchProduct.getExpirationDate(), Integer.parseInt(productComponent.get(1))));
            }
            addNew(new WareHouse(code, timeSlip, items));
            items = new ArrayList<>();
        }  
    }
    
    @Override
    public List<WareHouse> getList() throws Exception{
        return warehouseList;
    }

    @Override
    public void addNew(WareHouse warehouse) throws Exception {
        warehouseList.add(warehouse);
    }
    
    public void writeWarehouseToFile(List<String> result) throws Exception{
        fileManager.writeDataToFile(result);
        if (result.isEmpty()){
            return;
        }
    }
}
