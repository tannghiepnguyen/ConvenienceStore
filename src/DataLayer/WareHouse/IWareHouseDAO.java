/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataLayer.WareHouse;

import BusinessLayer.Entity.WareHouse;
import DataLayer.IDataDAO;

/**
 *
 * @author tanng
 */
public interface IWareHouseDAO extends IDataDAO<WareHouse>{
    void loadWareHouseFromFile() throws Exception;
}
