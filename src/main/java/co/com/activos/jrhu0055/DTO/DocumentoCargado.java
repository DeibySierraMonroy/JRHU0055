package co.com.activos.jrhu0055.DTO;

import co.com.activos.jrhu0055.model.Documento;

import java.io.Serializable;

public class DocumentoCargado implements Serializable {
    String estadoDelDocumento;
    String documentoRequerido;

    String azCodigoCli;
    String observaciones;
    String ruta;
    Documento documento;

    public String getEstadoDelDocumento() {
        return estadoDelDocumento;
    }

    public void setEstadoDelDocumento(String estadoDelDocumento) {
        this.estadoDelDocumento = estadoDelDocumento;
    }

    public String getDocumentoRequerido() {
        return documentoRequerido;
    }

    public void setDocumentoRequerido(String documentoRequerido) {
        this.documentoRequerido = documentoRequerido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public String getAzCodigoCli() {
        return azCodigoCli;
    }

    public void setAzCodigoCli(String azCodigoCli) {
        this.azCodigoCli = azCodigoCli;
    }
}
