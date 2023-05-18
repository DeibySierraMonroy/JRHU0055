package co.com.activos.jrhu0055.utiliti;


import co.com.activos.jrhu0055.model.ExecuteUrlRequestDto;
import co.com.activos.jrhu0055.model.ExecuteUrlResponseDto;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Utilidades para realizar llamados HTTP
 *
 * @author Francisco Javier Rincon (nValue)
 * @version 1.0
 * @since JDK 1.8
 */
public class HttpCustomUtils {

    @SuppressWarnings("HttpUrlsUsage")
    private static final String HTTP_PROTOCOL = "http://";

    /**
     * Dada una URL en string, la ejecuta y de ser solicitado en los parámetros de ejecución del jar, devuelve el
     * cuerpo en él response
     *
     * @param requestDto Info de la solicitud HTTP a ejecutar
     * @throws Exception Causada por un error no controlado al ejecutar el programa
     */
    public static ExecuteUrlResponseDto executeUrl(ExecuteUrlRequestDto requestDto) throws Exception {
        HttpURLConnection httpClient = null;
        try {
            URL url = new URL(requestDto.getUrl());
            httpClient = (HttpURLConnection) url.openConnection();
            httpClient.setRequestProperty(HttpHeaders.CONTENT_TYPE, getFullContentType(requestDto.getContentType()));
            httpClient.setRequestProperty(HttpHeaders.ACCEPT, requestDto.getContentType());
            httpClient.setRequestMethod(requestDto.getHttpMethod());
            if (requestDto.getHttpMethod().equals(HttpMethod.POST) && requestDto.isRest() && !requestDto.getParameters().isEmpty()) {
                String urlParameters = joiningParameters(requestDto.getParameters());
                try (DataOutputStream outputStream = new DataOutputStream(httpClient.getOutputStream())) {
                    outputStream.writeBytes(urlParameters);
                    outputStream.flush();
                }
            }
            if (requestDto.isSoap() && requestDto.getBody() != null) {
                httpClient.setDoOutput(Boolean.TRUE);
                try (OutputStreamWriter outputStream = new OutputStreamWriter(httpClient.getOutputStream())) {
                    outputStream.write(requestDto.getBody());
                    outputStream.flush();
                }
            }
            httpClient.setConnectTimeout(5000);
            httpClient.setReadTimeout(5000);
            StringBuilder response = new StringBuilder();
            boolean isError = (httpClient.getResponseCode() >= 400);
            InputStream inputStream = isError ? httpClient.getErrorStream() : httpClient.getInputStream();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            return new ExecuteUrlResponseDto(httpClient.getResponseCode(), response.toString());
        } catch (Exception e) {
            throw new Exception("Error no controlado en ConsumoWsUtil.executeUrl, causado por: ", e);
        } finally {
            assert httpClient != null;
            httpClient.disconnect();
        }
    }

    /**
     * Ejecuta un endpoint de un WS SOAP POST
     *
     * @param endPoint       Recurso a ejecutar
     * @param requestBodyXml Body de la petición en XML
     * @return El objeto ExecuteUrlResponseDto con el código http y el cuerpo de la respuesta a la petición
     * @throws Exception Causado por error no controlado al llamar al WS
     */
    public static ExecuteUrlResponseDto executeSoapPostResource(String endPoint, String requestBodyXml) throws Exception {
        ExecuteUrlRequestDto requestDto = new ExecuteUrlRequestDto();
        requestDto.setUrl(endPoint);
        requestDto.setContentType("SOAP");
        requestDto.setHttpMethod(HttpMethod.POST);
        requestDto.setSoap(Boolean.TRUE);
        requestDto.setBody(requestBodyXml);
        return HttpCustomUtils.executeUrl(requestDto);
    }
    
    private static String joiningParameters(HashMap<String, ?> parameters) {
        return parameters
                .entrySet()
                .stream()
                .map(p -> new StringBuilder().append(p.getKey()).append("=").append(p.getValue()))
                .collect(Collectors.joining("&"));
    }

    /**
     * Agrega el chart set estándar UTF-8 al content type para enviarlo en la cabecera
     */
    private static String getFullContentType(String contentType) {
        switch (contentType) {
            case MediaType.APPLICATION_JSON:
            case MediaType.APPLICATION_XML:
            case "SOAP":
                return "application/soap+xml; " + MediaType.CHARSET_PARAMETER + "=" + StandardCharsets.UTF_8.name();
            default:
                return contentType;
        }
    }

}
