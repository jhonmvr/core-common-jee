package com.relative.core.persistence;

import java.util.List;
import java.util.Map;

import com.relative.core.exception.RelativeException;

public interface CrudRepository<K,E> extends Repository<K,E>{
	
	
	/**
	 * Busca la entidad por ID
	 * @param id Identificador de la entidad a obtener
	 * @return Entidad encontrada
	 * @throws RelativeException
	 */
	public E findById(K id) throws RelativeException ;
	
	/**
	 * Metodo que busca todo los registros
	 * @param e entidad a la que se va a buscar
	 * @return Cantidad de registros obtenidos
	 * @throws RelativeException
	 */
	public Long countAll( Class<E> e ) throws RelativeException ;
  
 
	
	/**
	 * Metodo q carga todos las entidades encontradas
	 * @param e entidad de la cual se desea obtener la lista
	 * @return Listado de entidades encontradas
	 * @throws RelativeException
	 */
	public List<E> findAll( Class<E> e ) throws RelativeException;
	
	/**
	 * Metodo generico que carga la informacion de todos los datos asociados a la entidad 
	 * @param c Clase relacionada al objeto
	 * @param order ordenamiento
	 * @param direction direccion de ordenamiento
	 * @return Lista de entidades enconctradas
	 */
	public List<E> findAll(Class<E> c,String order, String direction) throws RelativeException ;
	
	/**
	 * Metodo generico que carga la informacion de todos los datos asociados a la entidad paginado
	 * @param c Clase relacionada al objeto
	 * @param firstResult primer registro desde cual cual buscar
	 * @param maxResults maximo de registros a mostrar
	 * @param order ordenamiento 
	 * @param direction direccion de ordenamiento
	 * @return
	 */
	public List<E> findAll(Class<E> c,int firstResult, int maxResults, String order, String direction) throws RelativeException;
	
	/**
	 * Busca una lista de objetos en base a sus parametros actualizados
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws RelativeException
	 */
	public <E> List<E> getObjectsByQueryUpdated(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException;

	/**
	 * Busca una lista de objetos en base a sus parametros
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws RelativeException
	 */
	public <E> List<E> getObjectsByNamedQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException ;
	
	/**
	 * Busca una lista de objetos en base a sus parametros actualizados
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws RelativeException
	 */
	public <E> List<E> getObjectsByNamedQueryUpdated(String consulta, Map<String, Object> parametros, Class<E> entityClass) throws RelativeException;


	/**
	 * Busca una lista de cualquier objeto en base a sus parametros
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws RelativeException
	 */
	public <E> List<E> getCampoByNamedQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException ;

	/**
	 * Obtiene el primer registro
	 * 
	 * @param consulta
	 * @param parametros
	 * @return
	 * @throws RelativeException
	 */
	public <E> E getFirstObjectByNamedQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException;


	/**
	 * Ejecuta un namedQuery que siempre devuelva un objeto.
	 *
	 * @param namedQuery
	 * @param parametros parametros
	 * @return entidad resultante
	 * @throws RelativeException
	 */
	public <E> E getObjectByNamedQuery(String namedQuery, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException ;
	
	/**
	 * Ejecuta un namedQuery que siempre devuelva un objeto actualizado.
	 *
	 * @param namedQuery
	 * @param parametros parametros
	 * @return entidad resultante
	 * @throws RelativeException
	 */
	public <E> E getObjectByNamedQueryUpdated(String namedQuery, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException ;

	/**
	 * Buca un registro en base a sus parametros
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return entidad resultante
	 * @throws RelativeException
	 */
	public <E> E getObjectByQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException ;

	/**
	 *
	 * @param namedQuery
	 * @param parametros
	 * @return
	 * @throws RelativeException
	 */
	public <E> Object getMaxCountSumByNamedQuery(String namedQuery, Map<String, Object> parametros,Class<E> entityClass)	throws RelativeException ;

	/**
	 * Retorna un Integer que cumple con las condiciones de la consulta
	 *
	 * @param consulta nombre de la consulta a ejecutar
	 * @param parametros condiciones a ejecutar
	 * @return Integer producto de la consulta
	 * @throws RelativeException
	 */
	public <E> Integer getIntegerByNameQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException ;
	

	
	/**
	 * Ejecuta un namedQuery para realizar un Update.
	 *
	 * @param namedUpdateQuery, nombre del query que haga un update
	 * @param parametros
	 * @return
	 * @throws RelativeException
	 */
	public int ejecutarUpdate(String namedUpdateQuery, Map<String, Object> parametros) throws RelativeException ;
	
	/**
	 * Ejecuta un query nativo, se puede utilizar en llamadas a SP
	 *
	 * @param strQuery Consulta nativa
	 * @param parametros parametros en orden
	 * @return Lista de objetos
	 * @throws RelativeException
	 */
	public List<Object> ejecutarQueryNativo(String strQuery, Map<Integer, Object> parametros) throws RelativeException ;

	/**
	 * Ejecuta un query nativo
	 *
	 * @param strQuery Consulta nativa
	 * @param parametros parametros en orden
	 * @return numero de entidades afectadas
	 * @throws RelativeException
	 */
	public int ejecutarQueryNativoNoResult(String strQuery, Map<Integer, Object> parametros) throws RelativeException ;

	/**
	 * Ejecutar un query nativo que devuelve un solo valor sum, count, exist.
	 *
	 * @param strQuery
	 * @param parametros
	 * @return
	 * @throws RelativeException
	 */
	public Object ejecutarNamedNativeQuerySingleResult(String strQuery, Map<Integer, Object> parametros)
			throws RelativeException ;


	
	/**
	 * Metodo que generar un query en base a la especificacion de criterios enviados
	 * @param specification patron de especificacion de criterios
	 * @return Listado de entidades encontrados
	 */
	public <T> List<T> findAllBySpecification(Specification<T> specification) ;
  
  public <T> List<T> findAllBySpecificationPaged(Specification<T> specification, int page, int pageSize) ;
  
  public <T> List<T> findAllBySpecificationPaged(Specification<T> specification, int page, int pageSize, String order, String direction) ;
  
  public <T> Long countBySpecification(Specification<T> specification);

	
	

}
