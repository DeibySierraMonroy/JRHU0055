
package co.com.activos.jrhu0055.model;
import co.com.activos.jrhu0055.utiliti.CustomSOAPUtils;
import co.com.activos.jrhu0055.utiliti.StringUtils;
import java.io.Serializable;

public class CargarAZArchivoWrapper implements Serializable {
    
    public static CargarAZArchivoMapper getMapper(String response) throws Exception {
        String nombre = CustomSOAPUtils.getNodeSingleValue(response, "out:nombre");
        String idArchivo = CustomSOAPUtils.getNodeSingleValue(response, "out:archivo");
        CargarAZArchivoMapper archivoResponse = new CargarAZArchivoMapper();
        if (StringUtils.isNullOrEmpty(nombre) || StringUtils.isNullOrEmpty(idArchivo)) {
            archivoResponse.setEstado("ERROR");
            return archivoResponse;
        }
        archivoResponse.setEstado(nombre.toUpperCase());
        archivoResponse.setIdArchivoNuevo(idArchivo);
        return archivoResponse;
    }
    
}
