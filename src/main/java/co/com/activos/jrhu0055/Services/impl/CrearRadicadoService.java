package co.com.activos.jrhu0055.Services.impl;

import co.com.activos.jrhu0055.DTO.RadicadoDTO;
import co.com.activos.jrhu0055.DTO.RadicadoGenialDTO;
import co.com.activos.jrhu0055.Services.IincapacidadService;
import co.com.activos.jrhu0055.Services.IncapacidadAuditoria;
import co.com.activos.jrhu0055.model.Documento;
import co.com.activos.jrhu0055.model.DocumentoAlmacenado;
import co.com.activos.jrhu0055.model.InformacionTaxonomia;
import co.com.activos.jrhu0055.model.Taxonomia;
import co.com.activos.jrhu0055.utiliti.CodigoError;
import co.com.activos.jrhu0055.utiliti.RespuestaGenerica;
import co.com.activos.jrhu0055.utiliti.TipoRespuesta;

import static co.com.activos.jrhu0055.utiliti.CodigoError.*;
import static co.com.activos.jrhu0055.utiliti.ServicioRest.crearTaxonomiaIncapacidades;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CrearRadicadoService {

    private static final String FLUJO = "DOCUMENTOS INCAPACIDADES";
    private static final String TIPO_FLUJO = "9";
    private static final String TIPO_DOCUMENTO = "A";
    private static final String EXT_DOCUMENTO = ".PDF";
    private static final long DEA_CODIGO_PADRE = 18648723;
    private static final String AZ_CODIGO_PADRE = "1153181";
    private static final String TIPO = "C";
    private static final Integer VALOR_BYTE = 1024;
    private static final String RADICACION_SIN_DOCUMENTOS = "Radicado sin documentos";
    private static final String RADICADO_CONSTRUIDO = "RadicadoDTO construido";
    private static final String TAXONOMIA_ENCONTRADA = "Taxonomia Encontrada";
    private static final String TAXONOMIA_CREADA = "Taxonomia Creada";
    private static final String CONSTANTE_PESO_MAXIMO = "TOPEARCHIV";
    private static final String DOCUMENTOS_CARGADOS = "Documentos cargados ";
    private static final String DOCUMENTOS_NO_CARGADOS = "Documentos Sin cargar ";
    private static final String NIVEL_CARPETA = "NC";
    private static final String NIVEL_RADICADO = "NR";

    @Inject
    private IincapacidadService iincapacidadService;

    @Inject
    private IncapacidadAuditoria incapacidadAuditoria;

    private Integer tamanioMaximoPermitidoDocumentos;

    static Logger logger = Logger.getLogger(CrearRadicadoService.class.getName());

    public RespuestaGenerica<DocumentoAlmacenado> crearRadicadoGenial(RadicadoGenialDTO radicadoGenialDTO) {

        try {
            if (Objects.nonNull(radicadoGenialDTO)) {
                //0. Se valida el tamanio de los docuementos antes de crear el radicado
                if (!radicadoGenialDTO.getDocumentosACargar().isEmpty()) {
                    if (!this.validarTamanio(radicadoGenialDTO.getDocumentosACargar())) {
                        return new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_ARC_FPO1_ERRN.getDescripcion(), INC_VAL_ARC_FPO1_ERRN, "");
                    }
                }

                //1. Nivel se buscar la taxonomia de la carpeta del empleado
                RespuestaGenerica<InformacionTaxonomia> validarTaxonomiaCarpetaEmpleado = validarOCrearTaxonomiaGenial(radicadoGenialDTO.getTipoDocumento(), radicadoGenialDTO.getNumeroDocumento(), DEA_CODIGO_PADRE, "");
                if (!validarTaxonomiaCarpetaEmpleado.getObjeto().isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, validarTaxonomiaCarpetaEmpleado.getMensaje());
                }

                //2. Se contruye el radicadoDTO dependiendo el nivel a crear.
                RespuestaGenerica<RadicadoDTO> radicadoDTOContruido = construirDTORadicado(radicadoGenialDTO.getTipoDocumento(),
                        radicadoGenialDTO.getNumeroRadicado(), radicadoGenialDTO.getNumeroContrato(), radicadoGenialDTO.getNumeroRadicado(),
                        validarTaxonomiaCarpetaEmpleado.getObjeto().getIdAzDigital(), validarTaxonomiaCarpetaEmpleado.getObjeto().getIdDeaCodigo(),
                        RadicadoDTO.NivelCreacion.NC, TIPO, new ArrayList<>(), "");

                //2.1 Se construye el objeto radicadoDTO.
                if (!TipoRespuesta.SUCCESS.equals(radicadoDTOContruido.getStatus())) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, validarTaxonomiaCarpetaEmpleado.getMensaje());
                }

                //3. Nivel se buscar la taxonomia de la carpeta contrato si no la tiene la crea.
                RespuestaGenerica<InformacionTaxonomia> creacionCarpetaContrato = validarTaxonomia(radicadoDTOContruido.getObjeto());
                if (!creacionCarpetaContrato.getObjeto().isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                            validarTaxonomiaCarpetaEmpleado.getMensaje());
                }

                //4. Insertar documentos segun el subtipo de incapacidad
                RespuestaGenerica<Boolean> documentosInsertados = insertarDocumentosBaseDeDatosGenial(radicadoGenialDTO.getNumeroRadicado(), radicadoGenialDTO.getSubtipoIncapacidad());
                if (!documentosInsertados.getObjeto()) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, documentosInsertados.getMensaje());
                }

                //5. Nivel se buscar la taxonomia de la carpeta radicado si no la tiene la crea.
                RespuestaGenerica<RadicadoDTO> radicadoDTOContruidoRadicado = construirDTORadicado(radicadoGenialDTO.getTipoDocumento(),
                        radicadoGenialDTO.getNumeroRadicado(), radicadoGenialDTO.getNumeroContrato(), radicadoGenialDTO.getNumeroRadicado(),
                        creacionCarpetaContrato.getObjeto().getIdAzDigital(), creacionCarpetaContrato.getObjeto().getIdDeaCodigo(),
                        RadicadoDTO.NivelCreacion.NR, TIPO, new ArrayList<>(), "");

                //6. valida la creacion del radicadoDTO
                if (!TipoRespuesta.SUCCESS.equals(radicadoDTOContruidoRadicado.getStatus())) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, validarTaxonomiaCarpetaEmpleado.getMensaje());
                }

                //7. valida la taxonomia si no la tiene la crea para el radicado.
                RespuestaGenerica<InformacionTaxonomia> creacionCarpetaRadicado = validarTaxonomia(radicadoDTOContruidoRadicado.getObjeto());
                if (!creacionCarpetaContrato.getObjeto().isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, validarTaxonomiaCarpetaEmpleado.getMensaje());
                }

                RespuestaGenerica<RadicadoDTO> radicadoDTOContruidoDocumentos = construirDTORadicado(radicadoGenialDTO.getTipoDocumento(),
                        radicadoGenialDTO.getNumeroRadicado(), radicadoGenialDTO.getNumeroContrato(), radicadoGenialDTO.getNumeroRadicado(),
                        creacionCarpetaRadicado.getObjeto().getIdAzDigital(), creacionCarpetaRadicado.getObjeto().getIdDeaCodigo(),
                        RadicadoDTO.NivelCreacion.NR, TIPO_DOCUMENTO, radicadoGenialDTO.getDocumentosACargar(), "");

                if (!TipoRespuesta.SUCCESS.equals(radicadoDTOContruidoDocumentos.getStatus())) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, validarTaxonomiaCarpetaEmpleado.getMensaje());
                }

                RespuestaGenerica<DocumentoAlmacenado> documentosCargados = construirTaxonomiaParaDocumento(radicadoDTOContruidoDocumentos.getObjeto(), radicadoGenialDTO.getNumeroRadicado());

                if (!TipoRespuesta.SUCCESS.equals(documentosCargados.getStatus())) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, documentosCargados.getMensaje());
                }
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, radicadoGenialDTO.getNumeroRadicado().toString(), Objects.isNull(documentosCargados.getResultadoSubidaDocumentos()) ? RADICACION_SIN_DOCUMENTOS
                        : documentosCargados.getResultadoSubidaDocumentos(), Objects.isNull(documentosCargados.getListaResultados()) ? new ArrayList<>()
                        : documentosCargados.getListaResultados(), "");
            }
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_CAM_RAD.getDescripcion(), INC_VAL_CAM_RAD, "");
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, String.format("INC_VAL_GEN%s : crearRadicadoGenial", e.getMessage()));
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_GEN.getDescripcion() + e.getMessage(), INC_VAL_GEN, "");
        }

    }

    private RespuestaGenerica<Boolean> insertarDocumentosBaseDeDatosGenial(Integer numeroRadicado, Integer subtipoIncapacidad) {
        try {
            return iincapacidadService.insertarDocumentosGenial(numeroRadicado, subtipoIncapacidad);
        } catch (Exception e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_ACT_O_CRE_DOC + e.getMessage());
        }
    }

    private RespuestaGenerica<RadicadoDTO> construirDTORadicado(String tipoDocumento, Integer numeroDocumento, Integer numeroContrato, Integer numeroRadicado, String azCodigo, String deaCodigo, RadicadoDTO.NivelCreacion nivelCreacion, String tipo, List<Documento> documentos, String idTransaccion) {
        try {
            RadicadoDTO radicadoDTO;
            if (!documentos.isEmpty()) {
                radicadoDTO = new RadicadoDTO(tipoDocumento, numeroDocumento, numeroContrato, azCodigo, deaCodigo, tipo, documentos, idTransaccion);
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, RADICADO_CONSTRUIDO, radicadoDTO.getIdTransaccionRadicado());
            }

            if (NIVEL_CARPETA.equals(nivelCreacion.name())) {
                radicadoDTO = new RadicadoDTO(tipoDocumento, numeroDocumento, numeroContrato, azCodigo, deaCodigo, tipo, documentos, idTransaccion);
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, RADICADO_CONSTRUIDO, radicadoDTO.getIdTransaccionRadicado());
            }
            if (NIVEL_RADICADO.equals(nivelCreacion.name())) {
                radicadoDTO = new RadicadoDTO(tipoDocumento, numeroDocumento, numeroRadicado, azCodigo, deaCodigo, tipo, documentos, idTransaccion);
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, RADICADO_CONSTRUIDO, radicadoDTO.getIdTransaccionRadicado());
            }

            return new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_CRE_O_COS_RAD.getDescripcion(), INC_VAL_CRE_O_COS_RAD, idTransaccion);

        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("INC_VAL_GEN%s : construirDTORadicado", e.getMessage()));
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_GEN.getDescripcion() + e.getMessage(), INC_VAL_GEN, "");
        }

    }

    public RespuestaGenerica<InformacionTaxonomia> validarOCrearTaxonomiaGenial(String tipoDocumento, Integer numeroDocumento, long deaCodigo, String idTransaccion) {
        Taxonomia taxonomia;
        InformacionTaxonomia informacionTaxonomia;
        try {
            informacionTaxonomia = iincapacidadService.buscarTaxonomiaGenial(tipoDocumento, numeroDocumento, deaCodigo);
            if (informacionTaxonomia.isEstado()) {
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, TAXONOMIA_ENCONTRADA, informacionTaxonomia, idTransaccion);
            }
            taxonomia = new Taxonomia("C", AZ_CODIGO_PADRE, //ubicacion de la carpeta en AZ-Digital
                    tipoDocumento + " " + numeroDocumento, String.valueOf(numeroDocumento), FLUJO, String.valueOf(DEA_CODIGO_PADRE)); //Ubicacion de la carpeta en base de datos
            return crearTaxonomia(taxonomia);

        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("INC_VAL_GEN%s : validarOCrearTaxonomiaGenial", e.getMessage()));
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_GEN.getDescripcion() + e.getMessage(), INC_VAL_GEN, idTransaccion);
        }
    }

    // INC_VAL_ARC_FPO1_ERRN
    public RespuestaGenerica<DocumentoAlmacenado> crearRadicadoSitioTrabajador(RadicadoDTO radicadoDTO) {
        try {
            if (Objects.nonNull(radicadoDTO)) {

                //0. Se valida el tamanio de los documentos antes de crear el radicado
                if (!radicadoDTO.getDocumentosACargar().isEmpty() && (!this.validarTamanio(radicadoDTO.getDocumentosACargar()))) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, CodigoError.INC_VAL_ARC_FPO1_ERRN, CodigoError.INC_VAL_ARC_FPO1_ERRN.getDescripcion(), radicadoDTO.getIdTransaccionRadicado());

                }

                //1. Se valida la taxonomia de la creacion de la carpeta del contrato activo
                RespuestaGenerica<InformacionTaxonomia> creacionCarpetaContrato = validarTaxonomia(radicadoDTO);
                if (!creacionCarpetaContrato.getObjeto().isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, creacionCarpetaContrato.getMensaje(), radicadoDTO.getIdTransaccionRadicado());
                }

                //2. Se crea el radicado en base de datos
                RespuestaGenerica<Integer> crearRadicado = crearRadicadoEnBaseDeDatos(radicadoDTO);
                if (!TipoRespuesta.SUCCESS.equals(crearRadicado.getStatus())) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, crearRadicado.getMensaje(), radicadoDTO.getIdTransaccionRadicado());
                }

                // 3. Se valida la taxonomia de la creacion de la carpeta del radicado
                RespuestaGenerica<InformacionTaxonomia> crearCarpetaRadicado = construirTaxonomiaParaCarpeta(
                        new RadicadoDTO(crearRadicado.getValorRetorno(), creacionCarpetaContrato.getObjeto().getIdAzDigital(),
                                creacionCarpetaContrato.getObjeto().getIdDeaCodigo(), radicadoDTO.getNumeroDocumentoEmpleado(),
                                radicadoDTO.getTipoACargar(), RadicadoDTO.NivelCreacion.NR, radicadoDTO.getIdTransaccionRadicado()));
                if (!TipoRespuesta.SUCCESS.equals(crearCarpetaRadicado.getStatus())) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, crearCarpetaRadicado.getMensaje(), radicadoDTO.getIdTransaccionRadicado(), crearCarpetaRadicado.getException());

                }

                // 4. Se construye la taxonomia para los documentos a cargar.
                RespuestaGenerica<DocumentoAlmacenado> cargarDocumentos = construirTaxonomiaParaDocumento(
                        new RadicadoDTO(crearCarpetaRadicado.getObjeto().getIdDeaCodigo(), radicadoDTO.getNumeroDocumentoEmpleado(),
                                TIPO_DOCUMENTO, radicadoDTO.getDocumentosACargar(), radicadoDTO.getIdTransaccionRadicado()), crearRadicado.getValorRetorno());
                if (!TipoRespuesta.SUCCESS.equals(cargarDocumentos.getStatus())) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, cargarDocumentos.getMensaje());
                }

                //5. Finaliza el flujo de creacion del radicado.
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, crearRadicado.getValorRetorno().toString(),
                        Objects.isNull(cargarDocumentos.getResultadoSubidaDocumentos())
                        ? RADICACION_SIN_DOCUMENTOS : cargarDocumentos.getResultadoSubidaDocumentos(),
                        Objects.isNull(cargarDocumentos.getListaResultados())
                        ? new ArrayList<>() : cargarDocumentos.getListaResultados(), radicadoDTO.getIdTransaccionRadicado());
            }

            // 6. Se controla el error.
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_CAM_RAD, INC_VAL_CAM_RAD.getDescripcion(), radicadoDTO.getIdTransaccionRadicado());

        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, String.format("INC_VAL_GEN%s : crearRadicadoSitioTrabajador", e.getMessage()));
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_GEN, INC_VAL_GEN.getDescripcion() + e.getMessage(), radicadoDTO.getIdTransaccionRadicado());
        }
    }

    public RespuestaGenerica<DocumentoAlmacenado> cargarDocumento(RadicadoDTO radicadoDTO) {

        if (Objects.nonNull(radicadoDTO.getNumeroRadicado())) {
            RespuestaGenerica<InformacionTaxonomia> taxonomiaEncontrada = validarTaxonomia(radicadoDTO);
            if (!TipoRespuesta.SUCCESS.equals(taxonomiaEncontrada.getStatus())) {
                return new RespuestaGenerica<>(TipoRespuesta.ERROR, taxonomiaEncontrada.getMensaje());
            }
            return construirTaxonomiaParaDocumento(new RadicadoDTO(taxonomiaEncontrada.getObjeto().getIdAzDigital(),
                    taxonomiaEncontrada.getObjeto().getIdDeaCodigo(), radicadoDTO.getDocumentosACargar(), radicadoDTO.getTipoACargar(),
                    radicadoDTO.getIdTransaccionRadicado()), radicadoDTO.getNumeroRadicado());
        }
        return new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_CAM, INC_VAL_CAM.getDescripcion(), radicadoDTO.getIdTransaccionRadicado());
    }

    private RespuestaGenerica<InformacionTaxonomia> validarTaxonomia(RadicadoDTO radicadoDTO) {
        InformacionTaxonomia informacionTaxonomia = new InformacionTaxonomia();
        try {
            if (Objects.nonNull(radicadoDTO.getDeaCodigo()) && (Objects.nonNull(radicadoDTO.getContrato()))) {

                informacionTaxonomia = iincapacidadService.obtenerInformacionTaxonomia(radicadoDTO.getDeaCodigo(), obtenerNombreCarpeta(radicadoDTO));

                if (informacionTaxonomia.isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, TAXONOMIA_ENCONTRADA, informacionTaxonomia, radicadoDTO.getIdTransaccionRadicado());
                }
                return construirTaxonomiaParaCarpeta(radicadoDTO);
            }
            if (Objects.nonNull(radicadoDTO.getNumeroRadicado())) {
                informacionTaxonomia = iincapacidadService.buscarTaxomiaRadicado(radicadoDTO.getNumeroRadicado(), TIPO_FLUJO).getObjeto();
                if (informacionTaxonomia.isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, TAXONOMIA_ENCONTRADA, informacionTaxonomia, radicadoDTO.getIdTransaccionRadicado());
                }
                return new RespuestaGenerica<>(TipoRespuesta.WARNING, INC_VAL_CRE_CAR_DOC.getDescripcion(), informacionTaxonomia, radicadoDTO.getIdTransaccionRadicado());
            }
            return new RespuestaGenerica<>(TipoRespuesta.WARNING,  INC_VAL_CAM.getDescripcion(),informacionTaxonomia
                   ,radicadoDTO.getIdTransaccionRadicado());
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, String.format("INC_VAL_TAX%s : validarTaxonomia", e.getMessage()));
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_TAX, INC_VAL_TAX.getDescripcion() + e.getMessage(), radicadoDTO.getIdTransaccionRadicado());
        }
    }

    private RespuestaGenerica<InformacionTaxonomia> construirTaxonomiaParaCarpeta(RadicadoDTO radicadoDTO) {
        Taxonomia taxonomia;
        try {
            taxonomia = new Taxonomia(radicadoDTO.getTipoACargar(), radicadoDTO.getAzCodigo(), //ubicacion de la carpeta en AZ-Digital
                    nivelDeCreacion(radicadoDTO), String.valueOf(radicadoDTO.getNumeroDocumentoEmpleado()), FLUJO, radicadoDTO.getDeaCodigo()); //Ubicacion de la carpeta en base de datos
            return crearTaxonomia(taxonomia);
        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("INV_VAL_CRE_TAX%s : construirTaxonomiaParaCarpeta", e.getMessage()));
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, INV_VAL_CRE_TAX + e.getMessage());

        }
    }

    private RespuestaGenerica<DocumentoAlmacenado> construirTaxonomiaParaDocumento(RadicadoDTO radicadoDTO, Integer numeroRadicado) {
        List<DocumentoAlmacenado> documentoProcesadosEnAz = new ArrayList<>();
        try {
            radicadoDTO.getDocumentosACargar().stream().map(documento -> {
                Taxonomia taxonomiaDocumento = new Taxonomia(documento.getNombreDocumento() + EXT_DOCUMENTO, radicadoDTO.getTipoACargar(), String.valueOf(radicadoDTO.getNumeroDocumentoEmpleado()), FLUJO, radicadoDTO.getDeaCodigo(), Integer.parseInt(documento.getIdDocumento()), documento.getBase64());
                RespuestaGenerica<InformacionTaxonomia> respuesSubidaDeDocumento = crearTaxonomia(taxonomiaDocumento);
                if (TipoRespuesta.SUCCESS.equals(respuesSubidaDeDocumento.getStatus())) {
                    documentoProcesadosEnAz.add(validarProcesoDeSubida(Boolean.TRUE, documento.getIdDocumento(), documento.getNombreDocumento(), String.valueOf(numeroRadicado)));

                    actulizarDocumentoCargado(Integer.valueOf(respuesSubidaDeDocumento.getObjeto().getIdDeaCodigo()), numeroRadicado, Integer.valueOf(documento.getIdDocumento()));
                } else {
                    documentoProcesadosEnAz.add(validarProcesoDeSubida(Boolean.FALSE, documento.getIdDocumento(), documento.getNombreDocumento(), String.valueOf(numeroRadicado)));
                }
                return documentoProcesadosEnAz;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("INC_VAL_SUB_DOC%s : construirTaxonomiaParaDocumento", e.getMessage()));
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_SUB_DOC.getDescripcion() + e, INC_VAL_SUB_DOC, radicadoDTO.getIdTransaccionRadicado());
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
        documentosSubidos = documentoProcesados.stream().filter(DocumentoAlmacenado::getEstadoDelProceso).count();
        String resultadoDelProceso = DOCUMENTOS_CARGADOS + documentosSubidos + " - " + DOCUMENTOS_NO_CARGADOS + documentoFallidos;
        return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "OK", resultadoDelProceso, documentoProcesados, "");
    }

    private static RespuestaGenerica<InformacionTaxonomia> crearTaxonomia(Taxonomia taxonomia) {
        try {
            RespuestaGenerica<InformacionTaxonomia> respuestaGenerica = crearTaxonomiaIncapacidades(taxonomia);
            if (TipoRespuesta.SUCCESS.equals(respuestaGenerica.getStatus())) {
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, TAXONOMIA_CREADA, respuestaGenerica.getObjeto(), "");
            }
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, respuestaGenerica.getMensaje(), respuestaGenerica.getException());
        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("INV_VAL_CRE_TAX%s : crearTaxonomia", e.getMessage()));
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, INV_VAL_CRE_TAX + e.getMessage());
        }
    }

    private RespuestaGenerica<Integer> crearRadicadoEnBaseDeDatos(RadicadoDTO radicadoDTO) {
        RespuestaGenerica<Integer> radicadoCreado = iincapacidadService.crearRadicado(radicadoDTO);
        if (!TipoRespuesta.SUCCESS.equals(radicadoCreado.getStatus())) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, radicadoCreado.getMensaje());
        }
        return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Radicacion Creada", radicadoCreado.getValorRetorno(), radicadoDTO.getIdTransaccionRadicado());
    }

    private String obtenerNombreCarpeta(RadicadoDTO radicadoDTO) {
        return radicadoDTO.getNumeroRadicado() != null ? String.valueOf(radicadoDTO.getNumeroRadicado()) : String.valueOf(radicadoDTO.getContrato());
    }

    private String nivelDeCreacion(RadicadoDTO radicadoDTO) {
        return RadicadoDTO.NivelCreacion.NR.equals(radicadoDTO.getNivel()) ? String.valueOf(radicadoDTO.getNumeroRadicado()) : String.valueOf(radicadoDTO.getContrato());
    }

    private void actulizarDocumentoCargado(Integer deaCodigo, Integer numeroRadico, Integer codigoDocumento) {
        RespuestaGenerica<Boolean> documentoActualizado = iincapacidadService.actualizarEstadoDocumento(deaCodigo, numeroRadico, codigoDocumento);
    }

    public boolean validarTamanio(List<Documento> documentosParaValidar) {
        tamanioMaximoPermitidoDocumentos = iincapacidadService.constanteTamanioPermitido(CONSTANTE_PESO_MAXIMO);
        return documentosParaValidar.stream().allMatch(documento -> calcularTamanio(documento.getBase64()) <= this.tamanioMaximoPermitidoDocumentos);
    }

    private static long calcularTamanio(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);
        return bytes.length / VALOR_BYTE;
    }
}
