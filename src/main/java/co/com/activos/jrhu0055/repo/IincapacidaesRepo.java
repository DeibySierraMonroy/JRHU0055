package co.com.activos.jrhu0055.repo;

import co.com.activos.jrhu0055.DTO.ContratoDTO;
import co.com.activos.jrhu0055.DTO.RadicadoDTO;
import co.com.activos.jrhu0055.DTO.ValidacionRadicadoDTO;
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
import java.util.List;
import java.util.Objects;


public class IincapacidaesRepo implements IincapacidadService {


    @Override
    public List<Incapacidad> incapacidades(ContratoDTO contratoDTO) {
        List<Incapacidad> listaIncapacidades = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()){
            String consulta = "call RHU.QB_APLICATION_JRHU0055.PL_OBTENER_TODAS_INCAPACIDADES(?,?,?,?,?,?,?)";
            try(CallableStatement callableStatement = connection.prepareCall(consulta)){
                callableStatement.setString("VCTIPODOCUMENTO",contratoDTO.getTipoDocumentoEmpleado());
                callableStatement.setInt("NMDOCUMENTO",contratoDTO.getDocumentoEmpleado());
                callableStatement.setString("VCTIPODOCUMENTOEMPRE",contratoDTO.getTipoDocumentoEmpresa());
                callableStatement.setInt("NMDOCUMENTOPRINCIPAL",contratoDTO.getDocumentoEmpresa());
                callableStatement.registerOutParameter("RCINC", OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject("RCINC");
                while (resultSet.next()){
                    Incapacidad incapacidad = new Incapacidad();
                    incapacidad.setEstado(resultSet.getString("INC_ESTADO"));
                    incapacidad.setNumeroContrato(resultSet.getInt("CTO_NUMERO"));
                    incapacidad.setSigla(resultSet.getString("TEN_SIGLA"));
                    incapacidad.setFechaFinal(resultSet.getString("INC_FECFIN"));
                    incapacidad.setFechaInicial(resultSet.getString("INC_FECINI"));
                    incapacidad.setNombreEmpresa(resultSet.getString("NOMBRE_EMPRESA_PRINCIPAL"));
                    incapacidad.setTipoIncapacidad(resultSet.getString("TIPO_INCAPACIDAD"));
                    incapacidad.setSubTipoIncapacidad(resultSet.getString("SUB_TIPO_INCAPACIDAD"));
                    incapacidad.setNumeroRadicado(resultSet.getInt("INC_RADICACION"));
                    incapacidad.setFechaDeRadicacion(resultSet.getString("INC_FECHA_CREACION"));
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
                        tipoIncapacidad.setCodigoTipoIncapacidad(resultSet.getInt("TIPOINC_CODIGO"));
                        tipoIncapacidad.setNombreTipoIncapacidad(resultSet.getString("TIPOINC_NOMBRE"));
                        tipoIncapacidad.setEstado(resultSet.getString("TIPOINC_ESTADO"));
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
                        subTipoIncapacidad.setCodigoSubTipoIncapacidad(resultSet.getInt("SUBTIPOINC_CODIGO"));
                        subTipoIncapacidad.setNombreSubTipoIncapacidad(resultSet.getString("SUBTIPOINC_NOMBRE"));
                        subTipoIncapacidad.setEstado(resultSet.getString("SUBTIPOINC_ESTADO"));
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
                callableStatement.setString("VTDC_TD_EPL",contratoDTO.getTipoDocumentoEmpleado());
                callableStatement.setInt("NEPL_ND",contratoDTO.getDocumentoEmpleado());
                callableStatement.setInt("NEMP_ND",contratoDTO.getDocumentoEmpresa());
                callableStatement.setString("VTDC_TD",contratoDTO.getTipoDocumentoEmpresa());

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
                        contrato.setFechaFinalizacionContrato(resultSet.getString("CTO_FECTERM"));
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
        try (Connection connection = Conexion.getConnection()){
            String consulta = "call RHU.QB_APLICATION_JRHU0055.PL_OBTENER_DOCUM_SUBTIP(?,?,?,?)";
            try(CallableStatement callableStatement = connection.prepareCall(consulta)){
               callableStatement.setInt("NSUBTIPOINC" , codigoSubTipoIncapacidad);
               callableStatement.registerOutParameter("RDOCUMENSUBTIPO" ,OracleTypes.CURSOR);
               callableStatement.registerOutParameter("VCESTADO_PROCESO" ,OracleTypes.VARCHAR);
               callableStatement.registerOutParameter("VCMENSAJE_PROCESO" ,OracleTypes.VARCHAR);
               callableStatement.execute();
               ResultSet resultSet = (ResultSet) callableStatement.getObject("RDOCUMENSUBTIPO");
                if (Objects.nonNull(resultSet)) {
                    while (resultSet.next()) {
                        DocumentoPorSubtipoIncapacidad documentoPorSubtipoIncapacidad = new DocumentoPorSubtipoIncapacidad();
                        documentoPorSubtipoIncapacidad.setIdDocumento(resultSet.getInt("TPD_CODIGO"));
                        documentoPorSubtipoIncapacidad.setDescripcionDelDocumento(resultSet.getString("TPD_DESCRIPCION"));
                        documentoPorSubtipoIncapacidad.setRequerido(
                                documentoPorSubtipoIncapacidad.
                                        validarSiElDocumentoEsrequerido(resultSet.getString("SUDO_REQUERIDO"))
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
                if (Objects.nonNull(informacionTaxonomia.getIdAzDigital()) &&
                        Objects.nonNull(informacionTaxonomia.getIdDeaCodigo())) {
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
    public  RespuestaGenerica<Enfermedad>  listarEnfermedades() {
        List<Enfermedad> listaDeEnfermedades = new ArrayList<>();
        try (Connection connection = Conexion.getConnection()){
            String consulta ="{ call RHU.QB_APLICATION_JRHU0055.PL_OBTENER_ENFERMEDAD(?,?,?)}";
            try(CallableStatement callableStatement = connection.prepareCall(consulta)){
                callableStatement.registerOutParameter("RSGPENFER",OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO", OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO", OracleTypes.VARCHAR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject("RSGPENFER");
                if (!resultSet.next()){
                    return new RespuestaGenerica<>(TipoRespuesta.WARNING, "Error al consultar la base de datos debido a :" +
                            callableStatement.getString("VCMENSAJE_PROCESO"),listaDeEnfermedades);
                }
                while (resultSet.next()){
                    Enfermedad enfermedad = new Enfermedad();
                    enfermedad.setCodigoEnfermedad(resultSet.getString("ENF_COD"));
                    enfermedad.setCodigoGrupoEnfermedad(resultSet.getString("GEN_COD"));
                    enfermedad.setCodigoSubtipoEnfermedad(resultSet.getString("SEN_COD"));
                    enfermedad.setNombreEnfermedad(resultSet.getString("ENF_NOMBRE"));
                    listaDeEnfermedades.add(enfermedad);
                }
                callableStatement.close();
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS,"Consulta Generada",listaDeEnfermedades);
            }
        } catch (SQLException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "Error no contralado en IincapacidaesRepo:listarEnfermedades debido a :" +
                    e);
        }

    }

    @Override
    public RespuestaGenerica<TerminosYCondiciones> obtenerTerminosYCondiciones() {
        TerminosYCondiciones terminosYCondiciones = new TerminosYCondiciones();
        try(Connection connection = Conexion.getConnection()){
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_OBTENER_TERMINOSYCOND(?,?,?)}";
            try (CallableStatement callableStatement = connection.prepareCall(consulta)){
                callableStatement.registerOutParameter("RTERMINO", OracleTypes.CURSOR);
                callableStatement.registerOutParameter("VCESTADO_PROCESO" , OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO" , OracleTypes.VARCHAR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject("RTERMINO");
                if ("S".equals(callableStatement.getString("VCESTADO_PROCESO"))){
                    while (resultSet.next()) {
                        terminosYCondiciones.setDescripcion(resultSet.getString("TER_TEXT_HTML"));
                        terminosYCondiciones.setCodigo(resultSet.getString("TER_CODIGO"));
                    }
                    callableStatement.close();
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS,"OK",terminosYCondiciones);
                }
                return new RespuestaGenerica<>(TipoRespuesta.WARNING,"ERROR AL CONSULTAR LOS TERMINOS Y CONDICIONES ",
                        terminosYCondiciones);
            }
        } catch (SQLException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,"ERROR NO CONTROLADO EN IincapacidaesRepo::obtenerTerminosYCondiciones DEBIDO A :" + e);
        }
    }

    @Override
    public RespuestaGenerica<Boolean> actualizarEstadoDocumento(Integer deaCodigo,Integer numeroRadico, Integer codigoDocumento) {
        try(Connection connection = Conexion.getConnection()){
            String consulta ="{ call RHU.QB_APLICATION_JRHU0055.PL_ACTUALIZAR_DOCUMENTO(?,?,?,?,?)}";
            try(CallableStatement callableStatement = connection.prepareCall(consulta)){
                callableStatement.setInt("NMDEACODIGO",deaCodigo);
                callableStatement.setInt("NMRADICADO",numeroRadico);
                callableStatement.setInt("NMDOCUMENTO",codigoDocumento);
                callableStatement.registerOutParameter("VCESTADO_PROCESO" , OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO" , OracleTypes.VARCHAR);
                callableStatement.execute();
                if ("S".equals(callableStatement.getString("VCESTADO_PROCESO"))){
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS,"OK",Boolean.TRUE);
                }
                callableStatement.close();
                return new RespuestaGenerica<>(TipoRespuesta.WARNING,"DOCUMENTO NO ACTUALIZADO",Boolean.FALSE);
            }
        } catch (SQLException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,"ERROR NO CONTROLADO EN IincapacidaesRepo::actualizarEstadoDocumento DEBIDO A : "+ e);
        }

    }

    @Override
    public RespuestaGenerica<Integer> crearRadicado(RadicadoDTO radicadoDTO)  {
        try(Connection connection = Conexion.getConnection()){
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_CREAR_RADICADO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            try(CallableStatement callableStatement = connection.prepareCall(consulta)){
                callableStatement.setString("VCTDOCUMENTO",radicadoDTO.getTipoDocumentoEmpleado());
                callableStatement.setInt("NMDOCUMENTO",radicadoDTO.getNumeroDocumentoEmpleado());
                callableStatement.setInt("NMEMPPRINCIPAL",radicadoDTO.getNumeroDocumentoEmpresaPrincipal());
                callableStatement.setString("VCTIPODOCEMPR",radicadoDTO.getTipoDocumentoEmpresaPrincipal());
                callableStatement.setInt("NMCONTRATO",radicadoDTO.getContrato());
                callableStatement.setInt("NMCODGENFE",radicadoDTO.getIdGrupoEnfermedad());
                callableStatement.setInt("NMCODSGENFE",radicadoDTO.getIdSubGrupoEnfermedad());
                callableStatement.setString("VCCODENFER",radicadoDTO.getIdCodigoEnfermedad());
                callableStatement.setInt("NMCONTINCA",radicadoDTO.getIdContigenciaIncapacidad());
                callableStatement.setString("VCFECINCIDENTE",radicadoDTO.getFechaIncidente());
                callableStatement.setString("VCFECINICIOINCA",radicadoDTO.getFechaInicioIncapacidad());
                callableStatement.setInt("NMDIAS",radicadoDTO.getNumeroDeDias());
                callableStatement.setString("VCPRORROGA",radicadoDTO.getProrroga());
                callableStatement.setInt("NMIDUSUARIO",radicadoDTO.getIdUsuarioCrea());
                callableStatement.setInt("NMSUBCONTI",radicadoDTO.getIdSubTipoContigencia());
                callableStatement.setString("NMIPUSUARIO" , radicadoDTO.getDireccionIp());
                callableStatement.registerOutParameter("NMRADICADO" , OracleTypes.NUMBER);
                callableStatement.registerOutParameter("VCESTADO_PROCESO" , OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO" , OracleTypes.VARCHAR);
                callableStatement.execute();
                String mensaje = callableStatement.getString("VCMENSAJE_PROCESO");
                if ("S".equals(callableStatement.getString("VCESTADO_PROCESO"))){
                    return new RespuestaGenerica<>(TipoRespuesta.SUCCESS,"OK",
                            callableStatement.getInt("NMRADICADO"));
                }
                callableStatement.close();
                return new RespuestaGenerica<>(TipoRespuesta.WARNING,"FALLO AL CREAR EL RADICADO " + mensaje);
            }
        } catch (SQLException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,"IincapacidaesRepo::crearRadicado DEBIDO A : "+ e);
        }
    }

    @Override
    public RespuestaGenerica<String> validacionRadicado(ValidacionRadicadoDTO validacionRadicadoDTO) {
        String estadoProceso = "";
        String mensajeProceso = "";
        try(Connection connection = Conexion.getConnection()){
            String consulta = "{ call RHU.QB_APLICATION_JRHU0055.PL_VALIDACION_RADICADO(?,?,?,?,?,?,?,?,?)}";
            try(CallableStatement callableStatement = connection.prepareCall(consulta)){
                callableStatement.setString("VCFECHAINICIO",validacionRadicadoDTO.getFechaIncio());
                callableStatement.setInt("NMDOCUMENTOEPL",validacionRadicadoDTO.getContratoDTO().getDocumentoEmpleado());
                callableStatement.setInt("NMCONTRATO",validacionRadicadoDTO.getNumeroContrato());
                callableStatement.setInt("NMNUMERODIAS",validacionRadicadoDTO.getNumeroDeDias());
                callableStatement.setString("VCTIPDOCUMEEPL",validacionRadicadoDTO.getContratoDTO().getTipoDocumentoEmpleado());
                callableStatement.setInt("NMDOCUMENTOPAL",validacionRadicadoDTO.getContratoDTO().getDocumentoEmpresa());
                callableStatement.setString("VCTIPODOCUMPAL",validacionRadicadoDTO.getContratoDTO().getTipoDocumentoEmpresa());
                callableStatement.registerOutParameter("VCESTADO_PROCESO",OracleTypes.VARCHAR);
                callableStatement.registerOutParameter("VCMENSAJE_PROCESO",OracleTypes.VARCHAR);
                callableStatement.execute();
                estadoProceso = callableStatement.getString("VCESTADO_PROCESO");
                mensajeProceso = callableStatement.getString("VCMENSAJE_PROCESO");
                if ("N".equals(estadoProceso)){
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR,mensajeProceso);
                }
            }
        } catch (SQLException e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,"Error no contralado debido a : " + e.getMessage());
        }
        return new RespuestaGenerica<>(TipoRespuesta.SUCCESS,"OK");
    }
}
