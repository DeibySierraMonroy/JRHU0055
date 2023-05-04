package co.com.activos.jrhu0055.model;

import java.io.Serializable;
import java.util.Objects;

public class TipoEnfermedad implements Serializable {
    private String codigoTipoEnfermedad;
    private String nombreTipoEnfermedad;


    public String getCodigoTipoEnfermedad() {
        return codigoTipoEnfermedad;
    }

    public void setCodigoTipoEnfermedad(String codigoTipoEnfermedad) {
        this.codigoTipoEnfermedad = codigoTipoEnfermedad;
    }

    public String getNombreTipoEnfermedad() {
        return nombreTipoEnfermedad;
    }

    public void setNombreTipoEnfermedad(String nombreTipoEnfermedad) {
        this.nombreTipoEnfermedad = nombreTipoEnfermedad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoEnfermedad that = (TipoEnfermedad) o;

        if (!Objects.equals(codigoTipoEnfermedad, that.codigoTipoEnfermedad))
            return false;
        return Objects.equals(nombreTipoEnfermedad, that.nombreTipoEnfermedad);
    }

    @Override
    public int hashCode() {
        int result = codigoTipoEnfermedad != null ? codigoTipoEnfermedad.hashCode() : 0;
        result = 31 * result + (nombreTipoEnfermedad != null ? nombreTipoEnfermedad.hashCode() : 0);
        return result;
    }
}
