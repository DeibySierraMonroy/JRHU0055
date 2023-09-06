package co.com.activos.jrhu0055.utiliti;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CamposExeptionHandler implements ExceptionMapper<ErrorAplicacion> {


    @Override
    public Response toResponse(ErrorAplicacion errorAplicacion) {
        String errorMessage = "Error en la aplicación: " + errorAplicacion.getMessage();
        String errorCode = errorAplicacion.getCodigo();
        JsonObject errorJson = Json.createObjectBuilder()
                .add("message", errorMessage)
                .add("code", errorCode)
                .build();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorJson.toString())
                .build();
    }
}
