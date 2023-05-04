package co.com.activos.jrhu0055.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Fault", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
public class SoapFault {

    @XmlElement(namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    private String faultcode;

    @XmlElement(namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    private String faultstring;

    @XmlElement(namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    private Detail detail;

    public String getFaultcode() {
        return faultcode;
    }

    public void setFaultcode(String faultcode) {
        this.faultcode = faultcode;
    }

    public String getFaultstring() {
        return faultstring;
    }

    public void setFaultstring(String faultstring) {
        this.faultstring = faultstring;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public static class Detail {

        @XmlElement(name = "ErrorMessage", namespace = "http://activos.com.co/cargar_archivo_ms")
        private ErrorMessage errorMessage;

        public ErrorMessage getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(ErrorMessage errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    public static class ErrorMessage {

        @XmlElement(namespace = "http://activos.com.co/cargar_archivo_ms")
        private String faultCode;

        @XmlElement(namespace = "http://activos.com.co/cargar_archivo_ms")
        private String faultMessage;

        public String getFaultCode() {
            return faultCode;
        }

        public void setFaultCode(String faultCode) {
            this.faultCode = faultCode;
        }

        public String getFaultMessage() {
            return faultMessage;
        }

        public void setFaultMessage(String faultMessage) {
            this.faultMessage = faultMessage;
        }
    }
}

