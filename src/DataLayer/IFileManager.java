/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataLayer;

import java.util.List;

/**
 *
 * @author tanng
 */
public interface IFileManager<T> {
    List<String> readDataFromFile()  throws Exception;
    void writeDataToFile(List<String> result) throws Exception;
}
