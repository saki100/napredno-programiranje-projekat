
package operation.clan;

import domain.Clan;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class ZapamtiClana extends AbstractGenericOperation{

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

    public Clan getClan() {
        return clan;
    }
    
    
}
