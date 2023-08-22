
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sara
 */
public class Ishrana implements GenericEntity {
    
    private PlanIshrane planIshrane;
    private Obrok obrok;
    private VremeObroka vreme;
    private Dan dan;
    private Status status;

    public Ishrana() {
    }

    public Ishrana(PlanIshrane planIshrane, Obrok obrok, VremeObroka vreme, Dan dan) {
        this.planIshrane = planIshrane;
        this.obrok = obrok;
        this.vreme = vreme;
        this.dan = dan;
        
    }

    public PlanIshrane getPlanIshrane() {
        return planIshrane;
    }

    public void setPlanIshrane(PlanIshrane planIshrane) {
        this.planIshrane = planIshrane;
    }

    public Obrok getObrok() {
        return obrok;
    }

    public void setObrok(Obrok obrok) {
        this.obrok = obrok;
    }

    public VremeObroka getVreme() {
        return vreme;
    }

    public void setVreme(VremeObroka vreme) {
        this.vreme = vreme;
    }

    public Dan getDan() {
        return dan;
    }

    public void setDan(Dan dan) {
        this.dan = dan;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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

