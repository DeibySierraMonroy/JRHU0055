package co.com.activos.jrhu0055.DTO;

import java.io.Serializable;
import java.util.Objects;

public class ContratoDTO  implements Serializable {
    private  String tipoDocumentoEmpleado;
    private  Integer documentoEmpleado;
    private  String tipoDocumentoEmpresa;
    private  Integer documentoEmpresa;

    public ContratoDTO() {
    }

    public String getTipoDocumentoEmpleado() {
        return tipoDocumentoEmpleado;
    }

    public void setTipoDocumentoEmpleado(String tipoDocumentoEmpleado) {
        this.tipoDocumentoEmpleado = tipoDocumentoEmpleado;
    }

    public Integer getDocumentoEmpleado() {
        return documentoEmpleado;
    }

    public void setDocumentoEmpleado(Integer documentoEmpleado) {
        this.documentoEmpleado = documentoEmpleado;
    }

    public String getTipoDocumentoEmpresa() {
        return tipoDocumentoEmpresa;
    }

    public void setTipoDocumentoEmpresa(String tipoDocumentoEmpresa) {
        this.tipoDocumentoEmpresa = tipoDocumentoEmpresa;
    }

    public Integer getDocumentoEmpresa() {
        return documentoEmpresa;
    }

    public void setDocumentoEmpresa(Integer documentoEmpresa) {
        this.documentoEmpresa = documentoEmpresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContratoDTO that = (ContratoDTO) o;
        return Objects.equals(tipoDocumentoEmpleado, that.tipoDocumentoEmpleado) && Objects.equals(documentoEmpleado, that.documentoEmpleado) && Objects.equals(tipoDocumentoEmpresa, that.tipoDocumentoEmpresa) && Objects.equals(documentoEmpresa, that.documentoEmpresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoDocumentoEmpleado, documentoEmpleado, tipoDocumentoEmpresa, documentoEmpresa);
    }
}
