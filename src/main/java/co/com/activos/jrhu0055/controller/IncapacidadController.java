package co.com.activos.jrhu0055.controller;

import co.com.activos.jrhu0055.DTO.*;
import co.com.activos.jrhu0055.Services.IncapacidadAuditoria;
import co.com.activos.jrhu0055.Services.impl.ConcatenarPDF;
import co.com.activos.jrhu0055.Services.impl.CrearRadicadoService;
import co.com.activos.jrhu0055.Services.impl.IncapacidadService;
import co.com.activos.jrhu0055.model.*;
import co.com.activos.jrhu0055.utiliti.ErrorAplicacion;
import co.com.activos.jrhu0055.utiliti.RespuestaGenerica;
import co.com.activos.jrhu0055.utiliti.TipoRespuesta;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static co.com.activos.jrhu0055.utiliti.CodigoError.INC_VAL_GEN;
import static co.com.activos.jrhu0055.utiliti.ErrorNegocio.Tipo.PARAMETRO_VACIO;
import static co.com.activos.jrhu0055.utiliti.StringUtils.isNullOrEmpty;
import java.util.Objects;

@Path("/")
public class IncapacidadController {

    private static final String NOMBRE_APLICATIVO = "JRHU0055";

    @Inject
    private IncapacidadService service;

    @Inject
    private CrearRadicadoService crear;

    @Inject
    private ConcatenarPDF concatenarPDF;

    @Inject
    private IncapacidadAuditoria incapacidadAuditoria;

