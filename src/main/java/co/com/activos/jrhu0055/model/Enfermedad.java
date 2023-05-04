package co.com.activos.jrhu0055.model;

import java.io.Serializable;
import java.util.Objects;

public class Enfermedad  implements Serializable {
    private String codigoEnfermedad;
    private String nombreEnfermedad;

    private String codigoSubtipoEnfermedad;
    private String codigoGrupoEnfermedadl;

    public Enfermedad() {

    }

    public String getCodigoEnfermedad() {
        return codigoEnfermedad;
    }

    public void setCodigoEnfermedad(String codigoEnfermedad) {
        this.codigoEnfermedad = codigoEnfermedad;
    }

    public String getNombreEnfermedad() {
        return nombreEnfermedad;
    }

    public void setNombreEnfermedad(String nombreEnfermedad) {
        this.nombreEnfermedad = nombreEnfermedad;
    }

    public String getCodigoSubtipoEnfermedad() {
        return codigoSubtipoEnfermedad;
    }

    public void setCodigoSubtipoEnfermedad(String codigoSubtipoEnfermedad) {
        this.codigoSubtipoEnfermedad = codigoSubtipoEnfermedad;
    }

    public String getCodigoGrupoEnfermedadl() {
        return codigoGrupoEnfermedadl;
    }

    public void setCodigoGrupoEnfermedadl(String codigoGrupoEnfermedadl) {
        this.codigoGrupoEnfermedadl = codigoGrupoEnfermedadl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Enfermedad that = (Enfermedad) o;

        if (!Objects.equals(codigoEnfermedad, that.codigoEnfermedad))
            return false;
        if (!Objects.equals(nombreEnfermedad, that.nombreEnfermedad))
            return false;
        if (!Objects.equals(codigoSubtipoEnfermedad, that.codigoSubtipoEnfermedad))
            return false;
        return Objects.equals(codigoGrupoEnfermedadl, that.codigoGrupoEnfermedadl);
    }

    @Override
    public int hashCode() {
        int result = codigoEnfermedad != null ? codigoEnfermedad.hashCode() : 0;
        result = 31 * result + (nombreEnfermedad != null ? nombreEnfermedad.hashCode() : 0);
        result = 31 * result + (codigoSubtipoEnfermedad != null ? codigoSubtipoEnfermedad.hashCode() : 0);
        result = 31 * result + (codigoGrupoEnfermedadl != null ? codigoGrupoEnfermedadl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Enfermedad{" +
                "codigoEnfermedad='" + codigoEnfermedad + '\'' +
                ", nombreEnfermedad='" + nombreEnfermedad + '\'' +
                ", codigoSubtipoEnfermedad='" + codigoSubtipoEnfermedad + '\'' +
                ", codigoGrupoEnfermedadl='" + codigoGrupoEnfermedadl + '\'' +
                '}';
    }
}
