
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Predstavlja plan treninga za odredjenog clana sportskog kluba.
 * Plan treninga ima svoj id, tip, datum od kada vazi i datum do kada traje, zatim clana za koga se izradjuje i status.
 *
 * @author Sara
 */

public class PlanTreninga implements GenericEntity{
    /**
     * Id plana treninga.
     */
    private long treningID;
    /**
     * Tip plana treninga.
     */
    private TipPlanaTreninga tip;
    /**
     * Datum od kada vazi plan treninga.
     */
    private Date datumOD;
    /**
     * Datum do kada je aktuelan plan treninga.
     */
    private Date datumDO;
    /**
     * Clan za koga je plan treninga namenjen.
     */
    private Clan clan;
    /**
     * Status plana treninga.
     */
    private Status status;
    /**
     *  Neparametrizovani konstruktor koji pravi nov prazan plan treninga.
     */
    public PlanTreninga() {
    }
   /**
    * Parametrizovani konstruktor koji inicijalizuje plan treninga i 
    * postavlja mu prosledjene vrednosti.
    * 
    * @param treningID  Id plana treninga kao Long.
    * @param tip        Tip plana treninga kao TipPlanaTreninga.
    * @param datumOD    Datum od kada vazi plan treninga.
    * @param datumDO    Datum do kada je aktuelan plan treninga.
    * @param clan       Clan za koga je plan treninga namenjen.
    */
    public PlanTreninga(long treningID, TipPlanaTreninga tip, Date datumOD, Date datumDO, Clan clan) {
       /* this.treningID = treningID;
        this.tip = tip;
        this.datumOD = datumOD;
        this.datumDO = datumDO;
        this.clan = clan;*/
    	
    	setTreningID(treningID);setTip(tip);setDatumDO(datumDO);setDatumOD(datumOD);setClan(clan);
    }

   
   /**
    * Vraca id plana treninga.
    * 
    * @return Id plana treninga kao Long.
    */
    public long getTreningID() {
        return treningID;
    }
    /**
     * Postavlja id plana treninga.
     * 
     * @param treningID Id plana treninga kao Long.
     */
    public void setTreningID(long treningID) {
        this.treningID = treningID;
    }
    /**
     * Vraca tip plana treninga.
     * 
     * @return Tip plana treninga kao TipPlanaTreninga.
     */
    public TipPlanaTreninga getTip() {
        return tip;
    }
    /**
     * Postavlja tip plana treninga.
     * 
     * @param tip Tip plana treninga kao TipPlanaTreninga.
     */
    public void setTip(TipPlanaTreninga tip) {
        this.tip = tip;
    }
    /**
     * Vraca datum od kada vazi plan treninga.
     * 
     * @return Datum od kada vazi plan treninga kao Date.
     */
    public Date getDatumOD() {
        return datumOD;
    }
    /**
     * Postavlja datum od kada vazi plan treninga.
     * 
     * @param datumOd Datum od kada vazi plan treninga kao Date.
     * 
     * @throws NullPointerException ako je unet datum od koga vazi plan treninga null
     */
    public void setDatumOD(Date datumOD) {
    	if(datumOD==null) throw new NullPointerException();
    	else this.datumOD = datumOD;
    }
    /**
     * Vraca datum do kog traje plan treninga.
     * 
     * @return Datum do kog traje plan treninga kao Date.
     */
    public Date getDatumDO() {
        return datumDO;
    }
    /**
     * Postavlja datum do kog traje plan treninga.
     * 
     * @param datumDo Datum do kog traje plan treninga kao Date.
     * 
     * @throws NullPointerException ako je unet datum do koga vazi plan ishrane null
     */
    public void setDatumDO(Date datumDO) {
    	if(datumDO==null) throw new NullPointerException();
    	else this.datumDO = datumDO;
    }
    /**
     * Vraca clana kome je plan treninga namenjen.
     * 
     * @return Clan kome je namenjen plan treninga kao Clan.
     */
    public Clan getClan() {
        return clan;
    }
    /**
     * Postavlja clana kome je namenjen plan treninga.
     * 
     * @param clan Clan komem je namenjen plan treninga kao Clan.
     * 
     * @throws NullPointerException ako je unet clan null
     */
    public void setClan(Clan clan) {
    	if(clan==null) throw new NullPointerException();
    	else this.clan = clan;
    }
    /**
     * Vraca status plana treninga.
     * 
     * @return Status kao Status.
     */
    public Status getStatus() {
        return status;
    }
    /**
     * Postavlja status plana treninga.
     * 
     * @param status Status kao Status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    /**
     * Vraca String sa svim podacima o planu treninga sem statusa.
     * 
     * @return Svi podaci o planu treninga sem statusa kao String.
     */
    @Override
    public String toString() {
        return "PlanTreninga{" + "treningID=" + treningID + ", tip=" + tip + ", datumOD=" + datumOD + ", datumDO=" + datumDO + ", clan=" + clan + '}';
    }

    
    
    @Override
    public String getTableName() {
      return "plantreninga";
    }

    @Override
    public String getColumnNamesForInsert() {
      return "tip,datumOd,datumDo,clan";
    }

    @Override
    public String getInsertValues() {
      StringBuilder sb = new StringBuilder();
        sb.append("'").append(tip.toString()).append("',")      
                .append("'").append(new java.sql.Date(datumOD.getTime())).append("',")
                .append("'").append(new java.sql.Date(datumDO.getTime())).append("',")
                .append(clan.getRbClana());
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
       treningID=id;
    }

    @Override
    public List<GenericEntity> createObjectRS(ResultSet rs) throws Exception {
       List<GenericEntity> lista=new ArrayList<>();
        
        while(rs.next()){
        PlanTreninga plan=new PlanTreninga();
        
            plan.setTreningID(rs.getLong("treningID"));
            plan.setTip(TipPlanaTreninga.valueOf(rs.getString("tip")));
            plan.setDatumOD(rs.getDate("datumOD"));
            plan.setDatumDO(rs.getDate("datumDO"));
            lista.add(plan);
        }
        return lista;
    }

    @Override
    public String getConditionForOne() {
        return "clan = " + clan.getRbClana() ;
    }

    @Override
    public String getConditionUpdateDelete() {
      return "treningID ="+treningID;
    }

    @Override
    public GenericEntity createOneObjectRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getConditionSetEdit() {
    Date datumOod= new java.sql.Date(datumOD.getTime());
      Date datumDdo= new java.sql.Date(datumDO.getTime());
       return "tip = '" + tip.toString() + "', datumOD='" + datumOod + "'"+", datumDO = '" + datumDdo 
               + "', clan= "+clan.getRbClana();
    }
    
    
    
}
