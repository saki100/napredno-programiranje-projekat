
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 * Interfejs koga implementiraju sve domenske klase.
 * Sadrzi metode koje omogucavaju izvrsavanje sistemskih operacija.
 * 
 * @author Sara
 */
public interface GenericEntity extends Serializable{
    /**
     * Vraca naziv tabele baze podataka za odredjeni objekat kao String vrednost.
     * @return
     */
    String getTableName();
    /**
     * Vraca nazive kolona potrebnih za ubacivanje novog objekta u bazu podataka
     * odvojene zarezom kao String.
     * 
     * @return Nazive kolona pri kreiranju nove n-torke u bazi podataka kao String.
     */
    String getColumnNamesForInsert();
    /**
     * Vraca vrednosti atributa objekta kog cemo ubaciti u bazu podataka.
     * 
     * @return Vrednosti atributa za insertovanje objekta u bazu kao String.
     */
    String getInsertValues();
    /**
     * Postavlja id objektima koji su podeseni kao Autoinkrement u bazi
     * i potrebno je vratiti iz baze taj podatak.
     * 
     * @param id Id objekta kao Long.
     */
    void setId(Long id);
    /**
     * Vraca Listu objekata procitanih iz prosledjenog ResultSet-a.
     * 
     * @param rs Skup rezultata koji predstavlja rezultat SQL upita.
     * @return Lista objekata procitanih iz datog ResultSet-a
     * @throws java.lang.Exception ako je neodgovarajuca vrednost kljuca.
     */
    public List<GenericEntity> createObjectRS(ResultSet rs) throws Exception;
    /**
     * Vraca uslov odnosno atribute po kojima se pretrazuju objekti u bazi kao String.
     * 
     * @return Uslov po kome se pretrazuju objekti u bazi kao String.
     */
    String getConditionForOne();
    /**
     * Vraca uslov za izmenu i brisanje n-torke iz baze.
     *  
     * @return Uslov za izmenu i brisanje objekta iz baze.
     */
    String getConditionUpdateDelete();
   /**
    * Vraca objekat procitanog iz primljenog ResultSet-a.
    * @param rs ResultSet koji predstavlja rezultat SQL upita.
    * @return Objekat procitan iz datog ResultSet-a.
    * @throws java.lang.Exception ako je neodgovarajuca vrednost kljuca.
    */
    public GenericEntity createOneObjectRS(ResultSet rs)throws Exception ;
   /**
    * Vraca vrednost na koju postavljamo objekat koga menjamo u bazi podataka kao String.
    * 
    * @return Vrednost na koju postavljamo objekat koga menjamo u bazi kao String.
    */
    String getConditionSetEdit();


    
    
    
}
