
package operation.ishrana;

import domain.Ishrana;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class PretraziIshrane extends AbstractGenericOperation{

    List<Ishrana> ishrane;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
      
      ishrane=repository.getAllCondition((Ishrana)param);
    }

    public List<Ishrana> getIshrane() {
        return ishrane;
    }
    
}

