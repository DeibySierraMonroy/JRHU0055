package co.com.activos.jrhu0055.utiliti;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.InitialContext;


public class Conexion {
    private static final String DATASOURCE_NAME = "test";
    // private static final String DATASOURCE_NAME = "DS_Intrauser_Acti";
    static Connection conexion;

    
//    public static Connection getConnection() {
//        try {           
//            InitialContext ic = new InitialContext();
//            javax.sql.DataSource dt = (javax.sql.DataSource) ic.lookup(DATASOURCE_NAME);
//            return dt.getConnection();
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//    } 
    
        public static Connection getConnection(){
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=off)(FAILOVER=on)(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.21.147)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.21.147)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=192.168.21.147)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=TEST)))", "WFORERO", "desa123");
        } catch (Exception e) {
              System.out.println("Error: " + e);
            throw new RuntimeException();
          
        }
        return conexion;
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
