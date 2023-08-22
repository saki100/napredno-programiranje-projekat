
package operation.trener;

import domain.Trener;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class UcitajTrenere extends AbstractGenericOperation{

    List<Trener> treneri;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       treneri=repository.getAll((Trener)param);
    }

    public List<Trener> getTreneri() {
        return treneri;
    }
    
    
}
