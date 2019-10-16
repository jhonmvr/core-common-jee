package com.relative.core.util.main;

import java.io.Serializable;
import java.util.List;

public class BaseWrapper<T extends Serializable> implements Serializable{
	
	private T entidad;
	private List<T> entidades;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7208793787167600464L;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public T getEntidad() {
		return entidad;
	}

	public void setEntidad(T entidad) {
		this.entidad = entidad;
	}

	public List<T> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<T> entidades) {
		this.entidades = entidades;
	}
	
	
	

}
