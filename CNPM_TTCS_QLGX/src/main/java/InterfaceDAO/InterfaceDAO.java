/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfaceDAO;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface InterfaceDAO<T> {
    public ArrayList<T> getList();
    public boolean insert(T Object);
    public boolean update(T Object);
    public boolean delete(T Object);
    public T findById(int id);
}
