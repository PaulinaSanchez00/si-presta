
package CRUD;


public interface CrudCambiosInterface<T> {
    public boolean Insertar (T Obj);
    public boolean Actualizar (T Obj);
    public boolean existe (int Rol_id);
}
