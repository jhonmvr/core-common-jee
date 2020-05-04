package ec.com.def.core.persistence;

import java.util.List;
import java.util.Map;

import ec.com.def.core.exception.DefException;

public interface ReadRepository {
	
	/**
	 * Busca una lista de objetos en base a sus parametros actualizados
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws DefException
	 */
	public <E> List<E> getObjectsByQueryUpdated(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws DefException;

	/**
	 * Busca una lista de objetos en base a sus parametros
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws DefException
	 */
	public <E> List<E> getObjectsByNamedQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws DefException ;
	
	/**
	 * Busca una lista de objetos en base a sus parametros actualizados
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws DefException
	 */
	public <E> List<E> getObjectsByNamedQueryUpdated(String consulta, Map<String, Object> parametros, Class<E> entityClass) throws DefException;


	/**
	 * Busca una lista de cualquier objeto en base a sus parametros
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws DefException
	 */
	public <E> List<E> getCampoByNamedQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws DefException ;

	/**
	 * Obtiene el primer registro
	 * 
	 * @param consulta
	 * @param parametros
	 * @return
	 * @throws DefException
	 */
	public <E> E getFirstObjectByNamedQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws DefException;


	/**
	 * Ejecuta un namedQuery que siempre devuelva un objeto.
	 *
	 * @param namedQuery
	 * @param parametros parametros
	 * @return entidad resultante
	 * @throws DefException
	 */
	public <E> E getObjectByNamedQuery(String namedQuery, Map<String, Object> parametros,Class<E> entityClass) throws DefException ;
	
	/**
	 * Ejecuta un namedQuery que siempre devuelva un objeto actualizado.
	 *
	 * @param namedQuery
	 * @param parametros parametros
	 * @return entidad resultante
	 * @throws DefException
	 */
	public <E> E getObjectByNamedQueryUpdated(String namedQuery, Map<String, Object> parametros,Class<E> entityClass) throws DefException ;

	/**
	 * Buca un registro en base a sus parametros
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return entidad resultante
	 * @throws DefException
	 */
	public <E> E getObjectByQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws DefException ;

	/**
	 *
	 * @param namedQuery
	 * @param parametros
	 * @return
	 * @throws DefException
	 */
	public <E> Object getMaxCountSumByNamedQuery(String namedQuery, Map<String, Object> parametros,Class<E> entityClass)	throws DefException ;

	/**
	 * Retorna un Integer que cumple con las condiciones de la consulta
	 *
	 * @param consulta nombre de la consulta a ejecutar
	 * @param parametros condiciones a ejecutar
	 * @return Integer producto de la consulta
	 * @throws DefException
	 */
	public <E> Integer getIntegerByNameQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws DefException ;
	

	
	/**
	 * Ejecuta un namedQuery para realizar un Update.
	 *
	 * @param namedUpdateQuery, nombre del query que haga un update
	 * @param parametros
	 * @return
	 * @throws DefException
	 */
	public int ejecutarUpdate(String namedUpdateQuery, Map<String, Object> parametros) throws DefException ;
	
	/**
	 * Ejecuta un query nativo, se puede utilizar en llamadas a SP
	 *
	 * @param strQuery Consulta nativa
	 * @param parametros parametros en orden
	 * @return Lista de objetos
	 * @throws DefException
	 */
	public List<Object> ejecutarQueryNativo(String strQuery, Map<Integer, Object> parametros) throws DefException ;

	/**
	 * Ejecuta un query nativo
	 *
	 * @param strQuery Consulta nativa
	 * @param parametros parametros en orden
	 * @return numero de entidades afectadas
	 * @throws DefException
	 */
	public int ejecutarQueryNativoNoResult(String strQuery, Map<Integer, Object> parametros) throws DefException ;

	/**
	 * Ejecutar un query nativo que devuelve un solo valor sum, count, exist.
	 *
	 * @param strQuery
	 * @param parametros
	 * @return
	 * @throws DefException
	 */
	public Object ejecutarNamedNativeQuerySingleResult(String strQuery, Map<Integer, Object> parametros)
			throws DefException ;


	
	/**
	 * Metodo que generar un query en base a la especificacion de criterios enviados
	 * @param specification patron de especificacion de criterios
	 * @return Listado de entidades encontrados
	 */
	public <T> List<T> findAllBySpecification(Specification<T> specification) ;


}
