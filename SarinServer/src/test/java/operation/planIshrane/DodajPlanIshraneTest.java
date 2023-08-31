package operation.planIshrane;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Clan;
import domain.Obrok;
import domain.PlanIshrane;
import domain.TipPlanaIshrane;
import domain.Trener;
import form.DBConfigModel;
import operation.obrok.DodajObrok;
import operation.trener.UcitajTrenere;
import repository.db.DbConnectionFactory;
import repository.db.impl.RepositoryDBGeneric;

public class DodajPlanIshraneTest {

	private static DodajPlanIshrane dodajPlanIshrane;
	private PlanIshrane planIshrane;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dodajPlanIshrane=new DodajPlanIshrane();
		
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
		planIshrane=new PlanIshrane();
	}

	@AfterEach
	void tearDown() throws Exception {
		planIshrane=null;
	}

	@Test
	public void testExecute() throws SQLException, Exception {
		try {
			
			Clan clan = new Clan();
			clan.setRbClana(1l);
			planIshrane.setClan(clan);
			planIshrane.setTip(TipPlanaIshrane.BIO);
			Date datumOd = new Date();
			Date datumDo = new Date();
			planIshrane.setDatumOd(datumOd);
			planIshrane.setDatumDo(datumDo);
			
			dodajPlanIshrane.execute(planIshrane);
			
			PretraziPlanoveIshrane pretraziPlanoveIshrane = new PretraziPlanoveIshrane();
			pretraziPlanoveIshrane.execute(clan);
			List<PlanIshrane> vraceniPlanoviIshrane = pretraziPlanoveIshrane.getPlanoveIshrane();
			
			assertNotNull(vraceniPlanoviIshrane);
			assertFalse(vraceniPlanoviIshrane.isEmpty());
			assertEquals(1, vraceniPlanoviIshrane.size());
			
			PlanIshrane vraceniPlanIshrane = vraceniPlanoviIshrane.get(0);
			
			assertEquals(planIshrane.getIshranaID(), vraceniPlanIshrane.getIshranaID());
			assertEquals(planIshrane.getTip(), vraceniPlanIshrane.getTip());
			
			String upit  = "Delete from planishrane where ishranaID=?";
			PreparedStatement ps = DbConnectionFactory.getInstance().getConnection().prepareStatement(upit);
			
			ps.setLong(1, planIshrane.getIshranaID());
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
