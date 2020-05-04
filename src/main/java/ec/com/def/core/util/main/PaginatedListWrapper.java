package ec.com.def.core.util.main;

import java.io.Serializable;
import java.util.List;

/**
 * Clase utilizada en los procesos de paginacion de la capa de presentacion hacia el core, gestiona listas genericas
 * @author RELATIVE ENGINE
 *
 */
public class PaginatedListWrapper<T extends Serializable> extends PaginatedWrapper{
	
	private static final long serialVersionUID = -2090955362812464164L;
	private List<T> list;
    	
	public PaginatedListWrapper(){
		// Constructor vacío
	}
	
	public PaginatedListWrapper( PaginatedWrapper pw ){
		this.currentPage=pw.getCurrentPage();
		this.pageNumber=pw.getPageNumber();
		this.pageSize=pw.getPageSize();
		this.size=this.pageSize;
		this.sortDirections=pw.getSortDirections();
		this.sortFields=pw.getSortFields();
		this.totalResults=pw.getTotalResults();
		this.totalElements=pw.getTotalElements();
		this.start=pw.getStart();
		this.end=pw.getEnd();		
	}
    
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

    
}
