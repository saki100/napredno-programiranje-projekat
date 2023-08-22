
package operation.obrok;

import domain.Obrok;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class UcitajListuObroka extends AbstractGenericOperation {

    private List<Obrok> obroci;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
     obroci=repository.getAll((Obrok)param);
    }

    public List<Obrok> getObroci() {
        return obroci;
    }
    
}
