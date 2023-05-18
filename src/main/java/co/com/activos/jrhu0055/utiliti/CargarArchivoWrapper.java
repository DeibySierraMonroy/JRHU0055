package co.com.activos.jrhu0055.utiliti;

import co.com.activos.jrhu0055.model.CargarArchivoMapper;

/**
 * Wrapper encargado de encapsular todos los valores obtenidos de la ejecución del WS de crear archivo
 *
 * @author Francisco Javier Rincon (nvalue)
 * @version 1.0
 * @since JDK 1.8
 */
public class CargarArchivoWrapper {

    public static CargarArchivoMapper getMapper(String response) throws Exception {
        String estado = CustomSOAPUtils.getNodeSingleValue(response, "out:estado");
        String idArchivo = CustomSOAPUtils.getNodeSingleValue(response, "out:idArchivoNuevo");
        String idCarpeta = CustomSOAPUtils.getNodeSingleValue(response, "out:idCarpeta");
        CargarArchivoMapper archivoResponse = new CargarArchivoMapper();
        if (StringUtils.isNullOrEmpty(estado) || StringUtils.isNullOrEmpty(idArchivo)) {
            archivoResponse.setEstado("ERROR");
            return archivoResponse;
        }
        archivoResponse.setEstado(estado.toUpperCase());
        archivoResponse.setIdArchivoNuevo(idArchivo);
        archivoResponse.setIdCarpeta(idCarpeta);
        return archivoResponse;
    }

}
