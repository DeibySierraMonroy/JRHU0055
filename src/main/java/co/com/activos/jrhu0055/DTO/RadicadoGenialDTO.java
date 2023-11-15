package co.com.activos.jrhu0055.DTO;

import java.io.Serializable;

public class RadicadoGenialDTO implements Serializable {

    private Integer numeroRadicado;
    private String tipoDocumento;
    private String numeroDocumento;
    private String numeroContrato;

    public RadicadoGenialDTO(Integer numeroRadicado, String tipoDocumento, String numeroDocumento, String numeroContrato) {
        this.numeroRadicado = numeroRadicado;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.numeroContrato = numeroContrato;
    }

    public RadicadoGenialDTO() {
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Integer getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(Integer numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }
}
