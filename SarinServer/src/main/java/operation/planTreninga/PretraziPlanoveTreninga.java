
package operation.planTreninga;

import domain.Clan;
import domain.PlanTreninga;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class PretraziPlanoveTreninga extends AbstractGenericOperation{

    List<PlanTreninga> planovi;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       PlanTreninga plan=new PlanTreninga();
       plan.setClan((Clan)param);
       planovi=repository.getAllCondition(plan);
    }

    public List<PlanTreninga> getPlanovi() {
        return planovi;
    }
    
}

