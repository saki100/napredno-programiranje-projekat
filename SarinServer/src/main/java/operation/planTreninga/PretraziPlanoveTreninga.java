
package operation.planTreninga;

import domain.Clan;
import domain.PlanTreninga;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja pretrazuje Planove treninga po odredjenom uslovu iz baze podataka 
 * i vraca kao listu samo one koji ispunjavaju uslove.
 * 
 * @author Sara
 */
public class PretraziPlanoveTreninga extends AbstractGenericOperation{
	/**
     * Lista Planova treninga koja ce cuvati rezultat operacije.
     */
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
    /**
     * Vraca listu Planova treninga koja predstavlja rezultat izvrsenja sistemske operacije.
     * 
     * @return Listu Planova treninga.
     */
    public List<PlanTreninga> getPlanovi() {
        return planovi;
    }
    
}

