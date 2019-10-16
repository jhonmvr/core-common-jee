package com.relative.core.persistence;

import com.relative.core.exception.RelativeException;

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
	 * @throws RelativeException
	 */
	public E add(E entity)  throws RelativeException ;

	/**
	 * Remueve la entidad enviada
	 * @param entity entidad a eliminar
	 * @throws RelativeException
	 */
	public void remove(E entity) throws RelativeException ;

	/**
	 * Modifica la entidad enviada
	 * @param entity entidada  modificar
	 * @return retorna la entidad modificada
	 * @throws RelativeException
	 */
	public E update(E entity) throws RelativeException ;
	
	/**
	 * Metodo que permite un registro batch de una pila iterable
	 * @param entities entidades a agregar
	 * @throws RelativeException
	 */
	public void add(Iterable<E> entities) throws RelativeException ;

	
	
	
	
	


}
