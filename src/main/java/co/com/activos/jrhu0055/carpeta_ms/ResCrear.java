
package co.com.activos.jrhu0055.carpeta_ms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResCrear complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResCrear">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://tempuri.org/carpeta_ms}idCarpetaNueva"/>
 *         &lt;element ref="{http://tempuri.org/carpeta_ms}idCarpetaColeccion"/>
 *         &lt;element ref="{http://tempuri.org/carpeta_ms}estado"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResCrear", propOrder = {
    "idCarpetaNueva",
    "idCarpetaColeccion",
    "estado"
})
public class ResCrear {

    @XmlElement(required = true)
    protected String idCarpetaNueva;
    @XmlElement(required = true)
    protected String idCarpetaColeccion;
    @XmlElement(required = true)
    protected String estado;

    /**
     * Gets the value of the idCarpetaNueva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCarpetaNueva() {
        return idCarpetaNueva;
    }

    /**
     * Sets the value of the idCarpetaNueva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCarpetaNueva(String value) {
        this.idCarpetaNueva = value;
    }

    /**
     * Gets the value of the idCarpetaColeccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCarpetaColeccion() {
        return idCarpetaColeccion;
    }

    /**
     * Sets the value of the idCarpetaColeccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCarpetaColeccion(String value) {
        this.idCarpetaColeccion = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

}
