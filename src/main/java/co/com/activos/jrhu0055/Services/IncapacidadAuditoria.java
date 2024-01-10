/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.activos.jrhu0055.Services;

import co.com.activos.jrhu0055.utiliti.TipoRespuesta;

/**
 *
 * @author egacha
 */
public interface IncapacidadAuditoria {
    
   void guardarAuditoria(String log,TipoRespuesta tipo , String aplicativo,String transaccion,String estadoTransaccion);
    
}
