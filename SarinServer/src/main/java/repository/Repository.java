
package repository;

import java.util.List;

/**
 *
 * @author Sara
 * @param <T>
 */
public interface Repository<T> {
    List<T> getAll(T param) throws Exception;
    void add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param)throws Exception;
    T getObject(T param) throws Exception;
    //List<T> getAll();  sklonila jer nisam sigurna za sta sluzi, vrati ako zatreba
   List<T> getAllCondition(T param) throws Exception;
   void addAsociation(T param) throws Exception;
}
