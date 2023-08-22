
package domain;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;   //!!!!!!!!!!!!!
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sara
 */
public class Clan implements GenericEntity{

    private long rbClana;
    private String ime;
    private String prezime;
    private String email;
    private Date datumRodjenja;
    private String brojTelefona;
    private Grad grad;

    

    public Clan() {
    }

    public Clan(long rbClana, String ime, String prezime, String email, Date datumRodjenja, String brojTelefona, Grad grad) {
        this.rbClana = rbClana;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.datumRodjenja = datumRodjenja;
        this.brojTelefona = brojTelefona;
        this.grad = grad;
    }

    

    
    
    public long getRbClana() {
        return rbClana;
    }

    public void setRbClana(long rbClana) {
        this.rbClana = rbClana;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    } 
    
    public void setGrad(Grad grad) {
		this.grad = grad;
	}
    
    public Grad getGrad() {
		return grad;
	}

    @Override
	public String toString() {
		return ime+" "+prezime;
	}

	@Override
    public String getTableName() {
       return "clan";
    }

    @Override
    public String getColumnNamesForInsert() {
           
        return "ime,prezime,email,datumRodjenja,brojTelefona,gradID";  //proveri da li staviti i rbClana
    }

    @Override
    public String getInsertValues() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(ime).append("',")         //da li dodati rbClana
                .append("'").append(prezime).append("',")
                .append("'").append(email).append("',")
                .append("'").append(new java.sql.Date(datumRodjenja.getTime())).append("',")
                .append("'").append(brojTelefona).append("',")
        		.append(grad.getGradID()+"");
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
      rbClana=id;
    }

    @Override
    public List<GenericEntity> createObjectRS(ResultSet rs) throws Exception{
        
        List<GenericEntity> lista=new ArrayList<>();
        
        while(rs.next()){
        	Grad grad = new Grad();
        	grad.setGradID(rs.getLong("gradID"));
        	
        	Clan clan=new Clan();
            clan.setRbClana(rs.getLong("rbClana"));
            clan.setIme(rs.getString("ime"));
            clan.setPrezime(rs.getString("prezime"));
            clan.setEmail(rs.getString("email"));
            clan.setDatumRodjenja(rs.getDate("datumRodjenja"));
            clan.setBrojTelefona(rs.getString("brojTelefona"));
            clan.setGrad(grad);
            
            lista.add(clan);
        }
        return lista;
    }

    @Override
    public String getConditionForOne() {
       
        return "ime = '" + ime + "' and prezime='" + prezime + "'";
        
    }
    
    
    
    @Override
    public GenericEntity createOneObjectRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getConditionUpdateDelete() {
         return "rbClana ="+rbClana;
    }

    @Override
    public String getConditionSetEdit() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       return "ime = '" + ime + "', prezime='" + prezime + "'"+", email = '" + email 
               + "', datumRodjenja='" +sdf.format(datumRodjenja)+"'"  + " , brojTelefona='" + brojTelefona+ "', gradID="+grad.getGradID();
    }

    
    
    
}

