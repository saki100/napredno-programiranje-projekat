/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja Trening kao agregiranu klasu klasa PlanTreninga i Vezba.
 * Trening ima svoj plan treninga, vezbu, dan kada ce se vezba izvoditi
 * i ova tri atributa cine primarni kljuc Treninga. Takodje sadrzi i podatke o rednom broju vezbe, broj serija, broju ponavljanja vezbe i statusu treninga.
 *
 * @author Sara
 */
public class Trening implements GenericEntity{
    /**
     * Plan treninga.
     */
    private PlanTreninga planTreninga;
    /**
     * Vezba koja ce se izvoditi.
     */
    private Vezba vezba;
    /**
     * Dan u kojem ce se vezba izvoditi.
     */
    private Dan dan;
    /**
     * Redni broj vezbe na treningu.
     */
    private int rbVezbe;
    /**
     * Broj serija izvodjenja vezbe.
     */
    private int brSerija;
    /**
     * Broj ponavljanja vezbe na treningu 
     */
    private int brPonavljanja;
    /**
     * Status treninga, moze biti INSERT, UPDATE ili DELETE.
     */
    private Status status;
    /**
     * Neparametrizovani konstruktor koji pravi nov prazan Trening.
     */
    public Trening() {
    }
    /**
     * Parametrizovani konstruktor koji inicijalizuje Trening i 
     * postavlja mu prosledjene vrednosti.
     * 
     * @param planTreninga  Plan treninga kao PlanTreninga.
     * @param vezba         Vezba kao Vezba.
     * @param dan           Dan kao Dan.
     * @param rbVezbe       Redni broj vezbe kao int.
     * @param brSerija      Broj serija kao int.
     * @param brPonavljanja Broj ponavljanja kao int.
     */
    public Trening(PlanTreninga planTreninga, Vezba vezba, Dan dan, int rbVezbe, int brSerija, int brPonavljanja) {
        /*this.planTreninga = planTreninga;
        this.vezba = vezba;
        this.dan = dan;
        this.rbVezbe = rbVezbe;
        this.brSerija = brSerija;
        this.brPonavljanja = brPonavljanja;*/
    	
    	setPlanTreninga(planTreninga);setVezba(vezba);setDan(dan);setRbVezbe(rbVezbe);setBrSerija(brSerija);setBrPonavljanja(brPonavljanja);
    }
    /**
     * Vraca plan treninga.
     * 
     * @return Plan treninga kao PlanTreninga.
     */
    public PlanTreninga getPlanTreninga() {
        return planTreninga;
    }
    /**
     * Postavlja plan treninga.
     * @param planTreninga Plan treninga za odredjenog clana sportskog kluba kao PlanTreninga.
     */
    public void setPlanTreninga(PlanTreninga planTreninga) {
    	if(planTreninga==null) throw new NullPointerException();
        this.planTreninga = planTreninga;
    }
   /**
    * Vraca vezbu koja ce se izvoditi.
    * 
    * @return Vezba kao Vezba.
    */
    public Vezba getVezba() {
        return vezba;
    }
    /**
     * Postavlja vezbu treninga.
     * 
     * @param vezba Vezba kao Vezba.
     */
    public void setVezba(Vezba vezba) {
    	if(vezba ==null) throw new NullPointerException();
    	 this.vezba = vezba;
    }
    /**
     * Vraca dan u nedelji kada ce se vezba izvoditi.
     * 
     * @return Dan u nedelji kao Dan.
     */
    public Dan getDan() {
        return dan;
    }
   /**
    * Postavlja dan u nedelji kada treba izvoditi vezbu.
    * 
    * @param dan Dan u nedelji kao Dan.
    */
    public void setDan(Dan dan) {
        this.dan = dan;
    }
    /**
     * Vraca redni broj vezbe.
     * 
     * @return Redni broj vezbe kao int.
     */
    public int getRbVezbe() {
        return rbVezbe;
    }
    /**
     * Postavlja redni broj vezbe.
     * 
     * @param rbVezbe Redni broj vezbe kao int.
     */
    public void setRbVezbe(int rbVezbe) {
        this.rbVezbe = rbVezbe;
    }
   /**
    * Vraca broj serija u kojem se vezba izvodi.
    * 
    * @return Broj serija kao int.
    */
    public int getBrSerija() {
        return brSerija;
    }
    /**
     * Postavlja broj serija u kojem ce se vezba izvoditi.
     * 
     * @param brSerija Broj serija kao int.
     */
    public void setBrSerija(int brSerija) {
        this.brSerija = brSerija;
    }
    /**
     * Vraca broj ponavljanja vezbe.
     * 
     * @return Broj ponavljanja vezbe kao int.
     */
    public int getBrPonavljanja() {
        return brPonavljanja;
    }
   /**
    * Postavlja broj ponavljanja vezbe.
    * 
    * @param brPonavljanja Broj ponavljanja vezbe kao int.
    */
    public void setBrPonavljanja(int brPonavljanja) {
    	if(brPonavljanja>20) throw new IllegalArgumentException("Broj ponavljanja ne moze biti veci 20.");
        this.brPonavljanja = brPonavljanja;
    }
    /**
     * Vraca status Treninga.
     * 
     * @return Status kao Status.
     */
    public Status getStatus() {
        return status;
    }
   /**
    * Postavlja status Treninga.
    * 
    * @param status Status kao Status.
    */
    public void setStatus(Status status) {
        this.status = status;
    }
    /**
     * Vraca String sa svim podacima o Treningu.
     * 
     *  @return Svi podaci o treningu.
     */
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

