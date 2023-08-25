
package operation.clan;

import domain.Clan;
import operation.AbstractGenericOperation;

/**
 *Sistemska operacija koja dodaje novog clana u bazu podataka.
 *
 * @author Sara
 */
public class ZapamtiClana extends AbstractGenericOperation{

	/**
	 * Clan koga cuvamo u bazi podataka i postavljamo mu id uzet iz baze jer je autoinkrement.
	 */
    private Clan clan;
    
    @Override
    protected void preconditions(Object param) throws Exception {  
      
       if(((Clan)param).getBrojTelefona().length()!=10) throw new Exception("Broj telefona nije korektan.");
        
    }
     
    @Override
    protected void executeOperation(Object param) throws Exception {
         
        repository.add((Clan)param);
        clan=(Clan)param;
    }
    /**
     * Vraca objekat klase Clan sa postavljenim rednim brojem clana.
     * @return Objekat klase clan.
     */
    public Clan getClan() {
        return clan;
    }
    
    
}
