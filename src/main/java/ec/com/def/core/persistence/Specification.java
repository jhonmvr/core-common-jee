package ec.com.def.core.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Interface que tiene los metodos basicos a implementar por las implementaciones de especificacion
 * @author LUIS TAMAYO - RELATIVE ENGINE
 *
 * @param <T> Tipo de objeto a utilizar como generico
 */
public interface Specification<T> {
	
	  public boolean isSatisfiedBy(T t); 
	  
	  /**
	   * Genera y convierte los parametros enviados como parametro en 
	   * @param root
	   * @param cb
	   * @return
	   */
	  public Predicate toPredicate(Root<T> entityRoot, CriteriaBuilder cb);
	  
	  /**
	   * Metodo que retorna el tipo genero a utilizarse en la generacion de predicados
	   * @return Tipo de objeto
	   */
	  public Class<T> getType();
	  
	  public T getEntidad();
	  
	  public void setEntidad(T entidad);
	}
