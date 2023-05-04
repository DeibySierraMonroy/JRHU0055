package co.com.activos.jrhu0055.config;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

public class NamespaceRemoverHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        try {
            SOAPMessage message = context.getMessage();
            message.getSOAPPart().getEnvelope().removeNamespaceDeclaration("ns1");
            message.getSOAPBody().removeNamespaceDeclaration("ns2");
            return true;
        } catch (SOAPException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
