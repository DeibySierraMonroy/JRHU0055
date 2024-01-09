package co.com.activos.jrhu0055.utiliti;

public enum CodigoError {
    
    // inc = Incapacidad 
    // con = conexion
    // az = az_digital (Proveedor, Tercero)
    // ft = flujo tercero / proveedor
    // 01 = indica el codigo del flujo
    // val = validaciones
    // arc = archivo
    // fp = flujo principal
    // errn = error de negocio
    // errp = error de aplicacion

    
    INC_CON_AZ_F01("FO1 : Error consumiendo el servicio de Az_Digital , Servicio caido o intermitente"),
    INC_CON_AZ_FO2("Error Consumiendo servicio en ServicioRest:crearTaxonomiaIncapacidades debido a :"),
    INC_VAL_ARC_FPO1_ERRN(" El tamaño de algún archivo supera los 10MB");

 
    private final String descripcion;
   

    private CodigoError(String descripcion ) {
        this.descripcion = descripcion;
      
    } 

    public String getDescripcion() {
        return descripcion;
    }   

    
}

