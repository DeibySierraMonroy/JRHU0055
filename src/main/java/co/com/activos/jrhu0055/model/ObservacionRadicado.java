package co.com.activos.jrhu0055.model;

import java.io.Serializable;

public class ObservacionRadicado implements Serializable {
    private Integer numeroRadicado;
    private Integer numObservacion;

    private String descripcion;

    private String fecha;
    private String usuario;
    private String estado;

    public Integer getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(Integer numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public Integer getNumObservacion() {
        return numObservacion;
    }

    public void setNumObservacion(Integer numObservacion) {
        this.numObservacion = numObservacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
