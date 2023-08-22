/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sara
 */
public class Trening implements GenericEntity{
    
    private PlanTreninga planTreninga;
    private Vezba vezba;
    private Dan dan;
    private int rbVezbe;
    private int brSerija;
    private int brPonavljanja;
    private Status status;
    
    public Trening() {
    }

    public Trening(PlanTreninga planTreninga, Vezba vezba, Dan dan, int rbVezbe, int brSerija, int brPonavljanja) {
        this.planTreninga = planTreninga;
        this.vezba = vezba;
        this.dan = dan;
        this.rbVezbe = rbVezbe;
        this.brSerija = brSerija;
        this.brPonavljanja = brPonavljanja;
    }

    public PlanTreninga getPlanTreninga() {
        return planTreninga;
    }

    public void setPlanTreninga(PlanTreninga planTreninga) {
        this.planTreninga = planTreninga;
    }

    public Vezba getVezba() {
        return vezba;
    }

    public void setVezba(Vezba vezba) {
        this.vezba = vezba;
    }

    public Dan getDan() {
        return dan;
    }

    public void setDan(Dan dan) {
        this.dan = dan;
    }

    public int getRbVezbe() {
        return rbVezbe;
    }

    public void setRbVezbe(int rbVezbe) {
        this.rbVezbe = rbVezbe;
    }

    public int getBrSerija() {
        return brSerija;
    }

    public void setBrSerija(int brSerija) {
        this.brSerija = brSerija;
    }

    public int getBrPonavljanja() {
        return brPonavljanja;
    }

    public void setBrPonavljanja(int brPonavljanja) {
        this.brPonavljanja = brPonavljanja;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Trening{" + "planTreninga=" + planTreninga + ", vezba=" + vezba + ", dan=" + dan + ", rbVezbe=" + rbVezbe + ", brSerija=" + brSerija + ", brPonavljanja=" + brPonavljanja + ", status=" + status + '}';
    }

    @Override
    public String getTableName() {
       return "trening";
    }

    @Override
    public String getColumnNamesForInsert() {
      return "planTreningaID,vezbaID,dan,rbVezbe,brSerija,brPonavljanja";
    }

    @Override
    public String getInsertValues() {
       StringBuilder sb = new StringBuilder();
        sb.append(planTreninga.getTreningID()).append(",")         //da li dodati rbClana
                .append(vezba.getVezbaID()).append(",")
                .append("'").append(dan.toString()).append("',")
                .append(rbVezbe).append(",").append(brSerija).append(",").append(brPonavljanja);
        return sb.toString();
    }

    @Override
    public void setId(Long id) {
     
    }

    @Override
    public List<GenericEntity> createObjectRS(ResultSet rs) throws Exception {
       List<GenericEntity> lista=new ArrayList<>();
        
        while(rs.next()){
        Trening trening=new Trening();
        
           Vezba v=new Vezba();
           v.setVezbaID(rs.getLong("vezbaID"));
            trening.setVezba(v);
            //PlanIshrane pl=new PlanIshrane();
           // pl.setIshranaID(rs.getLong("ishranaID"));
            //ishrana.setPlanIshrane(pl);
            trening.setDan(Dan.valueOf(rs.getString("dan")));
            trening.setRbVezbe(rs.getInt("rbVezbe"));
            trening.setBrSerija(rs.getInt("brSerija"));
            trening.setBrPonavljanja(rs.getInt("brPonavljanja"));
            lista.add(trening);
        }
        return lista;
    }

    @Override
    public String getConditionForOne() {
      return "planTreningaID="+planTreninga.getTreningID();
    }

    @Override
    public String getConditionUpdateDelete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GenericEntity createOneObjectRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getConditionSetEdit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}

