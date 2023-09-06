package co.com.activos.jrhu0055.utiliti;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import javax.ws.rs.core.Response;

/**
 * Utilidades para el manejo de cadenas de texto
 *
 * @author Francisco Javier Rincon (nvalue)
 * @version 1.0
 * @since JDK 1.8
 */
public class StringUtils {

    public static boolean anyItemIsNullOrEmpty(String... value) {
        return Arrays.stream(value).anyMatch(it -> it == null || it.isEmpty());
    }

    public static boolean isNullOrValue(String value, String compareValue) {
        return value != null && value.equals(compareValue);
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static String getStringMapValue(Map<?, ?> listValues, String key) {
        Optional<?> optional = listValues.entrySet()
                .stream().filter(i -> key.equals(i.getKey())).map(Map.Entry::getValue).findFirst();
        return optional.map(Object::toString).orElse(null);
    }

}
