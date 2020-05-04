package ec.com.def.core.util.main;

import java.io.Serializable;

/**
 * Clase utilizada en los procesos de paginacion de la capa de presentacion hacia el core
 * @author RELATIVE ENGINE
 *
 */
public class PaginatedWrapper implements Serializable{
	
	private static final long serialVersionUID = -9221245133997377551L;
	protected Integer currentPage;
	protected Integer pageNumber;
    protected Integer pageSize;
    protected Integer size;
    protected Integer totalResults;
    protected Integer totalPages;
    protected Integer totalElements;
    protected Integer start;
    protected Integer end;
    protected int startRecord;

    protected String sortFields;
    protected String sortDirections;
    
    private String isPaginated;
    
    public static final String YES="Y";
    
    public PaginatedWrapper(){
    	
    }
    
    public PaginatedWrapper(Integer currentPage,Integer pageSize,String sortFields,String sortDirections, String isPaginated){
    	this.currentPage=currentPage;
    	this.pageSize=pageSize;
    	this.sortFields=sortFields;
    	this.sortDirections=sortDirections;
    	this.isPaginated = isPaginated;
    }
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder("==> Object Page ");
    	sb.append(" currentPage " + this.getCurrentPage()) ;
    	sb.append(" pageNumber " +this.getPageNumber() ) ;
    	sb.append(" pageSize " + this.getPageSize()) ;
    	sb.append(" size " + this.getSize()) ;
    	sb.append(" totalResults " + this.getTotalResults()) ;
    	sb.append(" totalPages " + this.getTotalPages()) ;
    	sb.append(" totalElements " + this.getTotalElements()) ;
    	sb.append(" start " + this.getStart()) ;
    	sb.append(" end " + this.getEnd()) ;
    	sb.append(" sortFields " + this.getSortFields()) ;
    	sb.append(" sortDirections " + this.getSortDirections()) ;
    	sb.append(" isPaginated " + this.getIsPaginated()) ;
    	return sb.toString();
    }
    
    public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
	public String getSortFields() {
		return sortFields;
	}
	public void setSortFields(String sortFields) {
		this.sortFields = sortFields;
	}
	public String getSortDirections() {
		return sortDirections;
	}
	public void setSortDirections(String sortDirections) {
		this.sortDirections = sortDirections;
	}
	
	public int getStartRecord(){
		if( currentPage != null && currentPage>0 && pageSize != null  )
			//if( currentPage>1 )
				//return (currentPage - 1) * pageSize;
			//else 
				return currentPage * pageSize;
		else
			return 0;
	}
	public String getIsPaginated() {
		return isPaginated;
	}
	public void setIsPaginated(String isPaginated) {
		this.isPaginated = isPaginated;
	}

	public Integer getTotalPages() {
		if( getTotalResults() != null ){
			if( (getTotalResults() % (pageSize != null?pageSize:1)) == 0 ){
				return getTotalResults() / (pageSize != null?pageSize:1) ;
			} else {				
				return Double.valueOf( String.valueOf( getTotalResults() / (pageSize != null?pageSize:1))).intValue() + 1 ;								
			}
		}
		return  0;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalElements() {
		return getTotalResults();
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}

	public Integer getStart() {
		return getStartRecord();
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return Math.min(getStart() + (pageSize != null?pageSize:1), getTotalElements()!=null?getTotalElements():0);
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getPageNumber() {
		return getCurrentPage();
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getSize() {
		return getPageSize();
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	
	
	
	
	
	
    
}
