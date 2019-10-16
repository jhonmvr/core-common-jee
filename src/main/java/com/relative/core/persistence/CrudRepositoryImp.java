package com.relative.core.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.LockTimeoutException;
import javax.persistence.PersistenceException;
import javax.persistence.PessimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.util.main.Constantes;

/**
 * Clase que suministra métodos en común para la implementación de un Objeto de
 * Acceso a Datos.
 *
 * @author
 *         <ul>
 *         <li>RELATIVE ENGINE - Luis Tamayo</li>
 *         <li>Seguros Sucre - Alex Guaman</li>
 *         </ul>
 * @version 1.0
 * @param <K> Clave primaria de las Entidades
 * @param <E> Entidad
 */
public abstract class CrudRepositoryImp<K, E> extends ReadRepositoryImp implements CrudRepository<K, E> {

	private static final Logger log = Logger.getLogger(CrudRepositoryImp.class.getName());

	/** El entity class. */
	protected Class<E> entityClass;
	
	
	
	/**
	 * Constructor por defecto.
	 */
	@SuppressWarnings("unchecked")
	public CrudRepositoryImp() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	/**
	 * Persiste la entidad enviada
	 * En bases de datos autocommit es necesario comentar
	 * //getEntityManager().refresh(entity);
	 * @param entity entidad a registrar
	 * @return entidad registrada
	 * @throws RelativeException
	 */
	@Override
	public E add(E entity) throws RelativeException {
		try {
    System.out.println("===inicio persiste");
			getEntityManager().persist(entity);
      System.out.println("===termino persiste");
			getEntityManager().flush();
			getEntityManager().refresh(entity);
			return entity;
		} catch (Exception e) {
			log.log(Level.SEVERE, "ERROR EN LA CREACION DE " + entity.getClass().getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, "ERROR EN LA CREACION DE " + entity.getClass().getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}
	
	/**
	 * Itera sobre la lista enviada
	 * 
	 * @param entity entidad a registrar
	 * @return entidad registrada
	 * @throws RelativeException
	 */
	@Override
	public void add(Iterable<E> entities) throws RelativeException{
		try {
			for( E e : entities ) {
				getEntityManager().persist(e);
				getEntityManager().flush();
				getEntityManager().refresh(e);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "ERROR EN LA CREACION DE add batch" , e);
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, "ERROR EN LA CREACION DE add batch"  + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}
	
	/**
	 * Modifica la entidad enviada
	 * 
	 * @param entity entidada modificar
	 * @return retorna la entidad modificada
	 * @throws RelativeException
	 */
	@Override
	public E update(E entity) throws RelativeException {
		try {
			getEntityManager().merge(entity);
			getEntityManager().flush();
			getEntityManager().refresh(entity);
			return entity;
		} catch (Exception e) {
			log.log(Level.SEVERE, "ERROR EN LA ACTUALIZACION DE " + entity.getClass().getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_UPDATE, "ERROR EN LA ACTUALIZACION DE " + entity.getClass().getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

	/**
	 * Remueve la entidad enviada
	 * 
	 * @param entity entidad a eliminar
	 * @throws RelativeException
	 */
	@Override
	public void remove(E entity) throws RelativeException {
		try {
			log.info( "=============>ENTRA A ELIMINACION");
			getEntityManager().remove(entity);
		} catch (Exception e) {
			log.log(Level.SEVERE, "ERROR EN LA ELIMINACION DE " + entity.getClass().getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_DELETE, "ERROR EN LA ELIMINACION DE "
					+ entity.getClass().getName() + Constantes.MSG_CON_ERROR + ExceptionUtils.getRootCauseMessage(e));
		}
	}

	/**
	 * Metodo que busca entidad por id
	 * 
	 * @param id clave por la que se va a buscar
	 * @return entidad
	 * @throws RelativeException
	 */
	public E findById(K id) throws RelativeException {
		try {
			return getEntityManager().find(entityClass, id);
		} catch (Exception e) {
			log.log(Level.SEVERE, "ERROR EN LA BUSQUEDA POR ID DE " + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR EN LA BUSQUEDA POR ID DE " + entityClass.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

	/**
	 * Metodo generico que carga todos los registros asociados con una entidad
	 * 
	 * @param c Clase relacionada al objeto
	 * @return Lista de entidades encontradas
	 * @throws RelativeException
	 */
	@Override
	public List<E> findAll(Class<E> c) throws RelativeException {
		try {
			CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<E> cq = qb.createQuery(c);
			cq.select(cq.from(c));
			return getEntityManager().createQuery(cq).getResultList();
		} catch (PersistenceException e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Constantes.PERSISTENCEEXCEPTION" + Constantes.MSG_ERROR_BUSQUEDA + c.getClass().getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + c.getClass().getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

	/**
	 * Metodo generico que carga la informacion de todos los datos asociados a la
	 * entidad
	 * 
	 * @param c Clase relacionada al objeto
	 * @param order ordenamiento
	 * @param direction direccion de ordenamiento
	 * @return Lista de entidades encontradas
	 * @throws RelativeException
	 */
	@Override
	public List<E> findAll(Class<E> c, String order, String direction) throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<E> cq = cb.createQuery(c);
			List<Order> orderList = new ArrayList<>();
			Root<E> rootEntry = cq.from(c);
			if (order != null && !"".equalsIgnoreCase(order)) {
				if (Constantes.SORT_DIRECTION_ASC.equalsIgnoreCase(direction)) {
					orderList.add(cb.asc(rootEntry.get(order)));
				} else {
					orderList.add(cb.desc(rootEntry.get(order)));
				}
				cq.orderBy(orderList); 
			}
			CriteriaQuery<E> all = cq.select(rootEntry);
			TypedQuery<E> allQuery = getEntityManager().createQuery(all);
			return allQuery.getResultList();
		} catch (IllegalArgumentException e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,	"IllegalArgumentException " + Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		} catch (LockTimeoutException e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,	Constantes.MSG_ERROR_BUSQUEDA + Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		} catch (QueryTimeoutException e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,	Constantes.QUERYTIMEOUTEXCEPTION + Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		} catch (TransactionRequiredException e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,	Constantes.TRANSACTIONREQUIREDEXCEPTION + Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR
							+ e.getMessage());
		} catch (PessimisticLockException e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,	Constantes.PESSIMISTICLOCKEXCEPTION + Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		} catch (PersistenceException e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,	Constantes.PERSISTENCEEXCEPTION + Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

	/**
	 * Metodo generico que carga la informacion de todos los datos asociados a la
	 * entidad paginado
	 * 
	 * @param c Clase relacionada al objeto
	 * @param firstResult primer registro desde cual cual buscar
	 * @param maxResults maximo de registros a mostrar
	 * @param order ordenamiento
	 * @param direction direccion de ordenamiento
	 * @return
	 * @throws RelativeException
	 */
	@Override
	public List<E> findAll(Class<E> c, int firstResult, int maxResults, String order, String direction)
			throws RelativeException {
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<E> cq = cb.createQuery(c);
			List<Order> listOrder = new ArrayList<>();
			Root<E> rootEntry = cq.from(c);
			if (order != null && !"".equalsIgnoreCase(order)) {
				if (Constantes.SORT_DIRECTION_ASC.equalsIgnoreCase(direction)) {
					listOrder.add(cb.asc(rootEntry.get(order)));
				} else {
					listOrder.add(cb.desc(rootEntry.get(order)));
				}
				cq.orderBy(listOrder);
			}
			CriteriaQuery<E> all = cq.select(rootEntry);
			TypedQuery<E> allQuery = getEntityManager().createQuery(all);
			allQuery.setFirstResult(firstResult);
			allQuery.setMaxResults(maxResults);

			return allQuery.getResultList();
		} catch (IllegalArgumentException e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,	"IllegalArgumentException " + Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		} catch (LockTimeoutException e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,	Constantes.MSG_ERROR_BUSQUEDA + Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		} catch (QueryTimeoutException e) {
			log.log(Level.SEVERE, Constantes.QUERYTIMEOUTEXCEPTION + Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,	Constantes.QUERYTIMEOUTEXCEPTION + Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		} catch (TransactionRequiredException e) {
			log.log(Level.SEVERE, Constantes.TRANSACTIONREQUIREDEXCEPTION + Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,	Constantes.TRANSACTIONREQUIREDEXCEPTION + Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		} catch (PessimisticLockException e) {
			log.log(Level.SEVERE, Constantes.PESSIMISTICLOCKEXCEPTION + Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,	Constantes.PESSIMISTICLOCKEXCEPTION + Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		} catch (PersistenceException e) {
			log.log(Level.SEVERE, Constantes.PERSISTENCEEXCEPTION + Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,	Constantes.PERSISTENCEEXCEPTION + Constantes.MSG_ERROR_BUSQUEDA + c.getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + " " + e.getMessage(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName() + " " + e.getMessage());
		}
	}

	/**
	 * Metodo que cuenta todos los registros
	 * 
	 * @param c entidad a la que se va a contar registros
	 * @return Cantidad de registros obtenidos
	 * @throws RelativeException
	 */
	@Override
	public Long countAll(Class<E> c) throws RelativeException {
		try {
			CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Long> cq = qb.createQuery(Long.class);
			cq.select(qb.count(cq.from(c)));
			return getEntityManager().createQuery(cq).getSingleResult();
		} catch (Exception e) {
			log.log(Level.SEVERE, Constantes.MSG_ERROR_BUSQUEDA + entityClass.getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_READ, Constantes.MSG_ERROR_BUSQUEDA + c.getClass().getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
		
	}
  
  

	
	

	/**
	 * Método abstracto que obtiene un EntityManager.
	 *
	 * @return el entity manager
	 */
	public abstract EntityManager getEntityManager();

}
