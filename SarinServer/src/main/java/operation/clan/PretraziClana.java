
package operation.clan;

import domain.Clan;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Sara
 */
public class PretraziClana extends AbstractGenericOperation{

    List<Clan> clanovi;
    
    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
         clanovi= repository.getAllCondition(param);
    }

    public List<Clan> getClanovi() {
        return clanovi;
    }
    
}
