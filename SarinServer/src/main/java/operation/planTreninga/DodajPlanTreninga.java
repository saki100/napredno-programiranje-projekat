
package operation.planTreninga;

import domain.PlanTreninga;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class DodajPlanTreninga extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
         
        repository.add((PlanTreninga)param);

    }
    
}
