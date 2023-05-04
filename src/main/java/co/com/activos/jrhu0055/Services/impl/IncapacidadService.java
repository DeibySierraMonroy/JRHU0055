package co.com.activos.jrhu0055.Services.impl;

import co.com.activos.jrhu0055.DTO.ContratoDTO;
import co.com.activos.jrhu0055.DTO.RadicadoDTO;
import co.com.activos.jrhu0055.model.*;
import co.com.activos.jrhu0055.repo.IincapacidaesRepo;
import co.com.activos.jrhu0055.utiliti.RespuestaGenerica;
import co.com.activos.jrhu0055.utiliti.TipoRespuesta;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static co.com.activos.jrhu0055.utiliti.ServicioRest.crearTaxonomiaIncapacidades;

public class IncapacidadService {

    private static final String _FLUJO = "DOCUMENTOS INCAPACIDADES";
    private static final String _TIPO_DOCUMENTO = "A";
    private static final String _EXT_DOCUMENTO = ".PDF";


    @Inject
    private IincapacidaesRepo iincapacidaesRepo;

    public List<Incapacidad> listaIncapacidades() {
        return iincapacidaesRepo.incapacidades();
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


    public RespuestaGenerica<DocumentoAlmacenado> crearRadicado(RadicadoDTO radicadoDTO) {
        try {
            if (Objects.nonNull(radicadoDTO)) {
                RespuestaGenerica<InformacionTaxonomia> creacionCarpetaContrato = validarTaxonomia(radicadoDTO);
                if (!creacionCarpetaContrato.getObjeto().isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                            "Error al crear la carpeta de contrato debido a : " + creacionCarpetaContrato.getMensaje());
                }

                RespuestaGenerica<Integer> crearRadicado = crearRadicadoEnBaseDeDatos(radicadoDTO);
                if (!TipoRespuesta.SUCCESS.equals(crearRadicado.getStatus())) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                            "Error al crear el radicado debido  a : " + crearRadicado.getMensaje());
                }

                RespuestaGenerica<InformacionTaxonomia> crearCarpetaRadicado = construirTaxonomiaParaCarpeta(
                        new RadicadoDTO(crearRadicado.getObjeto(), creacionCarpetaContrato.getObjeto().getIdAzDigital(),
                                creacionCarpetaContrato.getObjeto().getIdDeaCodigo(), radicadoDTO.getNumeroDocumentoEmpleado(),
                                radicadoDTO.getTipoACargar(), RadicadoDTO.NivelCreacion.NR)
                );
                if (!crearCarpetaRadicado.getObjeto().isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                            "Error al crear la carpeta de radicado debido a : " + creacionCarpetaContrato.getMensaje());
                }

                RespuestaGenerica<DocumentoAlmacenado> cargarDocumentos = construirTaxonomiaParaDocumento(
                        new RadicadoDTO(crearCarpetaRadicado.getObjeto().getIdDeaCodigo(), radicadoDTO.getNumeroDocumentoEmpleado(),
                                _TIPO_DOCUMENTO, radicadoDTO.getDocumentosACargar()), crearRadicado.getObjeto()
                );

