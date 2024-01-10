package co.com.activos.jrhu0055.utiliti;

public enum CodigoError {
    
    // inc = Incapacidad 
    // con = conexion
    // tax = taxonomia
    // az = az_digital (Proveedor, Tercero)
    // ft = flujo tercero / proveedor
    // 01 = indica el codigo del flujo
    // val = validaciones
    // arc = archivo
    // fp = flujo principal
    // cre = creacion.
    // car = carpeta.
    // cto = contrato.
    // errn = error de negocio
    // errp = error de aplicacion
    // cam = campos obligatorios.
    // doc = documento
    // rad = radicado
    // bd = bases de datos.
    // sub = subir
    // gen = generico
    // cos = construir
    // act = Actua;izar

    
    INC_CON_AZ_F01("FO1 : Error consumiendo el servicio de Az_Digital , Servicio caido o intermitente"),
    INC_CON_AZ_FO2("Error Consumiendo servicio en ServicioRest:crearTaxonomiaIncapacidades: (JRHU0056)"),
    INC_VAL_CAM("Error No se puede validar la taxonomia debido a que hay campos necesarios sin informacion"),
    INC_VAL_TAX("Error validando la taxonomia debido a :"),

    INC_VAL_ACT_O_CRE_DOC("Error en actualizar el documento en IincapacidaesRepo::actualizarEstadoDocumento debido a : "),
    INC_VAL_CRE_O_COS_RAD("No se puede procesar el radicado , error al construirlo"),
    INC_VAL_CAM_RAD("Error No se puede procesar el radicado , revise la informacion a radicar"),
    INC_VAL_GEN("Error No se puede procesar el radicado debido a :"),
    INC_VAL_SUB_DOC("Error no contralado en : IncapacidadService:subirDocumentos debido a :"),
    INV_VAL_CRE_TAX("Error al Crear la taxonomia en : IncapacidadService:crearTaxonomia debido a : "),
    INC_VAL_CRE_RES("Error al construir la respuesta debido a "),
    INC_VAL_CRE_RAD_BD ("Error al crear el radicado en IincapacidaesRepo::crearRadicado  debido a : "),
    INC_VAL_CRE_CAR_DOC(" El documento no se puede modificar ya que el radicado no cuenta con una carpeta."),
    INC_VAL_ARC_FPO1_ERRN(" El tamaño de algún archivo supera los 10MB");

 
    private final String descripcion;
   

    private CodigoError(String descripcion ) {
        this.descripcion = descripcion;
      
    } 

    public String getDescripcion() {
        return descripcion;
    }   

    
}

