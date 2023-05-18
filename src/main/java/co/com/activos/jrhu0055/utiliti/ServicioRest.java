package co.com.activos.jrhu0055.utiliti;

import co.com.activos.jrhu0055.model.InformacionTaxonomia;
import co.com.activos.jrhu0055.model.Taxonomia;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ServicioRest {
    private static final String _URL = "http://eure.activos.com.co:10501/JADM0056/api/rest/document/manager/create";
    public static RespuestaGenerica<InformacionTaxonomia>  crearTaxonomiaIncapacidades(Taxonomia taxonomia) {
        String responseMessage;
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder reponse = new StringBuilder();
        try {
            URL url = new URL(_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setDoOutput(true);
            String params = mapper.writeValueAsString(taxonomia);
            try (DataOutputStream os = new DataOutputStream(conn.getOutputStream())) {
                os.write(params.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }
            responseMessage = conn.getResponseMessage();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            while ((output = br.readLine()) != null) {
                reponse.append(output);
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return new RespuestaGenerica<>(TipoRespuesta.ERROR,
                    "Error Consumiendo servicio en ServicioRest:crearTaxonomiaIncapacidades debido a :" + e.getMessage());
        }
        return obtenerRespuestaServicio(responseMessage, reponse.toString());
    } 

    private static RespuestaGenerica<InformacionTaxonomia> obtenerRespuestaServicio(String repuesta, String body) {
        InformacionTaxonomia informacionTaxonomia = new InformacionTaxonomia();
        try {
            if (body != null  &&  !(body.contains("ERROR"))) {
                String azCodigoCli = body.split("azCodigoCli=")[1].split(",")[0];
                String deaCodigo   = body.split("deaCodigo=")[1].split("}")[0];
                informacionTaxonomia.setEstado(true);
                informacionTaxonomia.setIdAzDigital(azCodigoCli);
                informacionTaxonomia.setIdDeaCodigo(deaCodigo);
                return new RespuestaGenerica<>(TipoRespuesta.SUCCESS,"Ok" , informacionTaxonomia);
            }
            return new RespuestaGenerica(TipoRespuesta.ERROR,"Error al Obtener la respuesta debido a que le servicio respondio " + body
            ,informacionTaxonomia);
        }catch (RuntimeException e){
            return new RespuestaGenerica(TipoRespuesta.ERROR,"Error al construir la respuesta debido a : " + e.getMessage());
        }
    }


}