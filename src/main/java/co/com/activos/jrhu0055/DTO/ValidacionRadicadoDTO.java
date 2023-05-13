package co.com.activos.jrhu0055.DTO;

import java.io.Serializable;

public class ValidacionRadicadoDTO implements Serializable {

    private Integer numeroDeDias;
    private Integer numeroContrato;
    private ContratoDTO contratoDTO;

    private String fechaInicio;

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }



    public Integer getNumeroDeDias() {
        return numeroDeDias;
    }

    public void setNumeroDeDias(Integer numeroDeDias) {
        this.numeroDeDias = numeroDeDias;
    }

    public Integer getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(Integer numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public ContratoDTO getContratoDTO() {
        return contratoDTO;
    }

    public void setContratoDTO(ContratoDTO contratoDTO) {
        this.contratoDTO = contratoDTO;
    }
}
