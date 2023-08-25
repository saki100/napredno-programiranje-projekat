
package operation.obrok;

import domain.Obrok;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja vraca sve Obroke iz baze podataka.
 *
 * @author Sara
 */
public class UcitajListuObroka extends AbstractGenericOperation {
     /**
      * Lista Obroka koja ce cuvati rezultat operacije.
      */
    private List<Obrok> obroci;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
     obroci=repository.getAll((Obrok)param);
    }
    /**
     * Vraca listu Obroka koja predstavlja rezultat izvrsenja sistemske operacije.
     * 
     * @return Listu Obroka.
     */
    public List<Obrok> getObroci() {
        return obroci;
    }
    
}
