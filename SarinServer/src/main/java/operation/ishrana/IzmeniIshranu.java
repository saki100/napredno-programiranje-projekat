
package operation.ishrana;

import domain.Ishrana;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class IzmeniIshranu extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       repository.edit((Ishrana)param);
    }
    
}

