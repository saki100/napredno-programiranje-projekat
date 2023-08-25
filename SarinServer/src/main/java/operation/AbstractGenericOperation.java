
package operation;

import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.RepositoryDBGeneric;

/**
 * Apstraktna klasa koja sadrzi metode za rad sa transakcijama i 
 * redosled njihovog pozivanja i izvrsavanja.
 *
 * @author Sara
 */
public abstract class AbstractGenericOperation {  //apstraktna klasa ne moze da ima instancu ali moze da ima konstruktor
    /**
     * Interfejs repozitorijum u kome su metode koje su potrebne za sistemske operacije.
     */
    protected Repository repository;
     /**
      * Neparametarski konstruktor koji inicijalizuje repository.
      */
    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }
   /**
    * Poziva metode za rad sa transakcijama po odredjenom redosledu.
    * @param param Objekat nad kojim se izvrsava sistemska operacija.
    * @throws Exception ako se pojavi bilo koji tip izuzetka.
    */
    public final void execute(Object param) throws Exception {
        try {
            preconditions(param);  //mozemo prvo staviti startTransaction pa proveru uslova jer mozda provera usl podrazumeva rad sa bazom
            startTransaction();
            executeOperation(param);
            commitTransaction();
        } catch (Exception ex) {
            System.out.println("usao u catch kod abstract generic operation");
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    // Operation-specific method
    /**
     * Vrsi validaciju objekta pre izvrsenja sistemske operacije.
     * 
     * @param param Objekat koga validiramo. 
     * @throws Exception ako se pojavi bilo koji tip izuzetka.
     */
    protected abstract void preconditions(Object param) throws Exception;  //provera preduslova zavisi od konkretne so i to znaci da je treba
                      // preusmeriti na konkretnu so

    /**
     * Zapocinjanje transakcije i uspostavljanje konekcije sa bazom podataka.
     * 
     * @throws Exception
     */
    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    // Operation-specific method
    /**
     * Izvrsava sistemsku operaciju nakon pocetka transakcije. 
     * 
     * @param param Objekat nad kojim se izvrsava sistemska operacija.
     * @throws Exception ako se pojavi bilo koji tip izuzetka.
     */
    protected abstract void executeOperation(Object param) throws Exception;
    /**
     * Komitovanje transakcije.
     * 
     * @throws Exception ako se pojavi bilo koji tip izuzetka.
     */
    private void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }
   /**
    * Rollback transakcije.
    * 
    * @throws Exception ako se pojavi bilo koji tip izuzetka.
    */
    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }
    /**
     * Prekida konekciju sa bazom podataka nakon izvrsene sistemske operacije.
     * 
     * @throws Exception
     */
    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }

}

