
package operation.planIshrane;

import domain.PlanIshrane;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class IzmeniPlanIshrane extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       repository.edit((PlanIshrane)param);
    }
    
}

