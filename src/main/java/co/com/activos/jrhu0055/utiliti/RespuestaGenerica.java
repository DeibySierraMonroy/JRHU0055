package co.com.activos.jrhu0055.utiliti;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RespuestaGenerica<T> implements Serializable{
    private TipoRespuesta status;
    private String mensaje;
    private Integer valorRetorno;

    private String resultadoSubidaDocumentos;
    private T objeto;
    private List<T> listaResultados;
    private Long resultLong;
    private Map<String, ?> listValues;

    public RespuestaGenerica() {
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

    public RespuestaGenerica(TipoRespuesta status, String mensaje, List<T> listaResultados) {
        this.status = status;
        this.mensaje = mensaje;
        this.listaResultados = listaResultados;
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
    
    
}
