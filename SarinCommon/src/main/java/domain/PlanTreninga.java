
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sara
 */

public class PlanTreninga implements GenericEntity{
    
    private long treningID;
    private TipPlanaTreninga tip;
    private Date datumOD;
    private Date datumDO;
    private Clan clan;
    private Status status;
    
    public PlanTreninga() {
    }

    public PlanTreninga(long treningID, TipPlanaTreninga tip, Date datumOD, Date datumDO, Clan clan) {
        this.treningID = treningID;
        this.tip = tip;
        this.datumOD = datumOD;
        this.datumDO = datumDO;
        this.clan = clan;
    }

   

    public long getTreningID() {
        return treningID;
    }

    public void setTreningID(long treningID) {
        this.treningID = treningID;
    }

    public TipPlanaTreninga getTip() {
        return tip;
    }

    public void setTip(TipPlanaTreninga tip) {
        this.tip = tip;
    }

    

    public Date getDatumOD() {
        return datumOD;
    }

    public void setDatumOD(Date datumOD) {
        this.datumOD = datumOD;
    }

    public Date getDatumDO() {
        return datumDO;
    }

    public void setDatumDO(Date datumDO) {
        this.datumDO = datumDO;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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
