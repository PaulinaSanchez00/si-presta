
package CRUD;

import java.util.List;


public interface CrudCorteInterface<T> {
    public boolean Insertar (T Obj);
    public List<T> listar2(String texto,int totalPorPagina,int numPagina);
}
