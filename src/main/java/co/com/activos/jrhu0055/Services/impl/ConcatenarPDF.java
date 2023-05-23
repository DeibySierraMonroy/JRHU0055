package co.com.activos.jrhu0055.Services.impl;

import co.com.activos.jrhu0055.Services.IincapacidadService;
import co.com.activos.jrhu0055.model.CargarAZArchivoMapper;
import co.com.activos.jrhu0055.model.CargarAZArchivoWrapper;
import co.com.activos.jrhu0055.model.ExecuteUrlResponseDto;
import co.com.activos.jrhu0055.utiliti.HttpCustomUtils;
import co.com.activos.jrhu0055.utiliti.RespuestaGenerica;
import co.com.activos.jrhu0055.utiliti.TipoRespuesta;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static co.com.activos.jrhu0055.utiliti.StringUtils.getStringMapValue;

/**
 *
 * @author desierra
 */
public class ConcatenarPDF {

    @Inject
    private IincapacidadService iincapacidadService;
    public static String documentoBase64;

    public  RespuestaGenerica<?> documentosAConcatenar(List<String> documentosAConcatenar){
        try {
          return ConcatenarPDF.unirPDF(documentosAConcatenar.stream()
                    .map(documento -> obtenerArchivo(documento).getObjeto())
                    .collect(Collectors.toList()));

        } catch (Exception e) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error no controlado al concatenar Los Documentos" + e.getMessage());
        }
    }

    public  RespuestaGenerica<String> obtenerArchivo(String codigoAz) {
        try {
            RespuestaGenerica<?> obtenerEndpointTramaAZResult = iincapacidadService.obtenerEndpointTramaCodAZ(codigoAz);
            String endPointaz = getStringMapValue(obtenerEndpointTramaAZResult.getListValues(), "endPoint");
            String requestBodyXmlaz = getStringMapValue(obtenerEndpointTramaAZResult.getListValues(), "requestBodyXml");
            ExecuteUrlResponseDto urlBase64Response = HttpCustomUtils.executeSoapPostResource(endPointaz, requestBodyXmlaz);
            CargarAZArchivoMapper cargarArchivoMapper = CargarAZArchivoWrapper.getMapper(urlBase64Response.getResponseBody());
            documentoBase64 = cargarArchivoMapper.getIdArchivoNuevo();
        } catch (Exception e) {
            e.printStackTrace();
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error no controlado al obtener el Archivo");

        }
        return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "El proceso finalizo correctamente", documentoBase64);
    }

    public static RespuestaGenerica<String> unirPDF(List<String> pdfs) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument destPdf = new PdfDocument(writer)) {
            for (String pdfBase64 : pdfs) {
                byte[] pdfBytes = Base64.getDecoder().decode(pdfBase64);
                try (PdfReader reader = new PdfReader(new ByteArrayInputStream(pdfBytes));
                     PdfDocument pdfCreado = new PdfDocument(reader)) {
                    pdfCreado.copyPagesTo(1, pdfCreado.getNumberOfPages(), destPdf);
                }
            }
        } catch (IOException exception) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error no controlado en unirPDF, causado por: " + exception.getMessage());
        }
        byte[] mergedPdfBytes = outputStream.toByteArray();
        String mergedPdfInBase64 = Base64.getEncoder().encodeToString(mergedPdfBytes);
        return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, mergedPdfInBase64);
    }


}