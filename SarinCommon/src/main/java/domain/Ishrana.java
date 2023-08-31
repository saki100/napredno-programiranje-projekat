
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja Ishranu kao agregiranu klasu klasa PlanIshrane i Obrok.
 * Ishrana ima svoj plan ishrane, obrok, vreme obroka i dan konzumiranja kao primarne kljuceve.
 * Takodje ima i status koji se postavlja shodno potrebama da li zelimo da menjamo Ishranu, dodajemo novu ili brisemo postojecu.
 *
 * @author Sara
 */
public class Ishrana implements GenericEntity {
    /**
     * Plan ishrane potreban zbog podatka o clanu na koji se odnosi, kao i datuma pocetka i kraja vazenja cele Ishrane.
     */
    private PlanIshrane planIshrane;
    /**
     * Obrok ishrane.
     */
    private Obrok obrok;
    /**
     * Vreme u koje ce se obrok jesti.
     */
    private VremeObroka vreme;
    /**
     * Dan u nedelji kada ce obrok biti konzumiran.
     */
    private Dan dan;
    /**
     * Status ishrane, moze biti INSERT, UPDATE ili DELETE.
     */
    private Status status;
    /**
     * Neparametrizovani konstruktor koji pravi novu praznu Ishranu.
     */
    public Ishrana() {
    }
    /**
     * Parametrizovani konstruktor koji inicijalizuje Ishranu i 
     * postavlja joj prosledjene vrednosti.
     * 
     * @param planIshrane  Plan ishrane kao PlanIshrane.
     * @param obrok        Obrok kao Obrok.
     * @param vreme        Vreme konzumiranja obroka kao VremeObroka.
     * @param dan          Dan u nedelji kao Dan.
     */
    public Ishrana(PlanIshrane planIshrane, Obrok obrok, VremeObroka vreme, Dan dan) {
        /*this.planIshrane = planIshrane;
        this.obrok = obrok;
        this.vreme = vreme;
        this.dan = dan;*/
    	
    	setPlanIshrane(planIshrane); setObrok(obrok); setVreme(vreme); setDan(dan);
        
    }
    /**
     * Vraca plan ishrane.
     * 
     * @return Plan ishrane kao PlanIshrane.
     */
    public PlanIshrane getPlanIshrane() {
        return planIshrane;
    }
    /**
     * Postavlja plan ishrane.
     * @param planIshrane Plan ishrane za odredjenog clana sportskog kluba kao PlanIshrane.
     */
    public void setPlanIshrane(PlanIshrane planIshrane) {
    	if(planIshrane==null) throw new NullPointerException();
    	else this.planIshrane = planIshrane;
    }
   /**
    * Vraca obrok koji ce se konzumirati.
    * 
    * @return Obrok kao Obrok.
    */
    public Obrok getObrok() {
        return obrok;
    }
    /**
     * Postavlja obrok na odedjenu vrednost.
     * 
     * @param obrok Obrok koji ce se konzumirati kao Obrok.
     */
    public void setObrok(Obrok obrok) {
    	if(obrok==null) throw new NullPointerException();
    	else this.obrok = obrok;
    }
    /**
     * Vraca da li se obrok jede za dorucak, rucak, veceru ili uzinu kao VremeObroka.
     * 
     * @return Vreme obroka kao VremeObroka.
     */
    public VremeObroka getVreme() {
        return vreme;
    }
    /**
     * Postavlja vreme obroka kada ce se konzumirati, za dorucak, rucak, veceru ili uzinu.
     * 
     * @param vreme Vreme kada ce clan konzumirati obrok kao VremeObroka.
     */
    public void setVreme(VremeObroka vreme) {
        this.vreme = vreme;
    }
    /**
     * Vraca dan u nedelji kada se obrok konzumira.
     * 
     * @return Dan u nedelji kao Dan.
     */
    public Dan getDan() {
        return dan;
    }
    /**
     * Postavlja dan u nedelji kada treba konzumirati obrok.
     * 
     * @param dan dan u nedelji kao Dan.
     */
    public void setDan(Dan dan) {
        this.dan = dan;
    }
    /**
     * Vraca status Ishrane.
     * 
     * @return Status kao Status.
     */
    public Status getStatus() {
        return status;
    }
   /**
    * Postavlja status Ishrane.
    * 
    * @param status Status kao Status.
    */
    public void setStatus(Status status) {
        this.status = status;
    }
    /**
     * Vraca String sa svim podacima o Ishrani sem njenog Statusa.
     * 
     *  @return Svi podaci o ishrani sem Statusa.
     */
    @Override
    public String toString() {
        return "Ishrana{" + "planIshrane=" + planIshrane + ", obrok=" + obrok + ", vreme=" + vreme + ", dan=" + dan + '}';
    }

    
    @Override
    public String getTableName() {
        return "ishrana";
    }

    @Override
    public String getColumnNamesForInsert() {
      return "planIshraneID,obrokID,vreme,dan";
    }

    @Override
    public String getInsertValues() {
         StringBuilder sb = new StringBuilder();
        sb.append(planIshrane.getIshranaID()).append(",")         //da li dodati rbClana
                .append(obrok.getObrokID()).append(",")
                .append("'").append(vreme.toString()).append("',")
                .append("'").append(dan.toString()).append("'");
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
    }

    @Override
    public List<GenericEntity> createObjectRS(ResultSet rs) throws Exception {
       List<GenericEntity> lista=new ArrayList<>();
        
        while(rs.next()){
        Ishrana ishrana=new Ishrana();
        
           Obrok o=new Obrok();
           o.setObrokID(rs.getLong("obrokID"));
            ishrana.setObrok(o);
            //PlanIshrane pl=new PlanIshrane();
           // pl.setIshranaID(rs.getLong("ishranaID"));
            //ishrana.setPlanIshrane(pl);
            ishrana.setVreme(VremeObroka.valueOf(rs.getString("vreme")));
            ishrana.setDan(Dan.valueOf(rs.getString("dan")));
            
            lista.add(ishrana);
        }
        return lista;
    }

    @Override
    public String getConditionForOne() {
      return "planIshraneID="+planIshrane.getIshranaID();
    }

    @Override
    public String getConditionUpdateDelete() {
     return "planIshraneID= "+planIshrane.getIshranaID()+" and vreme='"+vreme.toString()+"' and dan='"+dan.toString()+"'";
    }

    @Override
    public GenericEntity createOneObjectRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getConditionSetEdit() {
     return "obrokID= "+obrok.getObrokID();
    }
    
    
}

