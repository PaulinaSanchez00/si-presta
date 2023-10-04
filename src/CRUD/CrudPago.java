/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CRUD;

import java.util.List;

/**
 *
 * @author pauli
 */
public interface CrudPago <T> {
    public List<T> listar(String texto,int totalPorPagina,int numPagina);
    public boolean Insertar (T obj);
    public int Total ();
    
}
