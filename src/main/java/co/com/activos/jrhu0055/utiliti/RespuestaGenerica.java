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

    public RespuestaGenerica() {
    }

    public RespuestaGenerica(TipoRespuesta status, CodigoError codigoError, String mensaje) {
        this.status = status;
        this.codigo = codigoError.name();
        this.mensaje = mensaje;
    }
    
    
    
     public RespuestaGenerica(TipoRespuesta status, String mensaje,CodigoError codigoError) {
        this.status = status;
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, Integer valorRetorno) {
        this.status = status;
        this.mensaje = mensaje;
        this.valorRetorno = valorRetorno;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
    }
    
           
        public RespuestaGenerica(TipoRespuesta status, String mensaje,CodigoError codigoError, Exception exception) {
        this.status = status;
        this.mensaje = mensaje;
        this.codigoError = codigoError;
        this.exception = exception;
    }
        

    public RespuestaGenerica(TipoRespuesta status, String mensaje, Map<String, ?> listValues) {
        this.status = status;
        this.mensaje = mensaje;
        this.listValues = listValues;
    }

    public RespuestaGenerica(TipoRespuesta status, String mensaje, String resultadoSubidaDocumentos, List<T> listaResultados) {
        this.status = status;
        this.mensaje = mensaje;
        this.resultadoSubidaDocumentos = resultadoSubidaDocumentos;
        this.listaResultados = listaResultados;
    }
    
    

    public RespuestaGenerica(TipoRespuesta status, String mensaje, T objeto) {
        this.status = status;
        this.mensaje = mensaje;
        this.objeto = objeto;
    }
     
     public RespuestaGenerica(TipoRespuesta status, String mensaje, String codigo) {
        this.status = status;
        this.mensaje = mensaje +  " :" + codigo;
    }
     
   
    

    public RespuestaGenerica(TipoRespuesta status, String mensaje, List<T> listaResultados) {
        this.status = status;
        this.mensaje = mensaje;
        this.listaResultados = listaResultados;
    }

    /**
     *
     * @param estatus
     * @param mensaje
     * @param body
     */
    public RespuestaGenerica(Integer estatus, String mensaje, String body) {
        this.estatus = estatus;
        this.mensaje = mensaje;
        this.body = body;
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
    
    

    
}
