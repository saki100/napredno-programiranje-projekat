
package operation.ishrana;

import domain.Ishrana;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja menja podatke o Ishrani, odnosno neke od atributa objekta klase Ishrana.
 *
 * @author Sara
 */
public class IzmeniIshranu extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       repository.edit((Ishrana)param);
    }
    
}

