package co.com.activos.jrhu0055.model;

public class Incapacidad {
  private String sigla;
  private String tipoDocumentoEmpresa;
  private Integer nitEmpresa;
  private String tipoDocumentoEmpleado;
  private Integer numeroDocumentoEmpleado;
  private Integer numeroContrato;
  private String nombreEmpresa;
  private Integer numeroRadicado;
  private String fechaInicial;
  private String fechaFinal;
  private String tipoIncapacidad;
  private String subTipoIncapacidad;
  private String fechaRadicacion;
  private String estado;

  private String numeroDeDias;

  private String nombreDelEmpleado;

  private String fechaDeRadicacion;

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

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Integer getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(Integer numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getTipoIncapacidad() {
        return tipoIncapacidad;
    }

    public void setTipoIncapacidad(String tipoIncapacidad) {
        this.tipoIncapacidad = tipoIncapacidad;
    }

    public String getSubTipoIncapacidad() {
        return subTipoIncapacidad;
    }

    public void setSubTipoIncapacidad(String subTipoIncapacidad) {
        this.subTipoIncapacidad = subTipoIncapacidad;
    }

    public String getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(String fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public String getFechaDeRadicacion() {
        return fechaDeRadicacion;
    }

    public void setFechaDeRadicacion(String fechaDeRadicacion) {
        this.fechaDeRadicacion = fechaDeRadicacion;
    }

    public String getNombreDelEmpleado() {
        return nombreDelEmpleado;
    }

    public void setNombreDelEmpleado(String nombreDelEmpleado) {
        this.nombreDelEmpleado = nombreDelEmpleado;
    }

    public String getNumeroDeDias() {
        return numeroDeDias;
    }

    public void setNumeroDeDias(String numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }
}
