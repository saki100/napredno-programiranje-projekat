
package operation.obrok;

import domain.Obrok;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class PretraziObrok extends AbstractGenericOperation{

    Obrok o;
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        o=(Obrok)repository.getObject((Obrok)param);
    }

    public Obrok getO() {
        return o;
    }
    
}

