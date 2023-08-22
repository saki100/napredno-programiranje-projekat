
package operation.obrok;

import domain.Obrok;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class IzmeniObrok extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
           repository.edit((Obrok)param);
    }
    
}
