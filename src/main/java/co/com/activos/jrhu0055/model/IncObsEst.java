package co.com.activos.jrhu0055.model;

/**
 *
 * @author egacha
 */
public class IncObsEst {

    private String obsEstado;
    private String obsTipo;
    private String obsDescripcion;

    public IncObsEst() {
    }

    public IncObsEst(String obsEstado, String obsTipo, String obsDescripcion) {
        this.obsEstado = obsEstado;
        this.obsTipo = obsTipo;
        this.obsDescripcion = obsDescripcion;
    }

    public String getObsEstado() {
        return obsEstado;
    }

    public void setObsEstado(String obsEstado) {
        this.obsEstado = obsEstado;
    }

    public String getObsTipo() {
        return obsTipo;
    }

    public void setObsTipo(String obsTipo) {
        this.obsTipo = obsTipo;
    }

    public String getObsDescripcion() {
        return obsDescripcion;
    }

    public void setObsDescripcion(String obsDescripcion) {
        this.obsDescripcion = obsDescripcion;
    }

}
