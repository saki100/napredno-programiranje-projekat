
package operation.obrok;

import domain.Obrok;
import operation.AbstractGenericOperation;

/**
 *Sistemska operacija koja menja podatke o odredjenom Obroku u bazi podataka, odnosno vrsi izmene nekih od atributa objekta klase Obrok.
 *
 * @author Sara
 */
public class IzmeniObrok extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
           repository.edit((Obrok)param);
    }
    
}
