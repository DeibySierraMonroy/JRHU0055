package co.com.activos.jrhu0055.model;

import java.io.Serializable;
import java.util.Objects;

public class DocumentoPorSubtipoIncapacidad implements Serializable {

    private Integer idDocumento;
    private String descripcionDelDocumento;
    private String observacionDelDocumento;

    private Boolean requerido;

    public boolean validarSiElDocumentoEsrequerido(String documentoRequerido) {
        return this.requerido = "S".equals(documentoRequerido) ? true : false;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getDescripcionDelDocumento() {
        return descripcionDelDocumento;
    }

    public void setDescripcionDelDocumento(String descripcionDelDocumento) {
        this.descripcionDelDocumento = descripcionDelDocumento;
    }

    public Boolean getRequerido() {
        return requerido;
    }

    public void setRequerido(Boolean requerido) {
        this.requerido = requerido;
    }

    public String getObservacionDelDocumento() {
        return observacionDelDocumento;
    }

    public void setObservacionDelDocumento(String observacionDelDocumento) {
        this.observacionDelDocumento = observacionDelDocumento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DocumentoPorSubtipoIncapacidad that = (DocumentoPorSubtipoIncapacidad) o;
        return Objects.equals(idDocumento, that.idDocumento) && Objects.equals(descripcionDelDocumento, that.descripcionDelDocumento) && Objects.equals(requerido, that.requerido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDocumento, descripcionDelDocumento, requerido);
    }
}
