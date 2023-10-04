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
public interface CrudAbono <T> {
    public List<T> listar1(String texto,int totalPorPagina,int numPagina);
    public List<T> listarid3(String texto);
    public boolean Insertar (T obj);
    public boolean Desactivar (int id);
    public boolean Activar (int id);
    public int Total ();
    
}
