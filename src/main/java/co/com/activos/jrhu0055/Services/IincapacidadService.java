package co.com.activos.jrhu0055.Services;

import co.com.activos.jrhu0055.DTO.ContratoDTO;
import co.com.activos.jrhu0055.DTO.RadicadoDTO;
import co.com.activos.jrhu0055.DTO.ValidacionRadicadoDTO;
import co.com.activos.jrhu0055.model.*;
import co.com.activos.jrhu0055.utiliti.RespuestaGenerica;

import java.util.List;

public interface IincapacidadService {

    List<Incapacidad> incapacidades(ContratoDTO contratoDTO);

    List<TipoIncapacidad> listarTipoIncapacidades();

    List<SubTipoIncapacidad> listarSubTipoIncapacidades(Integer codigoTipoIncapacidad);

    List<Contrato> listarContratos(ContratoDTO contratoDTO);

    List<DocumentoPorSubtipoIncapacidad> listarDocumentos (Integer codigoSubTipoIncapacidad);

    InformacionTaxonomia obtenerInformacionTaxonomia(String deaCodigo,String nombreCarpeta);

    RespuestaGenerica<Enfermedad> listarEnfermedades();

    RespuestaGenerica<TerminosYCondiciones> obtenerTerminosYCondiciones();

    RespuestaGenerica<Boolean> actualizarEstadoDocumento(Integer deaCodigo,Integer numeroRadico, Integer codigoDocumento);

    RespuestaGenerica<Integer> crearRadicado(RadicadoDTO radicadoDTO);

    RespuestaGenerica<String> validacionRadicado(ValidacionRadicadoDTO validacionRadicadoDTO);








}
