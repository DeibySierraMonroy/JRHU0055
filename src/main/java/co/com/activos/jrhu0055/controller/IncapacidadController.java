package co.com.activos.jrhu0055.controller;

import co.com.activos.jrhu0055.DTO.ContratoDTO;
import co.com.activos.jrhu0055.DTO.RadicadoDTO;
import co.com.activos.jrhu0055.DTO.ValidacionRadicadoDTO;
import co.com.activos.jrhu0055.Services.impl.IncapacidadService;
import co.com.activos.jrhu0055.model.*;
import co.com.activos.jrhu0055.utiliti.RespuestaGenerica;
import co.com.activos.jrhu0055.utiliti.TipoRespuesta;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class IncapacidadController {

    @Inject
    private IncapacidadService service;

    @Inject
    private CrearRadicado crear;

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
                    .noContent()
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
                    .noContent()
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
                    .sorted(Comparator.comparing(Contrato::getFechaFinalizacionContrato).reversed())
                    .collect(Collectors.toList());
            GenericEntity<List<Contrato>> contratos
                    = new GenericEntity<List<Contrato>>(listarContratos) {
            };
            return Response.ok().entity(contratos).build();
        } catch (Throwable throwable) {
            return Response
                    .noContent()
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
            List<Enfermedad> enfermedades =
                    service.listarEnfermedades().getListaResultados()
                            .stream().limit(20)
                            .collect(Collectors.toList());
            GenericEntity<List<Enfermedad>> documentos = new GenericEntity<List<Enfermedad>>(enfermedades) {
            };
            return Response.ok()
                    .status(Response.Status.OK)
                    .entity(documentos)
                    .build();
        } catch (Exception e) {
            return Response.noContent()
                    .entity(new
                            RespuestaGenerica<>(TipoRespuesta.ERROR,"Error al obtener la lista de enfermedades debido a" + e))
                    .build();
        }
    }

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/documentos/{idSubtipoIncapacidad}")
    public Response listarDocumentos(@PathParam("idSubtipoIncapacidad") Integer idSubtipoIncapacidad) {
        try {
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
                    .noContent()
                    .entity(throwable)
                    .build();
        }
    }

    @GET
    @Path("/terminos/")
    public Response terminosYCondiciones(){
        try {
            TerminosYCondiciones terminosYCondiciones =
                    service.obtenerTerminosYCondiciones().getObjeto();
            return Response.ok()
                    .entity(terminosYCondiciones)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            return Response.noContent().entity(new RespuestaGenerica<>(
                    TipoRespuesta.ERROR, "Error No controlado", e)).build();
        }
    }

    @POST
    @Path("/radicado/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response crearRadicado(RadicadoDTO radicadoDTO) {
        try {
            RespuestaGenerica<DocumentoAlmacenado> respuestaDelProceso = crear.crearRadicado(radicadoDTO);
            if (!TipoRespuesta.ERROR.equals(respuestaDelProceso.getStatus())) {
                return Response.ok()
                        .type(MediaType.APPLICATION_JSON)
                        .entity(respuestaDelProceso)
                        .build();
            }
            return Response.noContent().entity
                    (new RespuestaGenerica<>(TipoRespuesta.ERROR, respuestaDelProceso.getMensaje())).build();
        } catch (Exception e) {
            return Response.noContent().entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado", e)).build();
        }
    }

    @POST
    @Path("/validaciones/")
    public Response validarRadicado(ValidacionRadicadoDTO validacionRadicadoDTO){
        try{
            RespuestaGenerica<String> validacion = service.validacionesRadicado(validacionRadicadoDTO);
            return Response.ok()
                    .type(MediaType.APPLICATION_JSON)
                    .entity(validacion)
                    .build();
        }catch (Exception e){
            return Response.noContent().entity(new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error No controlado", e)).build();
        }
    }




}
