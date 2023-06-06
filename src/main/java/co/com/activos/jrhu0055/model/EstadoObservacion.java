package co.com.activos.jrhu0055.model;

import java.io.Serializable;

public class EstadoObservacion implements Serializable {

    private String estado;
    private String tipo;
    private String descripcion;

    public EstadoObservacion( ) {

    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
