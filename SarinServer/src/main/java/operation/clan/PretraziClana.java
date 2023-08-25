
package operation.clan;

import domain.Clan;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja pretrazuje clanove po odredjenom uslovu iz baze podataka 
 * i vraca kao listu samo one koji ispunjavaju uslove.
 *
 * @author Sara
 */
public class PretraziClana extends AbstractGenericOperation{
	/**
     * Lista clanova koja ce cuvati rezultat operacije.
     */
    List<Clan> clanovi;
    
    @Override
    protected void preconditions(Object param) throws Exception {

    }
   
    @Override
    protected void executeOperation(Object param) throws Exception {
         clanovi= repository.getAllCondition(param);
    }
    /**
     * Vraca listu clanova koja predstavlja rezultat izvrsenja sistemske operacije.
     * 
     * @return Listu clanova.
     */
    public List<Clan> getClanovi() {
        return clanovi;
    }
    
}
