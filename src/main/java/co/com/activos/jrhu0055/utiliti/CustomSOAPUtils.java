package co.com.activos.jrhu0055.utiliti;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * Utilidades para obtener info de la respuesta al llamado de un servicio SOAP
 *
 * @author Francisco Javier Rincon (nvalue)
 * @version 1.0
 * @since JDK 1.8
 */
public class CustomSOAPUtils {

    private static final int ZERO = 0;
    private static final int ONE = 1;

    public static Document loadResponse(String response) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(response));
        return documentBuilder.parse(inputSource);
    }

    public static String getNodeSingleValue(String response, String tagName) throws Exception {
        Document document = loadResponse(response);
        NodeList nodeList = document.getElementsByTagName(tagName);
        if (nodeList.getLength() == ONE) {
            Node node = nodeList.item(ZERO);
            return node.getTextContent();
        }
        return null;
    }

}
