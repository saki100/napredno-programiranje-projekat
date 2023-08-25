
package operation.clan;

import domain.Clan;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja vraca sve clanove iz baze podataka.
 *
 * @author Sara
 */
public class UcitajListuClanova extends AbstractGenericOperation{
    /**
     * Lista clanova koja ce cuvati rezultat operacije.
     */
    private List<Clan> lista;
    
    
    @Override
    protected void preconditions(Object param) throws Exception {
        //nemamo preduslove
    }
    
    @Override
    protected void executeOperation(Object param) throws Exception {
         
       lista= repository.getAll((Clan)param);
    }
    /**
     * Vraca listu clanova koja predstavlja rezultat izvrsenja sistemske operacije.
     * 
     * @return Listu clanova.
     */
    public List<Clan> getClanove() {
        return lista;
    }
}
