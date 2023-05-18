package co.com.activos.jrhu0055.DTO;

import java.io.Serializable;
import java.util.Optional;

public class DocumentoActualizadoDTO implements Serializable{

    Double numeroRadicado;
    Integer codigoDocumento;
    String observacion;
    String estadoDocumento;

    public Double getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(Double numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }


    public Integer getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(Integer codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstadoDocumento() {
        return estadoDocumento;
    }

    public void setEstadoDocumento(String estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
    }
}
