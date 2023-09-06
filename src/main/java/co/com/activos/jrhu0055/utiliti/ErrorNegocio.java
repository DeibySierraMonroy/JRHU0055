package co.com.activos.jrhu0055.utiliti;

import java.util.function.Supplier;

public class ErrorNegocio extends ErrorAplicacion {

    public enum Tipo {

        PARAMETRO_VACIO("Revise que el parametro no esta vacio.");

        private final String mensaje;

        public String getMensaje() {
            return mensaje;
        }

        public ErrorNegocio build() {
            return new ErrorNegocio(this);
        }

        public Supplier<Throwable> defer() {
            return () -> new ErrorNegocio(this);
        }

        Tipo(String mensaje) {
            this.mensaje = mensaje;
        }
    }

    private final Tipo tipo;

    public ErrorNegocio(Tipo tipo){
        super(tipo.mensaje);
        this.tipo = tipo;
    }

    public ErrorNegocio(Tipo tipo, String message) {
        super(message);
        this.tipo = tipo;
    }

    @Override
    public String getCodigo(){
        return tipo.name();
    }
}
