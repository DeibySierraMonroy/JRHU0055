package co.com.activos.jrhu0055.model;

import java.io.Serializable;

public class TerminosYCondiciones implements Serializable {
    private String codigo;
    private String descripcion;

    public TerminosYCondiciones() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
