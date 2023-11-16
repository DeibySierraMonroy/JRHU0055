package co.com.activos.jrhu0055.Services.impl;

import co.com.activos.jrhu0055.DTO.RadicadoDTO;
import co.com.activos.jrhu0055.DTO.RadicadoGenialDTO;
import co.com.activos.jrhu0055.Services.IincapacidadService;
import co.com.activos.jrhu0055.model.DocumentoAlmacenado;
import co.com.activos.jrhu0055.model.InformacionTaxonomia;
import co.com.activos.jrhu0055.model.Taxonomia;
import co.com.activos.jrhu0055.utiliti.RespuestaGenerica;
import co.com.activos.jrhu0055.utiliti.TipoRespuesta;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static co.com.activos.jrhu0055.utiliti.ServicioRest.crearTaxonomiaIncapacidades;

public class CrearRadicadoService {

    private static final String _FLUJO = "DOCUMENTOS INCAPACIDADES";
    private static final String _TIPO_FLUJO = "9";
    private static final String _TIPO_DOCUMENTO = "A";
    private static final String _EXT_DOCUMENTO = ".PDF";
    private static final long _DEA_CODIGO_PADRE = 18648723;
    private static final String _AZ_CODIGO_PADRE = "1153181";
    private static final String _TIPO = "C";

    @Inject
    private IincapacidadService iincapacidadService;

