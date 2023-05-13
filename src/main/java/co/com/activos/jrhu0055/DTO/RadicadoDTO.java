
package co.com.activos.jrhu0055.DTO;

import co.com.activos.jrhu0055.model.Documento;
import co.com.activos.jrhu0055.utiliti.TipoAccion;
import java.io.Serializable;
import java.util.List;

import static co.com.activos.jrhu0055.DTO.RadicadoDTO.NivelCreacion.NC;

/**
 *
 * @author desierra
 */
public class RadicadoDTO implements Serializable {

    private String tipoDocumentoEmpleado;
    private Integer numeroDocumentoEmpleado;
    private Integer numeroDocumentoEmpresaPrincipal;
    private String tipoDocumentoEmpresaPrincipal;
    private Integer contrato;
    private Integer idGrupoEnfermedad;
    private Integer idSubGrupoEnfermedad;
    private String idCodigoEnfermedad;
    private Integer idContigenciaIncapacidad;
    private Integer idSubTipoContigencia;
    private String fechaIncidente;
    private String fechaInicioIncapacidad;
    private Integer numeroDeDias;
    private String prorroga;
    private Integer idUsuarioCrea;
    private Integer numeroRadicado;
    private String azCodigo;
    private String deaCodigo;
    private String tipoACargar;
    private TipoAccion tipoAccion;
    private List<Documento> documentosACargar;
    private NivelCreacion nivel = NC;

    private String direccionIp;

    private String estadoRadicado;

    private String fechaFueroMaterno;

    public RadicadoDTO() {
    }

    public enum NivelCreacion{
        // NC : NIVEL CONTRATO PRIMER NIVEL DE CREACION DE CARPETA
        // NR : NIVEL RADICACION SEGUNDO NIVEL DE CREACION DE CARPETA
        NC,NR
    }

    public RadicadoDTO(String azCodigo, String deaCodigo , List<Documento> documentosACargar , String tipoACargar) {
        this.azCodigo = azCodigo;
        this.deaCodigo = deaCodigo;
        this.documentosACargar = documentosACargar;
        this.tipoACargar=tipoACargar;
    }
    
    
    
    public RadicadoDTO(Integer numeroRadicado, String azCodigo, String deaCodigo, Integer numeroDocumentoEmpleado, String tipoACargar,NivelCreacion nivel) {
        this.numeroRadicado = numeroRadicado;
        this.azCodigo = azCodigo;
        this.deaCodigo = deaCodigo;
        this.numeroDocumentoEmpleado = numeroDocumentoEmpleado;
        this.tipoACargar = tipoACargar;
        this.nivel =nivel;
    }

    public RadicadoDTO(String deaCodigo, Integer numeroDocumentoEmpleado, String tipoACargar, List<Documento> documentosACargar) {
        this.deaCodigo = deaCodigo;
        this.numeroDocumentoEmpleado = numeroDocumentoEmpleado;
        this.tipoACargar = tipoACargar;
        this.documentosACargar = documentosACargar;
    }

    public Integer getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(Integer numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
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

    public String getTipoACargar() {
        return tipoACargar;
    }

    public void setTipoACargar(String tipoACargar) {
        this.tipoACargar = tipoACargar;
    }

    public TipoAccion getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(TipoAccion tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public String getAzCodigo() {
        return azCodigo;
    }

    public void setAzCodigo(String azCodigo) {
        this.azCodigo = azCodigo;
    }

    public String getDeaCodigo() {
        return deaCodigo;
    }

    public void setDeaCodigo(String deaCodigo) {
        this.deaCodigo = deaCodigo;
    }

    public Integer getNumeroDocumentoEmpresaPrincipal() {
        return numeroDocumentoEmpresaPrincipal;
    }

    public void setNumeroDocumentoEmpresaPrincipal(Integer numeroDocumentoEmpresaPrincipal) {
        this.numeroDocumentoEmpresaPrincipal = numeroDocumentoEmpresaPrincipal;
    }

    public String getTipoDocumentoEmpresaPrincipal() {
        return tipoDocumentoEmpresaPrincipal;
    }

    public void setTipoDocumentoEmpresaPrincipal(String tipoDocumentoEmpresaPrincipal) {
        this.tipoDocumentoEmpresaPrincipal = tipoDocumentoEmpresaPrincipal;
    }

 

    public Integer getContrato() {
        return contrato;
    }

    public void setContrato(Integer contrato) {
        this.contrato = contrato;
    }

    public List<Documento> getDocumentosACargar() {
        return documentosACargar;
    }

    public void setDocumentosACargar(List<Documento> documentosACargar) {
        this.documentosACargar = documentosACargar;
    }

    public NivelCreacion getNivel() {
        return nivel;
    }

    public void setNivel(NivelCreacion nivel) {
        this.nivel = nivel;
    }


    public Integer getIdGrupoEnfermedad() {
        return idGrupoEnfermedad;
    }

    public void setIdGrupoEnfermedad(Integer idGrupoEnfermedad) {
        this.idGrupoEnfermedad = idGrupoEnfermedad;
    }

    public Integer getIdSubGrupoEnfermedad() {
        return idSubGrupoEnfermedad;
    }

    public void setIdSubGrupoEnfermedad(Integer idSubGrupoEnfermedad) {
        this.idSubGrupoEnfermedad = idSubGrupoEnfermedad;
    }

    public String getIdCodigoEnfermedad() {
        return idCodigoEnfermedad;
    }

    public void setIdCodigoEnfermedad(String idCodigoEnfermedad) {
        this.idCodigoEnfermedad = idCodigoEnfermedad;
    }

    public Integer getIdContigenciaIncapacidad() {
        return idContigenciaIncapacidad;
    }

    public void setIdContigenciaIncapacidad(Integer idContigenciaIncapacidad) {
        this.idContigenciaIncapacidad = idContigenciaIncapacidad;
    }

    public Integer getIdSubTipoContigencia() {
        return idSubTipoContigencia;
    }

    public void setIdSubTipoContigencia(Integer idSubTipoContigencia) {
        this.idSubTipoContigencia = idSubTipoContigencia;
    }

    public String getFechaIncidente() {
        return fechaIncidente;
    }

    public void setFechaIncidente(String fechaIncidente) {
        this.fechaIncidente = fechaIncidente;
    }

    public String getFechaInicioIncapacidad() {
        return fechaInicioIncapacidad;
    }

    public void setFechaInicioIncapacidad(String fechaInicioIncapacidad) {
        this.fechaInicioIncapacidad = fechaInicioIncapacidad;
    }

    public Integer getNumeroDeDias() {
        return numeroDeDias;
    }

    public void setNumeroDeDias(Integer numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }

    public String getProrroga() {
        return prorroga;
    }

    public void setProrroga(String prorroga) {
        this.prorroga = prorroga;
    }

    public Integer getIdUsuarioCrea() {
        return idUsuarioCrea;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public void setIdUsuarioCrea(Integer idUsuarioCrea) {
        this.idUsuarioCrea = idUsuarioCrea;
    }

    public String getFechaFueroMaterno() {
        return fechaFueroMaterno;
    }

    public void setFechaFueroMaterno(String fechaFueroMaterno) {
        this.fechaFueroMaterno = fechaFueroMaterno;
    }

    public String getEstadoRadicado() {
        return estadoRadicado;
    }

    public void setEstadoRadicado(String estadoRadicado) {
        this.estadoRadicado = estadoRadicado;
    }
}
