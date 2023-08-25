
package repository;

import java.util.List;

/**
 * Interfejs sa metodama koje pozivaju sistemske operacije za rad sa bazom podataka.
 * Metode su implementirane u klasi RepositoryDBGeneric koja nasledjuje ovaj interfejs.
 * 
 * @author Sara
 * @param <T>
 */
public interface Repository<T> {
	/**
	 * Vraca sve objekte iz baze.
	 * 
	 * @param param Parametar koji ukazuje koje su klase objekti koje treba vratiti iz baze podataka.
	 * @return Lista domenskih objekata
	 * @throws Exception ako se desi bilo koja vrsta izuzetka.
	 */
    List<T> getAll(T param) throws Exception;
    /**
     * Dodaje novi objekat u bazu podataka.
     * 
     * @param param Parametar koji ukazuje koje je klase objekat kojeg dodajemo u bazu.
     * @throws Exception ako se desi bilo koja vrsta izuzetka.
     */
    void add(T param) throws Exception;
    /**
     * Menja podatke o objektu u bazi podataka, tacnije neke od atributa objekta.
     * 
     * @param param Parametar koji ukazuje koje je klase objekat kome menjamo vrednosti.
     * @throws Exception ako se desi bilo koja vrsta izuzetka.
     */
    void edit(T param) throws Exception;
    /**
     * Brise iz baze podataka objekat po odredjenim uslovima.
     * 
     * @param param Parametar koji ukazuje koje je klase objekat koga brisemo iz baze podataka.
     * @throws Exception ako se desi bilo koja vrsta izuzetka.
     */
    void delete(T param)throws Exception;
    /**
     * Vraca objekat iz baze podataka koji se pretrazuje prema jedinstvenoj vrednosti toga objekta, odnosno primarnom kljucu.
     * 
     * @param param Parametar koji ukazuje koje je klase objekat nad kime izvrsavamo sistemsku operaciju.
     * @return Objekat koji smo pretrazivali iz baze podataka.
     * @throws Exception ako se desi bilo koja vrsta izuzetka.
     */
    T getObject(T param) throws Exception;
    //List<T> getAll();  sklonila jer nisam sigurna za sta sluzi, vrati ako zatreba
    /**
     * Vraca sve objekte iz baze koji zadovoljavaju odredjene uslove.
     * 
     * @param param Parametar koji ukazuje koje su klase objekti koje treba vratiti iz baze podataka.
     * @return Lista domenskih objekata koji ispunjavaju odredjene uslove.
     * @throws Exception ako se desi bilo koja vrsta izuzetka.
     */
   List<T> getAllCondition(T param) throws Exception;
   /**
    * Dodaje agregirani objekat u bazu podataka.
    * 
    * @param param Parametar koji ukazuje koje je klase objekat kog treba dodati u bazu podataka.
    * @throws Exception ako se desi bilo koja vrsta izuzetka.
    */
   void addAsociation(T param) throws Exception;
}
