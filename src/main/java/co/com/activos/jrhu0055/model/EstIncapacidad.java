package co.com.activos.jrhu0055.model;

/**
 *
 * @author egacha
 */
public class EstIncapacidad {

    private Integer esinCodigo;
    private String incEstado;
    private String incDescripcion;
    private String audFecha;
    private String audUsuario;

    public EstIncapacidad(Integer esinCodigo, String incEstado, String incDescripcion, String audFecha, String audUsuario) {
        this.esinCodigo = esinCodigo;
        this.incEstado = incEstado;
        this.incDescripcion = incDescripcion;
        this.audFecha = audFecha;
        this.audUsuario = audUsuario;
    }

    public EstIncapacidad() {
    }

    public Integer getEsinCodigo() {
        return esinCodigo;
    }

    public void setEsinCodigo(Integer esinCodigo) {
        this.esinCodigo = esinCodigo;
    }

    public String getIncEstado() {
        return incEstado;
    }

    public void setIncEstado(String incEstado) {
        this.incEstado = incEstado;
    }

    public String getIncDescripcion() {
        return incDescripcion;
    }

    public void setIncDescripcion(String incDescripcion) {
        this.incDescripcion = incDescripcion;
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
