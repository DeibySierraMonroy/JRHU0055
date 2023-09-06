package co.com.activos.jrhu0055.utiliti;

import static co.com.activos.jrhu0055.utiliti.StringUtils.anyItemIsNullOrEmpty;
import java.util.List;
import javax.ws.rs.core.Response;

/**
 *
 * @author egacha
 */
public class Validacion<T> {

    private T objeto;
    private List<T> listaResultados;
    private String resultadoValidacion;
    

    public Validacion(T objeto, List<T> listaResultados, String resultadoValidacion) {
        this.objeto = objeto;
        this.listaResultados = listaResultados;
        this.resultadoValidacion = resultadoValidacion;
    }

    public Validacion() {
    }

    public Validacion(List<T> listaResultados) {
        this.listaResultados = listaResultados;
    }
    
//    public static Response validarCampos(Validacion validacion){
//        validacion.listaResultados;
//        
//        return null;
//    }

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

    public String getResultadoValidacion() {
        return resultadoValidacion;
    }

    public void setResultadoValidacion(String resultadoValidacion) {
        this.resultadoValidacion = resultadoValidacion;
    }
    
    

}
