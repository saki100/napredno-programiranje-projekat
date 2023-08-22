
package operation.clan;

import domain.Clan;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class UcitajListuClanova extends AbstractGenericOperation{

    private List<Clan> lista;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        //nemamo preduslove
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
         
       lista= repository.getAll((Clan)param);
    }
    
    public List<Clan> getClanove() {
        return lista;
    }
}
