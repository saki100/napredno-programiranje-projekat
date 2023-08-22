
package operation;

import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.RepositoryDBGeneric;

/**
 *
 * @author Cartman
 */
public abstract class AbstractGenericOperation {  //apstraktna klasa ne moze da ima instancu ali moze da ima konstruktor

    protected Repository repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }

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
    protected abstract void preconditions(Object param) throws Exception;  //provera preduslova zavisi od konkretne so i to znaci da je treba
                      // preusmeriti na konkretnu so

    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    // Operation-specific method
    protected abstract void executeOperation(Object param) throws Exception;

    private void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }

}

