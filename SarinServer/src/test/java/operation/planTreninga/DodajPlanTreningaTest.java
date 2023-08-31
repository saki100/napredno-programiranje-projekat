package operation.planTreninga;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Clan;
import domain.PlanIshrane;
import domain.PlanTreninga;
import domain.TipPlanaIshrane;
import domain.TipPlanaTreninga;
import form.DBConfigModel;
import operation.planIshrane.DodajPlanIshrane;
import operation.planIshrane.PretraziPlanoveIshrane;
import repository.db.DbConnectionFactory;

public class DodajPlanTreningaTest {

	private static DodajPlanTreninga dodajPlanTreninga;
	private PlanTreninga planTreninga;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dodajPlanTreninga=new DodajPlanTreninga();
		
		DBConfigModel dbConfigModel = new DBConfigModel();
		dbConfigModel.setUrl("jdbc:mysql://localhost:3306/sportski_klub_test");
		dbConfigModel.setUsername("root");
		dbConfigModel.setPassword("");
		ObjectMapper objectMapper = new ObjectMapper();
    	
    	BufferedWriter bufferedWriter =Files.newBufferedWriter(Paths.get("dbconfigJson.txt"), StandardOpenOption.TRUNCATE_EXISTING);
    	bufferedWriter.write(objectMapper.writeValueAsString(dbConfigModel));
    	bufferedWriter.flush();
    	bufferedWriter.close();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		DBConfigModel dbConfigModel = new DBConfigModel();
		dbConfigModel.setUrl("jdbc:mysql://localhost:3306/sportski_klub");
		dbConfigModel.setUsername("root");
		dbConfigModel.setPassword("");
		ObjectMapper objectMapper = new ObjectMapper();
    	
    	BufferedWriter bufferedWriter =Files.newBufferedWriter(Paths.get("dbconfigJson.txt"), StandardOpenOption.TRUNCATE_EXISTING);
    	bufferedWriter.write(objectMapper.writeValueAsString(dbConfigModel));
    	bufferedWriter.flush();
    	bufferedWriter.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		planTreninga=new PlanTreninga();
	}

	@AfterEach
	void tearDown() throws Exception {
		planTreninga=null;
	}

	@Test
	public void testExecute() throws SQLException, Exception {
		try {
			
			Clan clan = new Clan();
			clan.setRbClana(1);
			
			planTreninga.setClan(clan);
			planTreninga.setDatumOD(new Date());
			planTreninga.setDatumDO(new Date());
			planTreninga.setTip(TipPlanaTreninga.AEROBIK);
			
			dodajPlanTreninga.execute(planTreninga);
			
			PretraziPlanoveTreninga pretraziPlanoveTreninga = new PretraziPlanoveTreninga();
			pretraziPlanoveTreninga.execute(clan);
			List<PlanTreninga> vraceniPlanoviTreninga = pretraziPlanoveTreninga.getPlanovi();
			
			assertNotNull(vraceniPlanoviTreninga);
			assertFalse(vraceniPlanoviTreninga.isEmpty());
			assertEquals(1, vraceniPlanoviTreninga.size());
			assertEquals(planTreninga.getTreningID(), vraceniPlanoviTreninga.get(0).getTreningID());
			assertEquals(planTreninga.getTip(), vraceniPlanoviTreninga.get(0).getTip());
			
			String upit = "Delete from plantreninga where treningID=?";
			PreparedStatement ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(upit);
			ps.setLong(1, planTreninga.getTreningID());
			ps.execute(); 
			
			DbConnectionFactory.getInstance().getConnection().commit();
		}catch(Exception ex) {
			DbConnectionFactory.getInstance().getConnection().rollback();
			throw ex;
		}finally {
			DbConnectionFactory.getInstance().disconnect();
		}
	}

}