    @POST
    @Path("/listarIncapacidades")
    public Response listarIncapacidades(ContratoDTO contratoDTO) {
        List<Incapacidad> listarIncapacidades
                = service.listaIncapacidades(contratoDTO).stream()
                        .sorted(Comparator.comparing(Incapacidad::getEstado))
                        .collect(Collectors.toList());
        GenericEntity<List<Incapacidad>> incapacidades = new GenericEntity<List<Incapacidad>>(listarIncapacidades) {
        };
        return Response
                .ok(incapacidades)
                .status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/listarTiposIncapacidad")
    public Response listarTiposIncapacidad() {
        try {
            List<TipoIncapacidad> tipoIncapacidades
                    = service.listaTipoIncapacidades()
                            .stream()
                            .sorted(Comparator.comparing(TipoIncapacidad::getCodigoTipoIncapacidad))
                            .collect(Collectors.toList());
            GenericEntity<List<TipoIncapacidad>> incapacidades = new GenericEntity<List<TipoIncapacidad>>(tipoIncapacidades) {
            };
            return Response
                    .ok(incapacidades)
                    .status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();

        } catch (Throwable throwable) {
            return Response
                    .status(400)
                    .entity(throwable)
                    .build();

        }
    }

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/listarSubTiposIncapacidad/{idTipoIncapacidad}")
    public Response listarSubtipoIncapacidad(@PathParam("idTipoIncapacidad") Integer idTipoIncapacidad) {
        try {
            List<SubTipoIncapacidad> subTipoIncapacidades
                    = service.listaSubTipoIncapacidades(idTipoIncapacidad)
                            .stream()
                            .sorted(Comparator.comparing(SubTipoIncapacidad::getCodigoSubTipoIncapacidad))
                            .collect(Collectors.toList());
            GenericEntity<List<SubTipoIncapacidad>> subTipoIncapacidad
                    = new GenericEntity<List<SubTipoIncapacidad>>(subTipoIncapacidades) {
            };
            return Response.ok()
                    .entity(subTipoIncapacidad)
                    .status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
        } catch (Throwable throwable) {
            return Response
                    .status(400)
                    .entity(throwable)
                    .build();
        }

    }

    @POST
    @Path("/ListarContratos/")
    public Response ListarContratos(ContratoDTO contratoDTO) {
        try {

            List<Contrato> listarContratos
                    = service.listaContratos(contratoDTO)
                            .stream()
                            .sorted(Comparator.comparing(Contrato::getNumeroContrato).reversed())
                            .collect(Collectors.toList());
            GenericEntity<List<Contrato>> contratos
                    = new GenericEntity<List<Contrato>>(listarContratos) {
            };
            return Response.ok().entity(contratos).build();
        } catch (Throwable throwable) {
            return Response
                    .status(400)
                    .entity(throwable)
                    .build();
        }
    }

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/enfermedades/")
    public Response listarEnfermedades() {
        try {
            List<Enfermedad> enfermedades
                    = new ArrayList<>(service.listarEnfermedades().getListaResultados());
            GenericEntity<List<Enfermedad>> documentos = new GenericEntity<List<Enfermedad>>(enfermedades) {
            };
            return Response.ok()
                    .status(Response.Status.OK)
                    .entity(documentos)
                    .build();
        } catch (Exception e) {
            return Response.status(400)
                    .entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error al obtener la lista de enfermedades debido a" + e))
                    .build();
        }
    }

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/documentos/{idSubtipoIncapacidad}")
    public Response listarDocumentos(@PathParam("idSubtipoIncapacidad") Integer idSubtipoIncapacidad) {
        try {
            if (isNullOrEmpty(String.valueOf(idSubtipoIncapacidad))) {
                throw new ErrorAplicacion(PARAMETRO_VACIO.getMensaje(),
                        "400");
            }

            List<DocumentoPorSubtipoIncapacidad> documentoPorSubtipoIncapacidads
                    = service.listarDocumentos(idSubtipoIncapacidad)
                            .stream()
                            .sorted(Comparator.comparing(DocumentoPorSubtipoIncapacidad::getRequerido))
                            .collect(Collectors.toList());
            GenericEntity<List<DocumentoPorSubtipoIncapacidad>> documentos
                    = new GenericEntity<List<DocumentoPorSubtipoIncapacidad>>(documentoPorSubtipoIncapacidads) {
            };
            return Response.ok()
                    .entity(documentos)
                    .status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
        } catch (Throwable throwable) {
            return Response
                    .status(400)
                    .entity(throwable)
                    .build();
        }
    }

    @GET
    @Path("/terminos/")
    public Response terminosYCondiciones() {
        try {
            TerminosYCondiciones terminosYCondiciones
                    = service.obtenerTerminosYCondiciones().getObjeto();
            return Response.ok()
                    .entity(terminosYCondiciones)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            return Response.status(400).entity(new RespuestaGenerica<>(
                    TipoRespuesta.ERROR, "Error No controlado " + e)).build();
        }
    }

    @POST
    @Path("/radicado/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response crearRadicado(RadicadoDTO radicadoDTO) {
        RespuestaGenerica<DocumentoAlmacenado> respuestaDelProceso = crear.crearRadicadoSitioTrabajador(radicadoDTO);
        try {
            if (TipoRespuesta.SUCCESS.equals(respuestaDelProceso.getStatus())) {
                return Response.ok()
                        .type(MediaType.APPLICATION_JSON)
                        .entity(respuestaDelProceso)
                        .build();
            }
            incapacidadAuditoria.guardarAuditoria(respuestaDelProceso.getMensaje() + respuestaDelProceso.getException(), TipoRespuesta.ERROR, NOMBRE_APLICATIVO, respuestaDelProceso.getIdTransaccionRadicado(), "400");
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    respuestaDelProceso.getMensaje(), respuestaDelProceso.getIdTransaccionRadicado()
            )).build();
        } catch (Exception e) {
            incapacidadAuditoria.guardarAuditoria(e.getCause().getMessage(), TipoRespuesta.ERROR, NOMBRE_APLICATIVO, respuestaDelProceso.getIdTransaccionRadicado(), "400");
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, INC_VAL_GEN.getDescripcion(), INC_VAL_GEN, e, respuestaDelProceso.getIdTransaccionRadicado())).build();
        }
    }

    @POST
    @Path("/validaciones/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response validarRadicado(ValidacionRadicadoDTO validacionRadicadoDTO) {
        try {
            RespuestaGenerica<String> validacion = service.validacionesRadicado(validacionRadicadoDTO);
            return Response.ok()
                    .type(MediaType.APPLICATION_JSON)
                    .entity(validacion)
                    .build();
        } catch (Exception e) {
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado: " + e)).build();
        }
    }

    @GET
    @Path("/listarDocumentosPorRadicado/{numeroRadicado}/{ipUsuario}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarDocumentosPorRadicado(@PathParam("numeroRadicado") Integer numeroRadicado, @PathParam("ipUsuario") String ipUsuario) {
        try {
            List<DocumentoCargado> documentosCargados
                    = service.documentosCargadosPorRadicado(ipUsuario, numeroRadicado)
                            .getListaResultados()
                            .stream()
                            .sorted(Comparator.comparing(DocumentoCargado::getDocumentoRequerido))
                            .collect(Collectors.toList());
            GenericEntity<List<DocumentoCargado>> documentos
                    = new GenericEntity<List<DocumentoCargado>>(documentosCargados) {
            };
            return Response.ok()
                    .entity(documentos)
                    .status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
        } catch (Exception e) {
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado: " + e)).build();
        }

    }

    @POST
    @Path("/actulizarDocumentos")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response documentoActualizado(DocumentoActualizadoDTO documentoActualizadoDTO) {
        try {
            RespuestaGenerica documentoActual = service.documentoActualizado(documentoActualizadoDTO);
            return Response.ok()
                    .status(Response.Status.OK)
                    .entity(documentoActual)
                    .build();
        } catch (Exception e) {
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado: " + e)).build();
        }
    }

    @POST
    @Path("/cargarArchivo")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response archivoCargado(RadicadoDTO radicadoDTO) {
        try {
            RespuestaGenerica<DocumentoAlmacenado> documentoCargado = crear.cargarDocumento(radicadoDTO);
            if (!TipoRespuesta.ERROR.equals(documentoCargado.getStatus())) {
                actualizarEstadoRadicacion(radicadoDTO);
                return Response.ok()
                        .type(MediaType.APPLICATION_JSON)
                        .entity(documentoCargado)
                        .build();
            }
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, documentoCargado.getMensaje())).build();

        } catch (Exception e) {
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado: " + e)).build();
        }

    }

    @PUT
    @Path("/actualizarEstado/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response actualizarEstadoRadicacion(RadicadoDTO radicadoDTO) {
        //try {
        if (Objects.isNull(radicadoDTO.getNumeroRadicado())) {
            throw new ErrorAplicacion(PARAMETRO_VACIO.getMensaje(),
                    "400");
        }
        RespuestaGenerica<String> estadoDelProceso
                = service.radicadoActualizado(radicadoDTO);
        if (!TipoRespuesta.SUCCESS.equals(estadoDelProceso.getStatus())) {
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, estadoDelProceso.getMensaje())).build();
        }
        return Response.ok()
                .type(MediaType.APPLICATION_JSON)
                .entity(estadoDelProceso)
                .build();
        //} catch (Exception e) {
        //  return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado", e)).build();
        //}
    }

    @GET
    @Path("/listarIncapacidades")
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarIncapacidadesMesa() {
        try {
            List<Incapacidad> incapacidadList
                    = service.listarIncapacidades()
                            .getListaResultados();
//                    .stream()
//                    .sorted(Comparator.comparing(Incapacidad::getFechaInicial))
//                    .collect(Collectors.toList());
            GenericEntity<List<Incapacidad>> incapacidad
                    = new GenericEntity<List<Incapacidad>>(incapacidadList) {
            };
            return Response.ok()
                    .entity(incapacidad)
                    .status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
        } catch (Exception e) {
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado: " + e)).build();
        }

    }

    @POST
    @Path("/crearGers")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response crearGers(ContratoDTO contratoDTO) {
        try {
            RespuestaGenerica<Integer> gersCreado = service.crearGers(contratoDTO);
            if (!TipoRespuesta.SUCCESS.equals(gersCreado.getStatus())) {
                return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, gersCreado.getMensaje())).build();
            }
            return Response.ok()
                    .type(MediaType.APPLICATION_JSON)
                    .entity(gersCreado)
                    .build();
        } catch (Exception e) {
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado: " + e)).build();
        }
    }

    @POST
    @Path("/concatenarDocumentos")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response concatenarDocumentos(List<String> listaDeDocumentosAUnir) throws IOException {
        RespuestaGenerica<?> documentoConcatenado = concatenarPDF.documentosAConcatenar(listaDeDocumentosAUnir);
        if ("ERROR".equals(documentoConcatenado.getStatus())) {
            return Response.status(400)
                    .status(Response.Status.NOT_FOUND)
                    .entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, documentoConcatenado.getMensaje()))
                    .build();
        }
        return Response.ok()
                .type(MediaType.APPLICATION_JSON)
                .entity(documentoConcatenado)
                .build();
    }

    @GET
    @Path("/listarEstadosObservaciones")
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarEstadoObservaciones() {
        try {
            RespuestaGenerica<EstadoObservacion> listaDeEstados = service.listarEstadoObservacion();
            if ("ERROR".equals(listaDeEstados.getStatus())) {
                return Response.status(400)
                        .status(Response.Status.NOT_FOUND)
                        .entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, listaDeEstados.getMensaje()))
                        .build();
            }
            ;
            List<EstadoObservacion> estados
                    = listaDeEstados
                            .getListaResultados()
                            .stream()
                            .sorted(Comparator.comparing(EstadoObservacion::getEstado))
                            .collect(Collectors.toList());
            GenericEntity<List<EstadoObservacion>> incapacidad
                    = new GenericEntity<List<EstadoObservacion>>(estados) {
            };
            return Response.ok()
                    .entity(incapacidad)
                    .status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();

        } catch (Exception e) {
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado: " + e)).build();
        }

    }

    @GET
    @Path("/listarObservacionPorRadicado/{numeroRadicado}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response listarObservacionPorRadicado(@PathParam("numeroRadicado") Integer numeroRadicado) {
        try {
            RespuestaGenerica<ObservacionRadicado> listaDeEstados = service.listarObservacionesPorRadicado(numeroRadicado);
            if ("ERROR".equals(listaDeEstados.getStatus())) {
                return Response.status(400)
                        .status(Response.Status.NOT_FOUND)
                        .entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, listaDeEstados.getMensaje()))
                        .build();
            }
            ;
            List<ObservacionRadicado> observacionRadicado
                    = listaDeEstados
                            .getListaResultados()
                            .stream()
                            .sorted(Comparator.comparing(ObservacionRadicado::getFecha))
                            .collect(Collectors.toList());
            GenericEntity<List<ObservacionRadicado>> incapacidad
                    = new GenericEntity<List<ObservacionRadicado>>(observacionRadicado) {
            };
            return Response.ok()
                    .entity(incapacidad)
                    .status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();

        } catch (Exception e) {
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado: " + e)).build();
        }
    }

