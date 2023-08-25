
package operation.ishrana;

import domain.Ishrana;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja dodaje novu Ishranu u bazu podataka.
 *
 * @author Sara
 */
public class DodajIshranu extends AbstractGenericOperation{

	
	
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.addAsociation((Ishrana)param);
    }
    
}

