
package operation.trener;

import domain.Trener;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja vraca sve Trenere iz baze podataka.
 *
 * @author Sara
 */
public class UcitajTrenere extends AbstractGenericOperation{
	/**
     * Lista Trenera koja ce cuvati rezultat operacije.
     */
    List<Trener> treneri;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       treneri=repository.getAll((Trener)param);
    }
    /**
     * Vraca listu Trenera koja predstavlja rezultat izvrsenja sistemske operacije.
     * 
     * @return Listu Trenera.
     */
    public List<Trener> getTreneri() {
        return treneri;
    }
    
    
}
