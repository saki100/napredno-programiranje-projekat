
package operation.obrok;

import domain.Obrok;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja dodaje novi Obrok u bazu podataka.
 *
 * @author Sara
 */
public class DodajObrok extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       repository.add((Obrok)param);
    }
    
}