    public RespuestaGenerica<DocumentoAlmacenado> crearRadicadoGenial(RadicadoGenialDTO radicadoGenialDTO) {
        try {

            if (Objects.nonNull(radicadoGenialDTO)) {
                //1. Nivel se buscar la taxonomia de la carpeta del empleado
                RespuestaGenerica<InformacionTaxonomia> validarTaxonomiaCarpetaEmpleado
                        = validarOCrearTaxonomiaGenial(radicadoGenialDTO.getTipoDocumento(), radicadoGenialDTO.getNumeroDocumento(), _DEA_CODIGO_PADRE);
                if (!validarTaxonomiaCarpetaEmpleado.getObjeto().isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                            "Error al crear la carpeta del empleado debido a : " + validarTaxonomiaCarpetaEmpleado.getMensaje());
                }

                //2. Se contruye el radicadoDTO dependiendo el nivel a crear
                RespuestaGenerica<RadicadoDTO> radicadoDTOContruido = contruirDTORadicado(
                        radicadoGenialDTO.getTipoDocumento(),
                        radicadoGenialDTO.getNumeroRadicado(),
                        radicadoGenialDTO.getNumeroContrato(),
                        radicadoGenialDTO.getNumeroRadicado(),
                        validarTaxonomiaCarpetaEmpleado.getObjeto().getIdAzDigital(),
                        validarTaxonomiaCarpetaEmpleado.getObjeto().getIdDeaCodigo(),
                        RadicadoDTO.NivelCreacion.NC);

                if(!TipoRespuesta.SUCCESS.equals(radicadoDTOContruido.getStatus())){
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                            "Error al construir el radicadoDTO debido a : "
                                    + validarTaxonomiaCarpetaEmpleado.getMensaje());
                }

                //3. Nivel se buscar la taxonomia de la carpeta contrato si no la tiene la crea
                RespuestaGenerica<InformacionTaxonomia> creacionCarpetaContrato = validarTaxonomia(radicadoDTOContruido.getObjeto());
                if (!creacionCarpetaContrato.getObjeto().isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                            "Error al crear la carpeta de contrato debido a : " + validarTaxonomiaCarpetaEmpleado.getMensaje());
                }

                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Taxonomias Creadas ");

            }

            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "No se puede procesar el radicado , revise la informacion a radicar.");
        } catch (RuntimeException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "No se puede procesar el radicado debido a " + e.getMessage());
        }

    }


    private RespuestaGenerica<RadicadoDTO> contruirDTORadicado(String tipoDocumento,
                                                                     Integer numeroDocumento,
                                                                     Integer numeroContrato,
                                                                     Integer numeroRadicado,
                                                                     String azCodigo,
                                                                     String deaCodigo,
                                                                     RadicadoDTO.NivelCreacion nivelCreacion) {
        try {
            RadicadoDTO radicadoDTO;
            if ("NC".equals(nivelCreacion.name())) {
                radicadoDTO  = new RadicadoDTO(tipoDocumento
                    , numeroDocumento
                    , numeroContrato
                    , azCodigo
                    , deaCodigo);
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "RadicadoDTO construido", radicadoDTO);
            }
            if ("NR".equals(nivelCreacion.name())) {
                radicadoDTO  = new RadicadoDTO(tipoDocumento
                        , numeroDocumento
                        , numeroRadicado
                        , azCodigo
                        , deaCodigo);
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "RadicadoDTO construido", radicadoDTO);
            }
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "No se puede procesar el radicado , error al construirlo ");

        } catch (Exception e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "No se puede construir  el radicado debido a " + e.getMessage());
        }

    }


    public RespuestaGenerica<InformacionTaxonomia> validarOCrearTaxonomiaGenial(String tipoDocumento,
                                                                                Integer numeroDocumento,
                                                                                long deaCodigo) {
        Taxonomia taxonomia;
        InformacionTaxonomia informacionTaxonomia;
        try {
            informacionTaxonomia = iincapacidadService.buscarTaxonomiaGenial(tipoDocumento, numeroDocumento, deaCodigo);
            if (informacionTaxonomia.isEstado()) {
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Taxonomia Encontrada", informacionTaxonomia);
            }
            taxonomia = new Taxonomia("C",
                    _AZ_CODIGO_PADRE, //ubicacion de la carpeta en AZ-Digital
                    tipoDocumento + " " + numeroDocumento,
                    String.valueOf(numeroDocumento),
                    _FLUJO,
                    String.valueOf(_DEA_CODIGO_PADRE)); //Ubicacion de la carpeta en base de datos
            return crearTaxonomia(taxonomia);

        } catch (Exception e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "No se puede procesar el radicado debido a " + e.getMessage());
        }
    }

    public RespuestaGenerica<DocumentoAlmacenado> crearRadicadoSitioTrabajador(RadicadoDTO radicadoDTO) {
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
                            crearRadicado.getMensaje());
                }

                RespuestaGenerica<InformacionTaxonomia> crearCarpetaRadicado = construirTaxonomiaParaCarpeta(
                        new RadicadoDTO(crearRadicado.getValorRetorno(), creacionCarpetaContrato.getObjeto().getIdAzDigital(),
                                creacionCarpetaContrato.getObjeto().getIdDeaCodigo(), radicadoDTO.getNumeroDocumentoEmpleado(),
                                radicadoDTO.getTipoACargar(), RadicadoDTO.NivelCreacion.NR)
                );
                if (!TipoRespuesta.SUCCESS.equals(crearCarpetaRadicado.getStatus())) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                            crearCarpetaRadicado.getMensaje());
                }

                RespuestaGenerica<DocumentoAlmacenado> cargarDocumentos = construirTaxonomiaParaDocumento(
                        new RadicadoDTO(crearCarpetaRadicado.getObjeto().getIdDeaCodigo(), radicadoDTO.getNumeroDocumentoEmpleado(),
                                _TIPO_DOCUMENTO, radicadoDTO.getDocumentosACargar()), crearRadicado.getValorRetorno()
                );

                if (!TipoRespuesta.SUCCESS.equals(cargarDocumentos.getStatus())) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                            cargarDocumentos.getMensaje());
                }
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, crearRadicado.getValorRetorno().toString(),
                        Objects.isNull(cargarDocumentos.getResultadoSubidaDocumentos())
                                ? "Radicado sin documentos"
                                : cargarDocumentos.getResultadoSubidaDocumentos(),
                        Objects.isNull(cargarDocumentos.getListaResultados())
                                ? new ArrayList<>()
                                : cargarDocumentos.getListaResultados());
            }
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "No se puede procesar el radicado , revise la informacion a radicar.");
        } catch (RuntimeException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "No se puede procesar el radicado debido a " + e.getMessage());
        }
    }

    public RespuestaGenerica<DocumentoAlmacenado> cargarDocumento(RadicadoDTO radicadoDTO) {

        if (Objects.nonNull(radicadoDTO.getNumeroRadicado())) {
            RespuestaGenerica<InformacionTaxonomia> taxonomiaEncontrada = validarTaxonomia(radicadoDTO);
            if (!TipoRespuesta.SUCCESS.equals(taxonomiaEncontrada.getStatus())) {
                return new RespuestaGenerica<>(TipoRespuesta.ERROR, taxonomiaEncontrada.getMensaje());
            }
            return construirTaxonomiaParaDocumento(new RadicadoDTO(taxonomiaEncontrada.getObjeto().getIdAzDigital(),
                            taxonomiaEncontrada.getObjeto().getIdDeaCodigo(),
                            radicadoDTO.getDocumentosACargar(),
                            radicadoDTO.getTipoACargar()),
                    radicadoDTO.getNumeroRadicado());
        }
        return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                "No se puede validar la taxonomia del documento ya que hay campos necesarios sin informacion");
    }

    private RespuestaGenerica<InformacionTaxonomia> validarTaxonomia(RadicadoDTO radicadoDTO) {
        InformacionTaxonomia informacionTaxonomia = new InformacionTaxonomia();
        try {
            if (Objects.nonNull(radicadoDTO.getDeaCodigo()) && (Objects.nonNull(radicadoDTO.getContrato()))) {

                informacionTaxonomia = iincapacidadService.obtenerInformacionTaxonomia(
                        radicadoDTO.getDeaCodigo(),
                        obtenerNombreCarpeta(radicadoDTO));

                if (informacionTaxonomia.isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Taxonomia Encontrada", informacionTaxonomia);
                }
                return construirTaxonomiaParaCarpeta(radicadoDTO);
            }
            if (Objects.nonNull(radicadoDTO.getNumeroRadicado())) {
                informacionTaxonomia = iincapacidadService.buscarTaxomiaRadicado(radicadoDTO.getNumeroRadicado(),
                        _TIPO_FLUJO).getObjeto();
                if (informacionTaxonomia.isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Taxonomia Encontrada", informacionTaxonomia);
                }
                return new RespuestaGenerica<>(TipoRespuesta.WARNING,
                        "El documento no se puede modificar "
                                + "ya que el radicado no cuenta con una carpeta.", informacionTaxonomia);
            }
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "No se puede validar la taxonomia debido a que hay campos necesarios sin informacion");
        } catch (RuntimeException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "Error en IncapacidadService:validarTaxonomia "
                            + "No se puedo validar la taxonomia debido  a " + e.getMessage());
        }
    }

    private RespuestaGenerica<InformacionTaxonomia> construirTaxonomiaParaCarpeta(RadicadoDTO radicadoDTO) {
        Taxonomia taxonomia;
        try {
            taxonomia = new Taxonomia(radicadoDTO.getTipoACargar(),
                    radicadoDTO.getAzCodigo(), //ubicacion de la carpeta en AZ-Digital
                    nivelDeCreacion(radicadoDTO),
                    String.valueOf(radicadoDTO.getNumeroDocumentoEmpleado()),
                    _FLUJO,
                    radicadoDTO.getDeaCodigo()); //Ubicacion de la carpeta en base de datos
            return crearTaxonomia(taxonomia);
        } catch (Exception e) {
            return new RespuestaGenerica(TipoRespuesta.ERROR,
                    "Error al Crear la taxonomia en : IncapacidadService:crearTaxonomia " + e.getMessage());
        }
    }

    private RespuestaGenerica<DocumentoAlmacenado> construirTaxonomiaParaDocumento(RadicadoDTO radicadoDTO, Integer numeroRadicado) {
        List<DocumentoAlmacenado> documentoProcesadosEnAz = new ArrayList<>();
        try {
            radicadoDTO.getDocumentosACargar().stream()
                    .map(documento -> {
                        Taxonomia taxonomiaDocumento = new Taxonomia(
                                documento.getNombreDocumento() + _EXT_DOCUMENTO,
                                radicadoDTO.getTipoACargar(),
                                String.valueOf(radicadoDTO.getNumeroDocumentoEmpleado()),
                                _FLUJO,
                                radicadoDTO.getDeaCodigo(),
                                Integer.parseInt(documento.getIdDocumento()),
                                documento.getBase64()
                        );
                        RespuestaGenerica<InformacionTaxonomia> respuesSubidaDeDocumento = crearTaxonomia(taxonomiaDocumento);
                        if (TipoRespuesta.SUCCESS.equals(respuesSubidaDeDocumento.getStatus())) {
                            documentoProcesadosEnAz.add(validarProcesoDeSubida(Boolean.TRUE,
                                    documento.getIdDocumento(), documento.getNombreDocumento(), String.valueOf(numeroRadicado)));
                            actulizarDocumentoCargado(
                                    Integer.valueOf(respuesSubidaDeDocumento.getObjeto().getIdDeaCodigo()),
                                    numeroRadicado,
                                    Integer.valueOf(documento.getIdDocumento()));
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

    private static DocumentoAlmacenado validarProcesoDeSubida(boolean proceso, String nombreDocumento, String idDocumento, String radicado) {
        return new DocumentoAlmacenado(proceso, nombreDocumento, idDocumento, radicado);
    }

    private static RespuestaGenerica<DocumentoAlmacenado> extraerRespuesDocumentosProcesados(List<DocumentoAlmacenado> documentoProcesados) {
        float documentoFallidos = 0;
        float documentosSubidos = 0;
        if (documentoProcesados.isEmpty()) {
            return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "OK");
        }
        documentoFallidos = documentoProcesados.stream().filter(documentoAlmacenado -> !documentoAlmacenado.getEstadoDelProceso()).count();
        documentosSubidos = documentoProcesados.stream().filter(documentoAlmacenado -> documentoAlmacenado.getEstadoDelProceso()).count();
        String resultadoDelProceso = "Documentos cargados : " + documentosSubidos + " - "
                + "Documentos sin cargar : " + documentoFallidos;
        return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "OK", resultadoDelProceso, documentoProcesados);
    }

    private static RespuestaGenerica<InformacionTaxonomia> crearTaxonomia(Taxonomia taxonomia) {
        try {
            RespuestaGenerica<InformacionTaxonomia> respuestaGenerica = crearTaxonomiaIncapacidades(taxonomia);
            if (TipoRespuesta.SUCCESS.equals(respuestaGenerica.getStatus())) {
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Taxonomia Creada", respuestaGenerica.getObjeto());
            }
            return new RespuestaGenerica(TipoRespuesta.ERROR, respuestaGenerica.getMensaje());
        } catch (Exception e) {
            return new RespuestaGenerica(TipoRespuesta.ERROR, "Error al Crear la taxonomia en : IncapacidadService:crearTaxonomia " + e.getMessage());
        }
    }

    private RespuestaGenerica<Integer> crearRadicadoEnBaseDeDatos(RadicadoDTO radicadoDTO) {
        RespuestaGenerica<Integer> radicadoCreado = iincapacidadService.crearRadicado(radicadoDTO);
        if (!TipoRespuesta.SUCCESS.equals(radicadoCreado.getStatus())) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error al crear el radicado debido a : "
                    + radicadoCreado.getMensaje());
        }
        return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Radicacion Creada", radicadoCreado.getValorRetorno());
    }

    private String obtenerNombreCarpeta(RadicadoDTO radicadoDTO) {
        return radicadoDTO.getNumeroRadicado() != null
                ? String.valueOf(radicadoDTO.getNumeroRadicado())
                : String.valueOf(radicadoDTO.getContrato());
    }

    private String nivelDeCreacion(RadicadoDTO radicadoDTO) {
        return RadicadoDTO.NivelCreacion.NR.equals(radicadoDTO.getNivel())
                ? String.valueOf(radicadoDTO.getNumeroRadicado())
                : String.valueOf(radicadoDTO.getContrato());
    }

    private void actulizarDocumentoCargado(Integer deaCodigo, Integer numeroRadico, Integer codigoDocumento) {
        RespuestaGenerica<Boolean> documentoActualizado = iincapacidadService
                .actualizarEstadoDocumento(deaCodigo, numeroRadico, codigoDocumento);
    }
}
