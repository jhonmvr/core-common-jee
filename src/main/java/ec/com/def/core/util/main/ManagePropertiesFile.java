package ec.com.def.core.util.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import ec.com.def.core.exception.DefException;


/**
 * Clase que administra los archivos de propiedades y configuracion.
 * @author LUIS TAMAYO -RELATIVE ENGINE
 *
 */
public class ManagePropertiesFile {
	
	
	/**
	 * Metodo que registra la informacion del xml actualizado.
	 * @param properties listado de propiedades a actualizar
	 * @param fileName nombre del archivo a actualizar
	 * @throws Exception 
	 */
	public static void saveProperties( Map<String,String> properties, String fileName, String pathName ) throws DefException{
		try {
			FileOutputStream out = null;
			Properties prop = loadProperties( fileName,pathName );
			if( properties != null  ){
				Set<String> keys = properties.keySet();
				for( String key : keys  ){
					prop.remove(key);
					prop.put(key, properties.get(key) );
				}
			}
			out = new FileOutputStream(pathName + File.separator + fileName);
			prop.storeToXML(out, "ACTUALIZACION XML DE PROPIEDADES DESE APLICACION");
			out.close();
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "ERROR EN CARGA DEL ARCHIVO DE PROPIEDADES " + e.getMessage());
		}
	}
	
	/**
	 * Metodo que convierte el objeto Properties a un Map<String, String> sencillo
	 * @param fileName Archivo a cargar
	 * @return Mapa de propiedades
	 * @throws Exception
	 */
	public static Map<String,String> convertPropertiesToMap( String fileName, String pathName ) throws DefException{
		try {
			Map<String, String> local = new HashMap<>();
			Properties prop = loadProperties( fileName, pathName);
			Enumeration<Object> keys=prop.keys();
			while(keys.hasMoreElements()){
				String key = (String) keys.nextElement();
				local.put( key , prop.getProperty( key ));
			}
			return local;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "ERROR EN CARGA DEL ARCHIVO DE PROPIEDADES Y CONVERSION A MAP " + e.getMessage());
		}
	}
	
	/**
	 * Carga el arhivo de propiedades para su uso
	 * @param fileName Nombre del archivo de propiedades
	 * @param pathName Nombre del path donde se encuentra el archivo de propiedades
	 * @return Archivo de propiedades guardado 
	 * @throws Exception
	 */
	public static Properties loadProperties( String fileName, String pathName ) throws DefException{
		Properties prop = new Properties();
		try {
			InputStream in = null;
			in = new FileInputStream(pathName + File.separator + fileName);
			prop.load(in);
			in.close();
			return prop;
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"ERROR EN CARGA DEL ARCHIVO DE PROPIEDADES PATH " + pathName + " " + fileName +" " + e.getMessage());
		}
	}
	
	/**
	 * Metodo que reescribe un archivo de propiedades con las propiedades enviadas
	 * @param prop      Objeto con las propiedades a escribir
	 * @param fileName  Nombre del archivo de propiedades
	 * @param comments  Comentarios agregados al archivo
	 * @param pathPropFile  path al archivo de propiedades en caso de que no se desee utilizar la configracion por defecto
	 * @throws Exception
	 */
	public static void writeProperties( Properties prop,String fileName, String comments, String pathPropFile  ) throws DefException{
		try {
			FileOutputStream out =null;
			out = new FileOutputStream(pathPropFile  + fileName);
			prop.store(out, comments);
			out.close();
		}catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM,"ERROR LA ESCRITURA DE PROPIEDADES PATH " + pathPropFile + " " + fileName +" " + e.getMessage());
		} 
	}
	
	/**
	 * {@code
	 * try {
			Properties prop = ManagePropertiesFile.loadProperties("","");
			FileOutputStream fos = new FileOutputStream("c:/relfe/config/sriintegration.xml");
			prop.storeToXML(fos, "UPDATE PROPERTIE FILE");
			fos.close();
			
		} catch (Exception e) {
			System.err.println("ERROR " + e.getMessage());
		}
	 * }
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		//not implemented
	}

}
