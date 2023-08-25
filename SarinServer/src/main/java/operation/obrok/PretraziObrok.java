
package operation.obrok;

import domain.Obrok;
import operation.AbstractGenericOperation;

/**
 * Sistemska operacija koja pretrazuje Obrok u bazi podataka prema primarnom kljucu
 * i vraca kao objekat klase Obrok samo onog koji ispunjava uslov.
 *
 * @author Sara
 */
public class PretraziObrok extends AbstractGenericOperation{
   /**
    * Pronadjeni obrok nadjen u bazi, cuva rezultat sistemske operacije.
    */
    Obrok o;
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        o=(Obrok)repository.getObject((Obrok)param);
    }
    /**
     * Vraca Obrok koji predstavlja rezultat izvrsenja sistemske operacije.
     * 
     * @return Obrok koji smo trazili po primarnom kljucu iz baze.
     */
    public Obrok getO() {
        return o;
    }
    
}

