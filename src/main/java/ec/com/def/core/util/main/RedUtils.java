package ec.com.def.core.util.main;

import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase utilitaria para obtener direcciones de red
 * @author Alex Guaman
 */
public class RedUtils {
	
	private RedUtils(){
		
	}
	
	/**
	 * Obtiene la ip del host
	 * @return
	 */
    public static String getIp(){
        try {
            return java.net.InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
        	Logger.getLogger(RedUtils.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return "";
    }
}
