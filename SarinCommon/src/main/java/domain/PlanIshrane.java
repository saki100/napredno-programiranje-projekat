
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja plan ishrane za odredjenog clana sportskog kluba.
 * Plan ishrane ima svoj id, tip, datum od kada vazi i datum do kada traje, zatim clana za koga se izradjuje i status.
 *
 * @author Sara
 */
public class PlanIshrane implements GenericEntity{
    /**
     * Id plana ishrane.
     */
    private long ishranaID;
    /**
     * Tip plana ishrane.
     */
    private TipPlanaIshrane tip;
    /**
     * Datum od kada vazi plan ishrane.
     */
    private Date datumOd;
    /**
     * Datum do kada je aktuelan plan ishrane.
     */
    private Date datumDo;
    /**
     * Clan za koga je plan ishrane namenjen.
     */
    private Clan clan;
    /**
     * Status plana ishrane.
     */
    private Status status;
    /**
     *  Neparametrizovani konstruktor koji pravi nov prazan plan ishrane.
     */
    public PlanIshrane() {
    }
    /**
     * Parametrizovani konstruktor koji inicijalizuje plan ishrane i 
     * postavlja mu prosledjene vrednosti.
     * 
     * @param ishranaID   Id plana ishrane kao Long.
     * @param tip         Tip plana ishrane kao TipPlanaIshrane.
     * @param datumOd     Datum od kada vazi plan ishrane.
     * @param datumDo     Datum do kada je aktuelan plan ishrane.
     * @param clan        Clan za koga je plan ishrane namenjen.
     */
    public PlanIshrane(long ishranaID, TipPlanaIshrane tip, Date datumOd, Date datumDo, Clan clan) {
        /*this.ishranaID = ishranaID;
        this.tip = tip;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.clan = clan;*/
    	
    	setIshranaID(ishranaID); setTip(tip); setDatumOd(datumOd); setDatumDo(datumDo); setClan(clan);
    }

    /**
     * Vraca id plana ishrane.
     * 
     * @return id plana ishrane kao Long.
     */
    public long getIshranaID() {
        return ishranaID;
    }
    /**
     * Postavlja id plana ishrane.
     * 
     * @param ishranaID Id plana ishrane kao Long.
     */
    public void setIshranaID(long ishranaID) {
        this.ishranaID = ishranaID;
    }
   /**
    * Vraca tip plana ishrane.
    * 
    * @return Tip plana ishrane kao TipPlanaIshrane.
    */
    public TipPlanaIshrane getTip() {
        return tip;
    }
    /**
     * Postavlja tip plana ishrane.
     * 
     * @param tip Tip plana ishrane kao TipPlanaIshrane.
     */
    public void setTip(TipPlanaIshrane tip) {
        this.tip = tip;
    }
    /**
     * Vraca datum od kada vazi plan ishrane.
     * 
     * @return Datum od kada vazi plan ishrane kao Date.
     */
    public Date getDatumOd() {
        return datumOd;
    }
    /**
     * Postavlja datum od kada vazi plan ishrane.
     * 
     * @param datumOd Datum od kada vazi plan ishrane kao Date.
     * 
     * @throws NullPointerException ako je unet datum od koga vazi plan ishrane null
     */
    public void setDatumOd(Date datumOd) {
    	//if(datumOd.after(datumDo)) throw new IllegalArgumentException("Datum od mora biti pre datuma do.");
    	if(datumOd==null) throw new NullPointerException();
    	else this.datumOd = datumOd;
    }
    /**
     * Vraca datum do kog traje plan ishrane.
     * 
     * @return Datum do kog traje plan ishrane kao Date.
     */
    public Date getDatumDo() {
        return datumDo;
    }
    /**
     * Postavlja datum do kog traje plan ishrane.
     * 
     * @param datumDo Datum do kog traje plan ishrane kao Date.
     * 
     * @throws NullPointerException ako je unet datum do koga vazi plan ishrane null
     */
    public void setDatumDo(Date datumDo) {
    	//if(datumDo.before(datumOd)) throw new IllegalArgumentException("Datum do mora biti posle datuma od.");
    	if(datumDo==null) throw new NullPointerException();

        this.datumDo = datumDo;
    }
    /**
     * Vraca clana kome je plan ishrane namenjen.
     * 
     * @return Clan kome je namenjen plan ishrane kao Clan.
     */
    public Clan getClan() {
        return clan;
    }
    /**
     * Postavlja clana kome je namenjen plan ishrane.
     * 
     * @param clan Clan komem je namenjen plan ishrane kao Clan.
     * 
     * @throws NullPointerException ako je unet clan null
     */
    public void setClan(Clan clan) {
    	if(clan==null) throw new NullPointerException();
        this.clan = clan;
    }
   /**
    * Vraca status plana ishrane.
    * 
    * @return Status kao Status.
    */
    public Status getStatus() {
        return status;
    }
    /**
     * Postavlja status plana ishrane.
     * 
     * @param status Status kao Status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    
    /**
     * Vraca String sa svim podacima o planu ishrane sem statusa.
     * 
     * @return Svi podaci o planu ishrane sem statusa kao String.
     */
    @Override
    public String toString() {
        return "PlanIshrane{" + "ishranaID=" + ishranaID + ", tip=" + tip + ", datumOd=" + datumOd + ", datumDo=" + datumDo + ", clan=" + clan + '}';
    }

    
    
    @Override
    public String getTableName() {
      return "planIshrane";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "tip,datumOd,datumDo,clan";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(tip.toString()).append("',")      
                .append("'").append(new java.sql.Date(datumOd.getTime())).append("',")
                .append("'").append(new java.sql.Date(datumDo.getTime())).append("',")
                .append(clan.getRbClana());
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
     ishranaID=id;
    }

    @Override
    public List<GenericEntity> createObjectRS(ResultSet rs) throws Exception {
        List<GenericEntity> lista=new ArrayList<>();
        
        while(rs.next()){
        PlanIshrane plan=new PlanIshrane();
        
            plan.setIshranaID(rs.getLong("ishranaID"));
            plan.setTip(TipPlanaIshrane.valueOf(rs.getString("tip")));
            plan.setDatumOd(rs.getDate("datumOd"));
            plan.setDatumDo(rs.getDate("datumDo"));
            lista.add(plan);
        }
        return lista;
    }

    @Override
    public String getConditionForOne() {
        System.out.println(clan.getRbClana());
         return "clan = " + clan.getRbClana() ;
    }

    @Override
    public String getConditionUpdateDelete() {
        return "ishranaID ="+ishranaID;
    }

    @Override
    public GenericEntity createOneObjectRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getConditionSetEdit() {
       Date datumOod= new java.sql.Date(datumOd.getTime());
      Date datumDdo= new java.sql.Date(datumDo.getTime());
       return "tip = '" + tip.toString() + "', datumOd='" + datumOod + "'"+", datumDo = '" + datumDdo 
               + "', clan= "+clan.getRbClana();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
        final PlanIshrane other = (PlanIshrane) obj;
        if (this.ishranaID != other.ishranaID) {
            return false;
        }
        if (this.tip != other.tip) {
            return false;
        }
        if (!Objects.equals(this.datumOd, other.datumOd)) {
            return false;
        }
        if (!Objects.equals(this.datumDo, other.datumDo)) {
            return false;
        }
        return Objects.equals(this.clan, other.clan);
    }


    
    
    
}

