package ec.com.def.core.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class GeneralRepositoryImp<K, E> extends CrudRepositoryImp<K, E>  {
	
	
	
	
	@Inject
    private EntityManager em;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
}
