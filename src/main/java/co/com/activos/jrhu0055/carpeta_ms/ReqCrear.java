
package co.com.activos.jrhu0055.carpeta_ms;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReqCrear complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReqCrear">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://tempuri.org/carpeta_ms}idCarpetaPadre"/>
 *         &lt;element ref="{http://tempuri.org/carpeta_ms}nombre"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReqCrear", propOrder = {
    "idCarpetaPadre",
    "nombre"
})
public class ReqCrear {

    @XmlElement(required = true)
    @XmlSchemaType(name = "unsignedLong")
    protected BigInteger idCarpetaPadre;
    @XmlElement(required = true)
    protected String nombre;

    /**
     * Gets the value of the idCarpetaPadre property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdCarpetaPadre() {
        return idCarpetaPadre;
    }

    /**
     * Sets the value of the idCarpetaPadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdCarpetaPadre(BigInteger value) {
        this.idCarpetaPadre = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

}
