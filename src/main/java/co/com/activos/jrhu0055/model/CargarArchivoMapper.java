package co.com.activos.jrhu0055.model;

/**
 * Almacena la respuesta de la ejecución del WS que carga un archivo
 *
 * @author Francisco Javier Rincon (nvalue)
 * @version 1.0
 * @since JDK 1.8
 */
public class CargarArchivoMapper {

   private String estado;
   private String idArchivoNuevo;
   private String idCarpeta;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdArchivoNuevo() {
        return idArchivoNuevo;
    }

    public void setIdArchivoNuevo(String idArchivoNuevo) {
        this.idArchivoNuevo = idArchivoNuevo;
    }

    public String getIdCarpeta() {
        return idCarpeta;
    }

    public void setIdCarpeta(String idCarpeta) {
        this.idCarpeta = idCarpeta;
    }
}
