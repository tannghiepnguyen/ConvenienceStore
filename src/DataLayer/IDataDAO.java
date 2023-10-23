/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataLayer;

import java.util.List;

/**
 *
 * @author tanng
 * @param <T>
 */
public interface IDataDAO<T> {
    void addNew(T obj) throws Exception;
    List<T> getList() throws Exception;
}
