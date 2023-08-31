package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * Predstavlja grad u kome clan sportskog kluba zivi.
 * Grad ima svoj id i naziv.
 * 
 * @author Sara
 *
 */
public class Grad implements GenericEntity{
	/**
	 * Id grada.
	 */
	private long gradID;
	/**
	 * Naziv grada.
	 */
	private String nazivGrada;
	/**
     *  Neparametrizovani konstruktor koji pravi nov prazan grad.
     */
	public Grad() {
		
	}
	/**
	 * Parametrizovani konstruktor koji inicijalizuje grad i 
     * postavlja mu prosledjene vrednosti.
     * 
	 * @param gradID      Id grada kao Long.
	 * @param nazivGrada  Naziv grada kao String.
	 */
	public Grad(long gradID, String nazivGrada) {
		/*this.gradID = gradID;
		this.nazivGrada = nazivGrada;*/
		setGradID(gradID);
		setNazivGrada(nazivGrada);
	}

	/**
	 * Vraca id grada.
	 * 
	 * @return Id grada kao Long.
	 */
	public long getGradID() {
		return gradID;
	}
    /**
     * Postavlja id grada.
     * 
     * @param gradID Id grada kao Long.
     */
	public void setGradID(long gradID) {
		this.gradID = gradID;
	}
   /**
    * Vraca naziv grada.
    * 
    * @return Naziv grada kao String.
    */
	public String getNazivGrada() {
		return nazivGrada;
	}
   /**
    * Postavlja naziv grada.
    * 
    * @param nazivGrada Naziv grada kao String.
    */
	public void setNazivGrada(String nazivGrada) {
		this.nazivGrada = nazivGrada;
	}

	@Override
	public String getTableName() {
		return "grad";
	}

	@Override
	public String getColumnNamesForInsert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInsertValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<GenericEntity> createObjectRS(ResultSet rs) throws Exception {
		List<GenericEntity> lista=new ArrayList<>();
        
        while(rs.next()){
        	Grad grad = new Grad();
        	
        	grad.setGradID(rs.getLong("gradID"));
        	grad.setNazivGrada(rs.getString("nazivGrada"));
        	lista.add(grad);
        	
        }
        return lista;
	}

	@Override
	public String getConditionForOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConditionUpdateDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntity createOneObjectRS(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConditionSetEdit() {
		// TODO Auto-generated method stub
		return null;
	}

}
