
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Sara
 */
public class Obrok implements GenericEntity{
    private long obrokID;
    private String naziv;
    private int kalorije;

    public Obrok() {
    }

    public Obrok(long obrokID, String naziv, int kalorije) {
        this.obrokID = obrokID;
        this.naziv = naziv;
        this.kalorije = kalorije;
    }

    public int getKalorije() {
        return kalorije;
    }

    public void setKalorije(int kalorije) {
        this.kalorije = kalorije;
    }

    public long getObrokID() {
        return obrokID;
    }

    public void setObrokID(long obrokID) {
        this.obrokID = obrokID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return  naziv ;
    }

    @Override
    public String getTableName() {
        return "obrok";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "naziv,kalorije";
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Obrok other = (Obrok) obj;
        if (this.kalorije != other.kalorije) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    

    @Override
    public String getInsertValues() {
         StringBuilder sb = new StringBuilder();
         sb.append("'").append(naziv).append("',")         
           .append(kalorije);
                
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
      obrokID=id;
    }

    @Override
    public List<GenericEntity> createObjectRS(ResultSet rs) throws Exception {
         List<GenericEntity> lista=new ArrayList<>();
        
        while(rs.next()){
        Obrok obrok=new Obrok();
        
            obrok.setObrokID((long) rs.getLong("obrokid"));
            obrok.setNaziv((String) rs.getString("naziv"));
            obrok.setKalorije((int) rs.getInt("kalorije"));
            
            lista.add(obrok);
        }
        return lista;
    }

    @Override
    public String getConditionForOne() {
      return "obrokID="+obrokID;
    }

    @Override
    public GenericEntity createOneObjectRS(ResultSet rs) throws Exception {
      if (rs.next()) {
            obrokID = rs.getLong("obrokID");
            naziv = rs.getString("naziv");
            kalorije = rs.getInt("kalorije");
            return this;
        }
        throw new Exception("Obrok ne postoji u bazi!");
    }

    @Override
    public String getConditionUpdateDelete() {
       return " obrokID="+obrokID;
    }

    @Override
    public String getConditionSetEdit() {
     return "naziv = '" + naziv + "', kalorije=" + kalorije;
    }

    
    
    
}
