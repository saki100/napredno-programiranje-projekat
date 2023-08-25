
package operation.planIshrane;


import domain.PlanIshrane;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja dodaje novi Plan ishrane u bazu podataka.
 *
 * @author Sara
 */
public class DodajPlanIshrane  extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((PlanIshrane)param);
    }
    
}

