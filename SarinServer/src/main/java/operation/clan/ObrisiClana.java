
package operation.clan;

import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja brise odredjenog clana iz baze podataka.
 *
 * @author Sara
 */
public class ObrisiClana extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
           
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.delete(param);
    }
    
    
}
