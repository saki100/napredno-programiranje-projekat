
package operation.planIshrane;

import domain.PlanIshrane;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja menja podatke o odredjenom Planu ishrane u bazi podataka,
 * odnosno vrsi izmene nekih od atributa objekta klase PlanIshrane.
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

