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
public interface CrudDocumentosInterface <T> {
    public List<T> listar1(String texto,int totalPorPagina,int numPagina);
    public List<T> listarid(String texto);
    public boolean Insertar (T obj);
    public boolean Actualizar (T obj);
    public int Total ();
    public boolean existe (String texto);
}
