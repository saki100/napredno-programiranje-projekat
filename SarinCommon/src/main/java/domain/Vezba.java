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
public class Vezba implements GenericEntity{
    
    private long vezbaID;
    private String naziv;

    public Vezba() {
    }

    public Vezba(long vezbaID, String naziv) {
        this.vezbaID = vezbaID;
        this.naziv = naziv;
    }

    public long getVezbaID() {
        return vezbaID;
    }

    public void setVezbaID(long vezbaID) {
        this.vezbaID = vezbaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

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

