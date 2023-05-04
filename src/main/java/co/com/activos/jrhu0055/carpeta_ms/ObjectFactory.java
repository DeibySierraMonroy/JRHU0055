
package co.com.activos.jrhu0055.carpeta_ms;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri.carpeta_ms package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Estado_QNAME = new QName("http://tempuri.org/carpeta_ms", "estado");
    private final static QName _IdCarpetaColeccion_QNAME = new QName("http://tempuri.org/carpeta_ms", "idCarpetaColeccion");
    private final static QName _FaultCode_QNAME = new QName("http://tempuri.org/carpeta_ms", "faultCode");
    private final static QName _ReqCrear_QNAME = new QName("http://tempuri.org/carpeta_ms", "ReqCrear");
    private final static QName _IdCarpetaPadre_QNAME = new QName("http://tempuri.org/carpeta_ms", "idCarpetaPadre");
    private final static QName _FaultMessage_QNAME = new QName("http://tempuri.org/carpeta_ms", "faultMessage");
    private final static QName _ResCrear_QNAME = new QName("http://tempuri.org/carpeta_ms", "ResCrear");
    private final static QName _IdCarpetaNueva_QNAME = new QName("http://tempuri.org/carpeta_ms", "idCarpetaNueva");
    private final static QName _ErrorMessage_QNAME = new QName("http://tempuri.org/carpeta_ms", "ErrorMessage");
    private final static QName _Nombre_QNAME = new QName("http://tempuri.org/carpeta_ms", "nombre");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri.carpeta_ms
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReqCrear }
     * 
     */
    public ReqCrear createReqCrear() {
        return new ReqCrear();
    }

    /**
     * Create an instance of {@link ErrorMessage }
     * 
     */
    public ErrorMessage createErrorMessage() {
        return new ErrorMessage();
    }

    /**
     * Create an instance of {@link ResCrear }
     * 
     */
    public ResCrear createResCrear() {
        return new ResCrear();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/carpeta_ms", name = "estado")
    public JAXBElement<String> createEstado(String value) {
        return new JAXBElement<String>(_Estado_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/carpeta_ms", name = "idCarpetaColeccion")
    public JAXBElement<String> createIdCarpetaColeccion(String value) {
        return new JAXBElement<String>(_IdCarpetaColeccion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/carpeta_ms", name = "faultCode")
    public JAXBElement<String> createFaultCode(String value) {
        return new JAXBElement<String>(_FaultCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReqCrear }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/carpeta_ms", name = "ReqCrear")
    public JAXBElement<ReqCrear> createReqCrear(ReqCrear value) {
        return new JAXBElement<ReqCrear>(_ReqCrear_QNAME, ReqCrear.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/carpeta_ms", name = "idCarpetaPadre")
    public JAXBElement<BigInteger> createIdCarpetaPadre(BigInteger value) {
        return new JAXBElement<BigInteger>(_IdCarpetaPadre_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/carpeta_ms", name = "faultMessage")
    public JAXBElement<String> createFaultMessage(String value) {
        return new JAXBElement<String>(_FaultMessage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResCrear }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/carpeta_ms", name = "ResCrear")
    public JAXBElement<ResCrear> createResCrear(ResCrear value) {
        return new JAXBElement<ResCrear>(_ResCrear_QNAME, ResCrear.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/carpeta_ms", name = "idCarpetaNueva")
    public JAXBElement<String> createIdCarpetaNueva(String value) {
        return new JAXBElement<String>(_IdCarpetaNueva_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/carpeta_ms", name = "ErrorMessage")
    public JAXBElement<ErrorMessage> createErrorMessage(ErrorMessage value) {
        return new JAXBElement<ErrorMessage>(_ErrorMessage_QNAME, ErrorMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/carpeta_ms", name = "nombre")
    public JAXBElement<String> createNombre(String value) {
        return new JAXBElement<String>(_Nombre_QNAME, String.class, null, value);
    }

}
