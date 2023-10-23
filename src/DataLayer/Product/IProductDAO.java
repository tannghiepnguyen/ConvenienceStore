/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataLayer.Product;

import BusinessLayer.Entity.Product;
import DataLayer.IDataDAO;

/**
 *
 * @author tanng
 */
public interface IProductDAO extends IDataDAO<Product>{
    void loadProductFromFile() throws Exception;
}
