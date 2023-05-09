package co.com.activos.jrhu0055.Services.impl;

import co.com.activos.jrhu0055.DTO.ContratoDTO;
import co.com.activos.jrhu0055.DTO.RadicadoDTO;
import co.com.activos.jrhu0055.DTO.ValidacionRadicadoDTO;
import co.com.activos.jrhu0055.model.*;
import co.com.activos.jrhu0055.repo.IincapacidaesRepo;
import co.com.activos.jrhu0055.utiliti.RespuestaGenerica;
import co.com.activos.jrhu0055.utiliti.TipoRespuesta;

import javax.inject.Inject;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static co.com.activos.jrhu0055.utiliti.ServicioRest.crearTaxonomiaIncapacidades;

public class IncapacidadService {


    @Inject
    private IincapacidaesRepo iincapacidaesRepo;

    public List<Incapacidad> listaIncapacidades(ContratoDTO contratoDTO) {
        return iincapacidaesRepo.incapacidades(contratoDTO);
    }

    public List<TipoIncapacidad> listaTipoIncapacidades() {
        return iincapacidaesRepo.listarTipoIncapacidades();
    }

    public List<SubTipoIncapacidad> listaSubTipoIncapacidades(Integer codigoTipoIncapacidad) {
        return iincapacidaesRepo.listarSubTipoIncapacidades(codigoTipoIncapacidad);
    }
    public List<Contrato> listaContratos(ContratoDTO contratoDTO) {
        return iincapacidaesRepo.listarContratos(contratoDTO);
    }
    public List<DocumentoPorSubtipoIncapacidad> listarDocumentos(Integer codigoSubTipoIncapacidad) {
        return iincapacidaesRepo.listarDocumentos(codigoSubTipoIncapacidad);
    }
    public RespuestaGenerica<Enfermedad> listarEnfermedades(){
        return iincapacidaesRepo.listarEnfermedades();
    }
    public RespuestaGenerica<TerminosYCondiciones> obtenerTerminosYCondiciones(){
        return iincapacidaesRepo.obtenerTerminosYCondiciones();
    }

    public RespuestaGenerica<String> validacionesRadicado(ValidacionRadicadoDTO validacionRadicadoDTO){
        return iincapacidaesRepo.validacionRadicado(validacionRadicadoDTO);
    }

}
