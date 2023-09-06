package co.com.activos.jrhu0055.utiliti;

public class ErrorAplicacion  extends  RuntimeException{
    private final String codigo;


    public ErrorAplicacion(String codigo) {
        this.codigo = codigo;
    }
    public ErrorAplicacion(String mensaje, String codigo) {
        super(mensaje);
        this.codigo = codigo;
    }
    public String getCodigo() {
        return codigo;
    }
}
