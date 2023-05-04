package co.com.activos.jrhu0055.model;

import java.io.Serializable;

public class SubTipoIncapacidad implements Serializable {
    private Integer codigoSubTipoIncapacidad;
    private String nombreSubTipoIncapacidad;
    private String estado;

    public SubTipoIncapacidad() {
    }

    public Integer getCodigoSubTipoIncapacidad() {
        return codigoSubTipoIncapacidad;
    }

    public void setCodigoSubTipoIncapacidad(Integer codigoSubTipoIncapacidad) {
        this.codigoSubTipoIncapacidad = codigoSubTipoIncapacidad;
    }

    public String getNombreSubTipoIncapacidad() {
        return nombreSubTipoIncapacidad;
    }

    public void setNombreSubTipoIncapacidad(String nombreSubTipoIncapacidad) {
        this.nombreSubTipoIncapacidad = nombreSubTipoIncapacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
