package co.com.activos.jrhu0055.model;

import java.io.Serializable;

public class InformacionTaxonomia implements Serializable{
    private String idAzDigital;
    private String idDeaCodigo;
    private String nombreCarpeta;
    private String respuestaDelFlujo;
    private boolean estado = false;
    public enum RespuestaGenerica {
        SUCCESS("OK"),
        ERROR("EL SERVICIO HA RESPONDIDO ERROR DEBIDO A : ");
        private String estado;

        private RespuestaGenerica(String estado) {
            this.estado = estado;
        }
        public String getEstado() {
            return estado;
        }
        @Override
        public String toString() {
            return "RespuestaGenerica{" + "estado=" + estado + '}';
        }
    }

    public InformacionTaxonomia() {
    }

    public InformacionTaxonomia(String respuestaDelFlujo) {
        this.respuestaDelFlujo = respuestaDelFlujo;
    }


    public String getRespuestaDelFlujo() {
        return respuestaDelFlujo;
    }

    public void setRespuestaDelFlujo(String respuestaDelFlujo) {
        this.respuestaDelFlujo = respuestaDelFlujo;
    }

    public String getIdAzDigital() {
        return idAzDigital;
    }

    public void setIdAzDigital(String idAzDigital) {
        this.idAzDigital = idAzDigital;
    }

    public String getIdDeaCodigo() {
        return idDeaCodigo;
    }

    public void setIdDeaCodigo(String idDeaCodigo) {
        this.idDeaCodigo = idDeaCodigo;
    }

    public String getNombreCarpeta() {
        return nombreCarpeta;
    }

    public void setNombreCarpeta(String nombreCarpeta) {
        this.nombreCarpeta = nombreCarpeta;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}

