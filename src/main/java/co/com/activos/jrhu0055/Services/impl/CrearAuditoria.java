/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.activos.jrhu0055.Services.impl;

import co.com.activos.jrhu0055.Services.IncapacidadAuditoria;
import co.com.activos.jrhu0055.utiliti.Conexion;
import co.com.activos.jrhu0055.utiliti.TipoRespuesta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import oracle.jdbc.OracleType;

/**
 *
 * @author egacha
 */
public class CrearAuditoria implements IncapacidadAuditoria {

    @Override
    public void guardarAuditoria(String log, TipoRespuesta tipo, String aplicativo, String transaccion, String estadoTransaccion) {
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "call adm.qb_JADM0067_mesa_contratos.pl_insertar_auditoria(?,?,?,?,?,?,?)";
            try (CallableStatement call = connection.prepareCall(consulta)) {

                //Parametros  de entrada
                call.setString("VCLOG", log);
                call.setString("VCTYPE_ERROR", String.valueOf(tipo));
                call.setString("VCAPLICATIVO", aplicativo);
                call.setString("VCTRANSACCION", transaccion);
                call.setString("VCESTADOTRANSACCION", estadoTransaccion);

                //Parametros de Salida
                call.registerOutParameter("vcestadoproceso", OracleType.VARCHAR2);
                call.registerOutParameter("vcmensajeproceso", OracleType.VARCHAR2);
                call.execute();
                String resultSet = (String) call.getString("vcmensajeproceso");
                System.out.println("Resultado: " + resultSet);
                call.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    @Override
    public DatabaseResultDto<?> guardarAuditoria(String log, TipoRespuestaLog tipo, String aplicativo, String transaccion, String estadoTransaccion) {
        
    }
     */
}
