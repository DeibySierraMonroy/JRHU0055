package co.com.activos.jrhu0055.model;

import java.util.HashMap;

/**
 * DTO encargado de almacenar la información de una petición HTTP
 *
 * @author Francisco Javier Rincon
 * @version 1.0
 * @since JDK 1.8
 */
public class ExecuteUrlRequestDto {

    private String url;
    private String httpMethod;
    private String contentType;
    private HashMap<String, ?> parameters;
    private String body;
    private boolean soap;
    private boolean rest;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public HashMap<String, ?> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, ?> parameters) {
        this.parameters = parameters;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isSoap() {
        return soap;
    }

    public void setSoap(boolean soap) {
        this.soap = soap;
    }

    public boolean isRest() {
        return rest;
    }

    public void setRest(boolean rest) {
        this.rest = rest;
    }

}
