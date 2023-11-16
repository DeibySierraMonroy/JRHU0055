package co.com.activos.jrhu0055.DTO;

import java.io.Serializable;

public class RadicadoGenialDTO implements Serializable {

    private Integer numeroRadicado;
    private String tipoDocumento;
    private Integer numeroDocumento;
    private Integer numeroContrato;

    public RadicadoGenialDTO(Integer numeroRadicado, String tipoDocumento, Integer numeroDocumento, Integer numeroContrato) {
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

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(Integer numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Integer getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(Integer numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }
}
