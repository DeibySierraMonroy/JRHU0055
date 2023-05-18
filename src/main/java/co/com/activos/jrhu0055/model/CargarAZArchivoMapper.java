/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.activos.jrhu0055.model;

import java.io.Serializable;
/**
 *
 * @author julforero
 */

public class CargarAZArchivoMapper implements Serializable {
   private String nombre;
   private String idArchivoNuevo;
   
    public String getEstado() {
        return nombre;
    }

    public void setEstado(String estado) {
        this.nombre = estado;
    }

    public String getIdArchivoNuevo() {
        return idArchivoNuevo;
    }

    public void setIdArchivoNuevo(String idArchivoNuevo) {
        this.idArchivoNuevo = idArchivoNuevo;
    }
    
}
