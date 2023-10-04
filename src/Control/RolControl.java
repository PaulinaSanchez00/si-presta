
package Control;

import DAO.RolDAO;
import Entidades.Rol;



public class RolControl {
    private final RolDAO DATOS;
    private Rol obj;

    public RolControl() {
        this.DATOS = new RolDAO();
        this.obj = new Rol();
    }
    
    
     public String Insertar(String Pto_Empleado,String Descripcion){
         
         obj.setPto_Empleado(Pto_Empleado);
         obj.setDescripcion(Descripcion);

         if (DATOS.Insertar(obj)) {
             return "OK";
         } else {
             return "Error en el registro.";
         }
    }
      public String Actualizar(int Rol_id,int Rol_idAnt,String Pto_Empleado,String Descripcion){
        if (Rol_id == Rol_idAnt){
            obj.setPto_Empleado(Pto_Empleado);
            obj.setDescripcion(Descripcion);
            if(DATOS.Actualizar(obj)){
                return "OK";
            }else{
                return "Error en la actualizacion.";
            }
        }else{
            if(DATOS.existe(Rol_id)){
                return "El registro ya existe";
            }else{
                obj.setPto_Empleado(Pto_Empleado);
                obj.setDescripcion(Descripcion);
                if(DATOS.Actualizar(obj)){
                    return "OK";
                }else{
                    return "Error en la actualizacion.";
                }
                
            }
        }
    }
}
