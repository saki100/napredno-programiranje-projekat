
package domain;


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
 * Predstavlja clana sportskog kluba. Clan ima svoj redni broj, ime, 
 * prezime, email, datum rodjenja, br telefona i grad u kome zivi.
 *
 * @author Sara
 */
public class Clan implements GenericEntity{
    
	/**
	 * Redni broj clana.
	 */
    private long rbClana;
    /**
     * Ime clana.
     */
    private String ime;
    /**
     * Prezime clana.
     */
    private String prezime;
    /**
     * Email clana.
     */
    private String email;
    /**
     * Datum rodjenja clana.
     */
    private Date datumRodjenja;
    /**
     * Kontakt broj telefona clana kao String.
     */
    private String brojTelefona;
    /**
     * Grad u kome clan zivi.
     */
    private Grad grad;
    
    /**
     *  Neparametrizovani konstruktor koji pravi novog praznog clana.
     */
    public Clan() {
    }
    /**
     * Parametrizovani konstruktor koji inicijalizuje clana i 
     * postavlja mu prosledjene vrednosti.
     * 
     * @param rbClana        Redni broj clana kao Long.
     * @param ime            Ime clana kao String.
     * @param prezime        Prezime clana kao String.
     * @param email          Email clana kao String.
     * @param datumRodjenja  Datum rodjenja clana kao Date.
     * @param brojTelefona   Broj telefona clana kao String.
     * @param grad           Grad u kome clan zivi tipa Grad.
     */
    public Clan(long rbClana, String ime, String prezime, String email, Date datumRodjenja, String brojTelefona, Grad grad) {
        /*this.rbClana = rbClana;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.datumRodjenja = datumRodjenja;
        this.brojTelefona = brojTelefona;
        this.grad = grad;*/
    	
    	setRbClana(rbClana);  setEmail(email);       setGrad(grad);
    	setIme(ime);          setDatumRodjenja(datumRodjenja);
    	setPrezime(prezime);   setBrojTelefona(brojTelefona);
    }

    /**
     * Vraca redni broj clana.
     * 
     * @return Redni broj clana kao Long.
     */
    public long getRbClana() {
        return rbClana;
    }
   /**
    * Postavlja redni broj clana.
    * 
    * @param rbClana Redni broj clana.
    */
    public void setRbClana(long rbClana) {
        this.rbClana = rbClana;
    }
    /**
     * Vraca ime clana.
     * 
     * @return Ime clana kao String.
     */
    public String getIme() {
        return ime;
    }
    /**
     * Postavlja ime clana.
     * 
     * @param ime Ime clana kao String
     */
    public void setIme(String ime) {
    	if(ime==null) throw new NullPointerException();
    	if(ime.length()<3 || ime.equals("")) throw new IllegalArgumentException();
        this.ime = ime;
    }
    /**
     * Vraca prezime clana.
     * 
     * @return Prezime clana kao String.
     */
    public String getPrezime() {
        return prezime;
    }
   /**
    * Postavlja prezime clana.
    * 
    * @param prezime Prezime clana kao String.
    */
    public void setPrezime(String prezime) {
    	if(prezime==null) throw new NullPointerException();
    	if(prezime.length()<3 || prezime.equals("")) throw new IllegalArgumentException();
        this.prezime = prezime;
    }
    /**
     * Vraca email clana.
     * 
     * @return Email clana kao String.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Postavlja email clana.
     * 
     * @param email Email clana kao String.
     */
    public void setEmail(String email) {
    	if(email==null) throw new NullPointerException();
    	if(!email.contains("@")) this.email=null;
    	
    	else this.email = email;
    }
    /**
     * Vraca datum rodjenja clana kao Date.
     * 
     * @return Datum rodjenja clana kao Date.
     */
    public Date getDatumRodjenja() {
        return datumRodjenja;
    }
   /**
    * Postavlja datum rodjenja clana.
    * 
    * @param datumRodjenja Datum rodjenja clana kao String.
    */
    public void setDatumRodjenja(Date datumRodjenja) {
    	if(datumRodjenja==null) throw new NullPointerException();
    	if(datumRodjenja.after(new Date())) throw new IllegalArgumentException("Nemoguc datum rodjenja.");
        
    	this.datumRodjenja = datumRodjenja;
    }
    /**
     * Vraca broj telefona clana.
     * 
     * @return Broj telefona clana kao String.
     */
    public String getBrojTelefona() {
        return brojTelefona;
    }
    /**
     * Postavlja abroj telefona clana.
     * 
     * @param brojTelefona Broj telefona clana kao String.
     */
    public void setBrojTelefona(String brojTelefona) {
    	if(brojTelefona==null) throw new NullPointerException();
    	//Sledeca linija koda je zakomentarisana jer pravi problem u testovima za sistemske operacije
    	// posto prvobitno nije zamisljeno tako, medjutim ostavila sam cisto zbog logike da vidite kako bih odradila
    	//ostavicu zakomentarisan i odgovarajuci test
    	//if(!brojTelefona.contains("+381")) throw new IllegalArgumentException("Broj telefona mora poceti sa '+381'");
        this.brojTelefona = brojTelefona;
    } 
    /**
     * Postavlja grad u kome zivi clan.
     * 
     * @param grad Grad u kome zivi clan kao Grad.
     */
    public void setGrad(Grad grad) {
		this.grad = grad;
	}
    /**
     * Vraca grad u kome zivi clan.
     * 
     * @return Grad u kome zivi clan.
     */
    public Grad getGrad() {
		return grad;
	}
    /**
     * Vraca String sa svim podacima o clanu.
     * 
     * @return Svi podaci o clanu kao String.
     */
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

