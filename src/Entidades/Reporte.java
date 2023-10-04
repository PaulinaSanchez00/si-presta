
package Entidades;


public class Reporte {
    private int ID;
    private String NombreTrab;
    private int NumClientes;

    public Reporte() {
    }

    public Reporte(int ID, String NombreTrab, int NumClientes) {
        this.ID = ID;
        this.NombreTrab = NombreTrab;
        this.NumClientes = NumClientes;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombreTrab() {
        return NombreTrab;
    }

    public void setNombreTrab(String NombreTrab) {
        this.NombreTrab = NombreTrab;
    }

    public int getNumClientes() {
        return NumClientes;
    }

    public void setNumClientes(int NumClientes) {
        this.NumClientes = NumClientes;
    }


    @Override
    public String toString() {
        return "Reporte{" + "ID=" + ID + ", NombreTrab=" + NombreTrab + ", NumClientes=" + NumClientes + '}';
    }
    
    
}
