/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLayer.Component;

import BusinessLayer.Entity.Product;
import BusinessLayer.Entity.WareHouse;
import java.util.List;

/**
 *
 * @author tanng
 */
public final class DataValidation {

    public static boolean isExistProductCode(List<Product> product, String code) {
        for (Product item : product) {
            if (item.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidProductCode(String code) {
        return code.matches("AF\\d{3}");
    }

    public static boolean isValidWarehouseCode(String code) {
        return code.matches("\\d{7}");
    }

    public static boolean isValidDate(String fullDate) {
        if (fullDate.equals("")){
            return false;
        }
        int dd = Integer.parseInt(fullDate.split("-")[2]);
        int mm = Integer.parseInt(fullDate.split("-")[1]);
        int yy = Integer.parseInt(fullDate.split("-")[0]);
        if(mm>=1 && mm<=12)
        {
            //check days
            if((dd>=1 && dd<=31) && (mm==1 || mm==3 || mm==5 || mm==7 || mm==8 || mm==10 || mm==12))
                return true;
            else if((dd>=1 && dd<=30) && (mm==4 || mm==6 || mm==9 || mm==11))
                return true;
            else if((dd>=1 && dd<=28) && (mm==2))
                return true;
            else return dd==29 && mm==2 && (yy%400==0 ||(yy%4==0 && yy%100!=0));
        }
        else
        {
            return false;
        }
    }
    
    public static boolean isExistWarehouseCode(List<WareHouse> warehouse, String code){
        for (WareHouse item : warehouse){
            if (item.getCode().equals(code)){
                return true;
            }
        }
        return false;
    }
}
