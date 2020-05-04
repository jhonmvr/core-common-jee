package ec.com.def.core.exception;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.ApplicationException;

/**
 * Implementacion de Excepcion personalizada
 * @author Alex Guaman
 */
@ApplicationException(rollback = true)
public class DefException extends Exception {

    private static final long serialVersionUID = 3239880912882496024L;
    
    // Mensaje general error
    private String mensaje;
    
    // Mensaje detallado del error
    private String detalle;
    
    // Mensaje de Usuario
    private String mensajeUsuario;
    
    private StackTraceElement informacionClase;
    private StackTraceElement[] pilaMetodosLlamado;
    private List<String> mensajesError;

    /**
     * Constructor por defecto
     */
    public DefException() {
        super();
        this.mensaje="";
        this.detalle="";
        this.mensajeUsuario="";
        this.informacionClase=null;
        this.pilaMetodosLlamado = null;
        this.mensajesError = new ArrayList<>();
    }


    /**
     * @param mensaje Mensaje de error
     */
    public DefException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
        insertarError();
    }

    public DefException(String mensaje, String detalle) {
    	super(detalle);
    	this.mensaje = mensaje;
        this.detalle = detalle;        
        insertarError();
    }

    /**
     * <b> Crea un error detectado en la forma de exception (mensaje, exception)
     * Cuando recibe solo (exception) omite la insercion. </b>
     * 
     */
    private void insertarError() {
        String msg;
        Throwable tr = this;

        // Obtener el mensaje del programador
        this.mensajeUsuario = tr.getMessage();

        // Obtener informacion acerca de la clase en la cual se produjo la excepcion
        this.pilaMetodosLlamado = tr.getStackTrace();

        while (tr.getCause() != null) {
            tr = tr.getCause();
        }

        msg = tr.getMessage();

        if (msg != null && msg.length() > 0) {
            this.mensaje = msg;
        } else {
        	this.mensaje = tr.getClass().getName();
        }
    }

    /**
     * <b> Devuelve la descripcion del error escrita por el programador al
     * momento de crear la excepcion. </b>
     *
     * @return Mensaje de Usuario
     */
    public String getMensajeUsuario() {
        return mensajeUsuario;
    }
    
    /**
     * <b> Devuleve el detalle del error escrita por el programador al
     * momento de crear la excepcion. </b>
     * @return
     */
    public String getDetalle() {
        if (detalle == null) {
        	return super.getMessage();
        } else {
        	return detalle;
        }
    }

    @Override
    public String getMessage() {
        if (mensaje == null) {
            return super.getMessage();
        } else {

            return mensaje;
        }
    }

    /**
     * <b> Devuelve toda la pila de excepcciones. </b>
     *
     * @return Lista de excepxiones
     */
    private List<String> getErrorMessageList() {
        // Obtener el objeto Throwable de la excepcion en la cual se produjo la excepcion
        Throwable throwableExcepcionOriginal = this;
        while (throwableExcepcionOriginal.getCause() != null) {
            throwableExcepcionOriginal = throwableExcepcionOriginal.getCause();
        }
        // Obtener informacion acerca de la clase en la cual se produjo la excepcion
        for (StackTraceElement stackTraceElement : throwableExcepcionOriginal.getStackTrace()) {
            if (stackTraceElement.getFileName() != null
                    && stackTraceElement.getFileName().equals(pilaMetodosLlamado[0].getFileName())) {
                this.informacionClase = stackTraceElement;
                break;
            }
        }
        mensajesError.add("Causa ==> " + mensaje);
        mensajesError.add("Clase ==> " + informacionClase.getFileName());
        mensajesError.add("Metodo ==> " + informacionClase.getMethodName());
        mensajesError.add("Linea ==> " + informacionClase.getLineNumber());
        mensajesError.add(".: Por favor capture la pantalla y enviela al Administrador del Sistema. Gracias");
        return mensajesError;
    }

    /**
     * <b> Retorna el mensaje tecnico. </b>
     *
     * @return Mensaje tecnico
     */
    public String getMensajeTecnico() {
        StringBuilder mensajeToString = new StringBuilder(80);
        getErrorMessageList();
        if (!mensajesError.isEmpty()) {
            mensajeToString.append('\n');
            mensajeToString.append("Causa: ");
            mensajeToString.append(mensaje);
            mensajeToString.append('\n');
            mensajeToString.append("Clase: ");
            mensajeToString.append(informacionClase.getClassName());
            mensajeToString.append('\n');
            mensajeToString.append("Nombre Archivo: ");
            mensajeToString.append(informacionClase.getFileName());
            mensajeToString.append('\n');
            mensajeToString.append("Metodo: ");
            mensajeToString.append(informacionClase.getMethodName());
            mensajeToString.append('\n');
            mensajeToString.append("Linea: ");
            mensajeToString.append(informacionClase.getLineNumber());
        }
        return mensajeToString.toString();
    }

	public String getMensaje() {
		return mensaje;
	}

	public StackTraceElement getInformacionClase() {
		return informacionClase;
	}

	public StackTraceElement[] getPilaMetodosLlamado() {
		return pilaMetodosLlamado;
	}

	public void setPilaMetodosLlamado(StackTraceElement[] pilaMetodosLlamado) {
		this.pilaMetodosLlamado = pilaMetodosLlamado;
	}

	public List<String> getMensajesError() {
		return mensajesError;
	}
    
}
