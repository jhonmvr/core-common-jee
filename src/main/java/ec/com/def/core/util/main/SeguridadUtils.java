package ec.com.def.core.util.main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import ec.com.def.core.exception.DefException;

public class SeguridadUtils {
	
	private SeguridadUtils() {}
	
	public static void main(String args[]) {
		try {
			System.out.println( generatePassword("1234") );
		} catch (DefException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo encargado de encriptar el passwors
	 * @param password
	 * @return
	 * @throws DefException
	 */
	public static String generatePassword(String password) throws DefException{
			return getSecurePassword(password, getSalt());
	}
	
	/**
	 * Genera el password en MD5
	 * {@code
	  		// Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
	 * }
	 * @param passwordToHash password a encriptar
	 * @param salt generado
	 * @return Cadena encriptada
	 */
	public static String getSecurePassword(String passwordToHash, byte[] salt) throws DefException
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
        	throw new DefException(Constantes.ERROR_CODE_CUSTOM, "ERROR EN LA GENERACION DE SECURE PASSWORD");
        }
        return generatedPassword;
    }
	
	/**
	 * Metodo que genera SALT In cryptography, a salt is random data that is used as an additional input to a one-way function that "hashes" data, 
	 * a password or passphrase. Salts are closely related to the concept of nonce. The primary function of salts is to defend against dictionary 
	 * attacks or against its hashed equivalent, a pre-computed rainbow table attack.
	 * Salts are used to safeguard passwords in storage. 
	 * Historically a password was stored in plaintext on a system, but over time additional safeguards developed to protect a user's password 
	 * against being read from the system. A salt is one of those methods.
	 * 
	 * @return Arreglo de bytes con el algoritmo implementado
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] getSalt() throws DefException
    {
        try {
        	return Constantes.SECURITY_SALT.getBytes();
		} catch (Exception e) {
			throw new DefException(Constantes.ERROR_CODE_CUSTOM, "ERROR EN LA GENERACION DE SALT PARA PASSWORD");
		}
    }
    
    public static String generateMD5HashFromString(String toHash) throws DefException{
      try{
        byte[] bytesOfMessage = toHash.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
				md.update( bytesOfMessage );
				byte[] thedigest = md.digest();
				return DatatypeConverter.printHexBinary(thedigest).toUpperCase();
      } catch (UnsupportedEncodingException e) {
				throw new DefException(Constantes.ERROR_CODE_CUSTOM, "ERROR UnsupportedEncodingException EN LA GENERACION DE HASH MD5");
			} catch (NoSuchAlgorithmException e) {
			   throw new DefException(Constantes.ERROR_CODE_CUSTOM, "ERROR NoSuchAlgorithmException EN LA GENERACION DE HASH MD5");
			}
    }

}
