package co.com.activos.jrhu0055.DTO;

import java.io.Serializable;

public class ValidacionRadicadoDTO implements Serializable {

    private Integer numeroDeDias;
    private Integer numeroContrato;
    private ContratoDTO contratoDTO;
    private String fechaInicio;
    private String fechaAccidente;
    private Integer idContigenciaIncapacidad;
    private Integer numeroIncapacidad;

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

    public String getFechaAccidente() {
        return fechaAccidente;
    }

    public void setFechaAccidente(String fechaAccidente) {
        this.fechaAccidente = fechaAccidente;
    }

    public Integer getIdContigenciaIncapacidad() {
        return idContigenciaIncapacidad;
    }

    public void setIdContigenciaIncapacidad(Integer idContigenciaIncapacidad) {
        this.idContigenciaIncapacidad = idContigenciaIncapacidad;
    }

    public Integer getNumeroIncapacidad() {
        return numeroIncapacidad;
    }

    public void setNumeroIncapacidad(Integer numeroIncapacidad) {
        this.numeroIncapacidad = numeroIncapacidad;
    }

}
