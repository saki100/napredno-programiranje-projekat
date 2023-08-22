
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Sara
 */
public interface GenericEntity extends Serializable{
    
    String getTableName();

    String getColumnNamesForInsert();

    String getInsertValues();

    void setId(Long id);

    public List<GenericEntity> createObjectRS(ResultSet rs) throws Exception;

    String getConditionForOne();
    
    String getConditionUpdateDelete();

    public GenericEntity createOneObjectRS(ResultSet rs)throws Exception ;

    String getConditionSetEdit();


    
    
    
}
