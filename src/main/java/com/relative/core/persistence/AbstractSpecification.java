package com.relative.core.persistence;

import java.lang.reflect.ParameterizedType;

/**
 * Clase abstracta que implementa algunos de los mentodos de especificacion
 * 
 * @author LUIS TAMAYO - RELATIVE ENGINE
 *
 * @param <T>
 *            Tipo de objeto a utilizar como generico
 */
public abstract class AbstractSpecification<T> implements Specification<T> {
	
	private T entidad;
	
	/**
	 * Metodo que retorna el tipo genero a utilizarse en la generacion de predicados
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getType() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<T>) type.getActualTypeArguments()[0];
	}
	
	public void setEntidad(T entidad) {
		this.entidad=entidad;
	}
	
	public T getEntidad() {
		return entidad;
	}
	
	
}
