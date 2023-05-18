package co.com.activos.jrhu0055.model;

import java.io.IOException;

/**
 * DTO encargado de almacenar la información de una respuesta HTTP
 *
 * @author Francisco Javier Rincon
 * @version 1.0
 * @since JDK 1.8
 */
public class ExecuteUrlResponseDto {

    private int httpStatus;
    private String responseBody;

    public ExecuteUrlResponseDto() {
    }

    public ExecuteUrlResponseDto(int responseCode, String response) {
        this.httpStatus = responseCode;
        this.responseBody = response;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

}
