package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Grad implements GenericEntity{
	
	private long gradID;
	private String nazivGrada;
	
	public Grad() {
		
	}
	
	public Grad(long gradID, String nazivGrada) {
		this.gradID = gradID;
		this.nazivGrada = nazivGrada;
	}

	
	public long getGradID() {
		return gradID;
	}

	public void setGradID(long gradID) {
		this.gradID = gradID;
	}

	public String getNazivGrada() {
		return nazivGrada;
	}

	public void setNazivGrada(String nazivGrada) {
		this.nazivGrada = nazivGrada;
	}

	@Override
	public String getTableName() {
		return "grad";
	}

	@Override
	public String getColumnNamesForInsert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInsertValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<GenericEntity> createObjectRS(ResultSet rs) throws Exception {
		List<GenericEntity> lista=new ArrayList<>();
        
        while(rs.next()){
        	Grad grad = new Grad();
        	
        	grad.setGradID(rs.getLong("gradID"));
        	grad.setNazivGrada(rs.getString("nazivGrada"));
        	lista.add(grad);
        	
        }
        return lista;
	}

	@Override
	public String getConditionForOne() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConditionUpdateDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericEntity createOneObjectRS(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConditionSetEdit() {
		// TODO Auto-generated method stub
		return null;
	}

}