//    @POST
//    @Path("/radicarIncapacidadGenial/{tipoDocumento}/{numeroDocumento}/{numeroContrato}/{numeroRadicado}")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response crearRadicadoGenial(@PathParam("tipoDocumento") String tipoDocumento, @PathParam("numeroDocumento") int numeroDocumento,
//            @PathParam("numeroContrato") String numeroContrato, @PathParam("numeroRadicado") String numeroRadicado) {
//        try {
//            RespuestaGenerica<InformacionTaxonomia> respuestaDelProceso = crearParaGenial.validarTaxonomiaGenial(tipoDocumento, numeroDocumento, numeroContrato, numeroRadicado);//
//            if (TipoRespuesta.SUCCESS.equals(respuestaDelProceso.getStatus())) {
//                return Response.ok()
//                        .type(MediaType.APPLICATION_JSON)
//                        .entity(respuestaDelProceso)
//                        .build();
//            }
//            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, respuestaDelProceso.getMensaje())).build();
//        } catch (Exception e) {
//            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado: " + e)).build();
//        }
//    }
    @POST
    @Path("/radicarIncapacidadGenial")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response crearRadicadoGenial(RadicadoGenialDTO radicadoGenialDTO) {
        try {
            RespuestaGenerica<DocumentoAlmacenado> respuestaDelProceso = crear.crearRadicadoGenial(radicadoGenialDTO);//
            if (TipoRespuesta.SUCCESS.equals(respuestaDelProceso.getStatus())) {
                return Response.ok()
                        .type(MediaType.APPLICATION_JSON)
                        .entity(respuestaDelProceso)
                        .build();
            }
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, respuestaDelProceso.getMensaje())).build();
        } catch (Exception exception) {
            return Response.status(400).entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado: " + exception)).build();

        }

    }

}