                if (!TipoRespuesta.SUCCESS.equals(cargarDocumentos.getStatus())) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                            "Error al subir los documentos debido a : " + cargarDocumentos.getMensaje());
                }
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Radicado : " + crearRadicado.getObjeto()
                        ,cargarDocumentos.getResultadoSubidaDocumentos()
                        ,cargarDocumentos.getListaResultados() );

            }
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "No se puede procesar el radicado , revise la informacion a radicar.");
        } catch (RuntimeException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "No se puede procesar el radicado debido a " + e.getMessage());
        }
    }

    private RespuestaGenerica<InformacionTaxonomia> validarTaxonomia(RadicadoDTO radicadoDTO) {
        InformacionTaxonomia informacionTaxonomia;
        try {
            if (Objects.nonNull(radicadoDTO.getDeaCodigo()) && (Objects.nonNull(radicadoDTO.getContrato()))) {
                informacionTaxonomia = iincapacidaesRepo.obtenerInformacionTaxonomia(
                        radicadoDTO.getDeaCodigo(),
                        obtenerNombreCarpeta(radicadoDTO));
                if (informacionTaxonomia.isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Taxonomia Encontrada", informacionTaxonomia);
                }
                return construirTaxonomiaParaCarpeta(radicadoDTO);
            }
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "No se puede validar la taxonomia debido a que hay campos necesarios sin informacion");
        } catch (RuntimeException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "Error en IncapacidadService:validarTaxonomia " +
                            "No se puedo validar la taxonomia debido  a " + e.getMessage());
        }
    }

    public RespuestaGenerica<InformacionTaxonomia> construirTaxonomiaParaCarpeta(RadicadoDTO radicadoDTO) {
        Taxonomia taxonomia;
        try {
            taxonomia = new Taxonomia(radicadoDTO.getTipoACargar(),
                    radicadoDTO.getAzCodigo(),
                    nivelDeCreacion(radicadoDTO),
                    String.valueOf(radicadoDTO.getNumeroDocumentoEmpleado()),
                    _FLUJO,
                    radicadoDTO.getDeaCodigo());
            return crearTaxonomia(taxonomia);
        } catch (Exception e) {
            return new RespuestaGenerica(TipoRespuesta.ERROR,
                    "Error al Crear la taxonomia en : IncapacidadService:crearTaxonomia " + e.getMessage());
        }
    }

    public RespuestaGenerica<DocumentoAlmacenado> construirTaxonomiaParaDocumento(RadicadoDTO radicadoDTO , Integer numeroRadicado) {
        List<DocumentoAlmacenado> documentoProcesadosEnAz = new ArrayList<>();
        try {
            radicadoDTO.getDocumentosACargar().stream()
                    .map(documento -> {
                        Taxonomia taxonomiaDocumento = new Taxonomia(
                                documento.getNombreDocumento()+_EXT_DOCUMENTO,
                                radicadoDTO.getTipoACargar(),
                                String.valueOf(radicadoDTO.getNumeroDocumentoEmpleado()),
                                _FLUJO,
                                radicadoDTO.getDeaCodigo(),
                                Integer.parseInt(documento.getIdDocumento()),
                                documento.getBase64()
                        );
                        RespuestaGenerica<InformacionTaxonomia> respuesSubidaDeDocumento = crearTaxonomia(taxonomiaDocumento);
                        if (TipoRespuesta.SUCCESS .equals(respuesSubidaDeDocumento.getStatus())) {
                            documentoProcesadosEnAz.add(validarProcesoDeSubida(Boolean.TRUE,
                                    documento.getIdDocumento(), documento.getNombreDocumento(), String.valueOf(numeroRadicado)));
                            actulizarDocumentoCargado(
                                    Integer.valueOf(respuesSubidaDeDocumento.getObjeto().getIdDeaCodigo())
                                    ,numeroRadicado
                                    ,Integer.valueOf(documento.getIdDocumento()));
                        } else {
                            documentoProcesadosEnAz.add(validarProcesoDeSubida(Boolean.FALSE, documento.getIdDocumento(),
                                    documento.getNombreDocumento(), String.valueOf(numeroRadicado)));
                        }
                        return documentoProcesadosEnAz;
                    }).collect(Collectors.toList());

        } catch (Exception e) {
            new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error no contralado en : IncapacidadService:subirDocumentos debido a :" + e);
        }
        return extraerRespuesDocumentosProcesados(documentoProcesadosEnAz);
    }

    private DocumentoAlmacenado validarProcesoDeSubida(boolean proceso, String nombreDocumento, String idDocumento,String radicado) {
        return new DocumentoAlmacenado(proceso, nombreDocumento, idDocumento,radicado);
    }

    private RespuestaGenerica<DocumentoAlmacenado> extraerRespuesDocumentosProcesados(List<DocumentoAlmacenado> documentoProcesados) {
        InformacionTaxonomia informacionTaxonomia = new InformacionTaxonomia();
        float documentoFallidos = 0;
        float documentosSubidos = 0;
        if (documentoProcesados.isEmpty()) {
            informacionTaxonomia.setEstado(true);
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error al extraer la respuesta en IncapacidadService:extraerRespuesDocumentosProcesados debido a" +
                    "que la lista no tiene valores a procesar" );
        }
         documentoFallidos = documentoProcesados.stream().filter(documentoAlmacenado -> !documentoAlmacenado.getEstadoDelProceso()).count();
         documentosSubidos = documentoProcesados.stream().filter(documentoAlmacenado -> documentoAlmacenado.getEstadoDelProceso()).count();
        String resultadoDelProceso = "Documentos Exitosos : " + documentosSubidos + " - "
                + "Documentos Fallidos : " + documentoFallidos ;
        informacionTaxonomia.setEstado(true);
        return new RespuestaGenerica<>(TipoRespuesta.SUCCESS,"OK" ,resultadoDelProceso,documentoProcesados);
    }

    private RespuestaGenerica<InformacionTaxonomia> crearTaxonomia(Taxonomia taxonomia) {
        try {
            RespuestaGenerica<InformacionTaxonomia> respuestaGenerica = crearTaxonomiaIncapacidades(taxonomia);
            if (respuestaGenerica.getObjeto().isEstado()) {
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Taxonomia Creada Documento", respuestaGenerica.getObjeto());
            }
            return new RespuestaGenerica(TipoRespuesta.ERROR, respuestaGenerica.getMensaje());
        } catch (Exception e) {
            return new RespuestaGenerica(TipoRespuesta.ERROR, "Error al Crear la taxonomia en : IncapacidadService:crearTaxonomiaDocumento " + e.getMessage());
        }
    }

    private RespuestaGenerica<Integer> crearRadicadoEnBaseDeDatos(RadicadoDTO radicadoDTO) {
        RespuestaGenerica<Integer> radicadoCreado = iincapacidaesRepo.crearRadicado(radicadoDTO);
        if (!TipoRespuesta.SUCCESS.equals(radicadoCreado.getStatus())){
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,"Error al crear el radicado debido a : " +
                    radicadoCreado.getMensaje());
        }
        return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Radicacion Creada",radicadoCreado.getObjeto());
    }

    private String obtenerNombreCarpeta(RadicadoDTO radicadoDTO){
        return  radicadoDTO.getNumeroRadicado() != null
                ? String.valueOf(radicadoDTO.getNumeroRadicado())
                : String.valueOf(radicadoDTO.getContrato());
    }

    private String nivelDeCreacion(RadicadoDTO radicadoDTO){
        return   RadicadoDTO.NivelCreacion.NR.equals(radicadoDTO.getNivel())
                ? String.valueOf(radicadoDTO.getNumeroRadicado())
                : String.valueOf(radicadoDTO.getContrato());
    }

    private void actulizarDocumentoCargado(Integer deaCodigo,Integer numeroRadico, Integer codigoDocumento){
        RespuestaGenerica<Boolean> documentoActualizado = iincapacidaesRepo
                .actualizarEstadoDocumento(deaCodigo,numeroRadico,codigoDocumento);
    }

}
