
package operation.planIshrane;

import domain.Clan;
import domain.PlanIshrane;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class PretraziPlanoveIshrane extends AbstractGenericOperation{
    
    List<PlanIshrane> planovi;

    @Override
    protected void preconditions(Object param) throws Exception {
         
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        PlanIshrane plan=new PlanIshrane();
        plan.setClan((Clan)param);
         planovi=repository.getAllCondition(plan);
         
    }
    public List<PlanIshrane> getPlanoveIshrane(){
        return planovi;
    }
    
}
