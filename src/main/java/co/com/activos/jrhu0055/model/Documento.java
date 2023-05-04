package co.com.activos.jrhu0055.model;

import java.io.Serializable;

public class Documento implements Serializable {
    private String idDocumento;
    private String nombreDocumento;

    private String base64;

    public Documento() {
    }


    public Documento(String idDocumento, String nombreDocumento) {
        this.idDocumento = idDocumento;
        this.nombreDocumento = nombreDocumento;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "idDocumento='" + idDocumento + '\'' +
                ", nombreDocumento='" + nombreDocumento + '\'' +
                ", base64='" + base64 + '\'' +
                '}';
    }
}
