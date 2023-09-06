package co.com.activos.jrhu0055.model;

/**
 *
 * @author egacha
 */
public class EstadoIncPortalTrabajador {

    private Integer estCodigo;
    IncObsEst incObsEst;
    EstIncapacidad estIncapacidad;
    private String Observacion;
    private String audFecha;
    private String audUsuario;

    public EstadoIncPortalTrabajador(Integer estCodigo, IncObsEst incObsEst, EstIncapacidad estIncapacidad, String Observacion, String audFecha, String audUsuario) {
        this.estCodigo = estCodigo;
        this.incObsEst = incObsEst;
        this.estIncapacidad = estIncapacidad;
        this.Observacion = Observacion;
        this.audFecha = audFecha;
        this.audUsuario = audUsuario;
    }

    public EstadoIncPortalTrabajador() {
    }

    public Integer getEstCodigo() {
        return estCodigo;
    }

    public void setEstCodigo(Integer estCodigo) {
        this.estCodigo = estCodigo;
    }

    public IncObsEst getIncObsEst() {
        return incObsEst;
    }

    public void setIncObsEst(IncObsEst incObsEst) {
        this.incObsEst = incObsEst;
    }

    public EstIncapacidad getEstIncapacidad() {
        return estIncapacidad;
    }

    public void setEstIncapacidad(EstIncapacidad estIncapacidad) {
        this.estIncapacidad = estIncapacidad;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getAudFecha() {
        return audFecha;
    }

    public void setAudFecha(String audFecha) {
        this.audFecha = audFecha;
    }

    public String getAudUsuario() {
        return audUsuario;
    }

    public void setAudUsuario(String audUsuario) {
        this.audUsuario = audUsuario;
    }

}
