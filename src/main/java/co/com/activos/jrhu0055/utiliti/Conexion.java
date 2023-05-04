package co.com.activos.jrhu0055.utiliti;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;


public class Conexion {
    private static final String DATASOURCE_NAME = "desarrollo";
    static Connection conexion;

    
    public static Connection getConnection() {
        try {           
            InitialContext ic = new InitialContext();
            javax.sql.DataSource dt = (javax.sql.DataSource) ic.lookup(DATASOURCE_NAME);
            return dt.getConnection();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    } 
    
    public static void cerrarConexion(Connection conexion) {
        try {
            CallableStatement cs = conexion.prepareCall("{ CALL QB_DEFINIR_CONTEXTO_CTO.PL_CLEAR_SESION_USUARIO }");
            cs.execute();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
