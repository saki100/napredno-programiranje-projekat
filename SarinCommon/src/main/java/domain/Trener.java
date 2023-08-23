
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sara
 */
public class Trener implements GenericEntity{
    
    private long id;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Trener() {
    }

    public Trener(long id, String ime, String prezime, String username, String password) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

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

