
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavja trenera koji upravlja sportskim klubom odnosno celim sistemom.
 * 
 * @author Sara
 */
public class Trener implements GenericEntity{
    /**
     * Id trenera.
     */
    private long id;
    /**
     * Ime trenera.
     */
    private String ime;
    /**
     * Prezime trenera.
     */
    private String prezime;
    /**
     * Username trenera koji koristi pri logovanju na sistem.
     */
    private String username;
    /**
     * Password trenera koji koristi pri logovanju na sistem.
     */
    private String password;
    /**
     *  Neparametrizovani konstruktor koji pravi novog praznog trenera.
     */
    public Trener() {
    }
   /**
    * Parametrizovani konstruktor koji inicijalizuje trenera i 
    * postavlja mu prosledjene vrednosti.
    * 
    * @param id         Id trenera kao Long.
    * @param ime        Ime trenera kao String.
    * @param prezime    Prezime trenera kao String.
    * @param username   Username trenera kao String.
    * @param password   Password trenera kao String.
    */
    public Trener(long id, String ime, String prezime, String username, String password) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }
    /**
     * Vraca id trenera.
     * 
     * @return Id trenera kao Long.
     */
    public long getId() {
        return id;
    }
   /**
    * Postavlja id trenera.
    * 
    * @param id Id trenera kao Long.
    */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * Vraca ime trenera.
     * 
     * @return Ime trenera kao String.
     */
    public String getIme() {
        return ime;
    }
    /**
     * Postavlja ime trenera.
     * 
     * @param ime Ime trenera kao String
     */
    public void setIme(String ime) {
        this.ime = ime;
    }
    /**
     * Vraca prezime trenera.
     * 
     * @return Prezime trenera kao String.
     */
    public String getPrezime() {
        return prezime;
    }
    /**
     * Postavlja prezime trenera.
     * 
     * @param prezime Prezime trenera kao String.
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    /**
     * Vraca username trenera.
     * 
     * @return Username trenera kao String.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Postavlja username trenera.
     * 
     * @param username Username trenera kao String.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Vraca password trenera.
     * 
     * @return Password trenera kao String.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Postavlja password trenera.
     * 
     * @param password Password trenera kao String.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    
    /**
     * Vraca String sa svim podacima o treneru.
     * 
     * @return Svi podaci o treneru kao String.
     */
    @Override
	public String toString() {
		return "Trener [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", username=" + username + ", password="
				+ password + "]";
	}

	@Override
    public String getTableName() {
        
        return "trener";
    }

    @Override
    public String getColumnNamesForInsert() {
      
        return "ime,prezime,username,password";  //proveri da li stavljas i id
    }

    @Override
    public String getInsertValues() {
      StringBuilder sb = new StringBuilder();
        sb.append("'").append(ime).append("',")         //da li dodati id
                .append("'").append(prezime).append("',")
                .append("'").append(username).append("',")
                .append("'").append(password).append("'");
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
       this.id=id;
    }

    @Override
    public List<GenericEntity> createObjectRS(ResultSet rs) throws Exception {
        List<GenericEntity> lista=new ArrayList<>();
        
        while(rs.next()){
        Trener trener=new Trener();
        
            trener.setId(rs.getLong("id"));
            trener.setIme(rs.getString("ime"));
            trener.setPrezime(rs.getString("prezime"));
            trener.setUsername(rs.getString("username"));
            trener.setPassword(rs.getString("password"));
            
            lista.add(trener);
        }
        return lista;
    }

    @Override
    public String getConditionForOne() {
           
        return "username = '" + username + "' and password='" + password + "'";

    }

    @Override
    public GenericEntity createOneObjectRS(ResultSet rs) throws Exception{
          
        if (rs.next()) {
            id = rs.getLong("id");
            ime = rs.getString("ime");
            prezime = rs.getString("prezime");
            username = rs.getString("username");
            password = rs.getString("password");
            return this;
        }
        throw new Exception("Trener ne postoji u bazi!");
    }

    @Override
    public String getConditionUpdateDelete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getConditionSetEdit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
}

