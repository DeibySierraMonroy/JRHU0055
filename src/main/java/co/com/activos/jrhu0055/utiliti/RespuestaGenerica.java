package co.com.activos.jrhu0055.utiliti;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RespuestaGenerica<T> implements Serializable {

    private TipoRespuesta status;
    private CodigoError codigoError;
    private String mensaje;
    private Integer valorRetorno;
    private String body;
    private Integer estatus;
    private String resultadoSubidaDocumentos;
    private T objeto;
    private List<T> listaResultados;
    private Long resultLong;
    private Map<String, ?> listValues;
    private Exception exception;
    private String codigo;
    private String idTransaccionRadicado;

    public RespuestaGenerica() {
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
    }

    public RespuestaGenerica(TipoRespuesta status, CodigoError codigoError, String mensaje, String idTransaccionRadicado) {
        this.status = status;
        this.codigo = codigoError.name();
        this.mensaje = mensaje;
        this.idTransaccionRadicado = idTransaccionRadicado;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, CodigoError codigoError, String idTransaccionRadicado) {
        this.status = status;
        this.mensaje = mensaje;
        this.codigoError = codigoError;
        this.idTransaccionRadicado = idTransaccionRadicado;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, Integer valorRetorno, String idTransaccionRadicado) {
        this.status = status;
        this.mensaje = mensaje;
        this.valorRetorno = valorRetorno;
        this.idTransaccionRadicado = idTransaccionRadicado;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, String idTransaccionRadicado) {
        this.status = status;
        this.mensaje = mensaje;
        this.idTransaccionRadicado = idTransaccionRadicado;
    }
    
    
     public RespuestaGenerica(TipoRespuesta status, String mensaje, String idTransaccionRadicado, T objeto) {
        this.status = status;
        this.mensaje = mensaje;
        this.idTransaccionRadicado = idTransaccionRadicado;
        this.objeto = objeto;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, CodigoError codigoError, Exception exception, String idTransaccionRadicado) {
        this.status = status;
        this.mensaje = mensaje;
        this.codigoError = codigoError;
        this.exception = exception;
        this.idTransaccionRadicado = idTransaccionRadicado;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, Map<String, ?> listValues, String idTransaccionRadicado) {
        this.status = status;
        this.mensaje = mensaje;
        this.listValues = listValues;
        this.idTransaccionRadicado = idTransaccionRadicado;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, String resultadoSubidaDocumentos, List<T> listaResultados, String idTransaccionRadicado) {
        this.status = status;
        this.mensaje = mensaje;
        this.resultadoSubidaDocumentos = resultadoSubidaDocumentos;
        this.listaResultados = listaResultados;
        this.idTransaccionRadicado = idTransaccionRadicado;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, T objeto, String idTransaccionRadicado) {
        this.status = status;
        this.mensaje = mensaje;
        this.objeto = objeto;
        this.idTransaccionRadicado = idTransaccionRadicado;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, String codigo, String idTransaccionRadicado) {
        this.status = status;
        this.mensaje = mensaje + " :" + codigo;
        this.idTransaccionRadicado = idTransaccionRadicado;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, List<T> listaResultados, String idTransaccionRadicado) {
        this.status = status;
        this.mensaje = mensaje;
        this.listaResultados = listaResultados;
        this.idTransaccionRadicado = idTransaccionRadicado;
    }
    //TipoRespuesta.WARNING, INC_VAL_CAM, INC_VAL_CAM.getDescripcion(), 
    //   radicadoDTO.getIdTransaccionRadicado(), new InformacionTaxonomia().setEstado(false));

   

    /**
     *
     * @param estatus
     * @param mensaje
     * @param body
     */
    public RespuestaGenerica(Integer estatus, String mensaje, String body, String idTransaccionRadicado) {
        this.estatus = estatus;
        this.mensaje = mensaje;
        this.body = body;
        this.idTransaccionRadicado = idTransaccionRadicado;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, Exception exception) {
        this.status = status;
        this.mensaje = mensaje;
        this.exception = exception;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, String idTransaccionRadicado, Exception exception) {
        this.status = status;
        this.mensaje = mensaje;
        this.idTransaccionRadicado = idTransaccionRadicado;
        this.exception = exception;
    }

    public TipoRespuesta getStatus() {
        return status;
    }

    public void setStatus(TipoRespuesta status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }

    public List<T> getListaResultados() {
        return listaResultados;
    }

    public void setListaResultados(List<T> listaResultados) {
        this.listaResultados = listaResultados;
    }

    public Long getResultLong() {
        return resultLong;
    }

    public void setResultLong(Long resultLong) {
        this.resultLong = resultLong;
    }

    public Map<String, ?> getListValues() {
        return listValues;
    }

    public void setListValues(Map<String, ?> listValues) {
        this.listValues = listValues;
    }

    public String getResultadoSubidaDocumentos() {
        return resultadoSubidaDocumentos;
    }

    public void setResultadoSubidaDocumentos(String resultadoSubidaDocumentos) {
        this.resultadoSubidaDocumentos = resultadoSubidaDocumentos;
    }

    public Integer getValorRetorno() {
        return valorRetorno;
    }

    public void setValorRetorno(Integer valorRetorno) {
        this.valorRetorno = valorRetorno;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public CodigoError getCodigoError() {
        return codigoError;
    }

    public Exception getException() {
        return exception;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getIdTransaccionRadicado() {
        return idTransaccionRadicado;
    }

    public void setIdTransaccionRadicado(String idTransaccionRadicado) {
        this.idTransaccionRadicado = idTransaccionRadicado;
    }

}
