package com.relative.core.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;

/**
 * Clase que contiene metodos para acceso a datos que obtienene informacion especifica
 * @author root
 *
 */
public abstract class ReadRepositoryImp {
	
	private static final Logger log = Logger.getLogger(ReadRepositoryImp.class.getName());
	/**
	 * Busca una lista de objetos en base a sus parametros
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws RelativeException
	 */
	public <E> List<E> getObjectsByQuery(String consulta, Map<String, Object> parametros, Class<E> entityClass) throws RelativeException {
		try {
			TypedQuery<E> query = getEntityManager().createQuery(consulta, entityClass);
			setearTypedQueryParamsKeyString(parametros, query);
			return procesarListaResultado(query.getResultList());
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}
	
	/**
	 * Busca una lista de objetos en base a sus parametros actualizados
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws RelativeException
	 */
	public <E> List<E> getObjectsByQueryUpdated(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException {
		try {
			TypedQuery<E> query = getEntityManager().createQuery(consulta, entityClass);
			query.setHint(Constantes.PERSISTENCE_CACHE_STOREMODE, Constantes.REFRESH);
			setearTypedQueryParamsKeyString(parametros, query);
			return procesarListaResultado(query.getResultList());
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

	/**
	 * Busca una lista de objetos en base a sus parametros
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws RelativeException
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> getObjectsByNamedQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException {
		try {
			Query query = getEntityManager().createNamedQuery(consulta, entityClass);		
			setearTypedQueryParamsKeyString(parametros, query);
			return procesarListaResultado(query.getResultList());
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}
	
	/**
	 * Busca una lista de objetos en base a sus parametros actualizados
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws RelativeException
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> getObjectsByNamedQueryUpdated(String consulta, Map<String, Object> parametros, Class<E> entityClass) throws RelativeException {
		try {
			Query query = getEntityManager().createNamedQuery(consulta, entityClass);
			query.setHint(Constantes.PERSISTENCE_CACHE_STOREMODE, Constantes.REFRESH);
			setearTypedQueryParamsKeyString(parametros, query);
			return query.getResultList();
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}


	/**
	 * Busca una lista de cualquier objeto en base a sus parametros
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return List lista de entidades
	 * @throws RelativeException
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> getCampoByNamedQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException {
		try {
			Query query = getEntityManager().createNamedQuery(consulta, entityClass);
			setearTypedQueryParamsKeyString(parametros, query);
			return procesarListaResultado(query.getResultList());
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

	/**
	 * Obtiene el primer registro
	 * 
	 * @param consulta
	 * @param parametros
	 * @return
	 * @throws RelativeException
	 */
	@SuppressWarnings("unchecked")
	public <E> E getFirstObjectByNamedQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException {
		try {
			Query query = getEntityManager().createNamedQuery(consulta, entityClass);
			query.setMaxResults(1);
			setearTypedQueryParamsKeyString(parametros, query);
			return (E) query.getSingleResult();
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}


	/**
	 * Ejecuta un namedQuery que siempre devuelva un objeto.
	 *
	 * @param namedQuery
	 * @param parametros parametros
	 * @return entidad resultante
	 * @throws RelativeException
	 */
	@SuppressWarnings("unchecked")
	public <E> E getObjectByNamedQuery(String namedQuery, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException {
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery, entityClass);
			setearTypedQueryParamsKeyString(parametros, query);
			return (E) query.getSingleResult();
		
		}	catch (NoResultException ex) {
			return null;
		}	catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}
	
	/**
	 * Ejecuta un namedQuery que siempre devuelva un objeto actualizado.
	 *
	 * @param namedQuery
	 * @param parametros parametros
	 * @return entidad resultante
	 * @throws RelativeException
	 */
	@SuppressWarnings("unchecked")
	public <E> E getObjectByNamedQueryUpdated(String namedQuery, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException {
		try {
			Query query = getEntityManager().createNamedQuery(namedQuery, entityClass);
			query.setHint(Constantes.PERSISTENCE_CACHE_STOREMODE, Constantes.REFRESH);
			setearTypedQueryParamsKeyString(parametros, query);
			return (E) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

	/**
	 * Buca un registro en base a sus parametros
	 *
	 * @param consulta consulta
	 * @param parametros parametros
	 * @return entidad resultante
	 * @throws RelativeException
	 */
	public <E> E getObjectByQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException {
		try {
			TypedQuery<E> query = getEntityManager().createQuery(consulta, entityClass);
			setearTypedQueryParamsKeyString(parametros, query);
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

	/**
	 *
	 * @param namedQuery
	 * @param parametros
	 * @return
	 * @throws RelativeException
	 */
	public <E> Object getMaxCountSumByNamedQuery(String namedQuery, Map<String, Object> parametros,Class<E> entityClass)	throws RelativeException {
		try {
			TypedQuery<E> query = getEntityManager().createNamedQuery(namedQuery, entityClass);
			setearTypedQueryParamsKeyString(parametros, query);
			return query.getSingleResult();
		} catch (Exception e) {			
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

	/**
	 * Retorna un Integer que cumple con las condiciones de la consulta
	 *
	 * @param consulta nombre de la consulta a ejecutar
	 * @param parametros condiciones a ejecutar
	 * @return Integer producto de la consulta
	 * @throws RelativeException
	 */
	public <E> Integer getIntegerByNameQuery(String consulta, Map<String, Object> parametros,Class<E> entityClass) throws RelativeException {
		try {
			Integer result = null;
			Query query = getEntityManager().createNamedQuery(consulta, entityClass);
			setearTypedQueryParamsKeyString(parametros, query);
			List<?> resultList = query.getResultList();
			if (!resultList.isEmpty() && resultList.get(0) != null) {
				result = Integer.parseInt(resultList.get(0).toString());
			}
			return result;
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}
	

	
	/**
	 * Ejecuta un namedQuery para realizar un Update.
	 *
	 * @param namedUpdateQuery, nombre del query que haga un update
	 * @param parametros
	 * @return
	 * @throws RelativeException
	 */
	public int ejecutarUpdate(String namedUpdateQuery, Map<String, Object> parametros) throws RelativeException {
		try {
			Query query = getEntityManager().createNamedQuery(namedUpdateQuery);
			if (parametros != null) {
				for (Map.Entry<String, Object> param : parametros.entrySet()) {
					query.setParameter(param.getKey(), param.getValue());
				}
			}
			return query.executeUpdate();
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_EJECUCION + namedUpdateQuery, e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_EJECUCION + namedUpdateQuery + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}
	
	/**
	 * Ejecuta un query nativo, se puede utilizar en llamadas a SP
	 *
	 * @param strQuery Consulta nativa
	 * @param parametros parametros en orden
	 * @return Lista de objetos
	 * @throws RelativeException
	 */
	@SuppressWarnings("unchecked")
	public List<Object> ejecutarQueryNativo(String strQuery, Map<Integer, Object> parametros) throws RelativeException {
		try {
			Query query = getEntityManager().createNativeQuery(strQuery);
			setearQueryParametersKeyInteger(parametros, query);
			return procesarListaResultado(query.getResultList());
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_EJECUCION + strQuery, e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_EJECUCION + strQuery + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

	/**
	 * Ejecuta un query nativo
	 *
	 * @param strQuery Consulta nativa
	 * @param parametros parametros en orden
	 * @return numero de entidades afectadas
	 * @throws RelativeException
	 */
	public int ejecutarQueryNativoNoResult(String strQuery, Map<Integer, Object> parametros) throws RelativeException {
		try {
			Query query = getEntityManager().createNativeQuery(strQuery);
			setearQueryParametersKeyInteger(parametros, query);
			return query.executeUpdate();
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_EJECUCION + strQuery, e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_EJECUCION + strQuery + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

	/**
	 * Ejecutar un query nativo que devuelve un solo valor sum, count, exist.
	 *
	 * @param strQuery
	 * @param parametros
	 * @return
	 * @throws RelativeException
	 */
	public Object ejecutarNamedNativeQuerySingleResult(String strQuery, Map<Integer, Object> parametros)
			throws RelativeException {
		try {
			Query query = getEntityManager().createNamedQuery(strQuery);
			setearQueryParametersKeyInteger(parametros, query);
			return query.getSingleResult();
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_EJECUCION + strQuery, e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_EJECUCION + strQuery + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}


	/**
	 * Setea los parametros al query
	 *
	 * @param parametros Map de key Integer y value Object
	 * @param query
	 */
	private void setearQueryParametersKeyInteger(Map<Integer, Object> parametros, Query query) {
		if (parametros != null) {
			for (Map.Entry<Integer, Object> campo : parametros.entrySet()) {
				query.setParameter(campo.getKey(), campo.getValue());
			}
		}
	}

	/**
	 * setea los parametros a la consulta
	 *
	 * @param <T> entidad
	 * @param parametros parametros
	 * @param query consulta
	 */
	private void setearTypedQueryParamsKeyString(Map<String, Object> parametros, Query query) {
		if (parametros != null) {
			for (Map.Entry<String, Object> aux : parametros.entrySet()) {
				query.setParameter(aux.getKey(), aux.getValue());
			}
		}
	}

	/**
	 * Metodo que procesa el resultado de la lista recuperada de base de datos, verifica si esta vacia
	 *
	 * @param <T> Entidad al cual es implementada la lista
	 * @param res lista a ser procesada
	 * @return Null si la lista es vacia, caso contrario la lista
	 * @throws RelativeException
	 */
	@SuppressWarnings("unchecked")
	private <T> List<T> procesarListaResultado(List<T> res) {
		return (res != null && res.isEmpty()) ? Collections.EMPTY_LIST : res;
	}
	
	/**
	 * Metodo que generar un query en base a la especificacion de criterios enviados
	 * @param specification patron de especificacion de criterios
	 * @return Listado de entidades encontrados
	 */
	/*public <T> List<T> findAllBySpecification(Specification<T> specification) {
	    CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(specification.getType());
	    Root<T> root = criteriaQuery.from(specification.getType());
	    Predicate predicate = specification.toPredicate(root,  criteriaBuilder);
	    criteriaQuery.where(predicate);
	    return this.getEntityManager().createQuery(criteriaQuery).getResultList();
	  }*/
    
    
    public <T> List<T> findAllBySpecification(Specification<T> specification) {
	    CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(specification.getType());
	    Root<T> root = criteriaQuery.from(specification.getType());
	    Predicate predicate = specification.toPredicate(root,  criteriaBuilder);
	    criteriaQuery.where(predicate);
      TypedQuery<T> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
      return typedQuery.getResultList();
	  }
    
    
    public <T> List<T> findAllBySpecificationPaged(Specification<T> specification, int page, int pageSize) {
	    CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(specification.getType());
	    Root<T> root = criteriaQuery.from(specification.getType());
	    Predicate predicate = specification.toPredicate(root,  criteriaBuilder);
	    criteriaQuery.where(predicate);
      TypedQuery<T> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
      typedQuery.setFirstResult(page);
      typedQuery.setMaxResults(pageSize);
      return typedQuery.getResultList();
	  }
    
    public <T> List<T> findAllBySpecificationPaged(Specification<T> specification, int page, int pageSize,String order, String direction) {
	    List<Order> orderList = new ArrayList<>();
      CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(specification.getType());
	    Root<T> root = criteriaQuery.from(specification.getType());
	    Predicate predicate = specification.toPredicate(root,  criteriaBuilder);
	    criteriaQuery.where(predicate);
      if (order != null && !"".equalsIgnoreCase(order)) {
				if (Constantes.SORT_DIRECTION_ASC.equalsIgnoreCase(direction)) {
					orderList.add(criteriaBuilder.asc(root.get(order)));
				} else {
					orderList.add(criteriaBuilder.desc(root.get(order)));
				}
				criteriaQuery.orderBy(orderList); 
			}
      TypedQuery<T> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
      typedQuery.setFirstResult(page);
      typedQuery.setMaxResults(pageSize);
      return typedQuery.getResultList();
	  }
    
    public <T> Long countBySpecification(Specification<T> specification) {
	    CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
      Root<T> root = countQuery.from(specification.getType());
      //Root<T> root=countQuery.select(criteriaBuilder.count(countQuery.from(specification.getType())));
      Predicate predicate = specification.toPredicate(root,  criteriaBuilder);
      countQuery.select(criteriaBuilder.count( root ));
      countQuery.where(predicate);
      return this.getEntityManager().createQuery(countQuery).getSingleResult();
	  }

	/**
	 * Método abstracto que obtiene un EntityManager.
	 *
	 * @return el entity manager
	 */
	public abstract EntityManager getEntityManager();


}
