
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Sara
 */
public class PlanIshrane implements GenericEntity{
    
    private long ishranaID;
    private TipPlanaIshrane tip;
    private Date datumOd;
    private Date datumDo;
    private Clan clan;
    private Status status;
    
    public PlanIshrane() {
    }

    public PlanIshrane(long ishranaID, TipPlanaIshrane tip, Date datumOd, Date datumDo, Clan clan) {
        this.ishranaID = ishranaID;
        this.tip = tip;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.clan = clan;
    }

    

    public long getIshranaID() {
        return ishranaID;
    }

    public void setIshranaID(long ishranaID) {
        this.ishranaID = ishranaID;
    }

    public TipPlanaIshrane getTip() {
        return tip;
    }

    public void setTip(TipPlanaIshrane tip) {
        this.tip = tip;
    }

    

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
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

