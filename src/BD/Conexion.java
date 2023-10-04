
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class Conexion {
    private final String DRIVER="com.mysql.cj.jdbc.Driver";
    private final String URL="jdbc:mysql://localhost:3306/";
    private final String DB="sipresta";
    private final String USER="root";
    private final String PASSWORD="191100";
    
    public Connection cadena;
    public static Conexion Instancia;
    
    private Conexion(){
        this.cadena=null;
    }
    public Connection conectar(){
        try {
            Class.forName(DRIVER);
            this.cadena=DriverManager.getConnection(URL+DB,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return this.cadena;
    }
    public void desconectar(){
        try {
            this.cadena.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public synchronized static Conexion getInstancia(){
        if(Instancia == null){
            Instancia = new Conexion();
        }
        return Instancia;
    }
}
