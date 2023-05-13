package co.com.activos.jrhu0055.Services.impl;

import co.com.activos.jrhu0055.DTO.*;
import co.com.activos.jrhu0055.Services.IincapacidadService;
import co.com.activos.jrhu0055.model.*;
import co.com.activos.jrhu0055.utiliti.RespuestaGenerica;
import javax.inject.Inject;
import java.util.List;


public class IncapacidadService {


    @Inject
    private IincapacidadService iincapacidadService;

    public List<Incapacidad> listaIncapacidades(ContratoDTO contratoDTO) {
        return iincapacidadService.incapacidades(contratoDTO);
    }

    public List<TipoIncapacidad> listaTipoIncapacidades() {
        return iincapacidadService.listarTipoIncapacidades();
    }

    public List<SubTipoIncapacidad> listaSubTipoIncapacidades(Integer codigoTipoIncapacidad) {
        return iincapacidadService.listarSubTipoIncapacidades(codigoTipoIncapacidad);
    }
    public List<Contrato> listaContratos(ContratoDTO contratoDTO) {
        return iincapacidadService.listarContratos(contratoDTO); 
    }
    public List<DocumentoPorSubtipoIncapacidad> listarDocumentos(Integer codigoSubTipoIncapacidad) {
        return iincapacidadService.listarDocumentos(codigoSubTipoIncapacidad);
    }
    public RespuestaGenerica<Enfermedad> listarEnfermedades(){
        return iincapacidadService.listarEnfermedades();
    }
    public RespuestaGenerica<TerminosYCondiciones> obtenerTerminosYCondiciones(){
        return iincapacidadService.obtenerTerminosYCondiciones();
    }

    public RespuestaGenerica<String> validacionesRadicado(ValidacionRadicadoDTO validacionRadicadoDTO){
        return iincapacidadService.validacionRadicado(validacionRadicadoDTO);
    }

    public RespuestaGenerica<DocumentoCargado> documentosCargadosPorRadicado(String ipUsuario,Integer numeroRadicado){
        return iincapacidadService.documentosCargados(numeroRadicado,ipUsuario);
    }

    public RespuestaGenerica<String> documentoActualizado(DocumentoActualizadoDTO documentoActualizadoDTO){
        return iincapacidadService.actualizarEstadoDocumento(documentoActualizadoDTO);
    }

    public RespuestaGenerica<String>radicadoActualizado(Integer numeroRadicado,String estadoActualizar) {
        return iincapacidadService.actualizarEstadoRadicacion(numeroRadicado, estadoActualizar);
    }

    public RespuestaGenerica<Incapacidad> listarIncapacidades(){
        return iincapacidadService.listarIncapacidadesMesa();
    }

    public RespuestaGenerica<Integer> crearGers(ContratoDTO contratoDTO){
        return iincapacidadService.crearGers(contratoDTO);
    }


}
