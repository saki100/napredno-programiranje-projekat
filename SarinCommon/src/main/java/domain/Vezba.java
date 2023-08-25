/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja vezbu koji ce se izvoditi u odredjenom planu treninga za clanove sportskog kluba.
 * Vezba ima svoj id i naziv.
 * 
 * @author Sara
 */
public class Vezba implements GenericEntity{
    /**
     * Id vezbe.
     */
    private long vezbaID;
    /**
     * Naziv vezbe.
     */
    private String naziv;
    /**
     *  Neparametrizovani konstruktor koji pravi novu praznu vezbu.
     */
    public Vezba() {
    }
   /**
    * Parametrizovani konstruktor koji inicijalizuje vezbu i 
    * postavlja joj prosledjene vrednosti.
    * 
    * @param vezbaID   Id vezbe kao Long.
    * @param naziv     Naziv vezbe kao String.
    */
    public Vezba(long vezbaID, String naziv) {
        this.vezbaID = vezbaID;
        this.naziv = naziv;
    }
    /**
     * Vraca id vezbe.
     * 
     * @return Id vezbe kao Long.
     */
    public long getVezbaID() {
        return vezbaID;
    }
    /**
     * Postavlja id vezbe.
     * 
     * @param vezbaID Id vezbe kao Long.
     */
    public void setVezbaID(long vezbaID) {
        this.vezbaID = vezbaID;
    }
    /**
     * Vraca naziv vezbe kao String.
     * 
     * @return Naziv vezbe kao String.
     */
    public String getNaziv() {
        return naziv;
    }
    /**
     * Postavlja naziv vezbe.
     * 
     * @param naziv Naziv vezbe kao String.
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    /**
     * Vraca String o nazivu vezbe.
     * 
     * @return Naziv vezbe kao String.
     */
    @Override
    public String toString() {
        return  naziv ;
    }

    @Override
    public String getTableName() {
       return "vezba";
    }

    @Override
    public String getColumnNamesForInsert() {
      return null;
    }

    @Override
    public String getInsertValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<GenericEntity> createObjectRS(ResultSet rs) throws Exception {
          List<GenericEntity> lista=new ArrayList<>();
        
        while(rs.next()){
        	Vezba vezba=new Vezba();
        	
        	vezba.setVezbaID(rs.getLong("vezbaID"));
        	vezba.setNaziv(rs.getString("naziv"));
        	lista.add(vezba);
        	
        }
        return lista;
    }

    @Override
    public String getConditionForOne() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

