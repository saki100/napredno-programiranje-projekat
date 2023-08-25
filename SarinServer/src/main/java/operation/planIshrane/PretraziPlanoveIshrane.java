
package operation.planIshrane;

import domain.Clan;
import domain.PlanIshrane;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja pretrazuje Planove ishrane po odredjenom uslovu iz baze podataka 
 * i vraca kao listu samo one koji ispunjavaju uslove.
 *
 * @author Sara
 */
public class PretraziPlanoveIshrane extends AbstractGenericOperation{
	/**
     * Lista Planova ishrane koja ce cuvati rezultat operacije.
     */
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
    /**
     * Vraca listu Planova ishrane koja predstavlja rezultat izvrsenja sistemske operacije.
     * 
     * @return Listu Planova ishrane.
     */
    public List<PlanIshrane> getPlanoveIshrane(){
        return planovi;
    }
    
}
