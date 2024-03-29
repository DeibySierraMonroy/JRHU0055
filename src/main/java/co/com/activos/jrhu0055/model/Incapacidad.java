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
    private String nombreEmpPrincipal;
    private String nombreEmpUsuaria;

    private String estadoSitioDelTrabajador;
    private String estadoObservacion;

    private String numeroDeDias;

    private String nombreDelEmpleado;

    private String nombreEps;

    private String fechaDeRadicacion;
    private String incContin;

    private Integer subTipoIncapacidadCodigo;

    private String estadoObservacionTrabajador;
    private String incapacidadDescripcion;
    private String observacionGeneralDevolucion;

//    private Integer numeroIncapacidad;
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

    public String getEstadoSitioDelTrabajador() {
        return estadoSitioDelTrabajador;
    }

    public void setEstadoSitioDelTrabajador(String estadoSitioDelTrabajador) {
        this.estadoSitioDelTrabajador = estadoSitioDelTrabajador;
    }

    public String getEstadoObservacion() {
        return estadoObservacion;
    }

    public void setEstadoObservacion(String estadoObservacion) {
        this.estadoObservacion = estadoObservacion;
    }

    public void setNumeroDeDias(String numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }

    public String getIncContin() {
        return incContin;
    }

    public void setIncContin(String incContin) {
        this.incContin = incContin;
    }

    public Integer getSubTipoIncapacidadCodigo() {
        return subTipoIncapacidadCodigo;
    }

    public void setSubTipoIncapacidadCodigo(Integer subTipoIncapacidadCodigo) {
        this.subTipoIncapacidadCodigo = subTipoIncapacidadCodigo;
    }

    public String getEstadoObservacionTrabajador() {
        return estadoObservacionTrabajador;
    }

    public void setEstadoObservacionTrabajador(String estadoObservacionTrabajador) {
        this.estadoObservacionTrabajador = estadoObservacionTrabajador;
    }

    public String getIncapacidadDescripcion() {
        return incapacidadDescripcion;
    }

    public void setIncapacidadDescripcion(String incapacidadDescripcion) {
        this.incapacidadDescripcion = incapacidadDescripcion;
    }

    public String getNombreEps() {
        return nombreEps;
    }

    public void setNombreEps(String nombreEps) {
        this.nombreEps = nombreEps;
    }

    public String getNombreEmpPrincipal() {
        return nombreEmpPrincipal;
    }

    public void setNombreEmpPrincipal(String nombreEmpPrincipal) {
        this.nombreEmpPrincipal = nombreEmpPrincipal;
    }

    public String getNombreEmpUsuaria() {
        return nombreEmpUsuaria;
    }

    public void setNombreEmpUsuaria(String nombreEmpUsuaria) {
        this.nombreEmpUsuaria = nombreEmpUsuaria;
    }

//    public Integer getNumeroIncapacidad() {
//        return numeroIncapacidad;
//    }
//
//    public void setNumeroIncapacidad(Integer numeroIncapacidad) {
//        this.numeroIncapacidad = numeroIncapacidad;
//    }
    public String getObservacionGeneralDevolucion() {
        return observacionGeneralDevolucion;
    }

    public void setObservacionGeneralDevolucion(String observacionGeneralDevolucion) {
        this.observacionGeneralDevolucion = observacionGeneralDevolucion;
    }
}
