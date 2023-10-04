
package CRUD;

import java.util.List;


public interface CrudListarInterface<T> {
     public List<T> SelectNom(String Texto);
     public List<T> SelectNom1(String Texto);
     public boolean Insertar (T obj);
}
