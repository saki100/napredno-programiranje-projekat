/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import domain.GenericEntity;
import java.util.ArrayList;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author Sara
 */
public class RepositoryDBGeneric implements DbRepository<GenericEntity> {

    @Override
    public void add(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getTableName())
                    .append(" (").append(entity.getColumnNamesForInsert()).append(")")
                    .append(" VALUES (")
                    .append(entity.getInsertValues())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rsKey = statement.getGeneratedKeys();
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                entity.setId(id);
            }
            statement.close();
            rsKey.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<GenericEntity> getAll(GenericEntity param) throws Exception {
         
        List<GenericEntity> listaIzBaze=new ArrayList<>();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ")
                .append(param.getTableName());
        String query = sb.toString();
         Statement statement = connection.createStatement();
         ResultSet rs=statement.executeQuery(query);
         listaIzBaze=param.createObjectRS(rs);
         statement.close(); rs.close();
         
         return listaIzBaze;
    }

    @Override
    public void edit(GenericEntity param) throws Exception {
     Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
              .append(param.getTableName())
              .append(" SET ")
              .append(param.getConditionSetEdit())
              .append(" WHERE ")
              .append(param.getConditionUpdateDelete());
     String query = sb.toString();
     System.out.println(query);
     Statement statement = connection.createStatement();
     statement.executeUpdate(query);
        statement.close();
              
    }

    @Override
    public void delete(GenericEntity param) throws Exception {
       Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(param.getTableName())
                    .append(" WHERE ")
                    .append(param.getConditionUpdateDelete());
        String query = sb.toString();
        System.out.println(query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
        
    }

    @Override
    public GenericEntity getObject(GenericEntity param) throws Exception {
         // ovo je kada petrazujem po necemu sto je jedinstveno i onda mi vrati samo jedan obj
        Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ")
                    .append(param.getTableName())
                    .append(" WHERE ")
                    .append(param.getConditionForOne());
            String query = sb.toString();
            System.out.println(query);
            
            Statement statement = connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            
            GenericEntity obj=param.createOneObjectRS(rs);  
            System.out.println("kreiran trener u brokeru");
            rs.close(); statement.close();
            return obj;
    }

    @Override
    public List<GenericEntity> getAllCondition(GenericEntity param) throws Exception {
          List<GenericEntity> listaIzBaze=new ArrayList<>();
        Connection connection = DbConnectionFactory.getInstance().getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ")
                .append(param.getTableName())
                .append(" WHERE ")
                .append(param.getConditionForOne());
        String query = sb.toString();
            System.out.println(query);
            
            Statement statement = connection.createStatement();
            ResultSet rs=statement.executeQuery(query);
        
            List<GenericEntity> vraceni=param.createObjectRS(rs);
            rs.close(); statement.close();
            return vraceni;
          
    }

    @Override
    public void addAsociation(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getTableName())
                    .append(" (").append(entity.getColumnNamesForInsert()).append(")")
                    .append(" VALUES (")
                    .append(entity.getInsertValues())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
          
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    

   

}

