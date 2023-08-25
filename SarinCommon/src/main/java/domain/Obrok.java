
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja obrok koji ce se koristiti u odredjenom planu ishrane za clanove sportskog kluba.
 * Obrok ima svoj id, naziv i kalorije.
 *
 * @author Sara
 */
public class Obrok implements GenericEntity{
	/**
	 * Id obroka.
	 */
    private long obrokID;
    /**
     * Naziv obroka.
     */
    private String naziv;
    /**
     * Kalorije koje obrok sadrzi.
     */
    private int kalorije;
    /**
     *  Neparametrizovani konstruktor koji pravi nov prazan obrok.
     */
    public Obrok() {
    }
   /**
    * Parametrizovani konstruktor koji inicijalizuje obrok i 
     * postavlja mu prosledjene vrednosti.
     * 
    * @param obrokID    ID obroka kao Long.
    * @param naziv      Naziv obroka kao String.
    * @param kalorije   Kalorije koje obrok sadrzi kao int.
    */
    public Obrok(long obrokID, String naziv, int kalorije) {
        this.obrokID = obrokID;
        this.naziv = naziv;
        this.kalorije = kalorije;
    }
   /**
    * Vraca kalorije koje obrok sadrzi.
    * 
    * @return Kalorije obroka kao int.
    */
    public int getKalorije() {
        return kalorije;
    }
    /**
     * Postavlja kalorije obroka.
     * 
     * @param kalorije Kalorije kao int.
     */
    public void setKalorije(int kalorije) {
        this.kalorije = kalorije;
    }
    /**
     * Vraca id obroka.
     * 
     * @return Id obroka kao Long.
     */
    public long getObrokID() {
        return obrokID;
    }
    /**
     * Postavlja id obroka.
     * 
     * @param obrokID Id obroka kao Long.
     */
    public void setObrokID(long obrokID) {
        this.obrokID = obrokID;
    }
    /**
     * Vraca naziv obroka kao String.
     * 
     * @return Naziv obroka kao String.
     */
    public String getNaziv() {
        return naziv;
    }
    /**
     * Postavlja naziv obroka.
     * 
     * @param naziv Naziv obroka kao String.
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    /**
     * Vraca String o nazivu obroka.
     * 
     * @return Naziv obroka kao String.
     */
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

    /**
     * Poredi dva obroka po nazivu i kalorijama.
     * 
     * @param obj Drugi obrok.
     * 
     * @return true ako su naziv i kalorije isti, a false inace.
     */
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
