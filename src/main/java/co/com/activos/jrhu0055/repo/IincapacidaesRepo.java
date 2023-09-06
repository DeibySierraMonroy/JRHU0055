package co.com.activos.jrhu0055.repo;

import co.com.activos.jrhu0055.DTO.*;
import co.com.activos.jrhu0055.Services.IincapacidadService;
import co.com.activos.jrhu0055.model.*;
import co.com.activos.jrhu0055.utiliti.Conexion;
import co.com.activos.jrhu0055.utiliti.RespuestaGenerica;
import co.com.activos.jrhu0055.utiliti.TipoRespuesta;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class IincapacidaesRepo implements IincapacidadService {

    @Override
    public List<Incapacidad> incapacidades(ContratoDTO contratoDTO) {
        List<Incapacidad> listaIncapacidades = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "call RHU.QB_APLICATION_JRHU0055.PL_OBTENER_INCAPACIDADES_CPT(?,?,?,?,?,?,?)";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.setString("VCTDC_TD_EPL", contratoDTO.getTipoDocumentoEmpleado());
                callableStatement.setInt("NMEPL_ND", contratoDTO.getDocumentoEmpleado());
                callableStatement.setString("VCTDC_TD_PAL", contratoDTO.getTipoDocumentoEmpresa());
                callableStatement.setInt("NMEMP_ND_PAL", contratoDTO.getDocumentoEmpresa());
                callableStatement.registerOutParameter("RCINC", OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject("RCINC");
                while (resultSet.next()) {
                    Incapacidad incapacidad = new Incapacidad();
                    incapacidad.setNumeroRadicado(resultSet.getInt("RADICADO_INC"));
                    incapacidad.setNumeroDocumentoEmpleado(resultSet.getInt("NRO_DOC_TRABAJADOR"));
                    incapacidad.setNumeroContrato(resultSet.getInt("CONTRATO_TRABAJADOR"));
                    incapacidad.setNombreDelEmpleado(resultSet.getString("EMPLEADO"));
                    incapacidad.setNombreEmpresa(resultSet.getString("NOM_PPAL"));
                    incapacidad.setFechaInicial(resultSet.getString("INC_FECINI"));
                    incapacidad.setFechaRadicacion(resultSet.getString("INC_FECHA_CREACION"));
                    incapacidad.setSubTipoIncapacidad(resultSet.getString("SBT_NOMBRE"));
                    incapacidad.setSubTipoIncapacidadCodigo(resultSet.getInt("SBT_CODIGO"));
                    incapacidad.setNumeroDeDias(resultSet.getString("DIAS_INCAPACIDAD"));
                    incapacidad.setEstado(resultSet.getString("INC_ESTADO"));
                    incapacidad.setEstadoObservacion(resultSet.getString("ESTADO_OBSERVACION"));
                    incapacidad.setTipoDocumentoEmpleado(resultSet.getString("TIPO_DOC_TRABAJADOR"));
                    incapacidad.setTipoIncapacidad(resultSet.getString("INC_CONTIN"));
                    incapacidad.setIncapacidadDescripcion(resultSet.getString("INC_DESCRIPCION"));
                    incapacidad.setEstadoObservacionTrabajador(resultSet.getString("ESTADO_OBSERVACION_TRAB"));
                    incapacidad.setNombreEps(resultSet.getString("NOMBRE_EPS"));
                    incapacidad.setFechaFinal(resultSet.getString("FECHA_FINAL_INCAP"));
                    listaIncapacidades.add(incapacidad);
                }
                callableStatement.close();
                return listaIncapacidades;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TipoIncapacidad> listarTipoIncapacidades() {
        List<TipoIncapacidad> tipoIncapacidades = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "call RHU.QB_APLICATION_JRHU0055.PL_OBTENER_TIP_INCAPACIDAD(?,?,?)";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.registerOutParameter("RTIPINCAPACIDAD", OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject("RTIPINCAPACIDAD");
                if (Objects.nonNull(resultSet)) {
                    while (resultSet.next()) {
                        TipoIncapacidad tipoIncapacidad = new TipoIncapacidad();
                        tipoIncapacidad.setCodigoTipoIncapacidad(resultSet.getInt("TIP_CODIGO"));
                        tipoIncapacidad.setNombreTipoIncapacidad(resultSet.getString("TIP_NOMBRE"));
                        tipoIncapacidad.setEstado(resultSet.getString("TIP_ESTADO"));
                        tipoIncapacidades.add(tipoIncapacidad);
                    }
                }
                callableStatement.close();
                return tipoIncapacidades;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SubTipoIncapacidad> listarSubTipoIncapacidades(Integer codigoTipoIncapacidad) {
        List<SubTipoIncapacidad> subtipoIncapacidades = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "call RHU.QB_APLICATION_JRHU0055.PL_OBTENER_SUBTIP_INCAPACIDAD(?,?,?,?)";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.setInt("NTIPOINC", codigoTipoIncapacidad);
                callableStatement.registerOutParameter("RSUBINCAPACIDAD", OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject("RSUBINCAPACIDAD");
                if (Objects.nonNull(resultSet)) {
                    while (resultSet.next()) {
                        SubTipoIncapacidad subTipoIncapacidad = new SubTipoIncapacidad();
                        subTipoIncapacidad.setCodigoSubTipoIncapacidad(resultSet.getInt("SBT_CODIGO"));
                        subTipoIncapacidad.setNombreSubTipoIncapacidad(resultSet.getString("SBT_NOMBRE"));
                        subTipoIncapacidad.setEstado(resultSet.getString("SBT_ESTADO"));
                        subtipoIncapacidades.add(subTipoIncapacidad);
                    }
                }
                callableStatement.close();
                return subtipoIncapacidades;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contrato> listarContratos(ContratoDTO contratoDTO) {
        List<Contrato> contratos = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "call RHU.QB_APLICATION_JRHU0055.PL_OBTENER_CONTRATOS(?,?,?,?,?,?,?)";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.setString("VTDC_TD_EPL", contratoDTO.getTipoDocumentoEmpleado());
                callableStatement.setInt("NEPL_ND", contratoDTO.getDocumentoEmpleado());
                callableStatement.setInt("NEMP_ND", contratoDTO.getDocumentoEmpresa());
                callableStatement.setString("VTDC_TD", contratoDTO.getTipoDocumentoEmpresa());

                callableStatement.registerOutParameter("RCONTRATOS", OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject("RCONTRATOS");
                if (Objects.nonNull(resultSet)) {
                    while (resultSet.next()) {
                        Contrato contrato = new Contrato();
                        contrato.setNumeroContrato(resultSet.getInt("CTO_NUMERO"));
                        contrato.setFechaRetiro(resultSet.getString("CTO_FECRET"));
                        contrato.setTipoDocumentoEmppresaFilial(resultSet.getString("TDC_TD_FIL"));
                        contrato.setGetNumeroDocumentoEmpresaFilial(resultSet.getString("EMP_ND_FIL"));
                        contrato.setNombreEmpresaFilial(resultSet.getString("NOMBRE_EMPRESA_USUARIA"));
                        contrato.setTipoDocumentoEmpresaPrincipal(resultSet.getString("TDC_TD"));
                        contrato.setNumeroDocumentoEmpresaPrincipal(resultSet.getString("EMP_ND"));
                        contrato.setNombreEmpresaPrincipal(resultSet.getString("NOMBRE_EMPRESA_PRINCIPAL"));
                        contrato.setEstadoDelContrato(resultSet.getString("ESTADO_CONTRATO"));
                        contratos.add(contrato);
                    }
                }
                callableStatement.close();
                return contratos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DocumentoPorSubtipoIncapacidad> listarDocumentos(Integer codigoSubTipoIncapacidad) {
        List<DocumentoPorSubtipoIncapacidad> documentoPorSubtipoIncapacidades = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "call RHU.QB_APLICATION_JRHU0055.PL_OBTENER_DOCUM_SUBTIP(?,?,?,?)";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.setInt("NSUBTIPOINC", codigoSubTipoIncapacidad);
                callableStatement.registerOutParameter("RDOCUMENSUBTIPO", OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject("RDOCUMENSUBTIPO");
                if (Objects.nonNull(resultSet)) {
                    while (resultSet.next()) {
                        DocumentoPorSubtipoIncapacidad documentoPorSubtipoIncapacidad = new DocumentoPorSubtipoIncapacidad();
                        documentoPorSubtipoIncapacidad.setIdDocumento(resultSet.getInt("TPD_CODIGO"));
                        documentoPorSubtipoIncapacidad.setDescripcionDelDocumento(resultSet.getString("TPD_DESCRIPCION"));
                        documentoPorSubtipoIncapacidad.setRequerido(
                                documentoPorSubtipoIncapacidad.
                                        validarSiElDocumentoEsrequerido(resultSet.getString("SUD_REQUERIDO"))
                        );
                        documentoPorSubtipoIncapacidades.add(documentoPorSubtipoIncapacidad);
                    }
                }
                callableStatement.close();
                return documentoPorSubtipoIncapacidades;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public InformacionTaxonomia obtenerInformacionTaxonomia(String deaCodigo, String nombreCarpeta) {
        InformacionTaxonomia informacionTaxonomia = new InformacionTaxonomia();
        try (Connection conexion = Conexion.getConnection()) {
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_BUSCAR_TAX_INCA_INTER(?,?,?,?,?,?)}";
            try (CallableStatement call = conexion.prepareCall(consulta)) {
                call.setString("NDESCRIP", nombreCarpeta);
                call.setLong("NMDEACODIGO", Long.parseLong(deaCodigo));
                call.registerOutParameter("NMDEAPADRE", OracleTypes.NUMBER);
                call.registerOutParameter("NMAZCODIGO", OracleTypes.NUMBER);
                call.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                call.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                call.execute();
                informacionTaxonomia.setIdAzDigital(call.getNString("NMAZCODIGO"));
                informacionTaxonomia.setIdDeaCodigo(call.getNString("NMDEAPADRE"));
                if (Objects.nonNull(informacionTaxonomia.getIdAzDigital())
                        && Objects.nonNull(informacionTaxonomia.getIdDeaCodigo())) {
                    informacionTaxonomia.setEstado(true);
                } else {
                    informacionTaxonomia.setEstado(false);
                }
            }
        } catch (SQLException exception) {
            return new InformacionTaxonomia(InformacionTaxonomia.RespuestaGenerica.ERROR.getEstado() + exception.getLocalizedMessage());
        }
        return informacionTaxonomia;
    }

    @Override
    public RespuestaGenerica<Enfermedad> listarEnfermedades() {
        List<Enfermedad> listaDeEnfermedades = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_OBTENER_ENFERMEDAD(?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.registerOutParameter("RSGPENFER", OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject("RSGPENFER");
                if (!resultSet.next()) {
                    return new RespuestaGenerica<>(TipoRespuesta.WARNING, "Error al consultar la base de datos debido a :"
                            + callableStatement.getString("VCMENSAJE_PROCESO"), listaDeEnfermedades);
                }
                while (resultSet.next()) {
                    Enfermedad enfermedad = new Enfermedad();
                    enfermedad.setCodigoEnfermedad(resultSet.getString("ENF_COD"));
                    enfermedad.setCodigoGrupoEnfermedad(resultSet.getString("GEN_COD"));
                    enfermedad.setCodigoSubtipoEnfermedad(resultSet.getString("SEN_COD"));
                    enfermedad.setNombreEnfermedad(resultSet.getString("ENF_NOMBRE"));
                    listaDeEnfermedades.add(enfermedad);
                }
                callableStatement.close();
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Consulta Generada", listaDeEnfermedades);
            }
        } catch (SQLException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "Error no contralado en IincapacidaesRepo:listarEnfermedades debido a :"
                    + e);
        }

    }

    @Override
    public RespuestaGenerica<TerminosYCondiciones> obtenerTerminosYCondiciones() {
        TerminosYCondiciones terminosYCondiciones = new TerminosYCondiciones();
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_OBTENER_TERMINOSYCOND(?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.registerOutParameter("RTERMINO", OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject("RTERMINO");
                if ("S".equals(callableStatement.getString("VCESTADO_PROCESO"))) {
                    while (resultSet.next()) {
                        terminosYCondiciones.setDescripcion(resultSet.getString("TER_TEXT_HTML"));
                        terminosYCondiciones.setCodigo(resultSet.getString("TER_CODIGO"));
                    }
                    callableStatement.close();
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "OK", terminosYCondiciones);
                }
                return new RespuestaGenerica<>(TipoRespuesta.WARNING, "ERROR AL CONSULTAR LOS TERMINOS Y CONDICIONES ",
                        terminosYCondiciones);
            }
        } catch (SQLException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "ERROR NO CONTROLADO EN IincapacidaesRepo::obtenerTerminosYCondiciones DEBIDO A :" + e);
        }
    }

    @Override
    public RespuestaGenerica<Boolean> actualizarEstadoDocumento(Integer deaCodigo, Integer numeroRadico, Integer codigoDocumento) {
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_ACTUALIZAR_DOCUMENTO(?,?,?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.setInt("NMDEACODIGO", deaCodigo);
                callableStatement.setInt("NMRADICADO", numeroRadico);
                callableStatement.setInt("NMDOCUMENTO", codigoDocumento);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                if ("S".equals(callableStatement.getString("VCESTADO_PROCESO"))) {
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "OK", Boolean.TRUE);
                }
                callableStatement.close();
                return new RespuestaGenerica<>(TipoRespuesta.WARNING, "DOCUMENTO NO ACTUALIZADO", Boolean.FALSE);
            }
        } catch (SQLException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "ERROR NO CONTROLADO EN IincapacidaesRepo::actualizarEstadoDocumento DEBIDO A : " + e);
        }

    }

    @Override
    public RespuestaGenerica<Integer> crearRadicado(RadicadoDTO radicadoDTO) {
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_CREAR_RADICADO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.setString("VCTDOCUMENTO", radicadoDTO.getTipoDocumentoEmpleado());
                callableStatement.setInt("NMDOCUMENTO", radicadoDTO.getNumeroDocumentoEmpleado());
                callableStatement.setInt("NMEMPPRINCIPAL", radicadoDTO.getNumeroDocumentoEmpresaPrincipal());
                callableStatement.setString("VCTIPODOCEMPR", radicadoDTO.getTipoDocumentoEmpresaPrincipal());
                callableStatement.setInt("NMCONTRATO", radicadoDTO.getContrato());
                callableStatement.setInt("NMCODGENFE", radicadoDTO.getIdGrupoEnfermedad());
                callableStatement.setInt("NMCODSGENFE", radicadoDTO.getIdSubGrupoEnfermedad());
                callableStatement.setString("VCCODENFER", radicadoDTO.getIdCodigoEnfermedad());
                callableStatement.setInt("NMCONTINCA", radicadoDTO.getIdContigenciaIncapacidad());
                callableStatement.setString("VCFECINCIDENTE", radicadoDTO.getFechaIncidente());
                callableStatement.setString("VCFECINICIOINCA", radicadoDTO.getFechaInicioIncapacidad());
                callableStatement.setInt("NMDIAS", radicadoDTO.getNumeroDeDias());
                callableStatement.setString("VCPRORROGA", radicadoDTO.getProrroga());
                callableStatement.setInt("NMIDUSUARIO", radicadoDTO.getIdUsuarioCrea());
                callableStatement.setInt("NMSUBCONTI", radicadoDTO.getIdSubTipoContigencia());
                callableStatement.setString("NMIPUSUARIO", radicadoDTO.getDireccionIp());
                callableStatement.setString("VCFECHFUERMATE", radicadoDTO.getFechaFueroMaterno());
                callableStatement.registerOutParameter("NMRADICADO", OracleTypes.NUMBER);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                String mensaje = callableStatement.getString("VCMENSAJE_PROCESO");
                if ("S".equals(callableStatement.getString("VCESTADO_PROCESO"))) {
                    Integer numeroRadicado = callableStatement.getInt("NMRADICADO");
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "OK",
                            numeroRadicado);
                }
                callableStatement.close();
                return new RespuestaGenerica<>(TipoRespuesta.WARNING, mensaje);
            }
        } catch (SQLException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "IincapacidaesRepo::crearRadicado DEBIDO A : " + e);
        }
        //return null;
    }

    @Override
    public RespuestaGenerica<String> validacionRadicado(ValidacionRadicadoDTO validacionRadicadoDTO) {
        String estadoProceso = "";
        String mensajeProceso = "";
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_VALIDACION_RADICADO(?,?,?,?,?,?,?,?,?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.setString("VCFECHAINICIO", validacionRadicadoDTO.getFechaInicio());
                callableStatement.setInt("NMDOCUMENTOEPL", validacionRadicadoDTO.getContratoDTO().getDocumentoEmpleado());
                callableStatement.setInt("NMCONTRATO", validacionRadicadoDTO.getNumeroContrato());
                callableStatement.setInt("NMNUMERODIAS", validacionRadicadoDTO.getNumeroDeDias());
                callableStatement.setString("VCTIPDOCUMEEPL", validacionRadicadoDTO.getContratoDTO().getTipoDocumentoEmpleado());
                callableStatement.setInt("NMDOCUMENTOPAL", validacionRadicadoDTO.getContratoDTO().getDocumentoEmpresa());
                callableStatement.setString("VCTIPODOCUMPAL", validacionRadicadoDTO.getContratoDTO().getTipoDocumentoEmpresa());
                callableStatement.setInt("NMCONTINCA", validacionRadicadoDTO.getIdContigenciaIncapacidad());
                callableStatement.setString("VCFECHA_INCIDENTE", validacionRadicadoDTO.getFechaAccidente());
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                estadoProceso = callableStatement.getString("VCESTADO_PROCESO");
                mensajeProceso = callableStatement.getString("VCMENSAJE_PROCESO");
                if ("N".equals(estadoProceso)) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, mensajeProceso);
                }
            }
        } catch (SQLException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error no contralado debido a : " + e.getMessage());
        }
        return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "OK");
    }

    @Override
    public RespuestaGenerica<DocumentoCargado> documentosCargados(Integer numeroRadicado, String ipDelCliente) {
        List<DocumentoCargado> documentoCargados = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_LISTAR_DOCUMENTOS(?,?,?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.setString("VCIPCLIENTE", ipDelCliente);
                callableStatement.setInt("NMRADICADO", numeroRadicado);

                callableStatement.registerOutParameter("RCDOCUMENTOS", OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);

                callableStatement.execute();

                String estadoProceso = callableStatement.getString("VCESTADO_PROCESO");
                ResultSet resultSet = (ResultSet) callableStatement.getObject("RCDOCUMENTOS");
                if ("S".equals(estadoProceso)) {
                    while (resultSet.next()) {
                        DocumentoCargado documentoCargado = new DocumentoCargado();
                        documentoCargado.setEstadoDelDocumento(resultSet.getString("ESTADO_DOCUMENTO"));
                        documentoCargado.setObservaciones(resultSet.getString("OBSERVACION"));
                        documentoCargado.setRuta(resultSet.getString("RUTA"));
                        documentoCargado.setDocumento(new Documento(resultSet.getString("CODIGO_DOCUMENTO"),
                                resultSet.getString("NOMBRE_DOCUMENTO")));
                        documentoCargado.setDocumentoRequerido(resultSet.getString("REQUERIDO"));
                        documentoCargado.setAzCodigoCli(resultSet.getString("AZCODIGO"));
                        documentoCargados.add(documentoCargado);
                    }
                    callableStatement.close();
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "OK", documentoCargados);
                }
                return new RespuestaGenerica<>(TipoRespuesta.WARNING, "Fallo la consulta debido a : "
                        + callableStatement.getString("VCMENSAJE_PROCESO"),
                        documentoCargados);
            }

        } catch (SQLException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error no controlado en  documentosCargados debido a : " + e.getMessage());
        }

    }

    @Override
    public RespuestaGenerica<String> actualizarEstadoDocumento(DocumentoActualizadoDTO documentoActualizadoDTO) {
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_ACTUALIZAR_ESTADO_OBS(?,?,?,?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.setDouble("NMRADICADO", documentoActualizadoDTO.getNumeroRadicado());
                callableStatement.setInt("NMDOCUMENTO", documentoActualizadoDTO.getCodigoDocumento());
                callableStatement.setString("VCOBSERVACION", documentoActualizadoDTO.getObservacion());
                callableStatement.setString("VCESTADO", documentoActualizadoDTO.getEstadoDocumento());
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                String estadoDelProceso = callableStatement.getString("VCESTADO_PROCESO");
                if ("S".equals(estadoDelProceso)) {
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "OK", callableStatement.getString("VCMENSAJE_PROCESO"));
                }
                return new RespuestaGenerica<>(TipoRespuesta.WARNING, "No se logro actualizar el documento");
            }
        } catch (SQLException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error no controlado en  actualizarEstadoDocumento debido a : " + e.getMessage());
        }
    }

    @Override
    public RespuestaGenerica<InformacionTaxonomia> buscarTaxomiaRadicado(Integer numeroRadicado, String flujo) {
        InformacionTaxonomia informacionTaxonomia = new InformacionTaxonomia();
        try (Connection conexion = Conexion.getConnection()) {
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_VALIDAR_TAXRADICADO(?,?,?,?,?,?)}";
            try (CallableStatement call = conexion.prepareCall(consulta)) {
                call.setString("NMRADICADO", String.valueOf(numeroRadicado));
                call.setString("VCFLUJO", flujo);
                call.registerOutParameter("NMDEAPADRE", OracleTypes.NUMBER);
                call.registerOutParameter("NMAZCODIGO", OracleTypes.NUMBER);
                call.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                call.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                call.execute();
                informacionTaxonomia.setIdAzDigital(call.getNString("NMAZCODIGO"));
                informacionTaxonomia.setIdDeaCodigo(call.getNString("NMDEAPADRE"));
                if (Objects.nonNull(informacionTaxonomia.getIdAzDigital())
                        && Objects.nonNull(informacionTaxonomia.getIdDeaCodigo())) {
                    informacionTaxonomia.setEstado(true);
                } else {
                    informacionTaxonomia.setEstado(false);
                }
            }
        } catch (SQLException exception) {
            return new RespuestaGenerica(TipoRespuesta.ERROR, exception.toString());
        }
        return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Ok", informacionTaxonomia);
    }

        @Override
    public RespuestaGenerica<String> actualizarEstadoRadicacion(RadicadoDTO radicadoDTO) {
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_ACTUALIZAR_EST_RADICADO(?,?,?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.setInt("NMRADICADO", radicadoDTO.getNumeroRadicado());
                callableStatement.setString("VCESTADO", radicadoDTO.getEstadoRadicado());
                callableStatement.setString("VCDESCRIP", radicadoDTO.getObservacion());
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                String estado = callableStatement.getString("VCESTADO_PROCESO");
                String error = callableStatement.getString("VCMENSAJE_PROCESO");
                if (!"S".equals(estado)) {
                    return new RespuestaGenerica<>(TipoRespuesta.WARNING, "No se pudo actualizar el estado del radicado debido a : "
                            + callableStatement.getString("VCMENSAJE_PROCESO"));
                }
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "OK", callableStatement.getString("VCMENSAJE_PROCESO"));
            }
        } catch (SQLException e) {
            return new RespuestaGenerica(TipoRespuesta.ERROR, e.toString());
        }
    }

    @Override
    public RespuestaGenerica<Incapacidad> listarIncapacidadesMesa() {
        List<Incapacidad> incapacidades = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_OBTENER_INCAPACIDADES_CPT(?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("RCINC", OracleTypes.CURSOR);
                callableStatement.execute();
                String estado = callableStatement.getString("VCESTADO_PROCESO");
                if ("S".equals(estado)) {
                    ResultSet resultSet = (ResultSet) callableStatement.getObject("RCINC");
                    while (resultSet.next()) {
                        Incapacidad incapacidad = new Incapacidad();
                        incapacidad.setNumeroRadicado(resultSet.getInt("RADICADO_INC"));
                        incapacidad.setNumeroDocumentoEmpleado(resultSet.getInt("NRO_DOC_TRABAJADOR"));
                        incapacidad.setNumeroContrato(resultSet.getInt("CONTRATO_TRABAJADOR"));
                        incapacidad.setNombreDelEmpleado(resultSet.getString("EMPLEADO"));
                        incapacidad.setNombreEmpresa(resultSet.getString("NOM_PPAL"));
                        incapacidad.setFechaInicial(resultSet.getString("INC_FECINI"));
                        incapacidad.setFechaRadicacion(resultSet.getString("INC_FECHA_CREACION"));
                        incapacidad.setSubTipoIncapacidad(resultSet.getString("SBT_NOMBRE"));
                        incapacidad.setSubTipoIncapacidadCodigo(resultSet.getInt("SBT_CODIGO"));
                        incapacidad.setNumeroDeDias(resultSet.getString("DIAS_INCAPACIDAD"));
                        incapacidad.setEstado(resultSet.getString("INC_ESTADO"));
                        incapacidad.setEstadoObservacion(resultSet.getString("ESTADO_OBSERVACION"));
                        incapacidad.setTipoDocumentoEmpleado(resultSet.getString("TIPO_DOC_TRABAJADOR"));
                        incapacidad.setTipoIncapacidad(resultSet.getString("INC_CONTIN"));
                        incapacidad.setIncapacidadDescripcion(resultSet.getString("INC_DESCRIPCION"));
                        incapacidad.setEstadoObservacionTrabajador(resultSet.getString("ESTADO_OBSERVACION_TRAB"));
                        incapacidad.setNombreEps(resultSet.getString("NOMBRE_EPS"));
                        incapacidad.setNombreEmpPrincipal(resultSet.getString("NOMBRE_PPAL"));
                        incapacidad.setNombreEmpUsuaria(resultSet.getString("NOMBRE_USUA"));
                        incapacidad.setFechaFinal(resultSet.getString("FECHA_FINAL_INCAP"));
                        incapacidades.add(incapacidad);
                    }
                    callableStatement.close();
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "OK", incapacidades);
                }
                return new RespuestaGenerica<>(TipoRespuesta.WARNING, "No se pudieron traer las incapacidades radicadas debido a : "
                        + callableStatement.getString("VCMENSAJE_PROCESO"),
                        incapacidades);
            }
        } catch (SQLException e) {
            return new RespuestaGenerica(TipoRespuesta.ERROR, e.toString());
        }

    }

    @Override
    public RespuestaGenerica<?> obtenerEndpointTramaCodAZ(String deaCodigo) {
        Connection connection = null;
        try {
            connection = Conexion.getConnection();
            CallableStatement callableStatement = connection.prepareCall("call ADM.QB_APP_GESTORDOC.PL_SOLICITAR_ARCHIVO(?,?,?)");
            callableStatement.setObject("NMID", deaCodigo);
            callableStatement.registerOutParameter("VCEND_POINT", OracleTypes.VARCHAR);
            callableStatement.registerOutParameter("VCTRAMAXML", OracleTypes.VARCHAR);
            callableStatement.execute();
            String endPoint = callableStatement.getString("VCEND_POINT");
            String requestBodyXml = callableStatement.getString("VCTRAMAXML");
            if (Objects.isNull(endPoint) || Objects.isNull(requestBodyXml)) {
                return new RespuestaGenerica<>(TipoRespuesta.WARNING, "El procedimiento QB_APP_GESTORDOC.PL_FIRMAR_DOCUMENTO, ha retornado valores nulos");
            }
            HashMap<String, String> listValues = new HashMap<String, String>() {
                {
                    put("endPoint", endPoint);
                    put("requestBodyXml", requestBodyXml);
                }
            };
            return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Consulta realizada con exito", listValues);
        } catch (Exception e) {
            e.printStackTrace();
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error no controlado en obtenerEndpointTramaFirma");
        }
    }

    @Override
    public RespuestaGenerica<Integer> crearGers(ContratoDTO contratoDTO) {
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_CREAR_GERS(?,?,?,?,?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.setInt("NMNITEMP", contratoDTO.getDocumentoEmpresa());
                callableStatement.setInt("NMDOCUMEPL", contratoDTO.getDocumentoEmpleado());
                callableStatement.setString("VCTIPDOCEMP", contratoDTO.getTipoDocumentoEmpresa());
                callableStatement.setString("VCTIDOCEPL", contratoDTO.getTipoDocumentoEmpleado());
                callableStatement.registerOutParameter("NMNUMGERS", OracleTypes.NUMBER);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                String respuesta = callableStatement.getString("VCESTADO_PROCESO");
                if (!"S".equals(respuesta)) {
                    return new RespuestaGenerica<>(TipoRespuesta.WARNING, "No se pudieron traer las incapacidades radicadas debido a : "
                            + callableStatement.getString("VCMENSAJE_PROCESO"));
                }
                Integer numeroGers = callableStatement.getInt("NMNUMGERS");
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "OK", numeroGers);
            }

        } catch (SQLException e) {
            return new RespuestaGenerica(TipoRespuesta.ERROR, e.toString());
        }
    }

    @Override
    public RespuestaGenerica<EstadoObservacion> listarEstadosObservcacion() {
        List<EstadoObservacion> estadosObservacion = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "{call RHU.QB_APLICATION_JRHU0055.PL_LISTAR_ESTADO_SITIO(?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.registerOutParameter("RCESTADO", OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                String mensajeProceso = callableStatement.getString("VCESTADO_PROCESO");

                if ("S".equals(mensajeProceso)) {
                    ResultSet resultSet = (ResultSet) callableStatement.getObject("RCESTADO");
                    while (resultSet.next()) {
                        EstadoObservacion estadoObservacion = new EstadoObservacion();
                        estadoObservacion.setEstado(resultSet.getString("OBS_ESTADO"));
                        estadoObservacion.setDescripcion(resultSet.getString("OBS_DESCRIPCION"));
                        estadoObservacion.setTipo(resultSet.getString("OBS_TIPO"));
                        estadosObservacion.add(estadoObservacion);
                    }
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "ok", estadosObservacion);
                }
                return new RespuestaGenerica<>(TipoRespuesta.WARNING, "No se obtuvo la lista de estados", estadosObservacion);
            }
        } catch (SQLException e) {
            return new RespuestaGenerica(TipoRespuesta.ERROR, e.toString());
        }

    }

    @Override
    public RespuestaGenerica<ObservacionRadicado> listarObservacionesPorRadicado(Integer numRadicado) {
        List<ObservacionRadicado> observacionRadicado = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()) {
            String consulta = "{call RHU.QB_APLICATION_JRHU0055.PL_OBSERVACION_POR_RADICADO(?,?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)) {
                callableStatement.setInt("NMIN_RADICACION ", numRadicado);
                callableStatement.registerOutParameter("RCINC", OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                String mensajeProceso = callableStatement.getString("VCESTADO_PROCESO");

                if ("S".equals(mensajeProceso)) {
                    ResultSet resultSet = (ResultSet) callableStatement.getObject("RCINC");
                    while (resultSet.next()) {
                        ObservacionRadicado estadoObservacion = new ObservacionRadicado();
                        estadoObservacion.setNumeroRadicado(resultSet.getInt("INC_RADICACION"));
                        estadoObservacion.setNumObservacion(resultSet.getInt("OBS_SECUENCIA"));
                        estadoObservacion.setDescripcion(resultSet.getString("OBS_DESCRIPCION"));
                        estadoObservacion.setUsuario(resultSet.getString("OBS_USUARIO"));
                        estadoObservacion.setFecha(resultSet.getString("OBS_FECHA"));
                        estadoObservacion.setEstado(resultSet.getString("OBS_ESTADO"));
                        observacionRadicado.add(estadoObservacion);
                    }
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "ok", observacionRadicado);
                }
                return new RespuestaGenerica<>(TipoRespuesta.WARNING, "No se obtuvo la lista de Observaciones", observacionRadicado);
            }
        } catch (SQLException e) {
            return new RespuestaGenerica(TipoRespuesta.ERROR, e.toString());
        }
    }
}
