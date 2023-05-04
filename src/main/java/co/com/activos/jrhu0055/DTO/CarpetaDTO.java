package co.com.activos.jrhu0055.DTO;

import java.io.Serializable;
import java.math.BigInteger;

public class CarpetaDTO implements Serializable {
    private String idCarpetaPadre;
    private String nombreDeLaCarpetaACrear;

    public CarpetaDTO(String idCarpetaPadre, String nombreDeLaCarpetaACrear) {
        this.idCarpetaPadre = idCarpetaPadre;
        this.nombreDeLaCarpetaACrear = nombreDeLaCarpetaACrear;
    }

    public String getIdCarpetaPadre() {
        return idCarpetaPadre;
    }

    public void setIdCarpetaPadre(String idCarpetaPadre) {
        this.idCarpetaPadre = idCarpetaPadre;
    }

    public String getNombreDeLaCarpetaACrear() {
        return nombreDeLaCarpetaACrear;
    }

    public void setNombreDeLaCarpetaACrear(String nombreDeLaCarpetaACrear) {
        this.nombreDeLaCarpetaACrear = nombreDeLaCarpetaACrear;
    }

    @Override
    public String toString() {
        return "CarpetaDTO{" +
                "idCarpetaPadre='" + idCarpetaPadre + '\'' +
                ", nombreDeLaCarpetaACrear='" + nombreDeLaCarpetaACrear + '\'' +
                '}';
    }
}
