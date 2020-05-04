package ec.com.def.core.persistence;

import ec.com.def.core.exception.DefException;

/**
 * INTERFACE GENERICA PARA ACCESO A DATOS
 * @author RELATIVE ENGINE - LUIS TAMAYO
 *
 * @param <K> KEY INDENTIFICADOR DE LA ENTIDAD
 * @param <E> ENTIDAD SOBRE LA QUE SE ACCESA A DATOS
 */

public interface Repository<K,E>  {
	
	/**
	 * Persiste la entidad enviada
	 * @param entity entidad a registrar
	 * @return entidad registrada
	 * @throws DefException
	 */
	public E add(E entity)  throws DefException ;

	/**
	 * Remueve la entidad enviada
	 * @param entity entidad a eliminar
	 * @throws DefException
	 */
	public void remove(E entity) throws DefException ;

	/**
	 * Modifica la entidad enviada
	 * @param entity entidada  modificar
	 * @return retorna la entidad modificada
	 * @throws DefException
	 */
	public E update(E entity) throws DefException ;
	
	/**
	 * Metodo que permite un registro batch de una pila iterable
	 * @param entities entidades a agregar
	 * @throws DefException
	 */
	public void add(Iterable<E> entities) throws DefException ;

	
	
	
	
	


}
