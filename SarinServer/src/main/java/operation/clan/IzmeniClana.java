
package operation.clan;

import domain.Clan;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja menja podatke o odredjenom clanu u bazi podataka.
 *
 * @author Sara
 */
public class IzmeniClana extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
          repository.edit((Clan)param);
          
    }
    
}
