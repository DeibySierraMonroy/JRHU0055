package co.com.activos.jrhu0055.DTO;

import co.com.activos.jrhu0055.model.Documento;
import co.com.activos.jrhu0055.utiliti.TipoAccion;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static co.com.activos.jrhu0055.DTO.RadicadoDTO.NivelCreacion.NC;
import java.util.UUID;

/**
 *
 * @author desierra
 */
public class RadicadoDTO implements Serializable {

    private String tipoDocumentoEmpleado;//OK
    private Integer numeroDocumentoEmpleado;//OK
    private Integer numeroDocumentoEmpresaPrincipal;//OK
    private String tipoDocumentoEmpresaPrincipal;//OK
    private Integer contrato;//OK    
    private Integer idGrupoEnfermedad;//OK
    private Integer idSubGrupoEnfermedad;//OK
    private String idCodigoEnfermedad;//OK
    private Integer idContigenciaIncapacidad;//OK
    private Integer idSubTipoContigencia;//OK
    private String fechaIncidente;//OK
    private String fechaInicioIncapacidad;//OK
    private Integer numeroDeDias;//OK
    private String prorroga;//OK
    private Integer idUsuarioCrea;//OK
    private Integer numeroRadicado;
    private Integer numeroIncapacidad;//OK
    private String azCodigo;//OK
    private String deaCodigo;//OK
    private String tipoACargar;//OK
    private TipoAccion tipoAccion;
    private List<Documento> documentosACargar;//OK
    private NivelCreacion nivel = NC;

    private String direccionIp;//OK

    private String estadoRadicado;

    private String fechaFueroMaterno;//OK

    private String observacion;

    private Origen origen = Origen.RST;

    private Optional<RadicadoGenialDTO> radicadoGenialDTO;

    private String idTransaccionRadicado;

    public Optional<RadicadoGenialDTO> getRadicadoGenialDTO() {
        return radicadoGenialDTO;
    }

    public void setRadicadoGenialDTO(Optional<RadicadoGenialDTO> radicadoGenialDTO) {
        this.radicadoGenialDTO = radicadoGenialDTO;
    }

    public RadicadoDTO() {
        this.idTransaccionRadicado = UUID.randomUUID().toString();
        
    }

    public enum Origen {
        // RG : CREAR RADICADO DESDE GENIAL
        // RST : CREAR RADICADO DESDE SITIO TRABAJADOR
        RG, RST
    }

    public enum NivelCreacion {
        // NC : NIVEL CONTRATO PRIMER NIVEL DE CREACION DE CARPETA
        // NR : NIVEL RADICACION SEGUNDO NIVEL DE CREACION DE CARPETA
        NC, NR
    }

    public RadicadoDTO(String azCodigo, String deaCodigo, List<Documento> documentosACargar, String tipoACargar, String idTransaccionRadicado) {
        this.idTransaccionRadicado = idTransaccionRadicado;
        this.azCodigo = azCodigo;
        this.deaCodigo = deaCodigo;
        this.documentosACargar = documentosACargar;
        this.tipoACargar = tipoACargar;
    }

    public RadicadoDTO(Integer numeroRadicado, String azCodigo, String deaCodigo, Integer numeroDocumentoEmpleado, String tipoACargar, NivelCreacion nivel, String idTransaccionRadicado) {
        this.idTransaccionRadicado = idTransaccionRadicado;
        this.numeroRadicado = numeroRadicado;
        this.azCodigo = azCodigo;
        this.deaCodigo = deaCodigo;
        this.numeroDocumentoEmpleado = numeroDocumentoEmpleado;
        this.tipoACargar = tipoACargar;
        this.nivel = nivel;
    }

    public RadicadoDTO(String deaCodigo, Integer numeroDocumentoEmpleado, String tipoACargar, List<Documento> documentosACargar, String idTransaccionRadicado) {
        this.idTransaccionRadicado = idTransaccionRadicado;
        this.deaCodigo = deaCodigo;
        this.numeroDocumentoEmpleado = numeroDocumentoEmpleado;
        this.tipoACargar = tipoACargar;
        this.documentosACargar = documentosACargar;
    }

    public RadicadoDTO(String tipoDocumentoEmpleado, Integer numeroDocumentoEmpleado, Integer contrato, String azCodigo, String deaCodigo, String tipo, List<Documento> documentos, String idTransaccionRadicado) {
        this.idTransaccionRadicado = idTransaccionRadicado;
        this.tipoDocumentoEmpleado = tipoDocumentoEmpleado;
        this.numeroDocumentoEmpleado = numeroDocumentoEmpleado;
        this.contrato = contrato;
        this.azCodigo = azCodigo;
        this.deaCodigo = deaCodigo;
        this.tipoACargar = tipo;
        this.documentosACargar = documentos;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setEstadoRadicado(String estadoRadicado) {
        this.estadoRadicado = estadoRadicado;
    }

    public Integer getNumeroIncapacidad() {
        return numeroIncapacidad;
    }

    public void setNumeroIncapacidad(Integer numeroIncapacidad) {
        this.numeroIncapacidad = numeroIncapacidad;
    }

    public Origen getOrigen() {
        return origen;
    }

    public void setOrigen(Origen origen) {
        this.origen = origen;
    }

    public String getIdTransaccionRadicado() {
        return idTransaccionRadicado;
    }

    public void setIdTransaccionRadicado(String idTransaccionRadicado) {
        this.idTransaccionRadicado = idTransaccionRadicado;
    }

}
