package co.com.activos.jrhu0055.model;

import java.io.Serializable;

public class SubTipoEnfermedad  implements Serializable{

    private String codigoSubTipoEnfermedad;
    private String nombreSubTipoEnfermedad;

    public String getCodigoSubTipoEnfermedad() {
        return codigoSubTipoEnfermedad;
    }

    public void setCodigoSubTipoEnfermedad(String codigoSubTipoEnfermedad) {
        this.codigoSubTipoEnfermedad = codigoSubTipoEnfermedad;
    }

    public String getNombreSubTipoEnfermedad() {
        return nombreSubTipoEnfermedad;
    }

    public void setNombreSubTipoEnfermedad(String nombreSubTipoEnfermedad) {
        this.nombreSubTipoEnfermedad = nombreSubTipoEnfermedad;
    }
}
