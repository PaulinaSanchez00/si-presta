
package CRUD;

import java.util.List;


public interface CrudClienteInterface<T> {
    public List<T> listar(String texto,int totalPorPagina,int numPagina);
    public List<T> listarid2(String texto);
    public boolean Insertar (T obj);
    public boolean Actualizar (T obj);
    public boolean Desactivar (int id);
    public boolean Activar (int id);
    public int Total ();
    public boolean existe(String texto1,String texto2);
}
