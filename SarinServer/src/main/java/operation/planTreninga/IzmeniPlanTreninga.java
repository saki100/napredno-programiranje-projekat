
package operation.planTreninga;

import domain.PlanTreninga;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja menja podatke o odredjenom Planu treninga u bazi podataka,
 * odnosno vrsi izmene nekih od atributa objekta klase PlanTreninga.
 *
 * @author Sara
 */
public class IzmeniPlanTreninga extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        
        repository.edit((PlanTreninga)param);  
    }
    
}
