/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CRUD;

import java.util.List;


public interface CrudPersonaInterface<T> {
    public List<T> listar(String Texto,int TotalPorpagina, int numPagina);
    public boolean Insertar (T Obj);
    public boolean Actualizar (T Obj);
    public boolean Desactivar (int id);
    public boolean Activar (int id);
    public int Total();
    public boolean existe (String texto);
}
