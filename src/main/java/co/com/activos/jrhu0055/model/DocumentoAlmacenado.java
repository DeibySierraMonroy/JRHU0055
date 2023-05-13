package co.com.activos.jrhu0055.model;

import java.io.Serializable;

public class DocumentoAlmacenado extends Documento implements Serializable {
    boolean estadoDelProceso;
    private String radicado;
    


    public DocumentoAlmacenado(boolean estadoDelProceso , String idDocumento,String nombreDocumento , String radicado) {
        super(idDocumento,nombreDocumento);
        this.estadoDelProceso = estadoDelProceso;
        this.radicado =radicado;
    }

    public boolean getEstadoDelProceso() {
        return estadoDelProceso;
    }

    public void setEstadoDelProceso(boolean estadoDelProceso) {
        this.estadoDelProceso = estadoDelProceso;
    }

    public String getRadicado() {
        return radicado;
    }

    public void setRadicado(String radicado) {
        this.radicado = radicado;
    }

    @Override
    public String toString() {
        return "DocumentoAlmacenado{" +
                "  estado=" + estadoDelProceso +
                ", documentoId=" + getIdDocumento() +
                ", nombreDocumento=" + getNombreDocumento() +
                ", radicado='" + radicado + '\'' +
                '}';
    }
}
