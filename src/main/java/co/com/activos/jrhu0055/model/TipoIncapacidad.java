package co.com.activos.jrhu0055.model;

import java.io.Serializable;
import java.util.Objects;

public class TipoIncapacidad implements Serializable {
    private Integer codigoTipoIncapacidad;
    private String nombreTipoIncapacidad;
    private String estado;

    public TipoIncapacidad() {
    }

    public Integer getCodigoTipoIncapacidad() {
        return codigoTipoIncapacidad;
    }

    public void setCodigoTipoIncapacidad(Integer codigoTipoIncapacidad) {
        this.codigoTipoIncapacidad = codigoTipoIncapacidad;
    }

    public String getNombreTipoIncapacidad() {
        return nombreTipoIncapacidad;
    }

    public void setNombreTipoIncapacidad(String nombreTipoIncapacidad) {
        this.nombreTipoIncapacidad = nombreTipoIncapacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoIncapacidad that = (TipoIncapacidad) o;
        return Objects.equals(codigoTipoIncapacidad, that.codigoTipoIncapacidad) && Objects.equals(nombreTipoIncapacidad, that.nombreTipoIncapacidad) && Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoTipoIncapacidad, nombreTipoIncapacidad, estado);
    }
}
