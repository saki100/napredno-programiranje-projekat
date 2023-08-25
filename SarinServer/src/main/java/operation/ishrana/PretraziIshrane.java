
package operation.ishrana;

import domain.Ishrana;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja pretrazuje Ishrane po odredjenom uslovu iz baze podataka 
 * i vraca kao listu samo one koji ispunjavaju uslove.
 * 
 * @author Sara
 */
public class PretraziIshrane extends AbstractGenericOperation{
    /**
     * Lista Ishrana koja ce cuvati rezultat operacije.
     */
    List<Ishrana> ishrane;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
      
      ishrane=repository.getAllCondition((Ishrana)param);
    }
    /**
     * Vraca listu Ishrana koja predstavlja rezultat izvrsenja sistemske operacije.
     *
     * @return Listu Ishrana.
     */
    public List<Ishrana> getIshrane() {
        return ishrane;
    }
    
}

