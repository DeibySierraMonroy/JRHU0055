package co.com.activos.jrhu0055.model;

public class Incapacidad {
  private String sigla;
  private String tipoDocumentoEmpresa;
  private Integer nitEmpresa;
  private String tipoDocumentoEmpleado;
  private Integer numeroDocumentoEmpleado;
  private Integer numeroContrato;

  private String estado;

    public Incapacidad() {
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getTipoDocumentoEmpresa() {
        return tipoDocumentoEmpresa;
    }

    public void setTipoDocumentoEmpresa(String tipoDocumentoEmpresa) {
        this.tipoDocumentoEmpresa = tipoDocumentoEmpresa;
    }

    public Integer getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(Integer nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public String getTipoDocumentoEmpleado() {
        return tipoDocumentoEmpleado;
    }

    public void setTipoDocumentoEmpleado(String tipoDocumentoEmpleado) {
        this.tipoDocumentoEmpleado = tipoDocumentoEmpleado;
    }

    public Integer getNumeroDocumentoEmpleado() {
        return numeroDocumentoEmpleado;
    }

    public void setNumeroDocumentoEmpleado(Integer numeroDocumentoEmpleado) {
        this.numeroDocumentoEmpleado = numeroDocumentoEmpleado;
    }

    public Integer getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(Integer numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
