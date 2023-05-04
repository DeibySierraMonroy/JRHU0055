package co.com.activos.jrhu0055.DTO;

import java.io.Serializable;

public class CrearDTO  implements Serializable {


    private String idCarpeta;
    private String tipo;

    public String getIdCarpeta() {
        return idCarpeta;
    }

    public void setIdCarpeta(String idCarpeta) {
        this.idCarpeta = idCarpeta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
