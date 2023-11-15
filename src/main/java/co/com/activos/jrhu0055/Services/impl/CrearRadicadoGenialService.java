/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.activos.jrhu0055.Services.impl;

import co.com.activos.jrhu0055.Services.IincapacidadService;
import co.com.activos.jrhu0055.model.InformacionTaxonomia;
import co.com.activos.jrhu0055.model.Taxonomia;
import co.com.activos.jrhu0055.utiliti.RespuestaGenerica;
import static co.com.activos.jrhu0055.utiliti.ServicioRest.crearTaxonomiaIncapacidades;
import co.com.activos.jrhu0055.utiliti.TipoRespuesta;
import javax.inject.Inject;

/**
 *
 * @author egacha
 */
public class CrearRadicadoGenialService {

    private static final String _FLUJO = "DOCUMENTOS INCAPACIDADES";
    private static final String _TIPO_FLUJO = "9";
    private static final String _TIPO_DOCUMENTO = "A";
    private static final String _EXT_DOCUMENTO = ".PDF";
    private static final long _DEA_CODIGO_PADRE = 18648723;
    private static final String _AZ_CODIGO_PADRE = "1153181";
    private static final String _TIPO = "C";

    @Inject
    private IincapacidadService incapacidadService;

    /**
     * Valida si existe la carpeta del trabajador de lo contratio invoca el
     * método crearTaxonomiaGenial()
     *
     * @param tipoDocumento
     * @param numeroDocumento
     * @return
     */
    public RespuestaGenerica<InformacionTaxonomia> validarTaxonomiaGenial(String tipoDocumento, long numeroDocumento, String numeroContrato, String numeroRadicado) {
        InformacionTaxonomia informacionTaxonomia = new InformacionTaxonomia();

        try {
            informacionTaxonomia = incapacidadService.buscarTaxonomiaGenial(tipoDocumento, numeroDocumento, contrato y radicado ,_DEA_CODIGO_PADRE);

            /**
             * Se crea la carpeta del trabajador con su cedula y queda CC
             * numeroDeCedula
             */
            if (!informacionTaxonomia.isEstado()) {
                Taxonomia informacionCrearTaxonomia = new Taxonomia(_TIPO, _AZ_CODIGO_PADRE, tipoDocumento + " " + numeroDocumento, String.valueOf(numeroDocumento), _FLUJO, Long.toString(_DEA_CODIGO_PADRE));

                informacionTaxonomia = crearTaxonomiaGenial(informacionCrearTaxonomia);

                /**
                 *
                 */
                if (!informacionTaxonomia.isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Taxonomina empleado no creada", informacionTaxonomia);
                }
                
                
                informacionCrearTaxonomia = new Taxonomia(_TIPO, informacionTaxonomia.getIdAzDigital(), numeroContrato, String.valueOf(numeroDocumento), _FLUJO, informacionTaxonomia.getIdDeaCodigo());
                informacionTaxonomia = crearTaxonomiaGenial(informacionCrearTaxonomia);
                
                /**
                 *
                 */
                if (!informacionTaxonomia.isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Taxonomina contrato no creada", informacionTaxonomia);
                }
                
                
                informacionCrearTaxonomia = new Taxonomia(_TIPO, informacionTaxonomia.getIdAzDigital(), numeroRadicado, String.valueOf(numeroDocumento), _FLUJO, informacionTaxonomia.getIdDeaCodigo());
                informacionTaxonomia = crearTaxonomiaGenial(informacionCrearTaxonomia);
                
                 /**
                 *
                 */
                if (!informacionTaxonomia.isEstado()) {
                    return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Taxonomina radicado no creada", informacionTaxonomia);
                }
            }
        } catch (RuntimeException exception) {
            return new RespuestaGenerica<>(TipoRespuesta.ERROR, "Error al crear la taxonomia" + exception.getLocalizedMessage());
        }
        return new RespuestaGenerica<>(TipoRespuesta.SUCCESS, "Taxonomina creada", informacionTaxonomia);

    }

    /**
     * Crea la carpeta del trabajador en AZ digital
     *
     * @param tipoDocumento
     * @param numeroDocumento
     * @return
     */
    private InformacionTaxonomia crearTaxonomiaGenial(Taxonomia carpetaTaxonomia) {
        InformacionTaxonomia informacionTaxonomia = new InformacionTaxonomia();
        RespuestaGenerica respuestaServicio = crearTaxonomiaIncapacidades(carpetaTaxonomia);
        if (respuestaServicio.getBody() != null && !respuestaServicio.getBody().contains("ERROR")) {
            String azCodigoCli = respuestaServicio.getBody().split("azCodigoCli=")[1].split(",")[0];
            String deaCodigo = respuestaServicio.getBody().split("deaCodigo=")[1].split("}")[0];
            informacionTaxonomia.setEstado(true);
            informacionTaxonomia.setIdAzDigital(azCodigoCli);
            informacionTaxonomia.setIdDeaCodigo(deaCodigo);
            return informacionTaxonomia;
        }
        informacionTaxonomia.setEstado(false);
        return informacionTaxonomia;
    }

}
